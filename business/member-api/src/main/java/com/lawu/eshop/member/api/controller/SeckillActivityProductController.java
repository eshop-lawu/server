package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.concurrentqueue.bizctrl.BusinessDecisionService;
import com.lawu.concurrentqueue.bizctrl.BusinessInventorySynService;
import com.lawu.eshop.concurrentqueue.impl.BusinessKey;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.SeckillActivityProductService;
import com.lawu.eshop.member.api.service.SeckillActivityService;
import com.lawu.eshop.member.api.service.ShoppingOrderService;
import com.lawu.eshop.order.param.ActivityProductBuyQueryParam;
import com.lawu.eshop.product.dto.RecentlySeckillActivityDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductBuyPageDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductInformationDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductModelInformationDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductPartDTO;
import com.lawu.eshop.product.dto.ShareSeckillActivityProductDTO;
import com.lawu.eshop.product.dto.foreign.SeckillActivityProductInformationForeignDTO;
import com.lawu.eshop.product.param.SeckillActivityProductPageQueryParam;
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
 * 抢购活动商品控制
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
@Api(tags = "seckillActivityProduct")
@RestController
@RequestMapping(value = "seckillActivityProduct/")
public class SeckillActivityProductController extends BaseController {
    
    @Autowired
    private SeckillActivityProductService seckillActivityProductService;
    
    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @SuppressWarnings("rawtypes")
    @Autowired
    @Qualifier("seckillActivityProductBusinessDecisionServiceImpl")
    private BusinessDecisionService<Result> seckillActivityProductBusinessDecisionServiceImpl;
    
    @Autowired
    private BusinessInventorySynService businessInventorySynService;

    @Autowired
    private SeckillActivityService seckillActivityService;

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询当前活动所有商品", notes = "根据抢购活动id分页查询活动下的所有商品[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "page/{id}", method = RequestMethod.GET)
    public Result<Page<SeckillActivityProductBuyPageDTO>> page(@PathVariable("id") Long id, @ModelAttribute @Validated SeckillActivityProductPageQueryParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return successGet(seckillActivityProductService.page(id, param));
    }
    
    /**
     * 查询抢购活动商品信息
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取活动商品详情", notes = "获取活动商品详情[1004,1100]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "information/{id}", method = RequestMethod.GET)
    public Result<SeckillActivityProductInformationForeignDTO> information(@ApiParam(value = "如果用户有登录就传入") @RequestHeader(name = UserConstant.REQ_HEADER_TOKEN, required = false) String token, @ApiParam(value = "抢购活动商品id") @PathVariable("id") Long id) {
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        Result<SeckillActivityProductInformationDTO>  result = seckillActivityProductService.information(id, memberId);
        if (!isSuccess(result)) {
            return successGet(result);
        }
        SeckillActivityProductInformationDTO model = result.getModel();
        SeckillActivityProductInformationForeignDTO rtn = new SeckillActivityProductInformationForeignDTO();
        if (memberId != null && memberId != 0L) {
            ActivityProductBuyQueryParam param = new ActivityProductBuyQueryParam();
            param.setActivityProductId(model.getActivityProductId());
            param.setMemberId(memberId);
            Result<Boolean> isBuyResult = shoppingOrderService.isBuy(param);
            if (!isSuccess(isBuyResult)) {
                return successGet(isBuyResult);
            }
            rtn.setBuy(isBuyResult.getModel());
        } else {
            rtn.setBuy(false);
        }
        rtn.setActivityProductId(model.getActivityProductId());
        rtn.setActivityStatus(model.getActivityStatus());
        rtn.setAttentionCount(model.getAttentionCount());
        rtn.setCountdown(model.getCountdown());
        rtn.setEndCountdown(model.getEndCountdown());
        rtn.setMemberLevel(model.getMemberLevel());
        rtn.setProductModelList(model.getProductModelList());
        rtn.setSellingPrice(model.getSellingPrice());
        rtn.setAttention(model.getAttention());
        rtn.setSoldQuantity(model.getSoldQuantity());
        rtn.setExceededAttentionTime(model.getExceededAttentionTime());
        rtn.setSaleMoney(model.getSaleMoney());
        
        // 从缓存取出真实库存
        for (SeckillActivityProductModelInformationDTO item : rtn.getProductModelList()) {
            int inventory = businessInventorySynService.getInventory(seckillActivityProductBusinessDecisionServiceImpl, BusinessKey.SECKILL_ACTIVITY_PRODUCT, item.getSeckillActivityProductModelId());
            item.setLeftCount(inventory);
        }
        return successGet(rtn);
    }

    @Audit(date = "2018-03-17", reviewer = "杨清华")
    @ApiOperation(value = "趣乐购首页三件商品", notes = "趣乐购首页三件商品。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getRecommendSeckillActivityProduct", method = RequestMethod.GET)
    public Result<SeckillActivityProductPartDTO> getRecommendSeckillActivityProduct() {
        Result<SeckillActivityProductPartDTO> result = seckillActivityProductService.getRecommendSeckillActivityProduct();
        Result<RecentlySeckillActivityDTO> activityDTOResult = seckillActivityService.recentlyActivity();
        result.getModel().setCountdown(activityDTOResult.getModel().getCountdown());
        result.getModel().setActivityStatus(activityDTOResult.getModel().getActivityStatus());
        return successGet(result);
    }

    @Audit(date = "2018-05-17", reviewer = "孙林青")
    @ApiOperation(value = "根据分享消息查询抢购活动商品信息", notes = "根据分享消息查询抢购活动商品信息。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getShareSeckillActivityProduct/{id}", method = RequestMethod.GET)
    public Result<ShareSeckillActivityProductDTO> getShareSeckillActivityProduct(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                                @PathVariable @ApiParam(required = true, value = "抢购活动商品id") Long id) {
        Result<ShareSeckillActivityProductDTO> result = seckillActivityProductService.getShareSeckillActivityProduct(id);
        return successGet(result);
    }

}
