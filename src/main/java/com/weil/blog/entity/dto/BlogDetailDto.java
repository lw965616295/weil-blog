package com.weil.blog.entity.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @ClassName BlogDetailDto
 * @Author weil
 * @Description //博客明细页dto
 * @Date 2022/6/30 14:58
 * @Version 1.0.0
 **/
@Data
@ToString
@Accessors(chain = true)
public class BlogDetailDto {
    /**
     * 博客id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 分类图标
     */
    private String categoryIcon;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 博客封面图
     */
    private String coverImage;

    /**
     * 阅读数
     */
    private Long views;

    /**
     * 标签
     */
    private List<String> blogTags;

    /**
     * 博客内容==>html
     */
    private String content;

    /**
     * 是否可以评论
     */
    private Boolean enableComment;

    /**
     * 创建日期
     */
    private Date createDate;
}
