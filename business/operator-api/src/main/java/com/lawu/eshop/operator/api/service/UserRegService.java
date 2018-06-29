package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.statistics.dto.ReportUserRegAreaDTO;
import com.lawu.eshop.statistics.dto.ReportUserRegDTO;
import com.lawu.eshop.statistics.param.UserRegParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/6/30.
 */
@FeignClient(value = "statistics-srv")
public interface UserRegService {

    /**
     * 查询日统计列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/getReportUserRegDaily", method = RequestMethod.POST)
    Result<List<ReportUserRegDTO>> getReportUserRegDaily(@ModelAttribute UserRegParam param);

    /**
     * 查询月统计列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/getReportUserRegMonth", method = RequestMethod.POST)
    Result<List<ReportUserRegDTO>> getReportUserRegMonth(@ModelAttribute UserRegParam param);

    /**
     * 查询区域统计列表
     *
     * @return
     */
    @RequestMapping(value = "userReg/getReportUserRegArea", method = RequestMethod.GET)
    Result<List<ReportUserRegAreaDTO>> getReportUserRegArea();

}
