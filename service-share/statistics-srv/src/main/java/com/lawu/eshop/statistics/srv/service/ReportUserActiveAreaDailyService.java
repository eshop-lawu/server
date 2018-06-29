package com.lawu.eshop.statistics.srv.service;

import com.lawu.eshop.statistics.dto.UserActiveDTO;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public interface ReportUserActiveAreaDailyService {

    void saveUserActiveAreaDaily(List<UserActiveDTO> userActiveDTOS);

    void saveMerchantActiveAreaDaily(List<UserActiveDTO> userActiveDTOS);
    
    Date getDaily();
}
