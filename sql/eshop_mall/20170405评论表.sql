CREATE TABLE `comment_product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(500) DEFAULT NULL COMMENT '评价内容',
  `reply_content` varchar(500) DEFAULT NULL COMMENT '商家回复内容',
  `grade` tinyint(2) NOT NULL COMMENT '评分1~5星',
  `member_id` bigint(20) NOT NULL COMMENT '用户',
  `product_id` bigint(20) NOT NULL COMMENT '商品',
  `is_anonymous` tinyint(1) NOT NULL COMMENT '是否匿名（0：否1：是）',
  `status` tinyint(2) NOT NULL COMMENT '状态（1：有效0：无效）',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_reply` datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) COMMENT='评论商品表';

CREATE TABLE `comment_merchant` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(500) DEFAULT NULL COMMENT '评价内容',
  `reply_content` varchar(500) DEFAULT NULL COMMENT '商家回复内容',
  `grade` tinyint(2) NOT NULL COMMENT '评分',
  `member_id` bigint(20) NOT NULL COMMENT '用户',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家',
  `is_anonymous` tinyint(1) NOT NULL COMMENT '是否匿名（0：否1：是）',
  `status` tinyint(2) NOT NULL COMMENT '状态（1：有效0：无效）',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_reply` datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) COMMENT='评论商家表';

CREATE TABLE `comment_image` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `img_url` varchar(100) NOT NULL COMMENT '图片路径',
  `comment_id` bigint(20) NOT NULL COMMENT '评价id',
  `type` tinyint(1) NOT NULL COMMENT '评论类型 1：商家2：商品',
  `status` tinyint(1) NOT NULL COMMENT '0：删除1：正常',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='评论图片附件';