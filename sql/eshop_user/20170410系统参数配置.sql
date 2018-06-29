CREATE TABLE `property` (
	`id` BIGINT(20) UNSIGNED NOT NULL,
	`name` VARCHAR(50) NOT NULL COMMENT '键',
	`value` VARCHAR(50) NOT NULL COMMENT '值',
	`remark` TEXT NULL,
	`gmt_modified` DATETIME NULL DEFAULT NULL,
	`gmt_create` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='系统参数配置表'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;