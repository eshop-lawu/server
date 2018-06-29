/**
 * 
 */
package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.InformDTO;
import com.lawu.eshop.mall.param.InformEditParam;
import com.lawu.eshop.mall.param.InformQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 举报管理service
 * 
 * @author lihj
 * @date 2017年7月27日
 */
@FeignClient(value = "mall-srv")
public interface InformService {

	/**
	 * 新增举报记录
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "inform/selectInformList", method = RequestMethod.POST)
	Result<Page<InformDTO>> selectInformList(@ModelAttribute InformQueryParam param);

	/**
	 * 处理举报信息
	 * @param id
	 * @param status
	 * @param remark
	 * @return
	 */
	@RequestMapping(value="inform/editInform",method = RequestMethod.POST)
	Result editInform(@RequestBody InformEditParam param);

}
