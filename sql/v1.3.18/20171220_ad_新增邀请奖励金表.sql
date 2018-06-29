CREATE TABLE `inviter_bounty` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `money` decimal(10,2) NOT NULL COMMENT '金额',
  `send_count` int(8) DEFAULT '0' COMMENT '发送奖励金总个数',
  `status` tinyint(2) DEFAULT '1' COMMENT '奖励金状态 0-禁用 1-启用',
  `auditor_id` bigint(20) unsigned NOT NULL  COMMENT '操作人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='邀请奖励金表';

CREATE TABLE `take_inviter_bounty_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '邀请人编号',
  `red_packet_id` bigint(20) unsigned NOT NULL COMMENT '奖励金ID',
  `point` decimal(10,2) unsigned NOT NULL COMMENT '领取金额',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='邀请奖励金记录表';