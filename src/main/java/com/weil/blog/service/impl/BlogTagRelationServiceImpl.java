package com.weil.blog.service.impl;

import com.weil.blog.entity.BlogTagRelation;
import com.weil.blog.mapper.BlogTagRelationMapper;
import com.weil.blog.service.IBlogTagRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客-标签关系表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-24 13:27:00
 */
@Service
public class BlogTagRelationServiceImpl extends ServiceImpl<BlogTagRelationMapper, BlogTagRelation> implements IBlogTagRelationService {

}
