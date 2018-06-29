CREATE TABLE `log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(50) NOT NULL COMMENT '操作用户账号',
  `operation_type` tinyint(2) unsigned NOT NULL COMMENT '操作类型',
  `module` tinyint(2) unsigned NOT NULL COMMENT '模块',
  `business_id` varchar(30) NOT NULL COMMENT '业务id',
  `change_title` varchar(50) NOT NULL COMMENT '变更标题',
  `change_content` varchar(500) DEFAULT NULL COMMENT '变更内容(JSON数据)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;