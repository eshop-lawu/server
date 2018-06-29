use `eshop_activity`;
ALTER TABLE `draw_lottery_activity_prize` ADD COLUMN `prize_type` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '奖品类型：1--金额，2--积分，3--商品' AFTER `status`;
ALTER TABLE `draw_lottery_activity_prize` ADD COLUMN `is_send_prize` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否立即到账：0--否，1--是' AFTER `status`;
ALTER TABLE `draw_lottery_activity_prize` ADD COLUMN `is_address` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否需要填写地址：0--否，1--是' AFTER `status`;


ALTER TABLE `draw_lottery_activity_record` MODIFY COLUMN `status` TINYINT(2) UNSIGNED NOT NULL COMMENT '0--待处理，1--已参与，2--未中奖，3--已中奖，4--放弃领奖，5--已领奖，6--积分扣除失败，7--奖品已发放';