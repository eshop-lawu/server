package com.lawu.eshop.order.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.order.param.MerchantPayOrderListParam;
import com.lawu.eshop.order.param.OperatorPayOrderParam;
import com.lawu.eshop.order.param.PayOrderDataParam;
import com.lawu.eshop.order.param.PayOrderListParam;
import com.lawu.eshop.order.param.PayOrderReportDataParam;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.srv.bo.PayOrderBO;
import com.lawu.eshop.order.srv.bo.PayOrderBaseBO;
import com.lawu.eshop.order.srv.bo.ThirdPayCallBackQueryPayOrderBO;
import com.lawu.eshop.order.srv.constants.ExceptionMessageConstant;
import com.lawu.eshop.order.srv.converter.PayOrderConverter;
import com.lawu.eshop.order.srv.domain.PayOrderDO;
import com.lawu.eshop.order.srv.domain.PayOrderDOExample;
import com.lawu.eshop.order.srv.domain.extend.PayOrderExtendDOVew;
import com.lawu.eshop.order.srv.domain.extend.ReportFansSaleTransFormDO;
import com.lawu.eshop.order.srv.mapper.PayOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.extend.PayOrderExtendDOMapper;
import com.lawu.eshop.order.srv.service.PayOrderService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {
	@Autowired
	private PayOrderDOMapper payOrderDOMapper;

	@Autowired
	private PayOrderExtendDOMapper payOrderExtendDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayOrderBO savePayOrderInfo(Long memberId, PayOrderDataParam param, String numNum) {
        PayOrderDO payOrderDO = new PayOrderDO();
        payOrderDO.setMemberId(memberId);
        payOrderDO.setMemberNum(numNum);
        payOrderDO.setMerchantId(param.getMerchantId());
		if (param.getMerchantFavoredId() != null && param.getMerchantFavoredId() > 0) {
			payOrderDO.setActualAmount(BigDecimal.valueOf(param.getTotalAmount() - param.getFavoredAmount()));
			payOrderDO.setFavoredAmount(BigDecimal.valueOf(param.getFavoredAmount()));
			payOrderDO.setNotFavoredAmount(BigDecimal.valueOf(param.getNotFavoredAmount()));
		} else {
			payOrderDO.setActualAmount(BigDecimal.valueOf(param.getTotalAmount()));
			payOrderDO.setFavoredAmount(BigDecimal.ZERO);
			payOrderDO.setNotFavoredAmount(BigDecimal.ZERO);
		}
		payOrderDO.setTotalAmount(BigDecimal.valueOf(param.getTotalAmount()));
        payOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.PAY_ORDER));
        payOrderDO.setGmtCreate(new Date());
        payOrderDO.setGmtModified(new Date());
		payOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        payOrderDO.setMerchantNum(param.getMerchantNum());
        payOrderDO.setIsEvaluation(false);//未评
        payOrderDO.setStatus(PayOrderStatusEnum.STATUS_UNPAY.getVal());//待支付
		payOrderDO.setOrderStatus(true);
	    payOrderDO.setIsFans(param.getFans());
        payOrderDOMapper.insert(payOrderDO);
        PayOrderBO payOrderBO = new PayOrderBO();
        payOrderBO.setOrderNum(payOrderDO.getOrderNum());
        payOrderBO.setId(payOrderDO.getId());
        return payOrderBO;
    }

	@Override
	public Page<PayOrderBO> getpayOrderList(Long memberId, PayOrderListParam param) {
		PayOrderDOExample example = new PayOrderDOExample();
		if (param.getEvaluationEnum() == null) {
			example.createCriteria().andMemberIdEqualTo(memberId)
					.andStatusEqualTo(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal()).andOrderStatusEqualTo(true);
		} else {
			example.createCriteria().andMemberIdEqualTo(memberId).andIsEvaluationEqualTo(param.getEvaluationEnum().getVal())
					.andStatusEqualTo(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal()).andOrderStatusEqualTo(true);
		}
		example.setOrderByClause("id desc");
		// 分页
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		Page<PayOrderBO> page = new Page<>();
		page.setTotalCount(payOrderDOMapper.countByExample(example));
		page.setCurrentPage(param.getCurrentPage());

		List<PayOrderDO> payOrderDOS = payOrderDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		if (payOrderDOS == null) {
			return null;
		}
		List<PayOrderBO> payOrderBOS = new ArrayList<>();
		for (PayOrderDO payOrderDO : payOrderDOS) {
			PayOrderBO payOrderBO = PayOrderConverter.coverBO(payOrderDO);
			payOrderBOS.add(payOrderBO);
		}
		page.setRecords(payOrderBOS);
		return page;
	}

	/**
	 * 删除买单记录
	 *
	 * @param id 买单id
	 * @param memberId 会员id
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delPayOrderInfo(Long id, Long memberId) {
		PayOrderDO payOrderDO = payOrderDOMapper.selectByPrimaryKey(id);
		if (payOrderDO == null || !payOrderDO.getOrderStatus()) {
			throw new DataNotExistException(ExceptionMessageConstant.PAY_ORDER_DATA_DOES_NOT_EXIST);
		}
		if (!payOrderDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_PAY_ORDER);
		}
		PayOrderDO payOrderUpdateDO = new PayOrderDO();
		payOrderUpdateDO.setId(id);
		payOrderUpdateDO.setOrderStatus(false);
		payOrderDOMapper.updateByPrimaryKeySelective(payOrderUpdateDO);
	}

	@Override
	public ThirdPayCallBackQueryPayOrderBO selectThirdPayCallBackPayOrder(String orderId) {
		PayOrderDO payDO = payOrderDOMapper.selectByPrimaryKey(Long.valueOf(orderId));
		ThirdPayCallBackQueryPayOrderBO bo = new ThirdPayCallBackQueryPayOrderBO();
		bo.setActualMoney(payDO.getActualAmount().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		bo.setBusinessUserNum(payDO.getMerchantNum());
		bo.setPayOrderStatusEnum(PayOrderStatusEnum.getEnum(payDO.getStatus()));
		bo.setOrderNum(payDO.getOrderNum());
		return bo;
	}

	/**
	 * 查询未计算提成的买单
	 * 
	 * @return
	 * @author yangqh
	 */
	@Override
	public List<ShoppingOrderCommissionDTO> selectNotCommissionOrder(int offset, int pageSize) {
		PayOrderDOExample example = new PayOrderDOExample();
		example.createCriteria().andStatusEqualTo(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal())
				.andCommissionStatusEqualTo(CommissionStatusEnum.NOT_COUNTED.getValue());
		example.setOrderByClause(" id asc ");
		RowBounds rowBounds = new RowBounds(offset,pageSize);
		List<PayOrderDO> dos = payOrderDOMapper.selectByExampleWithRowbounds(example,rowBounds);
		List<ShoppingOrderCommissionDTO> dtos = new ArrayList<ShoppingOrderCommissionDTO>();
		for(PayOrderDO orderDO : dos){
			ShoppingOrderCommissionDTO dto = new ShoppingOrderCommissionDTO();
			dto.setId(orderDO.getId());
			dto.setMemberNum(orderDO.getMemberNum());
			dto.setMerchantNum(orderDO.getMerchantNum());
			dto.setActualAmount(orderDO.getActualAmount());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public int updateCommissionStatus(List<Long> ids) {
		PayOrderDOExample example = new PayOrderDOExample();
		example.createCriteria().andIdIn(ids);
		
		PayOrderDO payOrder = new PayOrderDO();
		payOrder.setGmtCommission(new Date());
		payOrder.setCommissionStatus(CommissionStatusEnum.CALCULATED.getValue());
		payOrderDOMapper.updateByExampleSelective(payOrder, example);
		
		return ResultCode.SUCCESS;
	}

	@Override
	public Page<PayOrderBO> getMerchantPayOrderList(Long userId, MerchantPayOrderListParam param) {
		PayOrderDOExample example = new PayOrderDOExample();
		example.createCriteria().andMerchantIdEqualTo(userId).andStatusEqualTo(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal());
		example.setOrderByClause("id desc");

		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		Page<PayOrderBO> page = new Page<>();
		page.setTotalCount(payOrderDOMapper.countByExample(example));
		page.setCurrentPage(param.getCurrentPage());

		List<PayOrderDO> payOrderDOS = payOrderDOMapper.selectByExampleWithRowbounds(example, rowBounds);

		List<PayOrderBO> payOrderBOS = PayOrderConverter.coverBOS(payOrderDOS);

		page.setRecords(payOrderBOS);
		return page;
	}

    /**
     * 获取买单记录
     *
     * @param id 买单id
     * @param memberId 会员id
     * @return
     * @author jiangxinjun
     * @date 2017年7月11日
     */
	@Override
	public PayOrderBO getOrderInfo(Long id, Long memberId) {
		PayOrderDO payOrderDO = payOrderDOMapper.selectByPrimaryKey(id);
		if (payOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.PAY_ORDER_DATA_DOES_NOT_EXIST);
		}
		if (memberId != null && !payOrderDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_PAY_ORDER);
		}
		return  PayOrderConverter.coverBO(payOrderDO);
	}

	@Override
	public Page<PayOrderBO> getOperatorPayOrderList(OperatorPayOrderParam param) {

		PayOrderDOExample example = new PayOrderDOExample();
		PayOrderDOExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal());
		if (param.getMerchantId() != null) {
			criteria.andMerchantIdEqualTo(param.getMerchantId());
		}
		if (param.getMemberId() != null) {
			criteria.andMemberIdEqualTo(param.getMemberId());
		}
		if (StringUtils.isNotEmpty(param.getBeginDate())) {
			criteria.andGmtCreateGreaterThanOrEqualTo(DateUtil.stringToDate(param.getBeginDate() + " 00:00:00"));
		}
		if (StringUtils.isNotEmpty(param.getEndDate())) {
			criteria.andGmtCreateLessThanOrEqualTo(DateUtil.stringToDate(param.getEndDate() + " 23:59:59"));
		}
		if (StringUtils.isNotEmpty(param.getOrderNum())) {
			criteria.andOrderNumEqualTo(param.getOrderNum());
		}
		example.setOrderByClause("id desc");

		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		Page<PayOrderBO> page = new Page<>();
		page.setTotalCount(payOrderDOMapper.countByExample(example));
		page.setCurrentPage(param.getCurrentPage());

		List<PayOrderDO> payOrderDOS = payOrderDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<PayOrderBO> payOrderBOS = PayOrderConverter.coverBOS(payOrderDOS);
		page.setRecords(payOrderBOS);
		return page;
	}

	@Override
	public List<PayOrderBO> getAutoCommentPayOrderList() {

		List<PayOrderExtendDOVew> list = payOrderExtendDOMapper.getAutoCommentPayOrderList(new Date());
		List<PayOrderBO> payOrderBOS = PayOrderConverter.coverBOSWithDOVews(list);
		return payOrderBOS;
	}

	@Override
	public List<ReportRiseRerouceDTO> fansSaleTransformPay(ReportDataParam dparam) {
		PayOrderReportDataParam param = new PayOrderReportDataParam();
		param.setMerchantId(dparam.getMerchantId());
		List<ReportFansSaleTransFormDO> reportFansSaleTransFormDOList = payOrderExtendDOMapper.selectByFansSaleTransformPay(param);
		return PayOrderConverter.convertReportRiseRerouceDTOList(reportFansSaleTransFormDOList);
	}

	@Override
	public PayOrderBaseBO getPayOrderById(String id) {
		PayOrderDO pdo = payOrderDOMapper.selectByPrimaryKey(Long.parseLong(id));
		PayOrderBaseBO bo = new PayOrderBaseBO();
		bo.setMemberId(pdo.getMemberId());
		bo.setMerchantId(pdo.getMerchantId());
		return bo;
	}
}
