use eshop_property;
DROP TABLE IF EXISTS `freeze`;

CREATE TABLE `freeze` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `money` decimal(10,6) NOT NULL COMMENT '冻结金额',
  `fund_type` tinyint(2) NOT NULL COMMENT '类型(1-订单)',
  `biz_id` bigint(20) NOT NULL COMMENT '关联业务表主键',
  `status` tinyint(2) NOT NULL COMMENT '状态(1-冻结2-释放)',
  `remark` varchar(200) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='冻结资金表';

alter table property modify column `name` varchar(50) NOT NULL COMMENT '键';
alter table freeze add column `original_money` decimal(10,6) NOT NULL COMMENT '冻结金额(原始冗余)' after `money`;
alter table transaction_detail add column `direction` tinyint(2) NOT NULL COMMENT '1-支出2-收入' after `amount`;
alter table point_detail add column `direction` tinyint(2) NOT NULL COMMENT '1-支出2-收入' after `point`;