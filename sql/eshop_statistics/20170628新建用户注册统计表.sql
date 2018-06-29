CREATE TABLE `report_user_reg_daily` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册会员数',
  `merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册商家数',
  `gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` DATETIME NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
)
COMMENT='用户注册统计报表(按天)'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `report_user_reg_month` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册会员数',
  `merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册商家数',
  `gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` DATETIME NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
)
COMMENT='用户注册统计报表(按月)'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `report_user_reg_area` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '会员总数',
  `merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '商家总数',
  `merchant_common_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '普通商家数',
  `merchant_entity_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '实体商家数',
  `city_id` INT(8) UNSIGNED NOT NULL COMMENT '市级区域ID',
  `city_name` VARCHAR(50) NOT NULL COMMENT '市级名称',
  `gmt_update` DATETIME NOT NULL COMMENT '更新时间',
  `gmt_create` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
COMMENT='用户注册区域统计报表'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;