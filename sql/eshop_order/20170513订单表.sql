-- 用于定时任务查询是否可以打款给商家
ALTER TABLE `shopping_order` ADD COLUMN `is_done` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '当前订单是否完成(0-未完成|1-已完成)' AFTER `is_automatic_receipt`;