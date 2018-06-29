use eshop_product;

alter table product add column `total_inventory` int(10) NOT NULL COMMENT '商品总库存' after `status`;
alter table product add column `total_sales_volume` int(10) NOT NULL COMMENT '商品总销量' after `total_inventory`;
alter table product add column `min_price` decimal(10,2) NOT NULL COMMENT '型号最低价' after `total_sales_volume`;
alter table product add column `max_price` decimal(10,2) NOT NULL COMMENT '型号最高价' after `min_price`;
alter table product add column `image_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '商品详情图片描述' after `status`;


alter table product_image add column `sortid` int(3) NOT NULL COMMENT '顺序' after `content`;
