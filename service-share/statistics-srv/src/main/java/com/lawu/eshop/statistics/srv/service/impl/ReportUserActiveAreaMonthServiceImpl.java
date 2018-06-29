package com.lawu.eshop.statistics.srv.service.impl;

import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;
import com.lawu.eshop.statistics.srv.converter.UserActiveConverter;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaMonthDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveAreaMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveAreaMonthService;
import com.lawu.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
@Service
public class ReportUserActiveAreaMonthServiceImpl implements ReportUserActiveAreaMonthService {

    @Autowired
    private ReportUserActiveAreaMonthDOMapper reportUserActiveAreaMonthDOMapper;
    
    @Override
    public List<ReportUserActiveAreaMonthBO> getReportUserActiveAreaMonthList(String reportDate) {

        ReportUserActiveAreaMonthDOExample example = new ReportUserActiveAreaMonthDOExample();
        example.createCriteria().andGmtReportEqualTo(DateUtil.formatDate(reportDate,"yyyy-MM"));
        List<ReportUserActiveAreaMonthDO> areaMonthDOS = reportUserActiveAreaMonthDOMapper.selectByExample(example);
        List<ReportUserActiveAreaMonthBO> reportUserActiveBOS = UserActiveConverter.coverReportAreaMonthBOS(areaMonthDOS);
        return reportUserActiveBOS;
    }

    @Override
    public void saveUserActiveAreaMonth(List<UserActiveDTO> userActiveDTOS) {
        for (UserActiveDTO userActiveDTO : userActiveDTOS) {
            ReportUserActiveAreaMonthDO  reportUserActiveAreaMonthDO = new ReportUserActiveAreaMonthDO();
            reportUserActiveAreaMonthDO.setCityName(userActiveDTO.getCityName());
            reportUserActiveAreaMonthDO.setGmtReport(DateUtil.getFirstDayOfMonth(userActiveDTO.getVisitDate()));
            reportUserActiveAreaMonthDO.setGmtCreate(new Date());
            reportUserActiveAreaMonthDO.setMemberCount(userActiveDTO.getUserCount());
            reportUserActiveAreaMonthDO.setCityId(userActiveDTO.getCityId());
            reportUserActiveAreaMonthDOMapper.insertSelective(reportUserActiveAreaMonthDO);
        }
    }

    @Override
    public void saveMerchantActiveAreaMonth(List<UserActiveDTO> list) {
        for (UserActiveDTO userActiveDTO : list) {
            //查询城市ID是在当天是否存在记录
            ReportUserActiveAreaMonthDOExample example = new ReportUserActiveAreaMonthDOExample();
            example.createCriteria().andCityIdEqualTo(userActiveDTO.getCityId()).andGmtReportEqualTo(userActiveDTO.getVisitDate());
            List<ReportUserActiveAreaMonthDO> oldActiveAreaMonthDOS = reportUserActiveAreaMonthDOMapper.selectByExample(example);
            if (oldActiveAreaMonthDOS.isEmpty()) {
                //不存在新增一条统计记录
                ReportUserActiveAreaMonthDO  reportUserActiveAreaMonthDO = new ReportUserActiveAreaMonthDO();
                reportUserActiveAreaMonthDO.setCityName(userActiveDTO.getCityName());
                reportUserActiveAreaMonthDO.setGmtReport(DateUtil.getFirstDayOfMonth(userActiveDTO.getVisitDate()));
                reportUserActiveAreaMonthDO.setGmtCreate(new Date());
                reportUserActiveAreaMonthDO.setMerchantCount(userActiveDTO.getUserCount());
                reportUserActiveAreaMonthDO.setCityId(userActiveDTO.getCityId());
                reportUserActiveAreaMonthDOMapper.insertSelective(reportUserActiveAreaMonthDO);
            } else {
                //存在的话更新统计记录
                ReportUserActiveAreaMonthDO userActiveAreaMonthDO = new ReportUserActiveAreaMonthDO();
                userActiveAreaMonthDO.setMerchantCount(userActiveDTO.getUserCount());
                userActiveAreaMonthDO.setId(oldActiveAreaMonthDOS.get(0).getId());
                reportUserActiveAreaMonthDOMapper.updateByPrimaryKeySelective(userActiveAreaMonthDO);
            }

        }
    }
    
    @Override
	public Date getMonth() {
    	ReportUserActiveAreaMonthDOExample example = new ReportUserActiveAreaMonthDOExample();
		example.setOrderByClause("gmt_report desc");
		List<ReportUserActiveAreaMonthDO> list = reportUserActiveAreaMonthDOMapper.selectByExample(example);
		if(list != null && !list.isEmpty())
			return list.get(0).getGmtReport();
		return null;
	}
}
