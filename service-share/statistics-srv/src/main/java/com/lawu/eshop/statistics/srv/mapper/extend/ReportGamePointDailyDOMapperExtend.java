package com.lawu.eshop.statistics.srv.mapper.extend;

import com.lawu.eshop.statistics.srv.domain.extend.ReportGamePointDailyDOView;
import com.lawu.eshop.statistics.srv.domain.extend.ReportTotalGamePointView;

/**
 * @author zhangyong
 * @date 2018/3/26.
 */
public interface ReportGamePointDailyDOMapperExtend {

    ReportGamePointDailyDOView getTotalGamePoint(Byte type);
    
    ReportTotalGamePointView selectTotalReport(Byte type);
}
