--地址表现在会员与商家共用，不能用id去判断唯一，需要添加user_num区分
ALTER TABLE `address` ADD COLUMN `user_num` varchar(19) NOT NULL COMMENT '用户编号' AFTER `user_id`;

--弃用user_id
ALTER TABLE `address` MODIFY COLUMN `user_id`  bigint(20) UNSIGNED NULL COMMENT '用户ID' AFTER `id`;