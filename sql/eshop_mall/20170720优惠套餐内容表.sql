-- 修改单价和总价的字段长度
ALTER TABLE `discount_package_content` MODIFY COLUMN `unit_price`  decimal(7,2) UNSIGNED NOT NULL COMMENT '单价' AFTER `name`;
ALTER TABLE `discount_package_content` MODIFY COLUMN `subtotal`  decimal(7,2) UNSIGNED NOT NULL COMMENT '小计' AFTER `unit`;