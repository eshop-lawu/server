package com.lawu.eshop.operator.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.operator.api.service.AdLexiconService;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 描述：广告管理
 * @author zhangrc
 * @date 2017/04/5
 */
@Api(tags = "adLexicon")
@RestController
@RequestMapping(value = "adLexicon/")
public class AdLexiconController {
	
	@Autowired
	private AdLexiconService adLexiconService;
	
	/**
     * 添加词库
     * @param title
     * @return
     */
    @ApiOperation(value = "添加词库", notes = "添加词库[]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result addAddress(@RequestParam @ApiParam(required = true, value = "名称") String title) {
        Result rs = adLexiconService.save(title);
        return rs;
    }
}
