package com.lawu.eshop.member.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.concurrentqueue.impl.AbstractBaseBusinessDecisionService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.member.api.service.SeckillActivityProductService;
import com.lawu.framework.web.Result;

/**
 * 
 * 
 * @author jiangxinjun
 * @createDate 2017年11月30日
 * @updateDate 2017年11月30日
 */
@SuppressWarnings("rawtypes")
@Service("seckillActivityProductBusinessDecisionServiceImpl")
public class SeckillActivityProductBusinessDecisionServiceImpl extends AbstractBaseBusinessDecisionService {
    
    @Autowired
    private SeckillActivityProductService seckillActivityProductService;
    
    @Override
    public Integer queryInventory(Object id) {
        Result<Integer> result = seckillActivityProductService.getInventory((Long) id);
        if (result.getRet() != ResultCode.SUCCESS) {
            return -1;
        }
        return result.getModel();
    }

    @Override
    public Result sellOut() {
        Result rs = new Result();
        rs.setRet(ResultCode.INVENTORY_SHORTAGE);
        rs.setMsg(ResultCode.get(rs.getRet()));
        return rs;
    }

}
