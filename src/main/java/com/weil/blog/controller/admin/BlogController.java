package com.weil.blog.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.Blog;
import com.weil.blog.service.IBlogCategoryService;
import com.weil.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 博客表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-22 17:20:31
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IBlogCategoryService blogCategoryService;

    /**
     * 跳转页面
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/23 9:01
     */
    @GetMapping("")
    public String page(HttpServletRequest request){
        request.setAttribute("path", "blog");
        return "admin/blog";
    }

    /**
     * 分页查询
     * @param page 当前页
     * @param rows 每页数
     * @param keyword 查询关键字(标题/分类)
     * @param sortField 排序字段
     * @param order 排序方式
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/23 9:09
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<Blog> list(Long page, Long rows, String keyword, String sortField, String order){
        IPage<Blog> pages = blogService.getList(page, rows, keyword, sortField, order);
        return Result.success(pages);
    }

    /**
     * 博客明细页面
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/23 9:37
     */
    @GetMapping("/detail")
    public String detail(HttpServletRequest request, @RequestParam(required = false) Long id){
        request.setAttribute("path", "detail");
        // 分类集合
        request.setAttribute("categories", blogCategoryService.getAll());
        // 博客信息
        if(id != null){
            request.setAttribute("blog", blogService.getById(id));
        }
        return "admin/detail";
    }

    /**
     * 新增或更新博客
     * @param blog 博客对象
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/23 15:12
     */
    @PostMapping("")
    @ResponseBody
    public Result saveOrUpdate(@Validated @RequestBody Blog blog){
        return blogService.saveOrUpdateBlog(blog);
    }

    /**
     * 批量逻辑删除博客
     * @param ids 博客id集合
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/27 15:31
     */
    @DeleteMapping("")
    @ResponseBody
    public Result del(@RequestBody List<Long> ids){
        return blogService.delBatch(ids);
    }
}
