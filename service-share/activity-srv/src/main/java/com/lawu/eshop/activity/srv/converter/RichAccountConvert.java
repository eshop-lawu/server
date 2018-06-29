package com.lawu.eshop.activity.srv.converter;

import com.google.common.collect.Lists;
import com.lawu.eshop.activity.dto.PersonalRichAccountDTO;
import com.lawu.eshop.activity.dto.PersonalRichDiamondDetailDTO;
import com.lawu.eshop.activity.dto.RichDetailDTO;
import com.lawu.eshop.activity.dto.RichMyDiamondRecordDetailDTO;
import com.lawu.eshop.activity.dto.RichMyDiamondRecordInfoDTO;
import com.lawu.eshop.activity.dto.RichPowerDetailDTO;
import com.lawu.eshop.activity.dto.RichPowerInfoRecordDTO;
import com.lawu.eshop.activity.dto.RichPowerRecordDTO;
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

import java.util.List;

/**
 * @author lihj
 * @date 2018/5/2
 */
public class RichAccountConvert {
    public static PersonalRichAccountBO convertPersonalRichAccountBO(RichAccountDO rich, List<RichDiamondRecordBO> list) {
        PersonalRichAccountBO pr =new PersonalRichAccountBO();
        pr.setDiamondTotal(rich.getDiamondTotal());
        pr.setPower(rich.getPower());
        pr.setFirstOpen(false);
		List<PersonalRichDiamondDetailBO> lt = Lists.newArrayList();
		if(list.size()!=0){
            for(RichDiamondRecordBO rd :list){
            	PersonalRichDiamondDetailBO prich =new PersonalRichDiamondDetailBO();
            	prich.setId(rd.getId());
            	prich.setDiamond(rd.getAmount());
            	prich.setTypeEnum(rd.getTypeEnum());
                lt.add(prich);
            }
        }
		pr.setDiamondList(lt);
        return pr;
    }

    public static PersonalRichAccountDTO convertPersonalRichAccountDTO(PersonalRichAccountBO bo) {
        PersonalRichAccountDTO dto =new PersonalRichAccountDTO();
        List<PersonalRichDiamondDetailBO> lt = bo.getDiamondList();
        List<PersonalRichDiamondDetailDTO> list =Lists.newArrayList();
        for(PersonalRichDiamondDetailBO per:lt){
        	PersonalRichDiamondDetailDTO pdto =new PersonalRichDiamondDetailDTO();
        	pdto.setDiamond(per.getDiamond());
        	pdto.setId(per.getId());
        	pdto.setTypeEnum(per.getTypeEnum());
        	list.add(pdto);
        }
        dto.setDiamondList(list);
        dto.setDiamondTotal(bo.getDiamondTotal());
        dto.setPower(bo.getPower());
        dto.setFirstOpen(bo.isFirstOpen());
        return dto;
    }

	public static RichDetailDTO convertRichDetailDTO(RichDetailBO rich) {
		RichDetailDTO dto = new RichDetailDTO();
		dto.setCreatorsPeople(rich.getCreatorsPeople());
		dto.setDayRichDiamond(rich.getDayRichDiamond());
		dto.setNotice(rich.getNotice());
		dto.setTotalRichDiamond(rich.getTotalRichDiamond());
		dto.setTotalRichPeople(rich.getTotalRichPeople());
		dto.setPowerList(convertRichPowerDetailBO(rich.getPowerList()));
		return dto;
	}

	private static List<RichPowerDetailDTO> convertRichPowerDetailBO(List<RichPowerDetailBO> powerList) {
		List<RichPowerDetailDTO> list = Lists.newArrayList();
		if(powerList.size()==0){
			return list;
		}
		for(RichPowerDetailBO bo : powerList){
			RichPowerDetailDTO dto = new RichPowerDetailDTO();
			dto.setMemberNum(bo.getMemberNum());
			dto.setTotalPower(bo.getTotalPower());
			list.add(dto);
		}
		return list;
	}

	public static List<RichPowerDetailBO> convertPowerList(List<RichAccountDO> listAccount) {
		List<RichPowerDetailBO> lt =Lists.newArrayList();
		if(listAccount.size()==0){
			return lt;
		}
		for(RichAccountDO rich : listAccount){
			RichPowerDetailBO power = new RichPowerDetailBO();
			power.setMemberNum(rich.getUserNum());
			power.setTotalPower(rich.getPower());
			lt.add(power);
		}
		return lt;
	}

	public static RichPowerInfoRecordDTO convertRichPowerInfoRecordDTO(RichPowerInfoRecordBO richBo) {
		RichPowerInfoRecordDTO rich = new RichPowerInfoRecordDTO();
		rich.setTotalPower(richBo.getTotalPower());
		rich.setListPowerRecord(convertPowerListDTO(richBo.getListRichPowerRecord()));
		return rich;
	}

	private static List<RichPowerRecordDTO> convertPowerListDTO(List<RichPowerRecordBO> listRichPowerRecord) {
		List<RichPowerRecordDTO> list = Lists.newArrayList();
		if(listRichPowerRecord.size()==0){
			return list;
		}
		for(RichPowerRecordBO power : listRichPowerRecord){
			RichPowerRecordDTO dto = new RichPowerRecordDTO();
			dto.setDirectionEnum(power.getDirectionEnum());
			dto.setPower(power.getPower());
			dto.setTitle(power.getTitle());
			dto.setTypeEnum(power.getTypeEnum());
			dto.setDate(power.getDate());
			list.add(dto);
		}
		return list;
	}

	public static RichMyDiamondRecordInfoDTO convertRichMyDiamondRecordInfoDTO(RichMyDiamondRecordInfoBO bo) {
		RichMyDiamondRecordInfoDTO dto = new RichMyDiamondRecordInfoDTO();
		dto.setTotalDiamond(bo.getTotalDiamond());
		dto.setDetailList(convertRichMyDiamondRecordDetailDTO(bo.getRecordList()));
		return dto;
	}

	private static List<RichMyDiamondRecordDetailDTO> convertRichMyDiamondRecordDetailDTO(List<RichMyDiamondRecordDetailBO> recordList) {
		List<RichMyDiamondRecordDetailDTO> lt = Lists.newArrayList();
		if(recordList.size()==0){
			return lt;
		}
		for(RichMyDiamondRecordDetailBO rich : recordList){
			RichMyDiamondRecordDetailDTO dto = new RichMyDiamondRecordDetailDTO();
			dto.setAmount(rich.getAmount());
			dto.setDirectionEnum(rich.getDirectionEnum());
			dto.setSourceEnum(rich.getSourceEnum());
			dto.setTakeTime(rich.getTakeTime());
			dto.setTitle(rich.getTitle());
			dto.setTypeEnum(rich.getTypeEnum());
			lt.add(dto);
		}
		return lt;
	}

	public static List<RichMyDiamondRecordDetailBO> convertRichMyDiamondRecordDetailBO(List<RichDiamondRecordBO> listRecord) {
		List<RichMyDiamondRecordDetailBO> lt =Lists.newArrayList();
		if(listRecord.size()==0){
			return lt;
		}
		for(RichDiamondRecordBO record :listRecord){
			RichMyDiamondRecordDetailBO rich =new RichMyDiamondRecordDetailBO();
			rich.setAmount(record.getAmount());
			rich.setDirectionEnum(record.getDirectionEnum());
			rich.setSourceEnum(record.getSourceEnum());
			rich.setTakeTime(record.getTakeTime());
			rich.setTitle(record.getTitle());
			rich.setTypeEnum(record.getTypeEnum());
			lt.add(rich);
		}
		return lt;
	}
}
