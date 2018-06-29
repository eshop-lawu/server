package com.lawu.eshop.mall.srv.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.param.SmsRecordParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.SmsRecordBO;
import com.lawu.eshop.mall.srv.domain.SmsRecordDO;
import com.lawu.eshop.mall.srv.domain.VerifyCodeDO;
import com.lawu.eshop.mall.srv.mapper.SmsRecordDOMapper;
import com.lawu.eshop.mall.srv.mapper.VerifyCodeDOMapper;
import com.lawu.eshop.mall.srv.service.SmsRecordService;

/**
 * @author zhangyong
 * @date 2017/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class SmsRecordServiceImplTest {

    @Autowired
    private SmsRecordService smsRecordService;
    @Autowired
    private SmsRecordDOMapper smsRecordDOMapper;

    @Autowired
    private VerifyCodeDOMapper verifyCodeDOMapper;

    @Ignore
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void verifySendSms() throws ParseException {

        String mobile = "123456789";
        String ip = "121.35.101.59";
        SmsRecordDO smsRecordDO ;
        smsRecordDO = new SmsRecordDO();
        smsRecordDO.setIp(ip);
        smsRecordDO.setMobile(mobile);
        smsRecordDO.setIsSuccess(true);
        smsRecordDO.setGmtCreate(new Date());
        smsRecordDOMapper.insert(smsRecordDO);

        smsRecordDO = new SmsRecordDO();
        smsRecordDO.setIp(ip);
        smsRecordDO.setMobile(mobile);
        smsRecordDO.setIsSuccess(true);
        smsRecordDO.setGmtCreate(new Date());
        smsRecordDOMapper.insert(smsRecordDO);

        smsRecordDO = new SmsRecordDO();
        smsRecordDO.setIp(ip);
        smsRecordDO.setMobile(mobile);
        smsRecordDO.setIsSuccess(true);
        smsRecordDO.setGmtCreate(new Date());
        smsRecordDOMapper.insert(smsRecordDO);

        smsRecordDO = new SmsRecordDO();
        smsRecordDO.setIp(ip);
        smsRecordDO.setMobile(mobile);
        smsRecordDO.setIsSuccess(true);
        smsRecordDO.setGmtCreate(new Date());
        smsRecordDOMapper.insert(smsRecordDO);

        int code = smsRecordService.verifySendSms(mobile,ip);
        Assert.assertEquals(ResultCode.SUCCESS,code);


        //同手机号
        smsRecordDO = new SmsRecordDO();
        smsRecordDO.setIp(ip);
        smsRecordDO.setMobile(mobile);
        smsRecordDO.setIsSuccess(true);
        smsRecordDO.setGmtCreate(new Date());
        smsRecordDOMapper.insert(smsRecordDO);
        //同手机号
        smsRecordDO = new SmsRecordDO();
        smsRecordDO.setIp(ip);
        smsRecordDO.setMobile(mobile);
        smsRecordDO.setIsSuccess(true);
        smsRecordDO.setGmtCreate(new Date());
        smsRecordDOMapper.insert(smsRecordDO);
        int code2 = smsRecordService.verifySendSms(mobile,ip);

        Assert.assertEquals(ResultCode.SMS_SEND_MOBILE_LIMIT,code2);


    }

    @Ignore
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveSmsRecord() {
        SmsRecordParam param = new SmsRecordParam();
        param.setMobile("13666666666");
        param.setIp("121.35.101.59");
        param.setPurposeEnum(VerifyCodePurposeEnum.USER_REGISTER);
        param.setSmsCode("123456");
        param.setSuccess(true);
        SmsRecordBO smsRecordBO = smsRecordService.saveSmsRecord(param);

        List<SmsRecordDO> smsRecordDOList = smsRecordDOMapper.selectByExample(null);
        Assert.assertNotNull(smsRecordDOList);
        Assert.assertEquals(1, smsRecordDOList.size());
        Assert.assertEquals(param.getPurposeEnum().val, smsRecordDOList.get(0).getType());

        List<VerifyCodeDO> verifyCodeDOS = verifyCodeDOMapper.selectByExample(null);
        Assert.assertNotNull(verifyCodeDOS);
        Assert.assertEquals(1, verifyCodeDOS.size());
        Assert.assertEquals(smsRecordBO.getVirifyCodeId(), verifyCodeDOS.get(0).getId());
    }

    @Ignore
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateSmsRecordResult(){
        String mobile = "123456789";
        String ip = "121.35.101.59";
        SmsRecordDO smsRecordDO = new SmsRecordDO();
        smsRecordDO.setIp(ip);
        smsRecordDO.setMobile(mobile);
        smsRecordDO.setIsSuccess(true);
        smsRecordDO.setGmtCreate(new Date());
        smsRecordDOMapper.insert(smsRecordDO);
        smsRecordService.updateSmsRecordResult(smsRecordDO.getId(),false,"fail");
        List<SmsRecordDO> list = smsRecordDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
        Assert.assertEquals(false,list.get(0).getIsSuccess());
    }
}
