/**
 * 20170418
 * 订单项表添加发送次数。用于记录发送提醒的次数
 */
ALTER TABLE shopping_order_item ADD COLUMN `send_time` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '发送提醒的次数' AFTER `refund_status`;