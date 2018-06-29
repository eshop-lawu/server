package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.srv.bo.VerifyCodeBO;

/**
 * @author meishuquan
 * @date 2017/3/28
 */
public interface VerifyCodeService {

    /**
     * 保存图形验证码
     *
     * @param mobile  手机号码
     * @param picCode 图形验证码
     * @param purpose 用途
     * @return
     */
    Long savePicCode(String mobile, String picCode, VerifyCodePurposeEnum purpose);

    /**
     * 根据ID查询验证码
     *
     * @param id ID
     * @return
     */
    VerifyCodeBO getVerifyCodeById(Long id);

    /**
     * 根据手机号码查询生成的最后一个图形验证码
     *
     * @return
     */
    VerifyCodeBO getLastPicVerifyCode(String mobile);
}
