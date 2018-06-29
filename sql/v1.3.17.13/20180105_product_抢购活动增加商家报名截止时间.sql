use eshop_product;
alter table seckill_activity add column  `attent_end_date` datetime NULL COMMENT '报名结束时间' after end_date;