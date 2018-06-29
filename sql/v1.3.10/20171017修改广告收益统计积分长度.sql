
alter table `eshop_statistics`.`report_earnings_daily` modify  column  `ad_point` decimal(20,6) not null COMMENT '广告积分';
alter table `eshop_statistics`.`report_earnings_daily` modify  column  `user_point` decimal(20,6) not null COMMENT '用户获取积分';
alter table `eshop_statistics`.`report_earnings_daily` modify  column  `love_point` decimal(20,6) not null COMMENT '爱心账户积分';
alter table `eshop_statistics`.`report_earnings_daily` modify  column  `platform_point` decimal(20,6) not null COMMENT '平台收益积分';


alter table `eshop_statistics`.`report_earnings_month` modify  column  `ad_point` decimal(20,6) not null COMMENT '广告积分';
alter table `eshop_statistics`.`report_earnings_month` modify  column  `user_point` decimal(20,6) not null COMMENT '用户获取积分';
alter table `eshop_statistics`.`report_earnings_month` modify  column  `love_point` decimal(20,6) not null COMMENT '爱心账户积分';
alter table `eshop_statistics`.`report_earnings_month` modify  column  `platform_point` decimal(20,6) not null COMMENT '平台收益积分';