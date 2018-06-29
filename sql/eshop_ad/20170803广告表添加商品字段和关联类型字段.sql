alter table ad add relate_id bigint(20)  COMMENT '关联id' after merchant_store_id;

alter table ad add relate_type tinyint(2) DEFAULT 0  COMMENT '关联类型 0没有关联 1店铺 2 商品' after relate_id;