ALTER TABLE `eshop_user`.`member`
ADD COLUMN `frozen_type` tinyint(2) COMMENT '冻结类型(1-高频访问|2-机器注册下线|3-后台冻结)' AFTER `is_freeze`;
ALTER TABLE `eshop_user`.`merchant`
ADD COLUMN `frozen_type` tinyint(2) COMMENT '冻结类型(1-高频访问|2-机器注册下线|3-后台冻结)' AFTER `is_freeze`;
ALTER TABLE `eshop_user`.`member`
ADD COLUMN `is_zombie` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是僵尸账号(0-否|1-是)' AFTER `is_freeze`;
ALTER TABLE `eshop_user`.`merchant`
ADD COLUMN `is_zombie` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是僵尸账号(0-否|1-是)' AFTER `is_freeze`;