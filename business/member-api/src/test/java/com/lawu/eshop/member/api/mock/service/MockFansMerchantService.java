package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.FansMerchantService;
import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.dto.FansMerchantQueryDTO;
import com.lawu.eshop.user.param.FansMerchantQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockFansMerchantService extends BaseController implements FansMerchantService {

    @Override
    public Result<Boolean> isFansMerchant(@PathVariable("merchantId") Long merchantId, @RequestParam("memberId") Long memberId) {
        return successCreated(true);
    }

    @Override
    public List<Long> findMerchant(@RequestParam("memberId") Long memberId) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        return list;
    }

    @Override
    public Result saveFansMerchant(@PathVariable("merchantId") Long merchantId, @RequestParam("memberId") Long memberId, @RequestParam("channelEnum") FansMerchantChannelEnum channelEnum) {
        return successCreated();
    }

    @Override
    public Result removeFansMerchant(Long merchantId, Long memberId) {
        return successCreated();
    }

    @Override
    public Result<Integer> countFans(@PathVariable("merchantId") Long merchantId) {
        return successCreated(new Integer(1));
    }

    @Override
    public Result<Integer> getAttentionMerchantCount(@RequestParam("memberId") Long memberId) {
        return null;
    }

    @Override
	public Result saveFansMerchantFromInvite(Long merchantId, Long memberId, Long messageId, Boolean dealWay) {
		return successCreated(true);
	}

	@Override
	public Result<Page<FansMerchantQueryDTO>> getFansMerchantList(Long memberId, FansMerchantQueryParam pageQuery) {
		return null;
	}
}
