package com.weil.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weil.blog.common.Result;
import com.weil.blog.entity.Blog;
import com.weil.blog.entity.BlogCategory;
import com.weil.blog.entity.BlogTag;
import com.weil.blog.entity.BlogTagRelation;
import com.weil.blog.mapper.BlogCategoryMapper;
import com.weil.blog.mapper.BlogMapper;
import com.weil.blog.service.IBlogService;
import com.weil.blog.service.IBlogTagRelationService;
import com.weil.blog.service.IBlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-22 17:20:31
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    BlogCategoryMapper categoryMapper;
    @Autowired
    IBlogTagService tagService;
    @Autowired
    IBlogTagRelationService relationService;
    @Override
    public IPage<Blog> getList(Long page, Long rows, String keyword, String sortField, String order) {
        Page<Blog> blogPage = new Page<>();
        blogPage.setPages(page).setSize(rows);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)){
            queryWrapper.like("title", keyword)
                    .like("category_name", keyword);
        }
        if(!StringUtils.isEmpty(sortField)){
            queryWrapper.orderBy(true, "asc".equals(order), sortField);
        }else {
            queryWrapper.orderByDesc("create_date");
        }

        return page(blogPage, queryWrapper);
    }

    @Override
    @Transactional
    public Result saveOrUpdateBlog(Blog blog) {
        // 分类名查询
        BlogCategory blogCategory = categoryMapper.selectById(blog.getCategoryId());
        if(blogCategory == null){
            // 默认分类
            blog.setCategoryId(1);
            blog.setCategoryName("默认分类");
        }else {
            blog.setCategoryName(blogCategory.getName());
            // 更新分类权重
            categoryMapper.updateById(blogCategory.setWeight(blogCategory.getWeight() + 1));
        }
        // 标签解析
        String tags = blog.getTags();
        String[] tagArr = tags.split(",");
        if(tagArr.length > 6){
            return Result.fail("操作失败，标签数太多！");
        }
        // 保存需要新增的标签
        List<BlogTag> tagNeedAdd = new ArrayList<>();
        // 保存已存在的标签
        List<BlogTag> tagList = new ArrayList<>();
        for (String tagName : tagArr) {
            BlogTag tag = tagService.getTagByName(tagName);
            if(tag == null){
                // 新增
                tagNeedAdd.add(new BlogTag().setName(tagName));
            }else {
                tagList.add(tag);
            }
        }
        // 批量操作保存标签
        tagService.saveBatch(tagNeedAdd);
        // 保存博客
        if(blog.getId() == null){
            // 新增
            save(blog);
        }else {
            // 更新
            updateById(blog);
        }

        // 上面的操作最终会将id赋值上，再加入tagList
        tagList.addAll(tagNeedAdd);
        // 保存博客-标签关系
        List<BlogTagRelation> relations = tagList.stream().map(p -> new BlogTagRelation().setBlogId(blog.getId()).setTagId(p.getId())).collect(Collectors.toList());
        relationService.saveBatch(relations);
        return Result.success("操作成功！");
    }
}
