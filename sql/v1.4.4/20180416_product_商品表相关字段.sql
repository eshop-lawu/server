-- ================修改现有商品表
alter table `eshop_product`.`product` add column category_name varchar(200) DEFAULT NULL COMMENT '商品分类名称' after category_id;
alter table `eshop_product`.`product` add column category_subitem_id bigint(20) DEFAULT NULL COMMENT '子类目ID' after `category_name`;
alter table `eshop_product`.`product` add column category_subitem_name varchar(200) DEFAULT NULL COMMENT '商品子类目名称' after category_subitem_id;
alter table `eshop_product`.`product` add column brand_id bigint(20) DEFAULT NULL COMMENT '品牌' after `category_subitem_name`;
alter table `eshop_product`.`product` add column brand_name varchar(200) DEFAULT NULL COMMENT '品牌名称' after brand_id;
alter table `eshop_product`.`product` add column spec varchar(6500) NOT NULL COMMENT '商品规格json([{"list":[{"name":"L","id":1,"icon":""},{"name":"M","id":2,"icon":""}],"spec":{"name":"大小","id":1}},{"list":[{"name":"白色","id":3,"icon":""},{"name":"红色","id":4,"icon":""}],"spec":{"name":"颜色","id":2}}])' after brand_name;
alter table `eshop_product`.`product` add column province_id bigint(20) DEFAULT NULL COMMENT '发货地，省' after `max_price`;
alter table `eshop_product`.`product` add column province_name varchar(200) DEFAULT NULL COMMENT '发货地，省名称' after province_id;
alter table `eshop_product`.`product` add column city_id bigint(20) DEFAULT NULL COMMENT '发货地，市' after `province_name`;
alter table `eshop_product`.`product` add column city_name varchar(200) DEFAULT NULL COMMENT '发货地，市名称' after city_id;
alter table `eshop_product`.`product` add column is_express_free tinyint(1) DEFAULT 1 COMMENT '是否包邮（0-不包邮|1-包邮）' after `city_name`;
alter table `eshop_product`.`product` add column freight varchar(500) DEFAULT NULL COMMENT '运费信息json({"defaultPieceNumber":"默认运费，多少件","defaultPieceMoney":"默认运费，多少件内多少钱","addPieceNumber":"每加多少件","addPieceMoney":"每加多少件，加多少钱"})' after is_express_free;
alter table `eshop_product`.`product` add column position tinyint(2) DEFAULT 0 COMMENT '位置（1-橱窗）' after `status`;
alter table `eshop_product`.`product` add column gmt_up datetime DEFAULT NULL COMMENT '上架时间' after `remark`;

-- ================修改现有商品型号表
alter table `eshop_product`.`product_model` add column spec_option_1 bigint(20) DEFAULT 0 COMMENT '规格选项1ID' after `status`;
alter table `eshop_product`.`product_model` add column spec_option_2 bigint(20) DEFAULT 0 COMMENT '规格选项2ID' after `spec_option_1`;
alter table `eshop_product`.`product_model` add column spec_option_3 bigint(20) DEFAULT 0 COMMENT '规格选项3ID' after `spec_option_2`;
alter table `eshop_product`.`product_model` add column spec_option_4 bigint(20) DEFAULT 0 COMMENT '规格选项4ID' after `spec_option_3`;
alter table `eshop_product`.`product_model` add column spec_option_5 bigint(20) DEFAULT 0 COMMENT '规格选项5ID' after `spec_option_4`;