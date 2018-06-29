package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawu.eshop.member.api.service.ExpressService;
import com.lawu.eshop.order.dto.ExpressInquiriesDTO;
import com.lawu.eshop.order.dto.ExpressRecognitionDetailDTO;
import com.lawu.eshop.order.param.ExpressQueryParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockExpressService extends BaseController implements ExpressService {

    @Override
    public Result<ExpressInquiriesDTO> inquiries(@RequestBody ExpressQueryParam param) {
        return null;
    }

    @Override
    public Result<ExpressRecognitionDetailDTO> recognition(@PathVariable("expNo") String expNo) {
        return null;
    }
}
