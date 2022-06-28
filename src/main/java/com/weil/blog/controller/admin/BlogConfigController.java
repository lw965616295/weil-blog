package com.weil.blog.controller.admin;


import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogConfig;
import com.weil.blog.service.IBlogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 配置表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-28 14:55:13
 */
@Controller
@RequestMapping("/admin/config")
public class BlogConfigController {
    @Autowired
    private IBlogConfigService configService;
    /**
     * 配置页面
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 15:01
     */
    @GetMapping("")
    public String page(HttpServletRequest request){
        request.setAttribute("path", "config");
        request.setAttribute("config", configService.getNameValueMap());
        return "admin/config";
    }

    /**
     * 批量更新
     * @param list 集合
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 15:31
     */
    @PutMapping("")
    @ResponseBody
    public Result update(@RequestBody List<BlogConfig> list){
        return configService.updateBatch(list);
    }
}
