package com.lawu.eshop.beh.analyze.srv.convert;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.dto.AbnormalDTO;
import com.lawu.eshop.beh.analyze.srv.bo.AbnormalBO;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
public class AbnormalConverter {
    public static List<AbnormalBO> convertBOS(List<InviteAbnormalDecideRecordDO> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<AbnormalBO> bos = new ArrayList<>();
        AbnormalBO abnormalBO;
        for (InviteAbnormalDecideRecordDO recordDO : list) {
            abnormalBO = new AbnormalBO();
            abnormalBO.setId(recordDO.getId());
            abnormalBO.setUserNum(recordDO.getUserNum());
            abnormalBO.setAccount(recordDO.getAccount());
            abnormalBO.setEarlyHf(recordDO.getIsEarlyHf());
            abnormalBO.setIpHf(recordDO.getIsIpHf());
            abnormalBO.setLongHf(recordDO.getIsLongHf());
            abnormalBO.setManyLongHf(recordDO.getIsManyLongHf());
            abnormalBO.setManyShortHf(recordDO.getIsManyShortHf());
            abnormalBO.setOneDayHf(recordDO.getIsOneDayHf());
            abnormalBO.setProcess(ProcessEnum.getEnum(recordDO.getProcessType()));
            abnormalBO.setShortHf(recordDO.getIsShortHf());
            abnormalBO.setType(UserTypeEnum.getEnum(recordDO.getType()));
            abnormalBO.setGmtCreate(recordDO.getGmtCreate());
            abnormalBO.setIsActivityAbnormal(recordDO.getIsActivityAbnormal());
            bos.add(abnormalBO);
        }
        return bos;
    }

    public static List<AbnormalDTO> convertDTOS(List<AbnormalBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<AbnormalDTO> abnormalDTOS = new ArrayList<>();
        AbnormalDTO abnormalDTO;
        for (AbnormalBO abnormalBO : records) {
            abnormalDTO = new AbnormalDTO();
            abnormalDTO.setId(abnormalBO.getId());
            abnormalDTO.setUserNum(abnormalBO.getUserNum());
            abnormalDTO.setAccount(abnormalBO.getAccount());
            abnormalDTO.setEarlyHf(abnormalBO.getEarlyHf());
            abnormalDTO.setIpHf(abnormalBO.getIpHf());
            abnormalDTO.setLongHf(abnormalBO.getLongHf());
            abnormalDTO.setManyLongHf(abnormalBO.getManyLongHf());
            abnormalDTO.setManyShortHf(abnormalBO.getManyShortHf());
            abnormalDTO.setOneDayHf(abnormalBO.getOneDayHf());
            abnormalDTO.setProcess(abnormalBO.getProcess());
            abnormalDTO.setShortHf(abnormalBO.getShortHf());
            abnormalDTO.setType(abnormalBO.getType());
            abnormalDTO.setGmtCreate(abnormalBO.getGmtCreate());
            abnormalDTO.setIsActivityAbnormal(abnormalBO.getIsActivityAbnormal());
            abnormalDTOS.add(abnormalDTO);
        }
        return abnormalDTOS;
    }
}
