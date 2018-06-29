package com.lawu.eshop.user.srv.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.constants.ReportFansRiseRateEnum;
import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.user.param.ReportDataParam;
import com.lawu.eshop.user.srv.converter.ReportConvert;
import com.lawu.eshop.user.srv.domain.extend.FansMerchantDOReportView;
import com.lawu.eshop.user.srv.mapper.extend.FansMerchantDOMapperExtend;
import com.lawu.eshop.user.srv.service.ReportFansService;
import com.lawu.utils.DateUtil;
import com.lawu.utils.StringUtil;

@Service
public class ReportFansServiceImpl implements ReportFansService {

	@Autowired
	private FansMerchantDOMapperExtend fansMerchantDOMapperExtend;

	@Override
	public ReportRiseRateDTO fansRiseRate(ReportDataParam dparam) {
		List<FansMerchantDOReportView> list = new ArrayList<>();
		int x = 0;
		if (ReportFansRiseRateEnum.DAY.getValue().equals(dparam.getFlag().getValue())) {
			list = fansMerchantDOMapperExtend.fansRiseRate(DateUtil.getDateFormat(new Date(), "yyyyMM"),
					dparam.getFlag().getValue(), dparam.getMerchantId());
			x = DateUtil.getNowMonthDay();
		} else if (ReportFansRiseRateEnum.MONTH.getValue().equals(dparam.getFlag().getValue())) {
			list = fansMerchantDOMapperExtend.fansRiseRate(DateUtil.getDateFormat(new Date(), "yyyy"),
					dparam.getFlag().getValue(), dparam.getMerchantId());
			x = 12;
		}
		ReportRiseRateDTO dto = ReportConvert.reportBrokeLineShow(list, x);
		return dto;
	}
	
	@Override
	public List<ReportRiseRerouceDTO> fansRiseSource(ReportDataParam dparam) {
		List<FansMerchantDOReportView> list = fansMerchantDOMapperExtend.fansRiseSource(dparam.getMerchantId());
		List<ReportRiseRerouceDTO> dtos = new ArrayList<ReportRiseRerouceDTO>();
		for (FansMerchantDOReportView view : list) {
			ReportRiseRerouceDTO dto = new ReportRiseRerouceDTO();
			dto.setName(FansMerchantChannelEnum.getEnum(StringUtil.intToByte(Integer.parseInt(view.getKeyTxt()))).getName());
			dto.setValue(view.getNum());
			dtos.add(dto);
		}
		return dtos;
	}
}
