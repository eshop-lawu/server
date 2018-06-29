package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.product.dto.SeckillActivityDetailsDTO;
import com.lawu.eshop.product.dto.SeckillActivityInfoDTO;
import com.lawu.eshop.product.param.SeckillActivityPageQueryParam;
import com.lawu.eshop.product.param.SeckillActivitySaveParam;
import com.lawu.eshop.product.param.SeckillActivityUpdateParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "product-srv", path = "seckillActivity/")
public interface SeckillActivityService {

    /**
     * 根据查询参数分页查询活动列表 用于运营平台
     * 
     * @param param
     * @param bindingResult
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "page", method = RequestMethod.PUT)
    Result<Page<SeckillActivityInfoDTO>> page(@RequestBody SeckillActivityPageQueryParam param);

    /**
     * 根据id查询活动详情 用于运营平台
     * 
     * @param id
     *            抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Result<SeckillActivityDetailsDTO> detail(@PathVariable("id") Long id);

    /**
     * 根据id删除抢购活动
     * 
     * @param id
     *            抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.PUT)
    Result<?> delete(@PathVariable("id") Long id);

    /**
     * 根据id下架抢购活动 下架之后抢购的活动的状态为已结束
     * 
     * @param id
     *            抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "down/{id}", method = RequestMethod.PUT)
    Result<?> down(@PathVariable("id") Long id);

    /**
     * 根据id发布抢购活动
     * 
     * @param id
     *            抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "release/{id}", method = RequestMethod.PUT)
    Result<?> release(@PathVariable("id") Long id);

    /**
     * 根据id更新抢购活动
     * 
     * @param id
     *            抢购活动id
     * @param param
     *            抢购活动更新参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    Result<?> update(@PathVariable("id") Long id, @RequestBody SeckillActivityUpdateParam param);
    
    /**
     * 根据id审核抢购活动
     * 
     * @param id 抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "audit/{id}", method = RequestMethod.PUT)
    Result<?> audit(@PathVariable("id") Long id);
    
    /**
     * 新增抢购活动
     * 
     * @param param 抢购活动保存参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月28日
     * @updateDate 2017年11月28日
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    Result<?> add(@RequestBody @Validated SeckillActivitySaveParam param);
}
