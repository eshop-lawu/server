ALTER TABLE `eshop_order`.`shopping_order`
ADD COLUMN `deduction_points` decimal(10, 2) UNSIGNED DEFAULT 0 COMMENT '抵扣积分' AFTER `commodity_total_price`,
ADD COLUMN `deduction_points_amount` decimal(10, 2) UNSIGNED DEFAULT 0 COMMENT '积分抵扣金额' AFTER `deduction_points`;

ALTER TABLE `eshop_order`.`shopping_order_item`
ADD COLUMN `deduction_points` decimal(10, 2) UNSIGNED DEFAULT 0 COMMENT '抵扣积分' AFTER `sales_price`,
ADD COLUMN `deduction_points_amount` decimal(10, 2) UNSIGNED DEFAULT 0 COMMENT '积分抵扣金额' AFTER `deduction_points`;