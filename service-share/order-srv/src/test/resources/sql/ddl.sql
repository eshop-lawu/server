/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.22_3306
Source Server Version : 50717
Source Host           : 192.168.1.22:3306
Source Database       : eshop_order

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-12 10:28:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '用户ID',
  `member_num` varchar(19) NOT NULL DEFAULT '' COMMENT '用户编号',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `merchant_num` varchar(19) NOT NULL COMMENT '商家编号',
  `order_num` varchar(30) NOT NULL COMMENT '买单编号',
  `third_number` varchar(50) DEFAULT NULL COMMENT '第三方支付编号',
  `total_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '原价',
  `actual_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实际消费',
  `favored_amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
  `not_favored_amount` decimal(10,2) DEFAULT '0.00' COMMENT '不参与优惠金额',
  `pay_type` tinyint(2) DEFAULT NULL COMMENT '支付方式(1-余额 2-支付宝 3-微信)',
  `is_evaluation` tinyint(1) DEFAULT '0' COMMENT '评价：0未评1已评',
  `status` tinyint(2) NOT NULL COMMENT '1-待支付,2-成功,3-失败',
  `order_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0删除1正常) ',
  `is_fans` tinyint(1) unsigned NOT NULL COMMENT '买单前用户是否是商家的粉丝',
  `commission_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否计算过提成(0-没有计算过提成|1-计算过提成)',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_commission` datetime DEFAULT NULL COMMENT '计算提成时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `value` varchar(20) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `merchant_id` bigint(20) unsigned NOT NULL,
  `merchant_store_id` bigint(20) unsigned NOT NULL,
  `merchant_name` varchar(100) NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `product_model_id` bigint(20) unsigned NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `sales_price` decimal(12,2) unsigned NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for shopping_order
-- ----------------------------
DROP TABLE IF EXISTS `shopping_order`;
CREATE TABLE `shopping_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `member_num` varchar(19) DEFAULT NULL COMMENT '会员编号',
  `member_nickname` varchar(50) NOT NULL COMMENT '会员昵称',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `merchant_store_id` bigint(20) unsigned NOT NULL COMMENT '门店id',
  `merchant_store_region_path` varchar(25) NOT NULL COMMENT '商家门店区域（省市区id）',
  `activity_id` bigint(20) unsigned DEFAULT NULL COMMENT '抢购活动id',
  `activity_product_id` bigint(20) unsigned DEFAULT NULL COMMENT '抢购活动商品id',
  `merchant_num` varchar(19) DEFAULT '' COMMENT '商家编号',
  `merchant_name` varchar(100) NOT NULL COMMENT '商家名称',
  `consignee_name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `consignee_address` varchar(180) NOT NULL COMMENT '收货人地址',
  `consignee_mobile` varchar(15) NOT NULL COMMENT '收货人手机号码',
  `remark` varchar(100) DEFAULT NULL COMMENT '订单备注(退货理由)',
  `message` varchar(200) DEFAULT NULL COMMENT '买家留言',
  `freight_price` decimal(10,2) unsigned NOT NULL COMMENT '运费',
  `commodity_total_price` decimal(10,2) unsigned NOT NULL COMMENT '商品总价',
  `order_total_price` decimal(10,2) unsigned NOT NULL COMMENT '订单总价',
  `actual_amount` decimal(10,2) unsigned DEFAULT NULL COMMENT '实际支付给商家的金额',
  `commission_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否计算过提成(0-没有计算过提成|1-计算过提成)',
  `order_status` tinyint(2) unsigned NOT NULL COMMENT '订单的总状态(0-待处理|1-待付款|2-待发货|3-待收货|4-交易成功|5-交易关闭|6-退款中)',
  `status` tinyint(2) NOT NULL COMMENT '状态(0删除1正常)',
  `send_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '发送提醒的次数',
  `is_fans` tinyint(1) unsigned NOT NULL COMMENT '购买前用户是否是商家的粉丝',
  `is_no_reason_return` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否支持无理由退货,0否 1是',
  `is_automatic_receipt` tinyint(1) unsigned DEFAULT NULL COMMENT '是否自动收货(0-否|1-是)',
  `is_done` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '当前订单是否完成(0-未完成|1-已完成)',
  `is_refund_items` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否有退款项(0-没有|1-有)',
  `shopping_cart_ids_str` varchar(50) DEFAULT NULL COMMENT '对应的购物车相应的id(多个id用,分隔)',
  `order_num` varchar(20) NOT NULL COMMENT '订单编号',
  `payment_method` tinyint(2) unsigned DEFAULT NULL COMMENT '支付方式(1-余额 2-支付宝 3-微信)',
  `third_number` varchar(50) DEFAULT NULL COMMENT '第三方支付交易号',
  `is_needs_logistics` tinyint(1) unsigned DEFAULT '0' COMMENT '是否需要物流(0-不需要|1-需要)',
  `waybill_num` varchar(20) DEFAULT NULL COMMENT '运单编号',
  `express_company_id` int(11) unsigned DEFAULT NULL COMMENT '快递公司id',
  `express_company_code` varchar(40) DEFAULT NULL COMMENT '快递公司编码',
  `express_company_name` varchar(50) DEFAULT NULL COMMENT '快递公司名称',
  `gmt_commission` datetime DEFAULT NULL COMMENT '计算提成的时间',
  `gmt_payment` datetime DEFAULT NULL COMMENT '付款时间',
  `gmt_transport` datetime DEFAULT NULL COMMENT '发货时间',
  `gmt_transaction` datetime DEFAULT NULL COMMENT '成交时间',
  `gmt_done` datetime DEFAULT NULL COMMENT '订单完成时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
);

