package com.lawu.eshop.game.srv.bo;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public class GamePuzzleUserPicUploadNumberBO {

    //上传次数
    private Integer uploadNumber;

    //采用次数
    private Integer usedNumber;

    public Integer getUploadNumber() {
        return uploadNumber;
    }

    public void setUploadNumber(Integer uploadNumber) {
        this.uploadNumber = uploadNumber;
    }

    public Integer getUsedNumber() {
        return usedNumber;
    }

    public void setUsedNumber(Integer usedNumber) {
        this.usedNumber = usedNumber;
    }
}
