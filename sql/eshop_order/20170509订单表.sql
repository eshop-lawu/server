-- 用于app从订单列表跳转到门店详情页面
ALTER TABLE `shopping_order` ADD COLUMN `merchant_store_id` bigint(20) unsigned NOT NULL COMMENT '门店id' AFTER `merchant_id`;
-- 如果用户是立即购买不需要保存到购物车。购物车id可以为空
ALTER TABLE `shopping_order` MODIFY COLUMN `shopping_cart_ids_str`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '对应的购物车相应的id(多个id用,分隔)' AFTER `is_automatic_receipt`; 