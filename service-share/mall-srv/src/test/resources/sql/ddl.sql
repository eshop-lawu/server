/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.26_3306
Source Server Version : 50717
Source Host           : 192.168.1.26:3306
Source Database       : eshop_mall

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-12 09:23:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment_image
-- ----------------------------
DROP TABLE IF EXISTS `comment_image`;
CREATE TABLE `comment_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_url` varchar(200) NOT NULL COMMENT '图片路径',
  `comment_id` bigint(20) NOT NULL COMMENT '评价id',
  `type` tinyint(2) NOT NULL COMMENT '评论类型 1：商家2：商品',
  `status` tinyint(1) NOT NULL COMMENT '0：删除1：正常',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for comment_merchant
-- ----------------------------
DROP TABLE IF EXISTS `comment_merchant`;
CREATE TABLE `comment_merchant` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(500) DEFAULT NULL COMMENT '评价内容',
  `pay_order_id` bigint(20) DEFAULT NULL COMMENT '买单ID',
  `reply_content` varchar(500) DEFAULT NULL COMMENT '商家回复内容',
  `grade` tinyint(2) NOT NULL COMMENT '评分',
  `member_id` bigint(20) NOT NULL COMMENT '用户',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家',
  `is_anonymous` tinyint(1) NOT NULL COMMENT '是否匿名（0：否1：是）',
  `avg_spend` decimal(10,2) NOT NULL COMMENT '人均消费',
  `status` tinyint(2) NOT NULL COMMENT '状态（1：有效0：无效）',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_reply` datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for comment_product
-- ----------------------------
DROP TABLE IF EXISTS `comment_product`;
CREATE TABLE `comment_product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(500) DEFAULT NULL COMMENT '评价内容',
  `reply_content` varchar(500) DEFAULT NULL COMMENT '商家回复内容',
  `grade` tinyint(2) NOT NULL COMMENT '评分1~5星',
  `member_id` bigint(20) NOT NULL COMMENT '用户',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品',
  `order_item_id` bigint(20) NOT NULL COMMENT '订单项ID',
  `product_model_id` bigint(20) NOT NULL COMMENT '商品型号id',
  `is_anonymous` tinyint(1) NOT NULL COMMENT '是否匿名（0：否1：是）',
  `status` tinyint(2) NOT NULL COMMENT '状态（1：有效0：无效）',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_reply` datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for discount_package
-- ----------------------------
DROP TABLE IF EXISTS `discount_package`;
CREATE TABLE `discount_package` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家id',
  `merchant_store_id` bigint(20) NOT NULL COMMENT '实体门店id',
  `name` varchar(25) NOT NULL COMMENT '套餐名称',
  `cover_image` varchar(255) NOT NULL COMMENT '封面图片',
  `price` decimal(7,2) unsigned NOT NULL COMMENT '套餐价格',
  `original_price` decimal(15,2) unsigned NOT NULL COMMENT '原价',
  `other_instructions` varchar(250) DEFAULT NULL COMMENT '其他说明',
  `validity_period_begin` datetime NOT NULL COMMENT '有效期-开始(yyyy-MM-dd)',
  `validity_period_end` datetime NOT NULL COMMENT '有效期-结束(yyyy-MM-dd)',
  `use_time_week` varchar(15) NOT NULL COMMENT '使用时间-周一到周日(用1-7表示,并用逗号分隔)',
  `use_time_begin` time NOT NULL COMMENT '使用时间-开始(HH:mm)',
  `use_time_end` time NOT NULL COMMENT '使用时间-结束(HH:mm)',
  `status` tinyint(2) unsigned NOT NULL COMMENT '状态(0-删除|1-上架|2-下架)',
  `is_reservation` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否需要预约(0-免预约|1-需要预约)',
  `advance_booking_time` varchar(10) DEFAULT NULL COMMENT '提前预约时间(xx小时|xx分钟|)',
  `purchase_notes` varchar(100) DEFAULT NULL COMMENT '购买须知',
  `use_rules` varchar(550) DEFAULT NULL COMMENT '使用规则',
  `gmt_up` datetime NOT NULL COMMENT '上架时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for discount_package_content
-- ----------------------------
DROP TABLE IF EXISTS `discount_package_content`;
CREATE TABLE `discount_package_content` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `discount_package_id` bigint(20) unsigned NOT NULL COMMENT '优惠套餐id',
  `name` varchar(20) NOT NULL COMMENT '内容名称',
  `unit_price` decimal(7,2) unsigned NOT NULL COMMENT '单价',
  `quantity` int(11) unsigned NOT NULL COMMENT '数量',
  `unit` varchar(5) NOT NULL COMMENT '单位',
  `subtotal` decimal(11,2) unsigned NOT NULL COMMENT '小计',
  `status` tinyint(2) unsigned NOT NULL COMMENT '状态(0-删除|1-正常)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for discount_package_image
-- ----------------------------
DROP TABLE IF EXISTS `discount_package_image`;
CREATE TABLE `discount_package_image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `discount_package_id` bigint(20) unsigned NOT NULL COMMENT '优惠套餐id',
  `description` varchar(250) NOT NULL COMMENT '文字描述',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `status` tinyint(2) unsigned NOT NULL COMMENT '状态(0-删除|1-正常)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for discount_package_purchase_notes
