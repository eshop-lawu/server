CREATE TABLE `work_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `account` varchar(20) NOT NULL DEFAULT '' COMMENT '账号',
  `type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '提交人类型 1--用户，2--商家',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '提交工单人/门店名称',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 1--未处理，2--已回复，3--不予处理',
  `content` varchar(250) NOT NULL COMMENT '工单内容',
  `reply_content` varchar(250) DEFAULT NULL COMMENT '回复内容',
  `auditor_id` int(11) DEFAULT '0' COMMENT '审核人员ID',
  `auditor_name` varchar(50) DEFAULT NULL COMMENT '审核人员名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `gmt_deal` datetime DEFAULT NULL COMMENT '处理时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工单表';