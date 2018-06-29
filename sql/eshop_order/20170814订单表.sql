-- 添加门店区域冗余字段，用于分区域保存交易明细记录，统计代理商报表
ALTER TABLE `shopping_order` ADD COLUMN `merchant_store_region_path`  varchar(25) NOT NULL COMMENT '商家门店区域（省市区id）' AFTER `merchant_store_id`;