package com.weil.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客表 服务类
 * </p>
 *
 * @author weil
 * @since 2022-06-22 17:20:31
 */
public interface IBlogService extends IService<Blog> {

    /**
     * 分页查询
     * @param page 当前页
     * @param rows 每页数
     * @param keyword 查询关键字(标题/分类)
     * @param sortField 排序字段
     * @param order 排序方式
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/23 9:06
     */
    IPage<Blog> getList(Long page, Long rows, String keyword, String sortField, String order);

    /**
     * 新增或者更新
     */
    Result saveOrUpdateBlog(Blog blog);

    /**
     * 批量逻辑删除
     */
    Result delBatch(List<Long> ids);

    /**
     * 获取所有博客数
     */
    Long getTotalBlogCount();
}
