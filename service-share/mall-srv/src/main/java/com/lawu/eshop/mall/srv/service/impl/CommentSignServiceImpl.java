package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.srv.bo.CommentSignBO;
import com.lawu.eshop.mall.srv.converter.CommentSignConverter;
import com.lawu.eshop.mall.srv.domain.CommentSignDO;
import com.lawu.eshop.mall.srv.mapper.CommentSignDOMapper;
import com.lawu.eshop.mall.srv.service.CommentSignService;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
@Service
public class CommentSignServiceImpl implements CommentSignService {

    @Autowired
    private CommentSignDOMapper commentSignDOMapper;

    @Override
    public List<CommentSignBO> getCommentSignList() {
        List<CommentSignDO> signDOS = commentSignDOMapper.selectByExample(null);
        return CommentSignConverter.convertBOS(signDOS);
    }

    @Override
    @Transactional
    public void addCommentSign(String name) {
        CommentSignDO signDO = new CommentSignDO();
        signDO.setName(name);
        signDO.setGmtCreate(new Date());
        commentSignDOMapper.insertSelective(signDO);
    }

    @Override
    @Transactional
    public void delCommentSign(Long id) {
        commentSignDOMapper.deleteByPrimaryKey(id);
    }
}
