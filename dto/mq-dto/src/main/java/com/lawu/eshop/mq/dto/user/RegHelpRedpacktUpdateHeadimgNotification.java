package com.lawu.eshop.mq.dto.user;

import java.io.Serializable;

/**
 * @author yangqh
 * @date 2017/12/28
 */
public class RegHelpRedpacktUpdateHeadimgNotification implements Serializable {

    private static final long serialVersionUID = 4620311503903699085L;
    private String helpUserNum;
    private String helpUserHeadimg;

    public String getHelpUserNum() {
        return helpUserNum;
    }

    public void setHelpUserNum(String helpUserNum) {
        this.helpUserNum = helpUserNum;
    }

    public String getHelpUserHeadimg() {
        return helpUserHeadimg;
    }

    public void setHelpUserHeadimg(String helpUserHeadimg) {
        this.helpUserHeadimg = helpUserHeadimg;
    }
}
