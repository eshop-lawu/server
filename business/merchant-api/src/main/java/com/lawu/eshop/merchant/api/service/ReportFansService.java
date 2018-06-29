package com.lawu.eshop.merchant.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.user.param.ReportDataParam;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年5月2日 下午2:18:07
 *
 */
@FeignClient(value= "user-srv")
public interface ReportFansService {

	/**
	 * 粉丝数据，粉丝增长量
	 * @param dparam
	 * @return
	 * @author yangqh
	 * @date 2017年5月2日 下午2:34:33
	 */
	@RequestMapping(method = RequestMethod.POST, value = "reportFans/fansRiseRate")
	Result<ReportRiseRateDTO> fansRiseRate(@RequestBody ReportDataParam dparam);

	/**
	 * 粉丝数据，增长来源
	 * @param dparam
	 * @return
	 * @author yangqh
	 * @date 2017年5月2日 下午7:15:22
	 */
	@RequestMapping(method = RequestMethod.POST, value = "reportFans/fansRiseSource")
	Result<List<ReportRiseRerouceDTO>> fansRiseSource(@RequestBody ReportDataParam dparam);
    
}
