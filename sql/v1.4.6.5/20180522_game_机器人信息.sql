-- 机器人服务
INSERT INTO `eshop_game`.`game_robot_server`(`service_path`, `instances`, `status`, `gmt_modified`, `gmt_create`) VALUES ('http://192.168.100.11:6001/', 0, 1, NOW(), NOW());
INSERT INTO `eshop_game`.`game_robot_server`(`service_path`, `instances`, `status`, `gmt_modified`, `gmt_create`) VALUES ('http://192.168.100.12:6001/', 0, 1, NOW(), NOW());

ALTER TABLE `eshop_game`.`game_robot_account` 
ADD COLUMN `nickname` varchar(300) NULL COMMENT '机器人昵称(多个昵称用逗号分隔)' AFTER `password`,
ADD COLUMN `head_img` varchar(300) NULL COMMENT '机器人头像(多个头像用逗号分隔)' AFTER `nickname`,
ADD COLUMN `region` varchar(300) NULL COMMENT '机器人区域(多个区域用逗号分隔)' AFTER `head_img`;

use eshop_user;
-- 初始化机器人游戏账号脚本
SET @num:="M80000000000000001";
SET @account:="20000000001";

INSERT INTO `eshop_user`.`member`(`id`, `num`, `account`, `pwd`, `mobile`, `name`, `nickname`, `region_path`, `region_name`, `sex`, `birthday`, `headimg`, `status`, `is_freeze`, `is_zombie`, `frozen_type`, `freeze_reason`, `inviter_id`, `inviter_type`, `level`, `reg_origin`, `gt_cid`, `ry_token`, `grade`, `growth_value`, `gmt_modified`, `gmt_create`) VALUES (@id, @num, @account, 'c59252a0ca1565530d197d7a782e12b29757320f23f2050e', @account, NULL, 'E店用户', '44/4403/440306', '广东省深圳市宝安区', 0, DATE_FORMAT(NOW(),'%Y-%m-%d'), 'default/user_headimg.png', 1, 0, 0, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, 1, 0, NULL, NOW());

SET @id:=(SELECT LAST_INSERT_ID());

INSERT INTO `eshop_user`.`member_profile`(`id`, `invite_member_count`, `invite_member_count2`, `invite_member_count3`, `invite_merchant_count`, `invite_merchant_count2`, `invite_merchant_count3`, `reg_ip`, `reg_platform_ver`, `reg_app_ver`, `gmt_last_login`, `is_help_rich_task`, `gmt_modified`, `gmt_create`) VALUES (@id, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 1, NOW(), NOW());

INSERT INTO `eshop_game`.`game_robot_account`(`account`, `user_num`, `password`, `nickname`, `head_img`, `region`, `status`, `server_id`, `gmt_modified`, `gmt_create`) VALUES (@account, @num, '123456', 'E店用户', 'default/user_headimg.png', '44/4403/440306', 1, NULL, NOW(), NOW());

