package com.lawu.eshop.ad.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.AdLexiconBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.AdLexiconDO;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.AdLexiconDOMapper;
import com.lawu.eshop.ad.srv.service.AdLexiconService;
import com.lawu.eshop.common.constants.ManageTypeEnum;

/**
 * @author Leach
 * @date 2017/7/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class AdLexiconServiceImplTest {

    @Autowired
    private AdLexiconService adLexiconService;

    @Autowired
    private AdDOMapper adDOMapper;

    @Autowired
    private AdLexiconDOMapper adLexiconDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {

        adLexiconService.save("save-title");

        List<AdLexiconDO> adLexiconDOS = adLexiconDOMapper.selectByExample(null);
        Assert.assertNotNull(adLexiconDOS);
        Assert.assertTrue(adLexiconDOS.size() == 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectList() {

        adLexiconService.save("selectList-title");
        adLexiconService.save("selectList-title1");
        adLexiconService.save("selectList-title2");
        adLexiconService.save("selectList-title3");
        adLexiconService.save("selectList-title4");

        AdDO adRecord = new AdDO();
        adRecord.setGmtCreate(new Date());
        adRecord.setGmtModified(new Date());
        adRecord.setAdCount(1);
        adRecord.setAreas("44");
        adRecord.setTitle("selectList-title");
        adRecord.setMerchantStoreId(1001l);
        adRecord.setMerchantStoreName("E店商家");
        adRecord.setManageType(ManageTypeEnum.ENTITY.getVal());
        adRecord.setLogoUrl("store/1494582624025648402.png");
        long adId = adDOMapper.insert(adRecord);
        Assert.assertTrue( adId > 0);

        List<AdLexiconBO> adLexiconBOS = adLexiconService.selectList(adRecord.getId());
        Assert.assertNotNull(adLexiconBOS);
        Assert.assertTrue(adLexiconBOS.size() == 4);

    }
}
