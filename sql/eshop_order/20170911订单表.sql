-- 添加用户昵称，用于消息的显示
ALTER TABLE `shopping_order` ADD COLUMN `member_nickname`  varchar(50) NOT NULL COMMENT '会员昵称' AFTER `member_num`;