-- ----------------------------
-- Table structure for shopping_order_item
-- ----------------------------
DROP TABLE IF EXISTS `shopping_order_item`;
CREATE TABLE `shopping_order_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shopping_order_id` bigint(20) unsigned NOT NULL COMMENT '订单id',
  `product_id` bigint(11) unsigned NOT NULL COMMENT '商品id',
  `product_name` varchar(100) NOT NULL COMMENT '商品名称',
  `product_model_id` bigint(20) unsigned NOT NULL COMMENT '商品型号id',
  `activity_product_model_id` bigint(20) unsigned DEFAULT NULL COMMENT '活动商品型号id',
  `product_model_name` varchar(100) NOT NULL COMMENT '商品型号名称',
  `product_feature_image` varchar(120) NOT NULL COMMENT '商品特征图片',
  `regular_price` decimal(10,2) unsigned NOT NULL COMMENT '原价',
  `sales_price` decimal(10,2) unsigned NOT NULL COMMENT '现价',
  `quantity` int(11) unsigned NOT NULL COMMENT '数量',
  `is_evaluation` tinyint(1) unsigned NOT NULL COMMENT '是否评价(0-未评价|1-已评价)',
  `is_allow_refund` tinyint(1) unsigned NOT NULL COMMENT '是否支持退换货(0-否1-是)',
  `order_status` tinyint(2) unsigned NOT NULL COMMENT '订单项状态(0-待处理|1-待付款|2-待发货|3-待收货|4-交易成功|5-交易关闭|6-退款中)',
  `refund_status` tinyint(2) unsigned DEFAULT NULL COMMENT '退款状态(0-待商家确认|1-填写退货地址|2-待退货|3-待退款|4-退款成功|5-退款失败|6-平台介入)',
  `send_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '发送提醒的次数',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
);

-- ----------------------------
-- Table structure for shopping_refund_detail
-- ----------------------------
DROP TABLE IF EXISTS `shopping_refund_detail`;
CREATE TABLE `shopping_refund_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shopping_order_item_id` bigint(20) unsigned NOT NULL COMMENT '购物订单项id',
  `type` tinyint(2) unsigned NOT NULL COMMENT '退款类型(0-退款|1-退货退款)',
  `reason` varchar(100) NOT NULL COMMENT '退货原因',
  `description` varchar(200) DEFAULT NULL COMMENT '退款描述',
  `voucher_picture` varchar(150) DEFAULT NULL COMMENT '凭证图片',
  `refuse_images` varchar(500) DEFAULT NULL COMMENT '拒绝退款图片 ',
  `refusal_reasons` varchar(200) DEFAULT NULL COMMENT '拒绝退款理由',
  `amount` decimal(10,2) NOT NULL COMMENT '退款金额',
  `consignee_name` varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  `consignee_address` varchar(100) DEFAULT NULL COMMENT '收货人地址',
  `consignee_mobile` varchar(15) DEFAULT NULL COMMENT '收货人手机号码',
  `express_company_id` int(11) DEFAULT NULL COMMENT '快递公司id',
  `express_company_code` varchar(10) DEFAULT NULL COMMENT '快递公司编码',
  `express_company_name` varchar(25) DEFAULT NULL COMMENT '快递公司名称',
  `waybill_num` varchar(20) DEFAULT NULL COMMENT '物流编号',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '状态(0-无效|1-有效)',
  `is_agree` tinyint(1) unsigned DEFAULT NULL COMMENT '商家是否同意退货申请',
  `gmt_refund` datetime DEFAULT NULL COMMENT '退款时间',
  `gmt_confirmed` datetime DEFAULT NULL COMMENT '商家确认时间',
  `gmt_fill` datetime DEFAULT NULL COMMENT '商家填写退货地址',
  `gmt_submit` datetime DEFAULT NULL COMMENT '买家提交退货物流时间',
  `gmt_intervention` datetime DEFAULT NULL COMMENT '平台介入时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for shopping_refund_process
-- ----------------------------
DROP TABLE IF EXISTS `shopping_refund_process`;
CREATE TABLE `shopping_refund_process` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shopping_refund_detail_id` bigint(20) unsigned NOT NULL,
  `refund_status` tinyint(2) unsigned NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
);
