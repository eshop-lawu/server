/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.26_3306
Source Server Version : 50717
Source Host           : 192.168.1.26:3306
Source Database       : eshop_behavior

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-29 16:10:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for follow_transaction_record
-- ----------------------------
DROP TABLE IF EXISTS `follow_transaction_record`;
CREATE TABLE `follow_transaction_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `transation_id` bigint(20) unsigned NOT NULL COMMENT '事务记录id',
  `topic` varchar(30) NOT NULL COMMENT 'MQ消息的topic',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for invite_abnormal_decide_record
-- ----------------------------
DROP TABLE IF EXISTS `invite_abnormal_decide_record`;
CREATE TABLE `invite_abnormal_decide_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT NULL COMMENT '用户编号',
  `account` varchar(20) NOT NULL DEFAULT '' COMMENT '账号',
  `type` tinyint(2) NOT NULL COMMENT '用户类型1--商户2--会员',
  `is_short_hf` tinyint(1) DEFAULT '0' COMMENT '是否短高频',
  `is_long_hf` tinyint(1) DEFAULT '0' COMMENT '是否长高频',
  `is_many_short_hf` tinyint(1) DEFAULT '0' COMMENT '是否多次短高频',
  `is_many_long_hf` tinyint(1) DEFAULT '0' COMMENT '是否多次短高频',
  `is_one_day_hf` tinyint(1) DEFAULT '0' COMMENT '是否一天注册下线高频',
  `is_early_hf` tinyint(1) DEFAULT '0' COMMENT '是否凌晨注册高频',
  `is_ip_hf` tinyint(1) DEFAULT '0' COMMENT '是否相同ip高频',
  `is_abnormal` tinyint(1) DEFAULT '0' COMMENT '是否异常',
  `process_type` tinyint(2) DEFAULT '0' COMMENT '处理类型，0未处理，1不处理，2冻结',
  `is_noticed` tinyint(1) DEFAULT '0' COMMENT '是否已通知',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);
