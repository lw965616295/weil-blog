package com.weil.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogTag;
import com.weil.blog.mapper.BlogTagMapper;
import com.weil.blog.service.IBlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-24 10:09:56
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements IBlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public IPage<BlogTag> getList(Long page, Long rows) {
        IPage<BlogTag> categoryPage = new Page<>();
        categoryPage.setCurrent(page);
        categoryPage.setPages(rows);
        return page(categoryPage, new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getIsDel, 0));
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        List<BlogTag> collect = ids.stream().map(p -> new BlogTag().setId(p).setIsDel(true)).collect(Collectors.toList());
        updateBatchById(collect);
    }

    @Override
    public Result saveTag(BlogTag tag) {
        List<BlogTag> list = list(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getName, tag.getName()).eq(BlogTag::getIsDel, 0));
        if(CollectionUtils.isEmpty(list)){
            // 不存在则新增
            save(tag);
        }
        return Result.success("操作成功！");
    }

    @Override
    public BlogTag getTagByName(String tagName) {
        List<BlogTag> list = list(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getName, tagName).eq(BlogTag::getIsDel, 0));
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public Long getTotalTagCount() {
        return count(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getIsDel, 0));
    }

    @Override
    public List<BlogTag> getHotTag(int i) {
        List<BlogTag> tag = blogTagMapper.getHotTag(i);
        return tag;
    }
}
