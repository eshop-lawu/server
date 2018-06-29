package com.lawu.eshop.jobs.service;

import com.lawu.eshop.product.dto.ProductAllRtnDTO;
import com.lawu.eshop.product.param.ProductCompatibleParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/4/25.
 */
@FeignClient(value = "product-srv")
public interface ProductService {

    /**
     * 更新商品日均销量
     *
     * @param pageSize
     */
    @RequestMapping(method = RequestMethod.PUT, value = "product/executeProductAverageDailySales")
    void executeProductAverageDailySales(@RequestParam("pageSize") Integer pageSize);

    /**
     * 重建商品索引
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/rebuildProductIndex")
    Result rebuildProductIndex(@RequestParam("pageSize") Integer pageSize);

    /**
     * 删除无效的商品索引
     *
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/delInvalidProductIndex")
    Result delInvalidProductIndex(@RequestParam("typeEnum") DelIndexTypeEnum typeEnum);

    /**
     * 查询所有商品，一级分类名称不为空
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/getAllProduct")
    Result<ProductAllRtnDTO> getAllProduct();

    /**
     * 商品型号兼容性处理
     *
     * @param productCompatibleParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/compatible")
    Result compatible(@RequestBody ProductCompatibleParam productCompatibleParam);
}
