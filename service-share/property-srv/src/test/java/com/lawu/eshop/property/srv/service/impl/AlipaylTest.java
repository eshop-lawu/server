package com.lawu.eshop.property.srv.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class AlipaylTest {

    private String alipayAppIdMember = "2017010804931124";
    private String alipayAppIdMerchant = "2017010804931165";
    private String alipayPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMx1t5K2k2HdpPSusRnDmJfMKVEIRFoRPevkSw7LrrMcfUTqqhijNWNnCwjRX3BQ25n2Nll0I5y8ZZCyHURY46CJtn3T6JvmcrMEvQS1lFIM3nWuOQiUxI0xas/2AATVN/hA9PS02Heyzid0qGsunkWRqlyezuaqnLrLLXe9iHT3AgMBAAECgYAIfFpCHJHBXhGaliWDjDqpEjAkzNMJae3H/JGg65M0m9oXIiYtH5j3py8uDoKHhtOWcEGRNODFgIw2lS3hyE6qKAOUwGnsucyUZ+chuP/q6WwlQARuGIKCvmLBCdh9CMmW6ifHGXMLtNyRDr8+0NaT0pHGtizClsCvcAf2a/JzAQJBAPVNh2gSuS094drh5KWyGKuIU/S3KdNqifaE2dagCkvKLGYxnUiP7IHT6PYEW9CFb5zBdtyDG1vKEtpWjZhc6mMCQQDVYDtQ2qwpRYz6FnRhDAVNGIP31+IOGDNVfJqVcWbXHIG2vrSsFNvDxnytS3E990SH9ZOxZ5TD+Tnxph57rSVdAkEAt7wacBmWGgz/3rLUUEfVsqWqPOH0siZasAtSHzBAhgQovWLsLC2hOJ1BIQSiwM0xjHhcT07kbxwsgt3RsQ3ngQJBAKqsN6Xb05uLk1EzBtHdcJwTQPPMzXdiqiT17xwcQhtMJQFMl22RnZuCN5XR43miQEHUD9f2N0tuHYgJzRY2D80CQHrYNG+P7rd4AjNe3fW0IO9VTkGckGueqTcE1NcY5w1n2IXCUyRlh3RMDJ+HosawCYtEbFDHo7ozT0bir5n7xF4=";
    private String alipayEdianPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    @Test
    public void alipayTradeQuery() {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipayAppIdMember, alipayPrivateKey, "json", "utf-8", alipayEdianPublicKey, "RSA");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"trade_no\":\"2018020321001004050513896306\"" +
                "}");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
            System.out.println(JSON.toJSONString(response));
            //System.out.println(response.getCode()+"->"+response.getMsg()+"->"+response.getSubCode()+"->"+response.getSubMsg()+"->"+response.getTradeStatus()+"->"+response.getTotalAmount());
        } else {
            System.out.println("调用失败");
        }
    }

}
