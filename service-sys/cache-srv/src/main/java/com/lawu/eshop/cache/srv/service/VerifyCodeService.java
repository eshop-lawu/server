package com.lawu.eshop.cache.srv.service;

/**
 * @author meishuquan
 * @date 2018/3/12.
 */
public interface VerifyCodeService {

    /**
     * 获取短信接口调用频率
     *
     * @param tag
     * @param expireTime
     * @return
     * @author meishuquan
     */
    Long getVisitFrequency(String tag, Integer expireTime);

    /**
     * 获取图形验证码标记
     *
     * @param tag
     * @param frequencyExpireTime
     * @param flagExpireTime
     * @param frequencyLimit
     * @return
     * @author meishuquan
     */
    Boolean getPicCodeFlag(String tag, Integer frequencyExpireTime, Integer flagExpireTime, Integer frequencyLimit);

    /**
     * 校验图形验证码
     *
     * @param key
     * @param picCode
     * @return
     * @author meishuquan
     */
    Boolean verifyPicCode(String key, String picCode);

    /**
     * 保存图形验证码
     *
     * @param key
     * @param picCode
     * @param expireTime
     * @author meishuquan
     */
    void savePicCode(String key, String picCode, Integer expireTime);

    /**
     * 更新图形验证码标记
     *
     * @param isOpen
     * @author meishuquan
     */
    void updatePicCodeFlag(Boolean isOpen);

}
