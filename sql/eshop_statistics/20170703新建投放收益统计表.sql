CREATE TABLE `report_earnings_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ad_point` decimal(10,6) unsigned NOT NULL DEFAULT '0' COMMENT '广告总投放积分',
  `user_point` decimal(10,6) unsigned NOT NULL DEFAULT '0' COMMENT '用户总收益',
  `love_point` decimal(10,6) unsigned NOT NULL DEFAULT '0' COMMENT '爱心账户总收益',
  `platform_point` decimal(10,6) unsigned NOT NULL DEFAULT '0' COMMENT '平台总收益',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='投放收益统计(按天)';

CREATE TABLE `report_earnings_month` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ad_point` decimal(10,6) unsigned NOT NULL DEFAULT '0' COMMENT '广告总投放积分',
  `user_point` decimal(10,6) unsigned NOT NULL DEFAULT '0' COMMENT '用户总收益',
  `love_point` decimal(10,6) unsigned NOT NULL DEFAULT '0' COMMENT '爱心账户总收益',
  `platform_point` decimal(10,6) unsigned NOT NULL DEFAULT '0' COMMENT '平台总收益',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='投放收益统计(按月)';