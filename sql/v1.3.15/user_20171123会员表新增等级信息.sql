ALTER TABLE eshop_user.member ADD COLUMN `grade` tinyint(2) DEFAULT 1 COMMENT '会员等级（一个数字对应一个级别，从低到高）' AFTER `ry_token`;
ALTER TABLE eshop_user.member ADD COLUMN `growth_value` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '等级成长值' AFTER `grade`;

ALTER TABLE eshop_user.member DROP COLUMN property_id;