use eshop_product;

alter table product add column `total_favorite` int(10) NOT NULL COMMENT '总收藏' after `total_sales_volume`;

