alter table eshop_user.member_profile add column  `reg_ip` varchar(50) default null COMMENT '注册来源IP' after invite_merchant_count3;
alter table eshop_user.member_profile add column  `reg_platform_ver` varchar(50) default null COMMENT '注册系统版本' after reg_ip;
alter table eshop_user.member_profile add column  `reg_app_ver` varchar(50) default null COMMENT '注册app版本' after reg_platform_ver;
alter table eshop_user.member_profile add column  `gmt_last_login` datetime DEFAULT NULL COMMENT '最后登录时间' after reg_app_ver;

alter table eshop_user.merchant_profile add column  `reg_ip` varchar(50) default null COMMENT '注册来源IP' after jd_url;
alter table eshop_user.merchant_profile add column  `reg_platform_ver` varchar(50) default null COMMENT '注册系统版本' after reg_ip;
alter table eshop_user.merchant_profile add column  `reg_app_ver` varchar(50) default null COMMENT '注册app版本' after reg_platform_ver;
alter table eshop_user.merchant_profile add column  `gmt_last_login` datetime DEFAULT NULL COMMENT '最后登录时间' after reg_app_ver;