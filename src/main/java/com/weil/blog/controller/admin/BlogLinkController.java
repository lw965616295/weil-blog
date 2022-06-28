package com.weil.blog.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogLink;
import com.weil.blog.service.IBlogLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 友链表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-28 10:30:57
 */
@Controller
@RequestMapping("/admin/link")
public class BlogLinkController {
    @Autowired
    private IBlogLinkService linkService;
    /**
     * 友链页面
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 10:32
     */
    @GetMapping("")
    public String page(HttpServletRequest request){
        request.setAttribute("path", "link");
        return "admin/link";
    }

    /**
     * 分页查询友链信息
     * @param page 当前页
     * @param rows 每页数
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 13:35
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<BlogLink> list(Long page, Long rows){
        IPage<BlogLink> pages = linkService.getList(page, rows);
        return Result.success(pages);
    }

    /**
     * 通过id查询链接
     * @param id
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 14:05
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Result<BlogLink> getLink(@PathVariable("id") Integer id){
        return Result.success(linkService.getById(id));
    }

    /**
     * 新增或者更新友链
     * @param link 友链对象
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 13:36
     */
    @PostMapping("")
    @ResponseBody
    public Result save(@Validated @RequestBody BlogLink link){
        return linkService.saveOrUpdateLink(link);
    }

    /**
     * 批量逻辑删除
     * @param ids id集合
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/28 13:36
     */
    @DeleteMapping("")
    @ResponseBody
    public Result del(@RequestBody List<Integer> ids){
        if(ids == null || ids.isEmpty()){
            return Result.fail("参数为空！");
        }
        return linkService.deleteByIds(ids);
    }
}
