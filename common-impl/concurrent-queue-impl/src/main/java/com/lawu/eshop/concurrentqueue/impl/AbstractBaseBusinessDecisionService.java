package com.lawu.eshop.concurrentqueue.impl;

import com.lawu.concurrentqueue.bizctrl.AbstractBusinessDecisionService;
import com.lawu.concurrentqueue.bizctrl.BusinessExecuteException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.web.Result;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年2月26日
 * @updateDate 2018年2月26日
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractBaseBusinessDecisionService extends AbstractBusinessDecisionService<Result> {

    @Override
    public Result fail(BusinessExecuteException e) {
        Result rs = new Result();
        if (e.getRet() != 0) {
            rs.setRet(e.getRet());
            rs.setMsg(e.getMsg());
        } else {
            rs.setRet(ResultCode.QUEUE_FAILED);
            rs.setMsg(ResultCode.get(rs.getRet()));
        }
        return rs;
    }

}
