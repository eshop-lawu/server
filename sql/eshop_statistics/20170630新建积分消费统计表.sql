CREATE TABLE `report_point_consume_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_point` decimal(20,6) unsigned NOT NULL COMMENT '会员积分消费',
  `merchant_point` decimal(20,6) unsigned NOT NULL COMMENT '商家积分消费',
  `total_point` decimal(20,6) unsigned NOT NULL COMMENT '总积分消费',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='积分消费统计(按天)';

CREATE TABLE `report_point_consume_month` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_point` decimal(20,6) unsigned NOT NULL COMMENT '会员积分消费',
  `merchant_point` decimal(20,6) unsigned NOT NULL COMMENT '商家积分消费',
  `total_point` decimal(20,6) unsigned NOT NULL COMMENT '总积分消费',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='积分消费统计(按月)';