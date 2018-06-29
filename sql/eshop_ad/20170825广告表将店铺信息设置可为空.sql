alter table `ad`  modify  column  `merchant_store_id` bigint(20) DEFAULT NULL COMMENT '店铺id';
alter table `ad`  modify  column  `merchant_store_name` varchar(100) DEFAULT NULL COMMENT '店铺名称';
alter table `ad`  modify  column  `manage_type` tinyint(2) DEFAULT NULL COMMENT '店铺类型';
alter table `ad`  modify  column  `logo_url` varchar(255) DEFAULT NULL  COMMENT '店铺logo';