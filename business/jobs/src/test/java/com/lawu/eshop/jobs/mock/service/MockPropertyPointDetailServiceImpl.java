package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.jobs.service.PropertyPointDetailService;
import com.lawu.eshop.property.dto.IncomeMsgDTO;
import com.lawu.eshop.property.dto.PointConsumeReportDTO;
import com.lawu.eshop.property.param.PointDetailReportParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockPropertyPointDetailServiceImpl implements PropertyPointDetailService {


	@Override
	public Result<PointConsumeReportDTO> selectPointDetailListByDateAndDirection(@RequestBody PointDetailReportParam param) {
		return null;
	}

	@Override
	public Result<PointConsumeReportDTO> selectPointDetailListByDateAndDirectionAndPointType(@RequestBody PointDetailReportParam param) {
		return null;
	}

	@Override
	public Result<List<IncomeMsgDTO>> getIncomeMsgDataList(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
		return null;
	}

}
