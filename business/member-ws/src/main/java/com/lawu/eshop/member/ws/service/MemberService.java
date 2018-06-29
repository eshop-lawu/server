package com.lawu.eshop.member.ws.service;

import java.util.List;

import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 会员服务接口
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@FeignClient(value = "user-srv", path = "member/")
public interface MemberService {

    /**
     * 通过用户编号集合<p>
     * 查找头脑PK所需要的用户信息
     * 
     * @param userNums
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @RequestMapping(value = "findUserInfoForGameMind", method = RequestMethod.GET)
    Result<List<GameUserInfoDTO>> findUserInfoForGameMind(@RequestParam("userNums") List<String> userNums);

    @RequestMapping(method = RequestMethod.GET, value = "getMemberByNum")
    Result<UserDTO> getMemberByNum(@RequestParam("num") String num);
}
