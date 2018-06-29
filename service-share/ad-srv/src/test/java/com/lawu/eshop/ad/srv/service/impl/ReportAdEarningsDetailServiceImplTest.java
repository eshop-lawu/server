package com.lawu.eshop.ad.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.MemberAdRecordStatusEnum;
import com.lawu.eshop.ad.constants.PointPoolStatusEnum;
import com.lawu.eshop.ad.constants.PointPoolTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.param.ReportAdEarningsDetailParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.ReportAdEarningsDetailBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.MemberAdRecordDO;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.MemberAdRecordDOMapper;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.eshop.ad.srv.service.ReportAdEarningsDetailService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.framework.core.page.Page;

/**
 * 广告收益详情测试
 * @author zhangrc
 * @date 2017/07/12
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class ReportAdEarningsDetailServiceImplTest {
	
	@Autowired
	private ReportAdEarningsDetailService reportAdEarningsDetailService;
	
	@Autowired
	private AdDOMapper adDOMapper;
	
	@Autowired
	private PointPoolDOMapper pointPoolDOMapper;
	
	@Autowired
	private MemberAdRecordDOMapper memberAdRecordDOMapper;
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getReportAdEarningsDetail() {
		
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

        MemberAdRecordDO memberAdRecordDO=new MemberAdRecordDO();
        memberAdRecordDO.setAdId(ad.getId());
        memberAdRecordDO.setClickDate(new Date());
        memberAdRecordDO.setGmtCommission(new Date());
        memberAdRecordDO.setGmtCreate(new Date(2017-07-18));
        memberAdRecordDO.setMemberId(1l);
        memberAdRecordDO.setMemberNum("aa");
        memberAdRecordDO.setOriginalPoint(BigDecimal.valueOf(0.5));
        memberAdRecordDO.setPoint(BigDecimal.valueOf(0.4));
        memberAdRecordDO.setStatus(MemberAdRecordStatusEnum.NONE.getVal());
        memberAdRecordDOMapper.insert(memberAdRecordDO);
       
        ReportAdEarningsDetailParam param=new ReportAdEarningsDetailParam();
        param.setAdTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        param.setAdId(ad.getId());
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<ReportAdEarningsDetailBO> page= reportAdEarningsDetailService.getReportAdEarningsDetail(param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() > 0);

    }

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getReportAdEarningsDetailPraise() {
		
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
        adDOMapper.insertSelective(ad);

		PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date(2017-07-18));
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setMemberNum("aaa");
        pointPoolDOMapper.insert(pointPoolDO);
        
       
        ReportAdEarningsDetailParam param=new ReportAdEarningsDetailParam();
        param.setAdTypeEnum(AdTypeEnum.AD_TYPE_PRAISE);
        param.setAdId(ad.getId());
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<ReportAdEarningsDetailBO> page= reportAdEarningsDetailService.getReportAdEarningsDetail(param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() > 0);

    }

}
