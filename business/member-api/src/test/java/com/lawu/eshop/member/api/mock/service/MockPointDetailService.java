package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.PointDetailService;
import com.lawu.eshop.property.dto.PointDetailDTO;
import com.lawu.eshop.property.param.PointDetailQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
class MockPointDetailService extends BaseController implements PointDetailService {


    @Override
    public Result<Page<PointDetailDTO>> findPageByUserNum(@PathVariable("userNum") String userNum, @RequestBody PointDetailQueryParam pointDetailQueryParam) {
        PointDetailDTO dto = new PointDetailDTO();
        List<PointDetailDTO> list = new ArrayList<>();
        list.add(dto);
        Page<PointDetailDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<Boolean> existsPointDetailByUserNumAndBizId(@RequestParam("userNum") String userNum, @RequestParam("bizId") String bizId) {
        return successGet(false);
    }
}
