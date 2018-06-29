alter table ad add merchant_num varchar(19) not Null COMMENT '商家编号' after merchant_id;
alter table point_pool add member_num varchar(19) not Null COMMENT '会员编号' after member_id;

alter table red_packet add merchant_num varchar(19) not Null COMMENT '商家编号' after merchant_id;

alter table ad_platform modify column  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键';
alter table favorite_ad modify column  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键';
alter table red_packet modify column  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键';

alter table point_pool modify column  `member_id`  bigint(20) unsigned  COMMENT '会员ID';
alter table point_pool modify column  `member_num`  varchar(19)   COMMENT '会员编号';

alter table ad_platform modify column `position` tinyint(2)  COMMENT '广告位置(1-人气推荐2-要购物顶部广告 3-要购物今日推荐4-要购物精品5-看广告顶部广告)';

alter table ad_platform add `content` varchar(500)  COMMENT '广告内容';