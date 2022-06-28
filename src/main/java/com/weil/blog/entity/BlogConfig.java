package com.weil.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 配置表
 * </p>
 *
 * @author weil
 * @since 2022-06-28 14:55:13
 */
@TableName("blog_config")
@Data
@ToString
@Accessors(chain = true)
public class BlogConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置项的名称
     */
    @TableId
    private String name;

    /**
     * 配置项的值
     */
    private String value;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateDate;

}
