package com.weil.blog.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 测试表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-15 11:08:11
 */
@Controller
@RequestMapping("/tb-test")
@Api(tags = "测试controller")
public class TbTestController {
    @ApiOperation("测试方法接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "名称", defaultValue = "weil"),
            @ApiImplicitParam(name = "age", value = "年龄", defaultValue = "1")
    })
    @GetMapping("/test")
    public void test1(String name, Integer age){

    }
}
