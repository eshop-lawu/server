package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyQueryDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyRetrieveDTO;
import com.lawu.eshop.mall.srv.bo.ExpressCompanyBO;
import com.lawu.eshop.mall.srv.domain.ExpressCompanyDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class ExpressCompanyConverterTest {

    @Test
    public void converter() {
        ExpressCompanyDO expressCompanyDO = new ExpressCompanyDO();
        expressCompanyDO.setCode("code");
        expressCompanyDO.setHomepage("page");
        expressCompanyDO.setName("name");
        expressCompanyDO.setOrdinal(2);
        expressCompanyDO.setTel("123");
        expressCompanyDO.setId(1);
        ExpressCompanyBO expressCompanyBO = ExpressCompanyConverter.convert(expressCompanyDO);
        Assert.assertEquals(expressCompanyDO.getCode(), expressCompanyBO.getCode());
        Assert.assertEquals(expressCompanyDO.getName(), expressCompanyBO.getName());
    }

    @Test
    public void convertBOS() {
        List<ExpressCompanyDO> dos = new ArrayList<>();
        ExpressCompanyDO expressCompanyDO = new ExpressCompanyDO();
        expressCompanyDO.setCode("code");
        expressCompanyDO.setHomepage("page");
        expressCompanyDO.setName("name");
        expressCompanyDO.setOrdinal(2);
        expressCompanyDO.setTel("123");
        expressCompanyDO.setId(1);
        dos.add(expressCompanyDO);
        List<ExpressCompanyBO> list = ExpressCompanyConverter.convertBOS(dos);
        Assert.assertEquals(dos.get(0).getCode(), list.get(0).getCode());
        Assert.assertEquals(dos.get(0).getName(), list.get(0).getName());
    }

    @Test
    public void convertDTO() {
        ExpressCompanyBO expressCompanyBO = new ExpressCompanyBO();
        expressCompanyBO.setCode("code");
        expressCompanyBO.setHomepage("page");
        expressCompanyBO.setName("name");
        expressCompanyBO.setOrdinal(2);
        expressCompanyBO.setTel("123");
        expressCompanyBO.setId(1);
        ExpressCompanyDTO expressCompanyDTO = ExpressCompanyConverter.convert(expressCompanyBO);
        Assert.assertEquals(expressCompanyBO.getCode(), expressCompanyDTO.getCode());
        Assert.assertEquals(expressCompanyBO.getName(), expressCompanyDTO.getName());
    }

    @Test
    public void convertDTOS() {
        List<ExpressCompanyBO> bos = new ArrayList<>();
        ExpressCompanyBO expressCompanyBO = new ExpressCompanyBO();
        expressCompanyBO.setCode("code");
        expressCompanyBO.setHomepage("page");
        expressCompanyBO.setName("name");
        expressCompanyBO.setOrdinal(2);
        expressCompanyBO.setTel("123");
        expressCompanyBO.setId(1);
        bos.add(expressCompanyBO);
        List<ExpressCompanyDTO> list = ExpressCompanyConverter.convertDTOS(bos);
        Assert.assertEquals(bos.get(0).getCode(), list.get(0).getCode());
        Assert.assertEquals(bos.get(0).getName(), list.get(0).getName());
    }

    @Test
    public void convertExpressCompanyRetrieveDTO() {
        List<ExpressCompanyBO> expressCompanyBOList = new ArrayList<>();
        ExpressCompanyBO expressCompanyBO = new ExpressCompanyBO();
        expressCompanyBO.setCode("code");
        expressCompanyBO.setHomepage("page");
        expressCompanyBO.setName("name");
        expressCompanyBO.setOrdinal(2);
        expressCompanyBO.setTel("123");
        expressCompanyBO.setId(1);
        expressCompanyBOList.add(expressCompanyBO);
        ExpressCompanyRetrieveDTO companyRetrieveDTO = ExpressCompanyConverter.convertExpressCompanyRetrieveDTO(expressCompanyBOList);
        Assert.assertEquals(expressCompanyBOList.get(0).getCode(), companyRetrieveDTO.getList().get(0).getCode());
        Assert.assertEquals(expressCompanyBOList.get(0).getName(), companyRetrieveDTO.getList().get(0).getName());
    }

    @Test
    public void convertExpressCompanyQueryDTO() {
        List<ExpressCompanyBO> expressCompanyBOList = new ArrayList<>();
        ExpressCompanyBO expressCompanyBO = new ExpressCompanyBO();
        expressCompanyBO.setCode("code");
        expressCompanyBO.setHomepage("page");
        expressCompanyBO.setName("name");
        expressCompanyBO.setOrdinal(2);
        expressCompanyBO.setTel("123");
        expressCompanyBO.setId(1);
        expressCompanyBOList.add(expressCompanyBO);

        ExpressCompanyQueryDTO expressCompanyQueryDTO = ExpressCompanyConverter.convertExpressCompanyQueryDTO(expressCompanyBOList);
        Assert.assertEquals(1, expressCompanyQueryDTO.getList().size());

    }
}
