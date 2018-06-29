package com.lawu.eshop.statistics.srv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.statistics.dto.ReportUserActiveAreaDTO;
import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.eshop.statistics.dto.UserActiveListDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveBO;
import com.lawu.eshop.statistics.srv.bo.UserActiveBO;
import com.lawu.eshop.statistics.srv.converter.UserActiveConverter;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveAreaDailyService;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveAreaMonthService;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveDailyService;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveMonthService;
import com.lawu.eshop.statistics.srv.service.UserActiveService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
@RestController
@RequestMapping(value = "userActive/")
public class UserActiveController extends BaseController{

    @Autowired
    private UserActiveService userActiveService;

    @Autowired
    private ReportUserActiveMonthService reportUserActiveMonthService;

    @Autowired
    private ReportUserActiveDailyService reportUserActiveDailyService;


    @Autowired
    private ReportUserActiveAreaDailyService reportUserActiveAreaDailyService;

    @Autowired
    private ReportUserActiveAreaMonthService reportUserActiveAreaMonthService;

    @RequestMapping(value = "collectionMemberActiveDaily", method = RequestMethod.GET)
    public Result<Integer> collectionMemberActiveDaily(@RequestParam(value = "reportDate") String  reportDate){
        Integer count = userActiveService.collectionMemberActiveDaily(DateUtil.getDateFormat(reportDate));
        return successGet(count);
    }

    @RequestMapping(value = "collectionMerchantActiveDaily", method = RequestMethod.GET)
    public Result<Integer> collectionMerchantActiveDaily(@RequestParam(value = "reportDate") String  reportDate){
        Integer count = userActiveService.collectionMerchantActiveDaily(DateUtil.getDateFormat(reportDate));
        return successGet(count);
    }

    @RequestMapping(value = "saveUserActiveDaily", method = RequestMethod.POST)
    public Result saveUserActiveDaily(@RequestParam(value = "memberCount") Integer memberCount,
                               @RequestParam(value = "merchantCount") Integer merchantCount,
                               @RequestParam(value = "reportDate") String reportDate){
        reportUserActiveDailyService.saveUserActiveDaily(memberCount, merchantCount, reportDate);
        return  successCreated();
    }

    @RequestMapping(value = "collectionMemberActiveMonth", method = RequestMethod.GET)
    public Result<Integer> collectionMemberActiveMonth(@RequestParam(value = "reportDate") String reportDate){
        Integer count = userActiveService.collectionMemberActiveMonth(DateUtil.getDateFormat(reportDate));
        return successGet(count);
    }

    @RequestMapping(value = "collectionMerchantActiveMonth", method = RequestMethod.GET)
    public Result<Integer> collectionMerchantActiveMonth(@RequestParam(value = "reportDate") String reportDate){
        Integer count = userActiveService.collectionMerchantActiveMonth(DateUtil.getDateFormat(reportDate));
        return successGet(count);
    }

    @RequestMapping(value = "saveUserActiveMonth", method = RequestMethod.POST)
    Result saveUserActiveMonth(@RequestParam(value = "memberCount") Integer memberCount,
                               @RequestParam(value = "merchantCount") Integer merchantCount,
                               @RequestParam(value = "reportDate") String reportDate){
        reportUserActiveMonthService.saveUserActiveMonth(memberCount, merchantCount, reportDate);
        return  successCreated();
    }

    @RequestMapping(value = "collectionMemberActiveAreaDaily", method = RequestMethod.GET)
    public Result<List<UserActiveDTO>> collectionMemberActiveAreaDaily(@RequestParam(value = "reportDate") String reportDate) {

        List<UserActiveBO> userActiveBOS = userActiveService.collectionMemberActiveAreaDaily(reportDate);
        List<UserActiveDTO> userActiveDTOS = UserActiveConverter.coverDTOS(userActiveBOS);
        return successGet(userActiveDTOS);

    }

    @RequestMapping(value = "collectionMerchantActiveAreaDaily", method = RequestMethod.GET)
    public Result<List<UserActiveDTO>> collectionMerchantActiveAreaDaily(@RequestParam(value = "reportDate") String reportDate) {
        List<UserActiveBO> userActiveBOS = userActiveService.collectionMerchantActiveAreaDaily(reportDate);
        List<UserActiveDTO> userActiveDTOS = UserActiveConverter.coverDTOS(userActiveBOS);
        return successGet(userActiveDTOS);
    }

