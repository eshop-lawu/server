USE `eshop_activity`;
ALTER TABLE `eshop_activity`.`help_redpacket_activity` 
MODIFY COLUMN `total_manual_amount` decimal(20, 2) UNSIGNED DEFAULT NULL COMMENT '手动分配红包总额' AFTER `max_redpacket`,
MODIFY COLUMN `total_auto_amount` decimal(20, 2) UNSIGNED DEFAULT NULL COMMENT '自动分配红包总额' AFTER `total_manual_amount`;