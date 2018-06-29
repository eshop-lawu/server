package com.lawu.eshop.order.srv.service;

import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.param.ReportDataParam;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年5月3日 下午2:19:30
 *
 */
public interface ReportPayService {

	/**
	 * 实体店铺买单交易数据
	 * @param dparam
	 * @return
	 * @author yangqh
	 * @date 2017年5月3日 下午2:20:48
	 */
	ReportRiseRateDTO payVolumeRiseRate(ReportDataParam dparam);
    
}
