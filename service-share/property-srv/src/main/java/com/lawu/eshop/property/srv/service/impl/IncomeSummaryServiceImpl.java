package com.lawu.eshop.property.srv.service.impl;

import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.param.BroadcastListParam;
import com.lawu.eshop.property.srv.bo.BroadcastListBO;
import com.lawu.eshop.property.srv.bo.IncomeSummaryBO;
import com.lawu.eshop.property.srv.domain.IncomeDailySummaryDO;
import com.lawu.eshop.property.srv.domain.IncomeDailySummaryDOExample;
import com.lawu.eshop.property.srv.mapper.IncomeDailySummaryDOMapper;
import com.lawu.eshop.property.srv.service.IncomeSummaryService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IncomeSummaryServiceImpl implements IncomeSummaryService {

    @Autowired
    private IncomeDailySummaryDOMapper incomeDailySummaryDOMapper;

    @Override
    public List<IncomeSummaryBO> getIncomeSummary(String userNum) {
        String date = DateUtil.getDateFormat(new Date(),DateUtil.DATE_DEFAULT_FORMAT);
        String begin = date + " 00:00:00";
        String end = date + " 23:59:59";
        IncomeDailySummaryDOExample example = new IncomeDailySummaryDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andGmtCreateBetween(DateUtil.formatDate(begin,DateUtil.DATETIME_DEFAULT_FORMAT),DateUtil.formatDate(end,DateUtil.DATETIME_DEFAULT_FORMAT)).andIsShowEqualTo(false);
        List<IncomeDailySummaryDO> incomeSummaryDOList = incomeDailySummaryDOMapper.selectByExample(example);
        if(incomeSummaryDOList == null || incomeSummaryDOList.isEmpty()){
            return null;
        }
        List<IncomeSummaryBO> incomeSummaryBOList = new ArrayList<>();
        for(IncomeDailySummaryDO incomeSummaryDO : incomeSummaryDOList){
            IncomeSummaryBO incomeSummaryBO = new IncomeSummaryBO();
            incomeSummaryBO.setMoney(incomeSummaryDO.getMoney());
            incomeSummaryBO.setIncomeType(incomeSummaryDO.getIncomeType());
            incomeSummaryBOList.add(incomeSummaryBO);
        }
        //更新为已播报
        IncomeDailySummaryDO incomeSummaryDO = new IncomeDailySummaryDO();
        incomeSummaryDO.setIsShow(true);
        incomeSummaryDO.setGmtModify(new Date());
        incomeDailySummaryDOMapper.updateByExampleSelective(incomeSummaryDO,example);
        return incomeSummaryBOList;
    }

    @Override
    public Page<BroadcastListBO> getBroadcastList(String userNum, BroadcastListParam broadcastListParam) {
        IncomeDailySummaryDOExample example = new IncomeDailySummaryDOExample();
        example.createCriteria().andUserNumEqualTo(userNum);

        long count = incomeDailySummaryDOMapper.countByExample(example);

        Page<BroadcastListBO> page = new Page<>();
        page.setCurrentPage(broadcastListParam.getCurrentPage());
        page.setTotalCount(new Long(count).intValue());

        if (count <= 0) {
            return page;
        }

        example.setOrderByClause(" gmt_create desc ");
        RowBounds rowBounds = new RowBounds(broadcastListParam.getOffset(), broadcastListParam.getPageSize());
        List<IncomeDailySummaryDO> incomeDailySummaryDOS = incomeDailySummaryDOMapper.selectByExampleWithRowbounds(example,rowBounds);
        List<BroadcastListBO> broadcastListBOList = new ArrayList<>();
        for (IncomeDailySummaryDO incomeDailySummaryDO : incomeDailySummaryDOS){
            BroadcastListBO broadcastListBO = new BroadcastListBO();
            broadcastListBO.setDate(DateUtil.getDateFormat(incomeDailySummaryDO.getGmtReport(),DateUtil.DATE_DEFAULT_FORMAT));
            broadcastListBO.setIncome(incomeDailySummaryDO.getMoney());
            broadcastListBO.setPayTypeEnum(PayTypeEnum.getEnum(incomeDailySummaryDO.getIncomeType()));
            broadcastListBOList.add(broadcastListBO);
        }
        page.setRecords(broadcastListBOList);
        return page;
    }
}
