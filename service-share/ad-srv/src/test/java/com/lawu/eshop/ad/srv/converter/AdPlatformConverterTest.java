package com.lawu.eshop.ad.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.dto.AdPlatformDTO;
import com.lawu.eshop.ad.dto.AdPlatformOperatorDTO;
import com.lawu.eshop.ad.dto.AdPlatformProductDTO;
import com.lawu.eshop.ad.srv.bo.AdPlatformBO;
import com.lawu.eshop.ad.srv.domain.AdPlatformDO;
import com.lawu.eshop.ad.srv.domain.extend.AdPlatformDOView;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdPlatformConverterTest {

	@Test
    public void convertBO(){
		
		AdPlatformDO adPlatformDO = new AdPlatformDO();
		adPlatformDO.setContent("运营广告测试");
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setId(1l);
		adPlatformDO.setMediaUrl("adPlatform/11111.png");
		adPlatformDO.setMerchantStoreId(1l);
		adPlatformDO.setPosition(PositionEnum.POSITON_AD_TOP.val);
		adPlatformDO.setProductId(1l);
		adPlatformDO.setRegionPath("11/1101");
		adPlatformDO.setStatus(new Byte("1"));
		adPlatformDO.setTitle("aaa");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		
		 AdPlatformBO  bo=AdPlatformConverter.convertBO(adPlatformDO);
		
        Assert.assertNotNull(bo);
    }
	
	@Test
    public void convertView(){
		
		AdPlatformDOView view =new AdPlatformDOView();
		view.setMediaUrl("adPlatform/11111.png");
		view.setProductId(1l);
		view.setTitle("aaa");
		List<AdPlatformDOView> list=new ArrayList<>();
		list.add(view);
		List<AdPlatformBO>  boList = AdPlatformConverter.convertBO(list);
        Assert.assertNotNull(boList);
    }
	
	@Test
    public void convertDTO(){
		
		AdPlatformBO adPlatformBO = new AdPlatformBO();
		adPlatformBO.setContent("运营广告测试");
		adPlatformBO.setGmtCreate(new Date());
		adPlatformBO.setId(1l);
		adPlatformBO.setMediaUrl("adPlatform/11111.png");
		adPlatformBO.setMerchantStoreId(1l);
		adPlatformBO.setPositionEnum(PositionEnum.POSITON_AD_TOP);
		adPlatformBO.setProductId(1l);
		adPlatformBO.setRegionPath("11/1101");
		adPlatformBO.setStatus(new Byte("1"));
		adPlatformBO.setTitle("aaa");
		adPlatformBO.setType(TypeEnum.TYPE_PRODUCT.val);
		
		AdPlatformDTO  dto=AdPlatformConverter.convertDTO(adPlatformBO);
		
        Assert.assertNotNull(dto);
    }
	
	
	@Test
    public void convertBOS(){
		
		AdPlatformDO adPlatformDO = new AdPlatformDO();
		adPlatformDO.setContent("运营广告测试");
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setId(1l);
		adPlatformDO.setMediaUrl("adPlatform/11111.png");
		adPlatformDO.setMerchantStoreId(1l);
		adPlatformDO.setPosition(PositionEnum.POSITON_AD_TOP.val);
		adPlatformDO.setProductId(1l);
		adPlatformDO.setRegionPath("11/1101");
		adPlatformDO.setStatus(new Byte("1"));
		adPlatformDO.setTitle("aaa");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		
		List<AdPlatformDO> list =new ArrayList<>();
		list.add(adPlatformDO);
		
		List<AdPlatformBO>  boList=AdPlatformConverter.convertBOS(list);
		
        Assert.assertNotNull(boList);
    }
	
	@Test
    public void convertDTOS(){
		
		AdPlatformBO adPlatformBO = new AdPlatformBO();
		adPlatformBO.setContent("运营广告测试");
		adPlatformBO.setGmtCreate(new Date());
		adPlatformBO.setId(1l);
		adPlatformBO.setMediaUrl("adPlatform/11111.png");
		adPlatformBO.setMerchantStoreId(1l);
		adPlatformBO.setPositionEnum(PositionEnum.POSITON_AD_TOP);
		adPlatformBO.setProductId(1l);
		adPlatformBO.setRegionPath("11/1101");
		adPlatformBO.setStatus(new Byte("1"));
		adPlatformBO.setTitle("aaa");
		adPlatformBO.setType(TypeEnum.TYPE_PRODUCT.val);
		
		List<AdPlatformBO> list =new ArrayList<>();
		list.add(adPlatformBO);
		
		List<AdPlatformDTO> DTOS=AdPlatformConverter.convertDTOS(list);
		
        Assert.assertNotNull(DTOS);
    }
	
	
	@Test
    public void convertOperatorDTOS(){
		
		AdPlatformBO adPlatformBO = new AdPlatformBO();
		adPlatformBO.setContent("运营广告测试");
		adPlatformBO.setGmtCreate(new Date());
		adPlatformBO.setId(1l);
		adPlatformBO.setMediaUrl("adPlatform/11111.png");
		adPlatformBO.setMerchantStoreId(1l);
		adPlatformBO.setPositionEnum(PositionEnum.POSITON_AD_TOP);
		adPlatformBO.setProductId(1l);
		adPlatformBO.setRegionPath("11/1101");
		adPlatformBO.setStatus(new Byte("1"));
		adPlatformBO.setTitle("aaa");
		adPlatformBO.setType(TypeEnum.TYPE_PRODUCT.val);
		
		List<AdPlatformBO> list =new ArrayList<>();
		list.add(adPlatformBO);
		
		List<AdPlatformOperatorDTO> DTOS=AdPlatformConverter.convertOperatorDTOS(list);
		
        Assert.assertNotNull(DTOS);
    }
	
	@Test
    public void convertOperatorDTO(){
		
		AdPlatformBO adPlatformBO = new AdPlatformBO();
		adPlatformBO.setContent("运营广告测试");
		adPlatformBO.setGmtCreate(new Date());
		adPlatformBO.setId(1l);
		adPlatformBO.setMediaUrl("adPlatform/11111.png");
		adPlatformBO.setMerchantStoreId(1l);
		adPlatformBO.setPositionEnum(PositionEnum.POSITON_AD_TOP);
		adPlatformBO.setProductId(1l);
		adPlatformBO.setRegionPath("11/1101");
		adPlatformBO.setStatus(new Byte("1"));
		adPlatformBO.setTitle("aaa");
		adPlatformBO.setType(TypeEnum.TYPE_PRODUCT.val);
		
		
	    AdPlatformOperatorDTO dto=AdPlatformConverter.convertOperatorDTO(adPlatformBO);
		
        Assert.assertNotNull(dto);
    }
	
	@Test
    public void convertAdDTO(){
		
		AdPlatformBO adPlatformBO = new AdPlatformBO();
		adPlatformBO.setContent("运营广告测试");
		adPlatformBO.setGmtCreate(new Date());
		adPlatformBO.setId(1l);
		adPlatformBO.setMediaUrl("adPlatform/11111.png");
		adPlatformBO.setMerchantStoreId(1l);
		adPlatformBO.setPositionEnum(PositionEnum.POSITON_AD_TOP);
		adPlatformBO.setProductId(1l);
		adPlatformBO.setRegionPath("11/1101");
		adPlatformBO.setStatus(new Byte("1"));
		adPlatformBO.setTitle("aaa");
		adPlatformBO.setType(TypeEnum.TYPE_PRODUCT.val);
		
		List<AdPlatformBO> list =new ArrayList<>();
		list.add(adPlatformBO);
		
		List<AdPlatformProductDTO> adPlatformProductDTOS=AdPlatformConverter.convertDTO(list);
		
        Assert.assertNotNull(adPlatformProductDTOS);
    }
}
