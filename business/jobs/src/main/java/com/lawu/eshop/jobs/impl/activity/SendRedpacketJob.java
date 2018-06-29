package com.lawu.eshop.jobs.impl.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.activity.constants.RedpacketSendRecordStatusEnum;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendSendDTO;
import com.lawu.eshop.activity.dto.RedpacketSendRecordDTO;
import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.jobs.service.HelpRedpacketAttendDetailService;
import com.lawu.eshop.jobs.service.RedpacketSendRecordService;
import com.lawu.eshop.jobs.service.SendRedpacketService;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
public class SendRedpacketJob extends AbstractPageJob<HelpRedpacketAttendSendDTO> {

    private static int executeCount = 0;

    @Autowired
    private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;

    @Autowired
    private RedpacketSendRecordService redpacketSendRecordService;

    @Autowired
    private SendRedpacketService sendRedpacketService;

    @Override
    public void executeSingle(HelpRedpacketAttendSendDTO attendSendDTO) {
        String mchBillno = IdWorkerHelperImpl.generate(BizIdType.USER_REDPACKET);
        RedpacketSendRecordParam sendRecordParam = new RedpacketSendRecordParam();
        sendRecordParam.setActivityId(attendSendDTO.getActivityId());
        sendRecordParam.setAttendDetailId(attendSendDTO.getId());
        sendRecordParam.setUserNum(attendSendDTO.getUserNum());
        sendRecordParam.setOpenid(attendSendDTO.getWxOpenid());
        sendRecordParam.setMchBillno(mchBillno);
        sendRecordParam.setTotalAmount(attendSendDTO.getFinalMoney());
        sendRecordParam.setStatusEnum(RedpacketSendRecordStatusEnum.SENDING);
        Result<RedpacketSendRecordDTO> recordDTOResult = redpacketSendRecordService.saveRedpacketSendRecord(sendRecordParam);
        if (recordDTOResult.getModel() == null || recordDTOResult.getModel().getId() == null) {
            return;
        }
        sendRedpacketService.sendRedpacket(recordDTOResult.getModel().getId(), mchBillno, attendSendDTO);
    }

    @Override
    public boolean isStatusData() {
        return true;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }

    @Override
    public List<HelpRedpacketAttendSendDTO> queryPage(int offset, int pageSize) {
        if (executeCount > 0) {
            executeCount = 0;
            return null;
        }
        executeCount++;
        Result<List<HelpRedpacketAttendSendDTO>> result = helpRedpacketAttendDetailService.listSendRedpacketAttendDetail(offset, pageSize);
        return result.getModel();
    }

}
