package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.FansMerchantDTO;
import com.lawu.eshop.user.srv.bo.FansMerchantBO;
import com.lawu.eshop.user.srv.domain.FansMerchantDO;
import com.lawu.eshop.user.srv.domain.extend.FansMerchantDOView;
import com.lawu.utils.StringUtil;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class FansMerchantConverterTest {

    @Test
    public void convertBO() {
        FansMerchantDO fansMerchantDO = new FansMerchantDO();
        fansMerchantDO.setMemberId(100L);
        FansMerchantBO fansMerchantBO = FansMerchantConverter.convertBO(fansMerchantDO);
        Assert.assertNotNull(fansMerchantBO);
        Assert.assertEquals(fansMerchantDO.getMemberId(), fansMerchantBO.getMemberId());
    }

    @Test
    public void convertBOS() {
        List<FansMerchantDOView> fansMerchantDOViewList = new ArrayList<>();
        FansMerchantDOView fansMerchantDOView = new FansMerchantDOView();
        fansMerchantDOView.setRegionPath("44/4403/440303");
        fansMerchantDOView.setRegionName("广东省深圳市南山区");
        fansMerchantDOView.setAccount("13666666666");
        fansMerchantDOView.setMemberId(100L);
        fansMerchantDOView.setNum("M0001");
        fansMerchantDOView.setGmtCreate(new Date());
        fansMerchantDOView.setHeadimg("pic");
        fansMerchantDOView.setLevel(1);
        fansMerchantDOView.setNickname("test");
        fansMerchantDOViewList.add(fansMerchantDOView);
        List<FansMerchantBO> fansMerchantBOS = FansMerchantConverter.convertBO(fansMerchantDOViewList);
        Assert.assertNotNull(fansMerchantBOS);
        Assert.assertEquals(fansMerchantDOView.getRegionPath(), fansMerchantBOS.get(0).getRegionPath());
    }

    @Test
    public void convertDTO() {
        List<FansMerchantBO> fansMerchantBOList = new ArrayList<>();
        FansMerchantBO fansMerchantBO = new FansMerchantBO();
        fansMerchantBO.setMemberId(100L);
        fansMerchantBO.setAccount(StringUtil.hideUserAccount("13666666666"));
        fansMerchantBO.setRegionPath("44/4403/440303");
        fansMerchantBO.setRegionName("广东省深圳市南山区");
        fansMerchantBO.setNum("M0001");
        fansMerchantBO.setNickname("test");
        fansMerchantBO.setHeadimg("pic");
        fansMerchantBO.setLevel(1);
        fansMerchantBO.setGmtCreate(new Date());
        fansMerchantBOList.add(fansMerchantBO);
        List<FansMerchantDTO> fansMerchantDTOS = FansMerchantConverter.convertDTO(fansMerchantBOList);
        Assert.assertNotNull(fansMerchantDTOS);
        Assert.assertEquals(fansMerchantBO.getMemberId(), fansMerchantDTOS.get(0).getMemberId());
    }
}
