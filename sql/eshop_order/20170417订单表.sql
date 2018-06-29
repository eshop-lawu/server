/**
 * 20170417
 * 在订单表中添加第三方交易号和支付方式、会员编号，提供给资产模块
 */
ALTER TABLE shopping_order ADD COLUMN `third_number` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方支付交易号' AFTER `order_num`;
ALTER TABLE shopping_order ADD COLUMN `payment_method` tinyint(2) unsigned DEFAULT NULL COMMENT '支付方式(1-余额 2-微信 3-支付宝)' AFTER `order_num`;
ALTER TABLE shopping_order ADD COLUMN `member_num` varchar(19) DEFAULT NULL COMMENT '会员编号' AFTER `member_id`;