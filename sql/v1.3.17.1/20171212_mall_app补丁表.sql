use eshop_mall;
CREATE TABLE `app_patch_version` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_version_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'app_version_id',
  `app_version` varchar(16) NOT NULL COMMENT '版本号',
  `patch_version` INT(5) UNSIGNED NOT NULL DEFAULT '0' COMMENT '补丁版本号',
  `update_desc` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '更新内容',
  `status` TINYINT(2) UNSIGNED NOT NULL DEFAULT '1' COMMENT '1--未启用，2--启用',
  `gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
  `gmt_create` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
COMMENT='APP补丁版本'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;