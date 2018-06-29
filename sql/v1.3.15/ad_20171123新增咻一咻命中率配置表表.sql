use eshop_ad;
CREATE TABLE `ad_rate_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `game_time` int(5) unsigned NOT NULL COMMENT '游戏时长（单位秒）',
  `rate` int(5) NOT NULL COMMENT '对应命中率0~100',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咻一咻命中率配置表';


INSERT INTO `ad_rate_setting` VALUES ('1', '1', '100', '2017-11-23 14:18:23');
INSERT INTO `ad_rate_setting` VALUES ('2', '2', '100', '2017-11-23 14:18:39');
INSERT INTO `ad_rate_setting` VALUES ('3', '3', '100', '2017-11-23 14:18:46');
INSERT INTO `ad_rate_setting` VALUES ('4', '4', '90', '2017-11-23 14:19:09');
INSERT INTO `ad_rate_setting` VALUES ('5', '5', '90', '2017-11-23 14:19:39');
INSERT INTO `ad_rate_setting` VALUES ('6', '6', '80', '2017-11-23 14:19:49');
INSERT INTO `ad_rate_setting` VALUES ('7', '7', '70', '2017-11-23 14:19:59');
INSERT INTO `ad_rate_setting` VALUES ('8', '8', '60', '2017-11-23 14:20:13');
INSERT INTO `ad_rate_setting` VALUES ('9', '9', '50', '2017-11-23 14:20:24');
INSERT INTO `ad_rate_setting` VALUES ('10', '10', '40', '2017-11-23 14:20:48');
INSERT INTO `ad_rate_setting` VALUES ('11', '11', '30', '2017-11-23 14:21:05');
INSERT INTO `ad_rate_setting` VALUES ('12', '12', '20', '2017-11-23 14:21:15');
INSERT INTO `ad_rate_setting` VALUES ('13', '13', '10', '2017-11-23 14:21:26');
INSERT INTO `ad_rate_setting` VALUES ('14', '14', '0', '2017-11-23 14:21:38');