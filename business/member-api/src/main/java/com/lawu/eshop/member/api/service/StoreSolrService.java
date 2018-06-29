package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.StoreSearchWordDTO;
import com.lawu.eshop.user.dto.StoreSolrDTO;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.StoreSolrParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/4/13.
 */
@FeignClient(value = "user-srv")
public interface StoreSolrService {

    /**
     * 会员APP查询门店列表
     *
     * @param storeSolrParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "storeSolr/listStore")
    Result<Page<StoreSolrDTO>> listStore(@ModelAttribute StoreSolrParam storeSolrParam);

    /**
     * 搜索词关联词频查询
     *
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "storeSolr/listStoreSearchWord")
    Result<List<StoreSearchWordDTO>> listStoreSearchWord(@RequestParam("name") String name, @RequestParam("regionPath") String regionPath);

    /**
     * 专属特惠
     *
     * @param discountStoreParam
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "storeSolr/discountStore")
    Result<Page<StoreSolrDTO>> discountStore(@ModelAttribute DiscountStoreParam discountStoreParam);
}
