alter table `eshop_user`.`weixin_user` add column `is_subscribe_mp` tinyint(1) DEFAULT '0' COMMENT '是否关注公众号' after tagid_list;
update `eshop_user`.`weixin_user` set `is_subscribe_mp`=1;