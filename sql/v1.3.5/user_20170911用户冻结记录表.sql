CREATE TABLE `user_freeze_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `account` varchar(20) NOT NULL COMMENT '用户账号',
  `user_type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户类型，1是会员，2是商家',
  `cause` varchar(100) NOT NULL COMMENT '冻结原因',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='用户冻结记录';