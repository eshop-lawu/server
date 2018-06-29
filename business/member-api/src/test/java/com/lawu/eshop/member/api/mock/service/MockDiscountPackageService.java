package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.lawu.eshop.mall.dto.DiscountPackageDetailForMemberDTO;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.member.api.service.DiscountPackageService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;


@Service
public class MockDiscountPackageService extends BaseController implements DiscountPackageService {

	@Override
	public Result<Page<DiscountPackageQueryDTO>> listForMember(@PathVariable("merchantId") Long merchantId) {
		DiscountPackageQueryDTO dto = new DiscountPackageQueryDTO();
		List<DiscountPackageQueryDTO> list = new ArrayList<>();
		list.add(dto);
		Page<DiscountPackageQueryDTO> page = new Page();
		page.setRecords(list);
		page.setCurrentPage(1);
		page.setTotalCount(100);
		return successCreated(page);
	}

	@Override
	public Result<DiscountPackageDetailForMemberDTO> getByMember(@PathVariable("id") Long id) {
		DiscountPackageDetailForMemberDTO dto = new DiscountPackageDetailForMemberDTO();
		return successCreated(dto);
	}
}
