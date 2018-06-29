package com.lawu.eshop.weixin.srv.converter;

import java.util.Date;

import com.lawu.weixinapi.dto.WeixinUserDTO;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Leach
 * @date 2018/1/1
 */
public class WeixinUserConverter {

    public static WeixinUserDTO convert(WxMpUser wxMpUser) {
        WeixinUserDTO weixinUserDTO = new WeixinUserDTO();
        if (wxMpUser != null) { 
            weixinUserDTO.setOpenid(wxMpUser.getOpenId());
            weixinUserDTO.setCity(wxMpUser.getCity());
            weixinUserDTO.setCountry(wxMpUser.getCountry());
            weixinUserDTO.setGroupid(wxMpUser.getGroupId());
            weixinUserDTO.setHeadimgurl(wxMpUser.getHeadImgUrl());
            weixinUserDTO.setLanguage(wxMpUser.getLanguage());
            weixinUserDTO.setNickname(wxMpUser.getNickname());
            weixinUserDTO.setProvince(wxMpUser.getProvince());
            weixinUserDTO.setRemark(wxMpUser.getRemark());
            weixinUserDTO.setSex(wxMpUser.getSexId());
            weixinUserDTO.setSubscribe(wxMpUser.getSubscribe());
            weixinUserDTO.setSubscribeTime(wxMpUser.getSubscribeTime() == null ? null : new Date(wxMpUser.getSubscribeTime()));
            weixinUserDTO.setTagidList(wxMpUser.getTagIds());
            weixinUserDTO.setUnionid(wxMpUser.getUnionId());
        }
        return weixinUserDTO;
    }
}
