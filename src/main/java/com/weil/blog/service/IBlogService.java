package com.weil.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weil.blog.entity.dto.BlogDetailDto;

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

//----------------------------博客前端页--------------------------
    /**
     * 用于blog页查询分页
     */
    IPage<Blog> getList2(Long page, Long pageSize, String tag, String category, String search);

    /**
     * 查询最新1，最火2博客
     */
    List<Blog> getBlogForWeb(int i, Integer blogCount);

    /**
     * 获取博客详情页
     */
    BlogDetailDto getDetail(Long id);
}
