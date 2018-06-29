package com.lawu.eshop.activity.srv.converter;/**
 * Created by ${Yangqh} on 2017/12/28.
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.activity.constants.AbnormalStatusEnum;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendPageDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendSendDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketUserDTO;
import com.lawu.eshop.activity.dto.SignAbnormalAccountDTO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendPageBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketUserBO;
import com.lawu.eshop.activity.srv.bo.SignAbnormalAccountBO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2017/12/28 14:33
 */
public class HelpRedpacketAttendDetailConverter {
    public static HelpRedpacketAttendDetailBO converterBO(HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO) {
        HelpRedpacketAttendDetailBO helpRedpacketAttendDetailBO = new HelpRedpacketAttendDetailBO();
        helpRedpacketAttendDetailBO.setAccount(helpRedpacketAttendDetailDO.getAccount());
        helpRedpacketAttendDetailBO.setHeadimg(helpRedpacketAttendDetailDO.getHeadimg());
        helpRedpacketAttendDetailBO.setUserNum(helpRedpacketAttendDetailDO.getUserNum());
        helpRedpacketAttendDetailBO.setId(helpRedpacketAttendDetailDO.getId());
        helpRedpacketAttendDetailBO.setActivityId(helpRedpacketAttendDetailDO.getActivityId());
        helpRedpacketAttendDetailBO.setWxOpenid(helpRedpacketAttendDetailDO.getWxOpenid());
        helpRedpacketAttendDetailBO.setFinalMoney(BigDecimal.valueOf(helpRedpacketAttendDetailDO.getFinalMoney()));
        helpRedpacketAttendDetailBO.setHelpCount(helpRedpacketAttendDetailDO.getHelpCount());
        return helpRedpacketAttendDetailBO;
    }


    public static HelpRedpacketAttendDTO converterDTO(HelpRedpacketAttendBO helpRedpacketAttendBO) {
        HelpRedpacketAttendDTO helpRedpacketAttendDetailDTO = new HelpRedpacketAttendDTO();
        if (helpRedpacketAttendBO == null) {
            return helpRedpacketAttendDetailDTO;
        }
        helpRedpacketAttendDetailDTO.setId(helpRedpacketAttendBO.getId());
        helpRedpacketAttendDetailDTO.setActivityStatusEnum(helpRedpacketAttendBO.getActivityStatusEnum());
        helpRedpacketAttendDetailDTO.setActivityTheme(helpRedpacketAttendBO.getActivityTheme());
        helpRedpacketAttendDetailDTO.setPuzzlePic(helpRedpacketAttendBO.getPuzzlePic());
        helpRedpacketAttendDetailDTO.setRules(helpRedpacketAttendBO.getRules());
        helpRedpacketAttendDetailDTO.setEndTime(helpRedpacketAttendBO.getEndTime());
        helpRedpacketAttendDetailDTO.setHelpCount(helpRedpacketAttendBO.getHelpCount());
        helpRedpacketAttendDetailDTO.setIsAttend(helpRedpacketAttendBO.getIsAttend());
        helpRedpacketAttendDetailDTO.setStartTime(helpRedpacketAttendBO.getStartTime());
        helpRedpacketAttendDetailDTO.setAccount(helpRedpacketAttendBO.getAccount());
        helpRedpacketAttendDetailDTO.setIsGet(helpRedpacketAttendBO.getIsGet());
        helpRedpacketAttendDetailDTO.setMoney(helpRedpacketAttendBO.getMoney());
        helpRedpacketAttendDetailDTO.setTotalMoney(helpRedpacketAttendBO.getTotalMoney());
        return helpRedpacketAttendDetailDTO;
    }

