package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.eshop.mall.srv.bo.SearchWordBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
public interface SearchWordService {

    /**
     * 新增搜索词条
     *
     * @param word
     * @param searchWordTypeEnum
     */
    void saveSearchWord(String word, SearchWordTypeEnum searchWordTypeEnum);

    /**
     * 根据ID查询词条
     *
     * @param id
     * @return
     */
    SearchWordBO getSearchWordById(Long id);

    /**
     * 根据ID删除词条
     *
     * @param id
     */
    void deleteSearchWordById(Long id);

    /**
     * 查询词条列表
     *
     * @param searchWordParam
     * @return
     */
    Page<SearchWordBO> listSearchWord(SearchWordParam searchWordParam);
}
