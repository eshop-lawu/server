package com.lawu.eshop.merchant.api.event;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.framework.web.impl.event.UserVisitEvent;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.mall.param.ShareFriendsParam;
import com.lawu.eshop.user.param.AliUserInfoDataParam;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.framework.web.constants.VisitConstants;
import com.lawu.framework.web.event.UserVisitEventPublish;
import com.lawu.utils.DataTransUtil;
import com.lawu.utils.IpUtil;

/**
 * @author zhangyong
 * @date 2017/7/4.
 */
@Component
public class EventPublisher implements UserVisitEventPublish {
    @Autowired
    ApplicationContext applicationContext;

    public void publishLoginEvent(HttpServletRequest request, String userNum, String account) {
        String imei = request.getAttribute(VisitConstants.REQUEST_IMEI) == null ? "" : request.getAttribute(VisitConstants.REQUEST_IMEI).toString();
        String platform = request.getAttribute(VisitConstants.REQUEST_PLATFORM) == null ? "" : request.getAttribute(VisitConstants.REQUEST_PLATFORM).toString();
        String platformVer = request.getAttribute(VisitConstants.REQUEST_PLATFORM_VERSION) == null ? "" : request.getAttribute(VisitConstants.REQUEST_PLATFORM_VERSION).toString();
        String appVer = request.getAttribute(VisitConstants.REQUEST_APP_VERSION) == null ? "" : request.getAttribute(VisitConstants.REQUEST_APP_VERSION).toString();
        String cityId = request.getAttribute(VisitConstants.REQUEST_LOCATION_PATH) == null ? "" : request.getAttribute(VisitConstants.REQUEST_LOCATION_PATH).toString();
        String channel = request.getAttribute(VisitConstants.REQUEST_CHANNEL) == null ? "" : request.getAttribute(VisitConstants.REQUEST_CHANNEL).toString();

        UserLoginLogParam loginLogParam = new UserLoginLogParam();
        loginLogParam.setUserNum(userNum);
        loginLogParam.setAccount(account);
        loginLogParam.setUserType(UserTypeEnum.MERCHANT.getValue());
        loginLogParam.setImei(imei);
        loginLogParam.setPlatform(StringUtils.isEmpty(platform) ? DataTransUtil.intToByte(0) : Byte.valueOf(platform));
        loginLogParam.setPlatformVer(platformVer);
        loginLogParam.setAppVer(appVer);
        loginLogParam.setCityId(StringUtils.isEmpty(cityId) ? 0 : Integer.valueOf(cityId));
        loginLogParam.setChannel(channel);
        loginLogParam.setIpAddr(IpUtil.getIpAddress(request));
        applicationContext.publishEvent(new LoginEvent(this, loginLogParam));
    }

    @Override
    public void publishUserVisitEvent(String userNum, Long userId) {
        applicationContext.publishEvent(new UserVisitEvent(this, userNum, UserTypeEnum.MERCHANT, userId));
    }

    public void publishInviteFansSendMessageEvent(MessageInfoParam messageInfoParam, MessageTempParam messageTempParam, String nums) {
        applicationContext.publishEvent(new InviteFansSendMessageEvent(this, messageInfoParam, messageTempParam, nums));
    }

    public void publishRichPowerTaskEvent(RichMerchantPowerTaskRecordParam taskRecordParam) {
        applicationContext.publishEvent(new RichPowerTaskEvent(this, taskRecordParam));
    }

    public void publishShareFriendsEvent(String shareUserNum, ShareFriendsParam param) {
        applicationContext.publishEvent(new ShareFriendsEvent(this, shareUserNum, param));
    }

    /**
     * 发送异步事件获取支付宝用户信息
     *
     * @param aliUserInfoDataParam
     */
    public void publishGetAliUserInfoEvent(AliUserInfoDataParam aliUserInfoDataParam) {
        AlipayUserInfoShareEvent alipayUserInfoShareEvent = new AlipayUserInfoShareEvent(this);
        alipayUserInfoShareEvent.setUserNum(aliUserInfoDataParam.getUserNum());
        alipayUserInfoShareEvent.setUserTypeEnum(aliUserInfoDataParam.getUserTypeEnum());
        alipayUserInfoShareEvent.setAliUserInfoParam(aliUserInfoDataParam.getAliUserInfoParam());
        applicationContext.publishEvent(alipayUserInfoShareEvent);
    }

}
