package com.lawu.eshop.jobs.service;

import java.util.List;

import com.lawu.eshop.property.dto.IncomeMsgDTO;
import com.lawu.eshop.property.dto.PointConsumeReportDTO;
import com.lawu.eshop.property.param.PointDetailReportParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value= "property-srv")
public interface PropertyTransactionDetailService {

	/**
	 * 查询昨天的收益记录（商家、用户）
	 * @return
	 * @author yangqh
	 * @date 2017年6月30日 下午2:29:46
	 */
	@RequestMapping(method = RequestMethod.GET, value = "transactionDetail/getIncomeMsgDataList")
	Result<List<IncomeMsgDTO>> getIncomeMsgDataList(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize);

	@RequestMapping(method = RequestMethod.POST, value = "transactionDetail/summaryIncome")
	Result summaryIncome();
}
