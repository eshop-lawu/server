alter table ad_platform add column  `merchant_store_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '�ŵ�id';

alter table ad_platform modify column `type` tinyint(2)  COMMENT '�������(1-������2-��Ʒ 3-�ŵ�)';


alter table member_ad_record add member_num varchar(19) not Null COMMENT '��Ա���' after member_id;
alter table member_ad_record add point decimal(10,2)  COMMENT '��û���' after ad_id;
alter table member_ad_record add `status` tinyint(2)  DEFAULT '0' COMMENT '״̬(0-û�������1-�������)';
