USE `eshop_user`;
ALTER TABLE `member` ADD COLUMN `reg_origin` varchar(50) DEFAULT NULL COMMENT '注册来源' AFTER `level`;