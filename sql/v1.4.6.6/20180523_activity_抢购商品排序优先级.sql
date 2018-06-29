ALTER TABLE `eshop_product`.`seckill_activity_product` 
ADD COLUMN `priority` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序优先级' AFTER `turnover`;