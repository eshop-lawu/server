use eshop_product;
-- ================品牌
CREATE TABLE `product_brand` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_category_id` int(5) unsigned NOT NULL COMMENT '商品分类ID',
  `brand_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '品牌名称',
  `ordinal` tinyint(3) unsigned NOT NULL DEFAULT '50' COMMENT '顺序排序',
  `status` tinyint(1) NOT NULL COMMENT '状态(0-删除1-有效)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品品牌系统配置表';



-- ================规格
CREATE TABLE `product_spec` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_category_id` int(5) unsigned NOT NULL COMMENT '商品分类ID',
  `spec_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '规格名称',
  `ordinal` tinyint(3) unsigned NOT NULL DEFAULT '50' COMMENT '顺序排序',
  `status` tinyint(1) NOT NULL COMMENT '状态(0-删除1-有效)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品规格系统配置表';


-- ================自定义类型/规格项表
CREATE TABLE `product_custom_spec` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `relate_id` bigint(20) unsigned NOT NULL COMMENT '关联ID：类型/品牌-商品ID，规格项-商品型号ID',
  `relate_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关联名称，类型-上下级名称，品牌-品牌名称，规格项-上下级类型+规格名称',
  `spec_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '自定义规格名称',
  `status` tinyint(2) NOT NULL COMMENT '状态(0-删除1-未处理 2-已处理 3-不处理)',
  `custom_type` tinyint(2) NOT NULL COMMENT '类型（1-类目|2-品牌|3-规格选项）',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品自定义类型/规格项表';



-- ================规格选项
CREATE TABLE `product_spec_option` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_spec_id` bigint(20) unsigned NOT NULL COMMENT 'product_spec_system表ID',
  `option_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '明细项名称',
  `ordinal` tinyint(3) unsigned NOT NULL DEFAULT '50' COMMENT '顺序排序',
  `icon_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '明细项icon图片路径',
  `status` tinyint(1) NOT NULL COMMENT '状态(0-删除1-有效)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品规格选项系统配置表';