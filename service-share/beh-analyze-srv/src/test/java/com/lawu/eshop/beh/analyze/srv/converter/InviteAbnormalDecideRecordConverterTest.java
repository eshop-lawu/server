package com.lawu.eshop.beh.analyze.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.dto.InviteAbnormalDecideRecordDTO;
import com.lawu.eshop.beh.analyze.srv.bo.InviteAbnormalDecideRecordBO;
import com.lawu.eshop.beh.analyze.srv.convert.InviteAbnormalDecideRecordConverter;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author zhangyong
 * @date 2018/1/30.
 */
public class InviteAbnormalDecideRecordConverterTest {

    @Test
    public void convert() {
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setId(1L);
        recordDO.setUserNum("M132456");
        recordDO.setAccount("131111111");
        recordDO.setIsEarlyHf(true);
        recordDO.setIsIpHf(true);
        recordDO.setIsLongHf(true);
        recordDO.setIsManyLongHf(true);
        recordDO.setIsManyShortHf(true);
        recordDO.setIsOneDayHf(true);
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setIsShortHf(true);
        recordDO.setType(UserTypeEnum.MEMBER.getValue());
        recordDO.setIsAbnormal(true);
        recordDO.setIsNoticed(false);
        InviteAbnormalDecideRecordBO recordBO = InviteAbnormalDecideRecordConverter.convert(recordDO);
        Assert.assertEquals(recordDO.getId(), recordBO.getId());
        Assert.assertEquals(recordDO.getUserNum(), recordBO.getUserNum());
        Assert.assertEquals(recordDO.getAccount(), recordBO.getAccount());
        Assert.assertEquals(recordDO.getIsEarlyHf(), recordBO.getIsEarlyHf());
        Assert.assertEquals(recordDO.getIsIpHf(), recordBO.getIsIpHf());
        Assert.assertEquals(recordDO.getIsLongHf(), recordBO.getIsLongHf());
        Assert.assertEquals(recordDO.getIsManyLongHf(), recordBO.getIsManyLongHf());
        Assert.assertEquals(recordDO.getIsManyShortHf(), recordBO.getIsManyShortHf());
        Assert.assertEquals(recordDO.getIsOneDayHf(), recordBO.getIsOneDayHf());
        Assert.assertEquals(recordDO.getProcessType(), recordBO.getProcessType().getVal());
        Assert.assertEquals(recordDO.getIsShortHf(), recordBO.getIsShortHf());
        Assert.assertEquals(recordDO.getType(), recordBO.getType().getValue());
        Assert.assertEquals(recordDO.getIsAbnormal(), recordBO.getIsAbnormal());
        Assert.assertEquals(recordDO.getIsNoticed(), recordBO.getIsNoticed());

    }

    @Test
    public void convertInviteAbnormalDecideRecordBOList() {
        List<InviteAbnormalDecideRecordDO> recordDOS = new ArrayList<>();
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setId(1L);
        recordDO.setUserNum("M132456");
        recordDO.setAccount("131111111");
        recordDO.setIsEarlyHf(true);
        recordDO.setIsIpHf(true);
        recordDO.setIsLongHf(true);
        recordDO.setIsManyLongHf(true);
        recordDO.setIsManyShortHf(true);
        recordDO.setIsOneDayHf(true);
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setIsShortHf(true);
        recordDO.setType(UserTypeEnum.MEMBER.getValue());
        recordDO.setIsAbnormal(true);
        recordDO.setIsNoticed(false);
        recordDOS.add(recordDO);
        List<InviteAbnormalDecideRecordBO> recordBOS = InviteAbnormalDecideRecordConverter.convertInviteAbnormalDecideRecordBOList(recordDOS);
        Assert.assertEquals(recordDO.getId(), recordBOS.get(0).getId());
        Assert.assertEquals(recordDO.getUserNum(), recordBOS.get(0).getUserNum());
        Assert.assertEquals(recordDO.getAccount(), recordBOS.get(0).getAccount());
        Assert.assertEquals(recordDO.getIsEarlyHf(), recordBOS.get(0).getIsEarlyHf());
        Assert.assertEquals(recordDO.getIsIpHf(), recordBOS.get(0).getIsIpHf());
        Assert.assertEquals(recordDO.getIsLongHf(), recordBOS.get(0).getIsLongHf());
        Assert.assertEquals(recordDO.getIsManyLongHf(), recordBOS.get(0).getIsManyLongHf());
        Assert.assertEquals(recordDO.getIsManyShortHf(), recordBOS.get(0).getIsManyShortHf());
        Assert.assertEquals(recordDO.getIsOneDayHf(), recordBOS.get(0).getIsOneDayHf());
        Assert.assertEquals(recordDO.getProcessType(), recordBOS.get(0).getProcessType().getVal());
        Assert.assertEquals(recordDO.getIsShortHf(), recordBOS.get(0).getIsShortHf());
        Assert.assertEquals(recordDO.getType(), recordBOS.get(0).getType().getValue());
        Assert.assertEquals(recordDO.getIsAbnormal(), recordBOS.get(0).getIsAbnormal());
        Assert.assertEquals(recordDO.getIsNoticed(), recordBOS.get(0).getIsNoticed());
    }

