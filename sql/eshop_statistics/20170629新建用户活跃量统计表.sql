CREATE TABLE `report_user_active_daily` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '活跃会员数',
  `merchant_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '活跃商家数',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户活跃统计报表(按天)';

CREATE TABLE `report_user_active_month` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '活跃会员数',
  `merchant_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '活跃商家数',
  `gmt_report` date NOT NULL COMMENT '统计数据所属年月',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户活跃统计报表(按月)';


CREATE TABLE `report_user_active_area_daily` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '活跃会员数',
  `merchant_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '活跃商家数',
  `city_id` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '市级区域ID',
  `city_name` varchar(50) NOT NULL DEFAULT '' COMMENT '市级名称',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户活跃统计报表(地区按天)';


CREATE TABLE `report_user_active_area_month` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '活跃会员数',
  `merchant_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '活跃商家数',
  `city_id` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '市级区域ID',
  `city_name` varchar(50) NOT NULL DEFAULT '' COMMENT '市级名称',
  `gmt_report` date NOT NULL COMMENT '统计数据所属年月',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户活跃统计报表(地区按月)';