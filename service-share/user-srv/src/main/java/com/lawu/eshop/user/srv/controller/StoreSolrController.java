package com.lawu.eshop.user.srv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.user.dto.StoreSearchWordDTO;
import com.lawu.eshop.user.dto.StoreSolrDTO;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.StoreSolrParam;
import com.lawu.eshop.user.srv.converter.MerchantStoreConverter;
import com.lawu.eshop.user.srv.solr.service.MerchantStoreSolrService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/3/30.
 */
@RestController
@RequestMapping(value = "storeSolr/")
public class StoreSolrController extends BaseController {

    @Autowired
    private MerchantStoreSolrService merchantStoreSolrService;

    /**
     * 搜索门店
     *
     * @param storeSolrParam
     * @return
     */
    @RequestMapping(value = "listStore", method = RequestMethod.POST)
    public Result<Page<StoreSolrDTO>> listStore(@RequestBody StoreSolrParam storeSolrParam) {
        org.springframework.data.domain.Page<MerchantStoreSolr> merchantStoreSolrPage = merchantStoreSolrService.listStore(storeSolrParam);
        if (merchantStoreSolrPage == null || merchantStoreSolrPage.getContent() == null || merchantStoreSolrPage.getContent().isEmpty()) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        Page<StoreSolrDTO> page = new Page<>();
        page.setRecords(MerchantStoreConverter.convertStoreSolrDTOList(merchantStoreSolrPage.getContent()));
        page.setTotalCount((int) merchantStoreSolrPage.getTotalElements());
        page.setCurrentPage(storeSolrParam.getCurrentPage());
        return successGet(page);
    }

    /**
     * 搜索词关联词频查询
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "listStoreSearchWord", method = RequestMethod.GET)
    public Result<List<StoreSearchWordDTO>> listStoreSearchWord(@RequestParam String name, @RequestParam String regionPath) {
        List<StoreSearchWordDTO> searchWordDTOS = new ArrayList<>();
        org.springframework.data.domain.Page<MerchantStoreSolr> keyWordsPage = merchantStoreSolrService.findKeywordssByKeywordssStartingWith(name, regionPath);
        if (keyWordsPage == null || keyWordsPage.getContent() == null || keyWordsPage.getContent().isEmpty()) {
            return successGet(searchWordDTOS);
        }
        for (MerchantStoreSolr item : keyWordsPage.getContent()) {
            for (String keyword : item.getKeywordss()) {
                if (keyword.startsWith(name)) {
                    StoreSearchWordDTO searchWordDTO = new StoreSearchWordDTO();
                    searchWordDTO.setName(keyword);
                    org.springframework.data.domain.Page<MerchantStoreSolr> findCountBykeywordPage = merchantStoreSolrService.findCountBykeyword(keyword, regionPath);
                    searchWordDTO.setCount((int) findCountBykeywordPage.getTotalElements());
                    searchWordDTOS.add(searchWordDTO);
                }
            }
        }

        Set<StoreSearchWordDTO> searchWordDTOSet = new TreeSet<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
        searchWordDTOSet.addAll(searchWordDTOS);
        List<StoreSearchWordDTO> resultDTOS = new ArrayList<>(searchWordDTOSet);
        return successGet(resultDTOS);
    }


    /**
     * 专属特惠(按折扣系数升序)
     *
     * @param discountStoreParam
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "discountStore", method = RequestMethod.POST)
    public Result<Page<StoreSolrDTO>> discountStore(@RequestBody DiscountStoreParam discountStoreParam) {
        org.springframework.data.domain.Page<MerchantStoreSolr> merchantStoreSolrPage = merchantStoreSolrService.discountStore(discountStoreParam);
        if (merchantStoreSolrPage == null || merchantStoreSolrPage.getContent() == null || merchantStoreSolrPage.getContent().isEmpty()) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        Page<StoreSolrDTO> page = new Page<>();
        page.setRecords(MerchantStoreConverter.convertStoreSolrDTOList(merchantStoreSolrPage.getContent()));
        page.setTotalCount((int) merchantStoreSolrPage.getTotalElements());
        page.setCurrentPage(discountStoreParam.getCurrentPage());
        return successGet(page);
    }

}
