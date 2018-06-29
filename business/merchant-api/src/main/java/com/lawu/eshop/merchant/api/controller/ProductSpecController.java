package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.ProductService;
import com.lawu.eshop.merchant.api.service.ProductSpecOptionService;
import com.lawu.eshop.merchant.api.service.ProductSpecService;
import com.lawu.eshop.product.dto.*;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品规格，规格选项
 */
@Api(tags = "productSpec")
@RestController
@RequestMapping(value = "productSpec/")
public class ProductSpecController extends BaseController {

    @Autowired
    private ProductSpecService productSpecService;
    @Autowired
    private ProductSpecOptionService productSpecOptionService;

    @Autowired
    private ProductService productService;

    @Audit(date = "2018-04-18", reviewer = "孙林青")
    @ApiOperation(value = "根据商品类目下规格、规格选项信息", notes = "根据商品类目下规格、规格选项信息(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "getBrandByCategoryId/{productCategoryId}", method = RequestMethod.GET)
    public Result<ProductSpecAndOptionsRtnDTO> getBrandByCategoryId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                    @PathVariable @ApiParam(name = "productCategoryId", required = true, value = "类目ID") Integer productCategoryId,
                                                                    @RequestParam @ApiParam(name = "productId", required = true, value = "商品ID，没有传0") Long productId) {
        Result<ProductSpecDTO> specResult = productSpecService.getProductSpecByCategoryId(productCategoryId);
        List<ProductSpecListDTO> specList = specResult.getModel() == null ? null : specResult.getModel().getSpecList();

        // 获取商品选中的规格选项信息，回显时默认选中
        List<ProductSpecJsonDTO> productSpecJsonList = new ArrayList<>();
        if (productId != null && productId > 0) {
            Result<List<ProductSpecJsonDTO>> result = productService.getProductSelectedSpecInfo(productId);
            productSpecJsonList = result.getModel();
        }

        List<ProductSpecJsonDTO> productSpecRtnDTOList = new ArrayList<>();
        for (ProductSpecListDTO spec : specList) {
            ProductSpecJsonDTO productSpecRtnDTO = new ProductSpecJsonDTO();
            productSpecRtnDTO.setSpecId(spec.getId());
            productSpecRtnDTO.setSpecName(spec.getSpecName());
            Result<ProductSpecOptionDTO> specOptionResult = productSpecOptionService.getSpecOptionBySpecId(spec.getId());
            List<ProductSpecOptionListDTO> optionList = specOptionResult.getModel() == null ? null : specOptionResult.getModel().getOptionList();
            List<ProductSpecOptionJsonDTO> specOptions = new ArrayList<>();

            //遍历系统设置的规格选项，存在商家选中的标记selected=true
            boolean isExistCustom = false;
            if (optionList != null && !optionList.isEmpty()) {
                for (ProductSpecOptionListDTO option : optionList) {
                    ProductSpecOptionJsonDTO optionRtnDTO = new ProductSpecOptionJsonDTO();
                    optionRtnDTO.setId(option.getId());
                    optionRtnDTO.setName(option.getOptionName());
                    optionRtnDTO.setIcon(option.getIconUrl());
                    optionRtnDTO.setSelected(false);
                    //存在商家自定义规格
                    if (productSpecJsonList != null && !productSpecJsonList.isEmpty()) {
                        for (ProductSpecJsonDTO selectedSpec : productSpecJsonList) {
                            if (spec.getId().longValue() == selectedSpec.getSpecId().longValue()) {
                                List<ProductSpecOptionJsonDTO> selectedOptions = selectedSpec.getOptions();
                                for (ProductSpecOptionJsonDTO selectedOption : selectedOptions) {
                                    if (selectedOption.getId() < 0) isExistCustom = true;//存在商家自定义选项
                                    if (option.getId().longValue() == selectedOption.getId().longValue()) {
                                        optionRtnDTO.setSelected(true);
                                    }
                                }
                            }
                        }
                    }
                    specOptions.add(optionRtnDTO);
                }
            } else if (productSpecJsonList != null && !productSpecJsonList.isEmpty()) {
                // 系统定义了规格，未定义选项的情况，商家自定义过选项
                for (ProductSpecJsonDTO selectedSpec : productSpecJsonList) {
                    if (spec.getId().longValue() == selectedSpec.getSpecId().longValue()) {
                        List<ProductSpecOptionJsonDTO> selectedOptions = selectedSpec.getOptions();
                        for (ProductSpecOptionJsonDTO selectedOption : selectedOptions) {
                            ProductSpecOptionJsonDTO optionRtnDTO = new ProductSpecOptionJsonDTO();
                            optionRtnDTO.setId(selectedOption.getId());
                            optionRtnDTO.setName(selectedOption.getName());
                            optionRtnDTO.setIcon(selectedOption.getIcon());
                            optionRtnDTO.setSelected(true);
                            specOptions.add(optionRtnDTO);
                        }
                    }
                }
            }
            //规格选项中存在商家自定义的情况
            if (isExistCustom) {
                //遍历商家自定义规格选项，直接selected=true
                for (ProductSpecJsonDTO selectedSpec : productSpecJsonList) {
                    if (spec.getId().longValue() == selectedSpec.getSpecId().longValue()) {
                        List<ProductSpecOptionJsonDTO> selectedOptions = selectedSpec.getOptions();
                        for (ProductSpecOptionJsonDTO selectedOption : selectedOptions) {
                            if (selectedOption.getId() < 0) {
                                ProductSpecOptionJsonDTO optionRtnDTO = new ProductSpecOptionJsonDTO();
                                optionRtnDTO.setId(selectedOption.getId());
                                optionRtnDTO.setName(selectedOption.getName());
                                optionRtnDTO.setIcon(selectedOption.getIcon());
                                optionRtnDTO.setSelected(true);
                                specOptions.add(optionRtnDTO);
                            }
                        }
                    }
                }
            }
            productSpecRtnDTO.setOptions(specOptions);
            productSpecRtnDTOList.add(productSpecRtnDTO);
        }

        //存在商家自定义规格的情况，直接将该规格下选项全部设置selected=true
        if (productSpecJsonList != null && !productSpecJsonList.isEmpty()) {
            for (ProductSpecJsonDTO selectedSpec : productSpecJsonList) {
                if (selectedSpec.getSpecId() < 0) {
                    ProductSpecJsonDTO productSpecRtnDTO = new ProductSpecJsonDTO();
                    productSpecRtnDTO.setSpecId(selectedSpec.getSpecId());
                    productSpecRtnDTO.setSpecName(selectedSpec.getSpecName());
                    List<ProductSpecOptionJsonDTO> specOptions = new ArrayList<>();
                    for (ProductSpecOptionJsonDTO selectedOption : selectedSpec.getOptions()) {
                        ProductSpecOptionJsonDTO optionRtnDTO = new ProductSpecOptionJsonDTO();
                        optionRtnDTO.setId(selectedOption.getId());
                        optionRtnDTO.setName(selectedOption.getName());
                        optionRtnDTO.setIcon(selectedOption.getIcon());
                        optionRtnDTO.setSelected(true);
                        specOptions.add(optionRtnDTO);
                    }
                    productSpecRtnDTO.setOptions(specOptions);
                    productSpecRtnDTOList.add(productSpecRtnDTO);
                }
            }
        }

        ProductSpecAndOptionsRtnDTO rtn = new ProductSpecAndOptionsRtnDTO();
        rtn.setProductSpecRtnDTOList(productSpecRtnDTOList);
        return successCreated(rtn);
    }

}
