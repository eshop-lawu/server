CREATE TABLE `app_version` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`app_version` VARCHAR(16) NOT NULL COMMENT '版本号',
	`app_big_version` VARCHAR(8) NOT NULL COMMENT '大版本号',
	`update_desc` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '更新内容',
	`app_type` TINYINT(2) NOT NULL COMMENT 'APP类型，1：会员端，2：商家端',
	`status` TINYINT(2) NOT NULL COMMENT '1:启用，2：禁用',
	`is_force` TINYINT(1) NOT NULL COMMENT '是否强制更新',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='站内消息'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;