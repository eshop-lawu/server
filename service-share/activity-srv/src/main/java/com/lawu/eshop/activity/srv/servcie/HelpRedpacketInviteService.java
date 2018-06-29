package com.lawu.eshop.activity.srv.servcie;

import com.lawu.eshop.activity.dto.HelpRedpacketInviteAbnormalDTO;
import com.lawu.eshop.activity.param.AbnormalInviteParam;
import com.lawu.eshop.activity.param.DoHelpDataParam;
import com.lawu.eshop.activity.param.HelpRedpacketInviteRegParam;

/**
 * 红包助力
 *
 * @author yangqh
 * @date 2017年12月28日
 */
public interface HelpRedpacketInviteService {

    /**
     * 登陆助力
     * @param param
     * @return
     */
    int doLoginHelp(DoHelpDataParam param);

    /**
     * 注册助力
     * @param param
     */
    void doRegHelp(HelpRedpacketInviteRegParam param);

    /**
     * 刷新助力好友的头像
     * @param helpUserNum
     * @param helpUserHeadimg
     */
    void updateInviteRecordHeadimg(String helpUserNum, String helpUserHeadimg);

   HelpRedpacketInviteAbnormalDTO queryAbnormalInviteRecord(AbnormalInviteParam param);
}
