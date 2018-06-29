USE `eshop_activity`;
ALTER TABLE `point_lottery_activity` ADD COLUMN `hot_number` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '热度人数' AFTER `attent_number`;