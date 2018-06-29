use eshop_user;
CREATE TABLE `user_grade` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grade_name` varchar(50) DEFAULT NULL COMMENT '等级名称',
  `grade_value` tinyint(2) unsigned NOT NULL COMMENT '等级值',
  `grade_weight` int(5) unsigned NOT NULL COMMENT '等级权值',
  `min_growth_value` int(11) NOT NULL COMMENT '最小成长值',
  `lottery_activity_point` int(5) NOT NULL COMMENT '抽奖活动兑换积分',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员等级表';