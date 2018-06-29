CREATE TABLE `ad_region` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家ID',
  `ad_id` bigint(20) unsigned NOT NULL COMMENT '广告ID',
  `region_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='广告区域表';