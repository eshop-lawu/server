package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.dto.LotteryRecordDTO;
import com.lawu.eshop.mall.dto.LotteryRecordOperatorDTO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordBO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordOperatorBO;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDO;
import com.lawu.eshop.mall.srv.domain.LotteryRecordDO;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
public class LotteryRecordConverter {

    /**
     * BO转换
     *
     * @param activityDO
     * @return
     */
    public static LotteryRecordBO converBO(LotteryActivityDO activityDO) {
        if (activityDO == null) {
            return null;
        }

        LotteryRecordBO recordBO = new LotteryRecordBO();
        recordBO.setPrizeName(activityDO.getPrizeName());
        recordBO.setPrizePrice(activityDO.getPrizePrice());
        recordBO.setImagePath(activityDO.getImagePath());
        recordBO.setEndTime(activityDO.getEndTime());
        return recordBO;
    }

    /**
     * DTO转换
     *
     * @param recordBO
     * @return
     */
    public static LotteryRecordDTO converDTO(LotteryRecordBO recordBO) {
        if (recordBO == null) {
            return null;
        }

        LotteryRecordDTO recordDTO = new LotteryRecordDTO();
        recordDTO.setPrizeName(recordBO.getPrizeName());
        recordDTO.setPrizePrice(recordBO.getPrizePrice());
        recordDTO.setImagePath(recordBO.getImagePath());
        recordDTO.setEndTime(recordBO.getEndTime());
        recordDTO.setLotteryNumber(recordBO.getLotteryNumber());
        recordDTO.setLotteryAccountList(recordBO.getLotteryAccountList());
        return recordDTO;
    }

    /**
     * DTO转换
     *
     * @param recordBOS
     * @return
     */
    public static List<LotteryRecordDTO> converDTOS(List<LotteryRecordBO> recordBOS) {
        List<LotteryRecordDTO> recordDTOS = new ArrayList<>();
        if (recordBOS == null || recordBOS.isEmpty()) {
            return recordDTOS;
        }

        for (LotteryRecordBO recordBO : recordBOS) {
            recordDTOS.add(converDTO(recordBO));
        }
        return recordDTOS;
    }

    /**
     * BO转换
     *
     * @param recordDOS
     * @return
     */
    public static List<LotteryRecordOperatorBO> converOperatorBOS(List<LotteryRecordDO> recordDOS) {
        List<LotteryRecordOperatorBO> operatorBOS = new ArrayList<>();
        if (recordDOS == null || recordDOS.isEmpty()) {
            return operatorBOS;
        }

        for (LotteryRecordDO recordDO : recordDOS) {
            LotteryRecordOperatorBO operatorBO = new LotteryRecordOperatorBO();
            operatorBO.setId(recordDO.getId());
            operatorBO.setAccount(recordDO.getAccount());
            operatorBO.setPrizeName(recordDO.getPrizeName());
            operatorBO.setLotteryCount(recordDO.getLotteryCount());
            operatorBO.setLotteryResult(recordDO.getLotteryResult());
            operatorBOS.add(operatorBO);
        }
        return operatorBOS;
    }

    /**
     * DTO转换
     *
     * @param operatorBOS
     * @return
     */
    public static List<LotteryRecordOperatorDTO> converOperatorDTOS(List<LotteryRecordOperatorBO> operatorBOS) {
        List<LotteryRecordOperatorDTO> operatorDTOS = new ArrayList<>();
        if (operatorBOS == null || operatorBOS.isEmpty()) {
            return operatorDTOS;
        }

        for (LotteryRecordOperatorBO operatorBO : operatorBOS) {
            LotteryRecordOperatorDTO operatorDTO = new LotteryRecordOperatorDTO();
            operatorDTO.setId(operatorBO.getId());
            operatorDTO.setAccount(operatorBO.getAccount());
            operatorDTO.setPrizeName(operatorBO.getPrizeName());
            operatorDTO.setLotteryCount(operatorBO.getLotteryCount());
            operatorDTO.setLotteryResult(operatorBO.getLotteryResult());
            operatorDTOS.add(operatorDTO);
        }
        return operatorDTOS;
    }

}
