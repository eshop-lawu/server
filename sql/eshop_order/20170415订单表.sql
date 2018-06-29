/*20170406 建表*/
CREATE TABLE `shopping_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `merchant_name` varchar(100) NOT NULL COMMENT '商家名称',
  `consignee_name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `consignee_address` varchar(100) NOT NULL COMMENT '收货人地址',
  `consignee_mobile` varchar(15) NOT NULL COMMENT '收货人手机号码',
  `remark` varchar(100) DEFAULT NULL COMMENT '订单备注(退货理由)',
  `message` varchar(200) DEFAULT NULL COMMENT '买家留言',
  `freight_price` decimal(10,2) unsigned NOT NULL COMMENT '运费',
  `commodity_total_price` decimal(10,2) unsigned NOT NULL COMMENT '商品总价',
  `order_total_price` decimal(10,2) unsigned NOT NULL COMMENT '订单总价',
  `order_status` tinyint(2) unsigned NOT NULL COMMENT '订单的总状态(0-待付款|1-待发货|2-待收货|3-交易成功|4-交易取消|5-待商家确认|6-待退货|7-待退款|8-退款成功)',
  `status` tinyint(2) NOT NULL COMMENT '状态(0删除1正常)',
  `is_no_reason_return` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否支持无理由退货,0否 1是',
  `is_evaluation` tinyint(1) unsigned NOT NULL COMMENT '是否评价',
  `order_num` varchar(20) NOT NULL COMMENT '订单编号',
  `waybill_num` varchar(20) DEFAULT NULL COMMENT '运单编号',
  `express_company_id` int(11) unsigned DEFAULT NULL COMMENT '快递公司id',
  `express_company_code` varchar(10) DEFAULT NULL COMMENT '快递公司编码',
  `express_company_name` varchar(25) DEFAULT NULL COMMENT '快递公司名称',
  `gmt_payment` datetime DEFAULT NULL COMMENT '付款时间',
  `gmt_transport` datetime DEFAULT NULL COMMENT '发货时间',
  `gmt_transaction` datetime DEFAULT NULL COMMENT '成交时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='购物订单';

/* 
 * 201704014 
 * 订单表删除是否评价字段
 * 添加购物车id字符串，用于删除购物车记录
 * */
ALTER TABLE shopping_order DROP COLUMN `is_evaluation`
ALTER TABLE shopping_order ADD COLUMN `shopping_cart_ids_str` varchar(50) NOT NULL COMMENT '对应的购物车相应的id(多个id用,分隔)' AFTER `order_num`

/*
 * 20170414
 * 添加商家编号字段
 * 用于用户确认收货保存到资金冻结表
 */
ALTER TABLE shopping_order  ADD COLUMN `merchant_num` varchar(19) DEFAULT '' COMMENT '商家编号' AFTER `merchant_id`

/*
 * 20170415
 * 判断订单是否自动收货
 */
ALTER TABLE shopping_order  ADD COLUMN `is_automatic_receipt` tinyint(1) unsigned NULL COMMENT '是否自动收货,0否 1是' AFTER `is_no_reason_return`