ALTER TABLE `eshop_order`.`shopping_refund_detail`
ADD COLUMN `point` decimal(10, 2) UNSIGNED DEFAULT 0 COMMENT '退还积分' AFTER `amount`,
ADD COLUMN `actual_amount` decimal(10, 2) UNSIGNED DEFAULT 0 COMMENT '实际退款金额' AFTER `point`;