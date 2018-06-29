package com.lawu.eshop.statistics.srv.service.impl;

import com.lawu.eshop.statistics.srv.domain.ReportUserActiveMonthDO;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveMonthService;
import com.lawu.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
@Service
public class ReportUserActiveMonthServiceImpl implements ReportUserActiveMonthService {

    @Autowired
    private ReportUserActiveMonthDOMapper reportUserActiveMonthDOMapper;
    
    @Override
    public void saveUserActiveMonth(Integer memberCount, Integer merchantCount, String reportDate) {
        ReportUserActiveMonthDO userActiveMonthDO = new ReportUserActiveMonthDO();
        userActiveMonthDO.setMemberCount(memberCount);
        userActiveMonthDO.setMerchantCount(merchantCount);
        userActiveMonthDO.setGmtCreate(new Date());
        userActiveMonthDO.setGmtReport(DateUtil.getDateFormat(reportDate));
        reportUserActiveMonthDOMapper.insertSelective(userActiveMonthDO);
    }
}
