package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDetailDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityNoticeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.dto.LotteryInfoDTO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityBO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.eshop.activity.srv.bo.LotteryInfoBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityDO;
import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.utils.StringUtil;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityConverter {

    public static DrawLotteryActivityBO convertBO(DrawLotteryActivityDO activityDO) {
        if (activityDO == null) {
            return null;
        }

        DrawLotteryActivityBO activityBO = new DrawLotteryActivityBO();
        activityBO.setId(activityDO.getId());
        activityBO.setName(activityDO.getName());
        activityBO.setImgPath(activityDO.getImgPath());
        activityBO.setGrade(activityDO.getGrade());
        activityBO.setStatus(activityDO.getStatus());
        activityBO.setRemark(activityDO.getRemark());
        activityBO.setBeginTime(activityDO.getBeginTime());
        activityBO.setEndTime(activityDO.getEndTime());
        activityBO.setGmtModified(activityDO.getGmtModified());
        activityBO.setGmtCreate(activityDO.getGmtCreate());
        return activityBO;
    }

    public static DrawLotteryActivityDTO convertDTO(DrawLotteryActivityBO activityBO) {
        if (activityBO == null) {
            return null;
        }

        DrawLotteryActivityDTO activityDTO = new DrawLotteryActivityDTO();
        activityDTO.setId(activityBO.getId());
        activityDTO.setName(activityBO.getName());
        activityDTO.setImgPath(activityBO.getImgPath());
        activityDTO.setGradeEnum(MemberGradeEnum.getEnum(activityBO.getGrade()));
        activityDTO.setGrade(activityBO.getGrade());
        activityDTO.setGradeDes(MemberGradeEnum.getEnum(activityBO.getGrade()).getName());
        activityDTO.setStatusEnum(DrawLotteryActivityStatusEnum.getEnum(activityBO.getStatus()));
        activityDTO.setStatusDes(DrawLotteryActivityStatusEnum.getEnum(activityBO.getStatus()).getName());
        activityDTO.setRemark(activityBO.getRemark());
        activityDTO.setBeginTime(activityBO.getBeginTime());
        activityDTO.setEndTime(activityBO.getEndTime());
        return activityDTO;
    }

    public static List<DrawLotteryActivityBO> convertBOS(List<DrawLotteryActivityDO> activityDOS) {
        List<DrawLotteryActivityBO> activityBOS = new ArrayList<>();
        if (activityDOS == null || activityDOS.isEmpty()) {
            return activityBOS;
        }

        for (DrawLotteryActivityDO activityDO : activityDOS) {
            activityBOS.add(convertBO(activityDO));
        }
        return activityBOS;
    }

    public static List<DrawLotteryActivityDTO> convertDTOS(List<DrawLotteryActivityBO> activityBOS) {
        List<DrawLotteryActivityDTO> activityDTOS = new ArrayList<>();
        if (activityBOS == null || activityBOS.isEmpty()) {
            return activityDTOS;
        }

        for (DrawLotteryActivityBO activityBO : activityBOS) {
            activityDTOS.add(convertDTO(activityBO));
        }
        return activityDTOS;
    }

    public static DrawLotteryActivityDetailDTO convertDetailDTO(DrawLotteryActivityBO activityBO) {
        if (activityBO == null) {
            return null;
        }

        DrawLotteryActivityDetailDTO detailDTO = new DrawLotteryActivityDetailDTO();
        detailDTO.setId(activityBO.getId());
        detailDTO.setName(activityBO.getName());
        detailDTO.setImgPath(activityBO.getImgPath());
        detailDTO.setGradeEnum(MemberGradeEnum.getEnum(activityBO.getGrade()));
        detailDTO.setGrade(activityBO.getGrade());
        detailDTO.setGradeDes(MemberGradeEnum.getEnum(activityBO.getGrade()).getName());
        detailDTO.setStatusEnum(DrawLotteryActivityStatusEnum.getEnum(activityBO.getStatus()));
        detailDTO.setStatusDes(DrawLotteryActivityStatusEnum.getEnum(activityBO.getStatus()).getName());
        detailDTO.setRemark(activityBO.getRemark());
        detailDTO.setBeginTime(activityBO.getBeginTime());
        detailDTO.setEndTime(activityBO.getEndTime());
        long startMillisecond = activityBO.getBeginTime().getTime() - new Date().getTime();
        long endMillisecond = activityBO.getEndTime().getTime() - new Date().getTime();
        long intervalMillisecond = activityBO.getEndTime().getTime() - activityBO.getBeginTime().getTime();
        detailDTO.setStartMillisecond(startMillisecond > 0 ? startMillisecond : 0);
        if (detailDTO.getStatusEnum().getVal().byteValue() == DrawLotteryActivityStatusEnum.PUBLISHED.getVal()) {
            detailDTO.setEndMillisecond(intervalMillisecond > 0 ? intervalMillisecond : 0);
        } else {
            detailDTO.setEndMillisecond(endMillisecond > 0 ? endMillisecond : 0);
        }
        detailDTO.setPrizeNumber(activityBO.getPrizeNumber());
        detailDTO.setFreeLotteryCount(activityBO.getFreeLotteryCount());
        detailDTO.setChannelEnum(activityBO.getLotteryChannel() == null ? null : DrawLotteryActivityRecordChannelEnum.getEnum(activityBO.getLotteryChannel()));
        List<DrawLotteryActivityPrizeDTO> prizeDTOS = new ArrayList<>();
        for (DrawLotteryActivityPrizeBO prizeBO : activityBO.getPrizeBOS()) {
            DrawLotteryActivityPrizeDTO prizeDTO = new DrawLotteryActivityPrizeDTO();
            prizeDTO.setId(prizeBO.getId());
            prizeDTO.setName(prizeBO.getName());
            prizeDTO.setImgPath(prizeBO.getImgPath());
            prizeDTO.setPrice(prizeBO.getPrice());
            prizeDTO.setNumber(prizeBO.getNumber());
            prizeDTO.setMerchantStoreId(prizeBO.getMerchantStoreId());
            prizeDTOS.add(prizeDTO);
        }
        detailDTO.setActivityPrizeDTOS(prizeDTOS);
        return detailDTO;
    }

    public static List<DrawLotteryActivityNoticeDTO> convertNoticeDTOS(List<DrawLotteryActivityBO> activityBOS) {
        List<DrawLotteryActivityNoticeDTO> noticeDTOS = new ArrayList<>();
        if (activityBOS == null || activityBOS.isEmpty()) {
            return noticeDTOS;
        }

        for (DrawLotteryActivityBO activityBO : activityBOS) {
            DrawLotteryActivityNoticeDTO noticeDTO = new DrawLotteryActivityNoticeDTO();
            noticeDTO.setId(activityBO.getId());
            noticeDTO.setName(activityBO.getName());
            noticeDTO.setImgPath(activityBO.getImgPath());
            noticeDTO.setGradeEnum(MemberGradeEnum.getEnum(activityBO.getGrade()));
            noticeDTO.setGrade(activityBO.getGrade());
            noticeDTO.setGradeDes(MemberGradeEnum.getEnum(activityBO.getGrade()).getName());
            noticeDTO.setStatusEnum(DrawLotteryActivityStatusEnum.getEnum(activityBO.getStatus()));
            noticeDTO.setStatusDes(DrawLotteryActivityStatusEnum.getEnum(activityBO.getStatus()).getName());
            noticeDTO.setRemark(activityBO.getRemark());
            noticeDTO.setBeginTime(activityBO.getBeginTime());
            noticeDTO.setEndTime(activityBO.getEndTime());
            List<LotteryInfoDTO> lotteryInfoDTOS = new ArrayList<>();
            for (LotteryInfoBO lotteryInfoBO : activityBO.getLotteryInfoBOS()) {
                LotteryInfoDTO lotteryInfoDTO = new LotteryInfoDTO();
                lotteryInfoDTO.setAccount(StringUtil.hideUserAccount(lotteryInfoBO.getAccount()));
                lotteryInfoDTO.setPrizeName(lotteryInfoBO.getPrizeName());
                lotteryInfoDTOS.add(lotteryInfoDTO);
            }
            noticeDTO.setLotteryInfoDTOS(lotteryInfoDTOS);
            noticeDTOS.add(noticeDTO);
        }
        return noticeDTOS;
    }

}
