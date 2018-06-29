package com.lawu.eshop.property.srv.service;

import com.lawu.eshop.property.param.BroadcastListParam;
import com.lawu.eshop.property.srv.bo.BroadcastListBO;
import com.lawu.eshop.property.srv.bo.IncomeSummaryBO;
import com.lawu.framework.core.page.Page;

import java.util.List;

public interface IncomeSummaryService {

    List<IncomeSummaryBO> getIncomeSummary(String userNum);

    Page<BroadcastListBO> getBroadcastList(String userNum, BroadcastListParam broadcastListParam);
}
