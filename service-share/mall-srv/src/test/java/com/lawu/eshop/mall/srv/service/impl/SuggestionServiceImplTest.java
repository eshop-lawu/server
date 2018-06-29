package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.constants.SuggestionClientType;
import com.lawu.eshop.mall.param.SuggestionListParam;
import com.lawu.eshop.mall.param.SuggestionParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.SuggestionBO;
import com.lawu.eshop.mall.srv.domain.SuggestionDO;
import com.lawu.eshop.mall.srv.mapper.SuggestionDOMapper;
import com.lawu.eshop.mall.srv.service.SuggestionService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2017/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class SuggestionServiceImplTest {

    @Autowired
    private SuggestionDOMapper suggestionDOMapper;

    @Autowired
    private SuggestionService suggestionService;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
        SuggestionParam param = new SuggestionParam();
        param.setContent("content");
        param.setClientType(SuggestionClientType.ANDROID);
        suggestionService.save("M00001", param);
        List<SuggestionDO> suggestionDOS = suggestionDOMapper.selectByExample(null);
        Assert.assertNotNull(suggestionDOS);
        Assert.assertEquals(1, suggestionDOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getSuggestionList() {
        SuggestionDO suggestionDO = new SuggestionDO();
        suggestionDO.setGmtCreate(new Date());
        suggestionDO.setContent("test");
        suggestionDO.setUserNum("M00001");
        suggestionDO.setClientType(SuggestionClientType.ANDROID.value);
        suggestionDOMapper.insert(suggestionDO);
        SuggestionListParam param = new SuggestionListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setBeginDate(DateUtil.getDate());
        param.setEndDate(DateUtil.getDate());
        Page<SuggestionBO> page = suggestionService.getSuggestionList(param);

        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());


    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delSuggestion() {
        SuggestionDO suggestionDO = new SuggestionDO();
        suggestionDO.setGmtCreate(new Date());
        suggestionDO.setContent("test");
        suggestionDO.setUserNum("M00001");
        suggestionDO.setClientType(SuggestionClientType.ANDROID.value);
        suggestionDOMapper.insert(suggestionDO);
        suggestionService.delSuggestion(suggestionDO.getId().longValue());
        List<SuggestionDO> suggestionDOS = suggestionDOMapper.selectByExample(null);
        Assert.assertEquals(0, suggestionDOS.size());
    }
}
