USE `eshop_activity`;
ALTER TABLE `eshop_activity`.`help_redpacket_attend_detail` 
MODIFY COLUMN `wx_openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '微信用户openid' AFTER `account`;