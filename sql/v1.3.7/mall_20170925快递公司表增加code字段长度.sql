-- 增加code长度以适应快递100的快递公司编码
ALTER TABLE `express_company` MODIFY COLUMN `code`  varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递鸟快递公司编号' AFTER `id`;

-- 增加name长度以适应快递100的快递公司名称
ALTER TABLE `express_company` MODIFY COLUMN `name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称' AFTER `code`;