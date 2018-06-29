package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.constants.WindowMessageTypeEnum;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public class WindowMessageParam {

    private Long id;

    private String imgPath;

    private Long relateId;

    private WindowMessageTypeEnum typeEnum;

    private WindowMessageStatusEnum statusEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public WindowMessageTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(WindowMessageTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public WindowMessageStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(WindowMessageStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
