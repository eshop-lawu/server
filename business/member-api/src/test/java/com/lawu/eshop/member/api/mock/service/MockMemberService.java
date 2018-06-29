package com.lawu.eshop.member.api.mock.service;/**
 * Created by ${Yangqh} on 2017/7/24.
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.user.constants.UserSexEnum;
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
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockMemberService extends BaseController implements MemberService {
    @Override
    public Result<LoginUserDTO> find(@PathVariable("account") String account, @RequestParam("pwd") String pwd) {
        LoginUserDTO dto = new LoginUserDTO();
        dto.setNum("M00001");
        dto.setId(1L);
        dto.setAccount("13800138000");
        dto.setRyToken("434444444444444444");
        dto.setUserSex(UserSexEnum.SEX_FEMALE);
        return successCreated(dto);
    }

    @Override
    public Result<UserDTO> findMemberInfo(@PathVariable("memberId") Long memberId) {
        UserDTO dto = new UserDTO();
        dto.setRegionPath("1/101/10101");
        dto.setNickname("fdfsafa");
        dto.setHeadimg("/head/1.jpg");
        dto.setLevel(1);
        dto.setAccount("1383838481");
        dto.setGrade((byte) 5);
        return successCreated(dto);
    }

    @Override
    public Result updateMemberInfo(@ModelAttribute UserParam memberParam, @PathVariable("id") Long id) {
        return null;
    }

    @Override
    public Result updateLoginPwd(@PathVariable("id") Long id, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd) {
        return successCreated();
    }

    @Override
    public Result resetLoginPwd(@PathVariable("mobile") String mobile, @RequestParam("newPwd") String newPwd) {
        return successCreated();
    }

    @Override
    public Result<Page<EfriendDTO>> findMemberListByUser(@RequestParam("userId") Long id, @RequestBody MemberQuery query, @RequestParam("inviterType") Byte inviterType) {
        EfriendDTO dto = new EfriendDTO();
        List<EfriendDTO> list = new ArrayList<>();
        list.add(dto);
        Page<EfriendDTO> page = new Page();
        page.setCurrentPage(1);
        page.setTotalCount(100);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result register(@ModelAttribute RegisterRealParam registerRealParam) {
        return successCreated();
    }

    @Override
    public Result<MemberDTO> getMemberByAccount(@PathVariable("account") String account) {
        MemberDTO dto = new MemberDTO();
        dto.setRegionPath("1/101/10101");
        return successCreated(dto);
    }

    @Override
    public Result<UserHeadImgDTO> saveHeadImage(@PathVariable("memberId") Long memberId, @RequestParam("headimg") String headimg) {
        return successCreated();
    }

    @Override
    public CashUserInfoDTO findCashUserInfo(@PathVariable("id") Long id) {
        CashUserInfoDTO dto = new CashUserInfoDTO();
        dto.setName("张三丰");
        dto.setProvinceId(1);
        dto.setCityId(101);
        dto.setAreaId(10101);
        dto.setRegionFullName("广东省深圳市南山区");
        return dto;
    }

    @Override
    public Result setGtAndRongYunInfo(@PathVariable("id") Long id, @RequestParam("cid") String cid) {
        return successCreated();
    }

    @Override
    public Result<MemberInfoForShoppingOrderDTO> getMemberInfoForShoppingOrder(@PathVariable("id") Long id) {
        return null;
    }

    @Override
    public Result<MemberDTO> findMemberInfoById(@PathVariable("memberId") Long memberId) {
        return null;
    }

    @Override
    public Result<UserRedPacketDTO> isRegister(@RequestParam("moblie") String moblie) {
        UserRedPacketDTO dto = new UserRedPacketDTO();
        dto.setMemberId(1L);
        dto.setUserNum("M00001");
        return successCreated(dto);
    }

    @Override
    public Result<RongYunDTO> getRongYunInfoByNum(@PathVariable("num") String num) {
        return successCreated();
    }

    @Override
    public Result<Boolean> isExistsMobile(@RequestParam("mobile") String mobile) {
        return successCreated(true);
    }

    @Override
    public Result delUserGtPush(@RequestParam("memberId") Long memberId) {
        return successCreated();
    }

    @Override
    public Result<List<MemberDTO>> getMemberByIds(@RequestParam("memberIds") List<Long> memberIds) {
        MemberDTO dto = new MemberDTO();
        dto.setId(1L);
        dto.setHeadimg("/head/1.jpg");
        dto.setMobile("13800138000");
        dto.setRegionPath("1/101/10101");
        List<MemberDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<MemberMineInfoDTO> findMemberMineInfo(@PathVariable("memberId") Long memberId) {
        MemberMineInfoDTO dto = new MemberMineInfoDTO();
        dto.setHeadimg("1.jpg");
        dto.setInviteMemberCount(1);
        dto.setInviteMerchantCount(1);
        dto.setLevel(1);
        dto.setNickname("nimei");
        return successCreated(dto);
    }

    @Override
    public Result<AdQueryMemberInfoDTO> adQueryMemberInfo(@PathVariable("memberId") Long memberId) {
        AdQueryMemberInfoDTO dto = new AdQueryMemberInfoDTO();
        dto.setRegionPath("1/101/10101");
        List<Long> list = new ArrayList<>();
        dto.setFansList(list);
        return successCreated(dto);
    }

    @Override
    public VisitUserInfoDTO findUserAccountAndRegionPathByNum(@PathVariable("userNum") String userNum) {
        VisitUserInfoDTO dto = new VisitUserInfoDTO();
        dto.setRegionPath("1/11/11");
        dto.setAccount("17512036361");
        return dto;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Result freezeAccount(FreezeParam param) {
        return successCreated();
    }

	@Override
	public Result<LoginUserDTO> smsLogin(String account) {
		return null;
	}

    @Override
    public Result setLoginPwd(@RequestParam("userNum") String userNum, @RequestParam("newPwd") String newPwd) {
        return null;
    }

    @Override
    public Result<UserDTO> getMemberByNum(@RequestParam("num") String num) {
        UserDTO dto = new UserDTO();
        dto.setAccount("13666666666");
        dto.setNickname("test");
        return successGet(dto);
    }

    @Override
    public Result<String> getRichTaskInviterNum(@RequestParam("userNum") String userNum) {
        return null;
    }

    @Override
    public Result<UserSexAgeDTO> getMemberSexAge(@PathVariable("memberId") Long memberId) {
        return null;
    }
}
