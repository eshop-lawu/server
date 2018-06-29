package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.eshop.member.api.service.IndustryTypeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Service
public class MockIndustryTypeService extends BaseController implements IndustryTypeService {


    @Override
    public Result<List<IndustryTypeDTO>> listIndustryType() {
        IndustryTypeDTO dto = new IndustryTypeDTO();
        List<IndustryTypeDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<List<IndustryTypeDTO>> listIndustryTypeByParentId(@PathVariable("parentId") Short parentId) {
        IndustryTypeDTO dto = new IndustryTypeDTO();
        List<IndustryTypeDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }
}
