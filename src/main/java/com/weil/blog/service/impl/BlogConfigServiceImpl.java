package com.weil.blog.service.impl;

import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogConfig;
import com.weil.blog.mapper.BlogConfigMapper;
import com.weil.blog.service.IBlogConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-28 14:55:13
 */
@Service
public class BlogConfigServiceImpl extends ServiceImpl<BlogConfigMapper, BlogConfig> implements IBlogConfigService {

    @Override
    public Map<String, Object> getNameValueMap() {
        Map<String, Object> map = new HashMap<>();
        for (BlogConfig blogConfig : list()) {
            map.put(blogConfig.getName(), blogConfig.getValue());
        }
        return map;
    }

    @Override
    public Result updateBatch(List<BlogConfig> list) {
        if (updateBatchById(list)) {
            return Result.success("操作成功！");
        }
        return Result.fail("更新失败！");
    }
}
