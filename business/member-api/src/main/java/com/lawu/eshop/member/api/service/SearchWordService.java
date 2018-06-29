package com.lawu.eshop.member.api.service;

import com.lawu.eshop.mall.dto.SearchWordDTO;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
@FeignClient(value = "mall-srv")
public interface SearchWordService {

    /**
     * 搜索词条列表
     *
     * @param searchWordParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "searchWord/listSearchWord")
    Result<Page<SearchWordDTO>> listSearchWord(@ModelAttribute SearchWordParam searchWordParam);
}
