use eshop_product;
CREATE TABLE `seckill_activity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '活动名称',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `member_level` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '会员等级',
  `product_valid_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商家可提交审核的商品数',
  `selling_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '活动定价',
  `picture` varchar(200) NOT NULL DEFAULT '' COMMENT '宣传图片',
  `activity_status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '活动状态(1-未发布|2-未开始|3-进行中|4-已结束)',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0-删除|1-正常)',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抢购活动表';

use eshop_product;
CREATE TABLE `seckill_activity_product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` bigint(20) unsigned NOT NULL COMMENT '抢购活动id',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家id',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品id',
  `product_picture` varchar(120) NOT NULL COMMENT '商品图片',
  `product_name` varchar(100) NOT NULL COMMENT '商品名称',
  `original_price` decimal(10,2) unsigned NOT NULL COMMENT '商品原价',
  `product_model_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商品型号总数量',
  `left_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '剩余数量',
  `turnover` decimal(10,2) unsigned NOT NULL DEFAULT '0' COMMENT '成交额',
  `reasons` varchar(200) NOT NULL DEFAULT '' COMMENT '反馈原因',
  `auditor_account` varchar(50) DEFAULT NULL COMMENT '审核人员',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `attention_count` int(10) unsigned NOT NULL COMMENT '关注人数',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1-未审核|2-已审核|3-未通过|)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抢购活动审核商品';

use eshop_product;
CREATE TABLE `seckill_activity_product_model` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_product_id` bigint(20) unsigned NOT NULL COMMENT '抢购活动审核商品id',
  `product_model_id` bigint(20) unsigned NOT NULL COMMENT '商品型号id',
  `count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商品型号数量',
  `left_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '剩余数量',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抢购活动审核商品';

use eshop_product;
CREATE TABLE `seckill_activity_attention` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` bigint(20) unsigned NOT NULL COMMENT '抢购活动id',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品id',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `is_remind` tinyint(1) unsigned NOT NULL COMMENT '是否提醒(0-未提醒|1-已提醒)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抢购商品关注表';