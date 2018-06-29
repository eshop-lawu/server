-- 修改注释
ALTER TABLE `ad` MODIFY COLUMN `type`  tinyint(2) NOT NULL COMMENT '广告类型(1-平面广告|2-视频广告|3-E赞|4-红包)' AFTER `content`;
ALTER TABLE `ad` MODIFY COLUMN `status`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '状态(0-删除|1-上架|2-投放中|3-投放结束|4-下架|5-审核中|审核不通过)' AFTER `viewCount`;

-- 新增冗余字段
alter table ad add column `merchant_store_id` bigint(20) unsigned NOT NULL COMMENT '商家门店id' after `merchant_num`;
alter table ad add column `merchant_store_name` varchar(100) NOT NULL COMMENT '店铺名称' after `merchant_num`;
alter table ad add column `manage_type` tinyint(2) unsigned NOT NULL COMMENT '经营类型(1-普通商户2-实体店铺)' after `merchant_num`;
alter table ad add column `logo_url` varchar(255) NOT NULL COMMENT '门店logo' after `media_url`;

-- 新增查询索引(每种投放方式对应一种索引)
-- 查询积分排行榜
ALTER TABLE `ad` ADD KEY `idx_type_begin_status_put_way_longitude_latitude_radius_point` (`type`,`begin_time`,`status`,`put_way`,`merchant_longitude`,`merchant_latitude`,`radius`,`point`,`total_point`) USING BTREE;
ALTER TABLE `ad` ADD KEY `idx_type_begin_time_status_put_way_merchant_id_point_total_point` (`type`,`begin_time`,`status`,`put_way`,`merchant_id`,`point`,`total_point`) USING BTREE;
ALTER TABLE `ad` ADD KEY `idx_type_begin_time_status_put_way_areas_point_total_point` (`type`,`begin_time`,`status`,`put_way`,`areas`,`point`,`total_point`) USING BTREE;
-- 查询E赚广告列表以及精选推荐广告列表
ALTER TABLE `ad` ADD KEY `idx_status_type_put_way_merchant_id_hits_gmt_create` (`type`,`status`,`put_way`,`merchant_id`,`hits`,`gmt_create`) USING BTREE;
ALTER TABLE `ad` ADD KEY `idx_status_type_put_way_areas_hits_gmt_create` (`type`,`status`,`put_way`,`areas`,`hits`,`gmt_create`) USING BTREE;
ALTER TABLE `ad` ADD KEY `idx_status_type_put_way_longitude_latitude_radius_hits_create` (`type`,`status`,`put_way`,`merchant_longitude`,`merchant_latitude`,`radius`,`hits`,`gmt_create`) USING BTREE;