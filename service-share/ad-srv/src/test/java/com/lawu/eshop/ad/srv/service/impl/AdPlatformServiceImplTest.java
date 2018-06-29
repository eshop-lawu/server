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

import com.lawu.eshop.ad.constants.AdPlatformStatusEnum;
import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.param.AdPlatformFindParam;
import com.lawu.eshop.ad.param.AdPlatformParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.AdPlatformBO;
import com.lawu.eshop.ad.srv.domain.AdPlatformDO;
import com.lawu.eshop.ad.srv.domain.AdPlatformDOExample;
import com.lawu.eshop.ad.srv.mapper.AdPlatformDOMapper;
import com.lawu.eshop.ad.srv.service.AdPlatformService;
import com.lawu.framework.core.page.Page;

/**
 * 广告位测试
 * @author zhangrc
 * @date 2017/07/12
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class AdPlatformServiceImplTest {
	
	@Autowired
	private AdPlatformDOMapper adPlatformDOMapper;
	
	
	@Autowired
	private AdPlatformService adPlatformService;
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveAdPlatform() {
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("看广告顶部广告测试");
		adPlatformParam.setPositionEnum(PositionEnum.POSITON_AD_TOP);
		adPlatformParam.setLinkUrl("www.baidu.com");
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_LINK);
		adPlatformParam.setMediaUrl("ad_image/1494897983991428080.jpg");

		adPlatformService.saveAdPlatform(adPlatformParam);

        List<AdPlatformDO> adPlatformDOS = adPlatformDOMapper.selectByExample(null);
        Assert.assertNotNull(adPlatformDOS);
        Assert.assertTrue(adPlatformDOS.size() == 1);
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void removeAdPlatform() {
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("要购物热门商品");
		adPlatformParam.setPositionEnum(PositionEnum.SHOPPING_HOT);
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_PRODUCT);
		adPlatformParam.setProductId(10061l);
		adPlatformParam.setMediaUrl("ad_image/1494897983991428080.jpg");

		adPlatformService.saveAdPlatform(adPlatformParam);
		
		List<AdPlatformDO> list = adPlatformDOMapper.selectByExample(null);
		
		adPlatformService.removeAdPlatform(list.get(0).getId());
		
		AdPlatformDOExample example=new AdPlatformDOExample();
		example.createCriteria().andStatusEqualTo(AdPlatformStatusEnum.DELETE.val);
		

        List<AdPlatformDO> adPlatformDOS = adPlatformDOMapper.selectByExample(example);
        Assert.assertNotNull(adPlatformDOS);
        Assert.assertTrue(adPlatformDOS.size() == 1);
    }
	
	
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByPosition() {
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("要购物热门商品");
		adPlatformParam.setPositionEnum(PositionEnum.SHOPPING_HOT);
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_PRODUCT);
		adPlatformParam.setProductId(10061l);
		adPlatformParam.setMediaUrl("ad_image/1494897983991428080.jpg");

		adPlatformService.saveAdPlatform(adPlatformParam);

		List<AdPlatformBO> list=adPlatformService.selectByPosition(PositionEnum.SHOPPING_HOT);
		
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
		
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectList() {
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("要购物热门商品");
		adPlatformParam.setPositionEnum(PositionEnum.SHOPPING_HOT);
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_PRODUCT);
		adPlatformParam.setProductId(10061l);
		adPlatformParam.setMediaUrl("ad_image/1494897983991428080.jpg");

		adPlatformService.saveAdPlatform(adPlatformParam);
		
		
		AdPlatformFindParam param=new AdPlatformFindParam();
		param.setCurrentPage(1);
		param.setPageSize(20);
		param.setTitle("要购物");
		param.setPositionEnum(PositionEnum.SHOPPING_HOT);

		 Page<AdPlatformBO> page = adPlatformService.selectList(param);
		
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() == 1);
		
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void issueAd() {
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("要购物热门商品");
		adPlatformParam.setPositionEnum(PositionEnum.SHOPPING_HOT);
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_PRODUCT);
		adPlatformParam.setProductId(10061l);
		adPlatformParam.setMediaUrl("ad_image/1494897983991428080.jpg");

		adPlatformService.saveAdPlatform(adPlatformParam);
		

		List<AdPlatformBO> list=adPlatformService.selectByPosition(PositionEnum.SHOPPING_HOT);
		
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
		
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void update() {
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("看广告顶部广告测试");
		adPlatformParam.setPositionEnum(PositionEnum.POSITON_AD_TOP);
		adPlatformParam.setLinkUrl("www.baidu.com");
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_LINK);

		adPlatformService.saveAdPlatform(adPlatformParam);
		
		AdPlatformParam adPlatform=new AdPlatformParam();
		adPlatform.setTitle("要购物热门商品");
		adPlatform.setPositionEnum(PositionEnum.SHOPPING_HOT);
		adPlatform.setContent("测试");
		adPlatform.setTypeEnum(TypeEnum.TYPE_PRODUCT);
		adPlatform.setProductId(10061l);
		
		List<AdPlatformDO> list = adPlatformDOMapper.selectByExample(null);
		
		adPlatformService.update(list.get(0).getId(), adPlatform);
		
		AdPlatformDOExample example=new AdPlatformDOExample();
		example.createCriteria().andPositionEqualTo(PositionEnum.SHOPPING_HOT.val);

        List<AdPlatformDO> adPlatformDOS = adPlatformDOMapper.selectByExample(example);
        Assert.assertNotNull(adPlatformDOS);
        Assert.assertTrue(adPlatformDOS.size() == 1);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void select() {
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("看广告顶部广告测试");
		adPlatformParam.setPositionEnum(PositionEnum.POSITON_AD_TOP);
		adPlatformParam.setLinkUrl("www.baidu.com");
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_LINK);

		adPlatformService.saveAdPlatform(adPlatformParam);
		
		List<AdPlatformDO> list = adPlatformDOMapper.selectByExample(null);
		
		AdPlatformBO bo=adPlatformService.select(list.get(0).getId());
		
        Assert.assertNotNull(bo);
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void unShelve() {
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("要购物热门商品");
		adPlatformParam.setPositionEnum(PositionEnum.SHOPPING_HOT);
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_PRODUCT);
		adPlatformParam.setProductId(10061l);

		adPlatformService.saveAdPlatform(adPlatformParam);
		
		List<AdPlatformDO> list = adPlatformDOMapper.selectByExample(null);
		adPlatformService.unShelve(list.get(0).getId());

		AdPlatformDOExample example=new AdPlatformDOExample();
		example.createCriteria().andStatusEqualTo(AdPlatformStatusEnum.DOWN.val);
		
		List<AdPlatformDO> bos = adPlatformDOMapper.selectByExample(example);
		
        Assert.assertNotNull(bos);
        Assert.assertTrue(bos.size() == 1);
		
    }

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void onShelve() {
		
		AdPlatformDO adPlatformDO=new AdPlatformDO();
		adPlatformDO.setTitle("要购物热门商品ss");
		adPlatformDO.setPosition(PositionEnum.SHOPPING_HOT.val);
		adPlatformDO.setContent("测试");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		adPlatformDO.setProductId(10061l);
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setStatus(AdPlatformStatusEnum.DOWN.val);
		adPlatformDO.setMediaUrl("ad_image/1494240515322705608.jpg");

		adPlatformDOMapper.insert(adPlatformDO);
		
		adPlatformService.onShelve(adPlatformDO.getId());

		AdPlatformDOExample example=new AdPlatformDOExample();
		example.createCriteria().andStatusEqualTo(AdPlatformStatusEnum.UP.val);
		
		List<AdPlatformDO> bos = adPlatformDOMapper.selectByExample(example);
		
        Assert.assertNotNull(bos);
        Assert.assertTrue(bos.size() == 1);
		
    }

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAdPlatformByTypePositionRegionPath() {
		
		AdPlatformDO adPlatformDO=new AdPlatformDO();
		adPlatformDO.setTitle("要购物热门商品ss");
		adPlatformDO.setPosition(PositionEnum.SHOPPING_HOT.val);
		adPlatformDO.setContent("测试");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		adPlatformDO.setProductId(10061l);
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setStatus(AdPlatformStatusEnum.UP.val);
		adPlatformDO.setMediaUrl("ad_image/1494240515322705608.jpg");
		adPlatformDO.setRegionPath("44/4403/440305");

		adPlatformDOMapper.insert(adPlatformDO);
		
		 List<AdPlatformBO>  list=adPlatformService.getAdPlatformByTypePositionRegionPath(TypeEnum.TYPE_PRODUCT, PositionEnum.SHOPPING_HOT, "44/4403/440305");
		
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
		
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAdPlatformByTypePosition() {
		
		AdPlatformDO adPlatformDO=new AdPlatformDO();
		adPlatformDO.setTitle("要购物热门商品ss");
		adPlatformDO.setPosition(PositionEnum.SHOPPING_HOT.val);
		adPlatformDO.setContent("测试");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		adPlatformDO.setProductId(10061l);
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setStatus(AdPlatformStatusEnum.UP.val);
		adPlatformDO.setMediaUrl("ad_image/1494240515322705608.jpg");
		adPlatformDO.setRegionPath("44/4403/440305");

		adPlatformDOMapper.insert(adPlatformDO);
		
		 List<AdPlatformBO>  list=adPlatformService.getAdPlatformByTypePosition(TypeEnum.TYPE_PRODUCT, PositionEnum.SHOPPING_HOT);
		
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
		
    }

	
}
