package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.NewMerchantStoreDTO;
import com.lawu.eshop.user.dto.RecommendFoodDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.eshop.user.param.StoreStatisticsParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/4/25.
 */
@FeignClient(value = "user-srv")
public interface MerchantStoreService {

    /**
     * 查询所有审核通过的实体店铺
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "merchantStore/listMerchantStore")
    Result<List<MerchantStoreDTO>> listMerchantStore(@ModelAttribute ListMerchantStoreParam listMerchantStoreParam);

    /**
     * 更新门店统计信息
     *
     * @param id
     * @param param
     */
    @RequestMapping(method = RequestMethod.PUT, value = "merchantStore/updateStoreStatistics/{id}")
    void updateStoreStatisticsById(@PathVariable("id") Long id, @ModelAttribute StoreStatisticsParam param);

    /**
     * 商家信息查询
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "merchantStore/selectMerchantStoreByMId", method = RequestMethod.GET)
	public Result<MerchantStoreDTO> selectMerchantStoreByMId(@RequestParam("merchantId") Long merchantId);

    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/findAccountAndRegionPathByNum")
    VisitUserInfoDTO findAccountAndRegionPathByNum(@RequestParam("merchantNum") String merchantNum);

    /**
     * 新店推荐
     *
     * @param regionPath
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/listNewMerchant")
    Result<List<NewMerchantStoreDTO>> listNewMerchant(@RequestParam("regionPath") String regionPath);

    /**
     * 优选美食-人气最高
     *
     * @param industryId
     * @param regionPath
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/listRecommendFoodConsume/{industryId}")
    Result<List<RecommendFoodDTO>> listRecommendFoodConsume(@PathVariable("industryId") Integer industryId, @RequestParam("regionPath") String regionPath);

    /**
     * 优选美食-评价最高
     *
     * @param industryId
     * @param regionPath
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/listRecommendFoodComment/{industryId}")
    Result<List<RecommendFoodDTO>> listRecommendFoodComment(@PathVariable("industryId") Integer industryId, @RequestParam("regionPath") String regionPath);

    /**
     * 重建门店索引
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "merchantStore/rebuildStoreIndex")
    Result rebuildStoreIndex(@RequestBody List<StoreIndexParam> indexParamList);

    /**
     * 删除无效的门店索引
     *
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/delInvalidStoreIndex")
    Result delInvalidStoreIndex(@RequestParam("typeEnum") DelIndexTypeEnum typeEnum);

}
