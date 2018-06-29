package com.lawu.eshop.jobs.service;

import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
@FeignClient(value = "statistics-srv")
public interface UserActiveService {

    @RequestMapping(value = "userActive/saveUserActiveDaily", method = RequestMethod.POST)
    Result saveUserActiveDaily(@RequestParam(value = "memberCount") Integer memberCount,
                               @RequestParam(value = "merchantCount") Integer merchantCount,
                               @RequestParam(value = "reportDate") String reportDate);

    @RequestMapping(value = "userActive/saveUserActiveMonth", method = RequestMethod.POST)
    Result saveUserActiveMonth(@RequestParam(value = "memberCount") Integer memberCount,
                               @RequestParam(value = "merchantCount") Integer merchantCount,
                               @RequestParam(value = "reportDate") String reportDate);
    
    @RequestMapping(value = "userActive/saveUserActiveAreaDaily", method = RequestMethod.POST)
    Result saveUserActiveAreaDaily(@RequestBody List<UserActiveDTO> activeDTOS);

    @RequestMapping(value = "userActive/saveMerchantActiveAreaDaily", method = RequestMethod.POST)
    Result saveMerchantActiveAreaDaily(@RequestBody List<UserActiveDTO> activeDTOS);

    @RequestMapping(value = "userActive/saveUserActiveAreaMonth", method = RequestMethod.POST)
    Result saveUserActiveAreaMonth(@RequestBody List<UserActiveDTO> model);

    @RequestMapping(value = "userActive/saveMerchantActiveAreaMonth", method = RequestMethod.POST)
    Result saveMerchantActiveAreaMonth(@RequestBody List<UserActiveDTO> list);
    
    @RequestMapping(value = "userActive/getMemberActiveDaily", method = RequestMethod.GET)
    Date getMemberActiveDaily();
    
    @RequestMapping(value = "userActive/getMemberActiveMonth", method = RequestMethod.GET)
    Date getMemberActiveMonth();
    
    @RequestMapping(value = "userActive/getAreaDaily", method = RequestMethod.GET)
    Date getAreaDaily();
    
    @RequestMapping(value = "userActive/getAreaMonth", method = RequestMethod.GET)
    Date getAreaMonth();
}
