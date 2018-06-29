package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendRecordDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.dto.PointLotteryRollDTO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendRecordBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityLotteryInfoBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.extend.PointLotteryRollView;

/**
 * @author meishuquan
 * @date 2018/2/9.
 */
public class PointLotteryActivityRecordConverterTest {

    @Test
    public void convert() {
        List<PointLotteryActivityRecordDO> source = new ArrayList<>();
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setId(100L);
        recordDO.setUserNum("M001");
        recordDO.setMobile("13666666666");
        recordDO.setLotteryNum(5);
        recordDO.setPointLotteryActivityId(1L);
        recordDO.setPointLotteryActivityOrderId(1L);
        recordDO.setPrizeName("test");
        recordDO.setStatus((byte) 1);
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        source.add(recordDO);
        List<PointLotteryActivityRecordBO> recordBOS = PointLotteryActivityRecordConverter.convert(source);
        Assert.assertNotNull(recordBOS);
        Assert.assertEquals(recordDO.getPrizeName(), recordBOS.get(0).getPrizeName());
    }

    @Test
    public void convertPointLotteryActivityRecordPageDTOList() {
        List<PointLotteryActivityRecordBO> source = new ArrayList<>();
        PointLotteryActivityRecordBO recordBO = new PointLotteryActivityRecordBO();
        recordBO.setId(100L);
        recordBO.setUserNum("M001");
        recordBO.setMobile("13666666666");
        recordBO.setLotteryNum(5);
        recordBO.setPointLotteryActivityId(1L);
        recordBO.setPrizeName("test");
        recordBO.setStatus(PointLotteryActivityRecordStatusEnum.getEnum((byte) 1));
        recordBO.setGmtModified(new Date());
        recordBO.setGmtCreate(new Date());
        source.add(recordBO);
        List<PointLotteryActivityRecordPageDTO> pageDTOS = PointLotteryActivityRecordConverter.convertPointLotteryActivityRecordPageDTOList(source);
        Assert.assertNotNull(pageDTOS);
        Assert.assertEquals(recordBO.getUserNum(), pageDTOS.get(0).getUserNum());
    }

    @Test
    public void convertInfoBO() {
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setId(100L);
        recordDO.setUserNum("M001");
        recordDO.setMobile("13666666666");
        recordDO.setLotteryNum(5);
        recordDO.setPointLotteryActivityId(1L);
        recordDO.setPointLotteryActivityOrderId(1L);
        recordDO.setPrizeName("test");
        recordDO.setStatus((byte) 1);
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        PointLotteryActivityLotteryInfoBO infoBO = PointLotteryActivityRecordConverter.convertInfoBO(recordDO);
        Assert.assertNotNull(infoBO);
        Assert.assertEquals(recordDO.getPrizeName(), infoBO.getPrizeName());
    }

    @Test
    public void convertBOS() {
        List<PointLotteryActivityRecordDO> recordDOS = new ArrayList<>();
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setId(100L);
        recordDO.setUserNum("M001");
        recordDO.setMobile("13666666666");
        recordDO.setLotteryNum(5);
        recordDO.setPointLotteryActivityId(1L);
        recordDO.setPointLotteryActivityOrderId(1L);
        recordDO.setPrizeName("test");
        recordDO.setStatus((byte) 1);
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDOS.add(recordDO);
        List<PointLotteryActivityLotteryInfoBO> infoBOS = PointLotteryActivityRecordConverter.convertBOS(recordDOS);
        Assert.assertNotNull(infoBOS);
        Assert.assertEquals(recordDO.getPrizeName(), infoBOS.get(0).getPrizeName());
    }

    @Test
    public void convertRollDTOS() {
        List<PointLotteryRollView> rollViews = new ArrayList<>();
        PointLotteryRollView rollView = new PointLotteryRollView();
        rollView.setPrizeName("test");
        rollView.setMobile("13666666666");
        rollViews.add(rollView);
        List<PointLotteryRollDTO> rollDTOS = PointLotteryActivityRecordConverter.convertRollDTOS(rollViews);
        Assert.assertNotNull(rollDTOS);
        Assert.assertEquals(rollView.getPrizeName(), rollDTOS.get(0).getPrizeName());
    }

    @Test
    public void convertBO() {
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setId(100L);
        recordDO.setUserNum("M001");
        recordDO.setMobile("13666666666");
        recordDO.setLotteryNum(5);
        recordDO.setPointLotteryActivityId(1L);
        recordDO.setPointLotteryActivityOrderId(1L);
        recordDO.setPrizeName("test");
        recordDO.setStatus((byte) 1);
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        PointLotteryActivityRecordBO recordBO = PointLotteryActivityRecordConverter.convertBO(recordDO);
        Assert.assertNotNull(recordBO);
        Assert.assertEquals(recordDO.getPrizeName(), recordBO.getPrizeName());
    }

    @Test
    public void convertAttendRecordDTOS() {
        List<PointLotteryActivityAttendRecordBO> records = new ArrayList<>();
        PointLotteryActivityAttendRecordBO recordBO = new PointLotteryActivityAttendRecordBO();
        recordBO.setPointLotteryActivityId(100L);
        recordBO.setPrizeName("test");
        recordBO.setAttendCount(10);
        recordBO.setLotteryTime(new Date());
        recordBO.setPrizeImagePath("png");
        recordBO.setStatusEnum(PointLotteryActivityRecordEnum.NO_OPEN_LOTTERY);
        records.add(recordBO);
        List<PointLotteryActivityAttendRecordDTO> recordDTOS = PointLotteryActivityRecordConverter.convertAttendRecordDTOS(records);
        Assert.assertNotNull(recordDTOS);
        Assert.assertEquals(recordBO.getPrizeName(), recordDTOS.get(0).getPrizeName());
    }

    @Test
    public void convertAttendRecordDetail() {
        PointLotteryActivityAttendDetailBO detail = new PointLotteryActivityAttendDetailBO();
        detail.setStatusEnum(PointLotteryActivityRecordEnum.NO_OPEN_LOTTERY);
        detail.setPrizeImagePath("png");
        detail.setLotteryTime(new Date());
        detail.setAttendCount(10);
        detail.setAttendNums("5");
        detail.setLotteryPoint(10);
        detail.setLotteryResultNums("5");
        detail.setPrizeName("test");
        detail.setPrizePrice(BigDecimal.valueOf(10));
        PointLotteryActivityAttendDetailDTO detailDTO = PointLotteryActivityRecordConverter.convertAttendRecordDetail(detail);
        Assert.assertNotNull(detailDTO);
        Assert.assertEquals(detail.getPrizeName(), detailDTO.getPrizeName());
    }

}
