/**
 *
 */
package com.lawu.eshop.ad.srv.service;

import com.lawu.eshop.ad.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.ad.param.UserPacketRefundParam;
import com.lawu.eshop.ad.param.UserRedPacketSaveParam;
import com.lawu.eshop.ad.param.UserRedPacketSelectNumParam;
import com.lawu.eshop.ad.param.UserRedPacketUpdateParam;
import com.lawu.eshop.ad.srv.bo.UserRedPacketAddReturnBO;
import com.lawu.eshop.ad.srv.bo.UserRedPacketBO;
import com.lawu.eshop.ad.srv.domain.extend.UserRedpacketMaxMoney;
import com.lawu.framework.core.page.Page;

/**
 * 用户红包Service
 * @author lihj
 * @date 2017年8月3日
 */
public interface UserRedPacketService {

	/**
	 *
	 * @param param
	 * @return
	 */
	public UserRedPacketAddReturnBO addUserRedPacket(UserRedPacketSaveParam param);

	/**
	 * 查询用户红包列表
	 * @param param
	 * @return
	 */
	Page<UserRedPacketBO> selectUserRedPacketList(UserRedPacketSelectNumParam param);

	/**
	 * @param redPacketId
	 * @return
	 */
	boolean isExistsRedPacket(Long redPacketId);

	@Deprecated

	/**
	 * 用户领取红包
	 * @param redPacketId
	 * @param userNum
	 */
	UserRedpacketMaxMoney getUserRedpacketMoney(Long redPacketId, String userNum);

	/**
	 * 获取最大的红包金额
	 * @param redPacketId
	 * @return
	 */
	UserRedpacketMaxMoney getUserRedpacketMaxMoney(Long redPacketId);

	/**
	 * 根据红包ID 获取红包金额、和orderNum支付时调用第三方用
	 * @param redPacketId
	 * @return
	 */
	ThirdPayCallBackQueryPayOrderDTO selectUserRedPacketInfoForThrid(Long redPacketId);

	/**
	 * 第三方回调更新
	 * @param paran
	 * @return
	 */
	boolean updateUserPacketInfo(UserRedPacketUpdateParam paran);

	UserPacketRefundParam selectBackTotalMoney(Long userRedpacketId);

	void executeUserRedPacketData(Integer pageSize);

	boolean checkUserGetRedpacket(Long redPacketId, String userNum);
}
