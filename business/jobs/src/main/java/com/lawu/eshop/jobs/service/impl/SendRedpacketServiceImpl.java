package com.lawu.eshop.jobs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.dto.HelpRedpacketAttendSendDTO;
import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.PayService;
import com.lawu.eshop.jobs.service.RedpacketSendRecordService;
import com.lawu.eshop.jobs.service.SendRedpacketService;
import com.lawu.framework.web.Result;
import com.lawu.weixinapi.dto.RedpackResultDTO;
import com.lawu.weixinapi.dto.param.RedpackParam;

/**
 * @author meishuquan
 * @date 2018/2/9.
 */
@Service
public class SendRedpacketServiceImpl implements SendRedpacketService {

    @Autowired
    private PayService payService;

    @Autowired
    private RedpacketSendRecordService redpacketSendRecordService;

    @Override
    public void sendRedpacket(Long sendRecordId, String mchBillno, HelpRedpacketAttendSendDTO sendDTO) {
        RedpackParam param = new RedpackParam();
        param.setOpenid(sendDTO.getWxOpenid());
        param.setMchBillno(mchBillno);
        param.setSendName(sendDTO.getSendName());
        param.setTotalAmount(sendDTO.getFinalMoney());
        param.setTotalNum(1);
        param.setWishing(sendDTO.getWishing());
        param.setActName(sendDTO.getActName());
        param.setRemark(sendDTO.getRemark());
        // 抽奖
        param.setSceneId("PRODUCT_2");
        Result<RedpackResultDTO> result = payService.sendRedpack(param);
        if (ResultCode.SUCCESS != result.getRet() || result.getModel() == null) {
            return;
        }

        RedpackResultDTO resultDTO = result.getModel();
        RedpacketSendRecordParam sendRecordParam = new RedpacketSendRecordParam();
        sendRecordParam.setId(sendRecordId);
        sendRecordParam.setReturnCode(resultDTO.getReturnCode());
        sendRecordParam.setReturnMsg(resultDTO.getReturnMsg());
        sendRecordParam.setResultCode(resultDTO.getResultCode());
        sendRecordParam.setErrCode(resultDTO.getErrCode());
        sendRecordParam.setErrCodeDes(resultDTO.getErrCodeDes());
        sendRecordParam.setSendListId(resultDTO.getSendListId());
        redpacketSendRecordService.updateRedpacketSendRecordResult(sendRecordParam);
    }

}
