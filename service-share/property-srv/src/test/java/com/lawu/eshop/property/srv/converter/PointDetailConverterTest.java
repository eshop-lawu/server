package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.eshop.property.dto.PointDetailBackageDTO;
import com.lawu.eshop.property.dto.PointDetailDTO;
import com.lawu.eshop.property.srv.bo.PointDetailBO;
import com.lawu.eshop.property.srv.domain.PointDetailDO;
import com.lawu.framework.core.page.Page;

/**
 * @author yangqh
 * @date 2017/7/18
 */
public class PointDetailConverterTest {

    @Test
    public void convert(){
        PointDetailDO pointDetailDO = new PointDetailDO();
        pointDetailDO.setGmtCreate(new Date());
        pointDetailDO.setPoint(new BigDecimal("1"));
        pointDetailDO.setPointNum("@@221");
        pointDetailDO.setPointType(new Byte("1"));
        pointDetailDO.setRemark("");
        pointDetailDO.setTitle("1");
        pointDetailDO.setUserNum("M10001");
        pointDetailDO.setBizId("1");
        pointDetailDO.setId(1L);
        PointDetailBO bo = PointDetailConverter.convert(pointDetailDO);
        Assert.assertNotNull(bo);
    }

    @Test
    public void convertBOS(){
        List<PointDetailDO> pointDetailDOS = new ArrayList<>();
        PointDetailDO pointDetailDO = new PointDetailDO();
        pointDetailDO.setGmtCreate(new Date());
        pointDetailDO.setPoint(new BigDecimal("1"));
        pointDetailDO.setPointNum("@@221");
        pointDetailDO.setPointType(new Byte("1"));
        pointDetailDO.setRemark("");
        pointDetailDO.setTitle("1");
        pointDetailDO.setUserNum("M10001");
        pointDetailDO.setBizId("1");
        pointDetailDO.setId(1L);
        pointDetailDOS.add(pointDetailDO);
        List<PointDetailBO> rtnList = PointDetailConverter.convertBOS(pointDetailDOS);
        Assert.assertNotNull(rtnList);
    }

    @Test
    public void convert1(){
        PointDetailBO pointDetailBO = new PointDetailBO();
        pointDetailBO.setDirection(ConsumptionTypeEnum.INCOME);
        pointDetailBO.setPoint(new BigDecimal("1"));
        pointDetailBO.setRemark("");
        pointDetailBO.setTitle("");
        pointDetailBO.setGmtCreate(new Date());
        PointDetailDTO dto = PointDetailConverter.convert(pointDetailBO);
        Assert.assertNotNull(dto);
    }

    @Test
    public void convertDTOS(){
        List<PointDetailBO> pointDetailBOS = new ArrayList<>();
        PointDetailBO pointDetailBO = new PointDetailBO();
        pointDetailBO.setDirection(ConsumptionTypeEnum.INCOME);
        pointDetailBO.setPoint(new BigDecimal("1"));
        pointDetailBO.setRemark("");
        pointDetailBO.setTitle("");
        pointDetailBO.setGmtCreate(new Date());
        pointDetailBOS.add(pointDetailBO);
        List<PointDetailDTO> rtnList = PointDetailConverter.convertDTOS(pointDetailBOS);
        Assert.assertNotNull(rtnList);
    }

    @Test
    public void convertDTOPage(){
        Page<PointDetailBO> pointDetailBOPage = new Page<>();
        pointDetailBOPage.setCurrentPage(1);
        pointDetailBOPage.setTotalCount(1);
        pointDetailBOPage.setRecords(new ArrayList<>());
        Page<PointDetailDTO> rtnPage = PointDetailConverter.convertDTOPage(pointDetailBOPage);
        Assert.assertNotNull(rtnPage);
    }

    @Test
    public void convertBackageDTOPage(){
        Page<PointDetailBO> pointDetailBOPage = new Page<>();
        pointDetailBOPage.setCurrentPage(1);
        pointDetailBOPage.setTotalCount(1);
        List<PointDetailBO> record =new ArrayList<>();
        PointDetailBO pointDetailBO = new PointDetailBO();
        pointDetailBO.setDirection(ConsumptionTypeEnum.INCOME);
        pointDetailBO.setPoint(new BigDecimal("1"));
        pointDetailBO.setRemark("");
        pointDetailBO.setTitle("");
        pointDetailBO.setGmtCreate(new Date());
        record.add(pointDetailBO);
        pointDetailBOPage.setRecords(record);
        Page<PointDetailBackageDTO> rtnPage = PointDetailConverter.convertBackageDTOPage(pointDetailBOPage);
        Assert.assertNotNull(rtnPage);
    }
}
