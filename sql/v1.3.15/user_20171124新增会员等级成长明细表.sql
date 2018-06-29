use eshop_user;
CREATE TABLE `user_grade_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT NULL COMMENT '会员编号',
  `transaction_detail_id` bigint(20) unsigned NOT NULL COMMENT '资产模块交易明细主键',
  `transaction_detail_type` tinyint(3) unsigned NOT NULL COMMENT '资产模块交易明细表交易类型',
  `biz_id` varchar(128) DEFAULT '' COMMENT '参与成长值业务记录表主键',
  `growth_value` int(11) NOT NULL COMMENT '成长值记录',
  `grade_before` tinyint(2) DEFAULT '1' COMMENT '操作前会员等级',
  `grade_after` tinyint(2) DEFAULT '1' COMMENT '操作后会员等级',
  `growth_value_before` int(11) unsigned NOT NULL COMMENT '操作前成长值',
  `growth_value_after` int(11) unsigned NOT NULL COMMENT '操作后成长值',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员等级成长明细';