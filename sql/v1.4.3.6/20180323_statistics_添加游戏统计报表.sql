use eshop_statistics;
CREATE TABLE `report_game_point_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `game_type` tinyint(2) NOT NULL COMMENT '游戏类型 1-头脑pk 2拼图 3-转盘',
  `stand_alone_expend_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '单机支出收益',
  `stand_alone_income_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '单机收入收益',
  `random_expend_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '随机支出收益',
  `random_income_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '随机收入收益',
  `many_people_expend_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '多人支出收益',
  `many_people_income_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '多人收入收益',
  `total_expend_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '总收入',
  `total_income_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '总支出',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='游戏收益统计(按天)';