package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.dto.CommentSignListDTO;
import com.lawu.eshop.mall.dto.CommentSignPageDTO;
import com.lawu.eshop.operator.api.service.CommentSignService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
@Api(tags = "commentSign")
@RestController
@RequestMapping("commentSign/")
public class CommentSignController extends BaseController{

    @Autowired
    private CommentSignService commentSignService;

    @PageBody
    @ApiOperation(value = "评价标签列表", notes = "评价标签列表 [1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("commentSign:list")
    @RequestMapping(value = "getCommentSignList", method = RequestMethod.GET)
    public Result<Page<CommentSignPageDTO>> getCommentSignList() {
        Result<CommentSignListDTO> listDTOResult = commentSignService.getCommentSignList();
        Page<CommentSignPageDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(listDTOResult.getModel().getSignDTOS().size());
        page.setRecords(listDTOResult.getModel().getSignDTOS());
        return successGet(page);
    }

    @ApiOperation(value = "添加标签", notes = "添加标签(章勇)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "addCommentSign", method = RequestMethod.POST)
    public Result addCommentSign(@RequestParam("name") String name) {
        commentSignService.addCommentSign(name);
        return successCreated();
    }

    @ApiOperation(value = "删除标签", notes = "删除标签(章勇)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "delCommentSign/{id}", method = RequestMethod.PUT)
    public Result delCommentSign(@PathVariable("id") Long id) {
        commentSignService.delCommentSign(id);
        return successCreated();
    }

}
