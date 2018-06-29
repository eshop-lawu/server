ALTER TABLE `eshop_activity`.`help_redpacket_activity`
ADD COLUMN `type` tinyint(2) NOT NULL COMMENT '活动类型' AFTER `redpacket_type`,
ADD COLUMN `rules` varchar(1000) COMMENT '活动规则' AFTER `is_close_entry`;