package com.lawu.eshop.statistics.srv.service.impl;

import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaDailyDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveAreaDailyDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveAreaDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
@Service
public class ReportUserActiveAreaDailyServiceImpl implements ReportUserActiveAreaDailyService {

    @Autowired
    private ReportUserActiveAreaDailyDOMapper reportUserActiveAreaDailyDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserActiveAreaDaily(List<UserActiveDTO> userActiveDTOS) {
        ReportUserActiveAreaDailyDO reportUserActiveAreaDailyDO;
        for (UserActiveDTO userActiveDTO : userActiveDTOS) {
            reportUserActiveAreaDailyDO = new ReportUserActiveAreaDailyDO();
            reportUserActiveAreaDailyDO.setCityName(userActiveDTO.getCityName());
            reportUserActiveAreaDailyDO.setGmtReport(userActiveDTO.getVisitDate());
            reportUserActiveAreaDailyDO.setGmtCreate(new Date());
            reportUserActiveAreaDailyDO.setMemberCount(userActiveDTO.getUserCount());
            reportUserActiveAreaDailyDO.setCityId(userActiveDTO.getCityId());
            reportUserActiveAreaDailyDOMapper.insertSelective(reportUserActiveAreaDailyDO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMerchantActiveAreaDaily(List<UserActiveDTO> userActiveDTOS) {
        ReportUserActiveAreaDailyDO reportUserActiveAreaDailyDO;
        for (UserActiveDTO userActiveDTO : userActiveDTOS) {
            //查询城市ID是在当天是否存在记录
            ReportUserActiveAreaDailyDOExample example = new ReportUserActiveAreaDailyDOExample();
            example.createCriteria().andCityIdEqualTo(userActiveDTO.getCityId()).andGmtReportEqualTo(userActiveDTO.getVisitDate());
            List<ReportUserActiveAreaDailyDO> oldActiveAreaDailyDOS = reportUserActiveAreaDailyDOMapper.selectByExample(example);
            if (oldActiveAreaDailyDOS.isEmpty()) {
                //不存在新增一条统计记录
                reportUserActiveAreaDailyDO = new ReportUserActiveAreaDailyDO();
                reportUserActiveAreaDailyDO.setCityName(userActiveDTO.getCityName());
                reportUserActiveAreaDailyDO.setGmtReport(userActiveDTO.getVisitDate());
                reportUserActiveAreaDailyDO.setGmtCreate(new Date());
                reportUserActiveAreaDailyDO.setMerchantCount(userActiveDTO.getUserCount());
                reportUserActiveAreaDailyDO.setCityId(userActiveDTO.getCityId());
                reportUserActiveAreaDailyDOMapper.insertSelective(reportUserActiveAreaDailyDO);
            } else {
                //存在的话更新统计记录
                ReportUserActiveAreaDailyDO userActiveAreaDailyDO = new ReportUserActiveAreaDailyDO();
                userActiveAreaDailyDO.setMerchantCount(userActiveDTO.getUserCount());
                userActiveAreaDailyDO.setId(oldActiveAreaDailyDOS.get(0).getId());
                reportUserActiveAreaDailyDOMapper.updateByPrimaryKeySelective(userActiveAreaDailyDO);
            }

        }
    }

	@Override
	public Date getDaily() {
		ReportUserActiveAreaDailyDOExample example = new ReportUserActiveAreaDailyDOExample();
		example.setOrderByClause("gmt_report desc");
		List<ReportUserActiveAreaDailyDO> list = reportUserActiveAreaDailyDOMapper.selectByExample(example);
		if(list != null && !list.isEmpty())
			return list.get(0).getGmtReport();
		return null;
	}
}
