package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.AddressService;
import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.eshop.user.param.AddressParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockAddressService extends BaseController implements AddressService {
    @Override
    public Result update(@RequestBody AddressParam address, @PathVariable("id") Long id, @RequestParam("userNum") String userNum) {
        return successCreated();
    }

    @Override
    public Result<AddressDTO> get(@PathVariable("id") Long id) {
        AddressDTO dto = new AddressDTO();
        dto.setRegionName("test");
        dto.setAddr("test");
        return successGet(dto);
    }

    @Override
    public Result delete(@PathVariable("id") Long userId, @RequestParam("userNum") String userNum) {
        return successDelete();
    }

    @Override
    public Result updateDefault(@PathVariable("id") Long id, @RequestParam("userNum") String userNum) {
        return successGet();
    }

    @Override
    public Result<List<AddressDTO>> selectByUserNum(@PathVariable("userNum") String userNum) {
        return successGet();
    }

    @Override
    public Result saveWithUserNum(@PathVariable("userNum") String userNum, @RequestBody @Validated AddressParam addressDO) {
        return successCreated();
    }
}