    @Test
    public void convertDTO() {
        InviteAbnormalDecideRecordBO recordBO = new InviteAbnormalDecideRecordBO();
        recordBO.setId(1L);
        recordBO.setUserNum("M132456");
        recordBO.setAccount("131111111");
        recordBO.setIsEarlyHf(true);
        recordBO.setIsIpHf(true);
        recordBO.setIsLongHf(true);
        recordBO.setIsManyLongHf(true);
        recordBO.setIsManyShortHf(true);
        recordBO.setIsOneDayHf(true);
        recordBO.setProcessType(ProcessEnum.NORMAL);
        recordBO.setIsShortHf(true);
        recordBO.setType(UserTypeEnum.MEMBER);
        recordBO.setIsAbnormal(true);
        recordBO.setIsNoticed(false);
        InviteAbnormalDecideRecordDTO recordDTO = InviteAbnormalDecideRecordConverter.convert(recordBO);
        Assert.assertEquals(recordDTO.getId(), recordBO.getId());
        Assert.assertEquals(recordDTO.getUserNum(), recordBO.getUserNum());
        Assert.assertEquals(recordDTO.getAccount(), recordBO.getAccount());
        Assert.assertEquals(recordDTO.getIsEarlyHf(), recordBO.getIsEarlyHf());
        Assert.assertEquals(recordDTO.getIsIpHf(), recordBO.getIsIpHf());
        Assert.assertEquals(recordDTO.getIsLongHf(), recordBO.getIsLongHf());
        Assert.assertEquals(recordDTO.getIsManyLongHf(), recordBO.getIsManyLongHf());
        Assert.assertEquals(recordDTO.getIsManyShortHf(), recordBO.getIsManyShortHf());
        Assert.assertEquals(recordDTO.getIsOneDayHf(), recordBO.getIsOneDayHf());
        Assert.assertEquals(recordDTO.getProcessType().getVal(), recordBO.getProcessType().getVal());
        Assert.assertEquals(recordDTO.getIsShortHf(), recordBO.getIsShortHf());
        Assert.assertEquals(recordDTO.getType().getValue(), recordBO.getType().getValue());
        Assert.assertEquals(recordDTO.getIsAbnormal(), recordBO.getIsAbnormal());
        Assert.assertEquals(recordDTO.getIsNoticed(), recordBO.getIsNoticed());
    }

    @Test
    public void convertInviteAbnormalDecideRecordDTOList() {
        List<InviteAbnormalDecideRecordBO> recordBOS = new ArrayList<>();
        InviteAbnormalDecideRecordBO recordBO = new InviteAbnormalDecideRecordBO();
        recordBO.setId(1L);
        recordBO.setUserNum("M132456");
        recordBO.setAccount("131111111");
        recordBO.setIsEarlyHf(true);
        recordBO.setIsIpHf(true);
        recordBO.setIsLongHf(true);
        recordBO.setIsManyLongHf(true);
        recordBO.setIsManyShortHf(true);
        recordBO.setIsOneDayHf(true);
        recordBO.setProcessType(ProcessEnum.NORMAL);
        recordBO.setIsShortHf(true);
        recordBO.setType(UserTypeEnum.MEMBER);
        recordBO.setIsAbnormal(true);
        recordBO.setIsNoticed(false);
        recordBOS.add(recordBO);
        List<InviteAbnormalDecideRecordDTO> recordDTOS = InviteAbnormalDecideRecordConverter.convertInviteAbnormalDecideRecordDTOList(recordBOS);
        Assert.assertEquals(recordBO.getId(), recordDTOS.get(0).getId());
        Assert.assertEquals(recordBO.getUserNum(), recordDTOS.get(0).getUserNum());
        Assert.assertEquals(recordBO.getAccount(), recordDTOS.get(0).getAccount());
        Assert.assertEquals(recordBO.getIsEarlyHf(), recordDTOS.get(0).getIsEarlyHf());
        Assert.assertEquals(recordBO.getIsIpHf(), recordDTOS.get(0).getIsIpHf());
        Assert.assertEquals(recordBO.getIsLongHf(), recordDTOS.get(0).getIsLongHf());
        Assert.assertEquals(recordBO.getIsManyLongHf(), recordDTOS.get(0).getIsManyLongHf());
        Assert.assertEquals(recordBO.getIsManyShortHf(), recordDTOS.get(0).getIsManyShortHf());
        Assert.assertEquals(recordBO.getIsOneDayHf(), recordDTOS.get(0).getIsOneDayHf());
        Assert.assertEquals(recordBO.getProcessType().getVal(), recordDTOS.get(0).getProcessType().getVal());
        Assert.assertEquals(recordBO.getIsShortHf(), recordDTOS.get(0).getIsShortHf());
        Assert.assertEquals(recordBO.getType().getValue(), recordDTOS.get(0).getType().getValue());
        Assert.assertEquals(recordBO.getIsAbnormal(), recordDTOS.get(0).getIsAbnormal());
        Assert.assertEquals(recordBO.getIsNoticed(), recordDTOS.get(0).getIsNoticed());

    }


}
