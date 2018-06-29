CREATE TABLE `report_withdraw_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_money` decimal(20,6) unsigned NOT NULL COMMENT '会员提现成功金额(含手续费)',
  `merchant_money` decimal(20,6) unsigned NOT NULL COMMENT '商家提现成功金额(含手续费)',
  `total_money` decimal(20,6) unsigned NOT NULL COMMENT '总提现成功金额(含手续费)',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提现统计报表(按天)';

CREATE TABLE `report_withdraw_month` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_money` decimal(20,6) unsigned NOT NULL COMMENT '会员提现成功金额(含手续费)',
  `merchant_money` decimal(20,6) unsigned NOT NULL COMMENT '商家提现成功金额(含手续费)',
  `total_money` decimal(20,6) unsigned NOT NULL COMMENT '总提现成功金额(含手续费)',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提现统计报表(按月)';