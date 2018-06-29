package com.lawu.eshop.statistics.srv.mapper.extend;


import com.lawu.eshop.statistics.srv.domain.extend.AgentAreaRechargeDailyMonthDOExtend;
import com.lawu.eshop.statistics.srv.domain.extend.AgentAreaRechargeExampleExtend;

public interface ReportAreaRechargeMonthDOMapperExtend {

    AgentAreaRechargeDailyMonthDOExtend getAreaRechargeList(AgentAreaRechargeExampleExtend exampleExtend);
}