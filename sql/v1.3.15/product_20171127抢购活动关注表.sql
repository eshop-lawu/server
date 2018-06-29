-- 添加用户编号字段，用户推送和站内信
ALTER TABLE `seckill_activity_attention`
ADD COLUMN `member_num`  varchar(19) NOT NULL DEFAULT '' COMMENT '用户编号' AFTER `member_id`,
ADD COLUMN `activity_product_id`  bigint(20) UNSIGNED NOT NULL COMMENT '抢购活动商品id' AFTER `activity_id`,
DROP COLUMN `is_remind`;

