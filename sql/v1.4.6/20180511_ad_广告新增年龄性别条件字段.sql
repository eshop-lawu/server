alter table `eshop_ad`.`ad` add column  sex tinyint(2) DEFAULT '1' COMMENT '性别 (0--男，1--全部，2--女)' after radius;

alter table `eshop_ad`.`ad` add column  min_age int(5) DEFAULT '0' COMMENT '年龄范围（小）' after sex;
alter table `eshop_ad`.`ad` add column  max_age int(5) DEFAULT '200' COMMENT '年龄范围（大）' after min_age;