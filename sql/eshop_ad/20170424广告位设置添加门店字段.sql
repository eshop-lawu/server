alter table ad_platform add column  `merchant_store_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '门店id';

alter table ad_platform modify column `type` tinyint(2)  COMMENT '广告类型(1-纯链接2-商品 3-门店)';


alter table member_ad_record add member_num varchar(19) not Null COMMENT '会员编号' after member_id;
alter table member_ad_record add point decimal(10,2)  COMMENT '获得积分' after ad_id;
alter table member_ad_record add `status` tinyint(2)  DEFAULT '0' COMMENT '状态(0-没有算提成1-已算提成)';
