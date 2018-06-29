alter table recharge add column `province_id` int(8) unsigned DEFAULT NULL COMMENT '省ID' AFTER `third_number`;
alter table recharge add column `city_id` int(8) unsigned DEFAULT NULL COMMENT '市ID' AFTER `province_id`;
alter table recharge add column `area_id` int(8) unsigned DEFAULT NULL COMMENT '区ID' AFTER `city_id`;