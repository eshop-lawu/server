package com.lawu.eshop.weixin.srv.converter;

import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.lawu.weixinapi.dto.RedpackResultDTO;
import com.lawu.weixinapi.dto.RedpackStatus;
import com.lawu.weixinapi.dto.param.RedpackParam;

/**
 * @author Leach
 * @date 2018/1/2
 */
public class RedpackConverter {

    public static WxPaySendRedpackRequest convert(RedpackParam param) {
        WxPaySendRedpackRequest wxPaySendRedpackRequest = new WxPaySendRedpackRequest();
        wxPaySendRedpackRequest.setMchBillNo(param.getMchBillno());
        wxPaySendRedpackRequest.setSendName(param.getSendName());
        wxPaySendRedpackRequest.setReOpenid(param.getOpenid());
        wxPaySendRedpackRequest.setTotalAmount(param.getTotalAmount());
        wxPaySendRedpackRequest.setTotalNum(param.getTotalNum());
        wxPaySendRedpackRequest.setWishing(param.getWishing());
        wxPaySendRedpackRequest.setActName(param.getActName());
        wxPaySendRedpackRequest.setRemark(param.getRemark());
        wxPaySendRedpackRequest.setSceneId(param.getSceneId());
        return wxPaySendRedpackRequest;
    }

    public static RedpackResultDTO convert(WxPaySendRedpackResult wxPaySendRedpackResult) {
        if (wxPaySendRedpackResult == null) {
            return null;
        }
        RedpackResultDTO redpackResult = new RedpackResultDTO();
        redpackResult.setReturnCode(wxPaySendRedpackResult.getReturnCode());
        redpackResult.setReturnMsg(wxPaySendRedpackResult.getReturnMsg());
        redpackResult.setResultCode(wxPaySendRedpackResult.getResultCode());
        redpackResult.setErrCode(wxPaySendRedpackResult.getErrCode());
        redpackResult.setSendListId(wxPaySendRedpackResult.getSendListid());
        redpackResult.setStatus(RedpackStatus.SENDING);
        return redpackResult;
    }

    public static RedpackResultDTO convert(WxPayRedpackQueryResult wxPayRedpackQueryResult) {
        if (wxPayRedpackQueryResult == null) {
            return null;
        }
        RedpackResultDTO redpackResult = new RedpackResultDTO();
        redpackResult.setReturnCode(wxPayRedpackQueryResult.getReturnCode());
        redpackResult.setReturnMsg(wxPayRedpackQueryResult.getReturnMsg());
        redpackResult.setResultCode(wxPayRedpackQueryResult.getResultCode());
        redpackResult.setErrCode(wxPayRedpackQueryResult.getErrCode());
        redpackResult.setSendListId(wxPayRedpackQueryResult.getDetailId());
        redpackResult.setStatus(RedpackStatus.valueOf(wxPayRedpackQueryResult.getStatus()));
        redpackResult.setRefundTime(wxPayRedpackQueryResult.getReceiveTime());
        redpackResult.setRefundTime(wxPayRedpackQueryResult.getRefundTime());
        return redpackResult;
    }
}
