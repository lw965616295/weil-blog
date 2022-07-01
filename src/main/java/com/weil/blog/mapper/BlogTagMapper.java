package com.weil.blog.mapper;

import com.weil.blog.entity.BlogTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author weil
 * @since 2022-06-24 10:09:56
 */
public interface BlogTagMapper extends BaseMapper<BlogTag> {

    List<BlogTag> getHotTag(@Param("i") int i);
}
