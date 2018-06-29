package com.lawu.eshop.jobs.impl;

import java.util.ArrayList;
import java.util.List;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.IncomeMsgService;
import com.lawu.eshop.jobs.service.PropertyPointDetailService;
import com.lawu.eshop.jobs.service.PropertyTransactionDetailService;
import com.lawu.eshop.jobs.service.ReportSalesExtendService;
import com.lawu.eshop.property.dto.IncomeMsgDTO;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractWholePageJob;
import com.lawu.jobsextend.JobsExtendPageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 收益通知，个推
 *
 * @author yangqh
 * @date 2017年7月4日
 */
public class IncomeMsgJob extends AbstractWholePageJob<IncomeMsgDTO> {

    private static Logger logger = LoggerFactory.getLogger(IncomeMsgJob.class);

    @Autowired
    private IncomeMsgService incomeMsgService;

    @Autowired
    private PropertyTransactionDetailService propertyTransactionDetailService;
    @Autowired
    private PropertyPointDetailService propertyPointDetailService;

    @Override
    public boolean isStatusData() {
        return false;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return false;
    }

    @Override
    public void executePage(List<IncomeMsgDTO> list) throws JobsExtendPageException {
        incomeMsgService.execute(list);
    }

    @Override
    public List<IncomeMsgDTO> queryPage(int offset, int pageSize) {
        Result<List<IncomeMsgDTO>> transactionDetailListResult = propertyTransactionDetailService.getIncomeMsgDataList(offset,pageSize);
        Result<List<IncomeMsgDTO>> pointDetailListResult = propertyPointDetailService.getIncomeMsgDataList(offset,pageSize);
        List<IncomeMsgDTO> rtnList = new ArrayList<>();
        rtnList.addAll(transactionDetailListResult.getModel());
        rtnList.addAll(pointDetailListResult.getModel());
        return rtnList;
    }
}
