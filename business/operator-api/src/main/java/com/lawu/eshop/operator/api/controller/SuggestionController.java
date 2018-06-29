package com.lawu.eshop.operator.api.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.dto.SuggestionDTO;
import com.lawu.eshop.mall.param.SuggestionListParam;
import com.lawu.eshop.operator.api.service.SuggestionService;
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
 * @author zhangyong
 * @date 2017/4/10.
 */
@Api(tags = "suggestionOperator")
@RestController
@RequestMapping(value = "suggestionOperator/")
public class SuggestionController extends BaseController {

    @Autowired
    private SuggestionService suggestionService;

    @PageBody
    @ApiOperation(value = "意见记录列表", notes = "可以根据时间段查询已经反馈记录 [1004,1002]", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("suggest:list")
    @RequestMapping(value = "suggestion/getSuggestionList", method = RequestMethod.POST)
    public Result<Page<SuggestionDTO>> getSuggestionList(@RequestBody SuggestionListParam pageParam) {
        if (pageParam == null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Result<Page<SuggestionDTO>> pageResult = suggestionService.getSuggestionList(pageParam);
        return pageResult;
    }
    
    @ApiOperation(value = "删除反馈意见", notes = "删除反馈意见 []（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "delSuggestion/{id}", method = RequestMethod.PUT)
    public Result delCommentProductInfo(@PathVariable("id") @ApiParam(value = "反馈ID",required = true) Long id){
        if(id == null){
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        return  suggestionService.delSuggestion(id);
    }
    
    @ApiOperation(value = "批量删除反馈意见", notes = "批量删除反馈意见 []（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequiresPermissions("suggest:del")
    @RequestMapping(value = "batchelDelSuggestion/", method = RequestMethod.PUT)
    public Result batchelDelSuggestion(@RequestParam @ApiParam(value = "反馈ID集合 以逗号隔开",required = true) String ids){
        if(ids == null){
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        String[] commentIds=ids.split(",");
        for (String id : commentIds) {
        	 suggestionService.delSuggestion(Long.parseLong(id));
		}
       
        return  successCreated();
    }
}