-- ----------------------------
DROP TABLE IF EXISTS `discount_package_purchase_notes`;
CREATE TABLE `discount_package_purchase_notes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `note` varchar(255) NOT NULL COMMENT '购买须知选项',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for express_company
-- ----------------------------
DROP TABLE IF EXISTS `express_company`;
CREATE TABLE `express_company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(10) NOT NULL COMMENT '编码',
  `name` varchar(25) NOT NULL COMMENT '名称',
  `homepage` varchar(75)  NOT NULL COMMENT '网址',
  `tel` varchar(15)  NOT NULL COMMENT '电话',
  `ordinal` int(11) unsigned NOT NULL COMMENT '排序(对应name首字母ASCII编码的十进制',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否显示在列表',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for industry_type
-- ----------------------------
DROP TABLE IF EXISTS `industry_type`;
CREATE TABLE `industry_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` smallint(5) unsigned NOT NULL COMMENT '父编号',
  `path` varchar(25)  COMMENT '行业路径',
  `name` varchar(50)  COMMENT '行业名称',
  `image_url` varchar(120) NOT NULL DEFAULT '' COMMENT '图片路径',
  `ordinal` int(10) unsigned NOT NULL COMMENT '序号',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '1为启用,0为停用',
  `type` TINYINT(2) UNSIGNED NOT NULL DEFAULT '1' COMMENT '1--普通店铺行业,2--实体店铺行业',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for merchant_favored
