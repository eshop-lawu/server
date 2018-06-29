package com.lawu.eshop.property.srv.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test.xml"})
public class ThirdPaymentServiceImplTest {


    /**
     * 单笔转账到支付宝账户
     */
    @Test
    public void alipayTransferTest() {
        String appId = "2017010804931124";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMx1t5K2k2HdpPSusRnDmJfMKVEIRFoRPevkSw7LrrMcfUTqqhijNWNnCwjRX3BQ25n2Nll0I5y8ZZCyHURY46CJtn3T6JvmcrMEvQS1lFIM3nWuOQiUxI0xas/2AATVN/hA9PS02Heyzid0qGsunkWRqlyezuaqnLrLLXe9iHT3AgMBAAECgYAIfFpCHJHBXhGaliWDjDqpEjAkzNMJae3H/JGg65M0m9oXIiYtH5j3py8uDoKHhtOWcEGRNODFgIw2lS3hyE6qKAOUwGnsucyUZ+chuP/q6WwlQARuGIKCvmLBCdh9CMmW6ifHGXMLtNyRDr8+0NaT0pHGtizClsCvcAf2a/JzAQJBAPVNh2gSuS094drh5KWyGKuIU/S3KdNqifaE2dagCkvKLGYxnUiP7IHT6PYEW9CFb5zBdtyDG1vKEtpWjZhc6mMCQQDVYDtQ2qwpRYz6FnRhDAVNGIP31+IOGDNVfJqVcWbXHIG2vrSsFNvDxnytS3E990SH9ZOxZ5TD+Tnxph57rSVdAkEAt7wacBmWGgz/3rLUUEfVsqWqPOH0siZasAtSHzBAhgQovWLsLC2hOJ1BIQSiwM0xjHhcT07kbxwsgt3RsQ3ngQJBAKqsN6Xb05uLk1EzBtHdcJwTQPPMzXdiqiT17xwcQhtMJQFMl22RnZuCN5XR43miQEHUD9f2N0tuHYgJzRY2D80CQHrYNG+P7rd4AjNe3fW0IO9VTkGckGueqTcE1NcY5w1n2IXCUyRlh3RMDJ+HosawCYtEbFDHo7ozT0bir5n7xF4=";
        String alipayPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appId, privateKey, "json", "utf-8", alipayPublicKey, "RSA");
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\"201712261544001\"," +
                "\"payee_type\":\"17512036361\"," +
                "\"payee_account\":\"yangqinghua39@163.com\"," +
                "\"amount\":\"10\"," +
                "\"payer_show_name\":\"拉乌网络\"," +
                "\"payee_real_name\":\"杨清华\"," +
                "\"remark\":\"测试\"" +
                "}");
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}