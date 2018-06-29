package com.lawu.eshop.pay.sdk.weixin.sdk.protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.lawu.eshop.pay.sdk.weixin.base.RandomStringGenerator;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.Signature;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月13日 下午1:46:40
 *
 */
public class RefundReqData {
    //每个字段具体的意思请查看API文档
    private String appid = "";
    private String mch_id = "";
    private String nonce_str = "";
    private String sign = "";
    private String transaction_id = "";
    private String out_refund_no = "";
    private int total_fee = 0;
    private int refund_fee = 0;
    private String op_user_id = "";

    /**
     * 请求退款服务
     * @param appId
     * @param mchId
     * @param transactionID
     * @param outRefundNo
     * @param totalFee
     * @param refundFee
     * @param opUserID
     * @param key_app
     */
    public RefundReqData(String appId,String mchId,String transactionID,String outRefundNo,int totalFee,int refundFee,String opUserID,String key_app){

        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(appId);

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(mchId);

        //transaction_id是微信系统为每一笔支付交易分配的订单号，通过这个订单号可以标识这笔交易，它由支付订单API支付成功时返回的数据里面获取到。
        setTransaction_id(transactionID);

        //商户系统自己生成的唯一的订单号
//        setOut_trade_no(outTradeNo);

        setOut_refund_no(outRefundNo);

        setTotal_fee(totalFee);

        setRefund_fee(refundFee);

        setOp_user_id(opUserID);

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap(),key_app);
        setSign(sign);//把签名数据设置到Sign这个属性中

    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public int getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(int refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
