-- 增加小计字段长度
ALTER TABLE `discount_package_content` MODIFY COLUMN `subtotal` decimal(11,2) unsigned NOT NULL COMMENT '小计' AFTER `unit`;