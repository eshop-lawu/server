package com.lawu.eshop.mall.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.eshop.mall.dto.SearchWordDTO;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.eshop.mall.srv.bo.SearchWordBO;
import com.lawu.eshop.mall.srv.converter.SearchWordConverter;
import com.lawu.eshop.mall.srv.service.SearchWordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
@RestController
@RequestMapping(value = "searchWord/")
public class SearchWordController extends BaseController {

    @Autowired
    private SearchWordService searchWordService;

    /**
     * 新增搜索词条
     *
     * @param word
     * @param searchWordTypeEnum
     * @return
     */
    @RequestMapping(value = "saveSearchWord", method = RequestMethod.POST)
    public Result saveSearchWord(@RequestParam String word, @RequestParam SearchWordTypeEnum searchWordTypeEnum) {
        searchWordService.saveSearchWord(word, searchWordTypeEnum);
        return successCreated();
    }

    /**
     * 根据ID删除词条
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteSearchWord/{id}", method = RequestMethod.DELETE)
    public Result deleteSearchWord(@PathVariable Long id) {
        SearchWordBO searchWordBO = searchWordService.getSearchWordById(id);
        if (searchWordBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        searchWordService.deleteSearchWordById(id);
        return successDelete();
    }

    /**
     * 词条列表
     *
     * @param searchWordParam
     * @return
     */
    @RequestMapping(value = "listSearchWord", method = RequestMethod.POST)
    public Result<Page<SearchWordDTO>> listSearchWord(@RequestBody SearchWordParam searchWordParam) {
        Page<SearchWordBO> searchWordBOPage = searchWordService.listSearchWord(searchWordParam);
        Page<SearchWordDTO> page = new Page<>();
        page.setRecords(SearchWordConverter.convertDTO(searchWordBOPage.getRecords()));
        page.setCurrentPage(searchWordBOPage.getCurrentPage());
        page.setTotalCount(searchWordBOPage.getTotalCount());
        return successGet(page);
    }

}
