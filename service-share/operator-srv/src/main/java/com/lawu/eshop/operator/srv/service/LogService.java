package com.lawu.eshop.operator.srv.service;

import com.lawu.eshop.operator.param.ListLogParam;
import com.lawu.eshop.operator.param.LogParam;
import com.lawu.eshop.operator.srv.bo.LogBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public interface LogService {

    /**
     * 保存日志
     *
     * @param logParam
     */
    void saveLog(LogParam logParam);

    /**
     * 根据ID查询日志
     *
     * @param id
     * @return
     */
    LogBO getLogById(Long id);

    /**
     * 查询日志列表
     *
     * @param listLogParam
     * @return
     */
    Page<LogBO> listLog(ListLogParam listLogParam);
}
