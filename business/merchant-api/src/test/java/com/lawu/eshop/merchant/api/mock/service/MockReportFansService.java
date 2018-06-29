package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.ReportFansService;
import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.user.param.ReportDataParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockReportFansService extends BaseController implements ReportFansService {
    @Override
    public Result<ReportRiseRateDTO> fansRiseRate(@RequestBody ReportDataParam dparam) {
        return successCreated();
    }

    @Override
    public Result<List<ReportRiseRerouceDTO>> fansRiseSource(@RequestBody ReportDataParam dparam) {
        return successCreated();
    }
}
