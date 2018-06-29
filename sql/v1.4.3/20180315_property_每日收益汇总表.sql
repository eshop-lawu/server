use eshop_property;
CREATE TABLE `income_daily_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(2) DEFAULT NULL COMMENT '1-会员 2-商家',
  `money` decimal(20,6) NOT NULL COMMENT '积分或余额',
  `income_type` tinyint(2) NOT NULL COMMENT '收益类型（余额|积分）',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='每日收益汇总表';