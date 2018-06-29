package com.lawu.eshop.merchant.api.service.hystrix;

import com.lawu.eshop.merchant.api.service.MerchantInfoService;
import com.lawu.eshop.user.dto.MerchantInfoDTO;
import com.lawu.eshop.user.param.MerchantProfileParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * Created by Administrator on 2017/3/23.
 */
@Component
public class MerchantInfoServiceHystrix  {
    //@Override
    public Result updateMerchantSizeLink(@ModelAttribute MerchantProfileParam merchantProfileParam, @RequestParam Long id) {
        Result result = new Result();
        result.setMsg("异常");
        return result;
    }

    //@Override
    public Result<MerchantInfoDTO> findMerchantProfileInfo(@RequestParam("merchantProfileId") Long merchantProfileId) {
        Result<MerchantInfoDTO> result = new Result<MerchantInfoDTO>();
        result.setMsg("异常");
        return  result;
    }
}
