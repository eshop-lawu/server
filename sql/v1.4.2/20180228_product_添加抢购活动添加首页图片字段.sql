ALTER TABLE `eshop_product`.`seckill_activity`
ADD COLUMN `home_picture` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '首页图片' AFTER `picture`;