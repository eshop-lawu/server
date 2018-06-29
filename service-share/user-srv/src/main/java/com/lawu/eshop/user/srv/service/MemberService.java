package com.lawu.eshop.user.srv.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.AreasCountQuery;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.MemberQuery;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.eshop.user.param.UserParam;
import com.lawu.eshop.user.srv.bo.CashUserInfoBO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MessagePushBO;
import com.lawu.eshop.user.srv.bo.RongYunBO;
import com.lawu.framework.core.page.Page;

/**
 * 会员服务接口
 *
 * @author Leach
 * @date 2017/3/13
 */
public interface MemberService {

    /**
     * 查询会员信息
     *
     * @param account 登录账号
     * @param pwd     密码
     * @return
     */
    MemberBO find(String account, String pwd);

    /**
     * 查询会员个人资料
     *
     * @param id 会员id
     * @return
     */
    MemberBO findMemberInfoById(Long id);

    int updateMemberInfo(UserParam memberParam, Long id);

    /**
     * 修改登录密码
     *
     * @param id     主键
     * @param newPwd 新密码
     * @return
     */
    void updateLoginPwd(Long id, String newPwd);

    /**
     * 根据账号查询会员信息
     *
     * @param account 会员账号
     * @return
     */
    MemberBO getMemberByAccount(String account);

    /**
     * @return
     * @author zhangrc
     * @date 2017/03/23
     * 查询我的E友
     */
    Page<MemberBO> findMemberListByUser(Long inviterId, MemberQuery memberQuery, byte inviterType);

    /**
     * 会员注册
     *
     * @param registerRealParam 会员注册信息
     */
    void register(RegisterRealParam registerRealParam);

    /**
     * 根据会员ID查询会员信息
     *
     * @param id 会员ID
     */
    MemberBO getMemberById(Long id);

    /**
     * 修改头像
     *
     * @param headimg
     * @param mermberId
     */
    void updateMemberHeadImg(String headimg, Long mermberId);

    /**
     * 用户、商家提现时根据商家ID获取账号、名称、省市区信息冗余到提现表中
     *
     * @param id
     * @return
     * @author Yangqh
     */
    CashUserInfoBO findCashUserInfo(@PathVariable("id") Long id);

    /**
     * 根据区域查询用户数量
     *
     * @param regionPath
     * @return
     */
    Integer findMemberCount(AreasCountQuery query);

    Integer setGtAndRongYunInfo(Long id, String cid);

    MemberBO findMemberByNum(String userNum);

    List<MessagePushBO> findMessagePushList(String area);

    MessagePushBO findMessagePushByMobile(String moblie);


    
    /**
     * 根据手机号判断用户是否注册
     * @param mobile
     * @return
     */
    MemberBO isRegister(String mobile);

    /**
     * 根据会员编号查询会员信息
     *
     * @param num
     * @return
     */
    MemberBO getMemberByNum(String num);

    /**
     * 根据会员编号查询融云需要的信息
     *
     * @param num
     * @return
     */
    RongYunBO getRongYunInfoByNum(String num);
    
    /**
     * 判断会员手机号是否存在
     * @param mobile
     * @return
     */
    Boolean isExistsMobile(String mobile);


    int delUserGtPush(Long memberId);
    
    List<MemberBO> getMemberByIds(List<Long> memberIds);

    String getMemberAccountById(Long memberId);

    Integer getTotalCount();

    Page<MemberBO> getAccountList(AccountParam param);

    /**
     * 冻结用户账号
     * @param param
     * @author jiangxinjun
     * @createDate 2018年1月18日
     * @updateDate 2018年1月18日
     */
    void freezeAccount(FreezeParam param);

    /**
     * 保存会员登录日志
     *
     * @param loginLogParam
     * @author meishuquan
     */
    void saveLoginLog(UserLoginLogParam loginLogParam);

    /**
     * 更新用户等级
     * @param userNum
     * @param resultMoney
     * @param userCurrentGrade
     */
    void updateGradeInfoByUserNum(String userNum, Integer resultMoney, Byte userCurrentGrade);

    /**
     * 短信登录
     * @param account
     * @return
     */
    MemberBO smsLogin(String account);

    int setLoginPwd(String userNum, String newPwd);

    /**
     * 是否完善资料
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    Boolean isFinishInformation(String userNum);
    
    /**
     * 通过用户编号集合<p>
     * 查找头脑PK所需要的用户信息
     * 
     * @param userNums
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    List<MemberBO> findUserInfoForGameMind(List<String> userNums);

    /**
     * 获取完成E友邀请人的编号
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    String getRichTaskInviterNum(String userNum);

}
