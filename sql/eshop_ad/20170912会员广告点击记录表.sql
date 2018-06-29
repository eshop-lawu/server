-- 广告积分最大额度10W，限制6位
ALTER TABLE `member_ad_record`
MODIFY COLUMN `point`  decimal(12,6) NULL DEFAULT NULL COMMENT '获取积分' AFTER `ad_id`,
MODIFY COLUMN `original_point`  decimal(12,6) NULL DEFAULT NULL COMMENT '原始积分' AFTER `point`;