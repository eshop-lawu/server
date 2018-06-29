package com.lawu.eshop.operator.api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.activity.dto.GenerateLargeRedpacketDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityQueryDTO;
import com.lawu.eshop.activity.param.GenerateLargeRedpacketParam;
import com.lawu.eshop.activity.param.GenerateNormalRedpacketParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivitySaveParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivityUpdateParam;
import com.lawu.eshop.activity.param.RedpacketActivityQueryParam;
import com.lawu.eshop.activity.param.SaveRedpacketParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 助力红包活动服务接口
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
@FeignClient(value = "activity-srv", path = "helpRedpacketActivity/")
public interface HelpRedpacketActivityService {
    
    /**
     * 查询助力红包活动详情
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    @Deprecated
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    Result<HelpRedpacketActivityDTO> detail();
    
    /**
     * 查询助力红包活动详情
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    Result<HelpRedpacketActivityDTO> detail(@PathVariable(name = "id", required = false) Integer id);
    
    /**
     * 查询助力红包活动详情
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    @Deprecated
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    Result<HelpRedpacketActivityDTO> update(@RequestBody HelpRedpacketActivityUpdateParam param);
    
    /**
     * 查询助力红包活动详情
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    Result<HelpRedpacketActivityDTO> update(@PathVariable(name = "id", required = false) Integer id, @RequestBody HelpRedpacketActivityUpdateParam param);
    
    /**
     * 生成大额红包
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    @Deprecated
    @RequestMapping(value = "generateLargeRedpacket", method = RequestMethod.PUT)
    Result<GenerateLargeRedpacketDTO> generateLargeRedpacket(@RequestBody GenerateLargeRedpacketParam param);
    
    /**
     * 生成大额红包
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    @RequestMapping(value = "generateLargeRedpacket/{id}", method = RequestMethod.PUT)
    Result<GenerateLargeRedpacketDTO> generateLargeRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody GenerateLargeRedpacketParam param);
    
    /**
     * 保存大额红包
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月30日
     * @updateDate 2017年12月30日
     */
    @Deprecated
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveLargeRedpacket", method = RequestMethod.PUT)
    Result saveLargeRedpacket(@RequestBody List<SaveRedpacketParam> params);
    
    /**
     * 保存大额红包
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月30日
     * @updateDate 2017年12月30日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveLargeRedpacket/{id}", method = RequestMethod.PUT)
    Result saveLargeRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody List<SaveRedpacketParam> params);
    
    /**
     * 生成普通红包
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月2日
     * @updateDate 2018年1月2日
     */
    @Deprecated
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "generateNormalRedpacket", method = RequestMethod.PUT)
    Result generateNormalRedpacket(@RequestBody GenerateNormalRedpacketParam param);
    
    /**
     * 生成普通红包
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月2日
     * @updateDate 2018年1月2日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "generateNormalRedpacket/{id}", method = RequestMethod.PUT)
    Result generateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody GenerateNormalRedpacketParam param);
    
    /**
     * 获取已经分配的普通红包总金额
     * 
     * @author jiangxinjun
     * @createDate 2018年1月4日
     * @updateDate 2018年1月4日
     */
    @Deprecated
    @RequestMapping(value = "getNormalRedpacketTotalAmount", method = RequestMethod.GET)
    Result<BigDecimal> getNormalRedpacketTotalAmount();
    
    /**
     * 获取已经分配的普通红包总金额
     * 
     * @author jiangxinjun
     * @createDate 2018年1月4日
     * @updateDate 2018年1月4日
     */
    @RequestMapping(value = "getNormalRedpacketTotalAmount/{id}", method = RequestMethod.GET)
    Result<BigDecimal> getNormalRedpacketTotalAmount(@PathVariable(name = "id", required = false) Integer id);
    
    /**
     * 重新生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的重新生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    @Deprecated
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "againGenerateNormalRedpacket", method = RequestMethod.PUT)
    Result againGenerateNormalRedpacket(@RequestBody GenerateNormalRedpacketParam param);
    
    /**
     * 重新生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的重新生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "againGenerateNormalRedpacket/{id}", method = RequestMethod.PUT)
    Result againGenerateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody GenerateNormalRedpacketParam param);
    
    /**
     * 继续生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的继续生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    @Deprecated
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "continueGenerateNormalRedpacket", method = RequestMethod.PUT)
    Result continueGenerateNormalRedpacket();
    
    /**
     * 继续生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的继续生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "continueGenerateNormalRedpacket/{id}", method = RequestMethod.PUT)
    Result continueGenerateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id);
    
    /**
     * 获取活动列表
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @RequestMapping(value = "list", method = RequestMethod.PUT)
    Result<Page<HelpRedpacketActivityQueryDTO>> list(@RequestBody @Validated RedpacketActivityQueryParam param);
    
    /**
     * 保存红包活动
     * 
     * @param param
     *            保存参数
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    Result save(@RequestBody HelpRedpacketActivitySaveParam param);
}
