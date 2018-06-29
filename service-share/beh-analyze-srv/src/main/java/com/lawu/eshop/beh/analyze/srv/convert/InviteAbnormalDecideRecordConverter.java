package com.lawu.eshop.beh.analyze.srv.convert;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.dto.InviteAbnormalDecideRecordDTO;
import com.lawu.eshop.beh.analyze.srv.bo.InviteAbnormalDecideRecordBO;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * InviteAbnormalDecideRecord转换类
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
public class InviteAbnormalDecideRecordConverter {

    private InviteAbnormalDecideRecordConverter() {
        
    }
    
    public static InviteAbnormalDecideRecordBO convert(InviteAbnormalDecideRecordDO source) {
        if (source == null) {
            return null;
        }
        InviteAbnormalDecideRecordBO target = new InviteAbnormalDecideRecordBO();
        target.setId(source.getId());
        target.setUserNum(source.getUserNum());
        target.setAccount(source.getAccount());
        target.setIsEarlyHf(source.getIsEarlyHf());
        target.setIsIpHf(source.getIsIpHf());
        target.setIsLongHf(source.getIsLongHf());
        target.setIsManyLongHf(source.getIsManyLongHf());
        target.setIsManyShortHf(source.getIsManyShortHf());
        target.setIsOneDayHf(source.getIsOneDayHf());
        target.setProcessType(ProcessEnum.getEnum(source.getProcessType()));
        target.setIsShortHf(source.getIsShortHf());
        target.setType(UserTypeEnum.getEnum(source.getType()));
        target.setIsAbnormal(source.getIsAbnormal());
        target.setIsNoticed(source.getIsNoticed());
        return target;
    }
    
    public static List<InviteAbnormalDecideRecordBO> convertInviteAbnormalDecideRecordBOList(List<InviteAbnormalDecideRecordDO> source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        List<InviteAbnormalDecideRecordBO> rtn = new ArrayList<>();
        for (InviteAbnormalDecideRecordDO item : source) {
            rtn.add(convert(item));
        }
        return rtn;
    }
    
    public static InviteAbnormalDecideRecordDTO convert(InviteAbnormalDecideRecordBO source) {
        if (source == null) {
            return null;
        }
        InviteAbnormalDecideRecordDTO target = new InviteAbnormalDecideRecordDTO();
        target.setId(source.getId());
        target.setUserNum(source.getUserNum());
        target.setAccount(source.getAccount());
        target.setIsEarlyHf(source.getIsEarlyHf());
        target.setIsIpHf(source.getIsIpHf());
        target.setIsLongHf(source.getIsLongHf());
        target.setIsManyLongHf(source.getIsManyLongHf());
        target.setIsManyShortHf(source.getIsManyShortHf());
        target.setIsOneDayHf(source.getIsOneDayHf());
        target.setProcessType(source.getProcessType());
        target.setIsShortHf(source.getIsShortHf());
        target.setType(source.getType());
        target.setIsAbnormal(source.getIsAbnormal());
        target.setIsNoticed(source.getIsNoticed());
        return target;
    }
    
    public static List<InviteAbnormalDecideRecordDTO> convertInviteAbnormalDecideRecordDTOList(List<InviteAbnormalDecideRecordBO> source) {
        if (source == null) {
            return null;
        }
        List<InviteAbnormalDecideRecordDTO> rtn = new ArrayList<>();
        for (InviteAbnormalDecideRecordBO item : source) {
            rtn.add(convert(item));
        }
        return rtn;
    }
}
