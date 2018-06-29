CREATE TABLE `report_area_volume_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`report_total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '销售总额',
	`type` TINYINT(3) NOT NULL COMMENT '消费类型: 1--买单, 2--购物',
	`province_id` INT(8) NULL DEFAULT NULL COMMENT '省编号',
	`city_id` INT(8) NULL DEFAULT NULL COMMENT '市编号',
	`area_id` INT(8) NULL DEFAULT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
COMMENT='区域总消费量(按天)'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `report_area_volume_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`report_total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '销售总额',
	`type` TINYINT(3) NOT NULL COMMENT '消费类型: 1--买单, 2--购物',
	`province_id` INT(8) NULL DEFAULT NULL COMMENT '省编号',
	`city_id` INT(8) NULL DEFAULT NULL COMMENT '市编号',
	`area_id` INT(8) NULL DEFAULT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
COMMENT='区域总消费量(按月)'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
