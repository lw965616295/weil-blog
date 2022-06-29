package com.weil.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.entity.BlogCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 博客分类表 服务类
 * </p>
 *
 * @author weil
 * @since 2022-06-22 09:25:13
 */
public interface IBlogCategoryService extends IService<BlogCategory> {
    /**
     * 分页查询分类记录
     */
    IPage<BlogCategory> getList(Long page, Long rows);

    /**
     * 逻辑批量删除
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 不分页查询所有分类信息
     */
    List<BlogCategory> getAll();

    /**
     * 获取所有分类数
     */
    Long getTotalCategoryCount();
}
