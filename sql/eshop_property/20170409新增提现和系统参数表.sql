DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '键',
  `value` varchar(20) NOT NULL COMMENT '值',
  `remark` varchar(50) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统参数配置表';

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for withdraw_cash
-- ----------------------------
DROP TABLE IF EXISTS `withdraw_cash`;
CREATE TABLE `withdraw_cash` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `cash_money` decimal(18,0) NOT NULL COMMENT '提现金额',
  `current_scale` varchar(16) NOT NULL COMMENT '提现比例1:0.95',
  `money` decimal(18,0) NOT NULL COMMENT '到账金额',
  `user_type` tinyint(3) NOT NULL COMMENT '提现类型(1-用户提现2-商家提现)',
  `channel` tinyint(3) NOT NULL COMMENT '渠道（1-人工2-代付)',
  `status` tinyint(3) NOT NULL COMMENT '提现状态(1-申请中2-受理中3-提现成功4-提现失败)',
  `business_bank_account_id` bigint(20) NOT NULL COMMENT '提现银行卡关联ID',
  `cash_number` varchar(25) NOT NULL COMMENT '提现单号',
  `third_number` varchar(25) DEFAULT NULL COMMENT '第三方订单号(代付时存在)',
  `audit_user_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `audit_faild_reason` varchar(100) DEFAULT NULL COMMENT '审核失败原因(冗余)',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户提现表';