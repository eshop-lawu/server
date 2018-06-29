package com.lawu.eshop.order.srv.converter;

import com.lawu.eshop.order.constants.EvaluationEnum;
import com.lawu.eshop.order.dto.*;
import com.lawu.eshop.order.srv.bo.PayOrderBO;
import com.lawu.eshop.order.srv.domain.PayOrderDO;
import com.lawu.eshop.order.srv.domain.extend.PayOrderExtendDOVew;
import com.lawu.eshop.order.srv.domain.extend.ReportFansSaleTransFormDO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public class PayOrderConverter {

	/**
	 * 隐藏默认的构造器
	 */
	private PayOrderConverter() {
		throw new IllegalAccessError("Utility class");
	}

    public static PayOrderBO coverBO(PayOrderDO payOrderDO) {
        if (payOrderDO == null) {
            return null;
        }
        PayOrderBO payOrderBO = new PayOrderBO();
        payOrderBO.setOrderNum(payOrderDO.getOrderNum());
        payOrderBO.setId(payOrderDO.getId());
        payOrderBO.setActualAmount(payOrderDO.getActualAmount());
        payOrderBO.setGmtCreate(payOrderDO.getGmtCreate());
        payOrderBO.setMemberId(payOrderDO.getMemberId());
        payOrderBO.setMerchantId(payOrderDO.getMerchantId());
        payOrderBO.setCommentTime(payOrderDO.getCommentTime());
        payOrderBO.setPayType(payOrderDO.getPayType());
        payOrderBO.setEvaluation(payOrderDO.getIsEvaluation());
        payOrderBO.setFavoredAmount(payOrderDO.getFavoredAmount());
        payOrderBO.setTotalAmount(payOrderDO.getTotalAmount());
        return payOrderBO;
    }

    public static PayOrderDTO coverDTO(PayOrderBO payOrderBO) {
    	PayOrderDTO rtn ;
        if (payOrderBO == null) {
            return null;
        }
        rtn = new PayOrderDTO();
        rtn.setId(payOrderBO.getId());
        rtn.setTotalAmount(payOrderBO.getTotalAmount());
        rtn.setActualAmount(payOrderBO.getActualAmount());
        rtn.setEvaluationEnum(EvaluationEnum.getEnum(payOrderBO.getEvaluation()));
        rtn.setFavoredAmount(payOrderBO.getFavoredAmount());
        rtn.setGmtCreate(payOrderBO.getGmtCreate());
        rtn.setMerchantId(payOrderBO.getMerchantId());
        return rtn;
    }

    public static List<PayOrderBO> coverBOS(List<PayOrderDO> payOrderDOS) {
        if (payOrderDOS == null || payOrderDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<PayOrderBO> payOrderBOS = new ArrayList<>();
        for (PayOrderDO payOrderDO : payOrderDOS) {
            payOrderBOS.add(coverBO(payOrderDO));
        }
        return payOrderBOS;
    }

    public static List<MerchantPayOrderListDTO> coverDTOS(List<PayOrderBO> payOrderBOS) {
        if(payOrderBOS == null){
            return  new ArrayList<>();
        }
        List<MerchantPayOrderListDTO> payOrderListDTOS = new ArrayList<>();
        MerchantPayOrderListDTO merchantPayOrderListDTO ;
        for(PayOrderBO payOrderBO :payOrderBOS){
            merchantPayOrderListDTO = new MerchantPayOrderListDTO();
            merchantPayOrderListDTO.setOrderNum(payOrderBO.getOrderNum());
            merchantPayOrderListDTO.setGmtCreate(payOrderBO.getGmtCreate());
            merchantPayOrderListDTO.setActualAmount(payOrderBO.getActualAmount());
            payOrderListDTOS.add(merchantPayOrderListDTO);
        }
        return payOrderListDTOS;
    }

    public static MemberPayOrderInfoDTO coverOrderInfoDTO(PayOrderBO payOrderBO) {
        if(payOrderBO == null){
            return  null;
        }
        MemberPayOrderInfoDTO infoDTO = new MemberPayOrderInfoDTO();
        infoDTO.setId(payOrderBO.getId());
        infoDTO.setMerchantId(payOrderBO.getMerchantId());
        infoDTO.setActualAmount(payOrderBO.getActualAmount());
        infoDTO.setEvaluationEnum(EvaluationEnum.getEnum(payOrderBO.getEvaluation()));
        infoDTO.setFavoredAmount(payOrderBO.getFavoredAmount());
        infoDTO.setGmtCreate(payOrderBO.getGmtCreate());
        infoDTO.setOrderNum(payOrderBO.getOrderNum());
        infoDTO.setTotalAmount(payOrderBO.getTotalAmount());
        return  infoDTO;
    }

    public static List<OperatorPayOrderListDTO> coverOperatorPayOrderListDTOS(List<PayOrderBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<OperatorPayOrderListDTO> payOrderListDTOS = new ArrayList<>();
        OperatorPayOrderListDTO payOrderListDTO = null;
        for (PayOrderBO payOrderBO : records) {
            payOrderListDTO = new OperatorPayOrderListDTO();
            payOrderListDTO.setId(payOrderBO.getId());
            payOrderListDTO.setOrderNum(payOrderBO.getOrderNum());
            payOrderListDTO.setActualAmount(payOrderBO.getActualAmount());
            payOrderListDTO.setGmtCreate(payOrderBO.getGmtCreate());
            payOrderListDTO.setMerchantId(payOrderBO.getMerchantId());
            payOrderListDTO.setMemberId(payOrderBO.getMemberId());
            payOrderListDTOS.add(payOrderListDTO);
        }
        return payOrderListDTOS;
    }

    public static List<PayOrderBO> coverBOSWithDOVews(List<PayOrderExtendDOVew> list) {
	    if(list.isEmpty()){
	        return new ArrayList<>();
        }
        List<PayOrderBO> payOrderBOS = new ArrayList<>();
        PayOrderBO payOrderBO;
        for(PayOrderExtendDOVew payOrderExtendDOVew :list){
            payOrderBO = new PayOrderBO();
            payOrderBO.setId(payOrderExtendDOVew.getId());
            payOrderBO.setMerchantId(payOrderExtendDOVew.getMerchantId());
            payOrderBO.setMemberId(payOrderExtendDOVew.getMemberId());
            payOrderBO.setActualAmount(payOrderExtendDOVew.getActualAmount());
            payOrderBOS.add(payOrderBO);
        }
        return payOrderBOS;
    }

    public static List<PayOrderAutoCommentDTO> coverAutoCommentDTOS(List<PayOrderBO> payOrderBOS) {
        if (payOrderBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<PayOrderAutoCommentDTO> payOrderAutoCommentDTOS = new ArrayList<>();
        PayOrderAutoCommentDTO payOrderAutoCommentDTO;
        for (PayOrderBO payOrderBO : payOrderBOS) {
            payOrderAutoCommentDTO = new PayOrderAutoCommentDTO();
            payOrderAutoCommentDTO.setId(payOrderBO.getId());
            payOrderAutoCommentDTO.setMerchantId(payOrderBO.getMerchantId());
            payOrderAutoCommentDTO.setMemberId(payOrderBO.getMemberId());
            payOrderAutoCommentDTO.setActualAmount(payOrderBO.getActualAmount());
            payOrderAutoCommentDTOS.add(payOrderAutoCommentDTO);
        }
        return payOrderAutoCommentDTOS;
    }

    public static List<ReportRiseRerouceDTO> convertReportRiseRerouceDTOList(List<ReportFansSaleTransFormDO> list) {
        List<ReportRiseRerouceDTO> rtn = new ArrayList<>();

        Map<String, ReportFansSaleTransFormDO> reportFansSaleTransFormDOMap = new HashMap<>();
        for (ReportFansSaleTransFormDO item : list) {
            reportFansSaleTransFormDOMap.put(item.getIsFans(), item);
        }

        // 粉丝订单数量
        ReportRiseRerouceDTO reportRiseRerouceDTO = new ReportRiseRerouceDTO();
        reportRiseRerouceDTO.setName("粉丝买单消费");
        ReportFansSaleTransFormDO reportFansSaleTransFormDO = reportFansSaleTransFormDOMap.get("1");
        reportRiseRerouceDTO.setValue(reportFansSaleTransFormDO == null ? "0" : reportFansSaleTransFormDO.getCount().toString());
        rtn.add(reportRiseRerouceDTO);

        // 非粉丝订单数量
        reportRiseRerouceDTO = new ReportRiseRerouceDTO();
        reportRiseRerouceDTO.setName("非粉丝买单消费");
        reportFansSaleTransFormDO = reportFansSaleTransFormDOMap.get("0");
        reportRiseRerouceDTO.setValue(reportFansSaleTransFormDO == null ? "0" : reportFansSaleTransFormDO.getCount().toString());
        rtn.add(reportRiseRerouceDTO);

        return rtn;
    }
}
