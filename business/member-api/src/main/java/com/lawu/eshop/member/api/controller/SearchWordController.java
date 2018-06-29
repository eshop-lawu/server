package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.mall.dto.SearchWordDTO;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.eshop.member.api.service.SearchWordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

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

    @AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "搜索词条列表", notes = "门店、商品搜索词条列表。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listSearchWord", method = RequestMethod.GET)
    public Result<Page<SearchWordDTO>> listSearchWord(@ModelAttribute @ApiParam SearchWordParam searchWordParam) {
        return searchWordService.listSearchWord(searchWordParam);
    }
}