    @RequestMapping(value = "saveUserActiveAreaDaily", method = RequestMethod.POST)
    public Result saveUserActiveAreaDaily(@RequestBody List<UserActiveDTO> userActiveDTOS){
        reportUserActiveAreaDailyService.saveUserActiveAreaDaily(userActiveDTOS);
        return  successCreated();
    }

    @RequestMapping(value = "saveMerchantActiveAreaDaily", method = RequestMethod.POST)
    public Result saveMerchantActiveAreaDaily(@RequestBody List<UserActiveDTO> userActiveDTOS){
        reportUserActiveAreaDailyService.saveMerchantActiveAreaDaily(userActiveDTOS);
        return  successCreated();
    }


    /**
     * 查询一天活跃用户记录列表（按时间）
     * @param beginTime,endTime
     * @return
     */
    @RequestMapping(value = "getUserActiveListDaily",method = RequestMethod.GET)
    public Result<List<UserActiveListDTO>> getUserActiveListDaily(@RequestParam(required = false)  String beginTime,
                                                                  @RequestParam(required = false)  String endTime){

        List<ReportUserActiveBO> listBOS = reportUserActiveDailyService.getUserActiveListDaily(beginTime, endTime);
        List<UserActiveListDTO> listDTOS = UserActiveConverter.coverReportDTOS(listBOS);
        return successGet(listDTOS);
    }

    /**
     * 查询一个月活跃用户记录列表（按时间）
     * @param beginTime,endTime
     * @return
     */
    @RequestMapping(value = "getUserActiveListMonth",method = RequestMethod.GET)
    public Result<List<UserActiveListDTO>> getUserActiveListMonth(@RequestParam(required = false) String beginTime,
                                                                  @RequestParam(required = false) String endTime){
        List<ReportUserActiveBO> listBOS =  reportUserActiveDailyService.getUserActiveListMonth(beginTime,endTime);
        List<UserActiveListDTO> listDTOS = UserActiveConverter.coverReportDTOS(listBOS);
        return successGet(listDTOS);
    }

    /**
     * 运营平台根据时间查询一天活跃用户（按地区）
     * @param reportDate
     * @return
     */
    @RequestMapping(value = "getReportUserActiveAreaDailyList", method = RequestMethod.GET)
    public  Result<List<ReportUserActiveAreaDTO>> getReportUserActiveAreaDailyList(@RequestParam(value = "reportDate") String reportDate) {

        List<ReportUserActiveAreaDailyBO> listBOS = reportUserActiveDailyService.getReportUserActiveAreaDailyList(reportDate);
        List<ReportUserActiveAreaDTO> list = UserActiveConverter.coverReportAreaDTOS(listBOS);
        return successGet(list);
    }


    /**
     * 运营平台根据时间查询一个月活跃用户（按地区 ）
     * @param reportDate
     * @return
     */
    @RequestMapping(value = "getReportUserActiveAreaMonthList", method = RequestMethod.GET)
    public Result<List<ReportUserActiveAreaDTO>> getReportUserActiveAreaMonthList(@RequestParam(value = "reportDate") String reportDate) {

        List<ReportUserActiveAreaMonthBO> listBOS = reportUserActiveAreaMonthService.getReportUserActiveAreaMonthList(reportDate);
        List<ReportUserActiveAreaDTO> list = UserActiveConverter.coverReportAreaMonthDTOS(listBOS);
        return successGet(list);
    }

    /**
     * 查询地区按月活跃会员用户总数
     * @return
     */
    @RequestMapping(value = "collectionMemberActiveAreaMonth", method = RequestMethod.GET)
    public Result<List<UserActiveDTO>> collectionMemberActiveAreaMonth(@RequestParam(value = "reportDate") String  reportDate){
        List<UserActiveBO> listBOS = userActiveService.collectionMemberActiveAreaMonth(reportDate);
        List<UserActiveDTO> userActiveDTOS = UserActiveConverter.coverDTOS(listBOS);
        return successGet(userActiveDTOS);
    }

