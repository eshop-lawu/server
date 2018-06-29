package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.mall.dto.WindowMessageListDTO;
import com.lawu.eshop.member.api.service.WindowMessageService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
@Api(tags = "windowMessage")
@RestController
@RequestMapping(value = "windowMessage/")
public class WindowMessageController extends BaseController {

    @Autowired
    private WindowMessageService windowMessageService;

    @Audit(date = "2018-03-01", reviewer = "孙林青")
    @ApiOperation(value = "弹窗消息列表", notes = "弹窗消息列表（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listWindowMessage", method = RequestMethod.GET)
    public Result<WindowMessageListDTO> listWindowMessage() {
        Result<WindowMessageListDTO> result = windowMessageService.listWindowMessage();
        return successGet(result);
    }

}
