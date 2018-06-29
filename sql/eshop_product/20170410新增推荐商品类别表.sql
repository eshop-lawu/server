CREATE TABLE `recommend_product_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` int(5) unsigned NOT NULL COMMENT '分类ID',
  `category_name` varchar(20) NOT NULL COMMENT '名称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='推荐商品类别';