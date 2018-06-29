package com.lawu.eshop.mall.srv.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.sms.util.SmsConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.param.SmsRecordParam;
import com.lawu.eshop.mall.srv.MallSrvConfig;
import com.lawu.eshop.mall.srv.bo.SmsRecordBO;
import com.lawu.eshop.mall.srv.domain.SmsRecordDO;
import com.lawu.eshop.mall.srv.domain.SmsRecordDOExample;
import com.lawu.eshop.mall.srv.domain.VerifyCodeDO;
import com.lawu.eshop.mall.srv.mapper.SmsRecordDOMapper;
import com.lawu.eshop.mall.srv.mapper.VerifyCodeDOMapper;
import com.lawu.eshop.mall.srv.service.SmsRecordService;
import com.lawu.utils.DateUtil;
import com.lawu.utils.StringUtil;

/**
 * 发送短信服务实现
 *
 * @author meishuquan
 * @date 2017/3/27
 */
@Service
public class SmsRecordServiceImpl implements SmsRecordService {

    @Autowired
    private SmsRecordDOMapper smsRecordDOMapper;

    @Autowired
    private VerifyCodeDOMapper verifyCodeDOMapper;

    @Autowired
    private MallSrvConfig mallSrvConfig;

    @Override
    public int verifySendSms(String mobile, String ip) throws ParseException {
    	
    	//判断获取验证码是否过于频繁
    	SmsRecordDOExample example = new SmsRecordDOExample();
    	example.createCriteria().andMobileEqualTo(mobile).andGmtCreateGreaterThanOrEqualTo(DateUtil.getMinutepointTime(mallSrvConfig.getSmsSendMinute()*-1)).andGmtCreateLessThanOrEqualTo(new Date());
        long count = smsRecordDOMapper.countByExample(example);
		if (count > 0) {
			 return ResultCode.SMS_SEND_MOBILE_LIMIT_MINUTE;
		}
    	
        DateFormat dfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH");
        String date = dfs.format(new Date());
        String startDate = date + ":00:00";
        String endDate = date + ":59:59";
        SmsRecordDOExample smsRecordDOExample = new SmsRecordDOExample();
        smsRecordDOExample.createCriteria().andMobileEqualTo(mobile).andGmtCreateGreaterThanOrEqualTo(dfd.parse(startDate)).andGmtCreateLessThanOrEqualTo(dfd.parse(endDate)).andIsSuccessEqualTo(SmsConstant.SMS_SEND_SUCCESS);
        long hourCount = smsRecordDOMapper.countByExample(smsRecordDOExample);
        if (hourCount >= mallSrvConfig.getSmsSendHourCount()) {
            return ResultCode.SMS_SEND_HOUR_LIMIT;
        }

        date = DateUtil.getDate();
        startDate = date + " 00:00:00";
        endDate = date + " 23:59:59";
        if (ip != null) {
            smsRecordDOExample = new SmsRecordDOExample();
            smsRecordDOExample.createCriteria().andIpEqualTo(ip).andGmtCreateGreaterThanOrEqualTo(dfd.parse(startDate)).andGmtCreateLessThanOrEqualTo(dfd.parse(endDate)).andIsSuccessEqualTo(SmsConstant.SMS_SEND_SUCCESS);
            long ipCount = smsRecordDOMapper.countByExample(smsRecordDOExample);
            if (ipCount >= mallSrvConfig.getSmsSendIpCount()) {
                return ResultCode.SMS_SEND_IP_LIMIT;
            }
        }

        smsRecordDOExample = new SmsRecordDOExample();
        smsRecordDOExample.createCriteria().andMobileEqualTo(mobile).andGmtCreateGreaterThanOrEqualTo(dfd.parse(startDate)).andGmtCreateLessThanOrEqualTo(dfd.parse(endDate)).andIsSuccessEqualTo(SmsConstant.SMS_SEND_SUCCESS);
        long dayCount = smsRecordDOMapper.countByExample(smsRecordDOExample);
        if (dayCount >= mallSrvConfig.getSmsSendMobileCount()) {
            return ResultCode.SMS_SEND_MOBILE_LIMIT;
        }
        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SmsRecordBO saveSmsRecord(SmsRecordParam param) {
        String smsTemplate = "";
        if (param.getPurposeEnum() == VerifyCodePurposeEnum.USER_REGISTER) {
            smsTemplate = mallSrvConfig.getRegisterTemplateContent();
        } else if (param.getPurposeEnum() == VerifyCodePurposeEnum.FIND_LOGIN_PWD || param.getPurposeEnum() == VerifyCodePurposeEnum.FIND_PAY_PWD) {
            smsTemplate = mallSrvConfig.getFindPwdTemplateContent();
        } else if (param.getPurposeEnum() == VerifyCodePurposeEnum.REFUND_BOND) {
            smsTemplate = mallSrvConfig.getRefundDepositTemplateContent();
        } else if (param.getPurposeEnum() == VerifyCodePurposeEnum.SMS_LOGIN) {
            smsTemplate = mallSrvConfig.getLoginTemplateContent();
        } else if (param.getPurposeEnum() == VerifyCodePurposeEnum.THIRD_LOGIN) {
            smsTemplate = mallSrvConfig.getBindPhoneTemplateContent();
        }
        //插入短信记录
        SmsRecordDO smsRecordDO = new SmsRecordDO();
        smsRecordDO.setMobile(param.getMobile());
        smsRecordDO.setContent(StringUtil.getUtf8String(smsTemplate).replace("{smsCode}", param.getSmsCode()));
        smsRecordDO.setIp(param.getIp());
        smsRecordDO.setType(param.getPurposeEnum().val);
        smsRecordDO.setIsSuccess(param.getSuccess());
        smsRecordDO.setFailReason(param.getFailReason());
        smsRecordDO.setGmtModified(new Date());
        smsRecordDO.setGmtCreate(new Date());
        smsRecordDOMapper.insertSelective(smsRecordDO);

        if (param.getSuccess()) {
            //插入验证码记录
            VerifyCodeDO verifyCodeDO = new VerifyCodeDO();
            verifyCodeDO.setMobile(param.getMobile());
            verifyCodeDO.setCode(param.getSmsCode());
            verifyCodeDO.setPurpose(param.getPurposeEnum().val);
            verifyCodeDO.setGmtCreate(new Date());
            verifyCodeDOMapper.insertSelective(verifyCodeDO);

            SmsRecordBO smsRecordBO = new SmsRecordBO();
            smsRecordBO.setVirifyCodeId(verifyCodeDO.getId());
            return smsRecordBO;
        }
        return null;
    }

    @Override
    public void updateSmsRecordResult(Long id, Boolean isSuccess, String failReason) {
        SmsRecordDO smsRecordDO = new SmsRecordDO();
        smsRecordDO.setId(id);
        smsRecordDO.setIsSuccess(isSuccess);
        smsRecordDO.setFailReason(failReason);
        smsRecordDOMapper.updateByPrimaryKeySelective(smsRecordDO);
    }

}
