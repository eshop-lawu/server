package com.lawu.eshop.jobs.mock.service;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.common.param.CommissionJobParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.dto.AreaPointConsumeDTO;
import com.lawu.eshop.property.dto.AreaRechargePointDTO;
import com.lawu.eshop.property.dto.PropertyDTO;
import com.lawu.eshop.property.dto.ReportAdEarningsPointDTO;
import com.lawu.eshop.property.dto.ReportAdPointGroupByAreaDTO;
import com.lawu.eshop.property.dto.ReportEarningsDTO;
import com.lawu.eshop.property.dto.TotalSalesGroupByAreaDTO;
import com.lawu.eshop.property.param.CommissionParam;
import com.lawu.eshop.property.param.ReportAdEarningsPointParam;
import com.lawu.eshop.property.param.ReportAdPointParam;
import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.param.TotalSalesQueryParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockPropertySrvServiceImpl implements PropertySrvService {


	@Override
	public Result getValue(@RequestParam("name") String name) {
		return null;
	}

	@Override
	public int calculation(@RequestBody CommissionJobParam param) {
		return ResultCode.SUCCESS;
	}

	@Override
	public Result<ReportAdEarningsPointDTO> getReportAdEarningsPoint(@RequestBody ReportAdEarningsPointParam reportAdEarningsPointParam) {
		return null;
	}

	@Override
	public Result<ReportEarningsDTO> getReportEarnings(@RequestParam("bizIds") List<Long> bizIds) {
		return null;
	}

	@Override
	public Result<List<ReportAdPointGroupByAreaDTO>> getReportAdPointGroupByArea(@RequestBody ReportAdPointParam param) {
		return null;
	}

	@Override
	public Result<List<TotalSalesGroupByAreaDTO>> selectTotalSalesGroupByArea(@RequestBody TotalSalesQueryParam param) {
		return null;
	}

	@Override
	public Result<List<AreaPointConsumeDTO>> getAreaPointConsume(@RequestBody ReportAgentAreaPointParam param) {
		return null;
	}

	@Override
	public Result<List<AreaPointConsumeDTO>> getAreaPointRefund(@RequestBody ReportAgentAreaPointParam param) {
		return null;
	}

	@Override
	public Result<List<AreaRechargePointDTO>> selectAreaRechargePoint(@RequestBody ReportAgentAreaPointParam param) {
		return null;
	}

	@Override
	public Result<List<PropertyDTO>> getAll() {
		List<PropertyDTO> list = new ArrayList<>();
		PropertyDTO dto = new PropertyDTO();
		dto.setName(PropertyType.ad_commission_0);
		dto.setValue(PropertyType.ad_commission_0_default);
		list.add(dto);
		PropertyDTO dto1 = new PropertyDTO();
		dto1.setName(PropertyType.ad_commission_1);
		dto1.setValue(PropertyType.ad_commission_1_default);
		list.add(dto1);
		PropertyDTO dto2 = new PropertyDTO();
		dto2.setName(PropertyType.ad_commission_2);
		dto2.setValue(PropertyType.ad_commission_2_default);
		list.add(dto2);
		PropertyDTO dto3 = new PropertyDTO();
		dto3.setName(PropertyType.ad_commission_3);
		dto3.setValue(PropertyType.ad_commission_3_default);
		list.add(dto3);
		PropertyDTO dto4 = new PropertyDTO();
		dto4.setName(PropertyType.love_account_scale);
		dto4.setValue(PropertyType.love_account_scale_default);
		list.add(dto4);
		PropertyDTO dto5 = new PropertyDTO();
		dto5.setName(PropertyType.sale_commission_1);
		dto5.setValue(PropertyType.sale_commission_1_default);
		list.add(dto5);
		PropertyDTO dto6 = new PropertyDTO();
		dto6.setName(PropertyType.sale_commission_2);
		dto6.setValue(PropertyType.sale_commission_2_default);
		list.add(dto6);
		PropertyDTO dto7 = new PropertyDTO();
		dto7.setName(PropertyType.sale_commission_3);
		dto7.setValue(PropertyType.sale_commission_3_default);
		list.add(dto7);
		PropertyDTO dto8 = new PropertyDTO();
		dto8.setName(PropertyType.sale_commission_add_scope);
		dto8.setValue(PropertyType.sale_commission_add_scope_default);
		list.add(dto8);
		PropertyDTO dto9 = new PropertyDTO();
		dto9.setName(PropertyType.sale_commission_0);
		dto9.setValue(PropertyType.sale_commission_0_default);
		list.add(dto9);
		Result<List<PropertyDTO>> result = new Result<>();
		result.setModel(list);
		return result;
	}

	@Override
	public Result calculationRefactor(@RequestBody CommissionParam param) {
		return null;
	}

}
