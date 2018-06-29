package com.lawu.eshop.jobs.service;

import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.param.PayOrderAutoCommentParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
@FeignClient(value = "mall-srv")
public interface CommentMerchantService {

    /**
     * 商品好评率
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "commentMerchant/getCommentAvgGrade/{merchantId}",method = RequestMethod.GET)
    Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("merchantId") Long merchantId);

    @RequestMapping(value = "commentMerchant/payOrderAutoComment",method = RequestMethod.POST)
    Result payOrderAutoComment(@ModelAttribute PayOrderAutoCommentParam param);
}
