-- 用于计算提成
ALTER TABLE `shopping_order` ADD COLUMN `gmt_commission` datetime NULL COMMENT '计算提成的时间' AFTER `express_company_name`;
ALTER TABLE `shopping_order` ADD COLUMN `commission_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否计算过提成(0-没有计算过提成|1-计算过提成)' AFTER `order_total_price`;
ALTER TABLE `shopping_order` ADD COLUMN `actual_amount` decimal(10,2) unsigned DEFAULT NULL COMMENT '实际支付给商家的金额' AFTER `order_total_price`;