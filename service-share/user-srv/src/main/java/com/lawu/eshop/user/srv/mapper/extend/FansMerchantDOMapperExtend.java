package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lawu.eshop.user.param.ListFansRealParam;
import com.lawu.eshop.user.param.ListInviteFansRealParam;
import com.lawu.eshop.user.param.ListInviteFansRealWithContentParam;
import com.lawu.eshop.user.param.UserFansParam;
import com.lawu.eshop.user.srv.domain.FansMerchantDO;
import com.lawu.eshop.user.srv.domain.extend.FansMerchantDOReportView;
import com.lawu.eshop.user.srv.domain.extend.FansMerchantDOView;
import com.lawu.eshop.user.srv.domain.extend.FensCountView;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
public interface FansMerchantDOMapperExtend {

	List<FansMerchantDOView> listInviteFans(ListInviteFansRealParam listInviteFansRealParam);

	List<FansMerchantDOView> listInviteFansWithContent(ListInviteFansRealWithContentParam ListInviteFansRealWithContentParam);
	
	List<FansMerchantDOView> pageListInviteFans(ListInviteFansRealParam listInviteFansRealParam);

	int countInviteFans(ListInviteFansRealParam listInviteFansRealParam);

	List<FansMerchantDOView> listFans(ListFansRealParam listFansRealParam);

	int countFans(ListFansRealParam listFansRealParam);

	/**
	 * 粉丝增长量报表
	 * 
	 * @param formatDate
	 * @return
	 * @author yangqh
	 * @date 2017年5月2日 下午3:25:50
	 */
	List<FansMerchantDOReportView> fansRiseRate(@Param("formatDate") String formatDate, @Param("flag") Byte flag,
			@Param("merchantId") Long merchantId);

	/**
	 * 粉丝增长来源报表
	 * 
	 * @param merchantId
	 * @return
	 * @author yangqh
	 * @date 2017年5月2日 下午7:55:54
	 */
	List<FansMerchantDOReportView> fansRiseSource(@Param("merchantId") Long merchantId);

	/**
	 * 查询过期的粉丝数量
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	Integer countOverdueFans(UserFansParam param);

	/**
	 * 删除过期的粉丝
	 *
	 * @param param
	 * @author meishuquan
	 */
	void delOverdueFans(UserFansParam param);

	/**
	 * 批量新增粉丝记录
	 *
	 * @param fansMerchantDOS
	 * @author meishuquan
	 */
	void batchInsertFansMerchant(List<FansMerchantDO> fansMerchantDOS);

	/**
	 * 性别 ，年龄筛选查询分数总数
	 * @param view
	 * @return
	 */
	Integer findMerchantFensCount(FensCountView view);

}
