CREATE TABLE `report_area_user_reg_daily` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '会员总数',
  `merchant_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商家总数',
  `merchant_normal_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '普通商家数',
  `merchant_entity_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '实体商家数',
  `city_id` int(8) unsigned NOT NULL COMMENT '市级区域ID',
  `city_name` varchar(50) NOT NULL COMMENT '市级名称',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='代理商注册区域日统计报表';

CREATE TABLE `report_area_user_reg_month` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '会员总数',
  `merchant_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商家总数',
  `merchant_normal_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '普通商家数',
  `merchant_entity_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '实体商家数',
  `city_id` int(8) unsigned NOT NULL COMMENT '市级区域ID',
  `city_name` varchar(50) NOT NULL COMMENT '市级名称',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='代理商注册区域月统计报表';