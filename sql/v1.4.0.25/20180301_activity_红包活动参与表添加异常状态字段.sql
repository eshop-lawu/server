ALTER TABLE `eshop_activity`.`help_redpacket_attend_detail`
ADD COLUMN `abnormal_status` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '异常状态(0-正常|1-疑似|2-异常|3-白名单))' AFTER `status`;