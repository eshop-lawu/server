use eshop_activity;
CREATE TABLE `draw_lottery_activity_month_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `free_times` int(5) unsigned DEFAULT '0' COMMENT '免费抽奖次数',
  `point_times` int(5) unsigned DEFAULT '0' COMMENT '积分抽奖次数',
  `record_date` varchar(6) DEFAULT NULL COMMENT '记录时间，年月(如201801)',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户每月抽奖次数统计';