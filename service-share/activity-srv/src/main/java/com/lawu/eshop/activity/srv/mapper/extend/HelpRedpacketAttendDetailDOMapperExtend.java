package com.lawu.eshop.activity.srv.mapper.extend;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.lawu.eshop.activity.param.AbnormalInviteParam;
import com.lawu.eshop.activity.srv.domain.extend.GetRedpacketAmountParam;
import com.lawu.eshop.activity.srv.domain.extend.HelpRedpacketAttendDetailDOExtend;
import com.lawu.eshop.activity.srv.domain.extend.HelpRedpacketInviteAbnormalDOView;
import com.lawu.eshop.activity.srv.domain.extend.RedpacketAmountParam;
import com.lawu.eshop.activity.srv.domain.extend.UpdateMinRedpacketAmountParam;

public interface HelpRedpacketAttendDetailDOMapperExtend {

    int addHelpCount(HelpRedpacketAttendDetailDOExtend helpRedpacketAttendDetailDOExtend);
    
    /**
     * 通过下标获取某一行的id
     * @param index 下标
     * @param activityId 活动id
     * @param isLucky 是否是大奖
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    int getIdWithIndex(@Param("index") Integer index, @Param("activityId") Integer activityId, @Param("isLucky") Boolean isLucky);
    
    /**
     * 获取预期的红包的总额
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    BigDecimal getExpectedRedpacketAmount(RedpacketAmountParam param);
    
    /**
     * 分配最低红包金额
     * @param param
     * @author jiangxinjun
     * @createDate 2018年1月2日
     * @updateDate 2018年1月2日
     */
    void updateMinRedpacketAmount(UpdateMinRedpacketAmountParam param);
    
    /**
     * 获取已经分配的普通红包总金额
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月4日
     * @updateDate 2018年1月4日
     */
    BigDecimal getRedpacketTotalAmount(GetRedpacketAmountParam param);

    /**
     * 获取领取总金额
     * @return
     */
	BigDecimal getTotalMoney(@Param("activityId") Integer activityId);

    HelpRedpacketInviteAbnormalDOView queryAbnormalInviteRecordDate(AbnormalInviteParam param);
}