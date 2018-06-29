package com.lawu.eshop.property.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.property.srv.domain.TransactionDetailDOExample;
import com.lawu.eshop.property.srv.domain.extend.IncomeMsgDOView;
import com.lawu.eshop.property.srv.domain.extend.IncomeMsgExample;
import com.lawu.eshop.property.srv.domain.extend.MonthlyBillDO;
import com.lawu.eshop.property.srv.domain.extend.ReportAdEarningsPointView;
import com.lawu.eshop.property.srv.domain.extend.ReportAdPointView;
import com.lawu.eshop.property.srv.domain.extend.TotalSalesDO;
import com.lawu.eshop.property.srv.domain.extend.TotalSalesGroupByAreaDO;
import com.lawu.eshop.property.srv.domain.extend.TotalSalesQueryExample;
import com.lawu.eshop.property.srv.domain.extend.UserIncomeExpenditureDO;
import com.lawu.eshop.property.srv.domain.extend.UserIncomeExpenditureExample;

public interface TransactionDetailExtendDOMapper {
	
	/**
	 * 根据时间获取获取买单和订单的收入
	 * 
	 * @param param
	 * @return
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	List<TotalSalesDO> selectTotalSales(TotalSalesQueryExample example);
	
	/**
	 * 查询用户支出和收入的金额
	 * 
	 * @param param
	 * @return
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	List<UserIncomeExpenditureDO> selectUserIncomeExpenditure(UserIncomeExpenditureExample example);
	
	
	ReportAdEarningsPointView  getReportAdEarningsPoint(ReportAdPointView view);
	
   ReportAdEarningsPointView  getReportAdEarningsLovePoint(ReportAdPointView view);
	
   List<ReportAdEarningsPointView> getUserPointByBzId(List<Long> bizIds);
	
	/**
	 * 根据时间获取获取买单和订单的收入group by area
	 * 
	 * @param param
	 * @return
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	List<TotalSalesGroupByAreaDO> selectTotalSalesGroupByArea(TotalSalesQueryExample example);
	
	 List<ReportAdEarningsPointView> getLovePointByBzId(List<Long> bizIds);

    List<IncomeMsgDOView> getIncomeMsgDataList(IncomeMsgExample example);
    
    /**
     * 查询月结账单
     * 
     * @param example 查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    List<MonthlyBillDO> selectMonthlyBill(TransactionDetailDOExample example);

    List<IncomeMsgDOView> getIncomeMsgTotalDataList(IncomeMsgExample example);
}