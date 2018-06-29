package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.ReportNewDateDTO;
import com.lawu.eshop.statistics.param.UserRegAreaParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
@FeignClient(value = "statistics-srv")
public interface UserRegService {

    /**
     * 按日统计用户注册量
     *
     * @param memberCount
     * @param merchantCount
     */
    @RequestMapping(value = "userReg/saveUserRegDaily", method = RequestMethod.POST)
    Result saveUserRegDaily(@RequestParam("memberCount") Integer memberCount, @RequestParam("merchantCount") Integer merchantCount);

    /**
     * 按月统计用户注册量
     *
     * @param memberCount
     * @param merchantCount
     */
    @RequestMapping(value = "userReg/saveUserRegMonth", method = RequestMethod.POST)
    Result saveUserRegMonth(@RequestParam("memberCount") Integer memberCount, @RequestParam("merchantCount") Integer merchantCount);


    /**
     * 按市级更新用户注册量
     *
     * @param param
     */
    @RequestMapping(value = "userReg/updateUserRegArea", method = RequestMethod.POST)
    Result updateUserRegArea(@RequestBody UserRegAreaParam param);

    /**
     * 新增统计地区日注册量
     * @param userRegAreaParam
     * @return
     */
    @RequestMapping(value = "userReg/addUserRegAreaDaily", method = RequestMethod.POST)
    Result addUserRegAreaDaily(@RequestBody UserRegAreaParam userRegAreaParam);

    /**
     * 新增统计地区月注册量
     * @param userRegAreaParam
     * @return
     */
    @RequestMapping(value = "userReg/addUserRegAreaMonth", method = RequestMethod.POST)
    Result addUserRegAreaMonth(@RequestBody UserRegAreaParam userRegAreaParam);
    
    /**
     * 日统计最新统计时间
     * @return
     */
    @RequestMapping(value = "userReg/getReportDateUserRegDaily", method = RequestMethod.GET)
    Result<ReportNewDateDTO> getReportDateUserRegDaily();
    
    /**
     * 月统计最新统计时间
     * @return
     */
    @RequestMapping(value = "userReg/getReportDateUserRegMonth", method = RequestMethod.GET)
    Result<ReportNewDateDTO> getReportDateUserRegMonth();
}
