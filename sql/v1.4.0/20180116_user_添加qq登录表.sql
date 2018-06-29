use eshop_user;
CREATE TABLE `qq_login_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT NULL COMMENT '用户编号',
  `openid` varchar(50)  NOT NULL COMMENT 'openid',
  `nickname` varchar(300) DEFAULT '' COMMENT '昵称',
  `gender` varchar(2) DEFAULT '男' COMMENT '性别',
  `figureurl` varchar(500) DEFAULT '' COMMENT '大小为100×100像素的QQ空间头像URL',
  `msg` varchar(500) DEFAULT '' COMMENT '错误信息提示',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_openid` (`openid`)
) COMMENT='QQ登录用户表';