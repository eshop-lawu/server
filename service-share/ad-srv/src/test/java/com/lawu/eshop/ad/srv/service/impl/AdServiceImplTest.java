package com.lawu.eshop.ad.srv.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.lawu.eshop.ad.constants.AdEgainTypeEnum;
import com.lawu.eshop.ad.constants.AdPayTypeEnum;
import com.lawu.eshop.ad.constants.AdPraiseStatusEnum;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.AuditEnum;
import com.lawu.eshop.ad.constants.ClientTypeEnum;
import com.lawu.eshop.ad.constants.MemberAdRecordStatusEnum;
import com.lawu.eshop.ad.constants.OrderTypeEnum;
import com.lawu.eshop.ad.constants.PointPoolStatusEnum;
import com.lawu.eshop.ad.constants.PointPoolTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.ad.param.AdFindParam;
import com.lawu.eshop.ad.param.AdMemberParam;
import com.lawu.eshop.ad.param.AdMerchantParam;
import com.lawu.eshop.ad.param.AdParam;
import com.lawu.eshop.ad.param.AdPraiseParam;
import com.lawu.eshop.ad.param.AdSaveParam;
import com.lawu.eshop.ad.param.AdSetPayParam;
import com.lawu.eshop.ad.param.ListAdParam;
import com.lawu.eshop.ad.param.OperatorAdParam;
import com.lawu.eshop.ad.param.PointGetDetailParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.AdBO;
import com.lawu.eshop.ad.srv.bo.AdClickPraiseInfoBO;
import com.lawu.eshop.ad.srv.bo.AdDetailBO;
import com.lawu.eshop.ad.srv.bo.AdEgainDetailBO;
import com.lawu.eshop.ad.srv.bo.AdPraiseBO;
import com.lawu.eshop.ad.srv.bo.ClickAdPointBO;
import com.lawu.eshop.ad.srv.bo.ClickPointBO;
import com.lawu.eshop.ad.srv.bo.MerchantInfoBO;
import com.lawu.eshop.ad.srv.bo.OperatorAdBO;
import com.lawu.eshop.ad.srv.bo.PointGetDetailBO;
import com.lawu.eshop.ad.srv.bo.RedPacketInfoBO;
import com.lawu.eshop.ad.srv.bo.RedPacketIsSendBO;
import com.lawu.eshop.ad.srv.bo.ReportAdBO;
import com.lawu.eshop.ad.srv.bo.ViewBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.AdDOExample;
import com.lawu.eshop.ad.srv.domain.FavoriteAdDO;
import com.lawu.eshop.ad.srv.domain.MemberAdRecordDO;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.FavoriteAdDOMapper;
import com.lawu.eshop.ad.srv.mapper.MemberAdRecordDOMapper;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.eshop.ad.srv.service.AdService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.framework.core.page.Page;

