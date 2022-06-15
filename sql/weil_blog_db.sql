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
    PRIMARY KEY ( `id` ) ) ENGINE = INNODB COMMENT '测试表';
