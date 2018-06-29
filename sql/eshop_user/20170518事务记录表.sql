-- 执行次数
ALTER TABLE `transaction_record` ADD COLUMN `times` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '执行次数' AFTER `is_processed`;