package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.SuggestionClientType;
import com.lawu.eshop.mall.constants.SuggestionUserType;
import com.lawu.eshop.mall.dto.SuggestionDTO;
import com.lawu.eshop.mall.param.SuggestionParam;
import com.lawu.eshop.mall.srv.bo.SuggestionBO;
import com.lawu.eshop.mall.srv.domain.SuggestionDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class SuggestionConverterTest {

    @Test
    public void convert() {
        SuggestionDO suggestionDO = new SuggestionDO();
        suggestionDO.setContent("1234");
        suggestionDO.setGmtCreate(new Date());
        suggestionDO.setGmtModified(new Date());
        suggestionDO.setId(1);
        suggestionDO.setUserNum("1234566");
        suggestionDO.setClientType(SuggestionClientType.ANDROID.value);
        suggestionDO.setUserType(SuggestionUserType.MEMBER.value);

        SuggestionBO suggestionBO = SuggestionConverter.convert(suggestionDO);
        Assert.assertEquals(suggestionDO.getContent(), suggestionBO.getContent());
    }

    @Test
    public void convertBOS() {
        List<SuggestionDO> dos = new ArrayList<>();
        SuggestionDO suggestionDO = new SuggestionDO();
        suggestionDO.setContent("1234");
        suggestionDO.setGmtCreate(new Date());
        suggestionDO.setGmtModified(new Date());
        suggestionDO.setId(1);
        suggestionDO.setUserNum("1234566");
        suggestionDO.setClientType(SuggestionClientType.ANDROID.value);
        suggestionDO.setUserType(SuggestionUserType.MEMBER.value);
        dos.add(suggestionDO);
        List<SuggestionBO> list = SuggestionConverter.convertBOS(dos);
        Assert.assertEquals(dos.get(0).getContent(), list.get(0).getContent());

    }

    @Test
    public void convertDTO() {
        SuggestionBO bo = new SuggestionBO();
        bo.setContent("1234");
        bo.setGmtCreate(new Date());
        bo.setGmtModified(new Date());
        bo.setId(1);
        bo.setUserNum("1234566");
        bo.setClientType(SuggestionClientType.ANDROID);
        bo.setUserType(SuggestionUserType.MEMBER);
        SuggestionDTO suggestionDTO = SuggestionConverter.convert(bo);
        Assert.assertEquals(bo.getContent(), suggestionDTO.getContent());
    }

    @Test
    public void convertDTOS() {
        List<SuggestionBO> bos = new ArrayList<>();
        SuggestionBO bo = new SuggestionBO();
        bo.setContent("1234");
        bo.setGmtCreate(new Date());
        bo.setGmtModified(new Date());
        bo.setId(1);
        bo.setUserNum("1234566");
        bo.setClientType(SuggestionClientType.ANDROID);
        bo.setUserType(SuggestionUserType.MEMBER);
        bos.add(bo);
        List<SuggestionDTO> list = SuggestionConverter.convertDTOS(bos);
        Assert.assertEquals(bos.get(0).getContent(), list.get(0).getContent());
    }

    @Test
    public void convertDO() {
        SuggestionParam param = new SuggestionParam();
        param.setClientType(SuggestionClientType.ANDROID);
        param.setContent("test");
        SuggestionDO suggestionDO = SuggestionConverter.convert("M123456", param);
        Assert.assertEquals(param.getContent(), suggestionDO.getContent());
    }
}
