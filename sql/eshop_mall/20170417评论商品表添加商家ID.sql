alter table comment_product add merchant_id bigint(20) NOT NULL  COMMENT '商家ID'  after member_id;

alter table comment_product add product_model_id bigint(20) NOT NULL  COMMENT '商品型号id'  after order_item_id;