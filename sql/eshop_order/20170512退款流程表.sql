CREATE TABLE `shopping_refund_process` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shopping_refund_detail_id` bigint(20) unsigned NOT NULL COMMENT '退款详情id',
  `refund_status` tinyint(2) unsigned NOT NULL COMMENT '退款状态(0-待商家确认|1-填写退货地址|2-待退货|3-待退款|4-退款成功|5-退款失败|6-平台介入)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='退款流程表';