/**
 * 广告测试
 * @author zhangrc
 * @date 2017/07/12
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class AdServiceImplTest {
	
	@Autowired
	private AdDOMapper adDOMapper;
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private FavoriteAdDOMapper favoriteAdDOMapper;
	
	@Autowired
	private PointPoolDOMapper pointPoolDOMapper;
	
	@Autowired
	private MemberAdRecordDOMapper memberAdRecordDOMapper;
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {

		AdSaveParam adSaveParam=new AdSaveParam();
    	adSaveParam.setLatitude(BigDecimal.valueOf(22.547153));
    	adSaveParam.setLongitude(BigDecimal.valueOf(113.960333));
    	adSaveParam.setMerchantId(1002l);
    	adSaveParam.setUserNum("B856392484215848969");
    	adSaveParam.setMediaUrl("ad_image/1494582624025648401.png");
    	adSaveParam.setLogoUrl("store/1494582624025648402.png");
    	adSaveParam.setMerchantStoreId(1001l);
    	adSaveParam.setMerchantStoreName("E店商家");
    	adSaveParam.setManageType(ManageTypeEnum.ENTITY);
    	adSaveParam.setCount(100);
    	adSaveParam.setClentType(ClientTypeEnum.MOBLIE);
    	AdParam param=new AdParam();
    	param.setAdCount(20);
    	param.setBeginTime("2017-05-17 11:35:00");
    	param.setContent("广告测试内容");
    	param.setPoint(BigDecimal.valueOf(0.5));
    	param.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
    	param.setRegionName("全国");
    	param.setTitle("广告测试标题");
    	param.setTotalPoint(BigDecimal.valueOf(100));
    	param.setTypeEnum(AdTypeEnum.AD_TYPE_PRAISE);
    	param.setRelateType(RelateTypeEnum.PRODUCT_TYPE);
    	adSaveParam.setAdParam(param);
    	adService.saveAd(adSaveParam);

        List<AdDO> adDOS = adDOMapper.selectByExample(null);
        Assert.assertNotNull(adDOS);
        Assert.assertTrue(adDOS.size() >0);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveComPacket() {

		AdSaveParam adSaveParam=new AdSaveParam();
    	adSaveParam.setLatitude(BigDecimal.valueOf(22.547153));
    	adSaveParam.setLongitude(BigDecimal.valueOf(113.960333));
    	adSaveParam.setMerchantId(1002l);
    	adSaveParam.setUserNum("B856392484215848969");
    	adSaveParam.setLogoUrl("store/1494582624025648402.png");
    	adSaveParam.setMerchantStoreId(1001l);
    	adSaveParam.setMerchantStoreName("E店商家");
    	adSaveParam.setManageType(ManageTypeEnum.ENTITY);
    	adSaveParam.setCount(100);
    	adSaveParam.setClentType(ClientTypeEnum.MOBLIE);
    	AdParam param=new AdParam();
    	param.setAdCount(20);
    	param.setPutWayEnum(PutWayEnum.PUT_WAY_COMMON);
    	param.setTotalPoint(BigDecimal.valueOf(100));
    	param.setTypeEnum(AdTypeEnum.AD_TYPE_PACKET);
    	adSaveParam.setAdParam(param);
    	adService.saveAd(adSaveParam);

        List<AdDO> adDOS = adDOMapper.selectByExample(null);
        Assert.assertNotNull(adDOS);
        Assert.assertTrue(adDOS.size() >0);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveLuckPacket() {

		AdSaveParam adSaveParam=new AdSaveParam();
    	adSaveParam.setLatitude(BigDecimal.valueOf(22.547153));
    	adSaveParam.setLongitude(BigDecimal.valueOf(113.960333));
    	adSaveParam.setMerchantId(1002l);
    	adSaveParam.setUserNum("B856392484215848969");
    	adSaveParam.setLogoUrl("store/1494582624025648402.png");
    	adSaveParam.setMerchantStoreId(1001l);
    	adSaveParam.setMerchantStoreName("E店商家");
    	adSaveParam.setManageType(ManageTypeEnum.ENTITY);
    	adSaveParam.setCount(100);
    	adSaveParam.setClentType(ClientTypeEnum.MOBLIE);
    	AdParam param=new AdParam();
    	param.setAdCount(10);
    	param.setPutWayEnum(PutWayEnum.PUT_WAY_LUCK);
    	param.setTotalPoint(BigDecimal.valueOf(100));
    	param.setTypeEnum(AdTypeEnum.AD_TYPE_PACKET);
    	adSaveParam.setAdParam(param);
    	adService.saveAd(adSaveParam);
        List<AdDO> adDOS = adDOMapper.selectByExample(null);
        Assert.assertNotNull(adDOS);
        Assert.assertTrue(adDOS.size() >0);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectListByMerchant() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setIsPay(true);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        adDOMapper.insertSelective(ad);
        
        AdMerchantParam adMerchantParam=new AdMerchantParam();
        adMerchantParam.setPageSize(10);
        adMerchantParam.setCurrentPage(1);
        adMerchantParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        adMerchantParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        adMerchantParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        Page<AdBO> page= adService.selectListByMerchant(adMerchantParam, 1002l);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() >0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectListByMerchantStautsEnd() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTED.val);
        ad.setIsPay(true);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        adDOMapper.insertSelective(ad);
        
        AdMerchantParam adMerchantParam=new AdMerchantParam();
        adMerchantParam.setPageSize(10);
        adMerchantParam.setCurrentPage(1);
        adMerchantParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        adMerchantParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTED);
        adMerchantParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        Page<AdBO> page= adService.selectListByMerchant(adMerchantParam, 1002l);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() >0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectListByMerchantPutWay() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setIsPay(true);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        adDOMapper.insertSelective(ad);
        
        AdMerchantParam adMerchantParam=new AdMerchantParam();
        adMerchantParam.setPageSize(10);
        adMerchantParam.setCurrentPage(1);
        adMerchantParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        adMerchantParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        Page<AdBO> page= adService.selectListByMerchant(adMerchantParam, 1002l);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() >0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectListByMerchantType() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setIsPay(true);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        AdMerchantParam adMerchantParam=new AdMerchantParam();
        adMerchantParam.setPageSize(10);
        adMerchantParam.setCurrentPage(1);
        adMerchantParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        adMerchantParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        Page<AdBO> page= adService.selectListByMerchant(adMerchantParam, 1002l);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() >0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectListByMerchantStatus() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setIsPay(true);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        adDOMapper.insertSelective(ad);
        
        AdMerchantParam adMerchantParam=new AdMerchantParam();
        adMerchantParam.setPageSize(10);
        adMerchantParam.setCurrentPage(1);
        adMerchantParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        adMerchantParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        Page<AdBO> page= adService.selectListByMerchant(adMerchantParam, 1002l);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() >0);

    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateStatus() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
       
        adService.updateStatus(ad.getId());
        
        AdDOExample example=new AdDOExample();
        example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_OUT.val);
        
        List<AdDO> adDOS = adDOMapper.selectByExample(example);
        Assert.assertNotNull(adDOS);
        Assert.assertTrue(adDOS.size() == 1);

    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateStatusPacket() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setAdCount(20);
		ad.setPutWay(PutWayEnum.PUT_WAY_COMMON.val);
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
       
        adService.updateStatus(ad.getId());
        
        AdDOExample example=new AdDOExample();
        example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_OUT.val);
        
        List<AdDO> adDOS = adDOMapper.selectByExample(example);
        Assert.assertNotNull(adDOS);
        Assert.assertTrue(adDOS.size() == 1);

    } 

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void remove() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
       
        adService.remove(ad.getId());
        
        AdDOExample example=new AdDOExample();
        example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_DELETE.val);
        
        List<AdDO> adDOS = adDOMapper.selectByExample(example);
        Assert.assertNotNull(adDOS);
        Assert.assertTrue(adDOS.size() == 1);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void auditVideoPass() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_VIDEO.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_AUDIT.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
       
        adService.auditVideo(ad.getId(), 1, "通过", AuditEnum.AD_AUDIT_PASS);
        
        AdDOExample example=new AdDOExample();
        example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_ADD.val);
        
        List<AdDO> adDOS = adDOMapper.selectByExample(example);
        Assert.assertNotNull(adDOS);
        Assert.assertTrue(adDOS.size() >0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void auditVideoUnPass() {
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
		ad.setType(AdTypeEnum.AD_TYPE_VIDEO.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_AUDIT.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
       
        adService.auditVideo(ad.getId(), 1, "视频不通过", AuditEnum.AD_AUDIT_UN_PASS);
        
        AdDOExample example=new AdDOExample();
        example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_AUDIT_FAIL.val);
        
        List<AdDO> adDOS = adDOMapper.selectByExample(example);
        Assert.assertNotNull(adDOS);
        Assert.assertTrue(adDOS.size() == 1);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectListByPlatForm() {
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
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        ad.setIsPay(true);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        AdFindParam adPlatParam=new AdFindParam();
        adPlatParam.setCurrentPage(1);
        adPlatParam.setPageSize(20);
        adPlatParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        adPlatParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        adPlatParam.setStatusEnum(AdStatusEnum.AD_STATUS_ADD);
        Page<AdBO> page= adService.selectListByPlatForm(adPlatParam);
        
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectListByMember() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantNum("B856392484215848969");
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
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        AdMemberParam adMemberParam=new AdMemberParam();
        adMemberParam.setCurrentPage(1);
        adMemberParam.setPageSize(20);
        adMemberParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        Page<AdBO> page= adService.selectListByMember(adMemberParam, null);
        
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectListByMemberPoint() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantNum("B856392484215848969");
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
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        AdMemberParam adMemberParam=new AdMemberParam();
        adMemberParam.setCurrentPage(1);
        adMemberParam.setPageSize(20);
        adMemberParam.setOrderTypeEnum(OrderTypeEnum.AD_TORLEPOINT_DESC);
        Page<AdBO> page= adService.selectListByMember(adMemberParam, null);
        
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAbById() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1003l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
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
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        FavoriteAdDO favoriteAdDO=new FavoriteAdDO();
        favoriteAdDO.setAdId(ad.getId());
        favoriteAdDO.setMemberId(1l);
        favoriteAdDO.setMemberNum("M000001");
        favoriteAdDO.setGmtCreate(new Date());
        favoriteAdDOMapper.insert(favoriteAdDO);
        
        AdEgainDetailBO bo= adService.selectAbById(ad.getId(), 1l);
        
        Assert.assertNotNull(bo);

    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPraiseListByMember() {
    	AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1003l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setBeginTime(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        AdPraiseParam adPraiseParam=new AdPraiseParam();
        adPraiseParam.setCurrentPage(1);
        adPraiseParam.setPageSize(20);
        adPraiseParam.setStatusEnum(AdPraiseStatusEnum.AD_STATUS_TOBEGIN);
        
        Page<AdBO> page= adService.selectPraiseListByMember(adPraiseParam,1l);
        
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPraiseListShoot() {
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
		ad.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        AdPraiseParam adPraiseParam=new AdPraiseParam();
        adPraiseParam.setCurrentPage(1);
        adPraiseParam.setPageSize(20);
        adPraiseParam.setStatusEnum(AdPraiseStatusEnum.AD_STATUS_SHOOT);
        
        Page<AdBO> page= adService.selectPraiseListByMember(adPraiseParam,1l);
        
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void clickPraise() throws DataNotExistException, SQLException {
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
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setMemberNum("aaa");
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
        
        AdClickPraiseInfoBO point= adService.clickPraise(ad.getId(), 1l, "aaa");
        Assert.assertNotNull(point);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void clickPraiseEnd() {
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
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        AdClickPraiseInfoBO point = null;
		try {
			point = adService.clickPraise(0l, 0l, "aaa");
		} catch (DataNotExistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        Assert.assertNotNull(point);

    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void clickAd() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        
        ClickPointBO bo = null;
		try {
			bo = adService.clickAd(ad.getId(), 1l, "aaa");
		} catch (DataNotExistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        Assert.assertNotNull(bo);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectChoiceness() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantNum("B856392484215848969");
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
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);
        
        AdDO recode=new AdDO();
        recode.setMerchantLatitude(BigDecimal.valueOf(22.547153));
        recode.setMerchantLongitude(BigDecimal.valueOf(113.960333));
        recode.setMerchantId(1002l);
        recode.setMerchantStoreId(1001l);
        recode.setMerchantStoreName("E店商家");
        recode.setManageType(ManageTypeEnum.ENTITY.getVal());
        recode.setLogoUrl("store/1494582624025648402.png");
        recode.setMerchantNum("B856392484215848969");
        recode.setMediaUrl("ad_image/1494582624025648401.png");
        recode.setAdCount(20);
        recode.setBeginTime(new Date());
        recode.setContent("广告测试内容");
        recode.setPoint(BigDecimal.valueOf(0.5));
        recode.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
        recode.setRegionName("全国");
        recode.setTitle("广告测试标题");
        recode.setTotalPoint(BigDecimal.valueOf(100));
        recode.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
        recode.setGmtCreate(new Date());
        recode.setGmtModified(new Date());
        recode.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        Integer row=adDOMapper.insertSelective(recode);
        
        AdMemberParam adMemberParam=new AdMemberParam();
        adMemberParam.setCurrentPage(1);
        adMemberParam.setPageSize(20);
        Page<AdBO> page= adService.selectChoiceness(adMemberParam);
        
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);

    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectRPIsSend() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setAdCount(20);
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
       
        RedPacketIsSendBO count= adService.selectRPIsSend(1002l);
        Assert.assertNotNull(count);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRedPacket() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setAdCount(20);
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
       
        BigDecimal  point= adService.getRedPacket(1002l, 1l, "aaa");
        Assert.assertNotNull(point);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        Integer id=adDOMapper.insertSelective(ad);
        
       
        AdBO bo= adService.get(ad.getId());
        
        Assert.assertNotNull(bo);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectRedPacketByMember() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantNum("B856392484215848969");
		ad.setAdCount(20);
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
       
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(5));
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setMemberNum("aaa");
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PACKET.val);
        pointPoolDOMapper.insert(pointPoolDO);
        
        Boolean  flag= adService.selectRedPacketByMember(1002l, 1l);
        Assert.assertTrue(flag);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getClickAdPoint() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantNum("B856392484215848969");
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
        Integer id=adDOMapper.insertSelective(ad);
        
        MemberAdRecordDO memberAdRecordDO=new MemberAdRecordDO();
        memberAdRecordDO.setAdId(ad.getId());
        memberAdRecordDO.setClickDate(new Date());
        memberAdRecordDO.setGmtCommission(new Date());
        memberAdRecordDO.setGmtCreate(new Date());
        memberAdRecordDO.setMemberId(1l);
        memberAdRecordDO.setMemberNum("aa");
        memberAdRecordDO.setOriginalPoint(BigDecimal.valueOf(0.5));
        memberAdRecordDO.setPoint(BigDecimal.valueOf(0.4));
        memberAdRecordDO.setStatus(MemberAdRecordStatusEnum.YES.getVal());
        memberAdRecordDOMapper.insert(memberAdRecordDO);
       
        ClickAdPointBO bo =adService.getClickAdPoint(1l, BigDecimal.valueOf(20));
        
        Assert.assertNotNull(bo);

    }
	
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAllAd() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantNum("B856392484215848969");
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
        Integer id=adDOMapper.insertSelective(ad);
        
        List<ViewBO> list= adService.getAllAd();
        
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateViewCount() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        Integer id=adDOMapper.insertSelective(ad);
        
        adService.updateViewCount(ad.getId(), 11);
        
        AdDOExample example=new AdDOExample();
        example.createCriteria().andViewcountGreaterThan(10);
        List<AdDO> list=adDOMapper.selectByExample(example);
        
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listAllAd() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setIsPay(true);
        Integer id=adDOMapper.insertSelective(ad);
        
        ListAdParam listAdParam=new ListAdParam();
        listAdParam.setPageSize(10);
        listAdParam.setCurrentPage(1);
        listAdParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        listAdParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        listAdParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        listAdParam.setTitle("广告");
        Page<AdBO> page= adService.listAllAd(listAdParam);
        
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectById() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        Integer id=adDOMapper.insertSelective(ad);
        
        AdBO bo= adService.selectById(ad.getId());
        
        Assert.assertNotNull(bo);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void operatorUpdateAdStatus() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        Integer id=adDOMapper.insertSelective(ad);
        
     
        adService.operatorUpdateAdStatus(ad.getId(), AdStatusEnum.AD_STATUS_DELETE);
        
        AdDOExample example=new AdDOExample();
        example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_DELETE.val);
        List<AdDO> list=adDOMapper.selectByExample(example);
        
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listFlatVideoAd() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
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
        ad.setIsPay(true);
        Integer id=adDOMapper.insertSelective(ad);
        
        ListAdParam listAdParam=new ListAdParam();
        listAdParam.setPageSize(10);
        listAdParam.setCurrentPage(1);
        ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
        listAdParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        listAdParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        listAdParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        listAdParam.setTitle("广告");
        List<AdBO> list= adService.listFlatVideoAd(listAdParam);
        
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void rebuildAdIndex() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
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
        Integer id=adDOMapper.insertSelective(ad);
        
        adService.rebuildAdIndex(1000);
        
        List<AdDO> list=adDOMapper.selectByExample(null);
        
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void delInvalidAdIndex() {
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
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTED.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        adService.delInvalidAdIndex(DelIndexTypeEnum.ALL);
        
        List<AdDO> list=adDOMapper.selectByExample(null);
        
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size()>0);

    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void getRedPacketInfo() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantId(1002l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_COMMON.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setMemberNum("aaa");
        pointPoolDOMapper.insert(pointPoolDO);
        
        RedPacketInfoBO  bo = adService.getRedPacketInfo(ad.getMerchantId());
        Assert.assertNotNull(bo);

    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void isExistsRedPacket() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setAdCount(20);
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_OUT.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        Boolean  flag = adService.isExistsRedPacket(1002l);
        Assert.assertNotNull(flag);
        Assert.assertTrue(flag);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void isSendRedPacket() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setAdCount(20);
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        Boolean  flag = adService.isSendRedPacket(1002l);
        Assert.assertNotNull(flag);
        Assert.assertTrue(flag);
    }

	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void batchDeleteAd() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
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
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        List<Long> ids=new ArrayList<>();
        ids.add(ad.getId());
        adService.batchDeleteAd(ids);
        AdDOExample example=new AdDOExample();
        example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_DELETE.val);
        List<AdDO>  list = adDOMapper.selectByExample(example);
        Assert.assertNotNull(list);
        //Assert.assertTrue(list.size()>0);
    }
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectDetailFlatAndVideo() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        MemberAdRecordDO memberAdRecordDO=new MemberAdRecordDO();
        memberAdRecordDO.setAdId(ad.getId());
        memberAdRecordDO.setClickDate(new Date());
        memberAdRecordDO.setGmtCommission(new Date());
        memberAdRecordDO.setGmtCreate(new Date());
        memberAdRecordDO.setMemberId(1l);
        memberAdRecordDO.setMemberNum("aa");
        memberAdRecordDO.setOriginalPoint(BigDecimal.valueOf(0.5));
        memberAdRecordDO.setPoint(BigDecimal.valueOf(0.4));
        memberAdRecordDO.setStatus(MemberAdRecordStatusEnum.YES.getVal());
        memberAdRecordDOMapper.insert(memberAdRecordDO);
        
        AdDetailBO bo=adService.selectDetail(ad.getId());
        Assert.assertNotNull(bo);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectDetailPraise() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
        
        AdDetailBO bo=adService.selectDetail(ad.getId());
        Assert.assertNotNull(bo);
    }
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectDetailPraiseNotNull() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
        
        AdDetailBO bo=adService.selectDetail(ad.getId());
        Assert.assertNotNull(bo);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectDetailPacket() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_COMMON.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
        
        AdDetailBO bo=adService.selectDetail(ad.getId());
        Assert.assertNotNull(bo);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectDetailPacketNotNull() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_COMMON.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
        
        AdDetailBO bo=adService.selectDetail(ad.getId());
        Assert.assertNotNull(bo);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void isMyData() {
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        Boolean flag=adService.isMyData(ad.getId(),1002l);
        Assert.assertNotNull(flag);
        Assert.assertTrue(flag);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectReportAdEarnings() {
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        List<ReportAdBO> list=adService.selectReportAdEarnings();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size()>0);
    }
	
	
	/*@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectPageAdEgain() {
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
		ad.setAreas("11");
		ad.setRegionName("北京");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        AdEgainInternalParam param =new AdEgainInternalParam();
        
        List<Long> merchantIds=new ArrayList<>();
        merchantIds.add(1002l);
        param.setMerchantIds(merchantIds);
        
        List<String> areas=new ArrayList<>();
        areas.add("11");
        param.setAreas(areas);
        
        param.setLatitude(22.547153);
        param.setLongitude(113.960333);
        
        param.setCurrentPage(1);
        param.setPageSize(20);
        param.setTypeEnum(AdEgainTypeEnum.AD_TYPE_FLAT);
        Page<AdEgainBO> page=adService.selectPageAdEgain(1l, param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size()>0);
    }*/

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAdPraiseById() {
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1003l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
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
        Integer id=adDOMapper.insertSelective(ad);

        FavoriteAdDO favoriteAdDO=new FavoriteAdDO();
        favoriteAdDO.setAdId(ad.getId());
        favoriteAdDO.setMemberId(1l);
        favoriteAdDO.setMemberNum("M000001");
        favoriteAdDO.setGmtCreate(new Date());
        favoriteAdDO.setIsSend(false);
        favoriteAdDOMapper.insert(favoriteAdDO);

        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);

        AdPraiseBO bo= adService.selectAdPraiseById(ad.getId(), 1l);

        Assert.assertNotNull(bo);

    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectOperatorAdAll() {
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        ad.setIsPay(true);
        Integer id=adDOMapper.insertSelective(ad);

        OperatorAdParam operatorAdParam = new OperatorAdParam();
        operatorAdParam.setAdEgainType(AdEgainTypeEnum.AD_TYPE_FLAT);
        Page<OperatorAdBO> list=adService.selectOperatorAdAll(operatorAdParam);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.getRecords().size()>0);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
    @Test
    public void selectOperatorAdAllNull() {
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        ad.setIsPay(true);
        Integer id=adDOMapper.insertSelective(ad);

        OperatorAdParam operatorAdParam = new OperatorAdParam();
        Page<OperatorAdBO> list=adService.selectOperatorAdAll(operatorAdParam);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.getRecords().size()>0);
    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void soldOutAdByMerchantId() {
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setIsPay(true);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        Integer id=adDOMapper.insertSelective(ad);

		adDOMapper.insertSelective(ad);
		adService.soldOutAdByMerchantId(ad.getMerchantId());

		List<AdDO> list = adDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.get(0).getStatus() == AdStatusEnum.AD_STATUS_OUT.val);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void downOperatorById() {
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        Integer id=adDOMapper.insertSelective(ad);

		adDOMapper.insertSelective(ad);
		
		adService.downOperatorById(ad.getId(),1,"aaa");
		AdDOExample example = new AdDOExample();
		example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_OUT.val);
		List<AdDO> list = adDOMapper.selectByExample(example);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.get(0).getStatus() == AdStatusEnum.AD_STATUS_OUT.val);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectMerchantNumByAdId() {
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
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        Integer id=adDOMapper.insertSelective(ad);

		adDOMapper.insertSelective(ad);
		
		MerchantInfoBO bo = adService.selectMerchantNumByAdId(ad.getId());
		Assert.assertNotNull(bo);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateAdIsPay() {
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
        ad.setHits(0);
        ad.setIsPay(false);
        ad.setStatus(AdStatusEnum.AD_STATUS_AUDIT.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        AdSetPayParam param = new AdSetPayParam();
        param.setId(ad.getId());
        param.setPayTypeEnum(AdPayTypeEnum.ALIPAY);
		param.setThirdNumber("2017080711170000063644995");

		adService.updateAdIsPay(param);
		
		AdDOExample example = new AdDOExample();
		example.createCriteria().andIsPayEqualTo(true);
		List<AdDO> adDOS = adDOMapper.selectByExample(example);
		Assert.assertNotNull(adDOS);
		Assert.assertTrue(adDOS.size() > 0);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getDetailPage() {
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
        ad.setHits(0);
        ad.setIsPay(false);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTED.val);
        Integer id=adDOMapper.insertSelective(ad);
        
        MemberAdRecordDO memberAdRecordDO=new MemberAdRecordDO();
        memberAdRecordDO.setAdId(ad.getId());
        memberAdRecordDO.setClickDate(new Date());
        memberAdRecordDO.setGmtCommission(new Date());
        memberAdRecordDO.setGmtCreate(new Date());
        memberAdRecordDO.setMemberId(1l);
        memberAdRecordDO.setMemberNum("aa");
        memberAdRecordDO.setOriginalPoint(BigDecimal.valueOf(0.5));
        memberAdRecordDO.setPoint(BigDecimal.valueOf(0.4));
        memberAdRecordDO.setStatus(MemberAdRecordStatusEnum.YES.getVal());
        memberAdRecordDOMapper.insert(memberAdRecordDO);
        
        PointGetDetailParam param  = new PointGetDetailParam();
        param.setId(ad.getId());
        param.setCurrentPage(1);
		param.setPageSize(20);
		param.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);

		Page<PointGetDetailBO> page = adService.getDetailPage(param);
		
		Assert.assertNotNull(page.getRecords());
		Assert.assertTrue(page.getRecords().size() > 0);
	}
}
