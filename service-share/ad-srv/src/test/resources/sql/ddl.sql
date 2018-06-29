/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.22_3306
Source Server Version : 50717
Source Host           : 192.168.1.22:3306
Source Database       : eshop_ad

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-06 18:06:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家ID',
  `merchant_num` varchar(19) NOT NULL COMMENT '商家编号',
  `manage_type` tinyint(2) unsigned DEFAULT NULL COMMENT '经营类型(1-普通商户2-实体店铺)',
  `merchant_store_name` varchar(100) DEFAULT NULL COMMENT '店铺名称',
  `merchant_store_id` bigint(20) unsigned DEFAULT NULL COMMENT '商家门店id',
  `relate_id` bigint(20) DEFAULT NULL COMMENT '关联id',
  `relate_type` tinyint(2) DEFAULT '0' COMMENT '关联类型 0没有关联  1 商品 2店铺',
  `merchant_longitude` decimal(10,7) unsigned DEFAULT NULL COMMENT '经度',
  `merchant_latitude` decimal(10,7) unsigned DEFAULT NULL COMMENT '纬度',
  `merchant_region_path` varchar(25) DEFAULT NULL COMMENT '商家地区',
  `title` varchar(100) DEFAULT NULL COMMENT '名称',
  `media_url` varchar(120) DEFAULT NULL COMMENT '广附件路径',
  `file_size` varchar(10) DEFAULT NULL COMMENT '文件大小',
  `file_time` varchar(10) DEFAULT NULL COMMENT '文件时长(视频)',
  `file_type` tinyint(2) DEFAULT NULL COMMENT '文件类型 1-视频 2-图片',
  `logo_url` varchar(255) DEFAULT NULL COMMENT '门店logo',
  `video_img_url` varchar(120) DEFAULT NULL COMMENT '视频封面图片路径',
  `content` varchar(500) DEFAULT NULL COMMENT '广告内容',
  `type` tinyint(2) NOT NULL COMMENT '广告类型(1-平面广告|2-视频广告|3-E赞|4-红包)',
  `put_way` tinyint(2) NOT NULL COMMENT '投放方式(1-区域2-粉丝 3-雷达)',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `areas` varchar(100) DEFAULT NULL COMMENT '投放区域',
  `region_name` varchar(100) DEFAULT NULL COMMENT '区域名称',
  `radius` int(10) unsigned DEFAULT NULL COMMENT '半径，单位米',
  `point` decimal(10,2) unsigned DEFAULT NULL COMMENT '单个积分',
  `total_point` decimal(10,2) unsigned NOT NULL COMMENT '总投放积分',
  `ad_count` int(5) unsigned DEFAULT NULL COMMENT '广告数量',
  `hits` int(5) unsigned DEFAULT NULL COMMENT '已点击次数',
  `viewCount` int(5) unsigned DEFAULT '0' COMMENT '广告浏览次数',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态(0-删除|1-上架|2-投放中|3-投放结束|4-下架|5-审核中|审核不通过)',
  `ad_order_num` varchar(30) DEFAULT NULL COMMENT '广告订单编号',
  `third_number` varchar(30) DEFAULT NULL COMMENT '第三方交易号',
  `is_pay` tinyint(1) DEFAULT NULL COMMENT '是否支付',
  `pay_type` tinyint(2) DEFAULT NULL COMMENT '支付类型',
  `client_type` tinyint(2) DEFAULT NULL COMMENT '支付客户端类型',
  `auditor_id` int(11) DEFAULT '0' COMMENT '审核人员ID',
  `remark` varchar(200) DEFAULT NULL COMMENT '审核备注',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for ad_lexicon
-- ----------------------------
DROP TABLE IF EXISTS `ad_lexicon`;
CREATE TABLE `ad_lexicon` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(20) NOT NULL COMMENT '名称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for ad_platform
-- ----------------------------
DROP TABLE IF EXISTS `ad_platform`;
CREATE TABLE `ad_platform` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) unsigned DEFAULT NULL COMMENT '商品ID',
  `ad_id` bigint(20) DEFAULT NULL COMMENT '广告id',
  `title` varchar(100) DEFAULT NULL COMMENT '名称',
  `media_url` varchar(120) DEFAULT NULL COMMENT '广附件路径',
  `link_url` varchar(120) DEFAULT NULL COMMENT '链接地址',
  `merchant_store_id` bigint(20) unsigned DEFAULT NULL COMMENT '门店id',
  `type` tinyint(2) DEFAULT NULL COMMENT '广告类型(1-纯链接2-商品 3-门店)',
  `position` tinyint(2) NOT NULL COMMENT '广告位置(1-人气推荐2-要购物顶部广告 3-要购物今日推荐4-要购物精品5-看广告顶部广告 6-E店必够 7-特色好货 8-实惠单品  9-热门商品)',
  `region_path` varchar(100) DEFAULT NULL COMMENT '地区',
  `region_name` varchar(50) DEFAULT NULL COMMENT '地区名称',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态(0-删除1-上架2-下架)',
  `content` varchar(500) DEFAULT NULL COMMENT '广告内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for ad_region
-- ----------------------------
DROP TABLE IF EXISTS `ad_region`;
CREATE TABLE `ad_region` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家ID',
  `ad_id` bigint(20) unsigned NOT NULL COMMENT '广告ID',
  `region_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_ad_id` (`ad_id`)
);

-- ----------------------------
-- Table structure for favorite_ad
-- ----------------------------
DROP TABLE IF EXISTS `favorite_ad`;
CREATE TABLE `favorite_ad` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ad_id` bigint(20) unsigned NOT NULL COMMENT '广告ID',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员ID',
  `member_num` varchar(19) NOT NULL COMMENT '会员编号',
  `is_send`  tinyint(1) DEFAULT '0' NOT NULL COMMENT '是否发送消息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`)
);

