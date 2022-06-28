package com.weil.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客评论表 服务类
 * </p>
 *
 * @author weil
 * @since 2022-06-27 16:30:19
 */
public interface IBlogCommentService extends IService<BlogComment> {
    /**
     * 分页查询分类记录
     */
    IPage<BlogComment> getList(Long page, Long rows);

    /**
     * 审核
     */
    Result audit(List<Long> ids);

    /**
     * 回复评论（审核状态的）
     */
    Result reply(BlogComment comment);

    /**
     * 批量逻辑删除
     */
    Result deleteByIds(List<Long> ids);

}
