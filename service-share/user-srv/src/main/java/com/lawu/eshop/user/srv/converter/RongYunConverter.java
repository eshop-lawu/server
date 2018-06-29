package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.srv.bo.RongYunBO;

/**
 * @author meishuquan
 * @date 2017/5/19.
 */
public class RongYunConverter {

    /**
     * DTO转换
     *
     * @param rongYunBO
     * @return
     */
    public static RongYunDTO convertDTO(RongYunBO rongYunBO) {
        if (rongYunBO == null) {
            return null;
        }

        RongYunDTO rongYunDTO = new RongYunDTO();
        rongYunDTO.setUserNum(rongYunBO.getUserNum());
        rongYunDTO.setNickName(rongYunBO.getNickName());
        rongYunDTO.setHeadImg(rongYunBO.getHeadImg());
        return rongYunDTO;
    }
}
