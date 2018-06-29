use eshop_game;
CREATE TABLE `game_football_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `attend_min_point` int(8)  COMMENT '每次参与最小花费积分',
  `attend_max_point` int(8)  COMMENT '每次参与最大花费积分',
  `field_max_num` int(5) DEFAULT '0' COMMENT '串关最大可串场数',
  `status` tinyint(2) DEFAULT '1' COMMENT '1-启用 2-禁用',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='足球竞猜配置';


CREATE TABLE `game_football_team` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `team_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '球队名称' ,
  `team_logo`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '球队logo' ,
  `one_winner_odds`  decimal(10,2) NOT NULL COMMENT '冠军赔率' ,
  `second_winner_odds`  decimal(10,2) NOT NULL COMMENT '亚军赔率' ,
  `third_winner_odds`  decimal(10,2) NOT NULL COMMENT '季军赔率' ,
  `settle_status`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '结算状态（1-未结算 2-结算中 3-结算成功 4-结算失败）' ,
  `status` tinyint(2) DEFAULT '1' COMMENT '0-删除 1-禁用 1-启用 2-出局',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='足球竞猜球队信息';


CREATE TABLE `game_football_competition` (
`id`  bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
`title`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '赛事名称' ,
`begin_time`  datetime NOT NULL COMMENT '赛事开始时间' ,
`bet_end_time`  datetime NOT NULL COMMENT '押注结束时间' ,
`settle_time`  datetime NOT NULL COMMENT '结算时间' ,
`home_team_id` bigint(20) unsigned NOT NULL COMMENT '主场球队id' ,
`away_team_id` bigint(20) unsigned NOT NULL COMMENT '客场球队id' ,
`odds_home`  decimal(10,2) NOT NULL COMMENT '赔率（主场）' ,
`odds_away`  decimal(10,2) NOT NULL COMMENT '赔率（客场）' ,
`odds_even`  decimal(10,2) NOT NULL COMMENT '平局赔率' ,
`score_home`  int(10) NULL DEFAULT 0 COMMENT '得分（主场）' ,
`score_away`  int(10) NULL DEFAULT 0 COMMENT '得分(客场)' ,
`win_team`  tinyint(2) NULL COMMENT '获胜球队(1-主  2-客 3-平局)' ,
`settle_status`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '结算状态（1-未结算 2-结算中 3-结算成功 4-结算失败）' ,
`status`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '1-启用 2-禁用' ,
`gmt_modified`  datetime NOT NULL COMMENT '修改时间' ,
`gmt_create`  datetime NOT NULL COMMENT '创建时间' ,
 PRIMARY KEY (`id`)
) COMMENT='赛程表';
