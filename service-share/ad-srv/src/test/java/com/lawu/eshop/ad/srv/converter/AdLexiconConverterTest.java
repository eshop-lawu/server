package com.lawu.eshop.ad.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.ad.dto.AdLexiconDTO;
import com.lawu.eshop.ad.srv.bo.AdLexiconBO;
import com.lawu.eshop.ad.srv.domain.AdLexiconDO;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdLexiconConverterTest {

	@Test
    public void convertBOS(){
		List<AdLexiconDO> list =new ArrayList<>();
		AdLexiconDO adLexiconDO =new AdLexiconDO();
		adLexiconDO.setGmtCreate(new Date());
		adLexiconDO.setId(1l);
		adLexiconDO.setTitle("测试");
		list.add(adLexiconDO);
		
		List<AdLexiconBO>  boList = AdLexiconConverter.convertBOS(list);
        
        Assert.assertNotNull(boList);
    }
	
	@Test
    public void convertDTOS(){
		List<AdLexiconBO> list =new ArrayList<>();
		AdLexiconBO adLexiconBO =new AdLexiconBO();
		adLexiconBO.setTitle("测试");
		list.add(adLexiconBO);
		
		List<AdLexiconDTO>  dtoList = AdLexiconConverter.convertDTOS(list);
        
        Assert.assertNotNull(dtoList);
    }
}
