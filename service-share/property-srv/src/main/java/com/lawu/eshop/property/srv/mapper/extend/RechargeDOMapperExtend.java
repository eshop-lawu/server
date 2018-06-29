package com.lawu.eshop.property.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.srv.domain.extend.AreaRechargePointDOView;
import com.lawu.eshop.property.srv.domain.extend.RechargeDOView;
import com.lawu.eshop.property.srv.domain.extend.RechargeReportDOView;
import com.lawu.eshop.property.srv.domain.extend.ReportAreaRechargeDOExtend;
import com.lawu.eshop.property.srv.domain.extend.ReportAreaRechargeDOView;

public interface RechargeDOMapperExtend {

	List<ReportAreaRechargeDOExtend> selectAgentAreaReportRechargeListByDate(ReportAreaRechargeDOView recharge,RowBounds rowBounds);
	List<AreaRechargePointDOView> selectAreaRechargePoint(ReportAgentAreaPointParam param);
	
	/**
	 * 充值余额查询统计
	 * @param example
	 * @return
	 * @author zhangrc
	 * @date 2017/11/13
	 */
	List<RechargeDOView> selectWithdrawCashListByDateAndStatus(RechargeReportDOView example);
}
