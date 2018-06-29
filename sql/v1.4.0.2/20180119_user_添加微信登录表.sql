USE `eshop_user`;
CREATE TABLE `wx_login_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT NULL COMMENT '用户编号',
  `subscribe` int(3) DEFAULT '0' COMMENT '是否订阅公众号',
  `openid` varchar(50) NOT NULL COMMENT 'openid',
  `nickname` varchar(300) DEFAULT '' COMMENT '昵称',
  `sex` int(3) DEFAULT '0' COMMENT '性别',
  `city` varchar(50) DEFAULT '' COMMENT '用户所在城市',
  `country` varchar(50) DEFAULT '' COMMENT '用户所在国家',
  `province` varchar(50) DEFAULT '' COMMENT '用户所在省份',
  `language` varchar(15) DEFAULT '' COMMENT '用户的语言',
  `headimgurl` varchar(500) DEFAULT '' COMMENT '头像',
  `subscribe_time` datetime DEFAULT NULL COMMENT '用户关注时间',
  `unionid` varchar(50) DEFAULT '' COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `remark` varchar(200) DEFAULT '' COMMENT '公众号运营者对粉丝的备注',
  `groupid` bigint(20) unsigned DEFAULT '0' COMMENT '用户所在的分组ID',
  `tagid_list` varchar(200) DEFAULT '' COMMENT '用户被打上的标签ID列表',
  `is_subscribe_mp` tinyint(1) DEFAULT '0' COMMENT '是否关注公众号',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信登录用户表';