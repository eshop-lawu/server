package com.lawu.eshop.user.srv.service;

import java.util.List;

import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.MerchantInviterParam;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.bo.MerchantBaseInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantDetailBO;
import com.lawu.eshop.user.srv.bo.MerchantFreezeInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInviterBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.bo.MessagePushBO;
import com.lawu.eshop.user.srv.bo.RongYunBO;
import com.lawu.eshop.user.srv.domain.extend.MerchantDOView;
import com.lawu.framework.core.page.Page;

/**
 * 商户服务接口
 *
 * @author meishuquan
 * @date 2017/3/22
 */
public interface MerchantService {

    /**
     * 修改登录密码
     *
     * @param id     主键
     * @param newPwd 新密码
     */
    void updateLoginPwd(Long id, String newPwd);

    /**
     * 商家信息
     *
     * @param merchantProfileId
     * @return
     */
    MerchantInfoBO findMerchantInfo(Long merchantProfileId);

    /**
     * 根据账号查询商户信息
     *
     * @param account 商户账号
     * @return
     */
    MerchantBO getMerchantByAccount(String account);

    /**
     * 商户注册
     *
     * @param registerRealParam 商户注册信息
     */
    void register(RegisterRealParam registerRealParam);

    /**
     * 根据商户ID查询商户信息
     *
     * @param id 商户ID
     */
    MerchantBO getMerchantBOById(Long id);

    /**
     * 我推荐的商家
     *
     * @param pageQuery
     * @return
     * @author zhangrc
     * @date 2017/03/27
     */
    Page<MerchantInviterBO> getMerchantByInviter(Long userId, MerchantInviterParam pageQuery,byte  inviterType);

    /**
     * 查询会员信息
     *
     * @param account 登录账号
     * @param pwd     密码
     * @return
     */
    MerchantBO find(String account, String pwd);

    Integer setGtAndRongYunInfo(Long id, String cid);

    MerchantBO findMemberByNum(String userNum);

    /**
     * 商家个人中心信息
     * @param merchantId
     * @return
     */
	MerchantBO selectMerchantInfo(Long merchantId);

    List<MessagePushBO> findMessagePushList(String area);

    MessagePushBO findMessagePushByMobile(String moblie);

    void updateHeadImg(String headimg, Long merchantId);

    /**
     * 根据商家编号查询融云需要的信息
     *
     * @param num
     * @return
     */
    RongYunBO getRongYunInfoByNum(String num);

    /**
     * 根据商家ID查询商家基本信息
     * @param merchantId
     * @return
     * @author yangqh
     * @date 2017年5月22日 上午10:53:28
     */
	MerchantBaseInfoBO getMerchantById(Long merchantId);

    /**
     * 根据编号查询商户信息
     *
     * @param num
     * @return
     */
    MerchantBO getMerchantByNum(String num);
    
    /**
     * 判断商家是否注册
     * @param account
     * @return
     */
    Boolean isRegister(String account);

    int delMerchantGtPush(Long merchantId);

    /**
     * 根据商家ID查询商家基本信息
     *
     * @param id
     * @return
     */
    MerchantDOView getMerchantView(Long id);

    Integer getTotalCount();

    Page<MerchantBO> getAccountList(AccountParam param);

    /**
     * 冻结商家账号
     * @param param 冻结参数
     * @author jiangxinjun
     * @createDate 2018年1月18日
     * @updateDate 2018年1月18日
     */
    void freezeAccount(FreezeParam param);

    /**
     * 根据商家ID查询商家详细信息(包括门店、图片等信息)
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    MerchantDetailBO getMerchantDetailById(Long merchantId);

    /**
     * 保存商家登录日志
     *
     * @param loginLogParam
     * @author meishuquan
     */
    void saveLoginLog(UserLoginLogParam loginLogParam);

    /**
     * 根据id查询商家冻结相关信息
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    MerchantFreezeInfoBO getMerchantFreezeInfo(Long merchantId);

    /**
     * 短信登录
     * @param account
     * @return
     */
    MerchantBO smsLogin(String account);

    /**
     * 获取完成E友邀请人的编号
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    String getRichTaskInviterNum(String userNum);

    MerchantStoreBO getMerchantChatInfo(String userNum);
}
