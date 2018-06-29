package com.lawu.eshop.mall.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
public class CommentSignListDTO {

    private List<CommentSignPageDTO> signDTOS;

    public List<CommentSignPageDTO> getSignDTOS() {
        return signDTOS;
    }

    public void setSignDTOS(List<CommentSignPageDTO> signDTOS) {
        this.signDTOS = signDTOS;
    }
}
