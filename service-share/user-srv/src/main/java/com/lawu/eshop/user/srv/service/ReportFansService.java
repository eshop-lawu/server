package com.lawu.eshop.user.srv.service;

import java.util.List;

import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.user.param.ReportDataParam;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年5月2日 下午2:47:13
 *
 */
public interface ReportFansService {

	/**
	 * 粉丝增长量
	 * @param dparam
	 * @return
	 * @author yangqh
	 * @date 2017年5月2日 下午2:48:18
	 */
	ReportRiseRateDTO fansRiseRate(ReportDataParam dparam);

	/**
	 * 增长来源
	 * @param dparam
	 * @return
	 * @author yangqh
	 * @date 2017年5月2日 下午7:16:44
	 */
	List<ReportRiseRerouceDTO> fansRiseSource(ReportDataParam dparam);

    
}
