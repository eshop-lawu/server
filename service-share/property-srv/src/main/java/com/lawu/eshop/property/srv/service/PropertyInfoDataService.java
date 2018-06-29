package com.lawu.eshop.property.srv.service;

import com.lawu.eshop.property.param.PointDetailQueryData1Param;
import com.lawu.eshop.property.param.PropertyInfoDataParam;

/**
 * 
 * <p>
 * Description: 与积分相关业务处理
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午12:53:13
 *
 */
public interface PropertyInfoDataService {

	/**
	 * 业务消费减积分
	 * @param param
	 * @return
	 */
	int doHanlderMinusPoint(PropertyInfoDataParam param);
	
	/**
	 * 邀请粉丝减积分
	 * @param param
	 * @return
	 */
	Long doHanlderMinusPointByFans(PropertyInfoDataParam param);
	
	/**
	 * 业务消费加积分
	 * @param param
	 * @return
	 */
	int doHanlderAddPoint(PropertyInfoDataParam param);
	
	/**
	 * 用户收入加余额，计算爱心账户： 用户点广告/抢赞/抢红包
	 * @param param
	 * @return
	 */
	int doHanlderBalanceIncome(PropertyInfoDataParam param);

	/**
	 * 根据user_num、point_type、biz_id查询积分明细记录
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年6月15日 下午12:08:37
	 */
	Integer getPointDetailByUserNumAndPointTypeAndBizId(PointDetailQueryData1Param param);

	/**
	 * 消费积分兑换抽奖
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	int doHanlderMinusPointWithLottery(PropertyInfoDataParam param);

	/**
	 * 加余额
	 * @param param
	 * @return
	 */
	int doHanlderAddBalance(PropertyInfoDataParam param);
}
