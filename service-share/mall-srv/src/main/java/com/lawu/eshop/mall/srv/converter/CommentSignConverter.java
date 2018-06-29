package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.dto.CommentSignPageDTO;
import com.lawu.eshop.mall.srv.bo.CommentSignBO;
import com.lawu.eshop.mall.srv.domain.CommentSignDO;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
public class CommentSignConverter {
    public static List<CommentSignBO> convertBOS(List<CommentSignDO> signDOS) {
        if (signDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<CommentSignBO> signBOS = new ArrayList<>();
        CommentSignBO signBO;
        for (CommentSignDO signDO : signDOS) {
            signBO = new CommentSignBO();
            signBO.setId(signDO.getId());
            signBO.setName(signDO.getName());
            signBO.setGmtCreate(signDO.getGmtCreate());
            signBOS.add(signBO);
        }
        return signBOS;
    }

    public static List<CommentSignPageDTO> convertDTOS(List<CommentSignBO> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<CommentSignPageDTO> pageDTOS = new ArrayList<>();
        CommentSignPageDTO pageDTO;
        for (CommentSignBO signBO : list) {
            pageDTO = new CommentSignPageDTO();
            pageDTO.setId(signBO.getId());
            pageDTO.setName(signBO.getName());
            pageDTO.setGmtCreate(signBO.getGmtCreate());
            pageDTOS.add(pageDTO);
        }
        return pageDTOS;
    }
}
