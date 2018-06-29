use eshop_mall;
CREATE TABLE `lottery_activity` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prize_name` VARCHAR(50) NOT NULL COMMENT '奖品名称',
  `prize_price` DECIMAL(10,2) UNSIGNED NOT NULL COMMENT '奖品价值',
  `prize_number` INT(3) UNSIGNED NOT NULL COMMENT '奖品数量',
  `image_path` VARCHAR(200) NOT NULL COMMENT '奖品图片',
  `begin_time` DATETIME NOT NULL COMMENT '开始时间',
  `end_time` DATETIME NOT NULL COMMENT '结束时间',
  `grade` TINYINT(2) UNSIGNED NOT NULL COMMENT '等级',
  `status` TINYINT(3) UNSIGNED NOT NULL COMMENT '0--未发布，1--进行中，2--已发布，3--已结束，4--已开奖，5--下架，6--删除',
  `gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
  `gmt_create` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
COMMENT='抽奖活动'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;


use eshop_mall;
CREATE TABLE `lottery_record` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户id',
  `user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
  `account` VARCHAR(20) NOT NULL COMMENT '账号',
  `lottery_activity_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '抽奖活动id',
  `prize_name` VARCHAR(50) NOT NULL COMMENT '奖品名称',
  `lottery_count` INT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '抽奖次数',
  `lottery_result` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '抽奖结果(0--未中奖，1--中奖)',
  `gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
  `gmt_create` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
COMMENT='抽奖记录'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;