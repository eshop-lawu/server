CREATE TABLE `ad` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `media_url` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '广附件路径',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '广告内容',
  `type` tinyint(2) NOT NULL COMMENT '广告类型(1-平面广告2-视频广告)',
  `put_way` tinyint(2) NOT NULL COMMENT '投放方式(1-区域2-粉丝 3-雷达)',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `areas` varchar(25) DEFAULT NULL COMMENT '投放区域',
  `radius` int(10) unsigned DEFAULT NULL COMMENT '半径，单位米',
  `point` decimal(10,2) unsigned DEFAULT NULL COMMENT '单个积分',
  `total_point` decimal(10,2) unsigned NOT NULL COMMENT '总投放积分',
  `ad_count` int(5) unsigned DEFAULT NULL COMMENT '广告数量',
  `hits` int(5) unsigned DEFAULT NULL COMMENT '已点击次数',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态(0-删除1-上架2-投放中3-投放结束4-下架)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) CHARSET=utf8mb4 COMMENT='广告表';

CREATE TABLE `ad_platform` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `product_id` bigint(20) unsigned DEFAULT NULL COMMENT '商品ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `media_url` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '广附件路径',
  `link_url` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '链接地址',
  `type` tinyint(2) NOT NULL COMMENT '广告类型(1-纯链接2-商品)',
  `position` tinyint(2) NOT NULL COMMENT '广告位置(1-人气推荐2-要购物顶部广告 3-要购物今日推荐4-要购物精品5-看广告顶部广告)',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态(0-删除1-上架2-下架)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='平台广告表';

CREATE TABLE `favorite_ad` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `ad_id` bigint(20) unsigned NOT NULL COMMENT '广告ID',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员ID',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='广告收藏表';

CREATE TABLE `red_packet` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家ID',
  `put_way` tinyint(2) NOT NULL COMMENT '投放方式(1-普通红包2-拼手气红包)',
  `package_count` int(5) unsigned NOT NULL COMMENT '红包数量',
  `totle_point` decimal(10,2) unsigned DEFAULT NULL COMMENT '所需积分',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态(0-删除1-上架3-下架)',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='红包表';