ALTER TABLE pay_order ADD COLUMN `member_num` VARCHAR (19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编号' AFTER `member_id`;
ALTER TABLE pay_order ADD COLUMN `commission_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否计算过提成(0-没有计算过提成|1-计算过提成)' AFTER `status`;
ALTER TABLE pay_order ADD COLUMN `gmt_commission` datetime DEFAULT NULL COMMENT '计算提成时间' AFTER `gmt_create`;