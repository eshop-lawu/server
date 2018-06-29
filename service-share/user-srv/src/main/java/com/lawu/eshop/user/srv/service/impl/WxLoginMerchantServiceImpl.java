package com.lawu.eshop.user.srv.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.user.param.WxLoginMerchantParam;
import com.lawu.eshop.user.srv.domain.WxLoginMerchantDO;
import com.lawu.eshop.user.srv.domain.WxLoginMerchantDOExample;
import com.lawu.eshop.user.srv.mapper.WxLoginMerchantDOMapper;
import com.lawu.eshop.user.srv.service.WxLoginMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WxLoginMerchantServiceImpl implements WxLoginMerchantService {

    @Autowired
    private WxLoginMerchantDOMapper wxLoginMerchantDOMapper;

    @Override
    public void wxLoginMerchantSave(WxLoginMerchantParam param) {
        WxLoginMerchantDOExample example = new WxLoginMerchantDOExample();
        example.createCriteria().andOpenidEqualTo(param.getOpenid());

        WxLoginMerchantDO weixinUserDO = new WxLoginMerchantDO();
        weixinUserDO.setCity(param.getCity());
        weixinUserDO.setCountry(param.getCountry());
        if (param.getGroupid() != null) {
            weixinUserDO.setGroupid(Long.parseLong(param.getGroupid().toString()));
        }
        weixinUserDO.setHeadimgurl(param.getHeadimgurl());
        weixinUserDO.setLanguage(param.getLanguage());
        weixinUserDO.setNickname(param.getNickname());
        weixinUserDO.setProvince(param.getProvince());
        weixinUserDO.setRemark(param.getRemark());
        weixinUserDO.setSex(param.getSex());
        if (param.getSubscribe() != null && param.getSubscribe()) {
            weixinUserDO.setSubscribe(1);
        } else {
            weixinUserDO.setSubscribe(0);
        }
        weixinUserDO.setSubscribeTime(param.getSubscribeTime());
        if (param.getTagidList() != null && param.getTagidList().length > 0) {
            weixinUserDO.setTagidList(JSONObject.toJSONString(param.getTagidList()));
        }
        weixinUserDO.setUnionid(param.getUnionid());
        if (wxLoginMerchantDOMapper.countByExample(example) > 0) {
            wxLoginMerchantDOMapper.updateByExampleSelective(weixinUserDO, example);
            return;
        }
        weixinUserDO.setOpenid(param.getOpenid());
        weixinUserDO.setGmtCreate(new Date());
        wxLoginMerchantDOMapper.insertSelective(weixinUserDO);
    }

}
