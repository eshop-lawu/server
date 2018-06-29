-- 用于判断是否需要物流
ALTER TABLE `shopping_order` ADD COLUMN `is_needs_logistics` tinyint(1) unsigned DEFAULT '0' COMMENT '是否需要物流(0-不需要|1-需要)' AFTER `third_number`;