package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.ad.dto.AdLexiconDTO;
import com.lawu.eshop.member.api.service.AdLexiconService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockAdLexiconService extends BaseController implements AdLexiconService {


	@Override
	public Result<List<AdLexiconDTO>> selectList(@RequestParam("adId") Long adId) {
		AdLexiconDTO dto = new AdLexiconDTO();
		dto.setTitle("nimei");
		List<AdLexiconDTO> list = new ArrayList<>();
		list.add(dto);
		return successCreated(list);
	}
}
