alter table transaction_detail add column `province_id` int(8) unsigned DEFAULT NULL COMMENT '省ID' AFTER `biz_num`;
alter table transaction_detail add column `city_id` int(8) unsigned DEFAULT NULL COMMENT '市ID' AFTER `province_id`;
alter table transaction_detail add column `area_id` int(8) unsigned DEFAULT NULL COMMENT '区ID' AFTER `city_id`;
alter table point_detail add column `province_id` int(8) unsigned DEFAULT NULL COMMENT '省ID' AFTER `biz_id`;
alter table point_detail add column `city_id` int(8) unsigned DEFAULT NULL COMMENT '市ID' AFTER `province_id`;
alter table point_detail add column `area_id` int(8) unsigned DEFAULT NULL COMMENT '区ID' AFTER `city_id`;