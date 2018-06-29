/*2017414 初始建表*/
CREATE TABLE `product_model_inventory` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_model_id` bigint(20) unsigned NOT NULL COMMENT '商品型号id',
  `quantity` int(11) unsigned NOT NULL COMMENT '数量',
  `shopping_order_id` bigint(20) unsigned DEFAULT NULL COMMENT '购物订单id(在创建订单和取消订单会保存)',
  `type` tinyint(2) unsigned NOT NULL COMMENT '更新类型(0-加库存|1-减库存|2-创建订单|3-取消订单)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品型号库存';