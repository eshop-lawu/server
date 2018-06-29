use eshop_mall;
CREATE TABLE `window_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_path` varchar(200) NOT NULL COMMENT '弹窗图片',
  `relate_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '关联id',
  `type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '类型',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '状态：1--启用，2--禁用',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='弹窗消息';