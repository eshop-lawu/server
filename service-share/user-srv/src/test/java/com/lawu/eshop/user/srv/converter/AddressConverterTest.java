package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.eshop.user.param.AddressParam;
import com.lawu.eshop.user.srv.bo.AddressBO;
import com.lawu.eshop.user.srv.domain.AddressDO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class AddressConverterTest {

    @Test
    public void convertBO() {
        AddressDO addressDO = new AddressDO();
        addressDO.setId(10L);
        addressDO.setAddr("test");
        addressDO.setIsDefault(true);
        addressDO.setMobile("13666666666");
        addressDO.setName("test");
        addressDO.setPostcode("553636");
        addressDO.setRegionPath("44/4403/440303");
        addressDO.setRegionName("广东省深圳市南山区");
        AddressBO addressBO = AddressConverter.convertBO(addressDO);
        Assert.assertNotNull(addressBO);
        Assert.assertEquals(addressDO.getId(), addressBO.getId());
    }

    @Test
    public void convertListBOS() {
        List<AddressDO> addressDOS = new ArrayList<>();
        AddressDO addressDO = new AddressDO();
        addressDO.setId(10L);
        addressDO.setAddr("test");
        addressDO.setIsDefault(true);
        addressDO.setMobile("13666666666");
        addressDO.setName("test");
        addressDO.setPostcode("553636");
        addressDO.setRegionPath("44/4403/440303");
        addressDO.setRegionName("广东省深圳市南山区");
        addressDOS.add(addressDO);
        List<AddressBO> addressBOS = AddressConverter.convertListBOS(addressDOS);
        Assert.assertNotNull(addressBOS);
        Assert.assertEquals(addressDO.getId(), addressBOS.get(0).getId());
    }

    @Test
    public void convertDTO() {
        AddressBO addressBO = new AddressBO();
        addressBO.setId(10L);
        addressBO.setAddr("test");
        addressBO.setIsDefault(true);
        addressBO.setMobile("13666666666");
        addressBO.setName("test");
        addressBO.setPostcode("553636");
        addressBO.setRegionPath("44/4403/440303");
        addressBO.setRegionName("广东省深圳市南山区");
        AddressDTO addressDTO = AddressConverter.convertDTO(addressBO);
        Assert.assertNotNull(addressDTO);
        Assert.assertEquals(addressBO.getId(), addressDTO.getId());
    }

    @Test
    public void convertListDOTS() {
        List<AddressBO> addressBOS = new ArrayList<>();
        AddressBO addressBO = new AddressBO();
        addressBO.setId(10L);
        addressBO.setAddr("test");
        addressBO.setIsDefault(true);
        addressBO.setMobile("13666666666");
        addressBO.setName("test");
        addressBO.setPostcode("553636");
        addressBO.setRegionPath("44/4403/440303");
        addressBO.setRegionName("广东省深圳市南山区");
        addressBOS.add(addressBO);
        List<AddressDTO> addressDTOS = AddressConverter.convertListDOTS(addressBOS);
        Assert.assertNotNull(addressDTOS);
        Assert.assertEquals(addressBO.getId(), addressDTOS.get(0).getId());
    }

    @Test
    public void convertDO() {
        AddressParam addressParam = new AddressParam();
        addressParam.setAddr("test");
        addressParam.setMobile("13666666666");
        addressParam.setName("test");
        addressParam.setPostcode("553636");
        addressParam.setRegionPath("44/4403/440303");
        addressParam.setRegionName("广东省深圳市南山区");
        AddressDO addressDO = AddressConverter.convertDO(addressParam);
        Assert.assertNotNull(addressDO);
        Assert.assertEquals(addressParam.getAddr(), addressDO.getAddr());
    }

}
