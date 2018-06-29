package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordOperatorDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDO;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
public class DrawLotteryActivityRecordConverter {

    public static DrawLotteryActivityRecordBO convertBO(DrawLotteryActivityRecordDO recordDO) {
        if (recordDO == null) {
            return null;
        }

        DrawLotteryActivityRecordBO recordBO = new DrawLotteryActivityRecordBO();
        recordBO.setId(recordDO.getId());
        recordBO.setUserId(recordDO.getUserId());
        recordBO.setUserNum(recordDO.getUserNum());
        recordBO.setUserAccount(recordDO.getUserAccount());
        recordBO.setDrawLotteryActivityId(recordDO.getDrawLotteryActivityId());
        recordBO.setDrawLotteryActivityPrizeId(recordDO.getDrawLotteryActivityPrizeId());
        recordBO.setPrizeName(recordDO.getPrizeName());
        recordBO.setStatus(recordDO.getStatus());
        recordBO.setChannel(recordDO.getChannel());
        recordBO.setPayPoint(recordDO.getPayPoint());
        recordBO.setConsigneeName(recordDO.getConsigneeName());
        recordBO.setConsigneeMobile(recordDO.getConsigneeMobile());
        recordBO.setConsigneeAddress(recordDO.getConsigneeAddress());
        recordBO.setGmtModified(recordDO.getGmtModified());
        recordBO.setGmtCreate(recordDO.getGmtCreate());
        recordBO.setExpressNum(recordDO.getExpressNum());
        return recordBO;
    }

    public static DrawLotteryActivityRecordDTO convertDTO(DrawLotteryActivityRecordBO recordBO) {
        if (recordBO == null) {
            return null;
        }

        DrawLotteryActivityRecordDTO recordDTO = new DrawLotteryActivityRecordDTO();
        recordDTO.setId(recordBO.getId());
        recordDTO.setDrawLotteryActivityId(recordBO.getDrawLotteryActivityId());
        recordDTO.setPrizeName(recordBO.getPrizeName());
        recordDTO.setStatusEnum(DrawLotteryActivityRecordStatusEnum.getEnum(recordBO.getStatus()));
        return recordDTO;
    }

    public static List<DrawLotteryActivityRecordBO> convertBOS(List<DrawLotteryActivityRecordDO> recordDOS) {
        List<DrawLotteryActivityRecordBO> recordBOS = new ArrayList<>();
        if (recordDOS == null || recordDOS.isEmpty()) {
            return recordBOS;
        }

        for (DrawLotteryActivityRecordDO recordDO : recordDOS) {
            recordBOS.add(convertBO(recordDO));
        }
        return recordBOS;
    }

    public static List<DrawLotteryActivityRecordOperatorDTO> convertOperatorDTOS(List<DrawLotteryActivityRecordBO> recordBOS) {
        List<DrawLotteryActivityRecordOperatorDTO> operatorDTOS = new ArrayList<>();
        if (recordBOS == null || recordBOS.isEmpty()) {
            return operatorDTOS;
        }

        for (DrawLotteryActivityRecordBO recordBO : recordBOS) {
            DrawLotteryActivityRecordOperatorDTO operatorDTO = new DrawLotteryActivityRecordOperatorDTO();
            operatorDTO.setId(recordBO.getId());
            operatorDTO.setUserAccount(recordBO.getUserAccount());
            operatorDTO.setPrizeName(recordBO.getPrizeName());
            operatorDTO.setStatusEnum(DrawLotteryActivityRecordStatusEnum.getEnum(recordBO.getStatus()));
            operatorDTO.setStatusDes(DrawLotteryActivityRecordStatusEnum.getEnum(recordBO.getStatus()).getName());
            operatorDTO.setChannelEnum(DrawLotteryActivityRecordChannelEnum.getEnum(recordBO.getChannel()));
            operatorDTO.setChannelDes(DrawLotteryActivityRecordChannelEnum.getEnum(recordBO.getChannel()).getName());
            operatorDTO.setConsigneeName(recordBO.getConsigneeName());
            operatorDTO.setConsigneeMobile(recordBO.getConsigneeMobile());
            operatorDTO.setConsigneeAddress(recordBO.getConsigneeAddress());
            operatorDTO.setExpressNum(recordBO.getExpressNum());
            operatorDTOS.add(operatorDTO);
        }
        return operatorDTOS;
    }
    
    
    public static List<DrawLotteryActivityRecordPageDTO> convertDTOS(List<DrawLotteryActivityRecordBO> recordBOS) {
        List<DrawLotteryActivityRecordPageDTO> pageDTOS = new ArrayList<>();
        if (recordBOS == null || recordBOS.isEmpty()) {
            return pageDTOS;
        }

        for (DrawLotteryActivityRecordBO recordBO : recordBOS) {
        	DrawLotteryActivityRecordPageDTO pageDTO = new DrawLotteryActivityRecordPageDTO();
        	pageDTO.setId(recordBO.getId());
        	pageDTO.setPrizeName(recordBO.getPrizeName());
        	pageDTO.setStatusEnum(DrawLotteryActivityRecordStatusEnum.getEnum(recordBO.getStatus()));
        	pageDTO.setExpressNum(recordBO.getExpressNum());
        	pageDTO.setGmtCreate(recordBO.getGmtCreate());
        	pageDTO.setPrizeType(recordBO.getPrizeType());
        	pageDTO.setPrizeImg(recordBO.getPrizeImg());
        	pageDTOS.add(pageDTO);
        }
        return pageDTOS;
    }

}
