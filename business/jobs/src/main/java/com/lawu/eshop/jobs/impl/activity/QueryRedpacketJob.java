package com.lawu.eshop.jobs.impl.activity;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.activity.constants.RedpacketSendRecordStatusEnum;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendSendDTO;
import com.lawu.eshop.activity.dto.RedpacketMchBillnoDTO;
import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.HelpRedpacketAttendDetailService;
import com.lawu.eshop.jobs.service.PayService;
import com.lawu.eshop.jobs.service.RedpacketSendRecordService;
import com.lawu.eshop.jobs.service.SendRedpacketService;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageJob;
import com.lawu.utils.DateUtil;
import com.lawu.weixinapi.dto.RedpackResultDTO;
import com.lawu.weixinapi.dto.RedpackStatus;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
public class QueryRedpacketJob extends AbstractPageJob<RedpacketMchBillnoDTO> {

    @Autowired
    private RedpacketSendRecordService redpacketSendRecordService;

    @Autowired
    private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;

    @Autowired
    private PayService payService;

    @Autowired
    private SendRedpacketService sendRedpacketService;

    @Override
    public void executeSingle(RedpacketMchBillnoDTO redpacketMchBillnoDTO) {
        Result<RedpackResultDTO> result = payService.queryRedpack(redpacketMchBillnoDTO.getMchBillno());
        if (ResultCode.SUCCESS != result.getRet()) {
            return;
        }
        if (result.getModel() == null) {
            Result<HelpRedpacketAttendSendDTO> sendDTOResult = helpRedpacketAttendDetailService.getSendRedpacketAttendDetail(redpacketMchBillnoDTO.getAttendDetailId(), redpacketMchBillnoDTO.getMchBillno());
            if (ResultCode.SUCCESS != sendDTOResult.getRet() || sendDTOResult.getModel().getSendRecordId() == null) {
                return;
            }
            sendRedpacketService.sendRedpacket(sendDTOResult.getModel().getSendRecordId(), redpacketMchBillnoDTO.getMchBillno(), sendDTOResult.getModel());
            return;
        }

        RedpackResultDTO resultDTO = result.getModel();
        RedpacketSendRecordParam param = new RedpacketSendRecordParam();
        param.setMchBillno(redpacketMchBillnoDTO.getMchBillno());
        param.setAttendDetailId(redpacketMchBillnoDTO.getAttendDetailId());
        param.setRcvTime(StringUtils.isEmpty(resultDTO.getRcvTime()) ? null : DateUtil.getDateTimeFormat((resultDTO.getRcvTime())));
        param.setRefundTime(StringUtils.isEmpty(resultDTO.getRefundTime()) ? null : DateUtil.getDateTimeFormat((resultDTO.getRefundTime())));
        RedpackStatus redpackStatus = resultDTO.getStatus();
        if (RedpackStatus.SENDING.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.SENDING);
        } else if (RedpackStatus.SENT.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.SENT);
        } else if (RedpackStatus.FAILED.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.FAILED);
        } else if (RedpackStatus.RECEIVED.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.RECEIVED);
        } else if (RedpackStatus.RFUND_ING.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.RFUND_ING);
        } else if (RedpackStatus.REFUND.equals(redpackStatus)) {
            param.setStatusEnum(RedpacketSendRecordStatusEnum.REFUND);
        } else {
            return;
        }
        redpacketSendRecordService.updateRedpacketSendRecordStatus(param);
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
    public List<RedpacketMchBillnoDTO> queryPage(int offset, int pageSize) {
        Result<List<RedpacketMchBillnoDTO>> result = redpacketSendRecordService.listSendRedpacketMchBillno(offset, pageSize);
        return result.getModel();
    }

}
