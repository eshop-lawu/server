-- 增加文字描述字段长度
ALTER TABLE `discount_package_image` MODIFY COLUMN `description`  varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文字描述' AFTER `discount_package_id`;