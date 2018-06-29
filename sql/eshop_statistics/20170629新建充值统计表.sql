CREATE TABLE `report_recharge_balance_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_money` decimal(20,6) unsigned NOT NULL COMMENT '会员充值余额',
  `merchant_money` decimal(20,6) unsigned NOT NULL COMMENT '商家充值余额',
  `total_money` decimal(20,6) unsigned NOT NULL COMMENT '总充值余额',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='充值统计-余额充值(按天)';

CREATE TABLE `report_recharge_balance_month` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_money` decimal(20,6) unsigned NOT NULL COMMENT '会员充值余额',
  `merchant_money` decimal(20,6) unsigned NOT NULL COMMENT '商家充值余额',
  `total_money` decimal(20,6) unsigned NOT NULL COMMENT '总充值余额',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) COMMENT='充值统计-余额充值(按月)';