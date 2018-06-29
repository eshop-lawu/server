-- 新增商家编号
alter table product add column `merchant_num` varchar(19) NOT NULL DEFAULT '' COMMENT '商家编号' after `merchant_id`;