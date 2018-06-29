package com.lawu.eshop.member.api.mock.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.member.api.service.VerifyCodeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;


@Service
class MockVerifyCodeService extends BaseController implements VerifyCodeService {

    @Override
    public Result savePicCode(@PathVariable("mobile") String mobile, @RequestParam("picCode") String picCode, @RequestParam("purpose") VerifyCodePurposeEnum purpose) {
        return successCreated();
    }

    @Override
    public Result<VerifyCodeDTO> verifySmsCode(@PathVariable("id") Long id, @RequestParam("smsCode") String smsCode) {
        VerifyCodeDTO dto = new VerifyCodeDTO();
        dto.setMobile("13800138000");
        dto.setGmtCreate(new Date());
        return successCreated(dto);
    }

    @Override
    public Result verifyPicCode(@PathVariable("mobile") String mobile, @RequestParam("picCode") String picCode) {
        return successCreated();
    }

    @Override
    public Result getVerifyCodeById(@PathVariable("id") Long id) {
        return successCreated();
    }
}
