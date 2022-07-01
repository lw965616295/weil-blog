package com.weil.blog.controller.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weil.blog.entity.Blog;
import com.weil.blog.entity.dto.BlogDetailDto;
import com.weil.blog.service.IBlogCommentService;
import com.weil.blog.service.IBlogConfigService;
import com.weil.blog.service.IBlogService;
import com.weil.blog.service.IBlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

}
