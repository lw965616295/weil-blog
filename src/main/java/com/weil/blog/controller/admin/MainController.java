package com.weil.blog.controller.admin;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.weil.blog.entity.User;
import com.weil.blog.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @ClassName MainController
 * @Author weil
 * @Description //主控制
 * @Date 2022/6/15 15:37
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/admin")
@Slf4j
public class MainController {
    /**
     * 验证码接口
     */
    @Autowired
    private DefaultKaptcha captcha;
    /**
     * 用户接口
     */
    @Autowired
    private IUserService userService;
    /**
     * 请求登录页
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/15 15:38
     */
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    /**
     * 获取验证码
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/20 16:27
     */
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        // 生成文本存入session
        String text = captcha.createText();
        log.info("captcha：{}", text);
        request.getSession().setAttribute("captcha", text);
        // 生成图像
        BufferedImage image = captcha.createImage(text);
        response.setContentType("image/png");
        try (ServletOutputStream outputStream = response.getOutputStream()){
            ImageIO.write(image, "png", outputStream);
        }catch (Exception e){
            log.error("验证码获取错误，"+e.getMessage(), e);
        }
    }
    /**
     * 登录操作
     * @param username
     * @param password
     * @param captcha
     * @param session
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/20 16:30
     */
    @PostMapping("/login")
    public String login(String username, String password, String captcha, HttpSession session){
        if (StringUtils.isEmpty(captcha)) {
            session.setAttribute("msg", "验证码不能为空!");
            return "admin/login";
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            session.setAttribute("msg", "验证码不能为空!");
            return "admin/login";
        }
        String code = session.getAttribute("captcha") + "";
        if (StringUtils.isEmpty(code) || !captcha.equals(code)) {
            session.setAttribute("msg", "验证码错误!");
            return "admin/login";
        }
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("nickname", user.getNickName());
            return "redirect:/admin/index";
        } else {
            session.setAttribute("msg", "登陆失败，账号或者密码错误！");
            return "admin/login";
        }
    }
    /**
     * 访问主页
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/21 10:55
     */
    @GetMapping({"","/","/index","index.html"})
    public String index(HttpServletRequest request){
        // 左侧栏菜单dashboard选中状态
        request.setAttribute("path", "index");
        // 各维度数据展示
        return "admin/index";
    }
}
