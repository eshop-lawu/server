/**
 * 20170418
 * 在订单表中添加发送提醒的次数，用于记录在超时操作过程中发送的提醒次数
 */
ALTER TABLE shopping_order ADD COLUMN `send_time` int(11) NOT NULL unsigned DEFAULT 0 COMMENT '发送提醒的次数' AFTER `status`;