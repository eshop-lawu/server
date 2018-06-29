ALTER TABLE `eshop_game`.`game_puzzle_attend_record`
MODIFY COLUMN `reward_point` decimal(10,2) DEFAULT NULL COMMENT '参与成功奖励积分' AFTER `game_rank`;