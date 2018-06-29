package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.PointDetailService;
import com.lawu.eshop.property.dto.PointDetailDTO;
import com.lawu.eshop.property.param.PointDetailQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockPointDetailService extends BaseController implements PointDetailService {
    @Override
    public Result<Page<PointDetailDTO>> findPageByUserNum(@PathVariable("userNum") String userNum, @RequestBody PointDetailQueryParam pointDetailQueryParam) {
        return successCreated();
    }
}
