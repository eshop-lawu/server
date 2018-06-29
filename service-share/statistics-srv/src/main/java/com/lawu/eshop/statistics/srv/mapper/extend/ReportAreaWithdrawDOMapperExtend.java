package com.lawu.eshop.statistics.srv.mapper.extend;

import com.lawu.eshop.statistics.param.ReportAreaWithdrawParam;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaWithdrawDOView;

/**
 * @author zhangyong
 * @date 2017/8/15.
 */
public interface ReportAreaWithdrawDOMapperExtend {

    ReportAreaWithdrawDOView selectAreaWithdrawDailyReport(ReportAreaWithdrawParam param);

    ReportAreaWithdrawDOView selectAreaWithdrawMonthReport(ReportAreaWithdrawParam withdrawParam);
}
