package com.lawu.eshop.merchant.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyQueryDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyRetrieveDTO;
import com.lawu.eshop.merchant.api.service.ExpressCompanyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockExpressCompanyService extends BaseController implements ExpressCompanyService {
    @Override
    public Result<List<ExpressCompanyDTO>> list() {
        return successGet();
    }

    @Override
    public Result<ExpressCompanyQueryDTO> group() {
        return successGet();
    }

    @Override
    public Result<ExpressCompanyDTO> get(@PathVariable("id") Integer id) {
        ExpressCompanyDTO dto = new ExpressCompanyDTO();
        return successGet(dto);
    }

    @Override
    public Result<ExpressCompanyRetrieveDTO> listByKeyWord(@RequestParam("keyWord") String keyWord) {
        return successGet();
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