    /**
     * 用户活跃统计报表记录新增用户总数(地区按月)
     * @param userActiveDTOS
     * @return
     */
    @RequestMapping(value = "saveUserActiveAreaMonth", method = RequestMethod.POST)
    public Result saveUserActiveAreaMonth(@RequestBody List<UserActiveDTO> userActiveDTOS){
        reportUserActiveAreaMonthService.saveUserActiveAreaMonth(userActiveDTOS);
        return successCreated();
    }

    /**
     * 查询地区按月活跃商家总数
     * @return
     */
    @RequestMapping(value = "collectionMerchantActiveAreaMonth", method = RequestMethod.GET)
    public Result<List<UserActiveDTO>> collectionMerchantActiveAreaMonth(@RequestParam(value = "reportDate") String  reportDate){
        List<UserActiveBO> listBOS = userActiveService.collectionMerchantActiveAreaMonth(reportDate);
        List<UserActiveDTO> userActiveDTOS = UserActiveConverter.coverDTOS(listBOS);
        return successGet(userActiveDTOS);
    }

    /**
     * 用户活跃统计报表记录新增商家总数(地区已经存在记录更新)(地区按月)
     * @param list
     * @return
     */
    @RequestMapping(value = "saveMerchantActiveAreaMonth", method = RequestMethod.POST)
    public Result saveMerchantActiveAreaMonth(@RequestBody List<UserActiveDTO> list){
        reportUserActiveAreaMonthService.saveMerchantActiveAreaMonth(list);
        return successCreated();
    }

    /**
     * 按地区查询日统计活跃用户列表
     * @param param
     * @return
     */
    @RequestMapping(value ="getAgentUserActiveListDaily" ,method = RequestMethod.POST)
    public Result<List<UserActiveListDTO>> getAgentUserActiveListDaily(@RequestBody AgentReportParam param){
        List<ReportUserActiveAreaDailyBO> listBOS = reportUserActiveDailyService.getAgentUserActiveListDaily(param);
        List<UserActiveListDTO> listDTOS = UserActiveConverter.coverAgentReportDTOS(listBOS);
        return successGet(listDTOS);
    }

    /**
     * 按地区查询月统计活跃用户列表
     * @param param
     * @return
     */
    @RequestMapping(value = "getAgentUserActiveListMonth", method = RequestMethod.POST)
    public Result<List<UserActiveListDTO>> getAgentUserActiveListMonth(@RequestBody AgentReportParam param) {

        List<ReportUserActiveAreaMonthBO> listBOS = reportUserActiveDailyService.getAgentUserActiveListMonth(param);
        if (listBOS.isEmpty()) {
            return successGet(new ArrayList<>());
        }
        List<UserActiveListDTO> list = new ArrayList<>();
        UserActiveListDTO activeListDTO;
        for (ReportUserActiveAreaMonthBO activeAreaMonthBO : listBOS) {
            activeListDTO = new UserActiveListDTO();
            activeListDTO.setMerchantCount(activeAreaMonthBO.getMerchantCount());
            activeListDTO.setMemberCount(activeAreaMonthBO.getMemberCount());
            activeListDTO.setGmtReport(activeAreaMonthBO.getGmtReport());
            list.add(activeListDTO);
        }
        return successGet(list);
    }

    @RequestMapping(value = "getMemberActiveDaily", method = RequestMethod.GET)
    Date getDaily() {
    	Date date = userActiveService.getMemberActiveDaily();
    	return date;
    }
    
    @RequestMapping(value = "getMemberActiveMonth", method = RequestMethod.GET)
    Date getMonth() {
    	Date date = userActiveService.getMemberActiveMonth();
    	return date;
    }
    
    @RequestMapping(value = "getAreaDaily", method = RequestMethod.GET)
    Date getAreaDaily() {
    	Date date = reportUserActiveAreaDailyService.getDaily();
    	return date;
    }
    
    @RequestMapping(value = "getAreaMonth", method = RequestMethod.GET)
    Date getAreaMonth() {
    	Date date = reportUserActiveAreaMonthService.getMonth();
    	return date;
    }
    
}
