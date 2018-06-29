use eshop_game;
CREATE TABLE `game_robot_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(20) NOT NULL COMMENT '机器人账号',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `password` varchar(20) NOT NULL COMMENT '机器人账号密码',
  `nickname` varchar(300) DEFAULT NULL COMMENT '机器人昵称(多个昵称用逗号分隔)',
  `head_img` varchar(300) DEFAULT NULL COMMENT '机器人头像(多个头像用逗号分隔)',
  `region` varchar(300) DEFAULT NULL COMMENT '机器人区域(多个区域用逗号分隔)',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '机器人状态(0-禁用|1-空闲|2-使用中)',
  `server_id` int(10) unsigned DEFAULT '0' COMMENT '机器人服务id',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`),
  UNIQUE KEY `uk_user_num` (`user_num`)
) COMMENT='游戏机器人账号表';

CREATE TABLE `game_robot_server` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `service_path` varchar(100) NOT NULL COMMENT '服务路径',
  `instances` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '实例数量',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '服务状态(0-禁用|1-启用)',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='机器人服务表';