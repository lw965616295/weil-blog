package com.weil.blog;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @ClassName AutoGenerator
 * @Author weil
 * @Description //代码生成器
 * @Date 2022/6/14 17:13
 * @Version 1.0.0
 **/
public class AutoGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.85.131:3306/weil_blog?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        String path = "D:/codeDir/weil_blog";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("weil") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd HH:mm:ss") //注释日期
                            .outputDir(path + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.weil") // 设置父包名
                            .moduleName("blog") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, path+"/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tb_test") // 设置需要生成的表名
                            .addTablePrefix("", ""); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
