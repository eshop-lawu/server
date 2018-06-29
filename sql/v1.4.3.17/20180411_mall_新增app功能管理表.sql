use eshop_mall;
CREATE TABLE `app_function_manage` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `is_enable_game` tinyint(1) DEFAULT '1'  COMMENT '是否启用游戏',
  `is_enable_love` tinyint(1) DEFAULT '1'  COMMENT '是否启用爱心账户',
  `app_version` varchar(50) NOT NULL  COMMENT 'APP版本号',
  `is_enable` tinyint(1) DEFAULT '0'  COMMENT '是否启用管理',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='app功能管理';