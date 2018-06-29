-- 广告收藏加入会员编号字段
alter table favorite_ad add member_num varchar(19) not Null COMMENT '会员编号' after member_id;

-- 广告收藏加入是否发送消息字段
alter table favorite_ad add is_send tinyint(1) DEFAULT '0' NOT NULL COMMENT '是否发送消息' after member_num;