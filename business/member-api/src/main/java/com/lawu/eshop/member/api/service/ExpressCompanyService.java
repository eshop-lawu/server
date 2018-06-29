package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyQueryDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyRetrieveDTO;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@FeignClient(value= "mall-srv", path = "expressCompany/")
public interface ExpressCompanyService {

    /**
     * 查询全部快递公司，根据ordinal排序
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    Result<List<ExpressCompanyDTO>> list();
    
	/**
	 * 查询全部快递公司,并且按照名称首字母分组
	 * 
	 * @return
	 * @author Sunny
	 * @date 2017年7月7日
	 */
	@RequestMapping(value = "group", method = RequestMethod.GET)
	Result<ExpressCompanyQueryDTO> group();
    
	/**
	 * 根据id查询快递公司
	 * 
	 * @param id 快递公司id
	 * @return
	 */
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	Result<ExpressCompanyDTO> get(@PathVariable("id") Integer id);
	
	/**
	 * 根据关键字检索快递公司
	 * 
	 * @param keyWord 快递公司的name
	 * @return
	 * @author Sunny
	 * @date 2017年7月6日
	 */
	@RequestMapping(value = "keyWord", method = RequestMethod.GET)
	Result<ExpressCompanyRetrieveDTO> listByKeyWord(@RequestParam("keyWord") String keyWord);
	
	/**
	 * 根据第三方快递公司编号集合查询快递公司
	 * 
	 * @param codeList 快递公司编号集合
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@RequestMapping(value = "codeList", method = RequestMethod.PUT)
	Result<List<ExpressCompanyDTO>> codeList(@RequestBody List<String> codeList);
	
	/**
	 * 根据第三方快递公司编号查询快递公司
	 * 
	 * @param code 快递公司编号
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@RequestMapping(value = "code/{code}", method = RequestMethod.GET)
	Result<ExpressCompanyDTO> code(@PathVariable("code") String code);
}
