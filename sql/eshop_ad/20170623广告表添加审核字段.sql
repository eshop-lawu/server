alter table ad add column `audit_time` datetime DEFAULT NULL COMMENT '审核时间' after `status`;
alter table ad add column `remark` varchar(200) DEFAULT NULL COMMENT '审核备注' after `status`;
alter table ad add column `auditor_id` int(11) DEFAULT 0 COMMENT '审核人员ID' after `status`;