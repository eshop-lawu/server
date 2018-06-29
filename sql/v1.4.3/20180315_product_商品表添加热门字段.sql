ALTER TABLE eshop_product.recommend_product_category
ADD COLUMN `is_hot`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否热门' AFTER `ordinal`;