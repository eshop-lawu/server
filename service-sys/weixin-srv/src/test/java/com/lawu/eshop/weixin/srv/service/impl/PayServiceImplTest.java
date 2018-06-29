package com.lawu.eshop.weixin.srv.service.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.lawu.eshop.weixin.srv.WeixinSrvApplicationTest;
import com.lawu.eshop.weixin.srv.service.PayService;
import com.lawu.weixinapi.dto.param.RedpackParam;

/**
 * @author Leach
 * @date 2018/2/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WeixinSrvApplicationTest.class)
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Ignore
    @Test
    public void sendRedpack() {
        RedpackParam redpackParam = new RedpackParam();
        redpackParam.setActName("活动名称");
        redpackParam.setMchBillno("TEST0000001");
        redpackParam.setOpenid("omFIV0kw4G2qxg_KvOA_iZGCHsbk");
        redpackParam.setRemark("备注");
        redpackParam.setSceneId("PRODUCT_1");
        redpackParam.setSendName("商户名称");
        redpackParam.setTotalAmount(100);
        redpackParam.setTotalNum(1);
        redpackParam.setWishing("红包祝福语");
        try {
            payService.sendRedpack(redpackParam);
        } catch (WxPayException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Ignore
    @Test
    public void queryRedpack() {
        WxPayRedpackQueryResult wxPayRedpackQueryResult = null;
        try {
            wxPayRedpackQueryResult = payService.queryRedpack("TEST0000001");
        } catch (WxPayException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertNotNull(wxPayRedpackQueryResult);
        System.out.printf("return_code:%s,return_msg:%s, result_code:%s\n",
                wxPayRedpackQueryResult.getReturnCode(),
                wxPayRedpackQueryResult.getReturnMsg(),
                wxPayRedpackQueryResult.getResultCode());
    }
}
