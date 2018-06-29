package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.param.PlatformTotalSalesQueryParam;
import com.lawu.eshop.statistics.param.PlatformTotalSalesSaveParam;
import com.lawu.eshop.statistics.srv.bo.ReportSalesBO;

/**
 * 平台总销量服务接口类
 * 
 * @author Sunny
 * @date 2017年6月30日
 */
public interface ReportSalesService {

	/**
	 * 保存平台总销量记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
    void save(PlatformTotalSalesSaveParam param);
    
    /**
     * 查询平台总销量记录
     * 
     * @param param
     * @return
     * @author Sunny
     * @date 2017年7月3日
     */
    List<ReportSalesBO> list(PlatformTotalSalesQueryParam param);
}
