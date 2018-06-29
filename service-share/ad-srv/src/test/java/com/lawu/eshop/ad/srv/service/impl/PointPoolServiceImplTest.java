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
import com.lawu.eshop.ad.constants.PointPoolStatusEnum;
import com.lawu.eshop.ad.constants.PointPoolTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.GetRedPacketBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.eshop.ad.srv.service.PointPoolService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;

/**
 * 广告积分测试
 * @author zhangrc
 * @date 2017/07/12
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class PointPoolServiceImplTest {
	
	@Autowired
	private PointPoolService pointPoolService;
	
	@Autowired
	private PointPoolDOMapper pointPoolDOMapper;
	
	@Autowired
	private AdDOMapper adDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectList() {

		PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(1l);
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setMemberNum("aaa");
        pointPoolDOMapper.insert(pointPoolDO);
       
        List<PointPoolDO> list= pointPoolService.selectMemberList(1l);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);

    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectStatusByMember() {

		PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(1l);
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setMemberNum("aaa");
        pointPoolDOMapper.insert(pointPoolDO);
       
        Boolean flag= pointPoolService.selectStatusByMember(1l, 1l);
        Assert.assertTrue(flag);

    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void isGetRedPacket() {
		
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
		ad.setAdCount(2);
		ad.setPutWay(PutWayEnum.PUT_WAY_COMMON.val);
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PACKET.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        adDOMapper.insertSelective(ad);

		PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(1l);
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PACKET.val);
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setMemberNum("aaa");
        pointPoolDOMapper.insert(pointPoolDO);
       
        GetRedPacketBO bo= pointPoolService.isGetRedPacket(1002l, "aaa");
        Assert.assertNotNull(bo);

    }

}
