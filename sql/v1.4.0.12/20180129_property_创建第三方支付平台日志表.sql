use eshop_property;
CREATE TABLE `pay_platform_log` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `out_biz_no` varchar(64) NOT NULL COMMENT '交易号',
  `platform_type` tinyint(2) NOT NULL COMMENT '平台类型',
  `user_num` varchar(64) DEFAULT NULL COMMENT '用户编号',
  `code` varchar(32) DEFAULT NULL COMMENT '返回状态码',
  `sub_code` varchar(32) DEFAULT NULL COMMENT '业务返回码',
  `err_code` varchar(32) DEFAULT NULL COMMENT '错误代码(微信)',
  `response` text COMMENT '接口响应',
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT='第三方支付平台日志表';