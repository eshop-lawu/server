CREATE TABLE `report_area_point_consume_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员充值积分',
	`merchant_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家充值积分',
	`total_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总充值积分',
	`member_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员积分消费',
	`merchant_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家积分消费',
	`total_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总积分消费',
	`province_id` INT NOT NULL COMMENT '省编号',
	`city_id` INT NOT NULL COMMENT '市编号',
	`area_id` INT NOT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
COMMENT='区域积分消费统计(按天)'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `report_area_point_consume_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员充值积分',
	`merchant_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家充值积分',
	`total_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总充值积分',
	`member_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员积分消费',
	`merchant_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家积分消费',
	`total_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总积分消费',
	`province_id` INT NOT NULL COMMENT '省编号',
	`city_id` INT NOT NULL COMMENT '市编号',
	`area_id` INT NOT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
COMMENT='区域积分消费统计(按月)'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
