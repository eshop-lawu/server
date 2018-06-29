package com.lawu.eshop.operator.api.service;

import java.util.List;

import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.MerchantStorePlatDTO;
import com.lawu.eshop.user.dto.OperatorMerchantInfoDTO;
import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.param.MerchantStorePlatParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "user-srv")
public interface MerchantStoreService {
	
	 @RequestMapping(value = "merchantStore/selectAllMerchantStore",method = RequestMethod.POST)
	 Result<Page<MerchantStorePlatDTO>> selectAllMerchantStore(@ModelAttribute MerchantStorePlatParam param);
	 
	 /**
      * 根据商家id查询门店信息
      *
      * @param id
      * @return
      */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/findMerchantStoreInfo/{id}")
    Result<MerchantStoreDTO> selectMerchantStore(@PathVariable("id") Long id);

	/**
	 * 查询所有审核通过的实体店铺
	 *
	 * @param listMerchantStoreParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "merchantStore/listMerchantStore")
	Result<List<MerchantStoreDTO>> listMerchantStore(@ModelAttribute ListMerchantStoreParam listMerchantStoreParam);

	/**
	 * 重建门店索引
	 *
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "merchantStore/rebuildStoreIndex")
	Result rebuildStoreIndex(@RequestBody List<StoreIndexParam> indexParamList);

	/**
	 * 删除无效门店索引
	 *
	 * @param typeEnum
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "merchantStore/delInvalidStoreIndex")
	Result delInvalidStoreIndex(@RequestParam("typeEnum") DelIndexTypeEnum typeEnum);

	/**
	 * 删除全部门店索引
	 *
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(method = RequestMethod.GET, value = "merchantStore/delAllStoreIndex")
	Result delAllStoreIndex();

	/**
	 *查询买单列表中商家name  account
	 * @param merchantId
	 * @return
	 * @author zhangy
	 */
	@RequestMapping(method = RequestMethod.GET, value = "merchantStore/getPayOrderMerchantInfo")
	OperatorMerchantInfoDTO getPayOrderMerchantInfo(@RequestParam("merchantId")  Long merchantId);

	@RequestMapping(value = "merchantStore/delSolrDocsById", method = RequestMethod.DELETE)
	Result delSolrDocsById(@RequestParam(value = "merchantStoreId") Long merchantStoreId);
	
	
	 @RequestMapping(method = RequestMethod.GET, value = "merchantStore/selectMerchantStoreByMId")
	 Result<MerchantStoreDTO> selectMerchantStoreByMId(@RequestParam("merchantId") Long id);

	/**
	 * 根据门店ID查询门店信息
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "merchantStore/getMerchantStore/{id}")
	Result<MerchantStoreDTO> getMerchantStoreById(@PathVariable("id") Long id);

	@RequestMapping(method = RequestMethod.GET, value = "merchantStore/findAccountAndRegionPathByNum")
	VisitUserInfoDTO findAccountAndRegionPathByNum(@RequestParam("merchantNum") String merchantNum);

}
