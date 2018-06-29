/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.22
Source Server Version : 50717
Source Host           : 192.168.1.22:3306
Source Database       : eshop_agent

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-10 16:20:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for agent_user
-- ----------------------------
DROP TABLE IF EXISTS `agent_user`;
CREATE TABLE `agent_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `num` varchar(19) DEFAULT NULL COMMENT '代理商编号',
  `account` varchar(20) NOT NULL DEFAULT '' COMMENT '登录账号',
  `pwd` char(57) NOT NULL DEFAULT '' COMMENT '登录密码',
  `mobile` varchar(15)  NOT NULL DEFAULT '' COMMENT '手机号码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `region_path` varchar(25) DEFAULT NULL COMMENT '地区路径',
  `region_name` varchar(100) DEFAULT NULL COMMENT '区域名称',
  `status` tinyint(2) NOT NULL COMMENT '状态 (0--无效，1--有效)',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);
