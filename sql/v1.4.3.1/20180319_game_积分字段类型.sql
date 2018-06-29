ALTER TABLE `eshop_game`.`game_mind_attend_record`
MODIFY COLUMN `reward_point` int(10) UNSIGNED DEFAULT NULL COMMENT '参与成功奖励积分' AFTER `game_rank`;