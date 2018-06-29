alter table `eshop_game`.`game_mind_config` add column  robot_min_right_count int(5) NOT null COMMENT '机器人答题正确最小数量' after status;
alter table `eshop_game`.`game_mind_config` add column  robot_max_right_count int(5) NOT null COMMENT '机器人答题正确最大数量' after robot_min_right_count;
alter table `eshop_game`.`game_mind_config` add column  robot_status tinyint(2) default 1 COMMENT '机器人状态  0-禁用  1-启用' after robot_max_right_count;
alter table `eshop_game`.`game_mind_config` add column  robot_effective_time int(5) default 0 COMMENT '机器人生效时间（秒）' after status;


alter table `eshop_game`.`game_puzzle_difficulty` add column  robot_min_second int(5)  NOT NULL COMMENT '机器人答题时间（小）' after challenge_time;
alter table `eshop_game`.`game_puzzle_difficulty` add column  robot_max_second int(5)  NOT NULL COMMENT '机器人答题时间（大）' after robot_min_second;

alter table `eshop_game`.`game_puzzle_config` add column  robot_status tinyint(2) default 1 COMMENT '机器人状态  0-禁用  1-启用' after forbidden_remark;
alter table `eshop_game`.`game_puzzle_config` add column  robot_effective_time int(5) default 0 COMMENT '机器人生效时间（秒）' after forbidden_remark;