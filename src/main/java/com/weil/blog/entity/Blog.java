package com.weil.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 博客表
 * </p>
 *
 * @author weil
 * @since 2022-06-22 17:20:31
 */
@TableName("blog")
@Data
@ToString
@Accessors(chain = true)
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 博客标题
     */
    @NotBlank(message = "标题不能为空！")
    private String title;

    /**
     * 博客自定义路径url
     */
    private String url;

    /**
     * 博客封面图
     */
    private String coverImage;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 博客分类id
     */
    private Integer categoryId;

    /**
     * 博客分类(冗余字段)
     */
    private String categoryName;

    /**
     * 博客标签
     */
    @Length(max = 500, message = "标签过长")
    private String tags;

    /**
     * 0-草稿 1-发布
     */
    private Boolean status;

    /**
     * 阅读量
     */
    private Long views;

    /**
     * 0-允许评论 1-不允许评论
     */
    private Boolean enableComment;

    /**
     * 是否删除 0=否 1=是
     */
    private Boolean isDel;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

}
