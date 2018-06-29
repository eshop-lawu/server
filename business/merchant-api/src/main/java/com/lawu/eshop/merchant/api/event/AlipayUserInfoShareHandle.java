package com.lawu.eshop.merchant.api.event;

import com.lawu.eshop.merchant.api.service.AliUserInfoService;
import com.lawu.eshop.user.param.AliUserInfoDataParam;
import com.lawu.framework.core.event.AsyncEventHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlipayUserInfoShareHandle implements AsyncEventHandle<AlipayUserInfoShareEvent> {

    @Autowired
    private AliUserInfoService aliUserInfoService;

    @Override
    public void execute(AlipayUserInfoShareEvent alipayUserInfoShareEvent) {
        AliUserInfoDataParam aliUserInfoDataParam = new AliUserInfoDataParam();
        aliUserInfoDataParam.setUserNum(alipayUserInfoShareEvent.getUserNum());
        aliUserInfoDataParam.setUserTypeEnum(alipayUserInfoShareEvent.getUserTypeEnum());
        aliUserInfoDataParam.setAliUserInfoParam(alipayUserInfoShareEvent.getAliUserInfoParam());
        aliUserInfoService.save(aliUserInfoDataParam);
    }
}
