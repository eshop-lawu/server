alter table ad add column  `is_pay` tinyint(1)  COMMENT '是否支付' after status;

update ad set is_pay=1