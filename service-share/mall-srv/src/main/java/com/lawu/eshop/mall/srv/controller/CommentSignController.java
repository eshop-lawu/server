package com.lawu.eshop.mall.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.mall.dto.CommentSignListDTO;
import com.lawu.eshop.mall.srv.bo.CommentSignBO;
import com.lawu.eshop.mall.srv.converter.CommentSignConverter;
import com.lawu.eshop.mall.srv.service.CommentSignService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
@RestController
@RequestMapping(value = "commentSign")
public class CommentSignController extends BaseController{

    @Autowired
    private CommentSignService commentSignService;


    /**
     * 评价标签列表
     * @return
     */
    @RequestMapping(value = "getCommentSignList", method = RequestMethod.GET)
    public Result<CommentSignListDTO> getCommentSignList() {
        List<CommentSignBO> list = commentSignService.getCommentSignList();
        CommentSignListDTO listDTO = new CommentSignListDTO();
        listDTO.setSignDTOS(CommentSignConverter.convertDTOS(list));
        return successGet(listDTO);
    }

    /**
     * 新增评论标签
     * @param name
     * @return
     */
    @RequestMapping(value = "addCommentSign", method = RequestMethod.POST)
    public Result addCommentSign(@RequestParam("name") String name){
        commentSignService.addCommentSign(name);
        return successCreated();
    }

    @RequestMapping(value = "delCommentSign/{id}", method = RequestMethod.DELETE)
    public Result delCommentSign(@PathVariable("id") Long id){
        commentSignService.delCommentSign(id);
        return successCreated();
    }
}
