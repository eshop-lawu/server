package com.lawu.eshop.merchant.api.mock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.MerchantIndustryTypeEnum;
import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.eshop.merchant.api.service.IndustryTypeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockIndustryTypeService extends BaseController implements IndustryTypeService {
    @Override
    public Result<List<IndustryTypeDTO>> listIndustryType() {
        return successGet();
    }

    @Override
    public Result<List<IndustryTypeDTO>> listIndustryTypeByParentId(@PathVariable("parentId") Short parentId) {
        return successGet();
    }

    @Override
    public Result<List<IndustryTypeDTO>> getAllIndustryList() {
        return successGet();
    }

    @Override
    public Result<List<IndustryTypeDTO>> listIndustryTypeByType(@RequestParam("industryTypeEnum") MerchantIndustryTypeEnum industryTypeEnum) {
        return successGet();
    }
}
