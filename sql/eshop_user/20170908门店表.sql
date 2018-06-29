-- 修改门店的行业分类path的长度，与industry_type表中的industry_path长度保持一致
ALTER TABLE `merchant_store` MODIFY COLUMN `industry_path`  varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '主营业务' AFTER `latitude`;

