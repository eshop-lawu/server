ALTER TABLE member ADD COLUMN `region_name` varchar(100) DEFAULT NULL COMMENT '区域名称' AFTER `region_path`;

alter table member modify column `ry_token` varchar(200) DEFAULT NULL COMMENT '融云token';



