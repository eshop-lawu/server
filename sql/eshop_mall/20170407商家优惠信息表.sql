CREATE TABLE `merchant_favored` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `type` tinyint(2) NOT NULL COMMENT '1:每满、2:满减、3:全单折扣',
  `reach_amount` decimal(10,2) DEFAULT NULL COMMENT '满额',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠金额',
  `favored_amount` decimal(10,2) DEFAULT NULL COMMENT '折扣率',
  `valid_week_time` varchar(50) DEFAULT NULL COMMENT '每周有效时间段',
  `valid_day_begin_time` varchar(20) DEFAULT NULL COMMENT '每日有效开始时间',
  `valid_day_end_time` varchar(20) DEFAULT NULL COMMENT '每日有效结束时间',
  `entire_begin_time` datetime NOT NULL COMMENT '总有效期：开始时间',
  `entire_end_time` datetime NOT NULL COMMENT '总有效期：结束时间',
  `status` tinyint(2) NOT NULL COMMENT '状态（1：有效0：无效）',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='商家优惠设置';