CREATE TABLE `discount_package_purchase_notes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `note` varchar(255) NOT NULL COMMENT '购买须知选项',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠套餐购买须知表';

