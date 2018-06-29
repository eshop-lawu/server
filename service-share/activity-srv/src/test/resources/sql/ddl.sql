/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.22_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.1.22:3306
 Source Schema         : eshop_activity

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 28/12/2017 15:17:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for help_redpacket_activity
-- ----------------------------
DROP TABLE IF EXISTS `help_redpacket_activity`;
CREATE TABLE `help_redpacket_activity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_theme` varchar(50) NOT NULL COMMENT '活动主题',
  `reg_start_time` datetime DEFAULT NULL COMMENT '报名开始时间',
  `reg_end_time` datetime DEFAULT NULL COMMENT '报名结束时间',
  `start_time` datetime DEFAULT NULL COMMENT '抢红包开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '抢红包结束时间',
  `min_redpacket` decimal(6,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '最小红包额度',
  `max_redpacket` decimal(6,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '最大红包额度',
  `total_manual_amount` decimal(20,2) unsigned DEFAULT NULL COMMENT '手动分配红包总额',
  `total_auto_amount` decimal(20,2) unsigned DEFAULT NULL COMMENT '自动分配红包总额',
  `multiple` decimal(6,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '红包叠加倍数',
  `redpacket_type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '红包类型(1-微信红包|2-余额红包)',
  `is_open` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '活动是否开放',
  `is_close_entry` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否关闭活动入口',
  `start_pic` varchar(200) DEFAULT NULL COMMENT '活动开始图片',
  `end_pic` varchar(200) DEFAULT NULL COMMENT '活动结束图片',
  `end_url` varchar(500) DEFAULT NULL COMMENT '活动结束链接',
  `manual_info` varchar(500) DEFAULT NULL COMMENT '手动分配红包信息',
  `wx_send_name` varchar(200) DEFAULT NULL COMMENT '微信红包商户名称',
  `wx_wishing` varchar(200) DEFAULT NULL COMMENT '微信红包祝福语',
  `wx_act_name` varchar(200) DEFAULT NULL COMMENT '微信红包活动名称',
  `wx_remark` varchar(200) DEFAULT NULL COMMENT '微信红包备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of help_redpacket_activity
-- ----------------------------
INSERT INTO `help_redpacket_activity`(`id`, `activity_theme`, `reg_start_time`, `reg_end_time`, `start_time`, `end_time`, `min_redpacket`, `max_redpacket`, `total_manual_amount`, `total_auto_amount`, `multiple`, `redpacket_type`, `is_open`, `is_close_entry`, `manual_info`, `wx_send_name`, `wx_wishing`, `wx_act_name`, `wx_remark`, `gmt_modified`, `gmt_create`) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL, NULL, NULL, NULL, NULL, NOW(), NOW());

-- ----------------------------
-- Table structure for help_redpacket_attend_detail
-- ----------------------------
DROP TABLE IF EXISTS `help_redpacket_attend_detail`;
CREATE TABLE `help_redpacket_attend_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` int(10) unsigned NOT NULL COMMENT '活动ID',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `account` varchar(20) NOT NULL COMMENT '用户账号',
  `wx_openid` varchar(50) DEFAULT NULL COMMENT '微信用户openid',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `headimg` varchar(200) DEFAULT NULL COMMENT '用户图像',
  `help_count` int(5) DEFAULT '0' COMMENT '助力人数',
  `original_money` decimal(10,2) DEFAULT '0.00' COMMENT '原始红包金额,单位元',
  `final_money` int(10) DEFAULT '0' COMMENT '最终红包金额,单位分',
  `is_lucky` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否幸运奖',
  `redpacket_type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '红包类型(1-微信红包|2-余额红包)',
  `status` tinyint(2) DEFAULT '1' COMMENT '报名状态(1-已报名|2-已分配|3-已领取|4-已发放|5-发放失败|6-领取成功|7-已退款)',
  `abnormal_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '异常状态(0-正常|1-疑似|2-异常|3-白名单)',
  `send_count` int(5) DEFAULT '0' COMMENT '红包发放次数',
  `send_remark` varchar(200) DEFAULT NULL COMMENT '红包发放备注',
  `allot_time` datetime DEFAULT NULL COMMENT '红包分配时间',
  `take_time` datetime DEFAULT NULL COMMENT '红包领取时间',
  `send_time` datetime DEFAULT NULL COMMENT '红包发放时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_activity_id_num` (`activity_id`,`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- ----------------------------
-- Table structure for help_redpacket_invite_record
-- ----------------------------
DROP TABLE IF EXISTS `help_redpacket_invite_record`;
CREATE TABLE `help_redpacket_invite_record`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` int(10) UNSIGNED NOT NULL COMMENT '活动ID',
  `attend_id` bigint(20) NOT NULL COMMENT '参与表ID',
  `attend_user_num` varchar(19) NOT NULL DEFAULT '' COMMENT '参与用户编号',
  `help_user_num` varchar(19) NOT NULL DEFAULT '' COMMENT '助力用户编号',
  `help_user_account` varchar(20) NOT NULL DEFAULT '' COMMENT '助力用户账号',
  `help_user_headimg` varchar(200) DEFAULT '' COMMENT '助力用户头像',
  `help_type` tinyint(2) NOT NULL COMMENT '助力方式（1-活动期间分享登陆助力|2-活动期间分享注册助力）',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for redpacket_send_record
-- ----------------------------
DROP TABLE IF EXISTS `redpacket_send_record`;
CREATE TABLE `redpacket_send_record` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`activity_id` INT(10) UNSIGNED NOT NULL COMMENT '活动ID',
	`attend_detail_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '参与详情id',
	`user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
	`openid` VARCHAR(50) NOT NULL COMMENT '微信openid',
	`return_code` VARCHAR(16) NOT NULL COMMENT '返回状态码',
	`return_msg` VARCHAR(128) NULL DEFAULT NULL COMMENT '返回信息',
	`result_code` VARCHAR(16) NULL DEFAULT NULL COMMENT '业务结果',
	`err_code` VARCHAR(32) NULL DEFAULT NULL COMMENT '错误代码',
	`err_code_des` VARCHAR(128) NULL DEFAULT NULL COMMENT '错误代码描述',
	`mch_billno` VARCHAR(28) NOT NULL COMMENT '商户订单号',
	`send_list_id` VARCHAR(32) NOT NULL COMMENT '微信单号',
	`total_amount` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '付款金额',
	`status` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1' COMMENT '1--发放中，2--已发放待领取，3--发放失败，4--已领取，5--退款中，6--已退款',
	`rcv_time` DATETIME NULL DEFAULT NULL COMMENT '接收时间',
	`refund_time` DATETIME NULL DEFAULT NULL COMMENT '红包退款时间',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for point_lottery_activity
-- ----------------------------
DROP TABLE IF EXISTS `point_lottery_activity`;
CREATE TABLE `point_lottery_activity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prize_name` varchar(50) NOT NULL COMMENT '奖品名称',
  `prize_price` decimal(10,2) unsigned NOT NULL COMMENT '奖品价值',
  `prize_image_path` varchar(200) NOT NULL COMMENT '奖品图片',
  `begin_time` datetime NOT NULL COMMENT '参与开始时间',
  `end_time` datetime NOT NULL COMMENT '参与结束时间',
  `draw_time` datetime DEFAULT NULL COMMENT '抽奖时间',
  `lottery_time` datetime NOT NULL COMMENT '开奖时间',
  `lottery_point` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '抽奖积分',
  `lottery_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '可中奖人数',
  `status` tinyint(3) unsigned NOT NULL COMMENT '1--未发布，2--进行中，3--已发布(可参与)，4--参与结束，5--已开奖，6--删除',
  `attent_number` int(10) unsigned DEFAULT '0' COMMENT '参与人数',
  `hot_number` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '热度人数',
  `lottery_param` varchar(200) DEFAULT NULL COMMENT '抽奖参数',
  `lottery_base_num` int(10) unsigned DEFAULT '0' COMMENT '中奖基数',
  `lottery_result_nums` varchar(200) DEFAULT NULL COMMENT '中奖结果编号(多个)',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);


-- ----------------------------
-- Table structure for point_lottery_activity_record
-- ----------------------------
DROP TABLE IF EXISTS `point_lottery_activity_record`;
CREATE TABLE `point_lottery_activity_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `mobile` varchar(20) NOT NULL COMMENT '抽奖手机号',
  `lottery_num` int(10) unsigned NOT NULL COMMENT '抽奖号码',
  `point_lottery_activity_id` bigint(20) unsigned NOT NULL COMMENT '幸运抽奖活动id',
  `point_lottery_activity_order_id` bigint(20) unsigned NOT NULL COMMENT '活动订单id',
  `prize_name` varchar(50) DEFAULT NULL COMMENT '奖品名称',
  `status` tinyint(3) unsigned NOT NULL COMMENT '1--未中奖，2--中奖',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for point_lottery_activity_order
-- ----------------------------
DROP TABLE IF EXISTS `point_lottery_activity_order`;
CREATE TABLE `point_lottery_activity_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `mobile` varchar(20) NOT NULL COMMENT '抽奖手机号',
  `point_lottery_activity_id` bigint(20) unsigned NOT NULL COMMENT '抽奖活动id',
  `attent_count` int(10) unsigned NOT NULL COMMENT '参与次数',
  `pay_point` decimal(10,2) unsigned NOT NULL COMMENT '兑换积分',
  `status` tinyint(3) unsigned NOT NULL COMMENT '0--待处理，1--成功，2--积分扣除失败',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for point_lottery_activity_report
-- ----------------------------
DROP TABLE IF EXISTS `point_lottery_activity_report`;
CREATE TABLE `point_lottery_activity_report` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `lottery_times` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '抽奖次数',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for point_lottery_activity_prize_image
-- ----------------------------
DROP TABLE IF EXISTS `point_lottery_activity_prize_image`;
CREATE TABLE `point_lottery_activity_prize_image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `point_lottery_activity_id` bigint(20) unsigned NOT NULL COMMENT '幸运抽奖活动id',
  `image_path` varchar(200) NOT NULL COMMENT '图片路径',
  `order_num` int(10) unsigned DEFAULT '0' COMMENT '序号',
  `type` tinyint(3) unsigned NOT NULL COMMENT '1--活动介绍，2--中奖信息',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for draw_lottery_activity
-- ----------------------------
DROP TABLE IF EXISTS `draw_lottery_activity`;
CREATE TABLE `draw_lottery_activity` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` VARCHAR(50) NULL DEFAULT NULL COMMENT '活动名称',
	`img_path` VARCHAR(200) NOT NULL COMMENT '活动图片',
	`grade` TINYINT(2) UNSIGNED NOT NULL COMMENT '参与等级',
	`status` TINYINT(2) UNSIGNED NOT NULL COMMENT '1--未发布，2--进行中，3--已发布，4--已结束，5--下架，6--删除',
	`remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '活动说明',
	`begin_time` DATETIME NOT NULL COMMENT '开始时间',
	`end_time` DATETIME NOT NULL COMMENT '结束时间',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for draw_lottery_activity_month_record
-- ----------------------------
DROP TABLE IF EXISTS `draw_lottery_activity_month_record`;
CREATE TABLE `draw_lottery_activity_month_record` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
	`free_times` INT(5) UNSIGNED NULL DEFAULT '0' COMMENT '免费抽奖次数',
	`point_times` INT(5) UNSIGNED NULL DEFAULT '0' COMMENT '积分抽奖次数',
	`record_date` VARCHAR(6) NULL DEFAULT NULL COMMENT '记录时间，年月(如201801)',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for draw_lottery_activity_prize
-- ----------------------------
DROP TABLE IF EXISTS `draw_lottery_activity_prize`;
CREATE TABLE `draw_lottery_activity_prize` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`draw_lottery_activity_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '抽奖活动id',
	`name` VARCHAR(50) NOT NULL COMMENT '名称',
	`img_path` VARCHAR(200) NOT NULL COMMENT '图片',
	`price` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT '0.00' COMMENT '价格',
	`number` INT(5) UNSIGNED NOT NULL DEFAULT '0' COMMENT '数量',
	`inventory` INT(5) UNSIGNED NOT NULL DEFAULT '0' COMMENT '库存',
	`status` TINYINT(2) UNSIGNED NOT NULL COMMENT '1--有效，2--无效',
	`is_address` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否需要填写地址：0--否，1--是',
	`is_send_prize` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否立即到账：0--否，1--是',
	`prize_type` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '奖品类型：1--金额，2--积分，3--商品',
	`freight_price` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT '0.00' COMMENT '运费',
	`merchant_store_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '门店id',
	`ad_img_path` VARCHAR(200) NOT NULL COMMENT '广告图片',
	`rate` DECIMAL(5,2) NULL DEFAULT NULL COMMENT '中奖概率',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for draw_lottery_activity_record
-- ----------------------------
DROP TABLE IF EXISTS `draw_lottery_activity_record`;
CREATE TABLE `draw_lottery_activity_record` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户id',
	`user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
	`user_account` VARCHAR(20) NOT NULL COMMENT '用户账号',
	`draw_lottery_activity_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '抽奖活动id',
	`draw_lottery_activity_prize_id` BIGINT(20) UNSIGNED NULL DEFAULT '0' COMMENT '奖品id',
	`prize_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '奖品名称',
	`status` TINYINT(2) UNSIGNED NOT NULL COMMENT '0--待处理，1--已参与，2--未中奖，3--已中奖，4--放弃领奖，5--已领奖，6--积分扣除失败，7--奖品已发放',
	`channel` TINYINT(2) NOT NULL COMMENT '途径0免费1兑换',
	`pay_point` DECIMAL(10,2) UNSIGNED NULL DEFAULT '0.00' COMMENT '兑换积分',
	`consignee_name` VARCHAR(25) NULL DEFAULT NULL COMMENT '收货人姓名',
	`consignee_mobile` VARCHAR(15) NULL DEFAULT NULL COMMENT '收货人手机号',
	`consignee_address` VARCHAR(180) NULL DEFAULT NULL COMMENT '收货人地址',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `rich_account`;
CREATE TABLE `rich_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `order_num` int(8) NOT NULL DEFAULT '0' COMMENT '创建序号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `id_card_num` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `diamond` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT 'E钻数量',
  `diamond_lucky` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '幸运钻数量',
  `diamond_total` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT 'E钻总量',
  `power` int(11) NOT NULL DEFAULT '0' COMMENT '挖矿动力值',
  `power_snapshoot` int(11) NOT NULL DEFAULT '0' COMMENT '挖矿动力值快照',
  `alipay_user_id` varchar(20) DEFAULT NULL COMMENT '支付宝绑定ID',
  `alipay_bind_time` datetime DEFAULT NULL COMMENT '支付宝绑定时间',
  `last_take_time` datetime DEFAULT NULL COMMENT '最后领取时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

INSERT INTO `rich_account` (`id`, `user_num`, `order_num`, `name`, `id_card_num`, `diamond`, `diamond_lucky`, `diamond_total`, `power`, `power_snapshoot`, `alipay_user_id`, `alipay_bind_time`, `last_take_time`, `gmt_modified`, `gmt_create`) VALUES ('6', 'M949121843325829209', '3', NULL, NULL, '2.000000', '2.000000', '4.000000', '35', '0', NULL, NULL, '2018-05-04 14:46:11', '2018-05-04 14:52:40', '2018-05-04 14:46:15');


DROP TABLE IF EXISTS `rich_diamond_record`;
CREATE TABLE `rich_diamond_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `type` tinyint(2) NOT NULL COMMENT '类型 1-E钻 2-幸运钻',
  `source` tinyint(2) NOT NULL COMMENT '来源 1-日常领取',
  `direction` tinyint(2) NOT NULL COMMENT '1-支出 2-收入',
  `amount` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '数量',
  `status` tinyint(2) NOT NULL COMMENT '状态 0-未领取 1-已领取 2-未领取',
  `take_time` datetime DEFAULT NULL COMMENT '领取时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `rich_power_record`;
CREATE TABLE `rich_power_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `source` tinyint(2) NOT NULL COMMENT '来源 1-登录 2-邀请E友 3-娱乐 4-看广告 5-购物 6-绑定支付宝 7-身份认证 8-幸运钻领取消耗',
  `direction` tinyint(2) NOT NULL COMMENT '1-支出 2-收入',
  `power` int(11) NOT NULL DEFAULT '0' COMMENT '挖矿动力值',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `rich_power_task_record`;
CREATE TABLE `rich_power_task_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `is_binding_alipay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否绑定支付宝',
  `is_identity_auth` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否身份认证',
  `is_login` tinyint(1) NOT NULL DEFAULT '0' COMMENT '定期登录完成情况',
  `friend_invite_count` int(8) NOT NULL DEFAULT '0' COMMENT '定期邀请E友数量',
  `game_count` int(8) NOT NULL DEFAULT '0' COMMENT '定期娱乐游戏局数',
  `ad_count` int(8) NOT NULL DEFAULT '0' COMMENT '定期看广告数量',
  `shopping_amount` int(8) NOT NULL DEFAULT '0' COMMENT '定期购物金额',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `rich_config`;
CREATE TABLE `rich_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `diamond_config` text COMMENT '瑞奇岛E钻配置信息json',
  `diamond_effective_time` datetime DEFAULT NULL COMMENT '瑞奇岛E钻配置生效时间，第二天凌晨更新',
  `power_task_config` text COMMENT '动力任务配置信息json',
  `power_effective_time` datetime DEFAULT NULL COMMENT '动力任务配置生效时间，第二天凌晨更新',
  `notice` varchar(1000) DEFAULT NULL COMMENT '公告',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `rich_diamond_info`;
CREATE TABLE `rich_diamond_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `diamond_sent` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '已发放E钻数量',
  `diamond_lucky_sent` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '已发放幸运钻数量',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `diamond_distribution_record`;
CREATE TABLE `diamond_distribution_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `residents` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '分配的居民数量',
  `allocated_residents` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '已分配的居民数量',
  `expected_ordinary_allocations` decimal(20,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '预期分配E钻数量',
  `real_ordinary_allocations` decimal(20,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '实际分配E钻数量',
  `lucky_residents` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '分配幸运钻的居民数量',
  `allocated_lucky_residents` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '已分配幸运钻的居民数量',
  `expected_lucky_allocations` decimal(20,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '预期分配幸运钻数量',
  `real_luck_allocations` decimal(20,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '实际分配幸运钻数量',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1-分配中|2-已分配|3-已发放)',
  `gmt_allocations_complete` datetime DEFAULT NULL COMMENT '分配完成时间',
  `gmt_grant_complete` datetime DEFAULT NULL COMMENT '发放完成时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
