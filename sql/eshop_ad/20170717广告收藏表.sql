-- 添加唯一索引
alter table `favorite_ad` add UNIQUE KEY `uk_ad_id_member_id` (`ad_id`,`member_id`);