package com.lawu.eshop.statistics.srv.service;

import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public interface ReportUserActiveAreaMonthService {

    List<ReportUserActiveAreaMonthBO> getReportUserActiveAreaMonthList(String reportDate);

    void saveUserActiveAreaMonth(List<UserActiveDTO> userActiveDTOS);

    void saveMerchantActiveAreaMonth(List<UserActiveDTO> list);
    
    Date getMonth();
}
