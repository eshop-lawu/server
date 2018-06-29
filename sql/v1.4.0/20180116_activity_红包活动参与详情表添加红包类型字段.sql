USE `eshop_activity`;
ALTER TABLE `eshop_activity`.`help_redpacket_attend_detail` 
ADD COLUMN `redpacket_type` tinyint(2) UNSIGNED NOT NULL DEFAULT 1 COMMENT '红包类型(1-微信红包|2-余额红包)' AFTER `is_lucky`;