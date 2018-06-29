use eshop_property;
DROP TABLE IF EXISTS `business_deposit`;

CREATE TABLE `business_deposit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_id` bigint(20) NOT NULL COMMENT '商家ID',
  `user_num` varchar(50) NOT NULL COMMENT '商家编号',
  `business_account` varchar(20) NOT NULL COMMENT '商家账号',
  `business_name` varchar(50) NOT NULL COMMENT '负责人姓名',
  `deposit_number` varchar(50) NOT NULL COMMENT '商户订单号',
  `third_number` varchar(50) DEFAULT NULL COMMENT '第三方订单号',
  `third_account` varchar(200) DEFAULT NULL COMMENT '第三方平台支付账户',
  `payment_method` tinyint(3) DEFAULT NULL COMMENT '充值方式：2-支付宝,3微信',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `status` tinyint(3) NOT NULL COMMENT '状态(0待支付 1未核实 2已核实 3申请退款 4受理退款 5退款成功 6退款失败)',
  `business_bank_account_id` bigint(20) DEFAULT NULL COMMENT '提现银行卡关联ID',
  `province_id` bigint(20) NOT NULL COMMENT '注册区域省ID(冗余)',
  `city_id` bigint(20) NOT NULL COMMENT '注册区域城市ID(冗余)',
  `area_id` bigint(20) NOT NULL COMMENT '注册区域区ID(冗余)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '初始化时间',
  `gmt_pay` datetime DEFAULT NULL COMMENT '付款回调时间',
  `gmt_refund` datetime DEFAULT NULL COMMENT '退款时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `oper_user_id` bigint(20) DEFAULT NULL COMMENT '处理人ID',
  `oper_user_name` varchar(50) DEFAULT NULL COMMENT '处理人姓名',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家保证金表';

alter table transaction_detail modify column `biz_id` varchar(500) DEFAULT '0' COMMENT '业务类型操作对应的业务表ID';