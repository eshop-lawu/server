package com.lawu.eshop.operator.api.controller;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.eshop.mall.dto.SearchWordDTO;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.eshop.operator.api.service.SearchWordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
@Api(tags = "searchWord")
@RestController
@RequestMapping(value = "searchWord/")
public class SearchWordController extends BaseController {

    @Autowired
    private SearchWordService searchWordService;

    @ApiOperation(value = "新增搜索词条", notes = "新增搜索词条。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("searchWord:add")
    @RequestMapping(value = "saveSearchWord", method = RequestMethod.POST)
    public Result saveSearchWord(@RequestParam @ApiParam(name = "word", required = true, value = "词条") String word,
                                 SearchWordTypeEnum searchWordTypeEnum) {
        return searchWordService.saveSearchWord(word, searchWordTypeEnum);
    }

    @ApiOperation(value = "删除词条", notes = "根据ID删除词条。[1002]（梅述全）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequiresPermissions("searchWord:del")
    @RequestMapping(value = "deleteSearchWord/{id}", method = RequestMethod.DELETE)
    public Result deleteSearchWord(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id) {
        return searchWordService.deleteSearchWordById(id);
    }

    @ApiOperation(value = "词条列表", notes = "词条列表。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("searchWord:list")
    @PageBody
    @RequestMapping(value = "listSearchWord", method = RequestMethod.GET)
    public Result<Page<SearchWordDTO>> listSearchWord(@RequestBody @ApiParam SearchWordParam searchWordParam) {
        return searchWordService.listSearchWord(searchWordParam);
    }
}
