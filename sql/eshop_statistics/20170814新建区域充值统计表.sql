CREATE TABLE `report_area_recharge_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_recharge_balance` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '商家充值余额',
  `merchant_recharge_point` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '商家充值积分',
  `member_recharge_balance` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '用户充值余额',
  `member_recharge_point` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '用户充值积分',
  `total_recharge_balance` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '充值总余额',
  `total_recharge_point` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '充值总积分',
  `province_id` int(8) DEFAULT NULL COMMENT '省编号',
  `city_id` int(8) DEFAULT NULL COMMENT '市编号',
  `area_id` int(8) DEFAULT NULL COMMENT '区编号',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区域充值统计(按天，除去余额支付充值积分类型)';

CREATE TABLE `report_area_recharge_month` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_recharge_balance` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '商家充值余额',
  `merchant_recharge_point` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '商家值积分',
  `member_recharge_balance` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '用户值余额',
  `member_recharge_point` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '用户充值积分',
  `total_recharge_balance` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '充值总余额',
  `total_recharge_point` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '充值总积分',
  `province_id` int(8) DEFAULT NULL COMMENT '省编号',
  `city_id` int(8) DEFAULT NULL COMMENT '市编号',
  `area_id` int(8) DEFAULT NULL COMMENT '区编号',
  `gmt_report` date NOT NULL COMMENT '统计数据所属日期(yyyy-mm-01)',
  `gmt_create` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区域充值统计(按月，除去余额支付充值积分类型)';