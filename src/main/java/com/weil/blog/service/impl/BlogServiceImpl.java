package com.weil.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weil.blog.common.Result;
import com.weil.blog.entity.*;
import com.weil.blog.entity.dto.BlogDetailDto;
import com.weil.blog.mapper.BlogCategoryMapper;
import com.weil.blog.mapper.BlogCommentMapper;
import com.weil.blog.mapper.BlogMapper;
import com.weil.blog.service.IBlogService;
import com.weil.blog.service.IBlogTagRelationService;
import com.weil.blog.service.IBlogTagService;
import com.weil.blog.utils.BlogUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
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
    @Autowired
    BlogCommentMapper commentMapper;
    @Override
    public IPage<Blog> getList(Long page, Long rows, String keyword, String sortField, String order) {
        Page<Blog> blogPage = new Page<>();
        blogPage.setPages(page).setSize(rows);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)){
            queryWrapper.like("title", keyword)
                    .like("category_name", keyword);
        }
        queryWrapper.eq("is_del", false);
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

    @Override
    public Result delBatch(List<Long> ids) {
        List<Blog> list = ids.stream().map(id -> new Blog().setId(id).setIsDel(true)).collect(Collectors.toList());
        if(updateBatchById(list)){
            return Result.success("操作成功！");
        }
        return Result.fail("批量删除错误！");
    }

    @Override
    public Long getTotalBlogCount() {
        return count(new LambdaQueryWrapper<Blog>().eq(Blog::getIsDel, 0));
    }

    @Override
    public IPage<Blog> getList2(Long page, Long pageSize, String tag, String category, String search) {
        Page<Blog> blogPage = new Page<>();
        blogPage.setPages(page).setSize(pageSize);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", false);
        queryWrapper.eq("status", true);
        if(!StringUtils.isEmpty(search)){
            queryWrapper.like("title", search)
                    .like("category_name", search)
                    .like("tags", search);
        }
        if(!StringUtils.isEmpty(tag)){
            queryWrapper.like("tags", tag);
        }
        if(!StringUtils.isEmpty(category)){
            queryWrapper.eq("category_name", category);
        }
        return page(blogPage, queryWrapper);
    }

    @Override
    public List<Blog> getBlogForWeb(int i, Integer blogCount) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<Blog>().eq(Blog::getIsDel, false)
                .eq(Blog::getStatus, true);
        if(1==i){
            // 最新
            wrapper.orderByDesc(Blog::getCreateDate);
        }else if(2==i){
            // 最火
            wrapper.orderByDesc(Blog::getViews);
        }
        wrapper.last("limit "+blogCount);
        return list(wrapper);
    }

    @Override
    public BlogDetailDto getDetail(Long id) {
        Blog blog = getById(id);
        // 更新阅读量
        updateById(new Blog().setId(id).setViews(blog.getViews()+1));
        // 封装dto
        BlogDetailDto detailDto = new BlogDetailDto();
        BeanUtils.copyProperties(blog, detailDto);
        detailDto.setViews(detailDto.getViews()+1);
        // markdown转html
        detailDto.setContent(BlogUtil.mdToHtml(blog.getContent()));
        BlogCategory blogCategory = categoryMapper.selectById(blog.getCategoryId());
        if (blogCategory != null) {
            //分类信息
            detailDto.setCategoryIcon(blogCategory.getIcon());
        }else {
            detailDto.setCategoryIcon("/admin/dist/img/category/00.png");
        }
        if(!StringUtils.isEmpty(blog.getTags())){
            detailDto.setBlogTags(Arrays.asList(blog.getTags().split(",")));
        }
        //设置评论数
        detailDto.setCommentCount(commentMapper.selectCount(new LambdaQueryWrapper<BlogComment>()
                .eq(BlogComment::getBlogId, id)
                .eq(BlogComment::getStatus, true)
                .eq(BlogComment::getIsDel, false)));
        return detailDto;
    }
}
