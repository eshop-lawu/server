-- 添加下架时间,用于购物车列表显示
ALTER TABLE `product` ADD COLUMN `gmt_down` datetime NULL DEFAULT NULL COMMENT '下架时间' AFTER `max_price`;