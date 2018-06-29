alter table withdraw_cash add column `account` varchar(30) NOT NULL DEFAULT '' COMMENT '账号(冗余)' after `user_num`;
alter table withdraw_cash add column `name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称(冗余)' after `account`;
alter table withdraw_cash add column `province_id` int(8) NOT NULL DEFAULT 0 COMMENT '注册区域省ID(冗余)' after `name`;
alter table withdraw_cash add column `city_id` int(8) NOT NULL DEFAULT 0 COMMENT '注册区域城市ID(冗余)' after `province_id`;
alter table withdraw_cash add column `area_id` int(8) NOT NULL DEFAULT 0 COMMENT '注册区域区ID(冗余)' after `city_id`;