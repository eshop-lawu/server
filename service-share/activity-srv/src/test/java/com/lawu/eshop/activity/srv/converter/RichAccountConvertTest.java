package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.dto.PersonalRichAccountDTO;
import com.lawu.eshop.activity.dto.RichMyDiamondRecordInfoDTO;
import com.lawu.eshop.activity.dto.RichPowerInfoRecordDTO;
import com.lawu.eshop.activity.srv.bo.PersonalRichAccountBO;
import com.lawu.eshop.activity.srv.bo.PersonalRichDiamondDetailBO;
import com.lawu.eshop.activity.srv.bo.RichDetailBO;
import com.lawu.eshop.activity.srv.bo.RichDiamondRecordBO;
import com.lawu.eshop.activity.srv.bo.RichMyDiamondRecordDetailBO;
import com.lawu.eshop.activity.srv.bo.RichMyDiamondRecordInfoBO;
import com.lawu.eshop.activity.srv.bo.RichPowerDetailBO;
import com.lawu.eshop.activity.srv.bo.RichPowerInfoRecordBO;
import com.lawu.eshop.activity.srv.bo.RichPowerRecordBO;
import com.lawu.eshop.activity.srv.domain.RichAccountDO;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

import junit.framework.Assert;

/** 
 * 
 * @author lihj
 * @date 2018年5月8日
 */
public class RichAccountConvertTest {

	@Test
	public void convertPersonalRichAccountBO(){
		RichAccountDO rich = new RichAccountDO();
		rich.setDiamondTotal(new BigDecimal(100));
		rich.setPower(1000);
		
		List<RichDiamondRecordBO> list = new ArrayList<RichDiamondRecordBO>();
		RichDiamondRecordBO diamond = new RichDiamondRecordBO();
		diamond.setId(1L);
		diamond.setAmount(new BigDecimal(100));
		diamond.setTypeEnum(RichDiamondRecordTypeEnum.ECOIN);
		list.add(diamond);
		PersonalRichAccountBO per = RichAccountConvert.convertPersonalRichAccountBO(rich,list);
		Assert.assertNotNull(per);
	}
	
	@Test
	public void convertPersonalRichAccountDTO(){
		PersonalRichAccountBO rich = new PersonalRichAccountBO();
		rich.setDiamondTotal(new BigDecimal(100));
		rich.setPower(10);
		List<PersonalRichDiamondDetailBO> diamondList = new ArrayList<PersonalRichDiamondDetailBO>();
		PersonalRichDiamondDetailBO bo = new PersonalRichDiamondDetailBO();
		bo.setId(1L);
		bo.setDiamond(new BigDecimal(200));
		bo.setTypeEnum(RichDiamondRecordTypeEnum.ECOIN);
		diamondList.add(bo);
		rich.setDiamondList(diamondList);
		PersonalRichAccountDTO dto = RichAccountConvert.convertPersonalRichAccountDTO(rich);
		Assert.assertNotNull(dto);
		
	}
	
	@Test
	public void convertRichDetailDTO(){
		RichDetailBO bo =new RichDetailBO();
		bo.setCreatorsPeople(1);
		bo.setDayRichDiamond(BigDecimal.ZERO);
		bo.setNotice("123");
		bo.setTotalRichDiamond(BigDecimal.TEN);
		bo.setTotalRichPeople(10);
		List<RichPowerDetailBO> list = new ArrayList<RichPowerDetailBO>();
		RichPowerDetailBO rbo =new RichPowerDetailBO();
		rbo.setMemberNum("M001");
		rbo.setTotalPower(10022);
		list.add(rbo);
		bo.setPowerList(list);
		Assert.assertNotNull(bo);
	}
	
	@Test
	public void convertRichPowerDetailBO(){
		List<RichAccountDO> listAccount = new ArrayList<RichAccountDO>();
		RichAccountDO rich = new RichAccountDO();
		rich.setUserNum("M00001");
		rich.setPower(1000);
		listAccount.add(rich);
		List<RichPowerDetailBO> lt = RichAccountConvert.convertPowerList(listAccount);
		Assert.assertNotNull(lt);
	}
	
	@Test
	public void convertRichPowerInfoRecordDTO(){
		RichPowerInfoRecordBO rich = new RichPowerInfoRecordBO();
		rich.setTotalPower(100);
		List<RichPowerRecordBO> listRichPowerRecord =new ArrayList<RichPowerRecordBO>();
		RichPowerRecordBO bo = new RichPowerRecordBO();
		bo.setDate(new Date());
		bo.setPower(100);
		bo.setTitle("标题");
		bo.setTypeEnum(PowerTaskTypeEnum.ALIPAY_BIND);
		listRichPowerRecord.add(bo);
		rich.setListRichPowerRecord(listRichPowerRecord);
		RichPowerInfoRecordDTO richPower = RichAccountConvert.convertRichPowerInfoRecordDTO(rich);
		Assert.assertNotNull(richPower);
	}
	
	@Test
	public void convertRichMyDiamondRecordInfoDTO(){
		RichMyDiamondRecordInfoBO rich = new RichMyDiamondRecordInfoBO();
		rich.setTotalDiamond(new BigDecimal(1000));
		List<RichMyDiamondRecordDetailBO> list = new ArrayList<RichMyDiamondRecordDetailBO>();
		RichMyDiamondRecordDetailBO detail = new RichMyDiamondRecordDetailBO();
		detail.setAmount(new BigDecimal(100));
		detail.setDirectionEnum(RichPowerRecordDirectionEnum.IN);
		detail.setSourceEnum(RichDiamondRecordSourceEnum.DAYGET);
		detail.setTakeTime(new Date());
		detail.setTitle("title");
		detail.setTypeEnum(RichDiamondRecordTypeEnum.ECOIN);
		list.add(detail);
		rich.setRecordList(list);
		RichMyDiamondRecordInfoDTO info = RichAccountConvert.convertRichMyDiamondRecordInfoDTO(rich);
		Assert.assertNotNull(info);
	}
	
	@Test
	public void convertRichMyDiamondRecordDetailBO(){
		List<RichDiamondRecordBO> listRecord =new ArrayList<RichDiamondRecordBO>();
		RichDiamondRecordBO rich = new RichDiamondRecordBO();
		rich.setAmount(new BigDecimal(100));
		rich.setDirectionEnum(RichPowerRecordDirectionEnum.IN);
		rich.setSourceEnum(RichDiamondRecordSourceEnum.DAYGET);
		rich.setTakeTime(new Date());
		rich.setTitle("title");
		rich.setTypeEnum(RichDiamondRecordTypeEnum.ECOIN);
		listRecord.add(rich);
		List<RichMyDiamondRecordDetailBO> lt =  RichAccountConvert.convertRichMyDiamondRecordDetailBO(listRecord);
		Assert.assertNotNull(lt);
	}
	
	
}
