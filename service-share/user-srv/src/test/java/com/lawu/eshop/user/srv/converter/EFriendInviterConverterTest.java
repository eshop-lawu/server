package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.EFriendInviterDTO;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.srv.bo.EFriendInviterBO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class EFriendInviterConverterTest {

    @Test
    public void converter() {
        List<EFriendInviterBO> records = new ArrayList<>();
        EFriendInviterBO bo = new EFriendInviterBO();
        bo.setHeadImg("img");
        bo.setTitleName("titlename");
        bo.setMerchantStatus(MerchantStatusEnum.MERCHANT_STATUS_CANCEL);
        bo.setAccount("account");
        bo.setGmtCreate(new Date());
        bo.setInviterCount(1);
        bo.setLevel(1);
        bo.setName("name");
        bo.setRegionAddress("regionAddress");
        bo.setTotalInviteCount(1);
        records.add(bo);
        List<EFriendInviterDTO> rtnRecords = EFriendInviterConverter.converter(records);
        Assert.assertNotNull(rtnRecords);
        Assert.assertEquals(rtnRecords.get(0).getAccount(), records.get(0).getAccount());
    }

}
