package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.srv.domain.extend.FansMerchantDOReportView;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class ReportConvertTest {

    @Test
    public void reportBrokeLineShow() {
        List<FansMerchantDOReportView> reportViews = new ArrayList<>();
        FansMerchantDOReportView fansMerchantDOReportView = new FansMerchantDOReportView();
        fansMerchantDOReportView.setNum("10");
        fansMerchantDOReportView.setKeyTxt("20");
        reportViews.add(fansMerchantDOReportView);
        ReportRiseRateDTO dto = ReportConvert.reportBrokeLineShow(reportViews, 10);
        Assert.assertNotNull(dto);
    }

}
