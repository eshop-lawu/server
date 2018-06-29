package com.lawu.eshop.statistics.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.statistics.param.PlatformTotalSalesSaveParam;
import com.lawu.eshop.statistics.srv.bo.ReportSalesBO;
import com.lawu.eshop.statistics.srv.domain.ReportSalesDailyDO;

/**
 * ReportSalesDaily转换器
 * 
 * @author Sunny
 * @date 2017年6月30日
 */
public class ReportSalesDailyConverter {

    /**
     * ReportSalesDailyDO转ReportSalesBO
     *
     * @param reportSalesDailyDO
     * @return
     */
    public static ReportSalesBO convert(ReportSalesDailyDO reportSalesDailyDO) {
    	ReportSalesBO rtn = null;
    	if (reportSalesDailyDO == null) {
    		return rtn;
    	}
    	rtn = new ReportSalesBO();
        rtn.setPayOrderAmount(reportSalesDailyDO.getPayOrderAmount());
        rtn.setShoppingOrderAmount(reportSalesDailyDO.getShoppingOrderAmount());
        rtn.setTotalAmount(reportSalesDailyDO.getTotalAmount());
        rtn.setGmtReport(reportSalesDailyDO.getGmtReport());
        rtn.setGmtCreate(reportSalesDailyDO.getGmtCreate());
        return rtn;
    }
    
    /**
     * ReportSalesDailyDOList转ReportSalesBOList
     *
     * @param reportSalesDailyDO
     * @return
     */
    public static List<ReportSalesBO> convertReportSalesBOList(List<ReportSalesDailyDO> reportSalesDailyDOList) {
    	List<ReportSalesBO> rtn = null;
    	if (reportSalesDailyDOList == null || reportSalesDailyDOList.isEmpty()) {
    		return rtn;
    	}
    	rtn = new ArrayList<>();
    	for (ReportSalesDailyDO reportSalesDailyDO : reportSalesDailyDOList) {
    		rtn.add(convert(reportSalesDailyDO));
    	}
        return rtn;
    }

}
