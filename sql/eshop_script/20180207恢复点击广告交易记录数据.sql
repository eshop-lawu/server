-- 场景描述
-- 广告点击表、主事务表、从数据表数据正常，广告点击交易记录缺失

-- 1、删除从事务表相关数据（共2188条记录）
-- member_ad_record、transaction_detail关联查询（条件为广告id、用户编号、日期yyyy-mm-dd）
-- 关联查询得到的member_ad_record则为正常记录，否则为不正常记录
-- 查询不正常member_ad_record的主事务表eshop_ad.transaction_record，并删除关联的eshop_property.follow_transaction_record记录
DELETE FROM eshop_property.follow_transaction_record WHERE topic = 'ad_srv' AND transation_id IN (

		SELECT id FROM eshop_ad.transaction_record WHERE type = 4 AND relate_id IN (

      SELECT id FROM eshop_ad.member_ad_record AS ad_record WHERE ad_record.id NOT IN (

          SELECT record.id FROM eshop_ad.member_ad_record AS record
          LEFT JOIN eshop_property.transaction_detail AS detail ON record.ad_id = detail.biz_id
          AND record.member_num = detail.user_num
          AND DATE_FORMAT(record.click_date, '%Y-%m-%d') = DATE_FORMAT(detail.gmt_create, '%Y-%m-%d')
          WHERE (detail.transaction_type = 23 OR detail.transaction_type = 24)
          AND (detail.gmt_create BETWEEN '2018-02-06 00:00:00' AND '2018-02-08 00:00:00')
        )
      AND (
        ad_record.click_date BETWEEN '2018-02-06 00:00:00' AND '2018-02-08 00:00:00'
      )
    )
);

-- 2、将主事务表的成功标志改为0，以便定时任务重新调度（共2188条记录）
-- 此步骤实际为第1个步骤的子查询
UPDATE eshop_ad.transaction_record SET is_processed = 0 WHERE type = 4 AND relate_id IN (

  SELECT id FROM eshop_ad.member_ad_record AS ad_record WHERE ad_record.id NOT IN (

      SELECT record.id FROM eshop_ad.member_ad_record AS record
      LEFT JOIN eshop_property.transaction_detail AS detail ON record.ad_id = detail.biz_id
      AND record.member_num = detail.user_num
      AND DATE_FORMAT(record.click_date, '%Y-%m-%d') = DATE_FORMAT(detail.gmt_create, '%Y-%m-%d')
      WHERE (detail.transaction_type = 23 OR detail.transaction_type = 24)
      AND (detail.gmt_create BETWEEN '2018-02-06 00:00:00' AND '2018-02-08 00:00:00')
    )
  AND (
    ad_record.click_date BETWEEN '2018-02-06 00:00:00' AND '2018-02-08 00:00:00'
  )
);