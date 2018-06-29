package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
public class SearchWordParam extends AbstractPageParam {

    @ApiModelProperty(value = "词条类型：WORD_TYPE_STORE--门店词条，WORD_TYPE_PRODUCT--商品词条")
    private SearchWordTypeEnum searchWordTypeEnum;

    public SearchWordTypeEnum getSearchWordTypeEnum() {
        return searchWordTypeEnum;
    }

    public void setSearchWordTypeEnum(SearchWordTypeEnum searchWordTypeEnum) {
        this.searchWordTypeEnum = searchWordTypeEnum;
    }
}
