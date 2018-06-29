package com.lawu.eshop.statistics.srv.mapper.extend;

import com.lawu.eshop.statistics.param.UserActiveParam;
import com.lawu.eshop.statistics.srv.domain.extend.ReportUserActiveDOView;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public interface ReportUserActiveDOMapperExtend {

    List<ReportUserActiveDOView> getUserActiveListDaily(UserActiveParam param);

    List<ReportUserActiveDOView> getUserActiveListMonth(UserActiveParam param);
}
