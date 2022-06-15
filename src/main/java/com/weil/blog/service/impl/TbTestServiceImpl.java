package com.weil.blog.service.impl;

import com.weil.blog.entity.TbTest;
import com.weil.blog.mapper.TbTestMapper;
import com.weil.blog.service.ITbTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-15 11:08:11
 */
@Service
public class TbTestServiceImpl extends ServiceImpl<TbTestMapper, TbTest> implements ITbTestService {

}
