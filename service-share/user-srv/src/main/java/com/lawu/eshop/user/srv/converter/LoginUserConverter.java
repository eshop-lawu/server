package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantBO;

/**
 * @author Leach
 * @date 2017/3/27
 */
public class LoginUserConverter {

    /**
     * 会员信息-登录用户
     *
     * @param memberBO
     * @return
     */
    public static LoginUserDTO convert(MemberBO memberBO) {
        if (memberBO == null) {
            return null;
        }

        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setId(memberBO.getId());
        loginUserDTO.setNum(memberBO.getNum());
        loginUserDTO.setAccount(memberBO.getAccount());
        loginUserDTO.setRyToken(memberBO.getRyToken());
        loginUserDTO.setIsFreeze(memberBO.getIsFreeze());
        loginUserDTO.setUserSex(memberBO.getUserSex());
        return loginUserDTO;
    }

    /**
     * 商家信息-登录用户
     *
     * @param merchantBO
     * @return
     */
    public static LoginUserDTO convert(MerchantBO merchantBO) {
        if (merchantBO == null) {
            return null;
        }

        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setId(merchantBO.getId());
        loginUserDTO.setNum(merchantBO.getNum());
        loginUserDTO.setAccount(merchantBO.getAccount());
        loginUserDTO.setIsFreeze(merchantBO.getIsFreeze());
        loginUserDTO.setRyToken(merchantBO.getRyToken());
        return loginUserDTO;
    }
}
