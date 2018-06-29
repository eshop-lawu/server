package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.property.dto.FansInviteDetailDTO;
import com.lawu.eshop.property.srv.bo.FansInviteDetailBO;
import com.lawu.eshop.property.srv.domain.FansInviteDetailDO;

/**
 * @author yangqh
 * @date 2017/7/18
 */
public class FansInviteDetailConverterTest {

    @Test
    public void convertBO(){
        FansInviteDetailDO fansInviteDetailDO = new FansInviteDetailDO();
        fansInviteDetailDO.setRegionName("fdfd");
        fansInviteDetailDO.setConsumePoint(new BigDecimal("1"));
        fansInviteDetailDO.setGmtCreate(new Date());
        fansInviteDetailDO.setAge("1");
        fansInviteDetailDO.setInviteFansCount(1);
        fansInviteDetailDO.setSex(new Byte("1"));
        FansInviteDetailBO bo = FansInviteDetailConverter.convertBO(fansInviteDetailDO);
        Assert.assertNotNull(bo);
    }

    @Test
    public void convertDTO(){
        FansInviteDetailBO fansInviteDetailBO = new FansInviteDetailBO();
        fansInviteDetailBO.setRegionName("fdfd");
        fansInviteDetailBO.setConsumePoint(new BigDecimal("1"));
        fansInviteDetailBO.setGmtCreate(new Date());
        fansInviteDetailBO.setAge("1");
        fansInviteDetailBO.setInviteFansCount(1);
        fansInviteDetailBO.setSex(new Byte("1"));
        FansInviteDetailDTO dto = FansInviteDetailConverter.convertDTO(fansInviteDetailBO);
        Assert.assertNotNull(dto);
    }

    @Test
    public void convertBO1(){
        List<FansInviteDetailDO> fansInviteDetailDOList = new ArrayList<>();
        FansInviteDetailDO fansInviteDetailDO = new FansInviteDetailDO();
        fansInviteDetailDO.setRegionName("fdfd");
        fansInviteDetailDO.setConsumePoint(new BigDecimal("1"));
        fansInviteDetailDO.setGmtCreate(new Date());
        fansInviteDetailDO.setAge("1");
        fansInviteDetailDO.setInviteFansCount(1);
        fansInviteDetailDO.setSex(new Byte("1"));
        fansInviteDetailDOList.add(fansInviteDetailDO);
        List<FansInviteDetailBO> rtnList = FansInviteDetailConverter.convertBO(fansInviteDetailDOList);
        Assert.assertNotNull(rtnList);
    }

    @Test
    public void convertDTO1(){
        List<FansInviteDetailBO> fansInviteDetailBOList = new ArrayList<>();
        FansInviteDetailBO fansInviteDetailBO = new FansInviteDetailBO();
        fansInviteDetailBO.setRegionName("fdfd");
        fansInviteDetailBO.setConsumePoint(new BigDecimal("1"));
        fansInviteDetailBO.setGmtCreate(new Date());
        fansInviteDetailBO.setAge("1");
        fansInviteDetailBO.setInviteFansCount(1);
        fansInviteDetailBO.setSex(new Byte("1"));
        fansInviteDetailBOList.add(fansInviteDetailBO);
        List<FansInviteDetailDTO> rtnList = FansInviteDetailConverter.convertDTO(fansInviteDetailBOList);
        Assert.assertNotNull(rtnList);
    }
}
