package com.lawu.eshop.merchant.api.mock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.merchant.api.service.MemberService;
import com.lawu.eshop.user.dto.EfriendDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.MemberQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockMemberService extends BaseController implements MemberService {
    @Override
    public Result<UserDTO> findMemberInfo(@PathVariable("memberId") Long memberId) {
        UserDTO dto = new UserDTO();
        dto.setNickname("test");
        return successGet(dto);
    }

    @Override
    public Result<Page<EfriendDTO>> findMemberListByUser(@RequestParam("userId") Long id, @RequestBody MemberQuery query, @RequestParam("inviterType") byte inviterType) {
        return successCreated();
    }

    @Override
    public Result<UserDTO> getMemberByNum(@RequestParam("num") String num) {
        UserDTO dto = new UserDTO();
        dto.setNickname("test");
        return successGet(dto);
    }

    @Override
    public Result<RongYunDTO> getRongYunInfoByNum(@PathVariable("num") String num) {
        return successGet();
    }

    @Override
    public VisitUserInfoDTO findUserAccountAndRegionPathByNum(@PathVariable("userNum") String userNum) {
        VisitUserInfoDTO dto = new VisitUserInfoDTO();
        dto.setAccount("11111111111111");
        dto.setRegionPath("1/11/111");
        return dto;
    }

    @Override
    public Result<MemberDTO> getMemberByAccount(@PathVariable("account") String account) {
        MemberDTO dto = new MemberDTO();
        dto.setId(100L);
        dto.setNum("M0001");
        return null;
    }

	@Override
	public Result<List<MemberDTO>> getMemberByIds(List<Long> memberIds) {
		// TODO Auto-generated method stub
		return null;
	}
}