-- ----------------------------
DROP TABLE IF EXISTS `merchant_favored`;
CREATE TABLE `merchant_favored` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `type` tinyint(2) NOT NULL COMMENT '1:每满、2:满减、3:全单折扣',
  `reach_amount` decimal(10,2) DEFAULT NULL COMMENT '满额',
  `favored_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠金额',
  `discount_rate` decimal(10,2) DEFAULT NULL COMMENT '折扣率',
  `valid_week_time` varchar(50) DEFAULT NULL COMMENT '每周有效时间段',
  `valid_day_begin_time` varchar(20) DEFAULT NULL COMMENT '每日有效开始时间',
  `valid_day_end_time` varchar(20) DEFAULT NULL COMMENT '每日有效结束时间',
  `entire_begin_time` date NOT NULL COMMENT '总有效期：开始时间',
  `entire_end_time` date NOT NULL COMMENT '总有效期：结束时间',
  `status` tinyint(2) NOT NULL COMMENT '状态（1：有效0：无效）',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `relate_id` bigint(20) DEFAULT NULL COMMENT '关联主键',
  `type` tinyint(3) NOT NULL COMMENT '消息类型 1:推荐店铺2:平台通知3:充值4:提现5:收益6:发货通知7:派送通知8:签收通知',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) NOT NULL COMMENT '消息内容',
  `status` tinyint(2) unsigned NOT NULL COMMENT '状态：0未读，1已读，2删除',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for message_template
-- ----------------------------
DROP TABLE IF EXISTS `message_template`;
CREATE TABLE `message_template` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `content` varchar(500) NOT NULL COMMENT '模板内容',
  `type` tinyint(3) unsigned DEFAULT NULL COMMENT '1邀请粉丝--会员2订单付款3活动消息4订单已发货5订单正在派件6提现申请7提现成功8提现失败9余额充值10积分充值11买单成功12同意退款13拒绝退款14退款成功15付款成功16推荐E友获取现金17推荐E友获取积分18推荐商家获取现金19推荐商家获取积分20ad审核通过21ad审核不通过22门店审核通过23门店审核不通过24保证金审核通过25新增订单26商家发货提醒27商家退货提醒28用户已发货29运营平台通知30现金红包31ad自动下架32邀请粉丝--商家33订单已签收34看广告获取金额35抢赞获取金额36保证金审核未通过37ad强制下架38退款申请39消费积分投放广告40消费积分红包41订单成功--商家42买单成功--商家',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '键',
  `value` varchar(20) NOT NULL COMMENT '值',
  `remark` varchar(50) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `id` int(8) unsigned NOT NULL COMMENT '区域ID',
  `parent_id` int(8) unsigned NOT NULL COMMENT '父级区域',
  `path` varchar(25) NOT NULL DEFAULT '' COMMENT '路径',
  `level` tinyint(3) unsigned NOT NULL COMMENT '层级',
  `name` varchar(50) NOT NULL COMMENT '区域名称',
  `longitude` decimal(10,7) unsigned DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,7) unsigned DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for search_word
-- ----------------------------
DROP TABLE IF EXISTS `search_word`;
CREATE TABLE `search_word` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `word` varchar(20) NOT NULL COMMENT '搜索词条',
  `type` tinyint(3) unsigned NOT NULL COMMENT '词条类型：1--门店，2--商品',
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for sms_record
-- ----------------------------
DROP TABLE IF EXISTS `sms_record`;
CREATE TABLE `sms_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` varchar(15) NOT NULL DEFAULT '' COMMENT '手机号码',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `ip` varchar(20) DEFAULT NULL COMMENT '地址',
  `type` tinyint(2) unsigned DEFAULT NULL COMMENT '类型 1--注册，2--找回登录密码，2--找回支付密码',
  `is_success` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '1成功，0失败',
  `fail_reason` varchar(500) DEFAULT NULL COMMENT '失败原因',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for suggestion
-- ----------------------------
DROP TABLE IF EXISTS `suggestion`;
CREATE TABLE `suggestion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL DEFAULT '' COMMENT '用户编号',
  `content` varchar(1000) NOT NULL DEFAULT '' COMMENT '建议内容',
  `user_type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户类型，1是商家，2是会员',
  `client_type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '客户端类型，1是android，2是ios',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for verify_code
-- ----------------------------
DROP TABLE IF EXISTS `verify_code`;
CREATE TABLE `verify_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` varchar(15) NOT NULL DEFAULT '' COMMENT '手机号码',
  `code` varchar(10) NOT NULL COMMENT '验证码',
  `purpose` tinyint(3) unsigned DEFAULT NULL COMMENT '用途',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;


