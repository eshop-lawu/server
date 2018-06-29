package com.lawu.eshop.statistics.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.statistics.param.UserRegParam;
import com.lawu.eshop.statistics.srv.domain.extend.ReportNewDateDOView;
import com.lawu.eshop.statistics.srv.domain.extend.ReportUserRegDOView;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
public interface UserRegDOMapperExtend {

	List<ReportUserRegDOView> getReportUserRegDaily(UserRegParam param);

	List<ReportUserRegDOView> getReportUserRegMonth(UserRegParam param);
	
	ReportNewDateDOView getReportDateUserRegDaily();
	
	ReportNewDateDOView getReportDateUserRegMonth();

}
