alter table `eshop_game`.`game_puzzle_account` add column  share_attend_count int(5) default '0' COMMENT '分享免费次数' after free_count;
alter table `eshop_game`.`game_mind_account` add column  share_attend_count int(5) default '0' COMMENT '分享免费次数' after free_count;
alter table `eshop_game`.`game_dial_account` add column  share_attend_count int(5) default '0' COMMENT '分享免费次数' after free_count;

alter table `eshop_game`.`game_dial` add column  share_attend_count int(5) default '0' COMMENT '分享可免费次数' after free_count;