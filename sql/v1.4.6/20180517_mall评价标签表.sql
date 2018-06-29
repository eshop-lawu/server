use eshop_mall;
CREATE TABLE `comment_sign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '标签名称',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='评价标签表';

