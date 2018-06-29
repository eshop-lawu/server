-- 添加是否提醒字段，用于定时任务
use eshop_product;
ALTER TABLE `seckill_activity`
ADD COLUMN `is_remind` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否提醒' AFTER `activity_status`,
MODIFY COLUMN `activity_status`  tinyint(2) UNSIGNED NOT NULL DEFAULT 1 COMMENT '活动状态(1-未发布|2-发布中|3-审核中|4-未开始|5-进行中|6-已结束)' AFTER `picture`;
