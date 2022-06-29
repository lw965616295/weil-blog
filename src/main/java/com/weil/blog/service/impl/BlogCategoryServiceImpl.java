package com.weil.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weil.blog.entity.BlogCategory;
import com.weil.blog.mapper.BlogCategoryMapper;
import com.weil.blog.service.IBlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客分类表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-22 09:25:13
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements IBlogCategoryService {
    @Override
    public IPage<BlogCategory> getList(Long page, Long rows) {
        IPage<BlogCategory> categoryPage = new Page<>();
        categoryPage.setCurrent(page);
        categoryPage.setPages(rows);
        return page(categoryPage, new LambdaQueryWrapper<BlogCategory>().eq(BlogCategory::getIsDel, 0).orderByDesc(BlogCategory::getWeight));
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        List<BlogCategory> collect = ids.stream().map(p -> new BlogCategory().setIsDel(true).setId(p)).collect(Collectors.toList());
        updateBatchById(collect);
    }

    @Override
    public List<BlogCategory> getAll() {
        return list(new LambdaQueryWrapper<BlogCategory>().eq(BlogCategory::getIsDel, 0).orderByDesc(BlogCategory::getWeight));
    }

    @Override
    public Long getTotalCategoryCount() {
        return count(new LambdaQueryWrapper<BlogCategory>().eq(BlogCategory::getIsDel, 0));
    }
}
