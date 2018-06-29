package com.lawu.eshop.mall.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.constants.MessageQueryTypeEnum;
import com.lawu.eshop.mall.constants.MessageStatusEnum;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageParam;
import com.lawu.eshop.mall.param.MessageQueryParam;
import com.lawu.eshop.mall.param.MessageStatisticsParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.mall.param.OperatorMessageInfoParam;
import com.lawu.eshop.mall.param.OperatorMessageParam;
import com.lawu.eshop.mall.param.PushParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.MessageBO;
import com.lawu.eshop.mall.srv.bo.MessageStatisticsBO;
import com.lawu.eshop.mall.srv.bo.MessageTemplateBO;
import com.lawu.eshop.mall.srv.domain.MessageDO;
import com.lawu.eshop.mall.srv.mapper.MessageDOMapper;
import com.lawu.eshop.mall.srv.service.MessageService;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageDOMapper messageDOMapper;


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void selectNoReadCount(){
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setGmtCreate(new Date());
        messageDO.setTitle("test");
        messageDO.setContent("contents");
        messageDO.setUserNum("M0001");
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDOMapper.insert(messageDO);
        MessageStatisticsParam param = new MessageStatisticsParam();
        param.setUserNum("M0001");
        param.setTypeEnum(MessageQueryTypeEnum.BUSINESS);
        param.setUserType(UserTypeEnum.MERCHANT);
        int count = messageService.selectNoReadCount(param);
        Assert.assertEquals(1,count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectLastMessage(){
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setGmtCreate(new Date());
        messageDO.setTitle("test");
        messageDO.setContent("contents");
        messageDO.setUserNum("M0001");
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDOMapper.insert(messageDO);
        MessageStatisticsBO messageStatisticsBO = messageService.selectLastMessage("M0001");
        Assert.assertNotNull(messageStatisticsBO);
        Assert.assertEquals(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal(),messageStatisticsBO.getType());
    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMessageList(){
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setGmtCreate(new Date());
        messageDO.setTitle("test");
        messageDO.setContent("contents");
        messageDO.setUserNum("M0001");
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDOMapper.insert(messageDO);

        MessageParam param = new MessageParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<MessageBO> page = messageService.getMessageList(UserTypeEnum.MEMBER, "M0001",param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1,page.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMessageStatus(){
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setGmtCreate(new Date());
        messageDO.setTitle("test");
        messageDO.setContent("contents");
        messageDO.setUserNum("M0001");
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDOMapper.insert(messageDO);

        messageService.updateMessageStatus(messageDO.getId(),MessageStatusEnum.MESSAGE_STATUS_READ,"M0001");
        List<MessageDO> list  = messageDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
        Assert.assertEquals(MessageStatusEnum.MESSAGE_STATUS_READ.val,list.get(0).getStatus());
    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveMessage(){
        String userNum = "M0001";
        MessageInfoParam param = new MessageInfoParam();
        MessageTempParam tempParam;
        param.setRelateId(1L);
        // 0,12
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITE_FANS);
        tempParam = new MessageTempParam();
        tempParam.setUserName("test");
        tempParam.setMerchantName("merchant");
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //0,1,2
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_USER_SEND);
        tempParam = new MessageTempParam();
        tempParam.setUserName("test");
        tempParam.setOrderNum("123456");
        tempParam.setWaybillNum("321654");
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //3,4
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_BALANCE);
        tempParam = new MessageTempParam();
        tempParam.setBalance(BigDecimal.ONE);
        tempParam.setRechargeBalance(BigDecimal.TEN);
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //4,5
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_POINT);
        tempParam = new MessageTempParam();
        tempParam.setRechargeBalance(BigDecimal.TEN);
        tempParam.setPoint(BigDecimal.TEN);
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //14,6,7
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PAY_ORDER_SUCCESS);
        tempParam = new MessageTempParam();
        tempParam.setExpendAmount(BigDecimal.TEN);
        tempParam.setFavoredAmount(BigDecimal.TEN);
        tempParam.setStoreName("super");
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);


        //0,9
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PAYMENT_SUCCESS);
        tempParam = new MessageTempParam();
        tempParam.setUserName("test");
        tempParam.setProductName("product");
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //0,10
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECOMMEND_MEMBER_BALANCE);
        tempParam = new MessageTempParam();
        tempParam.setUserName("name");
        tempParam.setEarningAmount(BigDecimal.TEN);
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);


        //0，11
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECOMMEND_MEMBER_POINT);
        tempParam = new MessageTempParam();
        tempParam.setUserName("name");
        tempParam.setEarningAmount(BigDecimal.TEN);
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);


        //13,17,18
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_CHECK_AD_FAIL);
        tempParam = new MessageTempParam();
        tempParam.setAdName("ad");
        tempParam.setAdTypeName("type");
        tempParam.setFailReason("fail");
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //15,5
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_USE_POINT_REDPACKET);
        tempParam = new MessageTempParam();
        tempParam.setExpendPoint(BigDecimal.ONE);
        tempParam.setPoint(BigDecimal.ONE);
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);


        //8.21
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_REFUND_AGREE);
        tempParam = new MessageTempParam();
        tempParam.setRefundNum("123456");
        tempParam.setRefundAmount(BigDecimal.ONE);
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //19
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_SEND_NOTICE);
        tempParam = new MessageTempParam();
        tempParam.setProductCount(1);
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //9,20,2
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_ORDER_SENDING);
        tempParam = new MessageTempParam();
        tempParam.setProductName("name");
        tempParam.setExpressCompanyName("company");
        tempParam.setWaybillNum("123");
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        //22
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_ORDER_SUCCESS);
        tempParam = new MessageTempParam();
        tempParam.setOrderAmount(BigDecimal.ONE);
        param.setMessageParam(tempParam);
        messageService.saveMessage(userNum,param);

        List<MessageDO> messageDOS = messageDOMapper.selectByExample(null);
        Assert.assertNotNull(messageDOS);
        Assert.assertEquals(14,messageDOS.size());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getTemplateByType(){
        MessageTemplateBO messageTemplateBO = messageService.getTemplateByType(MessageTypeEnum.MESSAGE_TYPE_ACTIVE);
        Assert.assertNotNull(messageTemplateBO);
        Assert.assertEquals(MessageTypeEnum.MESSAGE_TYPE_ACTIVE.getVal(),messageTemplateBO.getType());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveMessageOperator(){
        OperatorMessageInfoParam param = new OperatorMessageInfoParam();
        param.setContent("content");
        param.setTitle("title");
        messageService.saveMessageOperator("M0001",param);
        List<MessageDO> list = messageDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
        Assert.assertEquals("title",list.get(0).getTitle());
    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveMessageToAll(){
        OperatorMessageParam param = new OperatorMessageParam();
        List<PushParam> pushParams = new ArrayList<>();
        PushParam pushParam = new PushParam();
        pushParam.setUserNum("M00001");
        pushParam.setGtCid("123456");
        pushParams.add(pushParam);
        param.setContent("content");
        param.setTitle("title");

        param.setUserTypeEnum(UserTypeEnum.MEMBER);
        param.setParams(pushParams);
        messageService.saveMessageToAll(param);
        param.setArea("all");
        messageService.saveMessageToAll(param);

        List<MessageDO> messageDOS = messageDOMapper.selectByExample(null);
        Assert.assertNotNull(messageDOS);
        Assert.assertEquals(2,messageDOS.size());
    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getOperatorMessageList(){
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_READ.val);
        messageDO.setGmtCreate(new Date());
        messageDO.setTitle("test");
        messageDO.setContent("contents");
        messageDO.setUserNum("M0001");
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDOMapper.insert(messageDO);

        MessageDO messageDO2 = new MessageDO();
        messageDO2.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO2.setGmtCreate(new Date());
        messageDO2.setTitle("test");
        messageDO2.setContent("contents");
        messageDO2.setUserNum("M0002");
        messageDO2.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDOMapper.insert(messageDO2);

        MessageQueryParam param = new MessageQueryParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<MessageBO> page = messageService.getOperatorMessageList(param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(2,page.getRecords().size());

        MessageQueryParam messageQueryParam = new MessageQueryParam();
        messageQueryParam.setCurrentPage(1);
        messageQueryParam.setPageSize(10);
        messageQueryParam.setUserNum("M0002");
        Page<MessageBO> messageBOPage = messageService.getOperatorMessageList(messageQueryParam);
        Assert.assertNotNull(messageBOPage.getRecords());
        Assert.assertEquals(1,messageBOPage.getRecords().size());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void  selectMessageId(){
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_READ.val);
        messageDO.setGmtCreate(new Date());
        messageDO.setTitle("test");
        messageDO.setContent("contents");
        messageDO.setUserNum("M0001");
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDOMapper.insert(messageDO);
        MessageBO messageBO = messageService.selectMessageId(messageDO.getId());
        Assert.assertNotNull(messageBO);
        Assert.assertEquals(MessageStatusEnum.MESSAGE_STATUS_READ.val,messageBO.getStatus());
    }


}
