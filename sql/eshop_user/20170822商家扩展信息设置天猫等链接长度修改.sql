alter table `merchant_profile`  modify  column  `website_url` varchar(500) DEFAULT NULL COMMENT '官网链接';
alter table `merchant_profile`  modify  column  `taobao_url` varchar(500) DEFAULT NULL COMMENT '淘宝链接';
alter table `merchant_profile`  modify  column  `tmall_url` varchar(500) DEFAULT NULL COMMENT '天猫链接';
alter table `merchant_profile`  modify  column  `jd_url` varchar(500) DEFAULT NULL COMMENT '京东链接';