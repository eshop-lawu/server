package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.eshop.mall.dto.SearchWordDTO;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
@FeignClient(value = "mall-srv")
public interface SearchWordService {

    /**
     * 新增搜索词条
     *
     * @param word
     * @param searchWordTypeEnum
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "searchWord/saveSearchWord")
    Result saveSearchWord(@RequestParam("word") String word, @RequestParam("searchWordTypeEnum") SearchWordTypeEnum searchWordTypeEnum);

    /**
     * 根据ID删除词条
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "searchWord/deleteSearchWord/{id}")
    Result deleteSearchWordById(@PathVariable("id") Long id);

    /**
     * 词条列表
     *
     * @param searchWordParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "searchWord/listSearchWord")
    Result<Page<SearchWordDTO>> listSearchWord(@ModelAttribute SearchWordParam searchWordParam);
}
