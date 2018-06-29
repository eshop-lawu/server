package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.AuditEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.MerchantInfoDTO;
import com.lawu.eshop.ad.dto.OperatorAdDTO;
import com.lawu.eshop.ad.param.AdFindParam;
import com.lawu.eshop.ad.param.ListAdParam;
import com.lawu.eshop.ad.param.OperatorAdParam;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "ad-srv")
public interface AdService {
	
	/**
	 * 查询广告
	 * @param adPlatParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "ad/selectListByPlatForm")
    Result<Page<AdDTO>> selectListByPlatForm(@RequestBody AdFindParam adPlatParam);
	
	/**
	 * 操作广告下架
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT,value = "ad/updateStatus/{id}")
    public Result updateStatus(@PathVariable("id") Long id);
	
	/**
	 * 操作广告删除
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT,value = "ad/remove/{id}")
    public Result remove(@PathVariable("id") Long id);
	
	/**
	 * 审核视频
	 * @param auditEnum
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT,value = "ad/auditVideo/{id}")
    Result auditVideo(@PathVariable("id") Long id, @RequestParam("auditorId") Integer auditorId, @RequestParam("remark") String remark, @RequestBody AuditEnum auditEnum);

	/**
	 * 查询广告列表
	 *
	 * @param listAdParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "ad/listAllAd")
	Result<Page<AdDTO>> listAd(@RequestBody ListAdParam listAdParam );

	/**
	 * 根据ID查询广告详情
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value = "ad/getAd/{id}")
	Result<AdDTO> getAdById(@PathVariable("id") Long id);

	/**
	 * 操作广告(下架、删除)
	 *
	 * @param id
	 * @param adStatusEnum
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT,value = "ad/operatorUpdateAdStatus/{id}")
	Result operatorUpdateAdStatus(@PathVariable("id") Long id, @RequestParam("adStatusEnum") AdStatusEnum adStatusEnum);

	/**
	 * 查询上架中的平面视频广告
	 *
	 * @param listAdParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "ad/listFlatVideoAd")
	Result<List<AdDTO>> listFlatVideoAd(@ModelAttribute ListAdParam listAdParam);

	/**
	 * 重建广告索引
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "ad/rebuildAdIndex")
	Result rebuildAdIndex(@RequestParam("pageSize") Integer pageSize);

	/**
	 * 删除无效广告索引
	 *
	 * @param typeEnum
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "ad/delInvalidAdIndex")
	Result delInvalidAdIndex(@RequestParam("typeEnum") DelIndexTypeEnum typeEnum);

	/**
	 * 删除全部广告索引
	 *
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "ad/delAllAdIndex")
	Result delAllAdIndex();
	
	
	/**
	 * 查询所有广告(上架|投放中  平面|视频)
	 * @param operatorAdParam
	 * @return
	 */
	@RequestMapping(value = "ad/selectOperatorAdAll", method = RequestMethod.POST)
	Result<Page<OperatorAdDTO>> selectOperatorAdAll(@RequestBody OperatorAdParam operatorAdParam);

	/**
	 * 下架商家所有广告
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "ad/soldOutAdByMerchantId")
	Result soldOutAdByMerchantId(@RequestParam(value = "merchantId") Long merchantId);
	
	@RequestMapping(value = "ad/selectMerchantNumByAdId", method = RequestMethod.GET)
	Result<MerchantInfoDTO> selectMerchantNumByAdId(@RequestParam("id") Long id);
	
	@RequestMapping(value = "ad/downOperatorById", method = RequestMethod.PUT)
	Result downOperatorById(@RequestParam("id") Long id, @RequestParam("auditorId") Integer auditorId, @RequestParam("remark") String remark);
}
