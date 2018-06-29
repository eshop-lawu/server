package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.activity.dto.DrawLotteryActivityDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDetailDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityNoticeDTO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityDO;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityConverterTest {

    @Test
    public void convertBO() {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setId(100L);
        activityDO.setName("test");
        activityDO.setImgPath("png");
        activityDO.setGrade((byte) 1);
        activityDO.setStatus((byte) 1);
        activityDO.setRemark("test");
        activityDO.setBeginTime(new Date());
        activityDO.setEndTime(new Date());
        activityDO.setGmtModified(new Date());
        activityDO.setGmtCreate(new Date());
        DrawLotteryActivityBO activityBO = DrawLotteryActivityConverter.convertBO(activityDO);
        Assert.assertNotNull(activityBO);
        Assert.assertEquals(activityDO.getId(), activityBO.getId());
    }

    @Test
    public void convertDTO() {
        DrawLotteryActivityBO activityBO = new DrawLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setName("test");
        activityBO.setImgPath("png");
        activityBO.setGrade((byte) 1);
        activityBO.setStatus((byte) 1);
        activityBO.setRemark("test");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        DrawLotteryActivityDTO activityDTO = DrawLotteryActivityConverter.convertDTO(activityBO);
        Assert.assertNotNull(activityDTO);
        Assert.assertEquals(activityBO.getId(), activityDTO.getId());
    }

    @Test
    public void convertBOS() {
        List<DrawLotteryActivityDO> activityDOS = new ArrayList<>();
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setId(100L);
        activityDO.setName("test");
        activityDO.setImgPath("png");
        activityDO.setGrade((byte) 1);
        activityDO.setStatus((byte) 1);
        activityDO.setRemark("test");
        activityDO.setBeginTime(new Date());
        activityDO.setEndTime(new Date());
        activityDO.setGmtModified(new Date());
        activityDO.setGmtCreate(new Date());
        activityDOS.add(activityDO);
        List<DrawLotteryActivityBO> activityBOS = DrawLotteryActivityConverter.convertBOS(activityDOS);
        Assert.assertNotNull(activityBOS);
        Assert.assertEquals(activityDO.getId(), activityBOS.get(0).getId());
    }

    @Test
    public void convertDTOS() {
        List<DrawLotteryActivityBO> activityBOS = new ArrayList<>();
        DrawLotteryActivityBO activityBO = new DrawLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setName("test");
        activityBO.setImgPath("png");
        activityBO.setGrade((byte) 1);
        activityBO.setStatus((byte) 1);
        activityBO.setRemark("test");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        activityBOS.add(activityBO);
        List<DrawLotteryActivityDTO> activityDTOS = DrawLotteryActivityConverter.convertDTOS(activityBOS);
        Assert.assertNotNull(activityDTOS);
        Assert.assertEquals(activityBO.getId(), activityDTOS.get(0).getId());
    }

    @Test
    public void convertDetailDTO() {
        DrawLotteryActivityBO activityBO = new DrawLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setName("test");
        activityBO.setImgPath("png");
        activityBO.setGrade((byte) 1);
        activityBO.setStatus((byte) 1);
        activityBO.setRemark("test");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        activityBO.setPrizeBOS(new ArrayList<>());
        DrawLotteryActivityDetailDTO detailDTO = DrawLotteryActivityConverter.convertDetailDTO(activityBO);
        Assert.assertNotNull(detailDTO);
        Assert.assertEquals(activityBO.getId(), detailDTO.getId());
    }

    @Test
    public void convertNoticeDTOS() {
        List<DrawLotteryActivityBO> activityBOS = new ArrayList<>();
        DrawLotteryActivityBO activityBO = new DrawLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setName("test");
        activityBO.setImgPath("png");
        activityBO.setGrade((byte) 1);
        activityBO.setStatus((byte) 1);
        activityBO.setRemark("test");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        activityBO.setLotteryInfoBOS(new ArrayList<>());
        activityBOS.add(activityBO);
        List<DrawLotteryActivityNoticeDTO> noticeDTOS = DrawLotteryActivityConverter.convertNoticeDTOS(activityBOS);
        Assert.assertNotNull(noticeDTOS);
        Assert.assertEquals(activityBO.getId(), noticeDTOS.get(0).getId());
    }

}
