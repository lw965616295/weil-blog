package com.weil.blog.controller.admin;


import com.weil.blog.common.Result;
import com.weil.blog.entity.User;
import com.weil.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-15 15:21:15
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private IUserService userService;
    /**
     * 用户信息页
     * @param request
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 16:32
     */
    @GetMapping("/profile")
    public String page(HttpServletRequest request){
        request.setAttribute("path", "profile");
        return "admin/profile";
    }

    /**
     * 修改账号名或昵称
     * @param name 账号
     * @param nickName 昵称
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 16:33
     */
    @PutMapping("/profile/name")
    @ResponseBody
    public Result updateName(HttpServletRequest request, String name, String nickName){
        User user = (User) request.getSession().getAttribute("user");
        return userService.updateName(user.setName(name).setNickName(nickName));
    }
    /**
     * 更新密码
     * @param originalPassword 初始密码
     * @param newPassword 新密码
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 16:48
     */
    @PutMapping("/profile/password")
    @ResponseBody
    public Result updatePwd(HttpServletRequest request, String originalPassword, String newPassword){
        User user = (User) request.getSession().getAttribute("user");
        return userService.updatePwd(user, originalPassword, newPassword);
    }
}
