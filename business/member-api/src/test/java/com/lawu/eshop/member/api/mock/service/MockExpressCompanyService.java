package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyQueryDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyRetrieveDTO;
import com.lawu.eshop.member.api.service.ExpressCompanyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;


@Service
public class MockExpressCompanyService extends BaseController implements ExpressCompanyService {

	@Override
	public Result<List<ExpressCompanyDTO>> list() {
		ExpressCompanyDTO dto = new ExpressCompanyDTO();
		List<ExpressCompanyDTO> list = new ArrayList<>();
		list.add(dto);
		return successCreated(list);
	}

	@Override
	public Result<ExpressCompanyQueryDTO> group() {
		ExpressCompanyQueryDTO dto = new ExpressCompanyQueryDTO();
		return successCreated(dto);
	}

	@Override
	public Result<ExpressCompanyDTO> get(@PathVariable("id") Integer id) {
		ExpressCompanyDTO dto = new ExpressCompanyDTO();
		dto.setCode("fd");
		dto.setId(1);
		dto.setName("yto");
		return successCreated(dto);
	}

	@Override
	public Result<ExpressCompanyRetrieveDTO> listByKeyWord(@RequestParam("keyWord") String keyWord) {
		ExpressCompanyRetrieveDTO dto = new ExpressCompanyRetrieveDTO();
		return successCreated(dto);
	}

	@Override
	public Result<List<ExpressCompanyDTO>> codeList(List<String> codeList) {
		List<ExpressCompanyDTO> rtn = new ArrayList<>();
		return successGet(rtn);
	}

	@Override
	public Result<ExpressCompanyDTO> code(String code) {
		ExpressCompanyDTO rtn = new ExpressCompanyDTO();
		return successGet(rtn);
	}
}
