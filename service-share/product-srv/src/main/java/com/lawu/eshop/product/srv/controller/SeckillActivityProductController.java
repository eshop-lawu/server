package com.lawu.eshop.product.srv.controller;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.SeckillActivityProductBuyPageDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductInfoDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductInformationDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductPartDTO;
import com.lawu.eshop.product.dto.ShareSeckillActivityProductDTO;
import com.lawu.eshop.product.param.SeckillActivityProductAuditParam;
import com.lawu.eshop.product.param.SeckillActivityProductNotPassedParam;
import com.lawu.eshop.product.param.SeckillActivityProductPageQueryParam;
import com.lawu.eshop.product.param.SeckillActivityProductPageSearchParam;
import com.lawu.eshop.product.param.SeckillActivityProductUpdateParam;
import com.lawu.eshop.product.srv.bo.ProductInfoBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductExtendBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductPartBO;
import com.lawu.eshop.product.srv.bo.ShareSeckillActivityProductBO;
import com.lawu.eshop.product.srv.constants.PropertyConstant;
import com.lawu.eshop.product.srv.converter.SeckillActivityProductConverter;
import com.lawu.eshop.product.srv.service.ProductService;
import com.lawu.eshop.product.srv.service.SeckillActivityAttentionService;
import com.lawu.eshop.product.srv.service.SeckillActivityProductService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

@RestController
@RequestMapping(value = "seckillActivityProduct/")
public class SeckillActivityProductController extends BaseController {
    
    private static Logger logger = LoggerFactory.getLogger(SeckillActivityProductController.class);
    
    @Autowired
    private SeckillActivityProductService seckillActivityProductService;
    
    @Autowired
    private SeckillActivityAttentionService seckillActivityAttentionService;
    
    @Autowired
    private ProductService productService;
    
    /**
     * 根据id和查询参数分页查询抢购活动商品列表
     * 用于用户端
     * 
     * @param id 抢购活动id
     * @param param 分页查询参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月24日
     */
    @RequestMapping(value = "page/{id}", method = RequestMethod.PUT)
    public Result<Page<SeckillActivityProductBuyPageDTO>> page(@PathVariable("id") Long id, @RequestBody @Validated SeckillActivityProductPageQueryParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Page<SeckillActivityProductBO> page = seckillActivityProductService.page(id, param);
        Page<SeckillActivityProductBuyPageDTO> rtn = new Page<>();
        rtn.setCurrentPage(page.getCurrentPage());
        rtn.setTotalCount(page.getTotalCount());
        rtn.setRecords(SeckillActivityProductConverter.convertSeckillActivityProductBuyPageDTOList(page.getRecords()));
        // 判断商品是否下架
        rtn.getRecords().forEach((item) -> {
            ProductInfoBO productInfoBO = productService.getProductById(item.getProductId());
            if (productInfoBO.getProductStatus().equals(ProductStatusEnum.PRODUCT_STATUS_DEL) || productInfoBO.getProductStatus().equals(ProductStatusEnum.PRODUCT_STATUS_DOWN)) {
                item.setValid(false);
            } else {
                item.setValid(true);
            }
        });
        return successCreated(rtn);
    }
    
