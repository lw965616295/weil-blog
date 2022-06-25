package com.weil.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 博客-标签关系表
 * </p>
 *
 * @author weil
 * @since 2022-06-24 13:27:00
 */
@TableName("blog_tag_relation")
@Data
@ToString
@Accessors(chain = true)
public class BlogTagRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关系表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * 创建日期
     */
    private LocalDateTime createDate;

}
