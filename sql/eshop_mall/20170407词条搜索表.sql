CREATE TABLE `search_word` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`word` VARCHAR(20) NOT NULL COMMENT '搜索词条',
	`type` TINYINT(3) UNSIGNED NOT NULL COMMENT '词条类型：1--门店，2--商品',
	`gmt_create` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='词条搜索'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;