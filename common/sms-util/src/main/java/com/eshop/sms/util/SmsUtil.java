package com.eshop.sms.util;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawu.utils.DateUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信工具类
 *
 * @author meishuquan
 * @date 2017/3/27
 */
public class SmsUtil {

    private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    private SmsUtil(){}

    /**
     * 发送短信验证码
     *
     * @param smsCode 短信验证码
     * @param mobile  手机号码
     * @param ip      请求IP
     * @return
     * @throws IOException
     */
    public static Map<String, Object> sendSms(String smsCode, String mobile, String ip,SmsConfigParam smsConfigParam) throws IOException {
        Map<String, Object> map = new HashMap<>();
        HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod(smsConfigParam.getSmsUrl());
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, smsConfigParam.getSmsEncoding());
        post.addParameter("SpCode", smsConfigParam.getSmsSpCode());
        post.addParameter("LoginName", smsConfigParam.getSmsLoginName());
        post.addParameter("Password", smsConfigParam.getSmsPassword());
        post.addParameter("MessageContent", smsConfigParam.getSmsTemplate().replace("{smsCode}", smsCode));
        post.addParameter("UserNumber", mobile);
        post.addParameter("SerialNumber", smsConfigParam.getSmsSerialNumber() + DateUtil.getIntDateTime());
        post.addParameter("ScheduleTime", "");
        post.addParameter("ExtendAccessNum", "");
        post.addParameter("f", smsConfigParam.getSmsF());
        httpclient.executeMethod(post);
        String sendResult = new String(post.getResponseBody(), smsConfigParam.getSmsEncoding());
        logger.info("发送短信结果：{} --------- {}", sendResult, ip);
        if (sendResult.contains("发送短信成功")) {
            map.put("sendCode", SmsConstant.SMS_SEND_SUCCESS);
            map.put("sendResult", "");
        } else {
            map.put("sendCode", SmsConstant.SMS_SEND_FAIL);
            map.put("sendResult", sendResult);
        }
        return map;
    }
}
