package com.lawu.eshop.mall.srv.converter;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.eshop.mall.dto.SearchWordDTO;
import com.lawu.eshop.mall.srv.bo.SearchWordBO;
import com.lawu.eshop.mall.srv.domain.SearchWordDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class SearchWordConverterTest {

    @Test
    public void convertBO() {
        SearchWordDO searchWordDO = new SearchWordDO();
        searchWordDO.setId(1L);
        searchWordDO.setWord("KEY");
        searchWordDO.setType(SearchWordTypeEnum.WORD_TYPE_PRODUCT.val);
        searchWordDO.setGmtCreate(new Date());
        SearchWordBO searchWordBO = SearchWordConverter.convertBO(searchWordDO);
        Assert.assertEquals(searchWordDO.getWord(), searchWordBO.getWord());
    }

    @Test
    public void convertDTO() {
        SearchWordBO searchWordBO = new SearchWordBO();
        searchWordBO.setId(1L);
        searchWordBO.setWord("KEY");
        searchWordBO.setType(SearchWordTypeEnum.WORD_TYPE_PRODUCT.val);
        searchWordBO.setGmtCreate(new Date());
        SearchWordDTO searchWordDTO = SearchWordConverter.convertDTO(searchWordBO);
        Assert.assertEquals(searchWordBO.getWord(), searchWordDTO.getWord());
    }
}
