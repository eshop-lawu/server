CREATE TABLE `report_sales_daily` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_order_amount` decimal(20,6) unsigned NOT NULL COMMENT '买单金额',
  `shopping_order_amount` decimal(20,6) unsigned NOT NULL COMMENT '购物订单金额',
  `total_amount` decimal(20,6) unsigned NOT NULL COMMENT '总金额',
  `gmt_report` datetime NOT NULL COMMENT '统计时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='平台总销量表(按天)';

CREATE TABLE `report_sales_month` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_order_amount` decimal(20,6) unsigned NOT NULL COMMENT '买单金额',
  `shopping_order_amount` decimal(20,6) unsigned NOT NULL COMMENT '购物订单金额',
  `total_amount` decimal(20,6) unsigned NOT NULL COMMENT '总金额',
  `gmt_report` date NOT NULL COMMENT '统计日期',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='平台总销量表(按月)';