package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeTypeEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDetailDTO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDO;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityPrizeConverter {

    public static DrawLotteryActivityPrizeBO convertBO(DrawLotteryActivityPrizeDO prizeDO) {
        if (prizeDO == null) {
            return null;
        }

        DrawLotteryActivityPrizeBO prizeBO = new DrawLotteryActivityPrizeBO();
        prizeBO.setId(prizeDO.getId());
        prizeBO.setDrawLotteryActivityId(prizeDO.getDrawLotteryActivityId());
        prizeBO.setName(prizeDO.getName());
        prizeBO.setImgPath(prizeDO.getImgPath());
        prizeBO.setPrice(prizeDO.getPrice());
        prizeBO.setNumber(prizeDO.getNumber());
        prizeBO.setInventory(prizeDO.getInventory());
        prizeBO.setStatus(prizeDO.getStatus());
        prizeBO.setAddress(prizeDO.getIsAddress());
        prizeBO.setSendPrize(prizeDO.getIsSendPrize());
        prizeBO.setPrizeType(prizeDO.getPrizeType());
        prizeBO.setFreightPrice(prizeDO.getFreightPrice());
        prizeBO.setMerchantStoreId(prizeDO.getMerchantStoreId());
        prizeBO.setAdImgPath(prizeDO.getAdImgPath());
        prizeBO.setRate(prizeDO.getRate());
        prizeBO.setGmtModified(prizeDO.getGmtModified());
        prizeBO.setGmtCreate(prizeDO.getGmtCreate());
        return prizeBO;
    }

    public static DrawLotteryActivityPrizeDTO convertDTO(DrawLotteryActivityPrizeBO prizeBO) {
        if (prizeBO == null) {
            return null;
        }

        DrawLotteryActivityPrizeDTO prizeDTO = new DrawLotteryActivityPrizeDTO();
        prizeDTO.setId(prizeBO.getId());
        prizeDTO.setName(prizeBO.getName());
        prizeDTO.setImgPath(prizeBO.getImgPath());
        prizeDTO.setPrice(prizeBO.getPrice());
        prizeDTO.setNumber(prizeBO.getNumber());
        prizeDTO.setInventory(prizeBO.getInventory());
        prizeDTO.setStatusEnum(DrawLotteryActivityPrizeStatusEnum.getEnum(prizeBO.getStatus()));
        prizeDTO.setStatusDes(DrawLotteryActivityPrizeStatusEnum.getEnum(prizeBO.getStatus()).getName());
        prizeDTO.setFreightPrice(prizeBO.getFreightPrice());
        prizeDTO.setMerchantStoreId(prizeBO.getMerchantStoreId());
        prizeDTO.setAdImgPath(prizeBO.getAdImgPath());
        prizeDTO.setRate(prizeBO.getRate());
        return prizeDTO;
    }

    public static List<DrawLotteryActivityPrizeBO> convertBOS(List<DrawLotteryActivityPrizeDO> prizeDOS) {
        List<DrawLotteryActivityPrizeBO> prizeBOS = new ArrayList<>();
        if (prizeDOS == null || prizeDOS.isEmpty()) {
            return prizeBOS;
        }

        for (DrawLotteryActivityPrizeDO prizeDO : prizeDOS) {
            prizeBOS.add(convertBO(prizeDO));
        }
        return prizeBOS;
    }

    public static List<DrawLotteryActivityPrizeDTO> convertDTOS(List<DrawLotteryActivityPrizeBO> prizeBOS) {
        List<DrawLotteryActivityPrizeDTO> prizeDTOS = new ArrayList<>();
        if (prizeBOS == null || prizeBOS.isEmpty()) {
            return prizeDTOS;
        }

        for (DrawLotteryActivityPrizeBO prizeBO : prizeBOS) {
            prizeDTOS.add(convertDTO(prizeBO));
        }
        return prizeDTOS;
    }

    public static DrawLotteryActivityPrizeDetailDTO convertDetailDTO(DrawLotteryActivityPrizeBO prizeBO) {
        if (prizeBO == null) {
            return null;
        }

        DrawLotteryActivityPrizeDetailDTO detailDTO = new DrawLotteryActivityPrizeDetailDTO();
        detailDTO.setId(prizeBO.getId());
        detailDTO.setName(prizeBO.getName());
        detailDTO.setImgPath(prizeBO.getImgPath());
        detailDTO.setPrice(prizeBO.getPrice());
        detailDTO.setNumber(prizeBO.getNumber());
        detailDTO.setInventory(prizeBO.getInventory());
        detailDTO.setFreightPrice(prizeBO.getFreightPrice());
        detailDTO.setMerchantStoreId(prizeBO.getMerchantStoreId());
        detailDTO.setAdImgPath(prizeBO.getAdImgPath());
        detailDTO.setRate(prizeBO.getRate());
        detailDTO.setIsAddress(prizeBO.getAddress());
        detailDTO.setIsSendPrize(prizeBO.getSendPrize());
        detailDTO.setPrizeTypeEnum(DrawLotteryActivityPrizeTypeEnum.getEnum(prizeBO.getPrizeType()));
        return detailDTO;
    }

}
