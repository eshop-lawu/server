-- 快递公司表字段长度变化。同步更改
ALTER TABLE `shopping_refund_detail`
MODIFY COLUMN `express_company_code`  varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '快递公司编码' AFTER `express_company_id`,
MODIFY COLUMN `express_company_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '快递公司名称' AFTER `express_company_code`;