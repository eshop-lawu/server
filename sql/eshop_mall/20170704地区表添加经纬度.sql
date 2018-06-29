alter table `region` add column  `latitude` DECIMAL(10,7) UNSIGNED  COMMENT '纬度' after name;
alter table `region` add column  `longitude` DECIMAL(10,7) UNSIGNED COMMENT '经度' after name;