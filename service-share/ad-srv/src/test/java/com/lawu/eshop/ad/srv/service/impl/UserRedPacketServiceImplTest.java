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

import com.lawu.eshop.ad.constants.PointPoolStatusEnum;
import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;
import com.lawu.eshop.ad.constants.RedPacketStatusEnum;
import com.lawu.eshop.ad.constants.UserRedPacketEnum;
import com.lawu.eshop.ad.constants.UserRedPacketPayTypeEnum;
import com.lawu.eshop.ad.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.ad.param.UserPacketRefundParam;
import com.lawu.eshop.ad.param.UserRedPacketSaveParam;
import com.lawu.eshop.ad.param.UserRedPacketSelectNumParam;
import com.lawu.eshop.ad.param.UserRedPacketUpdateParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.UserRedPacketAddReturnBO;
import com.lawu.eshop.ad.srv.bo.UserRedPacketBO;
import com.lawu.eshop.ad.srv.domain.UserRedPacketDO;
import com.lawu.eshop.ad.srv.domain.UserTakedRedPacketDO;
import com.lawu.eshop.ad.srv.domain.extend.UserRedpacketMaxMoney;
import com.lawu.eshop.ad.srv.mapper.UserRedPacketDOMapper;
import com.lawu.eshop.ad.srv.mapper.UserTakedRedPacketDOMapper;
import com.lawu.eshop.ad.srv.service.UserRedPacketService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author lihj
 * @date 2017/8/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class UserRedPacketServiceImplTest {

	@Autowired
	private UserRedPacketService userRedPacketService;
	
	@Autowired
	private UserRedPacketDOMapper userRedPacketDOMapper;
	
	@Autowired
	private UserTakedRedPacketDOMapper userTakedRedPacketDOMapper;

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void addUserRedPacket() {
		
		UserRedPacketSaveParam param = new UserRedPacketSaveParam();
		param.setRedPacketPutWayEnum(RedPacketPutWayEnum.PUT_WAY_COMMON);
		param.setTotalCount(1);
		param.setTotalMoney(new BigDecimal(100));
		param.setUserAccount("15000000000");
		param.setUserNum("M000001");
		UserRedPacketAddReturnBO ubo = userRedPacketService.addUserRedPacket(param);
		UserRedPacketSelectNumParam query = new UserRedPacketSelectNumParam();
		query.setCurrentPage(1);
		query.setPageSize(10);
		query.setNum("M000001");
		List<UserRedPacketDO> userList = userRedPacketDOMapper.selectByExample(null);
		Assert.assertNotNull(userList);
        Assert.assertTrue(userList.size()>0);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void addUserLuckRedPacket() {
		
		UserRedPacketSaveParam param = new UserRedPacketSaveParam();
		param.setRedPacketPutWayEnum(RedPacketPutWayEnum.PUT_WAY_LUCK);
		param.setTotalCount(1);
		param.setTotalMoney(new BigDecimal(100));
		param.setUserAccount("15000000000");
		param.setUserNum("M000001");
		UserRedPacketAddReturnBO ubo = userRedPacketService.addUserRedPacket(param);
		UserRedPacketSelectNumParam query = new UserRedPacketSelectNumParam();
		query.setCurrentPage(1);
		query.setPageSize(10);
		query.setNum("M000001");
		List<UserRedPacketDO> userList = userRedPacketDOMapper.selectByExample(null);
		Assert.assertNotNull(userList);
        Assert.assertTrue(userList.size()>0);
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void isExistsRedPacket() {
		
		UserRedPacketDO redPack = new UserRedPacketDO();
		redPack.setGmtCreate(new Date());
		redPack.setGmtModified(new Date());
		redPack.setOrderNum("2017082115590000182751221");
		redPack.setPayType(UserRedPacketPayTypeEnum.ALIPAY.getVal());
		redPack.setStatus(RedPacketStatusEnum.RED_PACKET_SUCCESS.val);
		redPack.setTotalCount(1);
		redPack.setTotalMoney(BigDecimal.valueOf(100));
		redPack.setType(RedPacketPutWayEnum.PUT_WAY_LUCK.val);
		redPack.setUserAccount("15000000000");
		redPack.setUserNum("M000001");
		userRedPacketDOMapper.insert(redPack);
		
		UserTakedRedPacketDO userTaked = new UserTakedRedPacketDO();
		userTaked.setGmtCreate(new Date());
		userTaked.setGmtModified(new Date());
		userTaked.setMoney(BigDecimal.valueOf(100));
		userTaked.setOrdinal(0);
		userTaked.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
		userTaked.setType(redPack.getType());
		userTaked.setUserRedPackId(redPack.getId());
		userTakedRedPacketDOMapper.insert(userTaked);
	
		UserRedPacketSelectNumParam query = new UserRedPacketSelectNumParam();
		query.setCurrentPage(1);
		query.setPageSize(10);
		query.setNum("M000001");
		Page<UserRedPacketBO> userPage = userRedPacketService.selectUserRedPacketList(query);
		List<UserRedPacketBO> list = userPage.getRecords();
		for (int i = 0; i < list.size(); i++) {
			boolean flag = userRedPacketService.isExistsRedPacket(list.get(i).getId());
			Assert.assertEquals(true, flag);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void executeUserRedPacketData() {
		UserRedPacketSaveParam param = new UserRedPacketSaveParam();
		param.setRedPacketPutWayEnum(RedPacketPutWayEnum.PUT_WAY_LUCK);
		param.setTotalCount(1);
		param.setTotalMoney(new BigDecimal(100));
		param.setUserAccount("15000000000");
		param.setUserNum("M000001");
		userRedPacketService.addUserRedPacket(param);
//		userRedPacketService.executeUserRedPacketData();
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getUserRedpacketMoney() {
		UserRedPacketDO redPack = new UserRedPacketDO();
		redPack.setGmtCreate(new Date());
		redPack.setGmtModified(new Date());
		redPack.setOrderNum("2017082115590000182751221");
		redPack.setPayType(UserRedPacketPayTypeEnum.ALIPAY.getVal());
		redPack.setStatus(UserRedPacketEnum.USER_STATUS_EFFECTIVE.val);
		redPack.setTotalCount(1);
		redPack.setTotalMoney(BigDecimal.valueOf(100));
		redPack.setType(RedPacketPutWayEnum.PUT_WAY_LUCK.val);
		redPack.setUserAccount("15000000000");
		redPack.setUserNum("M000001");
		userRedPacketDOMapper.insert(redPack);
		
		boolean flag = userRedPacketService.isExistsRedPacket(redPack.getId());
		Assert.assertEquals(true, flag);
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getUserRedpacketMaxMoney(){
		UserRedPacketDO redPack = new UserRedPacketDO();
		redPack.setGmtCreate(new Date());
		redPack.setGmtModified(new Date());
		redPack.setOrderNum("2017082115590000182751221");
		redPack.setPayType(UserRedPacketPayTypeEnum.ALIPAY.getVal());
		redPack.setStatus(RedPacketStatusEnum.RED_PACKET_SUCCESS.val);
		redPack.setTotalCount(1);
		redPack.setTotalMoney(BigDecimal.valueOf(100));
		redPack.setType(RedPacketPutWayEnum.PUT_WAY_LUCK.val);
		redPack.setUserAccount("15000000000");
		redPack.setUserNum("M000001");
		userRedPacketDOMapper.insert(redPack);
		
		UserTakedRedPacketDO userTaked = new UserTakedRedPacketDO();
		userTaked.setGmtCreate(new Date());
		userTaked.setGmtModified(new Date());
		userTaked.setMoney(BigDecimal.valueOf(100));
		userTaked.setOrdinal(0);
		userTaked.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
		userTaked.setType(redPack.getType());
		userTaked.setUserRedPackId(redPack.getId());
		userTakedRedPacketDOMapper.insert(userTaked);
		
		UserRedpacketMaxMoney maxMoney = userRedPacketService.getUserRedpacketMaxMoney(redPack.getId());
		
		Assert.assertNotNull(maxMoney);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectBackTotalMoney(){
		UserRedPacketDO redPack = new UserRedPacketDO();
		redPack.setGmtCreate(new Date());
		redPack.setGmtModified(new Date());
		redPack.setOrderNum("2017082115590000182751221");
		redPack.setPayType(UserRedPacketPayTypeEnum.ALIPAY.getVal());
		redPack.setStatus(RedPacketStatusEnum.RED_PACKET_SUCCESS.val);
		redPack.setTotalCount(1);
		redPack.setTotalMoney(BigDecimal.valueOf(100));
		redPack.setType(RedPacketPutWayEnum.PUT_WAY_LUCK.val);
		redPack.setUserAccount("15000000000");
		redPack.setUserNum("M000001");
		userRedPacketDOMapper.insert(redPack);
		
		UserTakedRedPacketDO userTaked = new UserTakedRedPacketDO();
		userTaked.setGmtCreate(new Date());
		userTaked.setGmtModified(new Date());
		userTaked.setMoney(BigDecimal.valueOf(100));
		userTaked.setOrdinal(0);
		userTaked.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
		userTaked.setType(redPack.getType());
		userTaked.setUserRedPackId(redPack.getId());
		userTakedRedPacketDOMapper.insert(userTaked);
		
		UserPacketRefundParam param = userRedPacketService.selectBackTotalMoney(redPack.getId());
		
		Assert.assertNotNull(param);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectUserRedPacketList(){
		UserRedPacketDO redPack = new UserRedPacketDO();
		redPack.setGmtCreate(new Date());
		redPack.setGmtModified(DateUtil.stringToDate("2017-06-10 00:00:00"));
		redPack.setOrderNum("2017082115590000182751221");
		redPack.setPayType(UserRedPacketPayTypeEnum.ALIPAY.getVal());
		redPack.setStatus(UserRedPacketEnum.USER_STATUS_OVER.val);
		redPack.setTotalCount(1);
		redPack.setTotalMoney(BigDecimal.valueOf(100));
		redPack.setType(RedPacketPutWayEnum.PUT_WAY_LUCK.val);
		redPack.setUserAccount("15000000000");
		redPack.setUserNum("M000001");
		userRedPacketDOMapper.insert(redPack);
		
		UserTakedRedPacketDO userTaked = new UserTakedRedPacketDO();
		userTaked.setGmtCreate(new Date());
		userTaked.setGmtModified(new Date());
		userTaked.setMoney(BigDecimal.valueOf(100));
		userTaked.setOrdinal(0);
		userTaked.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
		userTaked.setType(redPack.getType());
		userTaked.setUserRedPackId(redPack.getId());
		userTakedRedPacketDOMapper.insert(userTaked);
		
		UserRedPacketSelectNumParam query = new UserRedPacketSelectNumParam();
		query.setCurrentPage(1);
		query.setPageSize(10);
		query.setNum("M000001");
		Page<UserRedPacketBO> userPage = userRedPacketService.selectUserRedPacketList(query);
		
		Assert.assertNotNull(userPage.getRecords());
        Assert.assertTrue(userPage.getRecords().size()>0);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectUserRedPacketInfoForThrid(){
		UserRedPacketDO redPack = new UserRedPacketDO();
		redPack.setGmtCreate(new Date());
		redPack.setGmtModified(DateUtil.stringToDate("2017-06-10 00:00:00"));
		redPack.setOrderNum("2017082115590000182751221");
		redPack.setPayType(UserRedPacketPayTypeEnum.ALIPAY.getVal());
		redPack.setStatus(UserRedPacketEnum.USER_STATUS_OVER.val);
		redPack.setTotalCount(1);
		redPack.setTotalMoney(BigDecimal.valueOf(100));
		redPack.setType(RedPacketPutWayEnum.PUT_WAY_LUCK.val);
		redPack.setUserAccount("15000000000");
		redPack.setUserNum("M000001");
		userRedPacketDOMapper.insert(redPack);
		
		UserTakedRedPacketDO userTaked = new UserTakedRedPacketDO();
		userTaked.setGmtCreate(new Date());
		userTaked.setGmtModified(new Date());
		userTaked.setMoney(BigDecimal.valueOf(100));
		userTaked.setOrdinal(0);
		userTaked.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
		userTaked.setType(redPack.getType());
		userTaked.setUserRedPackId(redPack.getId());
		userTakedRedPacketDOMapper.insert(userTaked);
		
		ThirdPayCallBackQueryPayOrderDTO dto = userRedPacketService.selectUserRedPacketInfoForThrid(redPack.getId());
		
		Assert.assertNotNull(dto);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateUserPacketInfo(){
		UserRedPacketDO redPack = new UserRedPacketDO();
		redPack.setGmtCreate(new Date());
		redPack.setGmtModified(DateUtil.stringToDate("2017-06-10 00:00:00"));
		redPack.setOrderNum("2017082115590000182751221");
		redPack.setPayType(UserRedPacketPayTypeEnum.ALIPAY.getVal());
		redPack.setStatus(UserRedPacketEnum.USER_STATUS_OVER.val);
		redPack.setTotalCount(1);
		redPack.setTotalMoney(BigDecimal.valueOf(100));
		redPack.setType(RedPacketPutWayEnum.PUT_WAY_LUCK.val);
		redPack.setUserAccount("15000000000");
		redPack.setUserNum("M000001");
		userRedPacketDOMapper.insert(redPack);
		
		UserTakedRedPacketDO userTaked = new UserTakedRedPacketDO();
		userTaked.setGmtCreate(new Date());
		userTaked.setGmtModified(new Date());
		userTaked.setMoney(BigDecimal.valueOf(100));
		userTaked.setOrdinal(0);
		userTaked.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
		userTaked.setType(redPack.getType());
		userTaked.setUserRedPackId(redPack.getId());
		userTakedRedPacketDOMapper.insert(userTaked);
		
		UserRedPacketUpdateParam param = new UserRedPacketUpdateParam();
		param.setPayType(UserRedPacketPayTypeEnum.ALIPAY.getVal());
		param.setRedId(redPack.getId());
		param.setThirdNum("");
		
		Boolean flag = userRedPacketService.updateUserPacketInfo(param);
		
		 Assert.assertTrue(flag);
	}
	
	
	
}
