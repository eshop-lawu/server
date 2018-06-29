-- 用于判断订单下是否有退款项
ALTER TABLE `shopping_order` ADD COLUMN `is_refund_items`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否有退款项(0-没有|1-有)' AFTER `is_done`;

-- 根据订单项表的订单状态更新订单表
UPDATE shopping_order SET is_refund_items=1 WHERE id in (
SELECT shopping_order_id FROM shopping_order_item WHERE order_status=6
)