    public static List<HelpRedpacketAttendPageBO> converterOperatorBO(List<HelpRedpacketAttendDetailDO> list){
    	List<HelpRedpacketAttendPageBO> pageList = new ArrayList<>();
    	if(list.isEmpty()){
    		return pageList;
    	}
    	for (HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO : list) {
    		HelpRedpacketAttendPageBO attendBO = new HelpRedpacketAttendPageBO();
    		attendBO.setAccount(helpRedpacketAttendDetailDO.getAccount()); 
    		attendBO.setAllotTime(helpRedpacketAttendDetailDO.getAllotTime());
    		if(helpRedpacketAttendDetailDO.getFinalMoney()!=null){
    			attendBO.setFinalMoney(BigDecimal.valueOf(helpRedpacketAttendDetailDO.getFinalMoney()).divide(BigDecimal.valueOf(100)));
    		}
    		attendBO.setGmtCreate(helpRedpacketAttendDetailDO.getGmtCreate());
    		attendBO.setHeadimg(helpRedpacketAttendDetailDO.getHeadimg());
    		attendBO.setHelpCount(helpRedpacketAttendDetailDO.getHelpCount());
    		attendBO.setNickname(helpRedpacketAttendDetailDO.getNickname());
    		attendBO.setOriginalMoney(helpRedpacketAttendDetailDO.getOriginalMoney());
    		attendBO.setSendRemark(helpRedpacketAttendDetailDO.getSendRemark());
    		attendBO.setSendTime(helpRedpacketAttendDetailDO.getSendTime());
    		attendBO.setTakeTime(helpRedpacketAttendDetailDO.getTakeTime());
    		attendBO.setStatusEnum(ActivityAttendStatusEnum.getEnum(helpRedpacketAttendDetailDO.getStatus()));
    		attendBO.setAbnormalStatus(AbnormalStatusEnum.getEnum(helpRedpacketAttendDetailDO.getAbnormalStatus()));
    		pageList.add(attendBO);
		}
        return pageList;
    }
    
    public static List<HelpRedpacketAttendPageDTO> converterOperatorDTO(List<HelpRedpacketAttendPageBO> list) {
        List<HelpRedpacketAttendPageDTO> pageList = new ArrayList<>();
        if (list.isEmpty()) {
            return pageList;
        }
        for (HelpRedpacketAttendPageBO helpRedpacketAttendPageBO : list) {
            HelpRedpacketAttendPageDTO attendDTO = new HelpRedpacketAttendPageDTO();
            attendDTO.setAccount(helpRedpacketAttendPageBO.getAccount());
            attendDTO.setAllotTime(helpRedpacketAttendPageBO.getAllotTime());
            attendDTO.setFinalMoney(helpRedpacketAttendPageBO.getFinalMoney());
            attendDTO.setGmtCreate(helpRedpacketAttendPageBO.getGmtCreate());
            attendDTO.setHeadimg(helpRedpacketAttendPageBO.getHeadimg());
            attendDTO.setHelpCount(helpRedpacketAttendPageBO.getHelpCount());
            attendDTO.setNickname(helpRedpacketAttendPageBO.getNickname());
            attendDTO.setOriginalMoney(helpRedpacketAttendPageBO.getOriginalMoney());
            attendDTO.setSendRemark(helpRedpacketAttendPageBO.getSendRemark());
            attendDTO.setSendTime(helpRedpacketAttendPageBO.getSendTime());
            attendDTO.setTakeTime(helpRedpacketAttendPageBO.getTakeTime());
            attendDTO.setStatusEnum(helpRedpacketAttendPageBO.getStatusEnum());
            attendDTO.setStatusName(helpRedpacketAttendPageBO.getStatusEnum().getName(helpRedpacketAttendPageBO.getStatusEnum().getVal()));
            attendDTO.setAbnormalStatus(helpRedpacketAttendPageBO.getAbnormalStatus());
            pageList.add(attendDTO);
        }
        return pageList;
    }

    public static List<HelpRedpacketAttendDetailBO> converterBOS(List<HelpRedpacketAttendDetailDO> attendDetailDOS) {
        List<HelpRedpacketAttendDetailBO> attendDetailBOS = new ArrayList<>();
        if (attendDetailDOS == null || attendDetailDOS.isEmpty()) {
            return attendDetailBOS;
        }

        for (HelpRedpacketAttendDetailDO attendDetailDO : attendDetailDOS) {
            attendDetailBOS.add(converterBO(attendDetailDO));
        }
        return attendDetailBOS;
    }

