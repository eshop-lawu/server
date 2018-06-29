package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.PointLotteryActivityOperatorDTO;
import com.lawu.eshop.activity.dto.PointLotteryDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryInfoDTO;
import com.lawu.eshop.activity.dto.PointLotteryRelateDTO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public class PointLotteryActivityConverter {

    public static PointLotteryActivityBO convertBO(PointLotteryActivityDO activityDO) {
        if (activityDO == null) {
            return null;
        }

        PointLotteryActivityBO activityBO = new PointLotteryActivityBO();
        activityBO.setId(activityDO.getId());
        activityBO.setPrizeName(activityDO.getPrizeName());
        activityBO.setPrizePrice(activityDO.getPrizePrice());
        activityBO.setPrizeImagePath(activityDO.getPrizeImagePath());
        activityBO.setBeginTime(activityDO.getBeginTime());
        activityBO.setEndTime(activityDO.getEndTime());
        activityBO.setDrawTime(activityDO.getDrawTime());
        activityBO.setLotteryTime(activityDO.getLotteryTime());
        activityBO.setLotteryPoint(activityDO.getLotteryPoint());
        activityBO.setLotteryCount(activityDO.getLotteryCount());
        activityBO.setStatus(activityDO.getStatus());
        activityBO.setAttentNumber(activityDO.getAttentNumber());
        activityBO.setHotNumber(activityDO.getHotNumber());
        activityBO.setLotteryParam(activityDO.getLotteryParam());
        activityBO.setLotteryBaseNum(activityDO.getLotteryBaseNum());
        activityBO.setLotteryResultNums(activityDO.getLotteryResultNums());
        activityBO.setGmtModified(activityDO.getGmtModified());
        activityBO.setGmtCreate(activityDO.getGmtCreate());
        return activityBO;
    }

    public static List<PointLotteryActivityBO> convertBOS(List<PointLotteryActivityDO> activityDOS) {
        List<PointLotteryActivityBO> activityBOS = new ArrayList<>();
        if (activityDOS == null || activityDOS.isEmpty()) {
            return activityBOS;
        }

        for (PointLotteryActivityDO activityDO : activityDOS) {
            activityBOS.add(convertBO(activityDO));
        }
        return activityBOS;
    }

    public static PointLotteryInfoDTO convertInfoDTO(PointLotteryActivityBO activityBO) {
        if (activityBO == null) {
            return null;
        }

        PointLotteryInfoDTO infoDTO = new PointLotteryInfoDTO();
        infoDTO.setId(activityBO.getId());
        infoDTO.setPrizeName(activityBO.getPrizeName());
        infoDTO.setPrizePrice(activityBO.getPrizePrice());
        infoDTO.setPrizeImagePath(activityBO.getPrizeImagePath());
        infoDTO.setLotteryPoint(activityBO.getLotteryPoint());
        infoDTO.setAttentNumber(activityBO.getAttentNumber());
        infoDTO.setHot(false);
        if (activityBO.getHotNumber() > 0 && activityBO.getAttentNumber() >= activityBO.getHotNumber()) {
            infoDTO.setHot(true);
        }
        infoDTO.setBeginDays(DateUtil.daysOfTwo(new Date(), activityBO.getBeginTime()));
        Long beginMillisecond = activityBO.getBeginTime().getTime() - new Date().getTime();
        infoDTO.setBeginMillisecond(beginMillisecond > 0 ? beginMillisecond : 0);
        infoDTO.setBeginTime(activityBO.getBeginTime());
        infoDTO.setEndDays(DateUtil.daysOfTwo(new Date(), activityBO.getEndTime()));
        Long endMillisecond = activityBO.getEndTime().getTime() - new Date().getTime();
        infoDTO.setEndMillisecond(endMillisecond > 0 ? endMillisecond : 0);
        infoDTO.setEndTime(activityBO.getEndTime());
        infoDTO.setStatusEnum(PointLotteryActivityStatusEnum.getEnum(activityBO.getStatus()));
        return infoDTO;
    }

    public static List<PointLotteryInfoDTO> convertInfoDTOS(List<PointLotteryActivityBO> activityBOS) {
        List<PointLotteryInfoDTO> infoDTOS = new ArrayList<>();
        if (activityBOS == null || activityBOS.isEmpty()) {
            return infoDTOS;
        }

        for (PointLotteryActivityBO activityBO : activityBOS) {
            infoDTOS.add(convertInfoDTO(activityBO));
        }
        return infoDTOS;
    }

    public static PointLotteryDetailDTO convertDetailDTO(PointLotteryActivityBO activityBO) {
        if (activityBO == null) {
            return null;
        }

        PointLotteryDetailDTO detailDTO = new PointLotteryDetailDTO();
        detailDTO.setId(activityBO.getId());
        detailDTO.setPrizeName(activityBO.getPrizeName());
        detailDTO.setPrizePrice(activityBO.getPrizePrice());
        detailDTO.setPrizeImagePath(activityBO.getPrizeImagePath());
        detailDTO.setLotteryPoint(activityBO.getLotteryPoint());
        detailDTO.setAttentNumber(activityBO.getAttentNumber());
        detailDTO.setHot(false);
        if (activityBO.getHotNumber() > 0 && activityBO.getAttentNumber() >= activityBO.getHotNumber()) {
            detailDTO.setHot(true);
        }
        detailDTO.setBeginDays(DateUtil.daysOfTwo(new Date(), activityBO.getBeginTime()));
        Long beginMillisecond = activityBO.getBeginTime().getTime() - new Date().getTime();
        detailDTO.setBeginMillisecond(beginMillisecond > 0 ? beginMillisecond : 0);
        detailDTO.setBeginTime(activityBO.getBeginTime());
        detailDTO.setEndDays(DateUtil.daysOfTwo(new Date(), activityBO.getEndTime()));
        Long endMillisecond = activityBO.getEndTime().getTime() - new Date().getTime();
        detailDTO.setEndMillisecond(endMillisecond > 0 ? endMillisecond : 0);
        detailDTO.setEndTime(activityBO.getEndTime());
        detailDTO.setStatusEnum(PointLotteryActivityStatusEnum.getEnum(activityBO.getStatus()));
        return detailDTO;
    }

    public static PointLotteryActivityOperatorDTO convertOperatorDTO(PointLotteryActivityBO activityBO) {
        if (activityBO == null) {
            return null;
        }

        PointLotteryActivityOperatorDTO operatorDTO = new PointLotteryActivityOperatorDTO();
        operatorDTO.setId(activityBO.getId());
        operatorDTO.setPrizeName(activityBO.getPrizeName());
        operatorDTO.setPrizePrice(activityBO.getPrizePrice());
        operatorDTO.setPrizeImagePath(activityBO.getPrizeImagePath());
        operatorDTO.setBeginTime(activityBO.getBeginTime());
        operatorDTO.setEndTime(activityBO.getEndTime());
        operatorDTO.setDrawTime(activityBO.getDrawTime());
        operatorDTO.setLotteryTime(activityBO.getLotteryTime());
        operatorDTO.setLotteryPoint(activityBO.getLotteryPoint());
        operatorDTO.setAttentNumber(activityBO.getAttentNumber());
        operatorDTO.setHotNumber(activityBO.getHotNumber());
        operatorDTO.setStatusEnum(PointLotteryActivityStatusEnum.getEnum(activityBO.getStatus()));
        operatorDTO.setStatusDes(PointLotteryActivityStatusEnum.getEnum(activityBO.getStatus()).getName());
        operatorDTO.setLotteryCount(activityBO.getLotteryCount());
        operatorDTO.setLotteryResultNums(activityBO.getLotteryResultNums());
        operatorDTO.setLotteryBaseNum(activityBO.getLotteryBaseNum());
        return operatorDTO;
    }

    public static List<PointLotteryActivityOperatorDTO> convertOperatorDTOS(List<PointLotteryActivityBO> activityBOS) {
        List<PointLotteryActivityOperatorDTO> operatorDTOS = new ArrayList<>();
        if (activityBOS == null || activityBOS.isEmpty()) {
            return operatorDTOS;
        }

        for (PointLotteryActivityBO activityBO : activityBOS) {
            operatorDTOS.add(convertOperatorDTO(activityBO));
        }
        return operatorDTOS;
    }

    public static List<PointLotteryRelateDTO> convertRelateDTOS(List<PointLotteryActivityBO> activityBOS) {
        List<PointLotteryRelateDTO> relateDTOS = new ArrayList<>();
        if (activityBOS == null || activityBOS.isEmpty()) {
            return relateDTOS;
        }

        for (PointLotteryActivityBO activityBO : activityBOS) {
            PointLotteryRelateDTO relateDTO = new PointLotteryRelateDTO();
            relateDTO.setId(activityBO.getId());
            relateDTO.setName(activityBO.getPrizeName());
            relateDTO.setStatusDes(PointLotteryActivityStatusEnum.getEnum(activityBO.getStatus()).getName());
            relateDTOS.add(relateDTO);
        }
        return relateDTOS;
    }

}
