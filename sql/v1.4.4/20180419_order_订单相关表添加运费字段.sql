ALTER TABLE `eshop_order`.`shopping_order_item` 
ADD COLUMN `subtotal` decimal(10, 2) UNSIGNED NOT NULL COMMENT '小计' AFTER `deduction_points_amount`,
ADD COLUMN `freight` varchar(501) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '运费信息json({\"defaultPieceNumber\":\"默认运费，多少件\",\"defaultPieceMoney\":\"默认运费，多少件内多少钱\",\"addPieceNumber\":\"每加多少件\",\"addPieceMoney\":\"每加多少件，加多少钱\"})' AFTER `quantity`;

UPDATE `eshop_order`.`shopping_order_item` SET subtotal = sales_price * quantity - deduction_points_amount
WHERE sales_price * quantity >= deduction_points_amount;

ALTER TABLE `eshop_order`.`shopping_order`
ADD COLUMN `freight_price_deduction_points` decimal(10, 2) UNSIGNED DEFAULT 0 COMMENT '运费抵扣积分' AFTER `freight_price`,
ADD COLUMN `exchange_rate` decimal(10, 2) UNSIGNED COMMENT '金额兑积分比率' AFTER `deduction_points_amount`;

UPDATE `eshop_order`.`shopping_order` SET exchange_rate = 100;

ALTER TABLE `eshop_order`.`shopping_refund_detail`
ADD COLUMN `freight_price` decimal(10, 2) UNSIGNED COMMENT '退款运费' AFTER `actual_amount`,
ADD COLUMN `freight_price_deduction_points` decimal(10, 2) UNSIGNED DEFAULT 0 COMMENT '运费抵扣积分' AFTER `freight_price`,
ADD COLUMN `order_status` tinyint(2) UNSIGNED COMMENT '订单退款前的状态(0-待处理|1-待付款|2-待发货|3-待收货|4-交易成功|5-交易关闭|6-退款中)' AFTER `waybill_num`;

-- 更新订单状态到退款中的退款详情记录中
USE eshop_order;
UPDATE shopping_refund_detail srd, shopping_order_item soi, shopping_order so  SET srd.order_status = so.order_status
WHERE srd.shopping_order_item_id = soi.id AND soi.order_status = 6
AND soi.shopping_order_id = so.id AND so.order_status != 5