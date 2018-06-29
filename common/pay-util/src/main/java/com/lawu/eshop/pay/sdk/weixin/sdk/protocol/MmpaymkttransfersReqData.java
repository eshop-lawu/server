package com.lawu.eshop.pay.sdk.weixin.sdk.protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.lawu.eshop.pay.sdk.weixin.base.RandomStringGenerator;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.Signature;

/**
 * 企业付款请求参数
 * https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
 */
public class MmpaymkttransfersReqData {

    private String mch_appid = "";
    private String mchid = "";
    private String nonce_str = "";
    private String sign = "";
    private String partner_trade_no = "";
    private String openid = "";
    private String check_name = "FORCE_CHECK";//NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
    private String re_user_name = "";
    private int amount = 0;
    private String desc = "";
    private String spbill_create_ip = "192.168.1.1";

    /**
     * @param mchAppid
     * @param mchid
     * @param partnerTradeNo
     * @param openid
     * @param reUserName
     * @param amount
     * @param desc
     */
    public MmpaymkttransfersReqData(String mchAppid, String mchid, String partnerTradeNo, String openid, String reUserName, int amount, String desc, String key_app) {

        setMch_appid(mchAppid);
        setMchid(mchid);
        setPartner_trade_no(partnerTradeNo);
        setOpenid(openid);
        setCheck_name(getCheck_name());
        setRe_user_name(reUserName);
        setAmount(amount);
        setDesc(desc);
        setSpbill_create_ip(getSpbill_create_ip());

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap(), key_app);
        setSign(sign);//把签名数据设置到Sign这个属性中

    }


    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if (obj != null) {
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

    public String getMch_appid() {
        return mch_appid;
    }

    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
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

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheck_name() {
        return check_name;
    }

    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getRe_user_name() {
        return re_user_name;
    }

    public void setRe_user_name(String re_user_name) {
        this.re_user_name = re_user_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
}
