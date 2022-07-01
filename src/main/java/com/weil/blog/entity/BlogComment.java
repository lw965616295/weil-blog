package com.weil.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 博客评论表
 * </p>
 *
 * @author weil
 * @since 2022-06-27 16:30:19
 */
@TableName("blog_comment")
@Data
@ToString
@Accessors(chain = true)
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * blog主键
     */
    private Long blogId;

    /**
     * 评论者名称
     */
    private String commentator;

    /**
     * 评论人的邮箱
     */
    private String email;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 评论时的ip地址
     */
    private String commentatorIp;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replyDate;

    /**
     * 是否审核通过 0-未审核 1-审核通过
     */
    private Boolean status;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Boolean isDel;

    /**
     * web页面提交验证码
     */
    @TableField(exist = false)
    private String verifyCode;

}
