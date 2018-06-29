package com.lawu.eshop.product.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.product.param.OperatorProductSpecParam;
import com.lawu.eshop.product.param.OperatorSpecOptionListParam;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductSpecDOView;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductSpecOptionDOView;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
public interface ProductSpecDOMapperExtend {
    List<OperatorProductSpecDOView> getOperatorProductSpecList(OperatorProductSpecParam param);

    int getOperatorProductSpecListCount(OperatorProductSpecParam param);

    List<OperatorProductSpecOptionDOView> getOperatorSpecOptionList(OperatorSpecOptionListParam param);

    int getOperatorSpecOptionListCount(OperatorSpecOptionListParam param);
}
