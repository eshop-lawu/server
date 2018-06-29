package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.dto.AbnormalRedisCountDTO;
import com.lawu.eshop.cache.param.AbnormalInfoParam;
import com.lawu.eshop.cache.param.EarlyAbnormalParam;
import com.lawu.eshop.cache.srv.service.RegMachineService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/1/18.
 */
@RestController
@RequestMapping(value = "regMachine/")
public class RegMachineController extends BaseController{

    @Autowired
    private RegMachineService regMachineService;

    @RequestMapping(value = "addHfRegRedisRecord",method = RequestMethod.POST)
    private Result addHfRegRedisRecord(@RequestBody AbnormalInfoParam param){
        regMachineService.addHfRegRedisRecord(param);
        return successCreated();
    }

    @RequestMapping(value = "getAbnormalCount",method = RequestMethod.GET)
    public Result<AbnormalRedisCountDTO> getAbnormalCount(@RequestParam("userNum") String userNum){
        AbnormalRedisCountDTO countDTO = regMachineService.getAbnormalCount(userNum);
        return successGet(countDTO);
    }

    @RequestMapping(value = "addEarlyHfRedisRecord",method = RequestMethod.POST)
    public Result addEarlyHfRedisRecord(@RequestBody EarlyAbnormalParam param){
        regMachineService.addEarlyHfRedisRecord(param);
        return successCreated();
    }
}
