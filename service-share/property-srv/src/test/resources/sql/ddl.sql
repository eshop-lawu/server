/*
Navicat MySQL Data Transfer

Source Server         : 开发-192.168.1.22
Source Server Version : 50717
Source Host           : 192.168.1.22:3306
Source Database       : eshop_property

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-12 09:54:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `id` int(10)  NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) NOT NULL COMMENT '银行名称',
  `icon_url` varchar(120) NOT NULL COMMENT '图标路径',
  `status` tinyint(2)  NOT NULL DEFAULT '1' COMMENT '1为启用,0为停用',
  `ordinal` int(10)  NOT NULL DEFAULT '0' COMMENT '序号',
  `remark` varchar(25)  DEFAULT NULL COMMENT '备注',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for bank_account
-- ----------------------------
DROP TABLE IF EXISTS `bank_account`;
CREATE TABLE `bank_account` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL DEFAULT '' COMMENT '用户编号',
  `account_name` varchar(15) NOT NULL COMMENT '用户名',
  `account_number` varchar(25) NOT NULL COMMENT '账号',
  `bank_id` int(10)  NOT NULL COMMENT '开户行',
  `sub_branch_name` varchar(50) NOT NULL COMMENT '支行',
  `status` tinyint(2)  NOT NULL DEFAULT '1' COMMENT '0删除1正常',
  `note` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for business_deposit
-- ----------------------------
DROP TABLE IF EXISTS `business_deposit`;
CREATE TABLE `business_deposit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_id` bigint(20) NOT NULL COMMENT '商家ID',
  `user_num` varchar(50) NOT NULL COMMENT '商家编号',
  `business_account` varchar(20) NOT NULL COMMENT '商家账号',
  `business_name` varchar(50) NOT NULL COMMENT '负责人姓名',
  `deposit_number` varchar(50) NOT NULL COMMENT '商户订单号',
  `third_number` varchar(50) DEFAULT NULL COMMENT '第三方订单号',
  `third_account` varchar(200) DEFAULT NULL COMMENT '第三方平台支付账户',
  `payment_method` tinyint(3) DEFAULT NULL COMMENT '充值方式：2-支付宝,3微信',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `status` tinyint(3) NOT NULL COMMENT '状态(0待支付 1未核实 2已核实 3申请退款 4受理退款 5退款成功 6退款失败)',
  `business_bank_account_id` bigint(20) DEFAULT NULL COMMENT '提现银行卡关联ID',
  `province_id` bigint(20) NOT NULL COMMENT '注册区域省ID(冗余)',
  `city_id` bigint(20) NOT NULL COMMENT '注册区域城市ID(冗余)',
  `area_id` bigint(20) NOT NULL COMMENT '注册区域区ID(冗余)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '初始化时间',
  `gmt_pay` datetime DEFAULT NULL COMMENT '付款回调时间',
  `gmt_verify` datetime DEFAULT NULL COMMENT '核实时间',
  `gmt_refund` datetime DEFAULT NULL COMMENT '退款时间',
  `gmt_accpet` datetime DEFAULT NULL COMMENT '受理时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `oper_user_id` bigint(20) DEFAULT NULL COMMENT '处理人ID',
  `oper_user_name` varchar(50) DEFAULT NULL COMMENT '处理人姓名',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for fans_invite_detail
-- ----------------------------
DROP TABLE IF EXISTS `fans_invite_detail`;
CREATE TABLE `fans_invite_detail` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20)  NOT NULL COMMENT '商家ID',
  `point_num` varchar(25) NOT NULL DEFAULT '' COMMENT '积分编号',
  `region_name` text NOT NULL COMMENT '邀请区域名称',
  `sex` tinyint(2)  DEFAULT NULL COMMENT '性别 (0--男，2--女，1--全部)',
  `age` varchar(10) NOT NULL DEFAULT '' COMMENT '年龄区间',
  `invite_fans_count` int(10)  NOT NULL DEFAULT '0' COMMENT '邀请粉丝数量',
  `consume_point` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '消费积分',
  `gmt_create` datetime NOT NULL COMMENT '邀请时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for freeze
-- ----------------------------
DROP TABLE IF EXISTS `freeze`;
CREATE TABLE `freeze` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `money` decimal(10,6) NOT NULL COMMENT '冻结金额',
  `previous_money` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '操作前冻结资金',
  `original_money` decimal(10,6) NOT NULL COMMENT '冻结金额(原始冗余)',
  `fund_type` tinyint(2) NOT NULL COMMENT '类型(1-订单)',
  `fund_biz_type` tinyint(2) NOT NULL COMMENT '业务类型(10-(订单)用户确认收货初始化冻结资金|11-(订单)商家同意退款减冻结资金)',
  `days` int(2) NOT NULL COMMENT '冻结周期(天)',
  `biz_id` bigint(20) NOT NULL COMMENT '关联业务表主键',
  `item_id` bigint(20) DEFAULT NULL COMMENT '业务表子项主键',
  `order_num` varchar(30) DEFAULT NULL COMMENT '订单号',
  `status` tinyint(2) NOT NULL COMMENT '状态(0-冻结1-释放)',
  `remark` varchar(200) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for love_detail
-- ----------------------------
DROP TABLE IF EXISTS `love_detail`;
CREATE TABLE `love_detail` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '爱心标题',
  `love_num` varchar(30) NOT NULL DEFAULT '' COMMENT '爱心编号',
  `user_num` varchar(19) NOT NULL DEFAULT '' COMMENT '用户编号',
  `love_type` tinyint(3)  NOT NULL COMMENT '爱心来源类型',
  `amount` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '金额',
  `previous_amount` decimal(20,6)  NOT NULL DEFAULT '0.000000' COMMENT '操作前爱心账户',
  `remark` varchar(30) DEFAULT '' COMMENT '备注',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `biz_id` varchar(500) DEFAULT '0' COMMENT '业务类型操作对应的业务表ID',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for point_detail
-- ----------------------------
DROP TABLE IF EXISTS `point_detail`;
CREATE TABLE `point_detail` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '积分标题',
  `point_num` varchar(25) NOT NULL DEFAULT '' COMMENT '积分编号',
  `user_num` varchar(19) NOT NULL DEFAULT '' COMMENT '用户编号',
  `point_type` tinyint(3)  NOT NULL COMMENT '积分类型',
  `point` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '积分',
  `previous_point` decimal(20,6)  NOT NULL DEFAULT '0.000000' COMMENT '操作前积分',
  `direction` tinyint(2) NOT NULL COMMENT '1-支出2-收入',
  `biz_id` varchar(20) NOT NULL DEFAULT '0',
  `province_id` int(8) DEFAULT NULL COMMENT '省ID',
  `city_id` int(8) DEFAULT NULL COMMENT '省ID',
  `area_id` int(8) DEFAULT NULL COMMENT '省ID',
  `remark` varchar(30) DEFAULT '' COMMENT '备注',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '键',
  `value` varchar(20) NOT NULL COMMENT '值',
  `remark` varchar(50) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for property_info
-- ----------------------------
DROP TABLE IF EXISTS `property_info`;
CREATE TABLE `property_info` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL DEFAULT '' COMMENT '用户编号',
  `balance` decimal(20,6)  NOT NULL DEFAULT '0.000000' COMMENT '余额',
  `point` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '积分',
  `love_account` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '爱心账户',
  `freeze_money` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '冻结资金',
  `pay_password` char(57) DEFAULT NULL COMMENT '支付密码',
  `freeze` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否冻结(0-否1-是)',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(2) NOT NULL COMMENT '用户类型(用户1|商家2)',
  `recharge_money` decimal(18,4)  NOT NULL COMMENT '充值金额/积分',
  `current_scale` varchar(15)  NOT NULL COMMENT '当前充值比例',
  `money` decimal(18,4)  NOT NULL COMMENT '充值所得金额/积分',
  `recharge_type` tinyint(3)  NOT NULL COMMENT '充值类型：1-余额,2-积分',
  `channel` tinyint(3)  NOT NULL COMMENT '充值方式：2-支付宝,3微信',
  `status` tinyint(3)  NOT NULL COMMENT '1-待支付,2-成功,3-失败',
  `recharge_number` varchar(25)  DEFAULT NULL COMMENT '充值单号',
  `third_number` varchar(50)  DEFAULT NULL COMMENT '第三方支付的订单号',
  `province_id` int(8) DEFAULT NULL COMMENT '省ID',
  `city_id` int(8) DEFAULT NULL COMMENT '市ID',
  `area_id` int(8) DEFAULT NULL COMMENT '区ID',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `note` text  COMMENT '备注',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for transaction_detail
-- ----------------------------
DROP TABLE IF EXISTS `transaction_detail`;
CREATE TABLE `transaction_detail` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '交易标题',
  `transaction_num` varchar(30) NOT NULL DEFAULT '' COMMENT '交易编号',
  `user_num` varchar(19) NOT NULL DEFAULT '' COMMENT '用户编号',
  `transaction_type` tinyint(3) NOT NULL COMMENT '交易类型(用户（1-余额充值2-广告3-扫红包4-退款5-下级收益6-买单7-付商品订单8-积分充值9-提现）商家（100-买单101-订单102-下级收益103-余额充值104-投放广告105-积分充值106-退款107-提现）)',
  `transaction_account` varchar(50) NOT NULL DEFAULT '' COMMENT '第三方账户(如果是余额记账号，第三方记第三方账号)',
  `transaction_account_type` tinyint(3)  NOT NULL COMMENT '支付方式(1-余额2-支付宝3微信)',
  `amount` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '金额',
  `previous_amount` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '操作前余额',
  `direction` tinyint(2) NOT NULL COMMENT '1-支出2-收入',
  `third_transaction_num` varchar(30) DEFAULT NULL COMMENT '第三方支付交易号',
  `biz_id` varchar(500) DEFAULT '0' COMMENT '业务类型操作对应的业务表ID',
  `biz_num` varchar(30) DEFAULT NULL COMMENT '业务订单号',
  `transaction_desc` varchar(150) DEFAULT '' COMMENT '交易说明',
  `province_id` int(8) DEFAULT NULL COMMENT '省ID',
  `city_id` int(8) DEFAULT NULL COMMENT '省ID',
  `area_id` int(8) DEFAULT NULL COMMENT '省ID',
  `remark` varchar(30) DEFAULT '' COMMENT '备注',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for withdraw_cash
-- ----------------------------
DROP TABLE IF EXISTS `withdraw_cash`;
CREATE TABLE `withdraw_cash` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `account` varchar(30) NOT NULL DEFAULT '' COMMENT '账号(冗余)',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称(冗余)',
  `province_id` int(8) NOT NULL DEFAULT '0' COMMENT '注册区域省ID(冗余)',
  `city_id` int(8) NOT NULL DEFAULT '0' COMMENT '注册区域城市ID(冗余)',
  `area_id` int(8) NOT NULL DEFAULT '0' COMMENT '注册区域区ID(冗余)',
  `region_full_name` varchar(200) NOT NULL DEFAULT '' COMMENT '用户区域全路径(冗余)',
  `cash_money` decimal(18,6) NOT NULL DEFAULT '0.000000' COMMENT '提现金额',
  `current_scale` varchar(16) NOT NULL COMMENT '提现比例1:0.95',
  `money` decimal(18,6) NOT NULL DEFAULT '0.000000' COMMENT '到账金额',
  `user_type` tinyint(3) NOT NULL COMMENT '提现类型(1-用户提现2-商家提现)',
  `channel` tinyint(3) NOT NULL COMMENT '渠道（1-人工2-代付)',
  `status` tinyint(3) NOT NULL COMMENT '提现状态(1-申请中2-受理中3-提现成功4-提现失败)',
  `business_bank_account_id` bigint(20) NOT NULL COMMENT '提现银行卡关联ID',
  `cash_number` varchar(25) NOT NULL COMMENT '提现单号',
  `third_number` varchar(25) DEFAULT NULL COMMENT '第三方订单号(代付时存在)',
  `audit_user_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `audit_user_name` varchar(50) DEFAULT NULL COMMENT '审核人姓名',
  `audit_faild_reason` text COMMENT '审核失败原因',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime NOT NULL,
  `gmt_accept` datetime DEFAULT NULL COMMENT '受理时间',
  `gmt_finish` datetime DEFAULT NULL COMMENT '成功/失败时间',
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `income_daily_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(2) DEFAULT NULL COMMENT '1-会员 2-商家',
  `money` decimal(20,6) NOT NULL COMMENT '积分或余额',
  `income_type` tinyint(2) NOT NULL COMMENT '收益类型（余额|积分）',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);