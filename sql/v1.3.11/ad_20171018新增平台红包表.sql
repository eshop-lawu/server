CREATE TABLE `platform_red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `send_count` int(8) DEFAULT '0' COMMENT '发送红包总数',
  `status` tinyint(2) DEFAULT '1' COMMENT '红包状态 0-禁用 1-启用',
  `auditor_id` bigint(20) unsigned NOT NULL  COMMENT '操作人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='平台红包表';

CREATE TABLE `take_platform_red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT NULL COMMENT '会员编号',
  `red_packet_id` bigint(20) unsigned NOT NULL COMMENT '红包ID',
  `point` decimal(10,2) unsigned NOT NULL COMMENT '领取金额',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='领取平台红包记录表';

