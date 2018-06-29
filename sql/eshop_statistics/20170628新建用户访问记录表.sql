CREATE TABLE `user_visit_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `account` varchar(20) NOT NULL COMMENT '用户账号',
  `user_type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户类型，1是会员，2是商家',
  `visit_count` int(8) unsigned DEFAULT NULL COMMENT '访问次数',
  `city_id` int(8) unsigned DEFAULT '0' COMMENT '市级区域ID',
  `city_name` varchar(50) DEFAULT '' COMMENT '市级名称',
  `visit_date` date NOT NULL COMMENT '访问日期',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='用户访问记录';