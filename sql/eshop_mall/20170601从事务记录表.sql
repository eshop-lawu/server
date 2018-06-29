CREATE TABLE `follow_transaction_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `transation_id` bigint(20) unsigned NOT NULL COMMENT '事务记录id',
  `topic` varchar(10) NOT NULL COMMENT 'MQ消息的topic',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_transation_id_topic` (`transation_id`,`topic`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='从事务记录表';