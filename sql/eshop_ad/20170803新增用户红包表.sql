CREATE TABLE `user_red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) NOT NULL COMMENT '用户num',
  `user_account` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `type` tinyint(2) DEFAULT NULL COMMENT '红包类型[0普通红包,1拼手气红包]',
  `total_count` int(8) DEFAULT NULL COMMENT '红包总数',
  `total_money` decimal(18,4) DEFAULT NULL COMMENT '总金额',
  `status` tinyint(2) DEFAULT NULL COMMENT '红包状态 1有效 2领取完 3过期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户红包表';

CREATE TABLE `user_taked_red_packet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) DEFAULT NULL COMMENT '领取用户num',
  `user_red_pack_id` bigint(20) DEFAULT NULL COMMENT '红包id',
  `type` tinyint(2) DEFAULT NULL COMMENT '红包类型',
  `ordinal` int(8) DEFAULT NULL COMMENT '序号',
  `money` decimal(18,4) DEFAULT NULL COMMENT '分配的金额',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态(0未分配，1已分配，2过期)',
  `taked_time` datetime DEFAULT NULL COMMENT '红包领取时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户红包分配表';