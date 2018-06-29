package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.operator.api.service.MessageService;
import com.lawu.eshop.operator.api.service.SeckillActivityProductService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.product.dto.SeckillActivityProductInfoDTO;
import com.lawu.eshop.product.param.SeckillActivityProductAuditParam;
import com.lawu.eshop.product.param.SeckillActivityProductNotPassedParam;
import com.lawu.eshop.product.param.SeckillActivityProductPageSearchParam;
import com.lawu.eshop.product.param.SeckillActivityProductUpdateParam;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 抢购活动商品控制器
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
@Api(tags = "seckillActivityProduct")
@RestController
@RequestMapping(value = "seckillActivityProduct/")
public class SeckillActivityProductController extends BaseController {
    
    @Autowired
    private SeckillActivityProductService seckillActivityProductService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MerchantService merchantService;
    
    @SuppressWarnings("unchecked")
    @PageBody
    @ApiOperation(value = "查询抢购活动商品", notes = "查询抢购活动商品(蒋鑫俊)[1004]", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("seckillActivityProduct:pageSearch")
    @RequestMapping(value = "pageSearch/{id}", method = RequestMethod.GET)
    public Result<Page<SeckillActivityProductInfoDTO>> pageSearch(@PathVariable("id") Long id, @ModelAttribute @Validated SeckillActivityProductPageSearchParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return successGet(seckillActivityProductService.pageSearch(id, param));
    }
    
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ACTIVITY_PRODUCT_NOT_PASS,idParamName ="id")
    @ApiOperation(value = "抢购活动商品不通过", notes = "抢购活动商品不通过(蒋鑫俊)[1001, 1004, 1100]", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("seckillActivityProduct:notPassed")
    @RequestMapping(value = "notPassed/{id}", method = RequestMethod.PUT)
    public Result<?> notPassed(@PathVariable("id") Long id, @ModelAttribute @Validated SeckillActivityProductNotPassedParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        seckillActivityProductService.notPassed(id, param);

        Result<MerchantSNSDTO> merchantResult = merchantService.selectMerchantInfo(param.getMerchantId());
        if (isSuccess(merchantResult)) {
            //推送消息
            MessageInfoParam messageInfoParam = new MessageInfoParam();
            messageInfoParam.setRelateId(id);
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_SECKILL_ACTIVITY_PRODUCT_CHECK_FAIL);
            MessageTempParam messageTempParam = new MessageTempParam();
            messageTempParam.setProductName(param.getProductName());
            messageTempParam.setFailReason(param.getReasons());
            messageTempParam.setActivityName(param.getStartDate() + " " + param.getActivityName());
            messageInfoParam.setMessageParam(messageTempParam);
            messageService.saveMessage(merchantResult.getModel().getNum(), messageInfoParam);
        }
        return successCreated();
    }
    
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ACTIVITY_PRODUCT_PASS,idParamName ="id")
    @ApiOperation(value = "抢购活动商品审核", notes = "抢购活动商品审核(蒋鑫俊)[1001, 1100]", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("seckillActivityProduct:audit")
    @RequestMapping(value = "audit/{id}", method = RequestMethod.PUT)
    public Result<?> audit(@PathVariable("id") Long id) {
        String account = UserUtil.getCurrentUserAccount();
        SeckillActivityProductAuditParam param = new SeckillActivityProductAuditParam();
        param.setAuditorAccount(account);
        return successCreated(seckillActivityProductService.audit(id, param));
    }
    
    @SuppressWarnings("rawtypes")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ACTIVITY_PRODUCT_UPDATE,idParamName ="activityProductId")
    @ApiOperation(value = "更新抢购活动商品", notes = "更新抢购活动商品(蒋鑫俊)[1100]", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("seckillActivityProduct:audit")
    @RequestMapping(value = "update/{activityProductId}", method = RequestMethod.PUT)
    public Result update(@PathVariable("activityProductId") Long activityProductId, @ModelAttribute @Validated SeckillActivityProductUpdateParam param) {
        Result result = seckillActivityProductService.update(activityProductId, param);
        return successCreated(result);
    }
}
