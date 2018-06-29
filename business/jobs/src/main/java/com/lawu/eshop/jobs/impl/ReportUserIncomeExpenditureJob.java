package com.lawu.eshop.jobs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.jobs.service.ReportUserIncomeExpenditureExtendService;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveParam;
import com.lawu.jobsextend.AbstractWholePageJob;
import com.lawu.jobsextend.JobsExtendPageException;

/**
 * 定时任务统计用户支出和消费记录
 * 
 * @author jiangxinjun
 * @createDate 2017年7月4日
 * @updateDate 2017年11月16日
 */
public class ReportUserIncomeExpenditureJob extends AbstractWholePageJob<ReportUserIncomeExpenditureSaveParam> {
    
    @Autowired
    private ReportUserIncomeExpenditureExtendService reportUserIncomeExpenditureExtendService;
    
    @Override
    public List<ReportUserIncomeExpenditureSaveParam> queryPage(int offset, int pageSize) {
        return reportUserIncomeExpenditureExtendService.selecteUserIncomeExpenditureRecord(offset, pageSize);
    }
    
    @Override
    public void executePage(List<ReportUserIncomeExpenditureSaveParam> dataPage) throws JobsExtendPageException {
        reportUserIncomeExpenditureExtendService.executeSave(dataPage);
    }
    
    @Override
    public boolean isStatusData() {
        return false;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }
    /*
	private static Logger logger = LoggerFactory.getLogger(ReportUserIncomeExpenditureJob.class);

	@Autowired
	private ReportUserIncomeExpenditureExtendService reportUserIncomeExpenditureExtendService;

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

		reportUserIncomeExpenditureExtendService.executeSave();

		logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	}
	*/
}
