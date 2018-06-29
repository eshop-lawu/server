CREATE TABLE `report_user_income_expenditure` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `account` varchar(20) NOT NULL COMMENT '用户账号',
  `user_type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户类型，1是商家，2是会员',
  `income` decimal(20,6) unsigned NOT NULL COMMENT '收入金额',
  `expenditure` decimal(20,6) unsigned NOT NULL COMMENT '支出金额',
  `difference` decimal(20,6) NOT NULL COMMENT '差值金额',
  `gmt_report` date NOT NULL COMMENT '统计日期',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户收支统计表';