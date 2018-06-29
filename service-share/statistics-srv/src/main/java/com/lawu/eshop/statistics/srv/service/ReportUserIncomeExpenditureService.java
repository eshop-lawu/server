package com.lawu.eshop.statistics.srv.service;

import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureQueryParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveWrapperParam;
import com.lawu.eshop.statistics.srv.bo.ReportUserIncomeExpenditureBO;
import com.lawu.framework.core.page.Page;

/**
 * 用户收支服务接口类
 * 
 * @author Sunny
 * @date 2017年6月30日
 */
public interface ReportUserIncomeExpenditureService {

	/**
	 * 保存用户收支记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
    void save(ReportUserIncomeExpenditureSaveParam param);
    
	/**
	 * 保存用户收支记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
    void batchSave(ReportUserIncomeExpenditureSaveWrapperParam param);
    
    /**
     * 分页查询用户收支记录
     * 
     * @param param
     * @return
     * @author Sunny
     * @date 2017年7月3日
     */
    Page<ReportUserIncomeExpenditureBO> page(ReportUserIncomeExpenditureQueryParam param);
}
