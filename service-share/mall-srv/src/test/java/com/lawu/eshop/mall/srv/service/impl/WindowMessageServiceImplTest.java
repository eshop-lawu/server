package com.lawu.eshop.mall.srv.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.constants.WindowMessageTypeEnum;
import com.lawu.eshop.mall.param.WindowMessageParam;
import com.lawu.eshop.mall.query.OperatorWindowMessageQuery;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.WindowMessageBO;
import com.lawu.eshop.mall.srv.domain.WindowMessageDO;
import com.lawu.eshop.mall.srv.mapper.WindowMessageDOMapper;
import com.lawu.eshop.mall.srv.service.WindowMessageService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class WindowMessageServiceImplTest {

    @Autowired
    private WindowMessageService windowMessageService;

    @Autowired
    private WindowMessageDOMapper windowMessageDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveWindowMessage() {
        WindowMessageParam param = new WindowMessageParam();
        param.setImgPath("test");
        param.setRelateId(100L);
        param.setStatusEnum(WindowMessageStatusEnum.ENABLE);
        param.setTypeEnum(WindowMessageTypeEnum.POINT_LOTTERY);

        windowMessageService.saveWindowMessage(param);
        List<WindowMessageDO> messageDOS = windowMessageDOMapper.selectByExample(null);
        Assert.assertNotNull(messageDOS);
        Assert.assertEquals(1, messageDOS.size());
        Assert.assertEquals(param.getImgPath(), messageDOS.get(0).getImgPath());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listOperatorWindowMessage() {
        WindowMessageDO messageDO = new WindowMessageDO();
        messageDO.setImgPath("test");
        messageDO.setRelateId(100L);
        messageDO.setStatus((byte) 1);
        messageDO.setType((byte) 1);
        windowMessageDOMapper.insertSelective(messageDO);

        OperatorWindowMessageQuery query = new OperatorWindowMessageQuery();
        Page<WindowMessageBO> messageBOPage = windowMessageService.listOperatorWindowMessage(query);
        Assert.assertNotNull(messageBOPage.getRecords());
        Assert.assertEquals(messageDO.getImgPath(), messageBOPage.getRecords().get(0).getImgPath());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getWindowMessage() {
        WindowMessageDO messageDO = new WindowMessageDO();
        messageDO.setImgPath("test");
        messageDO.setRelateId(100L);
        messageDO.setStatus((byte) 1);
        messageDO.setType((byte) 1);
        windowMessageDOMapper.insertSelective(messageDO);

        WindowMessageBO messageBO = windowMessageService.getWindowMessage(messageDO.getId());
        Assert.assertNotNull(messageBO);
        Assert.assertEquals(messageDO.getImgPath(), messageBO.getImgPath());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateWindowMessageStatus() {
        WindowMessageDO messageDO = new WindowMessageDO();
        messageDO.setImgPath("test");
        messageDO.setRelateId(100L);
        messageDO.setStatus((byte) 1);
        messageDO.setType((byte) 1);
        windowMessageDOMapper.insertSelective(messageDO);

        windowMessageService.updateWindowMessageStatus(messageDO.getId(), WindowMessageStatusEnum.DISENABLE);
        WindowMessageDO windowMessageDO = windowMessageDOMapper.selectByPrimaryKey(messageDO.getId());
        Assert.assertNotNull(windowMessageDO);
        Assert.assertEquals(WindowMessageStatusEnum.DISENABLE.getVal(), windowMessageDO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listWindowMessage() {
        WindowMessageDO messageDO = new WindowMessageDO();
        messageDO.setImgPath("test");
        messageDO.setRelateId(100L);
        messageDO.setStatus((byte) 1);
        messageDO.setType((byte) 1);
        windowMessageDOMapper.insertSelective(messageDO);

        List<WindowMessageBO> messageBOS = windowMessageService.listWindowMessage();
        Assert.assertNotNull(messageBOS);
        Assert.assertEquals(messageDO.getImgPath(), messageBOS.get(0).getImgPath());
    }

}
