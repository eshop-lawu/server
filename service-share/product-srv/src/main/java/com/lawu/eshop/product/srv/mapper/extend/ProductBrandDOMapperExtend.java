package com.lawu.eshop.product.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.product.param.OperatorProductBrandParam;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductBrandDOView;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
public interface ProductBrandDOMapperExtend {
    List<OperatorProductBrandDOView> getOperatorProductBrandList(OperatorProductBrandParam param);

    int getOperatorProductBrandListCount(OperatorProductBrandParam param);
}
