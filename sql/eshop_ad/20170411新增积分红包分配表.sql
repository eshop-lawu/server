CREATE TABLE `point_pool` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家ID',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员ID',
  `ad_id` bigint(20) unsigned NOT NULL COMMENT '广告ID',
  `type` tinyint(2) NOT NULL COMMENT '类型(1-抢赞2-红包)',
  `ordinal` int(8) unsigned NOT NULL COMMENT '序号',
  `point` decimal(10,2) unsigned NOT NULL COMMENT '分配的金额',
  `status` tinyint(2) NOT NULL COMMENT '状态(0-未分配1-已分配)',
  `taked_time` datetime DEFAULT NULL COMMENT '分配时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='积分红包分配表';