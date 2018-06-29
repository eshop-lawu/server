USE `eshop_user`;
ALTER TABLE `user_grade` ADD COLUMN `free_lottery_count` INT(5) UNSIGNED NOT NULL DEFAULT '1' COMMENT '免费抽奖次数' AFTER `min_growth_value`;
UPDATE user_grade set free_lottery_count=1 where id=1;
UPDATE user_grade set free_lottery_count=2 where id=2;
UPDATE user_grade set free_lottery_count=3 where id=3;
UPDATE user_grade set free_lottery_count=4 where id=4;
UPDATE user_grade set free_lottery_count=5 where id=5;
