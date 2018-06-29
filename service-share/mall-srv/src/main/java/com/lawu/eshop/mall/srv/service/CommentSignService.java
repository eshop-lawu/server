package com.lawu.eshop.mall.srv.service;

import java.util.List;

import com.lawu.eshop.mall.srv.bo.CommentSignBO;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
public interface CommentSignService {
    List<CommentSignBO> getCommentSignList();

    void addCommentSign(String name);

    void delCommentSign(Long id);
}
