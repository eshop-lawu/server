package com.lawu.eshop.merchant.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.event.EventPublisher;
import com.lawu.eshop.merchant.api.service.SeckillActivityJoinService;
import com.lawu.eshop.product.dto.SeckillActivityDetailDTO;
import com.lawu.eshop.product.dto.SeckillActivityJoinDTO;
import com.lawu.eshop.product.dto.SeckillActivityManageDetailDTO;
import com.lawu.eshop.product.dto.SeckillActivityManagerDTO;
import com.lawu.eshop.product.param.JoinSeckillActivityParam;
import com.lawu.eshop.product.param.SeckillActivityJoinParam;
import com.lawu.eshop.product.param.SeckillActivityManageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author zhangrc
 * @date 2017/11/23
 *
 */
@Api(tags = "seckillActivityJoin")
@RestController
@RequestMapping(value = "seckillActivityJoin/")
public class SeckillActivityJoinController extends BaseController {
	
	@Autowired
	private SeckillActivityJoinService seckillActivityJoinService;
	
	@Autowired
	private EventPublisher eventPublisher;

	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "分页查询专场活动", notes = "专场活动，[]。(张荣成)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "queryPage", method = RequestMethod.GET)
	public Result<Page<SeckillActivityJoinDTO>>  queryPage(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam SeckillActivityJoinParam param) {
		 Result<Page<SeckillActivityJoinDTO>>  result= seckillActivityJoinService.queryPage(param);
		return successGet(result);
	}

	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "分页查询活动管理", notes = "活动管理，[]。(张荣成)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "queryManagePage", method = RequestMethod.GET)
	public Result<Page<SeckillActivityManagerDTO>>  queryManagePage(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam SeckillActivityJoinParam param) {
		SeckillActivityManageParam manageParam = new SeckillActivityManageParam();
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		manageParam.setMerchantId(merchantId);
		manageParam.setCurrentPage(param.getCurrentPage());
		manageParam.setPageSize(param.getPageSize());
		Result<Page<SeckillActivityManagerDTO>> result = seckillActivityJoinService.queryManagePage(manageParam);
		return successGet(result);
	}

	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@ApiOperation(value = "活动详情(报名前)", notes = "活动详情，[]。(张荣成)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "querySeckillActivityDetail/{id}", method = RequestMethod.GET)
	public Result<SeckillActivityDetailDTO>  querySeckillActivityDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@PathVariable @ApiParam(required = true, value = "活动id") Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		return seckillActivityJoinService.querySeckillActivityDetail(id, merchantId);
	}


	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@ApiOperation(value = "活动管理详情", notes = "活动管理详情，[]。(张荣成)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "querySeckillActivityManageDetail/{id}", method = RequestMethod.GET)
	public Result<SeckillActivityManageDetailDTO>  querySeckillActivityManageDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@PathVariable @ApiParam(required = true, value = "活动id") Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		return seckillActivityJoinService.querySeckillActivityManageDetail(id, merchantId);
	}

	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@ApiOperation(value = "报名活动", notes = "报名活动，[3006|3007]。(张荣成)", httpMethod = "POST")
	@Authorization
	@RequestMapping(value = "joinSeckillActivity", method = RequestMethod.POST)
	public Result  joinSeckillActivity(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestBody @ApiParam JoinSeckillActivityParam param) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		String merchantNum = UserUtil.getCurrentUserNum(getRequest());
		Result result = seckillActivityJoinService.joinSeckillActivity(param, merchantId);
		
		if(result.getRet() == ResultCode.SUCCESS){
			//获取动力任务
			RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
			taskRecordParam.setMerchantNum(merchantNum);
			taskRecordParam.setType(MerchantPowerTaskTypeEnum.ACTIVITY);
			eventPublisher.publishRichPowerTaskEvent(taskRecordParam);
		}
		return result;
	}
	
}
