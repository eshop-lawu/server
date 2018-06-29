-- 判断订单项是否可以退货
ALTER TABLE `shopping_order_item` ADD COLUMN `is_allow_refund` tinyint(1) unsigned NOT NULL COMMENT '是否支持退换货(0-否1-是)' AFTER `is_evaluation`;