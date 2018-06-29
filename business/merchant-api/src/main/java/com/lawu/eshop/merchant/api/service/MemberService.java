package com.lawu.eshop.merchant.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.EfriendDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.MemberQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/17.
 */
@FeignClient(value = "user-srv")
public interface MemberService {
    /**
     * 会员资料查询
     *
     * @param memberId 会员id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "member/findMemberInfo/{memberId}")
    Result<UserDTO> findMemberInfo(@PathVariable("memberId") Long memberId);

    /**
     * 查询我的E友
     *
     * @return
     * @author zhangrc
     * @date 2017/03/23
     */
    @RequestMapping(method = RequestMethod.POST, value = "member/findMemberListByUser")
    Result<Page<EfriendDTO>> findMemberListByUser(@RequestParam("userId") Long id, @RequestBody MemberQuery query, @RequestParam("inviterType") byte inviterType);

    /**
     * 根据会员编号查询会员信息
     *
     * @param num
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "member/getMemberByNum")
    Result<UserDTO> getMemberByNum(@RequestParam("num") String num);

    /**
     * 根据会员编号查询融云需要的信息
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "member/getRongYunInfo/{num}", method = RequestMethod.GET)
    Result<RongYunDTO> getRongYunInfoByNum(@PathVariable("num") String num);

    /**
     * 根据用户编号获取用户账号和区域路径
     * @param userNum
     * @return
     */
    @RequestMapping(value = "member/findUserAccountAndRegionPathByNum", method = RequestMethod.GET)
    VisitUserInfoDTO findUserAccountAndRegionPathByNum(@PathVariable("userNum") String userNum);

    /**
     * 根据账号查询会员信息
     *
     * @param account 会员账号
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "member/getMember/{account}")
    Result<MemberDTO> getMemberByAccount(@PathVariable("account") String account);
    
    /**
     * 查询用户信息
     * @param memberIds
     * @return
     */
    @RequestMapping(value = "member/getMemberByIds", method = RequestMethod.GET)
    Result<List<MemberDTO>> getMemberByIds(@RequestParam("memberIds") List<Long> memberIds);

}
