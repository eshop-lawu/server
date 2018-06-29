package com.lawu.eshop.mall.srv.service.impl;

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

import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.DiscountPackagePurchaseNotesBO;
import com.lawu.eshop.mall.srv.domain.DiscountPackagePurchaseNotesDO;
import com.lawu.eshop.mall.srv.mapper.DiscountPackagePurchaseNotesDOMapper;
import com.lawu.eshop.mall.srv.service.DiscountPackagePurchaseNotesService;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月31日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class DiscountPackagePurchaseNotesServiceImplTest {
	
    @Autowired
    private DiscountPackagePurchaseNotesService discountPackagePurchaseNotesService;
	
    @Autowired
    private DiscountPackagePurchaseNotesDOMapper discountPackagePurchaseNotesDOMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void list() {
    	DiscountPackagePurchaseNotesDO expected = new DiscountPackagePurchaseNotesDO();
    	expected.setGmtCreate(new Date());
    	expected.setNote("免费停车");
    	discountPackagePurchaseNotesDOMapper.insert(expected);
    	List<DiscountPackagePurchaseNotesBO> actual = discountPackagePurchaseNotesService.list();
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.size());
        assertDiscountPackagePurchaseNotesBO(expected, actual.get(0));
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listByIdsStr() {
    	DiscountPackagePurchaseNotesDO expected = new DiscountPackagePurchaseNotesDO();
    	expected.setGmtCreate(new Date());
    	expected.setNote("免费停车");
    	discountPackagePurchaseNotesDOMapper.insert(expected);
    	List<DiscountPackagePurchaseNotesBO> actual = discountPackagePurchaseNotesService.list(expected.getId().toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.size());
        assertDiscountPackagePurchaseNotesBO(expected, actual.get(0));
    }
    
    private void assertDiscountPackagePurchaseNotesBO(DiscountPackagePurchaseNotesDO expected, DiscountPackagePurchaseNotesBO actual) {
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getNote(), actual.getNote());
    	Assert.assertEquals(expected.getGmtCreate(), actual.getGmtCreate());
    }
}
