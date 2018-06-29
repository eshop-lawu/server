alter table transaction_detail add column `previous_amount` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '操作前余额' AFTER `amount`;
alter table point_detail add column `previous_point` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '操作前积分' AFTER `point`;
alter table love_detail add column `previous_amount` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '操作前爱心账户' AFTER `amount`;
alter table freeze add column `previous_money` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '操作前冻结资金' after `money`;
alter table freeze add column `fund_biz_type` tinyint(2) NOT NULL COMMENT '业务类型(10-(订单)用户确认收货初始化冻结资金|11-(订单)商家同意退款减冻结资金)' after `fund_type`;