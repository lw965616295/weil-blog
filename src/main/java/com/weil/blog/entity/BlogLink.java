package com.weil.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 友链表
 * </p>
 *
 * @author weil
 * @since 2022-06-28 10:30:57
 */
@TableName("blog_link")
@Data
@ToString
@Accessors(chain = true)
public class BlogLink implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 友链表主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 友链类别 0-友链 1-推荐 2-个人网站
     */
    @NotNull(message = "友链类型不能为空！")
    private Integer type;

    /**
     * 网站名称
     */
    @NotBlank(message = "网站名称不能为空！")
    private String name;

    /**
     * 网站链接
     */
    @NotBlank(message = "网站链接不能为空！")
    private String url;

    /**
     * 网站描述
     */
    private String description;

    /**
     * 用于列表排序
     */
    private Integer rank;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Boolean isDel;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

}
