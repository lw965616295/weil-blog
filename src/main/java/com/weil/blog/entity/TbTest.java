package com.weil.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 测试表
 * </p>
 *
 * @author weil
 * @since 2022-06-15 11:08:11
 */
@TableName("tb_test")
public class TbTest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TbTest{" +
            "id=" + id +
            ", name=" + name +
        "}";
    }
}
