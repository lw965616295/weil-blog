package com.weil.blog.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogComment;
import com.weil.blog.service.IBlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 博客评论表 前端控制器
 * </p>
 *
 * @author weil
 * @since 2022-06-27 16:30:19
 */
@Controller
@RequestMapping("/admin/comment")
public class BlogCommentController {
    @Autowired
    private IBlogCommentService commentService;
    /**
     * 评论页
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/27 16:31
     */
    @GetMapping("")
    public String page(HttpServletRequest request){
        request.setAttribute("path", "comment");
        return "admin/comment";
    }

    /**
     * 分页查询评论列表
     * @param page 当前页
     * @param rows 每页数
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/27 16:46
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<BlogComment> list(Long page, Long rows){
        IPage<BlogComment> pages = commentService.getList(page, rows);
        return Result.success(pages);
    }
    /**
     * 批量审批
     * @param ids
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/27 17:21
     */
    @PostMapping("/audit")
    @ResponseBody
    public Result audit(@RequestBody List<Long> ids){
        return commentService.audit(ids);
    }

    /**
     * 回复
     * @param comment
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/27 17:21
     */
    @PostMapping("/reply")
    @ResponseBody
    public Result reply(@RequestBody BlogComment comment){
        return commentService.reply(comment);
    }
    /**
     * 批量删除评论信息
     * @param ids id集合
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/27 17:04
     */
    @DeleteMapping("")
    @ResponseBody
    public Result del(@RequestBody List<Long> ids){
        if(ids == null || ids.isEmpty()){
            return Result.fail("参数为空！");
        }
        return commentService.deleteByIds(ids);
    }

    /**
     * 提交评论
     * @param null
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/30 16:44
     */
    @PostMapping("")
    @ResponseBody
    public Result save(HttpServletRequest request, @RequestBody BlogComment comment){
        comment.setCommentatorIp(request.getRemoteAddr());
        return commentService.saveComment(comment);
    }
}