    public static List<HelpRedpacketAttendSendDTO> converterDTOS(List<HelpRedpacketAttendDetailBO> attendDetailBOS) {
        List<HelpRedpacketAttendSendDTO> attendSendDTOS = new ArrayList<>();
        if (attendDetailBOS == null || attendDetailBOS.isEmpty()) {
            return attendSendDTOS;
        }

        for (HelpRedpacketAttendDetailBO attendDetailBO : attendDetailBOS) {
            HelpRedpacketAttendSendDTO attendSendDTO = new HelpRedpacketAttendSendDTO();
            attendSendDTO.setId(attendDetailBO.getId());
            attendSendDTO.setActivityId(attendDetailBO.getActivityId());
            attendSendDTO.setUserNum(attendDetailBO.getUserNum());
            attendSendDTO.setWxOpenid(attendDetailBO.getWxOpenid());
            attendSendDTO.setFinalMoney(attendDetailBO.getFinalMoney().intValue());
            attendSendDTOS.add(attendSendDTO);
        }
        return attendSendDTOS;
    }


    public static List<HelpRedpacketUserBO> converterUserBO(List<HelpRedpacketAttendDetailDO> list) {
        List<HelpRedpacketUserBO> pageList = new ArrayList<>();
        if (list.isEmpty()) {
            return pageList;
        }
        for (HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO : list) {
            HelpRedpacketUserBO attendBO = new HelpRedpacketUserBO();
            attendBO.setAccount(helpRedpacketAttendDetailDO.getAccount());
            attendBO.setMoney(BigDecimal.valueOf(helpRedpacketAttendDetailDO.getFinalMoney()).divide(BigDecimal.valueOf(100)));
            pageList.add(attendBO);
        }
        return pageList;
    }

    public static List<HelpRedpacketUserDTO> converterUserDTO(List<HelpRedpacketUserBO> list) {
        List<HelpRedpacketUserDTO> pageList = new ArrayList<>();
        if (list.isEmpty()) {
            return pageList;
        }
        for (HelpRedpacketUserBO helpRedpacketUserBO : list) {
            HelpRedpacketUserDTO attendDTO = new HelpRedpacketUserDTO();
            attendDTO.setAccount(helpRedpacketUserBO.getAccount().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
            attendDTO.setMoney(helpRedpacketUserBO.getMoney());
            pageList.add(attendDTO);
        }
        return pageList;
    }

    public static List<SignAbnormalAccountBO> converterAbnormalBOS(List<HelpRedpacketAttendDetailDO> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<SignAbnormalAccountBO> accountBOS = new ArrayList<>();
        SignAbnormalAccountBO accountBO;
        for (HelpRedpacketAttendDetailDO detailDO : list) {
            accountBO = new SignAbnormalAccountBO();
            accountBO.setId(detailDO.getId());
            accountBO.setActivityId(detailDO.getActivityId());
            accountBO.setAttendTime(detailDO.getGmtCreate());
            accountBO.setAccount(detailDO.getAccount());
            accountBO.setUserNum(detailDO.getUserNum());
            accountBO.setHelpCount(detailDO.getHelpCount());
            accountBO.setStatusEnum(AbnormalStatusEnum.getEnum(detailDO.getAbnormalStatus()));
            accountBOS.add(accountBO);
        }
        return accountBOS;
    }

    public static List<SignAbnormalAccountDTO> converterAbnormalDTOS(List<SignAbnormalAccountBO> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<SignAbnormalAccountDTO> accountDTOS = new ArrayList<>();
        SignAbnormalAccountDTO accountDTO;
        for (SignAbnormalAccountBO accountBO : list) {
            accountDTO = new SignAbnormalAccountDTO();
            accountDTO.setId(accountBO.getId());
            accountDTO.setActivityId(accountBO.getActivityId());
            accountDTO.setAttendTime(accountBO.getAttendTime());
            accountDTO.setAccount(accountBO.getAccount());
            accountDTO.setUserNum(accountBO.getUserNum());
            accountDTO.setHelpCount(accountBO.getHelpCount());
            accountDTO.setStatusEnum(accountBO.getStatusEnum());
            accountDTOS.add(accountDTO);
        }
        return accountDTOS;
    }
}

