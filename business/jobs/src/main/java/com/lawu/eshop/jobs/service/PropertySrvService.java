package com.lawu.eshop.jobs.service;

import java.util.List;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.common.param.CommissionJobParam;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.eshop.property.dto.AreaPointConsumeDTO;
import com.lawu.eshop.property.dto.AreaRechargePointDTO;
import com.lawu.eshop.property.dto.PropertyDTO;
import com.lawu.eshop.property.dto.ReportAdEarningsPointDTO;
import com.lawu.eshop.property.dto.ReportAdPointGroupByAreaDTO;
import com.lawu.eshop.property.dto.ReportEarningsDTO;
import com.lawu.eshop.property.dto.TotalSalesGroupByAreaDTO;
import com.lawu.eshop.property.param.CommissionParam;
import com.lawu.eshop.property.param.ReportAdEarningsPointParam;
import com.lawu.eshop.property.param.ReportAdPointParam;
import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.param.TotalSalesQueryParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "property-srv")
public interface PropertySrvService {

    /**
     * 查询系统配置参数
     *
     * @param name
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.GET, value = "property/getValue")
    Result getValue(@RequestParam("name") String name);

    /**
     * 计算上级提成
     *
     * @param param
     * @return
     * @author yangqh
     */
    @RequestMapping(method = RequestMethod.POST, value = "commission/calculation")
    int calculation(@RequestBody CommissionJobParam param);

    /**
     * 用户广告总收益
     *
     * @param reportAdEarningsPointParam
     * @return
     */
    @RequestMapping(value = "reportAdEarningsPoint/getReportAdEarningsPoint", method = RequestMethod.GET)
    Result<ReportAdEarningsPointDTO> getReportAdEarningsPoint(@RequestBody ReportAdEarningsPointParam reportAdEarningsPointParam);

    /**
     * 广告用户  爱心账户收益
     *
     * @param bizIds
     * @return
     */
    @RequestMapping(value = "reportAdEarningsPoint/getReportEarnings", method = RequestMethod.GET)
    Result<ReportEarningsDTO> getReportEarnings(@RequestParam("bizIds") List<Long> bizIds);

    /**
     * 获取时间内的发广告的区域统计
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "pointDetail/getReportAdPointGroupByArea", method = RequestMethod.POST)
    Result<List<ReportAdPointGroupByAreaDTO>> getReportAdPointGroupByArea(@RequestBody ReportAdPointParam param);

    /**
     * 查询指定日期的平台销量(group by area)
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "transactionDetail/selectTotalSalesGroupByArea", method = RequestMethod.POST)
    Result<List<TotalSalesGroupByAreaDTO>> selectTotalSalesGroupByArea(@RequestBody TotalSalesQueryParam param);


    @RequestMapping(value = "pointDetail/getAreaPointConsume", method = RequestMethod.POST)
    Result<List<AreaPointConsumeDTO>> getAreaPointConsume(@RequestBody ReportAgentAreaPointParam param);

    @RequestMapping(value = "pointDetail/getAreaPointRefund", method = RequestMethod.POST)
    Result<List<AreaPointConsumeDTO>> getAreaPointRefund(@RequestBody ReportAgentAreaPointParam param);

    @RequestMapping(value = "recharge/selectAreaRechargePoint", method = RequestMethod.POST)
    Result<List<AreaRechargePointDTO>> selectAreaRechargePoint(@RequestBody ReportAgentAreaPointParam param);

    @RequestMapping(method = RequestMethod.GET, value = "property/getAll")
    Result<List<PropertyDTO>> getAll();

    /**
     * 计算提成（重构）
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "commission/calculationRefactor")
    Result calculationRefactor(@RequestBody CommissionParam param);

}
