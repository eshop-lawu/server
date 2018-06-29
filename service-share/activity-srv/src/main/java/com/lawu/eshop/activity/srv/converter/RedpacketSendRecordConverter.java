package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.activity.dto.RedpacketMchBillnoDTO;
import com.lawu.eshop.activity.srv.bo.RedpacketSendRecordBO;
import com.lawu.eshop.activity.srv.domain.RedpacketSendRecordDO;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
public class RedpacketSendRecordConverter {

    public static RedpacketSendRecordBO converBO(RedpacketSendRecordDO sendRecordDO) {
        if (sendRecordDO == null) {
            return null;
        }

        RedpacketSendRecordBO sendRecordBO = new RedpacketSendRecordBO();
        sendRecordBO.setId(sendRecordDO.getId());
        sendRecordBO.setActivityId(sendRecordDO.getActivityId());
        sendRecordBO.setAttendDetailId(sendRecordDO.getAttendDetailId());
        sendRecordBO.setUserNum(sendRecordDO.getUserNum());
        sendRecordBO.setOpenid(sendRecordDO.getOpenid());
        sendRecordBO.setReturnCode(sendRecordDO.getReturnCode());
        sendRecordBO.setReturnMsg(sendRecordDO.getReturnMsg());
        sendRecordBO.setResultCode(sendRecordDO.getResultCode());
        sendRecordBO.setErrCodeDes(sendRecordDO.getErrCodeDes());
        sendRecordBO.setErrCodeDes(sendRecordDO.getErrCodeDes());
        sendRecordBO.setMchBillno(sendRecordDO.getMchBillno());
        sendRecordBO.setSendListId(sendRecordDO.getSendListId());
        sendRecordBO.setTotalAmount(sendRecordDO.getTotalAmount());
        sendRecordBO.setStatus(sendRecordDO.getStatus());
        sendRecordBO.setRcvTime(sendRecordDO.getRcvTime());
        sendRecordBO.setRefundTime(sendRecordDO.getRefundTime());
        sendRecordBO.setGmtModified(sendRecordDO.getGmtModified());
        sendRecordBO.setGmtCreate(sendRecordDO.getGmtCreate());
        return sendRecordBO;
    }

    public static List<RedpacketSendRecordBO> converBOS(List<RedpacketSendRecordDO> sendRecordDOS) {
        List<RedpacketSendRecordBO> sendRecordBOS = new ArrayList<>();
        if (sendRecordDOS == null || sendRecordDOS.isEmpty()) {
            return sendRecordBOS;
        }

        for (RedpacketSendRecordDO sendRecordDO : sendRecordDOS) {
            sendRecordBOS.add(converBO(sendRecordDO));
        }
        return sendRecordBOS;
    }

    public static List<RedpacketMchBillnoDTO> converRedpacketMchBillnoDTOS(List<RedpacketSendRecordBO> sendRecordBOS) {
        List<RedpacketMchBillnoDTO> mchBillnoDTOS = new ArrayList<>();
        if (sendRecordBOS == null || sendRecordBOS.isEmpty()) {
            return mchBillnoDTOS;
        }

        for (RedpacketSendRecordBO sendRecordBO : sendRecordBOS) {
            RedpacketMchBillnoDTO mchBillnoDTO = new RedpacketMchBillnoDTO();
            mchBillnoDTO.setMchBillno(sendRecordBO.getMchBillno());
            mchBillnoDTO.setAttendDetailId(sendRecordBO.getAttendDetailId());
            mchBillnoDTOS.add(mchBillnoDTO);
        }
        return mchBillnoDTOS;
    }

}
