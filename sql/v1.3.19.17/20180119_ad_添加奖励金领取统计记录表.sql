USE `eshop_ad`;
CREATE TABLE `take_inviter_bounty_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(2) NOT NULL COMMENT '用户类型 1-用户  2-商家' ,
  `take_count` int(5) DEFAULT '0' COMMENT '领取奖励金个数',
  `take_date` date NOT NULL COMMENT '领取日期:年月日',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_num_date` (`user_num`,`take_date`) USING BTREE
) COMMENT='领取奖励金统计记录表';