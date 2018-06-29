package com.lawu.eshop.product.srv.bo;

/**
 * Created by Yangqh
 * on 2017/3/22.
 */
public class ProductCategoryBO {

    private Integer id;

    private String name;

    private Integer parentId;

    private String path;

    private Integer level;

    private Byte type;

    private Boolean statue;

    private Boolean isVirtual;

    private Integer ordinal;

    private String imageUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getStatue() {
        return statue;
    }

    public void setStatue(Boolean statue) {
        this.statue = statue;
    }

    public Boolean getVirtual() {
        return isVirtual;
    }

    public void setVirtual(Boolean virtual) {
        isVirtual = virtual;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