-- ----------------------------
-- Table structure for inform
-- ----------------------------
DROP TABLE IF EXISTS `inform`;
CREATE TABLE `inform` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `informer_user_num` varchar(19) NOT NULL COMMENT '举报人用户编号',
  `informer_account` varchar(20) DEFAULT NULL COMMENT '举报人账号',
  `inform_type` tinyint(2) DEFAULT NULL COMMENT '举报类型:1平面广告、2E赞、3商家、4商品',
  `informt_item_id` bigint(20) DEFAULT NULL COMMENT '被举报对象ID',
  `informt_item_name` varchar(255) DEFAULT NULL COMMENT '被举报对象名称',
  `content` varchar(255) DEFAULT NULL COMMENT '举报内容',
  `status` tinyint(2) unsigned NOT NULL COMMENT '0：待处理，1：已处理，2：不处理',
  `auditor_id` int(11) DEFAULT '0' COMMENT '审核人员ID',
  `auditor_name` varchar(50) DEFAULT NULL COMMENT '审核人员名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);



INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('1', '粉丝邀请', '亲爱的{0}，您收到来自{12}的1条新的粉丝邀请。', '1', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('2', '订单待付款', '亲爱的{0}，您的订单{1}尚未付款，点击立即付款。', '2', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('3', '订单正在派件', '亲爱的{0}，您的商品{9}正在派件（{20}）。运单编号：{2}。', '5', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('4', '订单已签收', '亲爱的{0}，您已签收商品{9}', '33', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('5', '订单已发货', '亲爱的{0}，您购买的商品{9}已发货', '4', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('6', '提现通知', '亲爱的{0}，您发起的提现申请E店已受理，正在为您提现', '6', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('7', '提现成功', '亲爱的{0}，您已成功提现，请及时查收', '7', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('8', '提现失败', '亲爱的{0}，您的提现失败，原因：{18}', '8', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('9', '余额充值', '亲爱的{0}，您已成功充值账户余额，充值金额{4}元，当前账户余额{3}元', '9', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('10', '积分充值', '亲爱的{0}，您已成功充值账户积分，充值积分{4}元，当前账户积分{5}元', '10', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('11', '买单成功', '亲爱的{0}，您在{14}店内买单成功，共消费{6}元，优惠{7}元', '11', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('12', '商家同意退款', '亲爱的{0}，商家已同意您的退款申请，退款金额为{21}元。点击查看退款进度。退款编号：{8}', '12', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('13', '商家拒绝退款', '亲爱的{0}，商家拒绝您的退款申请，您可以申请平台介入', '13', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('14', '退款成功', '亲爱的{0}，您已成功退款，退款金额为{21}元', '14', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('15', '付款成功', '亲爱的{0}，您购买的商品{9}已成功付款，等待商家发货', '15', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('16', '收益通知', '亲爱的{0}，您推荐的E友助您获得一笔现金收益，金额为{10}元', '16', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('17', '收益通知', '亲爱的{0}，您推荐的E友助您获得一笔积分收益，数额为{11}积分', '17', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('18', '收益通知', '亲爱的{0}，您推荐的商家助您获得一笔现金收益，金额为{10}元', '18', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('19', '收益通知', '亲爱的{0}，您推荐的商家助您获得一笔积分收益，数额为{11}积分', '19', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('20', '收益通知', '亲爱的{0}，您通过观看广告共获得{10}元现金收益', '34', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('21', '收益通知', '亲爱的{0}，您通过抢赞共获得{10}元现金收益', '35', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('22', '收益通知', '亲爱的{0}，您获得商家{14}{10}元现金红包', '30', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('23', '广告审核通过', '亲爱的E店商家，您投放的{17}{13}已通过审核。', '20', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('24', '广告审核未通过', '亲爱的E店商家，您的{17}{13}因{18}未通过审核，请重新提交', '21', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('25', '门店审核通过', '亲爱的E店商家，您的门店{14}审核已通过，欢迎您的入驻', '22', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('26', '门店审核未通过', '亲爱的E店商家，您的门店{14}因{18}未通过审核，请检查资料并重新提交门店申请', '23', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('27', '退保证金审核通过', '亲爱的E店商家，您提交的保证金退还申请已通过审核，您的保证金将于3-5个工作日退还至您的账户余额', '24', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('28', '退保证金审核未通过', '亲爱的E店商家，您提交的保证金退还申请因{18}未通过审核，请检查信息并重新提交保证金退还申请', '36', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('29', '广告自动下架', '亲爱的E店商家，您的{17}{13}逾上架有效期，现已自动下架', '31', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('30', '广告强制下架', '亲爱的E店商家，您的{17}{13}因涉及敏感词(图)，现E店予以强制下架处理。检查修改后可重新上架', '37', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('31', '新订单', '亲爱的E店商家，您有1个新增订单,订单编号{1}', '25', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('32', '发货提醒', '亲爱的E店商家，您有{19}件商品尚未发货，请及时处理', '26', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('33', '退货提醒', '亲爱的E店商家，买家{0} 申请退货，请及时处理，订单编号：{1}', '27', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('34', '用户已发货', '亲爱的E店商家，买家{0}的退货申请（订单号：{1}）已发货。运单编号：{2}', '28', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('35', '退款申请', '亲爱的E店商家，买家{0}申请退款，请及时处理', '38', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('36', '积分消费', '亲爱的E店商家，您的{17}{13}投放成功，消费积分{15}。当前积分{5}', '39', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('37', '积分消费', '亲爱的E店商家，您的店铺红包投放成功，消费积分{15}分。当前积分{5}', '40', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('38', '积分消费', '亲爱的E店商家，您有一笔订单交易收入，交易金额{22}元', '41', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('39', '积分消费', '亲爱的E店商家，您有一笔买单交易收入，交易金额{22}元', '42', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('40', '积分消费', '亲爱的E店商家，您已成功发起粉丝邀请，消费积分{15}分。当前积分{5}分', '32', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('41', '活动消息', '亲爱的{0}，您有1条新的活动消息', '3', '2017-05-06 16:19:04', '2017-05-06 16:19:07');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('42', '退货提醒', '亲爱的{0}，商家已经填写退货地址，请您及时退货', '43', '2017-05-20 13:35:19', '2017-05-20 13:35:19');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('43', '资金冻结', '亲爱的{0}：您的资金账户已被冻结，有疑问请致电E店客服400-000-1111', '44', '2017-07-06 11:15:19', '2017-07-06 11:15:19');
INSERT INTO `message_template` (`id`, `title`, `content`, `type`, `gmt_modified`, `gmt_create`) VALUES ('44', '资金解冻', '亲爱的{0}：您的资金账户已解除冻结，恢复正常使用，有疑问请致电E店客服400-000-1111', '45', '2017-07-06 11:15:19', '2017-07-06 11:15:19');


INSERT INTO `region` (`id`, `parent_id`, `path`, `level`, `name`, `longitude`, `latitude`) VALUES ('4403', '44', '44/4403', '2', '深圳市', '114.0259737', '22.5460535');
INSERT INTO `region` (`id`, `parent_id`, `path`, `level`, `name`, `longitude`, `latitude`) VALUES ('440305', '4403', '44/4403/440305', '3', '南山区', NULL, NULL);


INSERT INTO `industry_type` (`id`, `parent_id`, `path`, `name`, `image_url`, `ordinal`, `status`,`type`, `gmt_modified`, `gmt_create`) VALUES ('10', '0', '10', '美食', '', '1', '1', '2', '2017-04-13 02:25:22', '2017-04-13 02:25:22');


-- ----------------------------
-- Table structure for work_order
-- ----------------------------
DROP TABLE IF EXISTS `work_order`;
CREATE TABLE `work_order` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
	`account` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '账号',
	`type` TINYINT(2) NOT NULL DEFAULT '1' COMMENT '提交人类型 1--用户，2--商家',
	`name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '提交工单人/门店名称',
	`status` TINYINT(2) NOT NULL DEFAULT '1' COMMENT '状态 1--未处理，2--已回复，3--不予处理',
	`content` VARCHAR(250) NOT NULL COMMENT '工单内容',
	`reply_content` VARCHAR(250) NULL DEFAULT NULL COMMENT '回复内容',
	`auditor_id` INT(11) NULL DEFAULT '0' COMMENT '审核人员ID',
	`auditor_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '审核人员名称',
	`gmt_deal` DATETIME NULL DEFAULT NULL COMMENT '处理时间',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
;


-- ----------------------------
-- Table structure for app_version
-- ----------------------------
DROP TABLE IF EXISTS `app_version`;
CREATE TABLE `app_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_version` varchar(16) NOT NULL COMMENT '版本号',
  `app_big_version` varchar(8) NOT NULL COMMENT '大版本号',
  `update_desc` varchar(500) NOT NULL DEFAULT '' COMMENT '更新内容',
  `platform` tinyint(2) NOT NULL COMMENT '1-ios 2-安卓',
  `app_type` tinyint(2) NOT NULL COMMENT 'APP类型，1：会员端，2：商家端',
  `status` tinyint(2) NOT NULL COMMENT '1:启用，2：禁用',
  `is_force` tinyint(1) NOT NULL COMMENT '是否强制更新',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for lottery_activity
-- ----------------------------
DROP TABLE IF EXISTS `lottery_activity`;
CREATE TABLE `lottery_activity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prize_name` varchar(50) NOT NULL COMMENT '奖品名称',
  `prize_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '奖品价值',
  `prize_number` int(3) UNSIGNED NOT NULL COMMENT '奖品数量',
  `image_path` varchar(200) NOT NULL COMMENT '奖品图片',
  `begin_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `grade` tinyint(2) UNSIGNED NOT NULL COMMENT '等级',
  `status` tinyint(3) UNSIGNED NOT NULL COMMENT '0--未发布，1--进行中，2--已发布，3--已结束，4--下架，5--删除',
  `gmt_modified` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for lottery_record
-- ----------------------------
DROP TABLE IF EXISTS `lottery_record`;
CREATE TABLE `lottery_record`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `lottery_activity_id` bigint(20) UNSIGNED NOT NULL COMMENT '抽奖活动id',
  `prize_name` varchar(50) NOT NULL COMMENT '奖品名称',
  `lottery_count` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '抽奖次数',
  `lottery_result` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '抽奖结果(0--未中奖，1--中奖)',
  `gmt_modified` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for app_patch_version
-- ----------------------------
DROP TABLE IF EXISTS `app_patch_version`;
CREATE TABLE `app_patch_version` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_version_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'app_version_id',
  `app_version` varchar(16) NOT NULL COMMENT '版本号',
  `patch_version` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '补丁版本号',
  `update_desc` varchar(50) NOT NULL DEFAULT '' COMMENT '更新内容',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '1--未启用，2--启用',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for window_message
-- ----------------------------
DROP TABLE IF EXISTS `window_message`;
CREATE TABLE `window_message` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`img_path` VARCHAR(200) NOT NULL COMMENT '弹窗图片',
	`relate_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '关联id',
	`type` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '类型',
	`status` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '状态：1--启用，2--禁用',
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `app_function_manage`;
CREATE TABLE `app_function_manage` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `is_enable_game` tinyint(1) DEFAULT '1'  COMMENT '是否启用游戏',
  `is_enable_love` tinyint(1) DEFAULT '1'  COMMENT '是否启用爱心账户',
  `app_version` varchar(50) NOT NULL  COMMENT 'APP版本号',
  `is_enable` tinyint(1) DEFAULT '0'  COMMENT '是否启用管理',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);
INSERT INTO `app_function_manage` (`id`, `is_enable_game`, `is_enable_love`, `app_version`, `is_enable`, `gmt_modified`, `gmt_create`) VALUES ('1', '0', '0', 'v2.8.0.22.t', '1', '2018-04-12 13:38:14', '2018-04-11 15:31:14');



