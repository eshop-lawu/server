USE eshop_activity;
ALTER TABLE `eshop_activity`.`help_redpacket_activity` 
MODIFY COLUMN `activity_theme` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '活动主题' AFTER `id`,
MODIFY COLUMN `min_redpacket` decimal(6, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最小红包额度' AFTER `end_time`,
MODIFY COLUMN `max_redpacket` decimal(6, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最大红包额度' AFTER `min_redpacket`,
MODIFY COLUMN `multiple` decimal(6, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '红包叠加倍数' AFTER `total_auto_amount`,
MODIFY COLUMN `is_close_entry` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否关闭活动入口' AFTER `is_open`,
MODIFY COLUMN `start_pic` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '活动开始图片' AFTER `is_close_entry`;