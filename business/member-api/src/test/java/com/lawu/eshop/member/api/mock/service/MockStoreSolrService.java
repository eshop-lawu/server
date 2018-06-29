package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.StoreSolrService;
import com.lawu.eshop.user.dto.StoreSearchWordDTO;
import com.lawu.eshop.user.dto.StoreSolrDTO;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.StoreSolrParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
class MockStoreSolrService extends BaseController implements StoreSolrService {

    @Override
    public Result<Page<StoreSolrDTO>> listStore(@ModelAttribute StoreSolrParam storeSolrParam) {
        StoreSolrDTO dto = new StoreSolrDTO();
        dto.setMerchantId(1L);
        dto.setIndustryPath("fdfd");
        dto.setIndustryName("fdfd");
        dto.setMerchantStoreId(1L);
        dto.setAreaName("fdfd");
        dto.setAverageConsumeAmount(Double.parseDouble("1"));
        dto.setAverageScore(Double.parseDouble("3"));
        dto.setDiscountPackage("fdfd");
        dto.setDistance(Double.parseDouble("3"));
        dto.setFavoreInfo("fdfd");
        dto.setFavoriteNumber(1);
        List<StoreSolrDTO> list = new ArrayList<>();
        list.add(dto);
        Page<StoreSolrDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<List<StoreSearchWordDTO>> listStoreSearchWord(@RequestParam("name") String name, @RequestParam("regionPath") String regionPath) {
        return successGet();
    }

    @Override
    public Result<Page<StoreSolrDTO>> discountStore(@ModelAttribute DiscountStoreParam discountStoreParam) {
        StoreSolrDTO dto = new StoreSolrDTO();
        dto.setMerchantId(1L);
        dto.setIndustryPath("fdfd");
        dto.setIndustryName("fdfd");
        dto.setMerchantStoreId(1L);
        dto.setAreaName("fdfd");
        dto.setAverageConsumeAmount(Double.parseDouble("1"));
        dto.setAverageScore(Double.parseDouble("3"));
        dto.setDiscountPackage("fdfd");
        dto.setDistance(Double.parseDouble("3"));
        dto.setFavoreInfo("fdfd");
        dto.setFavoriteNumber(1);
        List<StoreSolrDTO> list = new ArrayList<>();
        list.add(dto);
        Page<StoreSolrDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }
}
