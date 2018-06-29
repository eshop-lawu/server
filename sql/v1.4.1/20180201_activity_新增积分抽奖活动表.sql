use eshop_activity;
CREATE TABLE `point_lottery_activity` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`prize_name` VARCHAR(50) NOT NULL COMMENT '奖品名称',
	`prize_price` DECIMAL(10,2) UNSIGNED NOT NULL COMMENT '奖品价值',
	`prize_image_path` VARCHAR(200) NOT NULL COMMENT '奖品图片',
	`begin_time` DATETIME NOT NULL COMMENT '参与开始时间',
	`end_time` DATETIME NOT NULL COMMENT '参与结束时间',
	`draw_time` DATETIME DEFAULT NULL COMMENT '抽奖时间',
	`lottery_time` DATETIME NOT NULL COMMENT '开奖时间',
	`lottery_point` INT(5) UNSIGNED NOT NULL DEFAULT '0' COMMENT '抽奖积分',
	`lottery_count` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '可中奖人数',
	`status` TINYINT(3) UNSIGNED NOT NULL COMMENT '1--未发布，2--进行中，3--已发布(可参与)，4--参与结束，5--已开奖，6--删除',
	`attent_number` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '参与人数',
	`lottery_param` VARCHAR(200) NULL DEFAULT NULL COMMENT '抽奖参数',
	`lottery_base_num` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '中奖基数',
	`lottery_result_nums` VARCHAR(200) NULL DEFAULT NULL COMMENT '中奖结果编号(多个)',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='积分抽奖活动'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `point_lottery_activity_prize_image` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`point_lottery_activity_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '幸运抽奖活动id',
	`image_path` VARCHAR(200) NOT NULL COMMENT '图片路径',
	`order_num` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '序号',
	`type` TINYINT(3) UNSIGNED NOT NULL COMMENT '1--活动介绍，2--中奖信息',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='积分抽奖活动图片'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `point_lottery_activity_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `mobile` varchar(20) NOT NULL COMMENT '抽奖手机号',
  `point_lottery_activity_id` bigint(20) unsigned NOT NULL COMMENT '抽奖活动id',
  `attent_count` int(10) unsigned NOT NULL COMMENT '参与次数',
  `pay_point` decimal(10,2) unsigned NOT NULL COMMENT '兑换积分',
  `status` tinyint(3) unsigned NOT NULL COMMENT '0--待处理，1--成功，2--积分扣除失败',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分抽奖活动订单';

CREATE TABLE `point_lottery_activity_record` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
	`mobile` VARCHAR(20) NOT NULL COMMENT '抽奖手机号',
	`lottery_num` INT(10) UNSIGNED NOT NULL COMMENT '抽奖号码',
	`point_lottery_activity_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '幸运抽奖活动id',
	`point_lottery_activity_order_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '活动订单id',
	`prize_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '奖品名称',
	`status` TINYINT(3) UNSIGNED NOT NULL COMMENT '1--未中奖，2--中奖',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='积分抽奖活动记录'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
ALTER TABLE `point_lottery_activity_record` ADD UNIQUE uk_lottery_num_point_lottery_activity_id ( `lottery_num`, `point_lottery_activity_id`);

CREATE TABLE `point_lottery_activity_report` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
	`lottery_times` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '抽奖次数',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='积分抽奖活动次数统计'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;