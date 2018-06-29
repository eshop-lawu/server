package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.param.HelpRedpacketAttendParam;
import com.lawu.eshop.activity.param.HelpRedpacketDetailOperatorParam;
import com.lawu.eshop.activity.param.HelpRedpacketUserParam;
import com.lawu.eshop.activity.param.SignAbnormalParam;
import com.lawu.eshop.activity.srv.bo.AttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendPageBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketUserBO;
import com.lawu.framework.core.page.Page;
import com.lawu.eshop.activity.srv.bo.IdentifiedAsAbnormalBO;
import com.lawu.eshop.activity.srv.bo.SignAbnormalAccountBO;
import com.lawu.eshop.mq.dto.user.FreezeAccountNotification;

/**
 *
 * @Description 红包活动报名接口
 *
 * @author zhangrc
 * @date 2017年12月28日
 */
public interface HelpRedpacketAttendDetailService {
	/**
	 * 红包活动报名
	 * @param attendParam
	 */

	AttendBO helpRedpacketAttend(HelpRedpacketAttendParam attendParam);
	
	/**
	 * 运营平台报名详情列表
	 * @param detailParam
	 * @return
	 */
	Page<HelpRedpacketAttendPageBO> helpRedpacketAttendPageOperator(HelpRedpacketDetailOperatorParam detailParam);

	/**
	 * 报名详情
	 * @param userNum
	 * @return
	 */
	HelpRedpacketAttendBO helpRedpacketAttendDetail(Integer activityId, String userNum, Integer helpCount);

    /**
     * 根据ID查询，部分信息）
     * @param attendId
     * @return
     */
    HelpRedpacketAttendDetailBO selectByPrimaryKey(Long attendId);

    /**
     * 加助力人数+1
     * @param attendId
     */
    void addHelpCount(Long attendId);

    /**
     * 根据参与人用户编号和活动ID查询
     * @param attendUserNum
     * @return
     */
    HelpRedpacketAttendDetailBO selectByAttendUserNum(Integer activityId, String attendUserNum);
    
    /**
     * 领取红包
     * @param userNum
     * @return
     */
    HelpRedpacketBO getHelpRedpacket(Integer activityId, String userNum);

	/**
	 * 查询待发放的红包详情记录
	 *
	 * @param offset
	 * @param pageSize
	 * @return
	 * @author meishuquan
	 */
	List<HelpRedpacketAttendDetailBO> listSendRedpacketAttendDetail(int offset, int pageSize);
	
    /**
     * 获取已经领取到红包的用户
     * @return
     */
    Page<HelpRedpacketUserBO> getHelpRedpacketUser(HelpRedpacketUserParam param);

    /**
     * 根据用户账号是否冻结,设置该条参与记录是否有效
     *
     * @param freezeAccountNotification
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月1日
     * @updateDate 2018年3月1日
     */
    void settingInvalid(FreezeAccountNotification freezeAccountNotification);

    /**
     * 标识当前用户活动参与记录为异常<p>
     * 以及下级参与记录也标识为异常
     *
     * @param userNums
     * @param activityId
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月1日
     * @updateDate 2018年3月1日
     */
    List<IdentifiedAsAbnormalBO> identifiedAsAbnormal(List<String> userNums, int activityId);

	void executeUpdateActivitySuspectedStatus();

    List<SignAbnormalAccountBO> queryAttendDetailList(SignAbnormalParam param);

    void updateActivityAbnormalStatus(Long id,Byte status);
}
