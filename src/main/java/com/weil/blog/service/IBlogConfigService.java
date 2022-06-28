package com.weil.blog.service;

import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author weil
 * @since 2022-06-28 14:55:13
 */
public interface IBlogConfigService extends IService<BlogConfig> {

    /**
     * 获取name-value map值
     */
    Map<String, Object> getNameValueMap();

    /**
     * 批量更新
     */
    Result updateBatch(List<BlogConfig> list);
}
