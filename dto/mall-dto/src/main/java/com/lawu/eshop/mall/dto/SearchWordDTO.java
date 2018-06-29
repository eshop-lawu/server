package com.lawu.eshop.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
public class SearchWordDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "词条")
    private String word;

    @ApiModelProperty(value = "词条类型：WORD_TYPE_STORE--门店词条，WORD_TYPE_PRODUCT--商品词条")
    private SearchWordTypeEnum searchWordTypeEnum;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public SearchWordTypeEnum getSearchWordTypeEnum() {
        return searchWordTypeEnum;
    }

    public void setSearchWordTypeEnum(SearchWordTypeEnum searchWordTypeEnum) {
        this.searchWordTypeEnum = searchWordTypeEnum;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
