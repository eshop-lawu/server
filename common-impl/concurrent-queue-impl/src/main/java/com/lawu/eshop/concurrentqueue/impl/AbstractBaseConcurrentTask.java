package com.lawu.eshop.concurrentqueue.impl;

import com.lawu.concurrentqueue.requestctrl.ConcurrentTask;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.web.Result;


/**
 * 基础并发线程任务
 * 
 * @author jiangxinjun
 * @createDate 2017年12月18日
 * @updateDate 2017年12月18日
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractBaseConcurrentTask<T extends Result, V> implements ConcurrentTask<T, V> {

    @SuppressWarnings("unchecked")
    @Override
    public T executeWhenRejected() {
        Result rtn = new Result();
        rtn.setRet(ResultCode.QUEUE_FAILED);
        rtn.setMsg(ResultCode.get(rtn.getRet()));
        return (T) rtn;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T executeWhenException() {
        Result rtn = new Result();
        rtn.setRet(ResultCode.QUEUE_FAILED);
        rtn.setMsg(ResultCode.get(rtn.getRet()));
        return (T) rtn;
    }

}
