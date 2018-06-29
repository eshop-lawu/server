use eshop_activity;
CREATE TABLE `rich_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `order_num` int(8) NOT NULL DEFAULT '0' COMMENT '创建序号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `id_card_num` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `diamond` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT 'E钻数量',
  `diamond_lucky` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '幸运钻数量',
  `diamond_total` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT 'E钻总量',
  `power` int(11) NOT NULL DEFAULT '0' COMMENT '挖矿动力值',
  `power_snapshoot` int(11) NOT NULL DEFAULT '0' COMMENT '挖矿动力值快照',
  `alipay_user_id` varchar(20) DEFAULT NULL COMMENT '支付宝绑定ID',
  `alipay_bind_time` datetime DEFAULT NULL COMMENT '支付宝绑定时间',
  `last_take_time` datetime DEFAULT NULL COMMENT '最后领取时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_num` (`user_num`),
  UNIQUE KEY `order_num` (`order_num`)
) COMMENT='瑞奇岛账户表';

CREATE TABLE `rich_diamond_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `type` tinyint(2) NOT NULL COMMENT '类型 1-E钻 2-幸运钻',
  `source` tinyint(2) NOT NULL COMMENT '来源 1-日常领取',
  `direction` tinyint(2) NOT NULL COMMENT '1-支出 2-收入',
  `amount` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '数量',
  `status` tinyint(2) NOT NULL COMMENT '状态 0-已分配 1-已领取 2-未领取',
  `take_time` datetime DEFAULT NULL COMMENT '领取时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_num` (`user_num`)
) COMMENT='瑞奇岛E钻收支记录表';

CREATE TABLE `rich_power_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `source` tinyint(2) NOT NULL COMMENT '来源 1-登录 2-邀请E友 3-娱乐 4-看广告 5-购物 6-绑定支付宝 7-身份认证 8-幸运钻领取消耗',
  `direction` tinyint(2) NOT NULL COMMENT '1-支出 2-收入',
  `power` int(11) NOT NULL DEFAULT '0' COMMENT '挖矿动力值',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_num` (`user_num`)
) COMMENT='瑞奇岛挖矿动力收支记录表';

CREATE TABLE `rich_power_task_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `is_binding_alipay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否绑定支付宝',
  `is_identity_auth` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否身份认证',
  `is_login` tinyint(1) NOT NULL DEFAULT '0' COMMENT '定期登录完成情况',
  `friend_invite_count` int(8) NOT NULL DEFAULT '0' COMMENT '定期邀请E友数量',
  `game_count` int(8) NOT NULL DEFAULT '0' COMMENT '定期娱乐游戏局数',
  `ad_count` int(8) NOT NULL DEFAULT '0' COMMENT '定期看广告数量',
  `shopping_amount` int(8) NOT NULL DEFAULT '0' COMMENT '定期购物金额',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_num` (`user_num`)
) COMMENT='瑞奇岛动力任务完成情况记录表';

CREATE TABLE `rich_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `diamond_config` text COMMENT '瑞奇岛E钻配置信息json',
  `diamond_effective_time` datetime DEFAULT NULL COMMENT '瑞奇岛E钻配置生效时间，第二天凌晨更新',
  `power_task_config` text COMMENT '动力任务配置信息json',
  `power_effective_time` datetime DEFAULT NULL COMMENT '动力任务配置生效时间，第二天凌晨更新',
  `notice` varchar(1000) DEFAULT NULL COMMENT '公告',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='瑞奇岛配置表';

CREATE TABLE `rich_diamond_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `diamond_sent` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '已发放E钻数量',
  `diamond_lucky_sent` decimal(20,6) unsigned NOT NULL DEFAULT '0.000000' COMMENT '已发放幸运钻数量',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='瑞奇岛E钻信息表';

CREATE TABLE `diamond_distribution_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `residents` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '分配的居民数量',
  `allocated_residents` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '已分配的居民数量',
  `expected_ordinary_allocations` decimal(20,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '预期分配E钻数量',
  `real_ordinary_allocations` decimal(20,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '实际分配E钻数量',
  `lucky_residents` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '分配幸运钻的居民数量',
  `allocated_lucky_residents` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '已分配幸运钻的居民数量',
  `expected_lucky_allocations` decimal(20,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '预期分配幸运钻数量',
  `real_luck_allocations` decimal(20,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '实际分配幸运钻数量',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1-分配中|2-已分配|3-已发放)',
  `gmt_allocations_complete` datetime DEFAULT NULL COMMENT '分配完成时间',
  `gmt_grant_complete` datetime DEFAULT NULL COMMENT '发放完成时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='钻石分配记录表';

alter table eshop_user.member_profile add column `is_help_rich_task` TINYINT(1) UNSIGNED NULL DEFAULT '0' COMMENT '是否助力瑞奇岛任务' after gmt_last_login;
alter table eshop_user.merchant_profile add column `is_help_rich_task` TINYINT(1) UNSIGNED NULL DEFAULT '0' COMMENT '是否助力瑞奇岛任务' after gmt_last_login;
update eshop_user.member_profile set is_help_rich_task=1;
update eshop_user.merchant_profile set is_help_rich_task=1;