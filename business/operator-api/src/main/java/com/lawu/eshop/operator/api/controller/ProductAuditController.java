package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.LogService;
import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.operator.api.service.MessageService;
import com.lawu.eshop.operator.api.service.ProductAuditService;
import com.lawu.eshop.operator.api.service.ProductService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.ProductEditInfoDTO;
import com.lawu.eshop.product.dto.ProductInfoDTO;
import com.lawu.eshop.product.dto.ProductQueryDTO;
import com.lawu.eshop.product.param.ListProductParam;
import com.lawu.eshop.user.dto.MerchantViewDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
@Api(tags = "productAudit")
@RestController
@RequestMapping(value = "productAudit/")
public class ProductAuditController extends BaseController {

    @Autowired
    private ProductAuditService productAuditService;

    @Autowired
    private LogService logService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageService messageService;


    @ApiOperation(value = "商品审列表", notes = "查询所有门店上架中商品  [1002]（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("productAudit:list")
    @PageBody
    @RequestMapping(value = "listProduct", method = RequestMethod.POST)
    public Result<Page<ProductQueryDTO>> listProduct(@RequestBody @ApiParam ListProductParam listProductParam) {
        Result<Page<ProductQueryDTO>> result = productAuditService.listProduct(listProductParam);
        if(result != null && !result.getModel().getRecords().isEmpty()){
            for(ProductQueryDTO productQueryDTO : result.getModel().getRecords()){
                Result<MerchantViewDTO> merchantViewDTOResult = merchantService.getMerchantView(productQueryDTO.getMerchantId());
                if(isSuccess(merchantViewDTOResult)){
                    productQueryDTO.setStoreName(merchantViewDTOResult.getModel().getStoreName());
                    productQueryDTO.setAccount(merchantViewDTOResult.getModel().getAccount());
                }
            }
        }
        return result;
    }

    @ApiOperation(value = "查询商品详情", notes = "编辑商品时，根据商品ID查询商品详情信息，[1002|1003]，（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("productAudit:detail")
    @RequestMapping(value = "getProduct/{id}", method = RequestMethod.GET)
    public Result<ProductEditInfoDTO> getProductById(@PathVariable @ApiParam(name = "id", required = true, value = "商品ID") Long id) {
        return productAuditService.selectEditProductById(id);
    }

    @ApiOperation(value = "商品批量下架", notes = "商品批量下架，[1002]。(梅述全)", httpMethod = "POST")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.PRODUCT_DOWN)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("productAudit:soldOut")
    @RequestMapping(value = "downProduct", method = RequestMethod.POST)
    public Result downProduct(@RequestParam @ApiParam(required = true, value = "商品ID(多个英文逗号分开)") String ids,
   		 @RequestParam @ApiParam(required = true, value = "审核备注") String remark) {
        //Result result = productAuditService.updateProductStatus(ids, ProductStatusEnum.PRODUCT_STATUS_DOWN);
    	Result result = productService.downOperatorById(Long.valueOf(ids), remark);
    	if(!isSuccess(result)){
            return result;
        }
    	Result<ProductInfoDTO> res = productService.selectProductById(Long.valueOf(ids));

    	MessageInfoParam messageInfoParam = new MessageInfoParam();
		MessageTempParam messageTempParam = new MessageTempParam();
		messageTempParam.setAdName(res.getModel().getName());
		messageInfoParam.setRelateId(Long.valueOf(ids));
		messageTempParam.setFailReason(remark);
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PRODUCT_FORCE_DOWN);
		messageInfoParam.setMessageParam(messageTempParam);

		messageService.saveMessage(res.getModel().getMerchantUserNum(), messageInfoParam);

        return result;
    }

    @ApiOperation(value = "商品批量删除", notes = "商品批量删除，[1002]。(梅述全)", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.DELETE ,title = LogTitleEnum.PRODUCT_DELETE)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("productAudit:del")
    @RequestMapping(value = "deleteProduct", method = RequestMethod.PUT)
    public Result deleteProduct(@RequestParam @ApiParam(required = true, value = "商品ID(多个英文逗号分开)") String ids,
                                @RequestParam @ApiParam(required = true, value = "商家ID") Long merchantId) {
        Result result = productAuditService.updateProductStatus(ids, ProductStatusEnum.PRODUCT_STATUS_DEL, merchantId);
        if (!isSuccess(result)) {
            return result;
        }

        return result;
    }

}
