package com.lawu.eshop.jobs.service;

import java.util.List;

import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveParam;

/**
 * 用户支出和消费记录扩展服务接口
 * 
 * @author jiangxinjun
 * @createDate 2017年7月3日
 * @updateDate 2017年11月16日
 */
public interface ReportUserIncomeExpenditureExtendService {
    
    /**
     * 定时任务<p>
     * 保存用户支出和消费记录
     * 
     * @author jiangxinjun
     * @createDate 2017年11月16日
     * @updateDate 2017年11月16日
     */
    List<ReportUserIncomeExpenditureSaveParam> selecteUserIncomeExpenditureRecord(int offset, int pageSize);
    
    /**
     * 定时任务<p>
     * 保存用户支出和消费记录
     * 
     * @author jiangxinjun
     * @ceateDate 2017年7月3日
     * @updateDate 2017年11月16日
     */
	void executeSave(List<ReportUserIncomeExpenditureSaveParam> paramList);
	
}