-- ----------------------------
-- Table structure for member_ad_record
-- ----------------------------
DROP TABLE IF EXISTS `member_ad_record`;
CREATE TABLE `member_ad_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员',
  `member_num` varchar(19) NOT NULL COMMENT '会员编号',
  `ad_id` bigint(20) unsigned NOT NULL COMMENT '广告',
  `point` decimal(10,6) DEFAULT NULL COMMENT '获取积分',
  `original_point` decimal(10,6) DEFAULT NULL COMMENT '原始积分',
  `click_date` date DEFAULT NULL COMMENT '点击日期:年月日',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态(0-没有算提成1-已算提成)',
  `gmt_commission` datetime DEFAULT NULL COMMENT '计算提成时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for point_pool
-- ----------------------------
DROP TABLE IF EXISTS `point_pool`;
CREATE TABLE `point_pool` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家ID',
  `member_id` bigint(20) unsigned DEFAULT NULL COMMENT '会员ID',
  `member_num` varchar(19) DEFAULT NULL COMMENT '会员编号',
  `ad_id` bigint(20) unsigned NOT NULL COMMENT '广告ID',
  `type` tinyint(2) NOT NULL COMMENT '类型(1-抢赞2-红包)',
  `ordinal` int(8) unsigned NOT NULL COMMENT '序号',
  `point` decimal(10,2) unsigned NOT NULL COMMENT '分配的金额',
  `status` tinyint(2) NOT NULL COMMENT '状态(0-未分配1-已分配2-过期)',
  `taked_time` datetime DEFAULT NULL COMMENT '分配时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for red_packet
-- ----------------------------
DROP TABLE IF EXISTS `red_packet`;
CREATE TABLE `red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家ID',
  `merchant_num` varchar(19) NOT NULL COMMENT '商家编号',
  `put_way` tinyint(2) NOT NULL COMMENT '投放方式(1-普通红包2-拼手气红包)',
  `package_count` int(5) unsigned DEFAULT NULL COMMENT '红包数量',
  `totle_point` decimal(10,2) unsigned DEFAULT NULL COMMENT '所需积分',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态(0-删除1-上架3-下架)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `user_red_packet`;
CREATE TABLE `user_red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) NOT NULL COMMENT '用户num',
  `user_account` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `order_num` varchar(30) NOT NULL COMMENT '买单编号',
  `third_number` varchar(50) DEFAULT NULL COMMENT '第三方支付编号',
  `pay_type` tinyint(2) DEFAULT NULL COMMENT '支付类型',
  `type` tinyint(2) DEFAULT NULL COMMENT '红包类型[0普通红包,1拼手气红包]',
  `total_count` int(8) DEFAULT NULL COMMENT '红包总数',
  `total_money` decimal(18,4) DEFAULT NULL COMMENT '总金额',
  `refund_money` decimal(18,4) DEFAULT NULL COMMENT '退还金额',
  `status` tinyint(2) DEFAULT NULL COMMENT '红包状态 1有效 2领取完 3过期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `user_taked_red_packet`;
CREATE TABLE `user_taked_red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) DEFAULT NULL COMMENT '领取用户num',
  `user_red_pack_id` bigint(20) DEFAULT NULL COMMENT '红包id',
  `type` tinyint(2) DEFAULT NULL COMMENT '红包类型',
  `ordinal` int(8) DEFAULT NULL COMMENT '序号',
  `money` decimal(18,4) DEFAULT NULL COMMENT '分配的金额',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态(0未分配，1已分配，2过期)',
  `taked_time` datetime DEFAULT NULL COMMENT '红包领取时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `platform_red_packet`;
CREATE TABLE `platform_red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `send_count` int(8) DEFAULT '0' COMMENT '发送红包总数',
  `status` tinyint(2) DEFAULT '1' COMMENT '红包状态 0-禁用 1-启用',
  `auditor_id` bigint(20) unsigned NOT NULL COMMENT '操作人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `take_platform_red_packet`;
CREATE TABLE `take_platform_red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT NULL COMMENT '会员编号',
  `red_packet_id` bigint(20) unsigned NOT NULL COMMENT '红包ID',
  `point` decimal(10,2) unsigned NOT NULL COMMENT '领取金额',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `ad_rate_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `game_time` int(5) unsigned NOT NULL COMMENT '游戏时长（单位秒）',
  `rate` int(5) NOT NULL COMMENT '对应命中率0~100',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `inviter_bounty` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `money` decimal(10,2) NOT NULL COMMENT '金额',
  `send_count` int(8) DEFAULT '0' COMMENT '发送奖励金总个数',
  `status` tinyint(2) DEFAULT '1' COMMENT '奖励金状态 0-禁用 1-启用',
  `auditor_id` bigint(20) unsigned NOT NULL  COMMENT '操作人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='邀请奖励金表';

CREATE TABLE `take_inviter_bounty_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '邀请人编号',
  `red_packet_id` bigint(20) unsigned NOT NULL COMMENT '奖励金ID',
  `point` decimal(10,2) unsigned NOT NULL COMMENT '领取金额',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='邀请奖励金记录表';

CREATE TABLE `take_inviter_bounty_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(2) NOT NULL COMMENT '用户类型 1-用户  2-商家' ,
  `take_count` int(5) DEFAULT '0' COMMENT '每天领取奖励金个数',
  `take_time` date NOT NULL COMMENT '领取日期:年月日',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='领取奖励金个数记录表';