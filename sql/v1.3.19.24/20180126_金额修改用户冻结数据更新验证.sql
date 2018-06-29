-- 注意:只适用于正式环境

-- 1、更新奖励金红包统计金额
update `eshop_ad`.`inviter_bounty` set send_count =12556 where id =1

-- 2、更新指定异常广告退积分主事务执行次数为0，等待执行成功
SELECT * FROM  `eshop_ad`.`transaction_record` where relate_id in (SELECT id from `eshop_ad`.ad where hits != ad_count ) and type =2 and is_processed =0
update `eshop_ad`.`transaction_record` set times =0 where relate_id = 261

-- 3、验证交易记录
select * from  `eshop_property`.`point_detail` where biz_id ='adDown_261'


-- 4、更新所有异常广告退积分主事务执行次数为0，等待执行成功
update `eshop_ad`.`transaction_record` set times =0 where relate_id in (SELECT id from `eshop_ad`.ad where hits != ad_count ) and type =2 and is_processed =0

-- 5、将不需要退还积分的广告设置为已消费
update `eshop_ad`.`transaction_record` set is_processed =1   where relate_id in (SELECT id from `eshop_ad`.ad where hits = ad_count ) and type =2 and is_processed =0

-- 6、更新指定冻结用户冻结类型
update member set frozen_type=2 where is_freeze=1 and num='M949293911698837514'

-- 7、验证上下三级关系是否断开，总8765

SELECT
	id,is_del
FROM
	invite_relation
WHERE
	user_num IN (
		SELECT
			user_num
		FROM
			invite_relation
		WHERE
			invite_user_num = 'M949293911698837514'
		AND depth BETWEEN 0
		AND 3
	)
AND invite_user_num IN (
	SELECT
		invite_user_num
	FROM
		invite_relation
	WHERE
		user_num = 'M949293911698837514'
	AND depth BETWEEN 0
	AND 3
)
AND (
	user_num != 'M949293911698837514'
	OR invite_user_num != 'M949293911698837514'
)

-- 8、验证等级、下三级数量是否更新
-- 原数据
-- M948450543133528083	13637707277	4	18	141	2919	199	13	47	64					2018-01-25 19:36:40	2018-01-03 15:21:34
-- M949293911698837514	18166581922	4	380	2895	0	0	26	0	0					2018-01-20 21:09:17	2018-01-06 09:28:09
SELECT
	member.num,
	member.account,
	member.level,
	member_profile.*
FROM
	member
LEFT JOIN member_profile ON member.id = member_profile.id
WHERE
	is_freeze = 1
AND LEVEL > 1
AND account = '18166581922' or num='M948450543133528083';

-- 9、更新所有冻结用户冻结类型
update member set frozen_type=2 where is_freeze=1