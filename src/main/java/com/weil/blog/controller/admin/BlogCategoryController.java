package com.weil.blog.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogCategory;
import com.weil.blog.service.IBlogCategoryService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 博客分类表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-22 09:25:13
 */
@Controller
@RequestMapping("/admin/category")
public class BlogCategoryController {
    @Autowired
    private IBlogCategoryService categoryService;
    /**
     * 分类页
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/22 9:51
     */
    @GetMapping("")
    public String page(HttpServletRequest request){
        request.setAttribute("path", "category");
        return "admin/category";
    }

    /**
     * 分页查询分类列表
     * @param page 当前页
     * @param rows 每页数
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/22 10:32
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<BlogCategory> list(Long page, Long rows){
        IPage<BlogCategory> categoryPage = new Page<>();
        categoryPage.setCurrent(page);
        categoryPage.setPages(rows);
        IPage<BlogCategory> pages = categoryService.page(categoryPage);
        return Result.success(pages);
    }
    /**
     * 保存或者更新分类信息
     * @param category 分类对象
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/22 15:44
     */
    @PostMapping("")
    @ResponseBody
    public Result saveOrUpdate(@RequestBody BlogCategory category){
        categoryService.saveOrUpdate(category);
        return Result.success("操作成功！");
    }
    /**
     * 批量删除分类信息
     * @param ids 分类id集合
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/22 15:45
     */
    @DeleteMapping("")
    @ResponseBody
    public Result del(@RequestBody List<Integer> ids){
        if(ids == null || ids.isEmpty()){
            return Result.fail("参数为空！");
        }
        categoryService.removeBatchByIds(ids);
        return Result.success("操作成功！");
    }
}
