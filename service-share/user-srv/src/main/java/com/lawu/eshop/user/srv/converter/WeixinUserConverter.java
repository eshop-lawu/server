package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.WeixinUserInfoDTO;
import com.lawu.eshop.user.srv.bo.WeixinUserInfoBO;
import com.lawu.eshop.user.srv.domain.WeixinUserDO;

/**
 * 微信用户实体转换
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
public class WeixinUserConverter {
	
	public static WeixinUserInfoBO WeixinUserConverterBO(WeixinUserDO weixinUserDO){
		if(weixinUserDO == null){
			return null;
		}
		WeixinUserInfoBO weixinUserInfoBO = new WeixinUserInfoBO();
		weixinUserInfoBO.setHeadimg(weixinUserDO.getHeadimgurl());
		weixinUserInfoBO.setNickname(weixinUserDO.getNickname());
		weixinUserInfoBO.setWxOpenid(weixinUserDO.getOpenid());
		
		return weixinUserInfoBO;
	}
	
	public static WeixinUserInfoDTO WeixinUserConverterDTO(WeixinUserInfoBO weixinUserInfoBO){
		if(weixinUserInfoBO == null){
			return null;
		}
		WeixinUserInfoDTO weixinUserInfoDTO = new WeixinUserInfoDTO();
		weixinUserInfoDTO.setHeadimg(weixinUserInfoBO.getHeadimg());
		weixinUserInfoDTO.setNickname(weixinUserInfoBO.getNickname());
		weixinUserInfoDTO.setWxOpenid(weixinUserInfoBO.getWxOpenid());
		weixinUserInfoDTO.setAccount(weixinUserInfoBO.getAccount());
		
		return weixinUserInfoDTO;
	}

}
