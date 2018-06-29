package com.lawu.eshop.member.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.mall.dto.CommentSignDTO;
import com.lawu.eshop.mall.dto.CommentSignListDTO;
import com.lawu.eshop.mall.dto.CommentSignPageDTO;
import com.lawu.eshop.mall.dto.SignDTO;
import com.lawu.eshop.member.api.service.CommentSignService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

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
public class CommentSignController extends BaseController {

    @Autowired
    private CommentSignService commentSignService;

    @Audit(date = "2018-05-15", reviewer = "孙林青")
    @ApiOperation(value = "评价标签列表", notes = "评价标签列表 [1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getCommentSignList", method = RequestMethod.GET)
    public Result<SignDTO> getCommentSignList() {
        Result<CommentSignListDTO> listDTO = commentSignService.getCommentSignList();
        SignDTO signDTO = new SignDTO();
        List<CommentSignDTO> signDTOS = new ArrayList<>();
        if (!listDTO.getModel().getSignDTOS().isEmpty()) {
            CommentSignDTO commentSignDTO;
            for (CommentSignPageDTO pageDTO : listDTO.getModel().getSignDTOS()) {
                commentSignDTO = new CommentSignDTO();
                commentSignDTO.setName(pageDTO.getName());
                signDTOS.add(commentSignDTO);
            }
        }
        signDTO.setSignDTOS(signDTOS);
        return successGet(signDTO);
    }
}
