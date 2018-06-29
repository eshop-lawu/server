package com.lawu.eshop.mall.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
public class SignDTO {

    private List<CommentSignDTO> signDTOS;

    public List<CommentSignDTO> getSignDTOS() {
        return signDTOS;
    }

    public void setSignDTOS(List<CommentSignDTO> signDTOS) {
        this.signDTOS = signDTOS;
    }
}
