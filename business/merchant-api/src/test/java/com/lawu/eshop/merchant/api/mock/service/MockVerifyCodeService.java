package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.merchant.api.service.VerifyCodeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockVerifyCodeService extends BaseController implements VerifyCodeService {
    @Override
    public Result savePicCode(@PathVariable("mobile") String mobile, @RequestParam("picCode") String picCode, @RequestParam("purpose") VerifyCodePurposeEnum purpose) {
        return successGet();
    }

    @Override
    public Result<VerifyCodeDTO> verifySmsCode(@PathVariable("id") Long id, @RequestParam("smsCode") String smsCode) {
        VerifyCodeDTO dto = new VerifyCodeDTO();
        dto.setId(10L);
        dto.setMobile("13888888888");
        dto.setCode("666666");
        dto.setGmtCreate(new Date());
        return successGet(dto);
    }

    @Override
    public Result verifyPicCode(@PathVariable("mobile") String mobile, @RequestParam("picCode") String picCode) {
        return successGet();
    }

    @Override
    public Result getVerifyCodeById(@PathVariable("id") Long id) {
        return successGet();
    }
}
