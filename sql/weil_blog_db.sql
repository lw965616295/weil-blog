-- 初始化sql脚本
CREATE DATABASE IF NOT EXISTS `weil_blog`
DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;

-- 进入数据库
USE `weil_blog`;

-- 创建测试表
DROP TABLE IF EXISTS `tb_test`;
CREATE TABLE `tb_test` (
    `id` BIGINT NOT NULL COMMENT 'id',
    `name` VARCHAR (32) DEFAULT '' COMMENT '名字',
    PRIMARY KEY ( `id` )
) ENGINE = INNODB COMMENT '测试表';
INSERT INTO `tb_test` (id, name) values (1, 'name'),(2, 'age');

-- 用户表，初始化用户名和密码：admin-admin
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `name` VARCHAR ( 50 ) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR ( 50 ) NOT NULL COMMENT '密码',
    `nick_name` VARCHAR ( 50 ) NOT NULL COMMENT '昵称',
    `forbidden` TINYINT ( 1 ) DEFAULT '0' COMMENT '是否禁用：0未禁用，1禁用',
    PRIMARY KEY ( `id` )
) ENGINE = INNODB COMMENT '用户表';
INSERT INTO `user` ( `id`, `name`, `password`, `nick_name`, `forbidden` )
VALUES
( 1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 0 );

-- 博客分类表
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
     `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
     `name` VARCHAR ( 64 ) NOT NULL COMMENT '名称',
     `icon` VARCHAR ( 64 ) NOT NULL COMMENT '图标',
     `weight` INT NOT NULL DEFAULT 1 COMMENT '权重，数值越大权重越高',
     `is_del` TINYINT ( 1 ) NOT NULL DEFAULT 0 COMMENT '是否被删',
     `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
     PRIMARY KEY ( `id` )
) ENGINE = INNODB COMMENT '博客分类表';
INSERT INTO `blog_category` ( `id`, `name`, `icon`, `weight`, `is_del` )
VALUES
( 1, '默认分类', '/dist/img/category/10.png', 1, 0 ),
( 2, 'mybatis教程', '/dist/img/category/10.png', 8, 0 ),
( 3, 'springboot入门篇', '/dist/img/category/02.png', 19, 0 ),
( 4, '日常随笔', '/dist/img/category/06.png', 22, 0 );

-- 博客表
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title` varchar(200) NOT NULL COMMENT '博客标题',
    `url` varchar(200) NOT NULL COMMENT '博客自定义路径url',
    `cover_image` varchar(200) NOT NULL COMMENT '博客封面图',
    `content` mediumtext NOT NULL COMMENT '博客内容',
    `category_id` int(11) NOT NULL COMMENT '博客分类id',
    `category_name` varchar(50) NOT NULL COMMENT '博客分类(冗余字段)',
    `tags` varchar(200) NOT NULL COMMENT '博客标签',
    `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-草稿 1-发布',
    `views` bigint(20) NOT NULL DEFAULT '0' COMMENT '阅读量',
    `enable_comment` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-允许评论 1-不允许评论',
    `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0=否 1=是',
    `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    PRIMARY KEY (`id`),
    INDEX `idx_title` ( `title` ),
    INDEX `idx_category` ( `category_name` )
) ENGINE=InnoDB comment '博客表';

-- 标签表
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
     `id` INT ( 11 ) NOT NULL AUTO_INCREMENT COMMENT '主键',
     `name` VARCHAR ( 100 ) NOT NULL COMMENT '标签名称',
     `is_del` TINYINT ( 1 ) NOT NULL DEFAULT 0 COMMENT '是否删除，0否，1是',
     `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
     PRIMARY KEY ( `id` )
) ENGINE = INNODB COMMENT '标签表';
INSERT INTO `blog_tag` ( `id`, `name`, `is_del` )
VALUES
( 1, 'spring教程', 0 ),
( 2, 'js教程', 0 );

-- 博客-标签关系表
DROP TABLE IF EXISTS `blog_tag_relation`;
CREATE TABLE `blog_tag_relation` (
     `id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
     `blog_id` BIGINT ( 20 ) NOT NULL COMMENT '博客id',
     `tag_id` INT ( 11 ) NOT NULL COMMENT '标签id',
     `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
     PRIMARY KEY ( `id` )
) ENGINE = INNODB COMMENT '博客-标签关系表';

-- 博客评论表
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
    `id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `blog_id` BIGINT ( 20 ) DEFAULT NULL COMMENT 'blog主键',
    `commentator` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '评论者名称',
    `email` VARCHAR ( 100 ) NOT NULL DEFAULT '' COMMENT '评论人的邮箱',
    `comment` VARCHAR ( 200 ) NOT NULL DEFAULT '' COMMENT '评论内容',
    `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论提交时间',
    `commentator_ip` VARCHAR ( 20 ) NOT NULL DEFAULT '' COMMENT '评论时的ip地址',
    `reply_content` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '回复内容',
    `reply_date` datetime DEFAULT NULL COMMENT '回复时间',
    `status` TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '是否审核通过 0-未审核 1-审核通过',
    `is_del` TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
    PRIMARY KEY ( `id` )
) ENGINE = INNODB COMMENT '博客评论表';

-- 友链表
DROP TABLE IF EXISTS `blog_link`;
CREATE TABLE `blog_link` (
    `id` INT ( 11 ) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
    `type` TINYINT ( 4 ) NOT NULL DEFAULT '0' COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
    `name` VARCHAR ( 50 ) NOT NULL COMMENT '网站名称',
    `url` VARCHAR ( 100 ) NOT NULL COMMENT '网站链接',
    `description` VARCHAR ( 100 ) NOT NULL DEFAULT '' COMMENT '网站描述',
    `rank` INT ( 11 ) NOT NULL DEFAULT '0' COMMENT '用于列表排序',
    `is_del` TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
    `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    PRIMARY KEY ( `id` )
) ENGINE = INNODB COMMENT '友链表';

-- 配置表
DROP TABLE IF EXISTS `blog_config`;
CREATE TABLE `blog_config` (
   `name` VARCHAR ( 100 ) NOT NULL COMMENT '配置项的名称',
   `value` VARCHAR ( 200 ) NOT NULL DEFAULT '' COMMENT '配置项的值',
   `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
   `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
   PRIMARY KEY ( `name` )
) ENGINE = INNODB COMMENT '配置表';
INSERT INTO `blog_config` ( `name`, `value` )
VALUES
( 'footerAbout', 'weil-blog. have fun.' ),
( 'footerCopyRight', '2022 weil' ),
( 'footerICP', '苏ICP备10086号-1' ),
( 'footerPoweredBy', 'https://github.com/lw965616295' ),
( 'footerPoweredByURL', 'https://github.com/lw965616295' ),
( 'websiteDescription', 'weil- blog是SpringBoot+Thymeleaf+Mybatis建造的个人博客网站' ),
( 'websiteIcon', '/dist/img/favicon.png' ),
( 'websiteLogo', '/dist/img/logo2.png' ),
( 'websiteName', 'weil-blog' ),
( 'yourAvatar', '/dist/img/13.png' ),
( 'yourEmail', '965616295@qq.com' ),
( 'yourName', 'weil' );