package com.weil.blog.controller.admin;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-15 15:21:15
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
    @GetMapping("/get")
    @ResponseBody
    public String get(){
        return "ss";
    }

}
