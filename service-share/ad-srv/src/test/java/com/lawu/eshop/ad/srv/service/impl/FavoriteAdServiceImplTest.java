package com.lawu.eshop.ad.srv.service.impl;

import java.math.BigDecimal;
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

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.param.PraiseWarnParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.FavoriteAdPraiseWarnBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.FavoriteAdDO;
import com.lawu.eshop.ad.srv.domain.FavoriteAdDOExample;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.FavoriteAdDOMapper;
import com.lawu.eshop.ad.srv.service.FavoriteAdService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.utils.DateUtil;

/**
 * @author zhangrc
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class FavoriteAdServiceImplTest {

    @Autowired
    private FavoriteAdService favoriteAdService;

    @Autowired
    private FavoriteAdDOMapper favoriteAdDOMapper;
    
    @Autowired
    private AdDOMapper adDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {

    	favoriteAdService.save(1l, 1l,"M000001");

        List<FavoriteAdDO> dos = favoriteAdDOMapper.selectByExample(null);
        Assert.assertNotNull(dos);
        Assert.assertTrue(dos.size() == 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void remove() {
    	favoriteAdService.save(1l, 1l,"M000001");
    	favoriteAdService.remove(1l, 1l);
    	List<FavoriteAdDO> dos = favoriteAdDOMapper.selectByExample(null);
        Assert.assertTrue(dos.size() == 0);

    }
    
    /*@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMyFavoriteAd() {
    	AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
    	favoriteAdService.save(ad.getId(), 1l,"M000001");
    	
    	FavoriteAdParam paramQuery=new FavoriteAdParam();
    	paramQuery.setCurrentPage(1);
    	paramQuery.setPageSize(10);
    	paramQuery.setTypeEnum(FavoriteAdTypeEnum.AD_TYPE_EGAIN);
    	Page<FavoriteAdDOViewBO> page= favoriteAdService.selectMyFavoriteAd(paramQuery, 1l);
    	Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);

    }
    */
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void isFavoriteAd() {

    	favoriteAdService.save(1l, 1l,"M000001");

        Boolean flag = favoriteAdService.isFavoriteAd(1l, 1l);
        Assert.assertNotNull(flag);
        Assert.assertTrue(flag);
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectFavoriteAdPraise() {
    	AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(DateUtil.stringToDate("2017-08-08 22:04:00"));
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        adDOMapper.insertSelective(ad);
        
        FavoriteAdDO faDO = new FavoriteAdDO();
        faDO.setAdId(ad.getId());
        faDO.setGmtCreate(new Date());
        faDO.setIsSend(false);
        faDO.setMemberId(1l);
        faDO.setMemberNum("M000001");
    	favoriteAdDOMapper.insert(faDO);
    	PraiseWarnParam param = new PraiseWarnParam();
    	param.setCurrentPage(1);
    	param.setPageSize(20);
    	List<FavoriteAdPraiseWarnBO>  list = favoriteAdService.selectFavoriteAdPraise( param);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size()>0);
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateIsSend() {
    	FavoriteAdDO favoriteAd=new FavoriteAdDO();
		favoriteAd.setAdId(1l);
		favoriteAd.setMemberId(1l);
		favoriteAd.setGmtCreate(new Date());
		favoriteAd.setMemberNum("M000001");
		favoriteAdDOMapper.insert(favoriteAd);
		favoriteAdService.updateIsSend(favoriteAd.getId());
		
		FavoriteAdDOExample example = new FavoriteAdDOExample();
		example.createCriteria().andIdEqualTo(favoriteAd.getId()).andIsSendEqualTo(true);
    	List<FavoriteAdDO> dos = favoriteAdDOMapper.selectByExample(example);
    	Assert.assertNotNull(dos);
        Assert.assertTrue(dos.size() >= 0);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getFavoriteCount() {
        FavoriteAdDO favoriteAd = new FavoriteAdDO();
        favoriteAd.setAdId(1l);
        favoriteAd.setMemberId(1l);
        favoriteAd.setGmtCreate(new Date());
        favoriteAd.setMemberNum("M000001");
        favoriteAdDOMapper.insertSelective(favoriteAd);

        int favoriteCount = favoriteAdService.getFavoriteCount(favoriteAd.getAdId());
        Assert.assertNotNull(favoriteCount);
        Assert.assertEquals(1, favoriteCount);
    }
    
}
