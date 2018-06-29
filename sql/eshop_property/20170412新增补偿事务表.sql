CREATE TABLE `transaction_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `relate_id` bigint(20) unsigned NOT NULL COMMENT '关联ID',
  `type` tinyint(3) unsigned NOT NULL COMMENT '事务类型',
  `is_processed` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '已处理，0否，1是',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='补偿性事务记录表';