package com.weil.blog.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogCategory;
import com.weil.blog.entity.BlogTag;
import com.weil.blog.service.IBlogCategoryService;
import com.weil.blog.service.IBlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-24 10:09:56
 */
@Controller
@RequestMapping("/admin/tag")
public class BlogTagController {
    @Autowired
    private IBlogTagService tagService;
    /**
     * 标签页面
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/24 10:12
     */
    @GetMapping("")
    public String page(HttpServletRequest request){
        request.setAttribute("path", "tag");
        return "admin/tag";
    }

    /**
     * 分页查询标签信息
     * @param page 当前页
     * @param rows 每页数
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/24 10:15
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<BlogTag> list(Long page, Long rows){
        IPage<BlogTag> pages = tagService.getList(page, rows);
        return Result.success(pages);
    }
    /**
     * 新增标签
     * @param tag 标签对象
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/24 10:19
     */
    @PostMapping("")
    @ResponseBody
    public Result save(@Validated @RequestBody BlogTag tag){
        return tagService.saveTag(tag);
    }
    /**
     * 批量逻辑删除标签
     * @param ids 标签id集合
     * @Return: 
     * @Auther: weil
     * @Date: 2022/6/24 10:22
     */
    @DeleteMapping("")
    @ResponseBody
    public Result del(@RequestBody List<Integer> ids){
        if(ids == null || ids.isEmpty()){
            return Result.fail("参数为空！");
        }
        tagService.deleteByIds(ids);
        return Result.success("操作成功！");
    }
}
