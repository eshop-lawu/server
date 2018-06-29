alter table message add column `title` varchar(50) DEFAULT NULL COMMENT '标题' after type;

alter table message modify column `user_num` varchar(19) NOT NULL COMMENT '用户编号' after id;