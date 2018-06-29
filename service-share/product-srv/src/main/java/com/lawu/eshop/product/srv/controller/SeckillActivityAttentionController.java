package com.lawu.eshop.product.srv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.param.SeckillActivityProductAttentionParam;
import com.lawu.eshop.product.srv.service.SeckillActivityAttentionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 抢购活动关注控制器
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
@RestController
@RequestMapping(value = "seckillActivityAttention/")
public class SeckillActivityAttentionController extends BaseController {
    
    private static Logger logger = LoggerFactory.getLogger(SeckillActivityAttentionController.class);
    
    @Autowired
    private SeckillActivityAttentionService seckillActivityAttentionService;
    
    /**
     * 根据活动商品id添加关注记录以及增加商品关注人数
     * 
     * @param activityProductId 活动商品id
     * @param memberId 用户id
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "attention/{activityProductId}", method = RequestMethod.POST)
    public Result attention(@PathVariable("activityProductId") Long activityProductId, @RequestBody @Validated SeckillActivityProductAttentionParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            seckillActivityAttentionService.attention(activityProductId, param);
        } catch (DataNotExistException e) {
            logger.error(e.getMessage(), e);
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
        return successCreated();
    }
    
}
