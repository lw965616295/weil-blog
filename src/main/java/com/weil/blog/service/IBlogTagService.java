package com.weil.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author weil
 * @since 2022-06-24 10:09:56
 */
public interface IBlogTagService extends IService<BlogTag> {
    /**
     * 分页查询标签信息
     */
    IPage<BlogTag> getList(Long page, Long rows);

    /**
     * 批量逻辑删除
     */
    @CacheEvict(cacheNames = "blog:tag", allEntries = true)
    void deleteByIds(List<Integer> ids);

    /**
     * 新增标签，保证标签唯一
     */
    Result saveTag(BlogTag tag);

    /**
     * 查询标签根据名称
     */
    @Cacheable(cacheNames = "blog:tag", key = "#tagName")
    BlogTag getTagByName(String tagName);

    /**
     * 所有标签数
     */
    Long getTotalTagCount();

    /**
     * 热门标签，前i名
     */
    List<BlogTag> getHotTag(int i);
}
