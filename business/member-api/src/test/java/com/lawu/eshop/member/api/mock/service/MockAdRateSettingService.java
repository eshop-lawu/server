package com.lawu.eshop.member.api.mock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.dto.AdRateSettingDTO;
import com.lawu.eshop.member.api.service.AdRateSettingService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockAdRateSettingService extends BaseController implements AdRateSettingService {

    @Override
    public Result<List<AdRateSettingDTO>> queryAdRateSetting() {
        return null;
    }
}
