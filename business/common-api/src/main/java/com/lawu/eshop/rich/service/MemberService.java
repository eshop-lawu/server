package com.lawu.eshop.rich.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.AdQueryMemberInfoDTO;
import com.lawu.eshop.user.dto.CashUserInfoDTO;
import com.lawu.eshop.user.dto.EfriendDTO;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MemberInfoForShoppingOrderDTO;
import com.lawu.eshop.user.dto.MemberMineInfoDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.dto.UserHeadImgDTO;
import com.lawu.eshop.user.dto.UserRedPacketDTO;
import com.lawu.eshop.user.dto.UserSexAgeDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.MemberQuery;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.UserParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author Leach
 * @date 2017/3/13
 */
@FeignClient(value = "user-srv", path = "member/")
public interface MemberService {

    /**
     * 查询会员信息
     *
     * @param account 登录账号
     * @param pwd     密码
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "withPwd/{account}")
    Result<LoginUserDTO> find(@PathVariable("account") String account, @RequestParam("pwd") String pwd);

    /**
     * 会员资料查询
     *
     * @param memberId 会员id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "findMemberInfo/{memberId}")
    Result<UserDTO> findMemberInfo(@PathVariable("memberId") Long memberId);

    /**
     * 会员资料修改
     *
     * @param memberParam 会员信息
     */
    @RequestMapping(method = RequestMethod.PUT, value = "updateMemberInfo/{id}")
    Result updateMemberInfo(@ModelAttribute UserParam memberParam, @PathVariable("id") Long id);

    /**
     * 修改密码
     *
     * @param id          主键
     * @param originalPwd 原始密码
     * @param newPwd      新密码
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "updateLoginPwd/{id}")
    Result updateLoginPwd(@PathVariable("id") Long id, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd);

    /**
     * 重置密码
     *
     * @param mobile 账号
     * @param newPwd 新密码
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "resetLoginPwd/{mobile}")
    Result resetLoginPwd(@PathVariable("mobile") String mobile, @RequestParam("newPwd") String newPwd);

    /**
     * 查询我的E友
     *
     * @return
     * @author zhangrc
     * @date 2017/03/23
     */
    @RequestMapping(method = RequestMethod.POST, value = "findMemberListByUser")
    Result<Page<EfriendDTO>> findMemberListByUser(@RequestParam("userId") Long id, @RequestBody MemberQuery query, @RequestParam("inviterType") Byte inviterType);

    /**
     * 会员注册
     *
     * @param registerRealParam 会员注册信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "register")
    Result register(@ModelAttribute RegisterRealParam registerRealParam);

    /**
     * 根据账号查询会员信息
     *
     * @param account 会员账号
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "getMember/{account}")
    Result<MemberDTO> getMemberByAccount(@PathVariable("account") String account);

    /**
     * 修改头像
     *
     * @param memberId
     * @param headimg
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "saveHeadImage/{memberId}")
    Result<UserHeadImgDTO> saveHeadImage(@PathVariable("memberId") Long memberId, @RequestParam("headimg") String headimg);

    /**
     * 用户、商家提现时根据商家ID获取账号、名称、省市区信息冗余到提现表中
     *
     * @param id
     * @return
     * @author Yangqh
     */
    @RequestMapping(method = RequestMethod.GET, value = "findCashUserInfo/{id}")
    CashUserInfoDTO findCashUserInfo(@PathVariable("id") Long id);

    /**
     * 增加推送、融云 CID，token
     *
     * @param id
     * @param cid
     * @return
     */
    @RequestMapping(value = "setGtAndRongYunInfo/{id}", method = RequestMethod.PUT)
    Result setGtAndRongYunInfo(@PathVariable("id") Long id, @RequestParam("cid") String cid);

    /**
     * 创建商品订单需要添加用户的一些信息
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "getMemberInfoForShoppingOrder/{id}", method = RequestMethod.GET)
    Result<MemberInfoForShoppingOrderDTO> getMemberInfoForShoppingOrder(@PathVariable("id") Long id);

    /**
     * 查询用户信息
     *
     * @param memberId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "findMember/{memberId}")
    Result<MemberDTO> findMemberInfoById(@PathVariable("memberId") Long memberId);

    /**
     * 查询用户是否注册
     *
     * @param moblie
     * @return
     */
    @RequestMapping(value = "isRegister", method = RequestMethod.GET)
    Result<UserRedPacketDTO> isRegister(@RequestParam("moblie") String moblie);

    /**
     * 根据会员编号查询融云需要的信息
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "getRongYunInfo/{num}", method = RequestMethod.GET)
    Result<RongYunDTO> getRongYunInfoByNum(@PathVariable("num") String num);

    /**
     * 判断手机号是否存在
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "isExistsMobile", method = RequestMethod.GET)
    Result<Boolean> isExistsMobile(@RequestParam("mobile") String mobile);

    @RequestMapping(value = "delUserGtPush", method = RequestMethod.PUT)
    Result delUserGtPush(@RequestParam("memberId") Long memberId);

    /**
     * 广告top3排行榜
     *
     * @param memberIds
     * @return
     */
    @RequestMapping(value = "getMemberByIds", method = RequestMethod.GET)
    Result<List<MemberDTO>> getMemberByIds(@RequestParam("memberIds") List<Long> memberIds);

    /**
     * 返回我的页面所需要的资料
     *
     * @param memberId
     * @return
     * @author Sunny
     * @date 2017年6月16日
     */
    @RequestMapping(value = "findMemberMineInfo/{memberId}", method = RequestMethod.GET)
    Result<MemberMineInfoDTO> findMemberMineInfo(@PathVariable("memberId") Long memberId);

    /**
     * 查询广告所需要的用户信息
     *
     * @return
     * @author jiangxinjun
     * @date 2017年7月18日
     */
    @RequestMapping(value = "adQueryMemberInfo/{memberId}", method = RequestMethod.GET)
    Result<AdQueryMemberInfoDTO> adQueryMemberInfo(@PathVariable("memberId") Long memberId);

    /**
     * 根据用户编号获取用户账号和区域路径
     *
     * @param userNum
     * @return
     */
    @RequestMapping(value = "findUserAccountAndRegionPathByNum", method = RequestMethod.GET)
    VisitUserInfoDTO findUserAccountAndRegionPathByNum(@RequestParam("userNum") String userNum);

    /**
     * 冻结账号
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "freezeAccount", method = RequestMethod.PUT)
    Result freezeAccount(@RequestBody FreezeParam param);

    /**
     * 短信登录
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "smsLogin/{account}", method = RequestMethod.GET)
    Result<LoginUserDTO> smsLogin(@PathVariable("account") String account);

    /**
     * 设置登陆密码
     *
     * @param userNum
     * @param newPwd
     * @return
     */
    @RequestMapping(value = "setLoginPwd", method = RequestMethod.PUT)
    Result setLoginPwd(@RequestParam("userNum") String userNum, @RequestParam("newPwd") String newPwd);

    @RequestMapping(method = RequestMethod.GET, value = "getMemberByNum")
    Result<UserDTO> getMemberByNum(@RequestParam("num") String num);

    /**
     * 获取完成E友邀请人的编号
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "getRichTaskInviterNum")
    Result<String> getRichTaskInviterNum(@RequestParam("userNum") String userNum);

    /**
     * 查询用户性别年龄信息
     *
     * @param memberId
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "getMemberSexAge/{memberId}")
    Result<UserSexAgeDTO> getMemberSexAge(@PathVariable("memberId") Long memberId);

}
