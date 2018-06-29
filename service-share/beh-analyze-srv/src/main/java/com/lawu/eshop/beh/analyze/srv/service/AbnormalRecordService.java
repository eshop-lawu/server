package com.lawu.eshop.beh.analyze.srv.service;

import com.lawu.eshop.beh.analyze.param.AbnormalAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalParam;
import com.lawu.eshop.beh.analyze.srv.bo.AbnormalBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
public interface AbnormalRecordService {
    Page<AbnormalBO> getAbnormalRecord( AbnormalParam param);

    void editAbnormalRecord(AbnormalJobAddParam param);

    void addOrUpdateAbnormalRecord(AbnormalAddParam param);

    void addActiveAbnormalInfo(AbnormalJobAddParam addParam);
}
