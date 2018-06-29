alter table bank_account add column `audit_time` datetime DEFAULT NULL COMMENT '操作时间' ;
alter table bank_account add column `remark` varchar(200) DEFAULT NULL COMMENT '操作备注';
alter table bank_account add column `auditor_id` int(11)  COMMENT '操作人员ID' ;
alter table bank_account add column `user_type` tinyint(2)  COMMENT '1-会员 2-商家' ;
alter table bank_account add column  `is_bind_forever`  tinyint(1)  default 0 COMMENT '是否永久绑定';
alter table bank_account add column `gmt_modified` datetime COMMENT '修改时间';
alter table bank_account add column `gmt_create` datetime COMMENT '创建时间';

update bank_account set gmt_modified=CURRENT_TIMESTAMP, gmt_create=CURRENT_TIMESTAMP;