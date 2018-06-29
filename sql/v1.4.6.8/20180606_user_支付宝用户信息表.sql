use eshop_user;
CREATE TABLE `ali_user_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(2) DEFAULT NULL COMMENT '用户类型',
  `ali_user_id` varchar(20) DEFAULT NULL COMMENT '支付宝用户号',
  `ali_user_info` text DEFAULT NULL COMMENT '支付宝用户基本信息(json)',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;