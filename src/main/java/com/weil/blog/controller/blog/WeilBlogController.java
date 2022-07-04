package com.weil.blog.controller.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.common.Result;
import com.weil.blog.entity.Blog;
import com.weil.blog.entity.BlogComment;
import com.weil.blog.entity.BlogLink;
import com.weil.blog.entity.dto.BlogDetailDto;
import com.weil.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WeilBlogController
 * @Author weil
 * @Description //博客页
 * @Date 2022/6/29 15:46
 * @Version 1.0.0
 **/
@Controller
public class WeilBlogController {

    public static String theme = "amaze";
    // 每页展示博客数
    public static Long pageSize = 8L;
    // 展示热门标签数
    public static Integer hotTag = 20;
    // 最热，最火博客显示数
    public static Integer blogCount = 8;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IBlogConfigService configService;
    @Autowired
    private IBlogTagService tagService;
    @Autowired
    private IBlogCommentService commentService;
    @Autowired
    private IBlogLinkService linkService;
    /**
     * 博客首页
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/29 15:49
     */
    @GetMapping({"/", "/index", "/index.html"})
    public String index(HttpServletRequest request){
        return this.page(request, "index", 1L, null);
    }

    /**
     * 分页展示
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/29 15:51
     */
    @GetMapping("/blog/{type}/{page}")
    public String page(HttpServletRequest request, @PathVariable("type") String type,
                       @PathVariable("page") Long page,
                       @RequestParam(required = false) String search) {
        IPage<Blog> blogIPage = null;
        switch (type){
            case "index":
                blogIPage = blogService.getList2(page, pageSize, null, null, search);
                request.setAttribute("pageName", "首页");
                request.setAttribute("type", type);
                break;
            case "category":
                blogIPage = blogService.getList2(page, pageSize, null, search, null);
                request.setAttribute("pageName", "分类");
                request.setAttribute("type", type);
                break;
            case "tag":
                blogIPage = blogService.getList2(page, pageSize, search, null, null);
                request.setAttribute("pageName", "标签");
                request.setAttribute("type", type);
                break;
        }
        request.setAttribute("blogPageResult", blogIPage);
        request.setAttribute("newBlogs", blogService.getBlogForWeb(1, blogCount));
        request.setAttribute("hotBlogs", blogService.getBlogForWeb(2, blogCount));
        request.setAttribute("hotTags", tagService.getHotTag(hotTag));

        request.setAttribute("configurations", configService.getNameValueMap());
        return "blog/" + theme + "/index";
    }

    @GetMapping("/blog/detail")
    public String detail(HttpServletRequest request, @RequestParam Long id, @RequestParam(required = false, defaultValue = "1") Long commentPage){
        BlogDetailDto detailDto = blogService.getDetail(id);
        if (detailDto != null) {
            request.setAttribute("detailDto", detailDto);
            request.setAttribute("commentPageResult", commentService.getList2(commentPage, 8L, id));
        }
        request.setAttribute("pageName", "详情");
        request.setAttribute("configurations", configService.getNameValueMap());
        return "blog/" + theme + "/detail";
    }
    /**
     * 提交评论
     * @param comment
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/30 16:44
     */
    @PostMapping("/blog/comment")
    @ResponseBody
    public Result saveComment(HttpServletRequest request, @RequestBody BlogComment comment){
        String code = request.getSession().getAttribute("captcha") + "";
        if (StringUtils.isEmpty(code) || !code.equals(comment.getVerifyCode())) {
            return Result.fail("验证码错误！");
        }
        comment.setCommentatorIp(request.getRemoteAddr());
        return commentService.saveComment(comment);
    }

    /**
     * 友链页
     * @param request
     * @Return:
     * @Auther: weil
     * @Date: 2022/7/1 11:01
     */
    @GetMapping({"/link"})
    public String link(HttpServletRequest request) {
        request.setAttribute("pageName", "友情链接");
        Map<Integer, List<BlogLink>> linkMap = linkService.getLinksForWeb();
        if (linkMap != null) {
            //判断友链类别并封装数据 0-友链 1-推荐 2-个人网站
            if (linkMap.containsKey(0)) {
                request.setAttribute("favoriteLinks", linkMap.get(0));
            }
            if (linkMap.containsKey(1)) {
                request.setAttribute("recommendLinks", linkMap.get(1));
            }
            if (linkMap.containsKey(2)) {
                request.setAttribute("personalLinks", linkMap.get(2));
            }
        }
        request.setAttribute("configurations", configService.getNameValueMap());
        return "blog/" + theme + "/link";
    }

    /**
     * 关于页面 以及其他配置了url的文章页
     *
     * @return
     */
    @GetMapping({"/blog/{url}"})
    public String detail(HttpServletRequest request, @PathVariable("url") String url) {
        BlogDetailDto detailDto = blogService.getBlogDetailByUrl(url);
        if (detailDto != null) {
            request.setAttribute("detailDto", detailDto);
            request.setAttribute("pageName", url);
            request.setAttribute("configurations", configService.getNameValueMap());
            return "blog/" + theme + "/detail";
        } else {
            return "error/error_400";
        }
    }
}
