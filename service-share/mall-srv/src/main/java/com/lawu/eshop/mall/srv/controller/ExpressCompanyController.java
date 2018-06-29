package com.lawu.eshop.mall.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyQueryDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyRetrieveDTO;
import com.lawu.eshop.mall.srv.bo.ExpressCompanyBO;
import com.lawu.eshop.mall.srv.converter.ExpressCompanyConverter;
import com.lawu.eshop.mall.srv.service.ExpressCompanyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@RestController
@RequestMapping(value = "expressCompany/")
public class ExpressCompanyController extends BaseController {

	@Autowired
	private ExpressCompanyService expressCompanyService;

	/**
	 * 查询全部快递公司，根据ordinal排序
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Result<List<ExpressCompanyDTO>> list() {
		return successGet(ExpressCompanyConverter.convertDTOS(expressCompanyService.list()));
	}

	/**
	 * 查询可显示的快递公司,并且按照名称首字母分组
	 *
	 * @return
	 * @author Sunny
	 * @date 2017年7月7日
	 */
	@RequestMapping(value = "group", method = RequestMethod.GET)
	public Result<ExpressCompanyQueryDTO> group() {
		List<ExpressCompanyBO> expressCompanyBOList = expressCompanyService.list(true);
		ExpressCompanyQueryDTO rtn = ExpressCompanyConverter.convertExpressCompanyQueryDTO(expressCompanyBOList);
		return successGet(rtn);
	}
	
	/**
	 * 根据id查询快递公司
	 * 
	 * @param id 快递公司id
	 * @return
	 */
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<ExpressCompanyDTO> get(@PathVariable("id") Integer id) {
		ExpressCompanyBO expressCompanyBO = expressCompanyService.get(id);
		if (expressCompanyBO == null) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}
		return successGet(ExpressCompanyConverter.convert(expressCompanyBO));
	}
	
	/**
	 * 根据关键字检索快递公司
	 *
	 * @param keyWord 快递公司的name
	 * @return
	 * @author Sunny
	 * @date 2017年7月6日
	 */
	@RequestMapping(value = "keyWord", method = RequestMethod.GET)
	public Result<ExpressCompanyRetrieveDTO> listByKeyWord(@RequestParam("keyWord") String keyWord) {
		List<ExpressCompanyBO> expressCompanyBOList = expressCompanyService.list(false);

		/*
		 * 最终的检索结果
		 * 最大size为10
		 */
		int maxSize = 10;
		List<ExpressCompanyBO> filterList =  new ArrayList<>();

		/*
		 * 第一级筛选
		 * 以keyword开头的
		 */
		List<ExpressCompanyBO> firstFilterList =  new ArrayList<>();

		/*
		 * 第二级筛选
		 * 包含keyword的
		 * 如果secondFilterList为空或者size小于5
		 */
		List<ExpressCompanyBO> secondFilterList =  new ArrayList<>();

		/*
		 * 第三级筛选
		 * 拆分keyword为单个字,去匹配,全部匹配
		 * 如果secondFilterList的size小于5
		 */
		List<ExpressCompanyBO> thirdFilterList =  new ArrayList<>();

		/*
		 * 第四级筛选
		 * 拆分keyword为单个字,去匹配,单个匹配
		 * 如果secondFilterList的size小于5
		 */
		List<ExpressCompanyBO> fourthFilterList =  new ArrayList<>();

		for (ExpressCompanyBO expressCompanyBO : expressCompanyBOList) {
			if (expressCompanyBO.getName().startsWith(keyWord)) {
				firstFilterList.add(expressCompanyBO);
			} else if (expressCompanyBO.getName().contains(keyWord)) {
				secondFilterList.add(expressCompanyBO);
			} else {
				boolean isAllMatch = true;
				boolean isPartialMatch = false;
				for (int i = 0; i < keyWord.length(); i++) {
					if (expressCompanyBO.getName().contains(String.valueOf(keyWord.charAt(i)))) {
						isPartialMatch = true;
					} else {
						isAllMatch = false;
					}
				}
				if (isAllMatch) {
					thirdFilterList.add(expressCompanyBO);
				} else if (isPartialMatch) {
					fourthFilterList.add(expressCompanyBO);
				}
			}
		}

		filterList.addAll(firstFilterList);
		filterList.addAll(secondFilterList);
		filterList.addAll(thirdFilterList);
		filterList.addAll(fourthFilterList);

		// 如果数量超过最大限制,截取List
		if (filterList.size() > maxSize) {
			filterList = filterList.subList(0, maxSize);
		}
		ExpressCompanyRetrieveDTO rtn = ExpressCompanyConverter.convertExpressCompanyRetrieveDTO(filterList);
		return successGet(rtn);
	}
	
	/**
	 * 根据第三方快递公司编号集合查询快递公司
	 * 
	 * @param codeList 快递公司编号集合
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@RequestMapping(value = "codeList", method = RequestMethod.PUT)
	public Result<List<ExpressCompanyDTO>> codeList(@RequestBody List<String> codeList) {
		List<ExpressCompanyBO> expressCompanyBOList = expressCompanyService.codeList(codeList);
		if (expressCompanyBOList == null || expressCompanyBOList.isEmpty()) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		List<ExpressCompanyDTO> rtn = ExpressCompanyConverter.convertDTOS(expressCompanyBOList);
		return successGet(rtn);
	}
	
	/**
	 * 根据第三方快递公司编号查询快递公司
	 * 
	 * @param code 快递公司编号
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@RequestMapping(value = "code/{code}", method = RequestMethod.GET)
	public Result<ExpressCompanyDTO> code(@PathVariable("code") String code) {
		ExpressCompanyBO expressCompanyBO = expressCompanyService.code(code);
		if (expressCompanyBO == null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		ExpressCompanyDTO rtn = ExpressCompanyConverter.convert(expressCompanyBO);
		return successGet(rtn);
	}
}
