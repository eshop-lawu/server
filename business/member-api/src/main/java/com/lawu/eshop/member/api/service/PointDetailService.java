package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.property.dto.PointDetailDTO;
import com.lawu.eshop.property.param.PointDetailQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/30
 */
@FeignClient(value = "property-srv")
public interface PointDetailService {

    /**
     * 根据用户编号和查询参数查询交易明细
     *
     * @param userNum               用户编号
     * @param pointDetailQueryParam 查询参数
     * @return
     */
    @RequestMapping(value = "pointDetail/findPageByUserNum/{userNum}", method = RequestMethod.POST)
    Result<Page<PointDetailDTO>> findPageByUserNum(@PathVariable("userNum") String userNum, @RequestBody PointDetailQueryParam pointDetailQueryParam);

    /**
     * 根据用户编号和业务ID查询是否存在记录
     *
     * @param userNum
     * @param bizId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "pointDetail/existsPointDetailByUserNumAndBizId", method = RequestMethod.GET)
    Result<Boolean> existsPointDetailByUserNumAndBizId(@RequestParam("userNum") String userNum, @RequestParam("bizId") String bizId);

}
