use eshop_activity;

CREATE TABLE `draw_lottery_activity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '活动名称',
  `img_path` varchar(200) NOT NULL COMMENT '活动图片',
  `grade` tinyint(2) unsigned NOT NULL COMMENT '参与等级',
  `status` tinyint(2) unsigned NOT NULL COMMENT '1--未发布，2--进行中，3--已发布，4--已结束，5--下架，6--删除',
  `remark` varchar(500) DEFAULT NULL COMMENT '活动说明',
  `begin_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抽奖活动';

CREATE TABLE `draw_lottery_activity_prize` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `draw_lottery_activity_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '抽奖活动id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `img_path` varchar(200) NOT NULL COMMENT '图片',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '价格',
  `number` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '数量',
  `inventory` INT(5) UNSIGNED NOT NULL DEFAULT '0' COMMENT '库存',
  `status` tinyint(2) unsigned NOT NULL COMMENT '1--有效，2--无效',
  `freight_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '运费',
  `merchant_store_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '门店id',
  `ad_img_path` varchar(200) NOT NULL COMMENT '广告图片',
  `rate` decimal(5,2) DEFAULT NULL COMMENT '中奖概率',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抽奖活动奖品';


CREATE TABLE `draw_lottery_activity_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `user_account` varchar(20) NOT NULL COMMENT '用户账号',
  `draw_lottery_activity_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '抽奖活动id',
  `draw_lottery_activity_prize_id` BIGINT(20) UNSIGNED NULL DEFAULT '0' COMMENT '奖品id',
	`prize_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '奖品名称',
  `status` tinyint(2) unsigned NOT NULL COMMENT '0--待处理，1--已参与，2--未中奖，3--已中奖，4--放弃领奖，5--已领奖，6--积分扣除失败',
  `channel` tinyint(2) NOT NULL COMMENT '途径0免费1兑换',
  `pay_point` DECIMAL(10,2) UNSIGNED NULL DEFAULT '0.00' COMMENT '兑换积分',
  `consignee_name` VARCHAR(25) NULL DEFAULT NULL COMMENT '收货人姓名',
  `consignee_mobile` VARCHAR(15) NULL DEFAULT NULL COMMENT '收货人手机号',
  `consignee_address` VARCHAR(180) NULL DEFAULT NULL COMMENT '收货人地址',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抽奖记录';

