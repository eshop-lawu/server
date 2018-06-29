/**
 * 
 */
package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.param.InformSaveParam;
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
	@RequestMapping(value = "inform/addInform", method = RequestMethod.POST)
	Result addInform(@ModelAttribute InformSaveParam param);

}
