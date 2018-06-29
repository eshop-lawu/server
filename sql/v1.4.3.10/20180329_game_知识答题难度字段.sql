alter table `eshop_game`.`game_mind_question` add column  difficulty_level tinyint(2) default '1' COMMENT '难度系数 1-简单 2困难' after right_answer;
