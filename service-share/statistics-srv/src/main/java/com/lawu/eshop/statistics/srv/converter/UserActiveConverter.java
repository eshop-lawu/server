package com.lawu.eshop.statistics.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.statistics.dto.ReportUserActiveAreaDTO;
import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.eshop.statistics.dto.UserActiveListDTO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveBO;
import com.lawu.eshop.statistics.srv.bo.UserActiveBO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveMonthDO;
import com.lawu.eshop.statistics.srv.domain.extend.UserActiveDOView;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public class UserActiveConverter {
    public static List<UserActiveBO> coverBOS(List<UserActiveDOView> userActiveDOViews) {
        if (userActiveDOViews.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserActiveBO> userActiveBOS = new ArrayList<>();
        for (UserActiveDOView userActiveDOView : userActiveDOViews) {
            UserActiveBO userActiveBO = new UserActiveBO();
            userActiveBO.setCityId(userActiveDOView.getCityId());
            userActiveBO.setCityName(userActiveDOView.getCityName());
            userActiveBO.setVisitDate(userActiveDOView.getVisitDate());
            userActiveBO.setUserCount(userActiveDOView.getUserCount());
            userActiveBOS.add(userActiveBO);
        }

        return userActiveBOS;
    }

    public static List<UserActiveDTO> coverDTOS(List<UserActiveBO> userActiveBOS) {
        if (userActiveBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserActiveDTO> userActiveDTOS = new ArrayList<>();
        for (UserActiveBO userActiveBO : userActiveBOS) {
            UserActiveDTO userActiveDTO = new UserActiveDTO();
            userActiveDTO.setCityId(userActiveBO.getCityId());
            userActiveDTO.setCityName(userActiveBO.getCityName());
            userActiveDTO.setUserCount(userActiveBO.getUserCount());
            userActiveDTO.setVisitDate(userActiveBO.getVisitDate());
            userActiveDTOS.add(userActiveDTO);
        }
        return userActiveDTOS;
    }

    public static List<ReportUserActiveBO> coverReportBOS(List<ReportUserActiveMonthDO> userActiveMonthDOS) {
        if(userActiveMonthDOS.isEmpty()){
            return new ArrayList<>();
        }
        List<ReportUserActiveBO> reportUserActiveBOS = new ArrayList<>();
        for(ReportUserActiveMonthDO reportUserActiveDO : userActiveMonthDOS){
            ReportUserActiveBO reportUserActiveBO = new ReportUserActiveBO();
            reportUserActiveBO.setGmtReport(reportUserActiveDO.getGmtReport());
            reportUserActiveBO.setMerchantCount(reportUserActiveDO.getMerchantCount());
            reportUserActiveBO.setMemberCount(reportUserActiveDO.getMemberCount());
            reportUserActiveBOS.add(reportUserActiveBO);
        }
        return reportUserActiveBOS;
    }

    public static List<UserActiveListDTO> coverReportDTOS(List<ReportUserActiveBO> listBOS) {
        if (listBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserActiveListDTO> listDTOS = new ArrayList<>();

        for (ReportUserActiveBO userActiveBO : listBOS) {
            UserActiveListDTO userActiveListDTO = new UserActiveListDTO();
            userActiveListDTO.setMemberCount(userActiveBO.getMemberCount());
            userActiveListDTO.setGmtReport(userActiveBO.getGmtReport());
            userActiveListDTO.setMerchantCount(userActiveBO.getMerchantCount());
            listDTOS.add(userActiveListDTO);
        }
        return listDTOS;
    }

    public static List<ReportUserActiveAreaDailyBO> coverReportAreaBOS(List<ReportUserActiveAreaDailyDO> areaDailyDOS) {
        if(areaDailyDOS.isEmpty()){
            return new ArrayList<>();
        }
        List<ReportUserActiveAreaDailyBO> reportUserActiveBOS = new ArrayList<>();
        for(ReportUserActiveAreaDailyDO reportUserActiveDO : areaDailyDOS){
            ReportUserActiveAreaDailyBO reportUserActiveBO = new ReportUserActiveAreaDailyBO();
            reportUserActiveBO.setMerchantCount(reportUserActiveDO.getMerchantCount());
            reportUserActiveBO.setMemberCount(reportUserActiveDO.getMemberCount());
            reportUserActiveBO.setGmtReport(reportUserActiveDO.getGmtReport());
            reportUserActiveBO.setCityId(reportUserActiveDO.getCityId());
            reportUserActiveBO.setCityName(reportUserActiveDO.getCityName());
            reportUserActiveBOS.add(reportUserActiveBO);
        }
        return reportUserActiveBOS;
    }

    public static List<ReportUserActiveAreaDTO> coverReportAreaDTOS(List<ReportUserActiveAreaDailyBO> listBOS) {
        if (listBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ReportUserActiveAreaDTO> reportUserActiveDTOS = new ArrayList<>();
        for (ReportUserActiveAreaDailyBO reportUserActiveAreaDailyBO : listBOS) {
            ReportUserActiveAreaDTO reportUserActiveAreaDTO = new ReportUserActiveAreaDTO();
            reportUserActiveAreaDTO.setMerchantCount(reportUserActiveAreaDailyBO.getMerchantCount());
            reportUserActiveAreaDTO.setCityId(reportUserActiveAreaDailyBO.getCityId());
            reportUserActiveAreaDTO.setMemberCount(reportUserActiveAreaDailyBO.getMemberCount());
            reportUserActiveAreaDTO.setCityName(reportUserActiveAreaDailyBO.getCityName());
            reportUserActiveDTOS.add(reportUserActiveAreaDTO);
        }
        return reportUserActiveDTOS;
    }

    public static List<ReportUserActiveAreaMonthBO> coverReportAreaMonthBOS(List<ReportUserActiveAreaMonthDO> areaMonthDOS) {
        if (areaMonthDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ReportUserActiveAreaMonthBO> reportUserActiveBOS = new ArrayList<>();
        for (ReportUserActiveAreaMonthDO reportUserActiveDO : areaMonthDOS) {
            ReportUserActiveAreaMonthBO reportUserActiveBO = new ReportUserActiveAreaMonthBO();
            reportUserActiveBO.setMerchantCount(reportUserActiveDO.getMerchantCount());
            reportUserActiveBO.setMemberCount(reportUserActiveDO.getMemberCount());
            reportUserActiveBO.setGmtReport(reportUserActiveDO.getGmtReport());
            reportUserActiveBO.setCityId(reportUserActiveDO.getCityId());
            reportUserActiveBO.setCityName(reportUserActiveDO.getCityName());
            reportUserActiveBOS.add(reportUserActiveBO);
        }
        return reportUserActiveBOS;
    }

    public static List<ReportUserActiveAreaDTO> coverReportAreaMonthDTOS(List<ReportUserActiveAreaMonthBO> listBOS) {
        if (listBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ReportUserActiveAreaDTO> reportUserActiveDTOS = new ArrayList<>();
        for (ReportUserActiveAreaMonthBO reportUserActiveAreaBO : listBOS) {
            ReportUserActiveAreaDTO reportUserActiveAreaDTO = new ReportUserActiveAreaDTO();
            reportUserActiveAreaDTO.setMerchantCount(reportUserActiveAreaBO.getMerchantCount());
            reportUserActiveAreaDTO.setMemberCount(reportUserActiveAreaBO.getMemberCount());
            reportUserActiveAreaDTO.setCityName(reportUserActiveAreaBO.getCityName());
            reportUserActiveAreaDTO.setCityId(reportUserActiveAreaBO.getCityId());
            reportUserActiveDTOS.add(reportUserActiveAreaDTO);
        }
        return reportUserActiveDTOS;
    }

    public static List<ReportUserActiveBO> coverReportBOSWithDOS(List<ReportUserActiveDailyDO> userActiveDailyDOS) {
        if(userActiveDailyDOS.isEmpty()){
            return new ArrayList<>();
        }
        List<ReportUserActiveBO> reportUserActiveBOS = new ArrayList<>();
        for(ReportUserActiveDailyDO reportUserActiveDOView : userActiveDailyDOS){
            ReportUserActiveBO reportUserActiveBO = new ReportUserActiveBO();
            reportUserActiveBO.setMerchantCount(reportUserActiveDOView.getMerchantCount());
            reportUserActiveBO.setMemberCount(reportUserActiveDOView.getMemberCount());
            reportUserActiveBO.setGmtReport(reportUserActiveDOView.getGmtReport());
            reportUserActiveBOS.add(reportUserActiveBO);
        }
        return reportUserActiveBOS;
    }


    public static List<UserActiveListDTO> coverAgentReportDTOS(List<ReportUserActiveAreaDailyBO> listBOS) {
        if (listBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserActiveListDTO> listDTOS = new ArrayList<>();
        for (ReportUserActiveAreaDailyBO activeAreaDailyBO : listBOS) {
            UserActiveListDTO userActiveListDTO = new UserActiveListDTO();
            userActiveListDTO.setGmtReport(activeAreaDailyBO.getGmtReport());
            userActiveListDTO.setMemberCount(activeAreaDailyBO.getMemberCount());
            userActiveListDTO.setMerchantCount(activeAreaDailyBO.getMerchantCount());
            listDTOS.add(userActiveListDTO);
        }
        return listDTOS;
    }
}
