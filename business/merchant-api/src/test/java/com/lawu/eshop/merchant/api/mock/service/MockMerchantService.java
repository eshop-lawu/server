package com.lawu.eshop.merchant.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.merchant.api.service.MerchantService;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.dto.MobileDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserHeadImgDTO;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.core.page.PageParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockMerchantService extends BaseController implements MerchantService {
    @Override
    public Result<LoginUserDTO> find(@PathVariable("account") String account, @RequestParam("pwd") String pwd) {
        LoginUserDTO dto = new LoginUserDTO();
        dto.setNum("B0001");
        return successGet(dto);
    }

    @Override
    public Result updateLoginPwd(@PathVariable("id") Long id, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd) {
        return successCreated();
    }

    @Override
    public Result updateLoginPwd(@PathVariable("mobile") String mobile, @RequestParam("newPwd") String newPwd) {
        return successCreated();
    }

    @Override
    public Result getInviterByAccount(@PathVariable("account") String account) {
        return successGet();
    }

    @Override
    public Result register(@ModelAttribute RegisterRealParam registerRealParam) {
        return successCreated();
    }

    @Override
    public Result<Page<MerchantDTO>> getMerchantByInviter(@RequestParam("inviterId") Long inviterId, @RequestBody PageParam query) {
        return successCreated();
    }

    @Override
    public Result<MerchantDTO> getMerchantByAccount(@PathVariable("account") String account) {
        return successGet(ResultCode.NOT_FOUND_DATA);
    }

    @Override
    public Result setGtAndRongYunInfo(@PathVariable("id") Long id, @RequestParam("cid") String cid) {
        return successCreated();
    }

    @Override
    public Result<MerchantSNSDTO> selectMerchantInfo(@RequestParam("merchantId") Long merchantId) {
        MerchantSNSDTO dto = new MerchantSNSDTO();
        return successGet(dto);
    }

    @Override
    public Result<UserHeadImgDTO> saveHeadImage(@PathVariable("merchantId") Long merchantId, @RequestParam("headimg") String headimg) {
        return successCreated();
    }

    @Override
    public Result<MobileDTO> selectMobile(@PathVariable("merchantId") Long merchantId) {
        return successGet();
    }

    @Override
    public Result<RongYunDTO> getRongYunInfoByNum(@PathVariable("num") String num) {
        return successGet();
    }

    @Override
    public Result<Boolean> isRegister(@RequestParam("mobile") String mobile) {
        return successGet(false);
    }

    @Override
    public Result delMerchantGtPush(@RequestParam("merchantId") Long merchantId) {
        return successCreated();
    }

	@Override
	public Result<LoginUserDTO> smsLogin(String account) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Result<String> getRichTaskInviterNum(@RequestParam("userNum") String userNum) {
        return null;
    }
}
