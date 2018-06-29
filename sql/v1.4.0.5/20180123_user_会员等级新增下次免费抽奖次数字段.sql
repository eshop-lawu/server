USE `eshop_user`;
ALTER TABLE `user_grade` ADD COLUMN `next_free_lottery_count` INT(5) UNSIGNED NOT NULL DEFAULT '0' COMMENT '下次免费抽奖次数' AFTER `free_lottery_count`;