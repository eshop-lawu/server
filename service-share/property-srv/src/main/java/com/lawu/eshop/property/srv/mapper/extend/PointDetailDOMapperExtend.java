package com.lawu.eshop.property.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.srv.domain.extend.AreaPointConsumeDOView;
import com.lawu.eshop.property.srv.domain.extend.IncomeMsgDOView;
import com.lawu.eshop.property.srv.domain.extend.IncomeMsgExample;
import com.lawu.eshop.property.srv.domain.extend.PointDOView;
import com.lawu.eshop.property.srv.domain.extend.PointReportDOView;
import com.lawu.eshop.property.srv.domain.extend.ReportAdEarningsPointView;
import com.lawu.eshop.property.srv.domain.extend.ReportAdPointGroupByAreaView;

public interface PointDetailDOMapperExtend {
	
	ReportAdEarningsPointView  getReportAdEarningsPoint(ReportAdEarningsPointView view);
	
	ReportAdEarningsPointView  getReportAdEarningsLovePoint(ReportAdEarningsPointView view);
	
	ReportAdEarningsPointView getUserPointByBzId(ReportAdEarningsPointView view);
	
	ReportAdEarningsPointView getLovePointByBzId(ReportAdEarningsPointView view);
    
	List<ReportAdPointGroupByAreaView> getReportAdPointGroupByArea(@Param("bdate") String bdate, @Param("edate")String edate,RowBounds rowBounds);
	
	List<AreaPointConsumeDOView> getAreaPointConsume(ReportAgentAreaPointParam param);
	
	List<AreaPointConsumeDOView> getAreaPointRefund(ReportAgentAreaPointParam param);

    List<IncomeMsgDOView> getIncomeMsgDataList(IncomeMsgExample example);

	List<PointDOView> selectPointDetailListByDateAndDirection(PointReportDOView pointReportDOView);

	List<PointDOView> selectPointDetailListByDateAndDirectionAndPointType(PointReportDOView pointReportDOView);

    List<IncomeMsgDOView> getIncomeMsgTotalDataList(IncomeMsgExample example);
}