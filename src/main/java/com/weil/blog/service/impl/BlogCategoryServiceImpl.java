package com.weil.blog.service.impl;

import com.weil.blog.entity.BlogCategory;
import com.weil.blog.mapper.BlogCategoryMapper;
import com.weil.blog.service.IBlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
