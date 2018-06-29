package com.lawu.eshop.statistics.srv.mapper.extend;


import com.lawu.eshop.statistics.srv.domain.extend.AgentAreaRechargeDailyMonthDOExtend;
import com.lawu.eshop.statistics.srv.domain.extend.AgentAreaRechargeExampleExtend;

public interface ReportAreaRechargeDailyDOMapperExtend {

    AgentAreaRechargeDailyMonthDOExtend getAreaRechargeList(AgentAreaRechargeExampleExtend exampleExtend);
}