    /**
     * 查询抢购活动商品信息
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    @RequestMapping(value = "information/{id}", method = RequestMethod.GET)
    public Result<SeckillActivityProductInformationDTO> information(@PathVariable("id") Long id, @RequestParam(name = "memberId", required = false) Long memberId) {
        SeckillActivityProductExtendBO seckillActivityProductExtendBO = null;
        try {
            seckillActivityProductExtendBO = seckillActivityProductService.information(id);
        } catch (DataNotExistException e) {
            logger.error(e.getMessage(), e);
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
        SeckillActivityProductInformationDTO rtn = SeckillActivityProductConverter.convert(seckillActivityProductExtendBO);
        // 如果用户没有登录设置为未关注
        if (memberId != null) {
            // 查询是否已经关注过这个商品
            Boolean isAttention = seckillActivityAttentionService.isAttention(rtn.getActivityProductId(), memberId);
            rtn.setAttention(isAttention);
        } else {
            rtn.setAttention(false);
        }
        
        SeckillActivityBO seckillActivityBO = seckillActivityProductExtendBO.getSeckillActivity();
        if (seckillActivityBO != null) {
            // 判断是否已经超过设置关注的时间
            boolean isExceededAttentionTime = !DateUtil.isExceeds(new Date(), seckillActivityBO.getStartDate(), PropertyConstant.PROMPT_SECKILL_ACTIVITY_ABOUT_START_TIME, Calendar.MINUTE);
            rtn.setExceededAttentionTime(isExceededAttentionTime);
            // 倒计时在服务端放入
            Long countdown = null;
            Long endCountdown = null;
            switch (rtn.getActivityStatus()) {
                case NOT_STARTED:
                    countdown = DateUtil.interval(new Date(), seckillActivityBO.getStartDate(), Calendar.MILLISECOND);
                    endCountdown = DateUtil.interval(new Date(), seckillActivityBO.getEndDate(), Calendar.MILLISECOND);
                    break;
                case PROCESSING:
                    countdown = DateUtil.interval(new Date(), seckillActivityBO.getEndDate(), Calendar.MILLISECOND);
                    endCountdown = countdown;
                    break;
                default:
                    break;
            }
            rtn.setCountdown(countdown);
            rtn.setEndCountdown(endCountdown);
        }
        return successGet(rtn);
    }
    
    /**
     * 根据id和查询参数分页查询抢购活动商品列表
     * 用于运营平台
     * 
     * @param id 抢购活动id
     * @param param 分页查询参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "pageSearch/{id}", method = RequestMethod.PUT)
    public Result<Page<SeckillActivityProductInfoDTO>> pageSearch(@PathVariable("id") Long id, @RequestBody @Validated SeckillActivityProductPageSearchParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Page<SeckillActivityProductBO> page = seckillActivityProductService.page(id, param);
        Page<SeckillActivityProductInfoDTO> rtn = new Page<>();
        rtn.setCurrentPage(page.getCurrentPage());
        rtn.setTotalCount(page.getTotalCount());
        rtn.setRecords(SeckillActivityProductConverter.convertSeckillActivityProductInfoDTOList(page.getRecords()));
        return successCreated(rtn);
    }
    
    /**
     * 抢购活动商品
     * 审核不通过
     * 
     * @param id 抢购活动商品id
     * @param param 反馈参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "notPassed/{id}", method = RequestMethod.PUT)
    public Result<?> notPassed(@PathVariable("id") Long id, @RequestBody @Validated SeckillActivityProductNotPassedParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            seckillActivityProductService.notPassed(id, param);
        } catch (DataNotExistException e) {
            logger.error(e.getMessage(), e);
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (WrongOperationException e) {
            logger.error(e.getMessage(), e);
            return successCreated(ResultCode.FAIL, e.getMessage());
        }
        return successCreated();
    }
    
    /**
     * 审核抢购活动商品
     * 
     * @param id 抢购活动商品id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "audit/{id}", method = RequestMethod.PUT)
    public Result<?> audit(@PathVariable("id") Long id, @RequestBody SeckillActivityProductAuditParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            seckillActivityProductService.audit(id, param);
        } catch (DataNotExistException e) {
            logger.error(e.getMessage(), e);
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (WrongOperationException e) {
            logger.error(e.getMessage(), e);
            return successCreated(ResultCode.FAIL, e.getMessage());
        }
        return successCreated();
    }
    
    /**
     * 查询抢购商品型号库存
     * 
     * @param seckillActivityProductModelId 抢购活动商品型号id
     * @throws DataNotExistException 数据不存在
     * @author jiangxinjun
     * @createDate 2017年11月30日
     * @updateDate 2017年11月30日
     */
    @RequestMapping(value = "inventory/{seckillActivityProductModelId}", method = RequestMethod.GET)
    public Result<Integer> getInventory(@PathVariable("seckillActivityProductModelId") Long seckillActivityProductModelId) {
        Integer inventory = 0;
        try {
            inventory = seckillActivityProductService.getInventory(seckillActivityProductModelId);
        } catch (DataNotExistException e) {
            logger.error(e.getMessage(), e);
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
        return successCreated(inventory);
    }

    /**
     * 趣乐购首页三件商品
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getRecommendSeckillActivityProduct", method = RequestMethod.GET)
    public Result<SeckillActivityProductPartDTO> getRecommendSeckillActivityProduct() {
        SeckillActivityProductPartBO seckillActivityProductPartBO = seckillActivityProductService.getRecommendSeckillActivityProduct();
        return successGet(SeckillActivityProductConverter.convert(seckillActivityProductPartBO));
    }

    /**
     * 根据分享消息查询抢购活动商品信息
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getShareSeckillActivityProduct/{id}", method = RequestMethod.GET)
    public Result<ShareSeckillActivityProductDTO> getShareSeckillActivityProduct(@PathVariable Long id) {
        ShareSeckillActivityProductBO activityProductBO = seckillActivityProductService.getShareSeckillActivityProduct(id);
        if (activityProductBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        ShareSeckillActivityProductDTO activityProductDTO = new ShareSeckillActivityProductDTO();
        activityProductDTO.setProductId(activityProductBO.getProductId());
        activityProductDTO.setLeftCount(activityProductBO.getLeftCount());
        activityProductDTO.setStatusEnum(ActivityStatusEnum.getEnum(activityProductBO.getActivityStatus()));
        return successGet(activityProductDTO);
    }
    
    /**
     * 更新抢购活动商品
     * @param activityProductId
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月23日
     * @updateDate 2018年5月23日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "update/{activityProductId}", method = RequestMethod.PUT)
    public Result update(@PathVariable("activityProductId") Long activityProductId, @RequestBody @Validated SeckillActivityProductUpdateParam param) {
        try {
            seckillActivityProductService.update(activityProductId, param);
            return successCreated();
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
    }

}
