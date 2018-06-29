ALTER TABLE merchant_store MODIFY address varchar(75) DEFAULT NULL COMMENT '店铺地址信息';
ALTER TABLE merchant_store MODIFY longitude decimal(10,7) DEFAULT NULL COMMENT '经度';
ALTER TABLE merchant_store MODIFY latitude decimal(10,7) DEFAULT NULL COMMENT '纬度';