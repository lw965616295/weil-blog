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
    `name` VARCHAR ( 50 ) NOT NULL COMMENT '用户名',
    `password` VARCHAR ( 50 ) NOT NULL COMMENT '密码',
    `nick_name` VARCHAR ( 50 ) NOT NULL COMMENT '昵称',
    `forbidden` TINYINT ( 1 ) DEFAULT '0' COMMENT '是否禁用：0未禁用，1禁用',
    PRIMARY KEY ( `id` )
) ENGINE = INNODB COMMENT '用户表';
INSERT INTO `user` ( `id`, `name`, `password`, `nick_name`, `forbidden` )
VALUES
( 1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 0 );