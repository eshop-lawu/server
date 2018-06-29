package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.AbnormalAccountDTO;
import com.lawu.eshop.user.param.AbnormalJobParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/1/19.
 */
@FeignClient(value = "user-srv")
public interface AbnormalAccountService {

    @RequestMapping(value = "memberProfile/abnormalMemberList", method = RequestMethod.POST)
    Result<List<AbnormalAccountDTO>> abnormalMemberList(@ModelAttribute AbnormalJobParam param);

    @RequestMapping(value = "memberProfile/memberSameIpCount/{id}", method = RequestMethod.GET)
    Result<List<Integer>> memberSameIpCount(@PathVariable("id") Long id, @RequestParam("type") Byte type);

    @RequestMapping(value = "merchantProfile/merchantSameIpCount/{id}", method = RequestMethod.GET)
    Result<List<Integer>> merchantSameIpCount(@PathVariable("id") Long id, @RequestParam("type") Byte type);

    @RequestMapping(value = "merchantProfile/abnormalMerchantList", method = RequestMethod.POST)
    Result<List<AbnormalAccountDTO>> abnormalMerchantList(@ModelAttribute AbnormalJobParam param);

    @RequestMapping(value = "memberProfile/inviteMemberTotalCount/{id}", method = RequestMethod.GET)
    Integer inviteMemberTotalCount(@PathVariable("id") Long id, @RequestParam("type") Byte type);

    @RequestMapping(value = "merchantProfile/inviteMerchantTotalCount/{id}", method = RequestMethod.GET)
    Integer inviteMerchantTotalCount(@PathVariable("id") Long id, @RequestParam("type") Byte type);
}
