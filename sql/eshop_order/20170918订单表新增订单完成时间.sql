-- 新增订单完成时间，用来标识释放冻结资金的时间，用于商家经营效果的收入
ALTER TABLE `shopping_order` ADD COLUMN `gmt_done`  datetime NULL COMMENT '订单完成时间' AFTER `gmt_transaction`;