package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.PropertyInfoDataService;
import com.lawu.eshop.property.param.PointDetailQueryData1Param;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockPropertyInfoDataService extends BaseController implements PropertyInfoDataService {
    @Override
    public Result<Integer> getPointDetailByUserNumAndPointTypeAndBizId(@RequestBody PointDetailQueryData1Param param) {
        return successCreated();
    }
}
