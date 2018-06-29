ALTER TABLE eshop_product.recommend_product_category
ADD COLUMN `image_path`  varchar(200) DEFAULT NULL COMMENT '分类图标' AFTER `category_name`;