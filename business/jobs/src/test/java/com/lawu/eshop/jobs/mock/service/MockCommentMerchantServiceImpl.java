package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.CommentMerchantService;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.param.PayOrderAutoCommentParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class MockCommentMerchantServiceImpl implements CommentMerchantService {


	@Override
	public Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("merchantId") Long merchantId) {
		return null;
	}

	@Override
	public Result payOrderAutoComment(@ModelAttribute PayOrderAutoCommentParam param) {
		return null;
	}
}
