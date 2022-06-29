package com.weil.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogLink;

import java.util.List;

/**
 * <p>
 * 友链表 服务类
 * </p>
 *
 * @author weil
 * @since 2022-06-28 10:30:57
 */
public interface IBlogLinkService extends IService<BlogLink> {
    /**
     * 分页查询
     */
    IPage<BlogLink> getList(Long page, Long rows);

    /**
     * 新增或者更新
     */
    Result saveOrUpdateLink(BlogLink link);

    /**
     * 批量删除
     */
    Result deleteByIds(List<Integer> ids);

    /**
     * 所有友链数
     */
    Long getTotalLinkCount();
}
