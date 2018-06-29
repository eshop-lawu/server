package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MessagePushDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/24.
 */
@FeignClient(value = "user-srv")
public interface MemberService {

    /**
     * 查询所有用户userNum，cid
     * @return
     */
    @RequestMapping(value = "member/findMessagePushList", method = RequestMethod.GET)
    Result<List<MessagePushDTO>> findMessagePushList(@RequestParam(value = "area",required = false) String area);

    @RequestMapping(value = "member/findMessagePushByMobile", method = RequestMethod.GET)
    MessagePushDTO findMessagePushByMobile(@RequestParam("moblie") String moblie);

    /**
     * 根据账号查询会员信息
     *
     * @param account 会员账号
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "member/getMember/{account}")
    Result<MemberDTO> getMemberByAccount(@PathVariable("account") String account);

    /**
     * 根据编号查询会员信息
     *
     * @param num
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "member/getMemberByNum")
    Result<UserDTO> getMemberByNum(@RequestParam("num") String num);

    @RequestMapping(method = RequestMethod.GET, value = "member/getMemberAccountById")
    String getMemberAccountById(@RequestParam("memberId") Long memberId);
    
    
    @RequestMapping(value = "member/findMember/{memberId}", method = RequestMethod.GET)
    Result<MemberDTO> findMember(@PathVariable("memberId") Long memberId);

    @RequestMapping(value = "member/getAccountList", method = RequestMethod.POST)
    Result<Page<AccountDTO>> getAccountList(@RequestBody  AccountParam param);

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "member/freezeAccount", method = RequestMethod.PUT)
    Result freezeAccount(@RequestBody FreezeParam param);

    @RequestMapping(value = "member/delUserGtPush", method = RequestMethod.PUT)
    Result delUserGtPush(@RequestParam("memberId") Long memberId);
}
