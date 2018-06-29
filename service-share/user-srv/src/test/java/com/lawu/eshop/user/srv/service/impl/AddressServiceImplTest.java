package com.lawu.eshop.user.srv.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.user.param.AddressParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.AddressBO;
import com.lawu.eshop.user.srv.domain.AddressDO;
import com.lawu.eshop.user.srv.mapper.AddressDOMapper;
import com.lawu.eshop.user.srv.service.AddressService;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressDOMapper addressDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void update() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        addressService.saveWithUserNum("M0001", param);

        List<AddressDO> adLexiconDOS = addressDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);

        param.setPostcode("666666");
        int result = addressService.update(param, adLexiconDOS.get(0).getId(), adLexiconDOS.get(0).getUserNum());
        Assert.assertEquals(1, result);

        adLexiconDOS = addressDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);
        Assert.assertTrue(param.getPostcode().equals(adLexiconDOS.get(0).getPostcode()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        addressService.saveWithUserNum("M0001", param);

        List<AddressDO> adLexiconDOS = addressDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);
        AddressBO addressBO = addressService.get(adLexiconDOS.get(0).getId());
        Assert.assertNotNull(addressBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void remove() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        addressService.saveWithUserNum("M0001", param);

        List<AddressDO> adLexiconDOS = addressDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);

        int result = addressService.remove(adLexiconDOS.get(0).getId());
        Assert.assertEquals(1, result);

        adLexiconDOS = addressDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);
        Assert.assertTrue(adLexiconDOS.get(0).getStatus() == 0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateDefault() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        addressService.saveWithUserNum("M0001", param);

        List<AddressDO> adLexiconDOS = addressDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);

        int result = addressService.updateDefault(adLexiconDOS.get(0).getId(), adLexiconDOS.get(0).getUserNum());
        Assert.assertEquals(1, result);

        AddressDO addressDO = addressDOMapper.selectByPrimaryKey(adLexiconDOS.get(0).getId());
        Assert.assertNotNull(addressDO);
        Assert.assertTrue(addressDO.getIsDefault());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByUserNum() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        addressService.saveWithUserNum("M0001", param);

        List<AddressBO> addressBOS = addressService.selectByUserNum("M0001");
        Assert.assertNotNull(addressBOS);
        Assert.assertEquals(1, addressBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveWithUserNum() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        addressService.saveWithUserNum("M0001", param);

        List<AddressDO> adLexiconDOS = addressDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);
        Assert.assertEquals(1, adLexiconDOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void isCheckAddress() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        addressService.saveWithUserNum("M0001", param);

        List<AddressDO> adLexiconDOS = addressDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);
        boolean result = addressService.isCheckAddress(adLexiconDOS.get(0).getId(), adLexiconDOS.get(0).getUserNum());
        Assert.assertTrue(result);
    }
}
