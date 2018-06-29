-- 添加门店id用于跳转到商家首页
ALTER TABLE `shopping_cart` ADD COLUMN `merchant_store_id`  bigint(20) NOT NULL AFTER `merchant_id`;

-- 添加唯一索引用于查询
ALTER TABLE `shopping_cart` ADD UNIQUE INDEX `uk_member_id_product_model_id` (`member_id`, `product_model_id`) USING BTREE ;

