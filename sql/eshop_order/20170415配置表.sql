/*
 * 20170415
 * 初始建表
 * */
CREATE TABLE `property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '键',
  `value` varchar(20) NOT NULL COMMENT '值',
  `remark` varchar(50) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='系统参数配置表';

/*
 * 20170415
 * 添加退货申请时间
 * 自动评价时间
 * */
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (1, "refund_request_time", "7", "退货申请时间", NOW(), NOW())
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (2, "automatic_evaluation", "7", "自动评价时间", NOW(), NOW())