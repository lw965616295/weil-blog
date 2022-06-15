package com.weil.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weil.blog.entity.TbTest;
import com.weil.blog.mapper.TbTestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName TestDemo
 * @Author weil
 * @Description //测试类
 * @Date 2022/6/15 11:01
 * @Version 1.0.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {
    @Autowired
    private TbTestMapper testMapper;
    @Test
    public void test1(){
        List<TbTest> tbTests = testMapper.selectList(new QueryWrapper<>());
        System.out.println(tbTests);
    }
}
