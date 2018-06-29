package com.lawu.eshop.beh.analyze.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.dto.AbnormalDTO;
import com.lawu.eshop.beh.analyze.srv.bo.AbnormalBO;
import com.lawu.eshop.beh.analyze.srv.convert.AbnormalConverter;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author zhangyong
 * @date 2018/1/30.
 */
public class AbnormalConverterTest {

    @Test
    public void convertBOS() {
        List<InviteAbnormalDecideRecordDO> list = new ArrayList<>();
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setId(1L);
        recordDO.setUserNum("M123456");
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
        recordDO.setGmtCreate(new Date());
        list.add(recordDO);
        List<AbnormalBO> abnormalBOS = AbnormalConverter.convertBOS(list);
        Assert.assertEquals(recordDO.getAccount(), abnormalBOS.get(0).getAccount());
        Assert.assertEquals(recordDO.getUserNum(), abnormalBOS.get(0).getUserNum());
        Assert.assertEquals(recordDO.getIsEarlyHf(), abnormalBOS.get(0).getEarlyHf());
        Assert.assertEquals(recordDO.getIsOneDayHf(), abnormalBOS.get(0).getOneDayHf());
        Assert.assertEquals(recordDO.getIsLongHf(), abnormalBOS.get(0).getLongHf());
        Assert.assertEquals(recordDO.getIsManyShortHf(), abnormalBOS.get(0).getManyShortHf());
        Assert.assertEquals(recordDO.getIsManyLongHf(), abnormalBOS.get(0).getManyLongHf());
        Assert.assertEquals(recordDO.getIsIpHf(), abnormalBOS.get(0).getIpHf());
        Assert.assertEquals(recordDO.getIsShortHf(), abnormalBOS.get(0).getShortHf());
        Assert.assertEquals(recordDO.getType(), abnormalBOS.get(0).getType().getValue());
        Assert.assertEquals(recordDO.getId(), abnormalBOS.get(0).getId());
    }

    @Test
    public void convertDTOS() {
        List<AbnormalBO> list = new ArrayList<>();
        AbnormalBO abnormalBO = new AbnormalBO();
        abnormalBO.setId(1L);
        abnormalBO.setUserNum("M123456");
        abnormalBO.setAccount("131111111");
        abnormalBO.setEarlyHf(true);
        abnormalBO.setIpHf(true);
        abnormalBO.setLongHf(true);
        abnormalBO.setManyLongHf(true);
        abnormalBO.setManyShortHf(true);
        abnormalBO.setOneDayHf(true);
        abnormalBO.setProcess(ProcessEnum.NORMAL);
        abnormalBO.setShortHf(true);
        abnormalBO.setType(UserTypeEnum.MEMBER);
        abnormalBO.setGmtCreate(new Date());
        list.add(abnormalBO);
        List<AbnormalDTO> abnormalDTOS = AbnormalConverter.convertDTOS(list);
        Assert.assertEquals(abnormalBO.getAccount(), abnormalDTOS.get(0).getAccount());
        Assert.assertEquals(abnormalBO.getUserNum(), abnormalDTOS.get(0).getUserNum());
        Assert.assertEquals(abnormalBO.getEarlyHf(), abnormalDTOS.get(0).getEarlyHf());
        Assert.assertEquals(abnormalBO.getOneDayHf(), abnormalDTOS.get(0).getOneDayHf());
        Assert.assertEquals(abnormalBO.getLongHf(), abnormalDTOS.get(0).getLongHf());
        Assert.assertEquals(abnormalBO.getManyShortHf(), abnormalDTOS.get(0).getManyShortHf());
        Assert.assertEquals(abnormalBO.getManyLongHf(), abnormalDTOS.get(0).getManyLongHf());
        Assert.assertEquals(abnormalBO.getIpHf(), abnormalDTOS.get(0).getIpHf());
        Assert.assertEquals(abnormalBO.getShortHf(), abnormalDTOS.get(0).getShortHf());
        Assert.assertEquals(abnormalBO.getType().getValue(), abnormalDTOS.get(0).getType().getValue());
        Assert.assertEquals(abnormalBO.getId(), abnormalDTOS.get(0).getId());
    }
}
