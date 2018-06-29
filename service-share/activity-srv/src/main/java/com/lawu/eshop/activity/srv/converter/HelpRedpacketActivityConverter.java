package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.constants.RedPacketActivityTypeEnum;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;
import com.lawu.eshop.activity.dto.GenerateLargeRedpacketDTO;
import com.lawu.eshop.activity.dto.GenerateRedpacketDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityOpenDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityQueryDTO;
import com.lawu.eshop.activity.dto.RedpacketActivityInfoOfAttendDTO;
import com.lawu.eshop.activity.srv.bo.GenerateLargeRedpacketBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDO;

/**
 * 助力活动红包转换类
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
public class HelpRedpacketActivityConverter {
    
    /**
     * 隐藏构造方法
     */
    private HelpRedpacketActivityConverter() {
        throw new IllegalAccessError("Utility class");
    }
    
    public static HelpRedpacketActivityBO convert(HelpRedpacketActivityDO source) {
        if (source == null) {
            return null;
        }
        HelpRedpacketActivityBO target  = new HelpRedpacketActivityBO();
        target.setActivityTheme(source.getActivityTheme());
        target.setEndTime(source.getEndTime());
        target.setId(source.getId());
        target.setIsOpen(source.getIsOpen());
        target.setIsCloseEntry(source.getIsCloseEntry());
        target.setMaxRedpacket(source.getMaxRedpacket());
        target.setMinRedpacket(source.getMinRedpacket());
        target.setMultiple(source.getMultiple());
        target.setRegEndTime(source.getRegEndTime());
        target.setRegStartTime(source.getRegStartTime());
        target.setStartTime(source.getStartTime());
        target.setTotalManualAmount(source.getTotalManualAmount());
        target.setTotalAutoAmount(source.getTotalAutoAmount());
        target.setWxActName(source.getWxActName());
        target.setWxRemark(source.getWxRemark());
        target.setWxSendName(source.getWxSendName());
        target.setWxWishing(source.getWxWishing());
        target.setManualInfo(source.getManualInfo());
        target.setStartPic(source.getStartPic());
        target.setEndPic(source.getEndPic());
        target.setEndUrl(source.getEndUrl());
        target.setRedpacketType(RedPacketTypeEnum.getEnum(source.getRedpacketType()));
        target.setType(RedPacketActivityTypeEnum.getEnum(source.getType()));
        target.setSentAmount(source.getSentAmount());
        if (StringUtils.isNotBlank(source.getRules())) {
            target.setRules(JSONObject.parseArray(source.getRules(), String.class));
        }
        target.setPuzzlePic(source.getAdPic());
        return target;
    }
    
    public static List<HelpRedpacketActivityBO> convert(List<HelpRedpacketActivityDO> source) {
        if (source == null) {
            return null;
        }
        List<HelpRedpacketActivityBO> rtn = new ArrayList<>();
        for (HelpRedpacketActivityDO item : source) {
            rtn.add(convert(item));
        }
        return rtn;
    }
    
    public static List<HelpRedpacketActivityQueryDTO> convertHelpRedpacketActivityQueryDTOList(List<HelpRedpacketActivityBO> source) {
        List<HelpRedpacketActivityQueryDTO> rtn = new ArrayList<>();
        if (source == null) {
            return rtn;
        }
        for (HelpRedpacketActivityBO item : source) {
            HelpRedpacketActivityQueryDTO target = new HelpRedpacketActivityQueryDTO();
            target.setId(item.getId());
            target.setActivityTheme(item.getActivityTheme());
            target.setCloseEntry(item.getIsCloseEntry());
            target.setMaxRedpacket(item.getMaxRedpacket());
            target.setMinRedpacket(item.getMinRedpacket());
            target.setMultiple(item.getMultiple());
            target.setOpen(item.getIsOpen());
            target.setRegStartTime(item.getRegStartTime());
            target.setStatus(item.getStatus());
            rtn.add(target);
        }
        return rtn;
    }
    
    public static HelpRedpacketActivityDTO convert(HelpRedpacketActivityBO source) {
        if (source == null) {
            return null;
        }
        HelpRedpacketActivityDTO target  = new HelpRedpacketActivityDTO();
        target.setActivityTheme(source.getActivityTheme());
        target.setEndTime(source.getEndTime());
        target.setOpen(source.getIsOpen());
        target.setMaxRedpacket(source.getMaxRedpacket());
        target.setMinRedpacket(source.getMinRedpacket());
        target.setMultiple(source.getMultiple());
        target.setRegEndTime(source.getRegEndTime());
        target.setRegStartTime(source.getRegStartTime());
        target.setStartTime(source.getStartTime());
        target.setTotalManualAmount(source.getTotalManualAmount());
        target.setTotalAutoAmount(source.getTotalAutoAmount());
        target.setStatus(source.getStatus());
        target.setWxActName(source.getWxActName());
        target.setWxRemark(source.getWxRemark());
        target.setWxSendName(source.getWxSendName());
        target.setWxWishing(source.getWxWishing());
        target.setStartPic(source.getStartPic());
        target.setEndPic(source.getEndPic());
        target.setEndUrl(source.getEndUrl());
        target.setCloseEntry(source.getIsCloseEntry());
        target.setRedpacketType(source.getRedpacketType());
        target.setType(source.getType());
        target.setRules(source.getRules());
         target.setSentAmount(source.getSentAmount());
        target.setPuzzlePic(source.getPuzzlePic());
        return target;
    }
    
    /**
     * 
     * @param source
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    public static GenerateLargeRedpacketDTO convert(GenerateLargeRedpacketBO source) {
        if (source == null) {
            return null;
        }
        GenerateLargeRedpacketDTO target  = new GenerateLargeRedpacketDTO();
        target.setExpectedMaxRedpacketAmount(source.getExpectedMaxRedpacketAmount());
        target.setExpectedMinRedpacketAmount(source.getExpectedMinRedpacketAmount());
        target.setTotalManualAmount(source.getTotalManualAmount());
        target.setRedpacketList(new ArrayList<>());
        source.getRedpacketList().forEach(item -> {
            GenerateRedpacketDTO generateRedpacketDTO = new GenerateRedpacketDTO();
            generateRedpacketDTO.setAccount(item.getAccount());
            generateRedpacketDTO.setAttendDetailId(item.getAttendDetailId());
            generateRedpacketDTO.setFinalMoney(item.getFinalMoney());
            generateRedpacketDTO.setGenerateLargeRedpacketIndex(item.getGenerateLargeRedpacketIndex());
            generateRedpacketDTO.setHeadimg(item.getHeadimg());
            generateRedpacketDTO.setHelpCount(item.getHelpCount());
            generateRedpacketDTO.setOriginalMoney(item.getOriginalMoney());
            generateRedpacketDTO.setUserNum(item.getUserNum());
            target.getRedpacketList().add(generateRedpacketDTO);
        });
        return target;
    }
    
    public static HelpRedpacketActivityOpenDTO convertHelpRedpacketActivityOpenDTO(HelpRedpacketActivityBO source) {
        if (source == null) {
            return null;
        }
        HelpRedpacketActivityOpenDTO target  = new HelpRedpacketActivityOpenDTO();
        target.setId(source.getId());
        target.setOpen(source.getIsOpen());
        target.setStatus(source.getStatus());
        target.setStartPic(source.getStartPic());
        target.setEndPic(source.getEndPic());
        target.setEndUrl(source.getEndUrl());
        target.setCloseEntry(source.getIsCloseEntry());
        target.setType(source.getType());
        return target;
    }
    
    public static List<HelpRedpacketActivityOpenDTO> convertHelpRedpacketActivityOpenDTOList(List<HelpRedpacketActivityBO> source) {
        List<HelpRedpacketActivityOpenDTO> rtn = new ArrayList<>();
        if (source == null) {
            return rtn;
        }
        for (HelpRedpacketActivityBO item : source) {
            rtn.add(convertHelpRedpacketActivityOpenDTO(item));
        }
        return rtn;
    }
    
    public static RedpacketActivityInfoOfAttendDTO convertRedpacketActivityInfoOfAttendDTO(HelpRedpacketActivityBO source) {
        if (source == null) {
            return null;
        }
        RedpacketActivityInfoOfAttendDTO target  = new RedpacketActivityInfoOfAttendDTO();
        target.setRedpacketType(source.getRedpacketType());
        return target;
    }
}
