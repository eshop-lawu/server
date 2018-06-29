alter table `eshop_ad`.`ad_platform` add column priority int(8) DEFAULT 0 COMMENT '广告位排序优先级' after content;

use eshop_user;
CREATE TABLE `wx_login_merchant` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `subscribe` int(3) DEFAULT '0' COMMENT '是否订阅公众号',
  `openid` varchar(50) NOT NULL COMMENT 'openid',
  `nickname` varchar(300) DEFAULT '' COMMENT '昵称',
  `sex` int(3) DEFAULT '0' COMMENT '性别',
  `city` varchar(50) DEFAULT '' COMMENT '用户所在城市',
  `country` varchar(50) DEFAULT '' COMMENT '用户所在国家',
  `province` varchar(50) DEFAULT '' COMMENT '用户所在省份',
  `language` varchar(15) DEFAULT '' COMMENT '用户的语言',
  `headimgurl` varchar(500) DEFAULT '' COMMENT '头像',
  `subscribe_time` datetime DEFAULT NULL COMMENT '用户关注时间',
  `unionid` varchar(50) DEFAULT '' COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `remark` varchar(200) DEFAULT '' COMMENT '公众号运营者对粉丝的备注',
  `groupid` bigint(20) unsigned DEFAULT '0' COMMENT '用户所在的分组ID',
  `tagid_list` varchar(200) DEFAULT '' COMMENT '用户被打上的标签ID列表',
  `is_subscribe_mp` tinyint(1) DEFAULT '0' COMMENT '是否关注公众号',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_openid` (`openid`) USING BTREE
) COMMENT='微信登录商家表';

use eshop_activity;
CREATE TABLE `rich_merchant_power_task_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(20) NOT NULL COMMENT '商家编号',
  `is_binding_alipay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否绑定支付宝',
  `is_binding_wx` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否绑定微信',
  `is_login` tinyint(1) NOT NULL DEFAULT '0' COMMENT '定期登录完成情况',
  `friend_invite_count` int(8) NOT NULL DEFAULT '0' COMMENT '定期邀请E友数量',
  `fens_invite_count` int(8) NOT NULL DEFAULT '0' COMMENT '每日邀请粉丝数量',
  `new_product_count` int(8) NOT NULL DEFAULT '0' COMMENT '每日新品上架',
  `ad_count` int(8) NOT NULL DEFAULT '0' COMMENT '每日广告投放数量',
  `recharge_point` int(8) NOT NULL DEFAULT '0' COMMENT '每日充值积分',
  `activity_count` int(8) NOT NULL DEFAULT '0' COMMENT '专场活动数量',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_num` (`user_num`)
) COMMENT='瑞奇岛商家动力任务完成情况记录表';

alter table `eshop_activity`.`rich_config` add column merchant_power_task_config text  COMMENT '商家动力任务配置信息json' after power_effective_time;
alter table `eshop_activity`.`rich_config` add column merchant_power_effective_time datetime  COMMENT '商家动力任务配置生效时间' after merchant_power_task_config;

alter table `eshop_product`.`product` add column gmt_finish_rich_task datetime DEFAULT NULL COMMENT '完成上新时间' after `remark`;

ALTER TABLE `eshop_activity`.`rich_config` ADD COLUMN `merchant_notice`  varchar(1000) NULL COMMENT '商家公告' AFTER `notice`;

INSERT INTO `eshop_operator`.`permission` (`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES ('252', '商家任务配置', 'powerTask:list', 'richConfig/merchant-rich-power-config.html', '247', '999', '2018-05-03 10:42:43', '2018-05-03 10:42:43');
INSERT INTO `eshop_operator`.`permission` (`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES ('253', '钻石记录', 'richDiamondRecord:list', 'richConfig/diamond-record-list.html', '247', '999', '2018-06-07 16:15:24', '2018-06-07 16:15:33');

alter table `eshop_mall`.`app_function_manage` add column is_enable_merchant_rich tinyint(1) DEFAULT 1 COMMENT '是否启用商家瑞奇岛' after `is_enable_rich`;
alter table `eshop_mall`.`app_function_manage` add column app_merchant_version varchar(50) DEFAULT NULL COMMENT '商家app版本号' after `app_version`;
