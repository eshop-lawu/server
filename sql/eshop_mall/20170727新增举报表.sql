CREATE TABLE `inform` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `informer_user_num` varchar(19) NOT NULL COMMENT '举报人用户编号',
  `informer_account` varchar(20) DEFAULT NULL COMMENT '举报人账号',
  `inform_type` tinyint(2) DEFAULT NULL COMMENT '举报类型:1平面广告、2E赞、3商家、4商品',
  `informt_item_id` bigint(20) DEFAULT NULL COMMENT '被举报对象ID',
  `informt_item_name` varchar(255) DEFAULT NULL COMMENT '被举报对象名称',
  `content` varchar(255) DEFAULT NULL COMMENT '举报内容',
  `status` tinyint(2) unsigned NOT NULL COMMENT '0：待处理，1：已处理，2：不处理',
  `auditor_id` int(11) DEFAULT '0' COMMENT '审核人员ID',
  `auditor_name` varchar(50) DEFAULT NULL COMMENT '审核人员名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='举报表';