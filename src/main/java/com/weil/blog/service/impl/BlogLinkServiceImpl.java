package com.weil.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weil.blog.common.Result;
import com.weil.blog.entity.BlogLink;
import com.weil.blog.mapper.BlogLinkMapper;
import com.weil.blog.service.IBlogLinkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 友链表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-28 10:30:57
 */
@Service
public class BlogLinkServiceImpl extends ServiceImpl<BlogLinkMapper, BlogLink> implements IBlogLinkService {

    @Override
    public IPage<BlogLink> getList(Long page, Long rows) {
        IPage<BlogLink> linkPage = new Page<>();
        linkPage.setCurrent(page);
        linkPage.setPages(rows);
        return page(linkPage, new LambdaQueryWrapper<BlogLink>().eq(BlogLink::getIsDel, 0).orderByDesc(BlogLink::getCreateDate));
    }

    @Override
    public Result saveOrUpdateLink(BlogLink link) {
        if(saveOrUpdate(link)){
            return Result.success("操作成功！");
        }
        return Result.fail("新增失败！");
    }

    @Override
    public Result deleteByIds(List<Integer> ids) {
        List<BlogLink> list = ids.stream().map(id -> new BlogLink().setId(id).setIsDel(true)).collect(Collectors.toList());
        if(updateBatchById(list)){
            return Result.success("操作成功！");
        }
        return Result.fail("删除失败！");
    }

    @Override
    public Long getTotalLinkCount() {
        return count(new LambdaQueryWrapper<BlogLink>().eq(BlogLink::getIsDel, 0));
    }
}
