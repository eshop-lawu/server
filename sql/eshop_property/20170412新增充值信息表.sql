use eshop_property;
DROP TABLE IF EXISTS `recharge`;

CREATE TABLE `recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `recharge_money` decimal(18,4) unsigned NOT NULL COMMENT '充值金额/积分',
  `current_scale` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '当前充值比例',
  `money` decimal(18,4) unsigned NOT NULL COMMENT '充值所得金额/积分',
  `recharge_type` tinyint(3) unsigned NOT NULL COMMENT '充值类型：1-余额,2-积分',
  `channel` tinyint(3) unsigned NOT NULL COMMENT '充值方式：2-支付宝,3微信',
  `status` tinyint(3) unsigned NOT NULL COMMENT '1-待支付,2-成功,3-失败',
  `recharge_number` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '充值单号',
  `third_number` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方支付的订单号',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NULL COMMENT '修改日期',
  `note` text COLLATE utf8mb4_unicode_ci COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='充值记录表(第三方支付)';