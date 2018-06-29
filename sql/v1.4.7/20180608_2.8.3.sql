-- 2.8.3商家收藏数据迁移至商家粉丝表，只迁移普通店铺
-- 商家粉丝表加唯一索引
use eshop_user;
ALTER TABLE `fans_merchant` ADD UNIQUE INDEX `idx_mer_mem` (`merchant_id`, `member_id`) USING BTREE ;
-- 同步数据
INSERT INTO fans_merchant SELECT
  0 as id,
  favorite_merchant.merchant_id AS merchant_id,
  favorite_merchant.member_id AS member_id,
  1 AS STATUS,
  now() AS gmt_create,
  6 AS channel
FROM
  favorite_merchant
WHERE favorite_merchant.manage_type=1 and
  NOT EXISTS (
    SELECT
      *
    FROM
      fans_merchant
    WHERE
      favorite_merchant.merchant_id = fans_merchant.merchant_id
    AND favorite_merchant.member_id = fans_merchant.member_id
  )
  
  -- 创建备份表并且备份数据
  CREATE TABLE `fans_merchant_bak` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) unsigned NOT NULL COMMENT '商家',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '粉丝状态: 0--邀请了未成为粉丝, 1--成为粉丝',
  `gmt_create` datetime NOT NULL COMMENT '创建日期',
  `channel` tinyint(3) NOT NULL COMMENT '成为粉丝的途径(1-注册|2-邀请|3-买单消费|4-订单消费|5-抢红包|6-关注)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_mer_mem` (`merchant_id`,`member_id`) USING BTREE,
  KEY `idx_merchant_id` (`merchant_id`) USING BTREE,
  KEY `idx_member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商家粉丝';


INSERT into fans_merchant_bak SELECT * from fans_merchant;
delete from fans_merchant where merchant_id in (select merchant_id from merchant_store_profile where manage_type=2)