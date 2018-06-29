package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lawu.eshop.user.param.AbnormalJobParam;
import com.lawu.eshop.user.param.SameIpCountParam;
import com.lawu.eshop.user.srv.domain.extend.AbnormalAccountDOView;
import com.lawu.eshop.user.srv.domain.extend.InviteMerchantInfoView;
import com.lawu.eshop.user.srv.domain.extend.MerchantDOView;

/**
 * @author zhangyong
 * @date 2017/6/5.
 */
public interface MerchantDOMapperExtend {

    int delMerchantGtPush(Long merchantId);

    MerchantDOView getMerchantViewById(Long id);

    List<String> selectNumLikeContent(String queryContent);

    /**
     * 我的E友查询商家的信息
     * @param inviteUserNum
     * @return
     */
    List<InviteMerchantInfoView> selectInviteMerchantInfo(String inviteUserNum);

    List<Integer> merchantSameIpCount(SameIpCountParam param);

    List<AbnormalAccountDOView> abnormalMerchantList(AbnormalJobParam param);

    /**
     * 根据用户编号标记改用户所有的一级下线为僵尸账户
     * @param num
     * @author jiangxinjun
     * @createDate 2018年1月24日
     * @updateDate 2018年1月24日
     */
    void updateZombieAccount(@Param("num") String num, @Param("isZombie") boolean isZombie);

    int inviteMerchantTotalCount(SameIpCountParam param);

    MerchantDOView getMerchantChatInfo(String num);
}
