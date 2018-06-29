package com.lawu.eshop.member.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.concurrentqueue.impl.AbstractBaseBusinessDecisionService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.member.api.service.AdService;
import com.lawu.framework.web.Result;

/**
 * 
 * 
 * @Description
 * @author zhangrc
 * @date 2017年12月8日
 */
@SuppressWarnings("rawtypes")
@Service("adBusinessDecisionServiceImpl")
public class AdBusinessDecisionServiceImpl extends AbstractBaseBusinessDecisionService {
    
    @Autowired
    private AdService adService;
    
    @Override
    public Integer queryInventory(Object id) {
        Result<Integer> result = adService.getInventory((Long) id);
        if (result.getRet() != ResultCode.SUCCESS) {
            return -1;
        }
        return result.getModel();
    }

    @Override
    public Result sellOut() {
        Result rs = new Result();
        rs.setRet(ResultCode.AD_CLICK_PUTED);
        rs.setMsg(ResultCode.get(rs.getRet()));
        return rs;
    }
    
}
