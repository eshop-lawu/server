package com.lawu.eshop.jobs.service;

import com.lawu.eshop.activity.dto.HelpRedpacketAttendSendDTO;

/**
 * @author meishuquan
 * @date 2018/2/9.
 */
public interface SendRedpacketService {

    /**
     * 发送微信红包，更新红包发放记录表状态
     *
     * @param sendRecordId
     * @param mchBillno
     * @param sendDTO
     * @author meishuquan
     */
    void sendRedpacket(Long sendRecordId, String mchBillno, HelpRedpacketAttendSendDTO sendDTO);

}
