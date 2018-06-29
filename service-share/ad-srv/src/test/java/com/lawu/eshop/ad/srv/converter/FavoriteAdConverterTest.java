package com.lawu.eshop.ad.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.ad.constants.FavoriteTypeEnum;
import com.lawu.eshop.ad.dto.FavoriteAdDOViewDTO;
import com.lawu.eshop.ad.srv.bo.FavoriteAdDOViewBO;
import com.lawu.eshop.ad.srv.domain.extend.FavoriteAdDOView;
import com.lawu.eshop.common.constants.AdTypeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoriteAdConverterTest {
	
	@Test
    public void convertBO(){
		
		FavoriteAdDOView favoriteAdDOView =new FavoriteAdDOView();
		favoriteAdDOView.setAdId(1l);
		favoriteAdDOView.setContent("aaaa");
		favoriteAdDOView.setId(1l);
		favoriteAdDOView.setMediaUrl("ad/111.png");
		favoriteAdDOView.setMemberId(1l);
		favoriteAdDOView.setMerchantId(1l);
		favoriteAdDOView.setStatus(new Byte("1"));
		favoriteAdDOView.setTitle("广告标题");
		favoriteAdDOView.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		favoriteAdDOView.setVideoImgUrl("ad/111.png");
		
		FavoriteAdDOViewBO  bo=FavoriteAdConverter.convertBO(favoriteAdDOView);
		
        Assert.assertNotNull(bo);
    }
	
	@Test
    public void convertBOS(){
		
		FavoriteAdDOView favoriteAdDOView =new FavoriteAdDOView();
		favoriteAdDOView.setAdId(1l);
		favoriteAdDOView.setContent("aaaa");
		favoriteAdDOView.setId(1l);
		favoriteAdDOView.setMediaUrl("ad/111.png");
		favoriteAdDOView.setMemberId(1l);
		favoriteAdDOView.setMerchantId(1l);
		favoriteAdDOView.setStatus(new Byte("1"));
		favoriteAdDOView.setTitle("广告标题");
		favoriteAdDOView.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		favoriteAdDOView.setVideoImgUrl("ad/111.png");
		
		List<FavoriteAdDOView> favoriteAdDOViews =new ArrayList<>();
		favoriteAdDOViews.add(favoriteAdDOView);
		List<FavoriteAdDOViewBO> BOS=FavoriteAdConverter.convertBOS(favoriteAdDOViews);
		
        Assert.assertNotNull(BOS);
    }

	@Test
    public void convertDTO(){
		
		FavoriteAdDOViewBO favoriteAdDOViewBO =new FavoriteAdDOViewBO();
		favoriteAdDOViewBO.setAdId(1l);
		favoriteAdDOViewBO.setContent("aaaa");
		favoriteAdDOViewBO.setId(1l);
		favoriteAdDOViewBO.setMediaUrl("ad/111.png");
		favoriteAdDOViewBO.setTitle("广告标题");
		favoriteAdDOViewBO.setTypeEnum(FavoriteTypeEnum.AD_TYPE_FLAT);
		favoriteAdDOViewBO.setVideoImgUrl("ad/111.png");
		
		FavoriteAdDOViewDTO  dto=FavoriteAdConverter.convertDTO(favoriteAdDOViewBO);
		
        Assert.assertNotNull(dto);
    }
	
	@Test
    public void convertDTOS(){
		
		FavoriteAdDOViewBO favoriteAdDOViewBO =new FavoriteAdDOViewBO();
		favoriteAdDOViewBO.setAdId(1l);
		favoriteAdDOViewBO.setContent("aaaa");
		favoriteAdDOViewBO.setId(1l);
		favoriteAdDOViewBO.setMediaUrl("ad/111.png");
		favoriteAdDOViewBO.setTitle("广告标题");
		favoriteAdDOViewBO.setTypeEnum(FavoriteTypeEnum.AD_TYPE_FLAT);
		favoriteAdDOViewBO.setVideoImgUrl("ad/111.png");
		
		List<FavoriteAdDOViewBO> favoriteAdDOViewBOS = new ArrayList<>();
		favoriteAdDOViewBOS.add(favoriteAdDOViewBO);
		
		List<FavoriteAdDOViewDTO>  dtoList=FavoriteAdConverter.convertDTOS(favoriteAdDOViewBOS);
		
        Assert.assertNotNull(dtoList);
    }
}
