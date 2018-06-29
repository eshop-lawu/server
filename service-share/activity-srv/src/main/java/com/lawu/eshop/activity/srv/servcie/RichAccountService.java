package com.lawu.eshop.activity.srv.servcie;

import com.lawu.eshop.activity.param.ReceiveDiamondsParam;
import com.lawu.eshop.activity.srv.bo.IdentityInfoBO;
import com.lawu.eshop.activity.srv.bo.PersonalRichAccountBO;
import com.lawu.eshop.activity.srv.bo.RichDetailBO;
import com.lawu.eshop.activity.srv.bo.RichMyDiamondRecordInfoBO;
import com.lawu.eshop.activity.srv.bo.RichPowerInfoRecordBO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/** 
 * 瑞奇岛账户service
 * @author lihj
 * @date 2018年5月2日
 */
public interface RichAccountService {

    /**
     * 获取个人E钻信息
     * @param userNum
     * @return
     */
    PersonalRichAccountBO getPersonalRichAccountInfo(String userNum,UserTypeEnum userType);

    /**
     * 获取首页瑞奇岛信息
     * @return
     */
	RichDetailBO getRichInfo(UserTypeEnum userType);

	/**
	 * 获取瑞奇岛当前动力值以及动力收支明细TOP10
	 * @param userNum
	 * @return
	 */
	RichPowerInfoRecordBO getRichPowerInfoRecord(String userNum);

	/**
	 * 我的数字资产(E钻总数和领取记录TOP10)
	 * @param userNum
	 * @return
	 */
	RichMyDiamondRecordInfoBO getMyRichDiamondRecordInfo(String userNum);
	
	
	/**
	 * 我的身份
	 * 
	 * @param memberNum
	 * @return
	 */
	IdentityInfoBO getIdentityInfo(String memberNum);
	
	/**
     * 领取钻石
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月4日
     * @updateDate 2018年5月4日
     */
    void receiveDiamonds(String memberNum, ReceiveDiamondsParam param);
    
    /**
     * 支付宝授权绑定
     * @param memberNum
     * @param alipayUserId
     */
    void bindAlipayInfo(String memberNum,String alipayUserId);
}
