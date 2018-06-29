package com.lawu.eshop.user.srv.bo;

/**
 * @author meishuquan
 * @date 2017/4/11.
 */
public class MerchantStoreImageBO {

    private Long id;

    private Byte type;

    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
