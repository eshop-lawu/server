USE `eshop_user`;
ALTER TABLE `member` MODIFY COLUMN `pwd` char(57) DEFAULT NULL COMMENT '密码' AFTER `account`;