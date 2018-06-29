package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.CommentSignListDTO;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
@FeignClient(value = "mall-srv")
public interface CommentSignService {
    @RequestMapping(value = "commentSign/getCommentSignList", method = RequestMethod.GET)
    Result<CommentSignListDTO> getCommentSignList();

    @RequestMapping(value = "commentSign/delCommentSign/{id}", method = RequestMethod.DELETE)
    Result delCommentSign(@PathVariable("id") Long id);

    @RequestMapping(value = "commentSign/addCommentSign", method = RequestMethod.POST)
    Result addCommentSign(@RequestParam("name") String name);
}
