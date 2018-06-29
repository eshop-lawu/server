package com.lawu.eshop.member.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.member.api.service.CommentMerchantService;
import com.lawu.eshop.member.api.service.FansMerchantService;
import com.lawu.eshop.member.api.service.MerchantFavoredService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.PayOrderService;
import com.lawu.eshop.order.constants.EvaluationEnum;
import com.lawu.eshop.order.dto.MemberPayOrderInfoDTO;
import com.lawu.eshop.order.dto.PayOrderDTO;
import com.lawu.eshop.order.dto.PayOrderIdDTO;
import com.lawu.eshop.order.dto.PayOrderInfoDTO;
import com.lawu.eshop.order.param.PayOrderDataParam;
import com.lawu.eshop.order.param.PayOrderListParam;
import com.lawu.eshop.order.param.PayOrderParam;
import com.lawu.eshop.user.dto.PayOrderMerchantStoreInfoDTO;
import com.lawu.eshop.user.dto.PayOrderStoreInfoDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
@Api(tags = "payOrder")
@RestController
@RequestMapping(value = "payOrder/")
public class PayOrderController extends BaseController {

	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private MerchantStoreService merchantStoreService;

	@Autowired
	private MerchantFavoredService merchantFavoredService;

	@Autowired
	private CommentMerchantService commentMerchantService;
	@Autowired
	private FansMerchantService fansMerchantService;

    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "新增买单记录", notes = "新增买单记录  [1004,1005,1000] （章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "savePayOrderInfo", method = RequestMethod.POST)
    public Result<PayOrderIdDTO> savePayOrderInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                  @ModelAttribute PayOrderParam param) {
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        //查询优惠信息记录
        double realFavoredAmount = 0;//实际优惠金额
        double canFavoredAmount = param.getTotalAmount()-param.getNotFavoredAmount();//参与优惠金额
        if(param.getMerchantFavoredId() !=null && param.getMerchantFavoredId() >0){
            Result<MerchantFavoredDTO> favoredDTOResult = merchantFavoredService.findFavoredById(param.getMerchantFavoredId());
            if(favoredDTOResult.getModel() !=null){
                if(favoredDTOResult.getModel().getTypeEnum().val == MerchantFavoredTypeEnum.TYPE_FULL.val.byteValue()){
                    //每满xxx减xxx
                    int m =  (int)(canFavoredAmount/(favoredDTOResult.getModel().getReachAmount().doubleValue()));
                    realFavoredAmount =  m*favoredDTOResult.getModel().getFavoredAmount().doubleValue();
                } else if (favoredDTOResult.getModel().getTypeEnum().val == MerchantFavoredTypeEnum.TYPE_FULL_REDUCE.val.byteValue()
                        && (canFavoredAmount - favoredDTOResult.getModel().getReachAmount().doubleValue()) >= 0) {
                    //满xxx减xxx
                    realFavoredAmount = favoredDTOResult.getModel().getFavoredAmount().doubleValue();
                }else if(favoredDTOResult.getModel().getTypeEnum().val == MerchantFavoredTypeEnum.TYPE_DISCOUNT.val.byteValue()){
                    realFavoredAmount = canFavoredAmount*((BigDecimal.TEN.subtract(favoredDTOResult.getModel().getDiscountRate()).doubleValue())/10);
                }
                BigDecimal realFavoredAmount2 =   BigDecimal.valueOf(realFavoredAmount);
                double rfa2  =  realFavoredAmount2.setScale(2,   BigDecimal.ROUND_DOWN).doubleValue();
                if(Double.doubleToLongBits(rfa2) != Double.doubleToLongBits(param.getFavoredAmount())){
                    return successCreated(ResultCode.PAY_ORDER_FAVORED_AMOUNT_UNEQUAL);
                }
            }
        }

        //查询该用户是否为该商家粉丝
	    Result<Boolean> isFans = fansMerchantService.isFansMerchant(param.getMerchantId(),memberId);
	    PayOrderDataParam dParam = new PayOrderDataParam();
	    dParam.setMerchantId(param.getMerchantId());
	    dParam.setTotalAmount(param.getTotalAmount());
	    dParam.setNotFavoredAmount(param.getNotFavoredAmount());
	    dParam.setFavoredAmount(param.getFavoredAmount());
	    dParam.setMerchantNum(param.getMerchantNum());
	    dParam.setMerchantFavoredId(param.getMerchantFavoredId());
	    dParam.setFans(isFans.getModel());

        return  payOrderService.savePayOrderInfo(memberId, dParam,userNum);
    }

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@ApiOperation(value = "买单记录列表", notes = "买单记录列表  [1004,1002,1000] （章勇）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getpayOrderList", method = RequestMethod.POST)
	public Result<Page<PayOrderInfoDTO>> getpayOrderList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute PayOrderListParam param) {

		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<Page<PayOrderDTO>> pageResult = payOrderService.getpayOrderList(memberId, param);
		if (pageResult == null || pageResult.getModel() == null) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}
		List<PayOrderInfoDTO> payOrderInfoDTOS = new ArrayList<>();
		List<PayOrderDTO> payOrderDTOS = pageResult.getModel().getRecords();
		if (payOrderDTOS.isEmpty()) {
			Page<PayOrderInfoDTO> list = new Page<>();
			list.setRecords(new ArrayList<PayOrderInfoDTO>());
			list.setTotalCount(0);
			list.setCurrentPage(1);
			return successGet(list);
		}
		// 把要查询的id放入list,统一一次性查询
		Set<Long> merchantIds = new HashSet<>();
		for (PayOrderDTO payOrderDTO : payOrderDTOS) {
			merchantIds.add(payOrderDTO.getMerchantId());
		}

		// 查询商家信息
		Result<List<PayOrderStoreInfoDTO>> storeInfoDTOResult = merchantStoreService.getPayOrderStoreInfo(new ArrayList<Long>(merchantIds));
		Map<Long, PayOrderStoreInfoDTO> storeInfoDTOMap = new HashMap<>();
		if (isSuccess(storeInfoDTOResult) && !storeInfoDTOResult.getModel().isEmpty()) {
			for (PayOrderStoreInfoDTO storeInfoDTO : storeInfoDTOResult.getModel()) {
				if (!storeInfoDTOMap.containsKey(storeInfoDTO.getMerchantId())) {
					storeInfoDTOMap.put(storeInfoDTO.getMerchantId(), storeInfoDTO);
				}
			}
		}
		PayOrderStoreInfoDTO storeInfo;
		for (PayOrderDTO payOrderDTO : payOrderDTOS) {
			PayOrderInfoDTO payOrderInfoDTO = new PayOrderInfoDTO();
			payOrderInfoDTO.setEvaluationEnum(payOrderDTO.getEvaluationEnum());
			payOrderInfoDTO.setActualAmount(payOrderDTO.getActualAmount());
			payOrderInfoDTO.setFavoredAmount(payOrderDTO.getFavoredAmount());
			payOrderInfoDTO.setGmtCreate(payOrderDTO.getGmtCreate());
			payOrderInfoDTO.setId(payOrderDTO.getId());
			payOrderInfoDTO.setMerchantId(payOrderDTO.getMerchantId());
			payOrderInfoDTO.setTotalAmount(payOrderDTO.getTotalAmount());
			// 查询相关商家Id
			storeInfo = storeInfoDTOMap.get(payOrderDTO.getMerchantId());
			if (storeInfo == null) {
				payOrderInfoDTO.setName("");
				payOrderInfoDTO.setImgUrl("");
			} else {
				payOrderInfoDTO.setName(storeInfo.getName());
				payOrderInfoDTO.setImgUrl(storeInfo.getStoreUrl());
			}

			payOrderInfoDTOS.add(payOrderInfoDTO);
		}
		Page<PayOrderInfoDTO> list = new Page<>();
		list.setRecords(payOrderInfoDTOS);
		list.setTotalCount(pageResult.getModel().getTotalCount());
		list.setCurrentPage(pageResult.getModel().getCurrentPage());
		return successGet(list);
	}

	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@ApiOperation(value = "删除买单记录", notes = "删除买单记录  [1004,1000] （章勇）", httpMethod = "DELETE")
	@ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
	@Authorization
	@RequestMapping(value = "delPayOrderInfo/{id}", method = RequestMethod.DELETE)
	public Result delPayOrderInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "买单id", required = true) @PathVariable("id") Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = payOrderService.delPayOrderInfo(id, memberId);
		if (!isSuccess(result)) {
			return successCreated(result);
		}
		return successDelete();
	}

    @Audit(date = "2017-07-04", reviewer = "孙林青")
    @ApiOperation(value = "买单记录详情", notes = "买单记录详情  [1000,1100,1004] （章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getOrderInfo/{id}", method = RequestMethod.GET)
    public Result<MemberPayOrderInfoDTO> getOrderInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                      @PathVariable("id") @ApiParam(required = true, value = "买单id") Long id) {
		if (id == null) {
			return successGet(ResultCode.REQUIRED_PARM_EMPTY);
		}

		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<MemberPayOrderInfoDTO> getOrderInfoResult = payOrderService.getOrderInfo(id, memberId);
		if (!isSuccess(getOrderInfoResult)) {
			return successGet(getOrderInfoResult);
		}
		MemberPayOrderInfoDTO memberPayOrderInfoDTO = getOrderInfoResult.getModel();
		// 查询商家信息
		PayOrderMerchantStoreInfoDTO storeInfoDTO = merchantStoreService.getPayOrderDetailStoreInfo(memberPayOrderInfoDTO.getMerchantId());
		if (storeInfoDTO != null) {
			memberPayOrderInfoDTO.setMerchantStoreId(storeInfoDTO.getMerchantStoreId());
			memberPayOrderInfoDTO.setName(storeInfoDTO.getName());
			memberPayOrderInfoDTO.setAddress(storeInfoDTO.getAddress());
			memberPayOrderInfoDTO.setStoreUrl(storeInfoDTO.getStoreUrl());
			memberPayOrderInfoDTO.setPrincipalMobile(storeInfoDTO.getPrincipalMobile());
			memberPayOrderInfoDTO.setRegionName(storeInfoDTO.getRegionName());
			memberPayOrderInfoDTO.setUserNum(storeInfoDTO.getUserNum());
		}
		if (EvaluationEnum.EVALUATION_SUCCESS.equals(memberPayOrderInfoDTO.getEvaluationEnum())) {
			// 已经评论
			Byte grade = commentMerchantService.getGradeByOrderId(memberPayOrderInfoDTO.getId(), memberId);
			if (grade == null) {
				grade = 0;
			}
			memberPayOrderInfoDTO.setGrade(grade);
		}

		return successGet(getOrderInfoResult);
	}

}
