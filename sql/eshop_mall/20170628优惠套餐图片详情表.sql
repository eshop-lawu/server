CREATE TABLE `discount_package_image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `discount_package_id` bigint(20) unsigned NOT NULL COMMENT '优惠套餐id',
  `description` varchar(100) NOT NULL COMMENT '文字描述',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `status` tinyint(2) unsigned NOT NULL COMMENT '状态(0-删除|1-正常)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_discount_package_id_status` (`discount_package_id`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;