package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.dto.LotteryActivityDTO;
import com.lawu.eshop.mall.dto.LotteryActivityOperatorDTO;
import com.lawu.eshop.mall.srv.bo.LotteryActivityBO;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDO;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
public class LotteryActivityConverter {

    /**
     * BO转换
     *
     * @param activityDO
     * @return
     */
    public static LotteryActivityBO converBO(LotteryActivityDO activityDO) {
        if (activityDO == null) {
            return null;
        }

        LotteryActivityBO activityBO = new LotteryActivityBO();
        activityBO.setId(activityDO.getId());
        activityBO.setPrizeName(activityDO.getPrizeName());
        activityBO.setPrizePrice(activityDO.getPrizePrice());
        activityBO.setPrizeNumber(activityDO.getPrizeNumber());
        activityBO.setImagePath(activityDO.getImagePath());
        activityBO.setBeginTime(activityDO.getBeginTime());
        activityBO.setEndTime(activityDO.getEndTime());
        activityBO.setGrade(activityDO.getGrade());
        activityBO.setStatus(activityDO.getStatus());
        activityBO.setGmtModified(activityDO.getGmtModified());
        return activityBO;
    }

    /**
     * DTO转换
     *
     * @param activityBO
     * @return
     */
    public static LotteryActivityDTO converDTO(LotteryActivityBO activityBO) {
        if (activityBO == null) {
            return null;
        }

        LotteryActivityDTO activityDTO = new LotteryActivityDTO();
        activityDTO.setLotteryActivityId(activityBO.getId());
        activityDTO.setPrizeName(activityBO.getPrizeName());
        activityDTO.setPrizePrice(activityBO.getPrizePrice());
        activityDTO.setPrizeNumber(activityBO.getPrizeNumber());
        activityDTO.setImagePath(activityBO.getImagePath());
        activityDTO.setGradeEnum(MemberGradeEnum.getEnum(activityBO.getGrade()));
        activityDTO.setGrade(activityBO.getGrade());
        activityDTO.setStatusEnum(LotteryActivityStatusEnum.getEnum(activityBO.getStatus()));
        activityDTO.setLotteryCount(activityBO.getLotteryCount());
        activityDTO.setLotteryNumber(activityBO.getLotteryNumber());
        long startMillisecond = activityBO.getBeginTime().getTime() - new Date().getTime();
        long endMillisecond = activityBO.getEndTime().getTime() - new Date().getTime();
        long lotteryMillisecond = activityBO.getEndTime().getTime() - activityBO.getBeginTime().getTime();
        activityDTO.setStartMillisecond(startMillisecond > 0 ? startMillisecond : 0);
        if (activityDTO.getStatusEnum().getVal().byteValue() == LotteryActivityStatusEnum.PUBLISHED.getVal()) {
            activityDTO.setMillisecond(lotteryMillisecond > 0 ? lotteryMillisecond : 0);
        } else {
            activityDTO.setMillisecond(endMillisecond > 0 ? endMillisecond : 0);
        }
        return activityDTO;
    }

    /**
     * BO转换
     *
     * @param activityDOS
     * @return
     */
    public static List<LotteryActivityBO> converBOS(List<LotteryActivityDO> activityDOS) {
        List<LotteryActivityBO> activityBOS = new ArrayList<>();
        if (activityDOS == null || activityDOS.isEmpty()) {
            return activityBOS;
        }

        for (LotteryActivityDO activityDO : activityDOS) {
            activityBOS.add(converBO(activityDO));
        }
        return activityBOS;
    }

    /**
     * DTO转换
     *
     * @param activityBOS
     * @return
     */
    public static List<LotteryActivityDTO> converDTOS(List<LotteryActivityBO> activityBOS) {
        List<LotteryActivityDTO> activityDTOS = new ArrayList<>();
        if (activityBOS == null || activityBOS.isEmpty()) {
            return activityDTOS;
        }

        for (LotteryActivityBO activityBO : activityBOS) {
            activityDTOS.add(converDTO(activityBO));
        }
        return activityDTOS;
    }

    /**
     * DTO转换
     *
     * @param activityBO
     * @return
     */
    public static LotteryActivityOperatorDTO converOperatorDTO(LotteryActivityBO activityBO) {
        if (activityBO == null) {
            return null;
        }

        LotteryActivityOperatorDTO activityOperatorDTO = new LotteryActivityOperatorDTO();
        activityOperatorDTO.setId(activityBO.getId());
        activityOperatorDTO.setPrizeName(activityBO.getPrizeName());
        activityOperatorDTO.setPrizePrice(activityBO.getPrizePrice());
        activityOperatorDTO.setPrizeNumber(activityBO.getPrizeNumber());
        activityOperatorDTO.setImagePath(activityBO.getImagePath());
        activityOperatorDTO.setBeginTime(activityBO.getBeginTime());
        activityOperatorDTO.setEndTime(activityBO.getEndTime());
        activityOperatorDTO.setGradeEnum(MemberGradeEnum.getEnum(activityBO.getGrade()));
        activityOperatorDTO.setGradeDes(MemberGradeEnum.getEnum(activityBO.getGrade()).getName());
        activityOperatorDTO.setStatusEnum(LotteryActivityStatusEnum.getEnum(activityBO.getStatus()));
        activityOperatorDTO.setStatusDes(LotteryActivityStatusEnum.getEnum(activityBO.getStatus()).getName());
        if (LotteryActivityStatusEnum.LOTTERYED.getVal().byteValue() == activityBO.getStatus()) {
            activityOperatorDTO.setLotteryDate(activityBO.getGmtModified());
        }
        return activityOperatorDTO;
    }

    /**
     * DTO转换
     *
     * @param activityBOS
     * @return
     */
    public static List<LotteryActivityOperatorDTO> converOperatorDTOS(List<LotteryActivityBO> activityBOS) {
        List<LotteryActivityOperatorDTO> activityOperatorDTOS = new ArrayList<>();
        if (activityBOS == null || activityBOS.isEmpty()) {
            return activityOperatorDTOS;
        }

        for (LotteryActivityBO activityBO : activityBOS) {
            activityOperatorDTOS.add(converOperatorDTO(activityBO));
        }
        return activityOperatorDTOS;
    }

}
