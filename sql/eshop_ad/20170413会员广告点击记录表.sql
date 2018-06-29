CREATE TABLE `member_ad_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员',
  `ad_id` bigint(20) unsigned NOT NULL COMMENT '广告',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='会员广告点击记录';