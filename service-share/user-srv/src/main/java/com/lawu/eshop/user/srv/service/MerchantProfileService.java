package com.lawu.eshop.user.srv.service;

import java.util.List;

import com.lawu.eshop.user.param.AbnormalJobParam;
import com.lawu.eshop.user.param.MerchantProfileParam;
import com.lawu.eshop.user.srv.bo.AbnormalAccountBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoFromInviteFansBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoFromPublishAdBO;
import com.lawu.eshop.user.srv.bo.MerchantProfileBO;
import com.lawu.eshop.user.srv.bo.MerchantSizeLinkBO;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface MerchantProfileService {

    /**
     * 设置相关网站链接
     * @param merchantProfileParamd
     */
    int updateMerchantSizeLink(MerchantProfileParam merchantProfileParamd, Long id);

    /**
     * 查询商家主页
     * @param merchantProfileId
     * @return
     */
    MerchantProfileBO findMerchantProfileInfo(Long merchantProfileId);

    MerchantSizeLinkBO getMerchantSizeLink(Long merchantId);
    
    /**
     * 查询商家对应的网站链接
     * @param merchantId
     * @return
     */
    MerchantProfileBO  getMerchantProfile(Long merchantId);
    
    /**
     * 商家发广告时需要查询的信息
     * @param merchantId
     * @return
     */
    MerchantInfoFromPublishAdBO getMerchantInfoFromPublishAd(Long merchantId);
    
    /**
     * 商家邀请粉丝需要查询的信息
     * @param merchantId
     * @return
     */
    MerchantInfoFromInviteFansBO getMerchantInfoFromInviteFans(Long merchantId);

    void updateLastLoginTime(Long id);

    List<Integer> merchantSameIpCount(Long id,Byte type);

    List<AbnormalAccountBO> abnormalMerchantList(AbnormalJobParam param);

    int inviteMerchantTotalCount(Long id, Byte type);

    /**
     * 更新为已助力瑞奇岛动力任务
     *
     * @param userNum
     * @author meishuquan
     */
    void updateHelpRichTask(String userNum);
}
