CREATE TABLE `discount_package_content` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `discount_package_id` bigint(20) unsigned NOT NULL COMMENT '优惠套餐id',
  `name` varchar(20) NOT NULL COMMENT '内容名称',
  `unit_price` decimal(5,2) unsigned NOT NULL COMMENT '单价',
  `quantity` int(11) unsigned NOT NULL COMMENT '数量',
  `unit` varchar(5) NOT NULL COMMENT '单位',
  `subtotal` decimal(5,2) unsigned NOT NULL COMMENT '小计',
  `status` tinyint(2) unsigned NOT NULL COMMENT '状态(0-删除|1-正常)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_discount_package_id_status` (`discount_package_id`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='优惠套餐内容表';