package com.weil.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogComment;
import com.weil.blog.mapper.BlogCommentMapper;
import com.weil.blog.service.IBlogCommentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客评论表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-27 16:30:19
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

    @Override
    public IPage<BlogComment> getList(Long page, Long rows) {
        IPage<BlogComment> categoryPage = new Page<>();
        categoryPage.setCurrent(page);
        categoryPage.setPages(rows);
        return page(categoryPage, new LambdaQueryWrapper<BlogComment>().eq(BlogComment::getIsDel, 0).orderByDesc(BlogComment::getCreateDate));
    }

    @Override
    public Result audit(List<Long> ids) {
        List<BlogComment> list = ids.stream().map(id -> new BlogComment().setId(id).setStatus(true)).collect(Collectors.toList());
        if (updateBatchById(list)) {
            return Result.success("操作成功！");
        }
        return Result.fail("批量删除失败！");
    }

    @Override
    public Result reply(BlogComment comment) {
        if(comment.getId() == null){
            return Result.fail("id为空！");
        }
        if(StringUtils.isEmpty(comment.getReplyContent())){
            return Result.fail("回复内容为空！");
        }
        BlogComment comm = getById(comment.getId());
        if(comm == null || comm.getIsDel()){
            return Result.fail("该评论不存在，或被删除！");
        }
        if(!comm.getStatus()){
            return Result.fail("先进行审核！");
        }
        if(updateById(comm.setReplyDate(new Date()).setReplyContent(comment.getReplyContent()))){
            return Result.success("操作成功！");
        }
        return Result.fail("回复失败！");
    }

    @Override
    public Result deleteByIds(List<Long> ids) {
        List<BlogComment> list = ids.stream().map(id -> new BlogComment().setId(id).setIsDel(true)).collect(Collectors.toList());
        if (updateBatchById(list)) {
            return Result.success("操作成功！");
        }
        return Result.fail("批量删除失败！");
    }

    @Override
    public Long getTotalCommentCount() {
        return count(new LambdaQueryWrapper<BlogComment>().eq(BlogComment::getIsDel, 0));
    }

    @Override
    public Result saveComment(BlogComment comment) {
        if (save(comment)) {
            return Result.success("保存成功！");
        }
        return Result.fail("操作失败！");
    }

    @Override
    public IPage<BlogComment> getList2(Long page, Long rows, Long blogId) {
        IPage<BlogComment> categoryPage = new Page<>();
        categoryPage.setCurrent(page);
        categoryPage.setPages(rows);
        return page(categoryPage, new LambdaQueryWrapper<BlogComment>()
                .eq(BlogComment::getIsDel, false)
                .eq(BlogComment::getStatus, true)
                .orderByAsc(BlogComment::getCreateDate)
                .eq(BlogComment::getBlogId, blogId));
    }
}
