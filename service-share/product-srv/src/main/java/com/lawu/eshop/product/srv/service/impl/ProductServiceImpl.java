package com.lawu.eshop.product.srv.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.product.param.*;
import com.lawu.eshop.product.srv.bo.*;
import com.lawu.eshop.product.srv.exception.*;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.constant.ProductImgTypeEnum;
import com.lawu.eshop.product.constant.ProductModelInventoryTypeEnum;
import com.lawu.eshop.product.constant.ProductPositionEnum;
import com.lawu.eshop.product.constant.ProductQueryTypeEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.MemberProductImageDetailDTO;
import com.lawu.eshop.product.dto.MemberProductModelDTO;
import com.lawu.eshop.product.dto.ProductSpecJsonDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionJsonDTO;
import com.lawu.eshop.product.query.ProductDataQuery;
import com.lawu.eshop.product.query.ProductListQuery;
import com.lawu.eshop.product.srv.bo.ProductBO;
import com.lawu.eshop.product.srv.bo.ProductBrandBO;
import com.lawu.eshop.product.srv.bo.ProductCategoryBO;
import com.lawu.eshop.product.srv.bo.ProductCategoryTierBO;
import com.lawu.eshop.product.srv.bo.ProductDetailBO;
import com.lawu.eshop.product.srv.bo.ProductEditDetailBO;
import com.lawu.eshop.product.srv.bo.ProductEditInfoBO;
import com.lawu.eshop.product.srv.bo.ProductInfoBO;
import com.lawu.eshop.product.srv.bo.ProductListBO;
import com.lawu.eshop.product.srv.bo.ProductModelBO;
import com.lawu.eshop.product.srv.bo.ProductModelInfoBO;
import com.lawu.eshop.product.srv.bo.ProductQueryBO;
import com.lawu.eshop.product.srv.bo.ProductRelateAdInfoBO;
import com.lawu.eshop.product.srv.bo.ProductSpecBO;
import com.lawu.eshop.product.srv.bo.ProductSpecOptionBO;
import com.lawu.eshop.product.srv.bo.ProductTypeCountBO;
import com.lawu.eshop.product.srv.converter.ProductConverter;
import com.lawu.eshop.product.srv.converter.ProductModelConverter;
import com.lawu.eshop.product.srv.converter.ProductSpecConverter;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductDOExample;
import com.lawu.eshop.product.srv.domain.ProductDOExample.Criteria;
import com.lawu.eshop.product.srv.domain.ProductImageDO;
import com.lawu.eshop.product.srv.domain.ProductImageDOExample;
import com.lawu.eshop.product.srv.domain.ProductModelDO;
import com.lawu.eshop.product.srv.domain.ProductModelDOExample;
import com.lawu.eshop.product.srv.domain.ProductModelInventoryDO;
import com.lawu.eshop.product.srv.domain.extend.ProductCategoryItemDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductCityDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductModelDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductNumsView;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductImageDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductModelDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductModelInventoryDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.ProductDOMapperExtend;
import com.lawu.eshop.product.srv.mapper.extend.ProductModelDOMapperExtend;
import com.lawu.eshop.product.srv.service.ProductBrandService;
import com.lawu.eshop.product.srv.service.ProductCategoryService;
import com.lawu.eshop.product.srv.service.ProductCustomSpecService;
import com.lawu.eshop.product.srv.service.ProductModelService;
import com.lawu.eshop.product.srv.service.ProductService;
import com.lawu.eshop.product.srv.service.ProductSpecOptionService;
import com.lawu.eshop.product.srv.service.ProductSpecService;
import com.lawu.eshop.product.srv.solr.service.ProductSolrService;
import com.lawu.eshop.solr.impl.entity.ProductSolr;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;
import com.lawu.utils.StringUtil;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDOMapper productDOMapper;

    @Autowired
    private ProductModelDOMapper productModelDOMapper;

    @Autowired
    private ProductModelInventoryDOMapper productModelInventoryDOMapper;

    @Autowired
    private ProductImageDOMapper productImageDOMapper;

    @Autowired
    private ProductCategoryeDOMapper productCategoryeDOMapper;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductDOMapperExtend productDOMapperExtend;

    @Autowired
    private ProductModelDOMapperExtend productModelDOMapperExtend;

    @Autowired
    private ProductSpecService productSpecService;

    @Autowired
    private ProductSpecOptionService productSpecOptionService;

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private ProductModelService productModelService;

    @Autowired
    @Qualifier("delProductCommentTransactionMainServiceImpl")
    private TransactionMainService<Reply> delProductCommentTransactionMainServiceImpl;

    @Autowired
    private ProductSolrService productSolrService;

    @Autowired
    private ProductCustomSpecService productCustomSpecService;

    @Override
    public Page<ProductQueryBO> selectProduct(ProductDataQuery query) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andMerchantIdEqualTo(query.getMerchantId()).andStatusEqualTo(query.getProductStatus().getVal());
        if (query.getName() != null && !"".equals(query.getName())) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        if (query.getCategoryId() != null && !"".equals(query.getCategoryId())) {
            criteria.andCategoryIdEqualTo(Integer.valueOf(query.getCategoryId()));
        }
        example.setOrderByClause(query.getProductSortFieldEnum().getVal() + " " + query.getOrderType());

        // 查询总数
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        Page<ProductQueryBO> page = new Page<>();
        page.setTotalCount(Long.valueOf(productDOMapper.countByExample(example)).intValue());
        page.setCurrentPage(query.getCurrentPage());
        List<ProductDO> productDOS = productDOMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);

        List<ProductQueryBO> productBOS = new ArrayList<ProductQueryBO>();

        for (ProductDO productDO : productDOS) {

            String specJson = "";
            String category = "";
            if (!query.getIsApp()) {
                ProductModelDOExample modelExample = new ProductModelDOExample();
                modelExample.createCriteria().andProductIdEqualTo(productDO.getId()).andStatusEqualTo(true);
                // 查询商品型号
                List<ProductModelDO> productModelDOS = productModelDOMapper.selectByExample(modelExample);
                List<ProductModelBO> ProductModelBOS = new ArrayList<ProductModelBO>();
                for (ProductModelDO productModelDO : productModelDOS) {
                    ProductModelBO productModelBO = ProductModelConverter.convertBO(productModelDO);
                    ProductModelBOS.add(productModelBO);
                }
                specJson = JSON.toJSONString(ProductModelBOS);
                category = productCategoryService.getFullName(productDO.getCategoryId());
            }
            ProductQueryBO productBO = ProductConverter.convertQueryBO(productDO);
            productBO.setSpec(specJson);

            productBO.setCategory(category);

            productBOS.add(productBO);
        }
        page.setRecords(productBOS);

        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateProductStatus(String ids, ProductStatusEnum productStatus, Long merchantId,List<Long> newUpIds) {
        int rows = 0;
        List<Long> delList = new ArrayList<>();
        List<ProductSolr> saveList = new ArrayList<>();
        String idArray[] = ids.split(",");
        for (int i = 0; i < idArray.length; i++) {
            ProductDO productDOEdit = new ProductDO();
            ProductDO productDO = productDOMapper.selectByPrimaryKey(Long.valueOf(idArray[i]));

            if (ProductStatusEnum.PRODUCT_STATUS_UP.getVal().equals(productStatus.getVal())) {
                if (productDO.getTotalInventory() < 1) {
                    return -1;
                }
                //如果第一次上架更新上新时间，计入瑞奇岛每日上新任务
                if (productDO.getGmtFinishRichTask() == null) {
                    productDOEdit.setGmtFinishRichTask(new Date());
                    if (newUpIds == null) newUpIds = new ArrayList<>();
                    newUpIds.add(Long.valueOf(idArray[i]));
                }
                productDOEdit.setGmtUp(new Date());
            }

            productDOEdit.setStatus(productStatus.getVal());
            if (ProductStatusEnum.PRODUCT_STATUS_DOWN.equals(productStatus)) {
                productDOEdit.setGmtDown(new Date());
                productDOEdit.setPosition(ProductPositionEnum.CANCEL.getVal());
            } else {
                productDOEdit.setGmtDown(null);
            }
            productDOEdit.setGmtModified(new Date());
            ProductDOExample example = new ProductDOExample();
            example.createCriteria().andIdEqualTo(Long.valueOf(idArray[i])).andMerchantIdEqualTo(merchantId);
            int row = productDOMapper.updateByExampleSelective(productDOEdit, example);
            rows = rows + row;

            //更新solr索引
            if (productStatus.getVal().byteValue() == ProductStatusEnum.PRODUCT_STATUS_DEL.getVal()
                    || productStatus.getVal().byteValue() == ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal()) {
                delList.add(Long.valueOf(idArray[i]));
            }
            if (productStatus.getVal().byteValue() == ProductStatusEnum.PRODUCT_STATUS_UP.getVal()) {
                saveList.add(ProductConverter.convertProductSolr(productDO));
            }
        }
        if (!delList.isEmpty()) {
            productSolrService.delete(delList);
        }
        if (!saveList.isEmpty()) {
            productSolrService.save(saveList);
        }
        return rows;
    }

    @Override
    public ProductInfoBO selectProductById(Long id) {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(id);
        if (productDO == null) {
            return null;
        }

        ProductInfoBO productInfoBO = ProductConverter.convertInfoBO(productDO);
        String featureImage = productInfoBO.getFeatureImage();
        featureImage = featureImage.replaceAll("\\\\", "/");
        productInfoBO.setFeatureImage(featureImage);

        // 查询商品型号
        ProductModelDOExample modelExample = new ProductModelDOExample();
        modelExample.createCriteria().andProductIdEqualTo(productDO.getId()).andStatusEqualTo(true);
        List<ProductModelDO> productModelDOS = productModelDOMapper.selectByExample(modelExample);
        if (productModelDOS == null || productModelDOS.isEmpty()) {
            return null;
        }
        List<MemberProductModelDTO> spec = new ArrayList<MemberProductModelDTO>();
        BigDecimal minPrice = new BigDecimal("0");
        BigDecimal maxPrice = new BigDecimal("0");
        int k = 0;
        for (ProductModelDO mdo : productModelDOS) {
            MemberProductModelDTO dto = new MemberProductModelDTO();
            dto.setId(mdo.getId());
            dto.setInventory(mdo.getInventory());
            dto.setName(mdo.getName());
            dto.setOriginalPrice(mdo.getOriginalPrice());
            dto.setPrice(mdo.getPrice());
            spec.add(dto);

            if (k == 0) {
                minPrice = mdo.getPrice();
                maxPrice = mdo.getPrice();
            } else {
                if (mdo.getPrice().compareTo(minPrice) == -1) {
                    minPrice = mdo.getPrice();
                }
                if (mdo.getPrice().compareTo(maxPrice) == 1) {
                    maxPrice = mdo.getPrice();
                }
            }
            k++;
        }
        productInfoBO.setSpec(spec);
        productInfoBO.setMinPrice(minPrice.toString());
        productInfoBO.setMaxPrice(maxPrice.toString());


        // 查询商品图片
        ProductImageDOExample imageExample = new ProductImageDOExample();
        imageExample.createCriteria().andProductIdEqualTo(productDO.getId()).andStatusEqualTo(true);
        imageExample.setOrderByClause(" sortid asc ");
        List<ProductImageDO> imageDOS = productImageDOMapper.selectByExample(imageExample);
        List<String> imagesHead = new ArrayList<String>();
        List<String> imagesDetail = new ArrayList<String>();
        for (ProductImageDO image : imageDOS) {
            if (image.getImgType().byteValue() == ProductImgTypeEnum.PRODUCT_IMG_HEAD.getVal().byteValue()) {
                imagesHead.add(image.getImagePath().replaceAll("\\\\", "/"));
            } else if (image.getImgType().byteValue() == ProductImgTypeEnum.PRODUCT_IMG_DETAIL.getVal().byteValue()) {
                imagesDetail.add(image.getImagePath().replaceAll("\\\\", "/"));
            }
        }
        productInfoBO.setImagesHeadUrl(imagesHead);
        //商品描述图片
        String imageContent = productDO.getImageContent() == null ? "[]" : productDO.getImageContent();
        List<String> imageContents = StringUtil.getJsonListToStringList(imageContent);
        List<MemberProductImageDetailDTO> imageDetail = new ArrayList<MemberProductImageDetailDTO>();
        for (int i = 0; i < imageContents.size(); i++) {
            MemberProductImageDetailDTO d = new MemberProductImageDetailDTO();
            d.setDetail(imageContents.get(i));
            if (imagesDetail.size() < (i + 1)) {
                break;
            }
            d.setImageUrl(imagesDetail.get(i));
            imageDetail.add(d);
        }
        productInfoBO.setImageDetail(imageDetail);

        return productInfoBO;
    }

    @Override
    public ProductEditInfoBO selectEditProductById(Long productId) {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(productId);
        if (productDO == null) {
            return null;
        }

        ProductEditInfoBO productEditInfoBO = ProductConverter.convertEditInfoBO(productDO);

        // 查询商品型号
        ProductModelDOExample modelExample = new ProductModelDOExample();
        modelExample.createCriteria().andProductIdEqualTo(productDO.getId()).andStatusEqualTo(true);
        List<ProductModelDO> productModelDOS = productModelDOMapper.selectByExample(modelExample);

        List<ProductModelBO> ProductModelBOS = new ArrayList<ProductModelBO>();
        for (ProductModelDO productModelDO : productModelDOS) {
            ProductModelBO productModelBO = ProductModelConverter.convertBO(productModelDO);
            ProductModelBOS.add(productModelBO);
        }
        productEditInfoBO.setSpec(ProductModelBOS);

        String featureImage = productEditInfoBO.getFeatureImage();
        featureImage = featureImage.replaceAll("\\\\", "/");
        productEditInfoBO.setFeatureImage(featureImage);

        // 查询滚动图片
        ProductImageDOExample imageExample = new ProductImageDOExample();
        imageExample.createCriteria().andProductIdEqualTo(productDO.getId())
                .andImgTypeEqualTo(ProductImgTypeEnum.PRODUCT_IMG_HEAD.getVal()).andStatusEqualTo(true);
        List<ProductImageDO> imageDOS = productImageDOMapper.selectByExample(imageExample);
        List<String> images = new ArrayList<String>();
        for (ProductImageDO image : imageDOS) {
            String imageUrl = image.getImagePath();
            imageUrl = imageUrl.replaceAll("\\\\", "/");
            images.add(imageUrl);
        }
        productEditInfoBO.setImagesUrl(images);

        // 查询详情图片
        imageExample.clear();
        imageExample.createCriteria().andProductIdEqualTo(productDO.getId())
                .andImgTypeEqualTo(ProductImgTypeEnum.PRODUCT_IMG_DETAIL.getVal()).andStatusEqualTo(true);
        List<ProductImageDO> imageDetailDOS = productImageDOMapper.selectByExample(imageExample);
        List<String> imageDetails = new ArrayList<String>();
        for (ProductImageDO image : imageDetailDOS) {
            String imageUrl = image.getImagePath();
            imageUrl = imageUrl.replaceAll("\\\\", "/");
            imageDetails.add(imageUrl);
        }
        productEditInfoBO.setImageDetailUrl(imageDetails);

        String category = productCategoryService.getFullName(productDO.getCategoryId());
        productEditInfoBO.setCategoryName(category);

        return productEditInfoBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void eidtProduct(EditProductDataParam param) {

        Long productId = param.getProductId();
        int inventory = 0;
        int salesVolume = 0;
        BigDecimal price = new BigDecimal("0");
        BigDecimal mprice = new BigDecimal("0");//修改为型号中最低价对应的原价,用于商品首页显示
        int traverseCnt = 0;

        boolean isEdit = true;
        if (productId == null || productId <= 0) {
            // 保存商品信息
            ProductDO productDO = ProductConverter.convertDO(param, 0L);
            String featureImage = productDO.getFeatureImage();
            featureImage = featureImage.replaceAll("/", File.separator);
            productDO.setFeatureImage(featureImage);
            ProductCategoryeDO productCategoryeDO = productCategoryeDOMapper.selectByPrimaryKey(productDO.getCategoryId());
            if (productCategoryeDO != null && productCategoryeDO.getIsVirtual()) {
                productDO.setIsAllowRefund(false);
            }
            productDOMapper.insertSelective(productDO);
            productId = productDO.getId();
            isEdit = false;
        } else {
            // 修改商品基本信息
            ProductDO productDO = ProductConverter.convertDO(param, productId);
            String featureImage = productDO.getFeatureImage();
            featureImage = featureImage.replace("/", File.separator);
            productDO.setFeatureImage(featureImage);
            ProductCategoryeDO productCategoryeDO = productCategoryeDOMapper.selectByPrimaryKey(productDO.getCategoryId());
            if (productCategoryeDO != null && productCategoryeDO.getIsVirtual()) {
                productDO.setIsAllowRefund(false);
            }
            ProductDOExample example = new ProductDOExample();
            example.createCriteria().andIdEqualTo(productId).andMerchantIdEqualTo(param.getMerchantId());
            int ret = productDOMapper.updateByExampleSelective(productDO, example);
            if (ret == 0) {
                return;
            }
        }

        String spec = param.getSpec();
        List<ProductModelBO> speclist = JSON.parseArray(spec, ProductModelBO.class);
        if (!isEdit) {
            ProductModelDO pmDO = null;
            for (ProductModelBO dataBO : speclist) {
                BigDecimal bdOriginalPrice = new BigDecimal("0");//原价等于空时保存数据库为0
                if (dataBO.getOriginalPrice() != null) {
                    bdOriginalPrice = dataBO.getOriginalPrice();
                }
                pmDO = new ProductModelDO();
                pmDO.setMerchantId(param.getMerchantId());
                pmDO.setProductId(productId);
                pmDO.setName(dataBO.getName());
                pmDO.setOriginalPrice(bdOriginalPrice);
                pmDO.setPrice(dataBO.getPrice());
                pmDO.setInventory(dataBO.getInventory());
                pmDO.setStatus(true);
                pmDO.setGmtCreate(new Date());
                pmDO.setGmtModified(new Date());
                productModelDOMapper.insertSelective(pmDO);

                if (traverseCnt == 0) {
                    price = dataBO.getPrice();
                    mprice = dataBO.getOriginalPrice() == null ? dataBO.getPrice() : dataBO.getOriginalPrice();//原价等于空时取现价
                }
                if (dataBO.getPrice().compareTo(price) == -1) {
                    price = dataBO.getPrice();
                    mprice = dataBO.getOriginalPrice() == null ? dataBO.getPrice() : dataBO.getOriginalPrice();//原价等于空时取现价
                }
                inventory += dataBO.getInventory();
                traverseCnt++;
            }
        } else {
            for (ProductModelBO dataBO : speclist) {
                BigDecimal bdOriginalPrice = new BigDecimal("0");
                if (dataBO.getOriginalPrice() != null) {
                    bdOriginalPrice = dataBO.getOriginalPrice();
                }
                if (dataBO.getId() == null || dataBO.getId() == 0L) {
                    ProductModelDO pmDO = new ProductModelDO();
                    pmDO.setMerchantId(param.getMerchantId());
                    pmDO.setProductId(productId);
                    pmDO.setName(dataBO.getName());
                    pmDO.setOriginalPrice(bdOriginalPrice);
                    pmDO.setPrice(dataBO.getPrice());
                    pmDO.setInventory(dataBO.getInventory());
                    pmDO.setStatus(true);
                    pmDO.setGmtCreate(new Date());
                    pmDO.setGmtModified(new Date());
                    productModelDOMapper.insertSelective(pmDO);

                } else {
                    ProductModelDOView modelDO = new ProductModelDOView();
                    modelDO.setId(Long.valueOf(dataBO.getId()));
                    modelDO.setOriginalPrice(bdOriginalPrice);
                    modelDO.setPrice(dataBO.getPrice());
                    Integer gapInventory = dataBO.getInventory() - dataBO.getInventoryTrans();
                    modelDO.setInventory(gapInventory);
                    modelDO.setName(dataBO.getName());
                    modelDO.setGmtModified(new Date());
                    productModelDOMapperExtend.updateByExampleSelective(modelDO);

                    if (gapInventory != 0) {
                        ProductModelInventoryDO pmiDO = new ProductModelInventoryDO();
                        pmiDO.setProductModelId(dataBO.getId());
                        pmiDO.setQuantity(Math.abs(gapInventory.intValue()));
                        if (gapInventory > 0) {
                            pmiDO.setType(ProductModelInventoryTypeEnum.PLUS.getValue());
                        } else if (gapInventory < 0) {
                            pmiDO.setType(ProductModelInventoryTypeEnum.MINUS.getValue());
                        }
                        pmiDO.setGmtCreate(new Date());
                        pmiDO.setGmtModified(new Date());
                        productModelInventoryDOMapper.insertSelective(pmiDO);
                    }
                }

                if (traverseCnt == 0) {
                    price = dataBO.getPrice();
                    mprice = dataBO.getOriginalPrice() == null ? dataBO.getPrice() : dataBO.getOriginalPrice();//原价等于空时取现价
                }
                if (dataBO.getPrice().compareTo(price) == -1) {
                    price = dataBO.getPrice();
                    mprice = dataBO.getOriginalPrice() == null ? dataBO.getPrice() : dataBO.getOriginalPrice();//原价等于空时取现价
                }
                inventory += dataBO.getInventory();
                salesVolume += dataBO.getSalesVolume();
                traverseCnt++;
            }
        }

        // 删除商品型号信息
        if (param.getDeleteSpecIds() != null && !"".equals(param.getDeleteSpecIds())) {
            String deleteSpecIds = param.getDeleteSpecIds();
            List<String> specIdsList = Arrays.asList(deleteSpecIds.split(","));
            for (String specId : specIdsList) {
                ProductModelDOExample modelExample = new ProductModelDOExample();
                modelExample.createCriteria().andIdEqualTo(Long.valueOf(specId));
                ProductModelDO modelDO = new ProductModelDO();
                modelDO.setStatus(false);
                modelDO.setGmtModified(new Date());
                productModelDOMapper.updateByExampleSelective(modelDO, modelExample);

                // 逻辑删除商品型号评价
                delProductCommentTransactionMainServiceImpl.sendNotice(Long.valueOf(specId));
            }
        }

        ProductDO productDO = new ProductDO();
        productDO.setTotalInventory(inventory);
        productDO.setTotalSalesVolume(salesVolume);
        productDO.setMinPrice(price);
        productDO.setMaxPrice(mprice);
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andIdEqualTo(productId);
        productDOMapper.updateByExampleSelective(productDO, example);

        if (isEdit) {
            // 删除产品图片
            ProductImageDOExample imageExample = new ProductImageDOExample();
            imageExample.createCriteria().andProductIdEqualTo(productId);
            ProductImageDO imageDO = new ProductImageDO();
            imageDO.setStatus(false);
            imageDO.setGmtModified(new Date());
            productImageDOMapper.updateByExampleSelective(imageDO, imageExample);
        }

        // 保存商品滚动图片信息
        ProductImageDO pcDO = null;
        String imageUrl = param.getProductImages();
        String[] imageUrls = imageUrl.split(",");
        for (int i = 0; i < imageUrls.length; i++) {
            String imgUrl = imageUrls[i];
            imgUrl = imgUrl.replace("/", File.separator);
            pcDO = new ProductImageDO();
            pcDO.setProductId(Long.valueOf(productId));
            pcDO.setImagePath(imgUrl);
            pcDO.setGmtCreate(new Date());
            pcDO.setGmtModified(new Date());
            pcDO.setSortid(0);
            pcDO.setStatus(true);
            pcDO.setImgType(ProductImgTypeEnum.PRODUCT_IMG_HEAD.getVal());
            if (StringUtils.isBlank(imgUrl)) {
                continue;
            }
            productImageDOMapper.insert(pcDO);
        }
        // 保存商品详情图片
        String detaiImageUrl = param.getDetailImages();
        String[] detaiImageUrls = detaiImageUrl.split(",");
        for (int i = 0; i < detaiImageUrls.length; i++) {
            String imgUrl = detaiImageUrls[i];
            imgUrl = imgUrl.replace("/", File.separator);
            pcDO = new ProductImageDO();
            pcDO.setProductId(Long.valueOf(productId));
            pcDO.setImagePath(imgUrl);
            pcDO.setGmtCreate(new Date());
            pcDO.setGmtModified(new Date());
            pcDO.setStatus(true);
            pcDO.setSortid(i + 1);
            pcDO.setImgType(ProductImgTypeEnum.PRODUCT_IMG_DETAIL.getVal());
            if (StringUtils.isBlank(imgUrl)) {
                continue;
            }
            productImageDOMapper.insert(pcDO);
        }

        ProductDO solrProduct = productDOMapper.selectByPrimaryKey(productId);
        if (solrProduct.getStatus().byteValue() == ProductStatusEnum.PRODUCT_STATUS_UP.getVal()) {
            productSolrService.save(ProductConverter.convertProductSolr(solrProduct));
        } else {
            productSolrService.delete(productId);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editTotalInventory(Long productId, int num, String flag) {
        ProductNumsView view = new ProductNumsView();
        view.setProductId(productId);
        view.setFlag(flag);
        view.setNum(num);
        view.setGmtModified(new Date());
        productDOMapperExtend.editTotalInventory(view);

        ProductDO solrProduct = productDOMapper.selectByPrimaryKey(productId);
        if (solrProduct.getStatus().byteValue() == ProductStatusEnum.PRODUCT_STATUS_UP.getVal()) {
            productSolrService.save(ProductConverter.convertProductSolr(solrProduct));
        } else {
            productSolrService.delete(productId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editTotalSaleVolume(Long productId, int num, String flag) {
        ProductNumsView view = new ProductNumsView();
        view.setProductId(productId);
        view.setFlag(flag);
        view.setNum(num);
        view.setGmtModified(new Date());
        productDOMapperExtend.editTotalSaleVolume(view);

        ProductDO solrProduct = productDOMapper.selectByPrimaryKey(productId);
        if (solrProduct.getStatus().byteValue() == ProductStatusEnum.PRODUCT_STATUS_UP.getVal()) {
            productSolrService.save(ProductConverter.convertProductSolr(solrProduct));
        } else {
            productSolrService.delete(productId);
        }
    }

    @Override
    public void editTotalFavorite(Long productId, int num, String flag) {
        ProductNumsView view = new ProductNumsView();
        view.setProductId(productId);
        view.setFlag(flag);
        view.setNum(num);
        view.setGmtModified(new Date());
        productDOMapperExtend.editTotalFavorite(view);
    }

    @Override
    public Page<ProductQueryBO> selectProductPlat(ProductParam param) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        if (StringUtils.isNotEmpty(param.getName())) {
            criteria.andNameLike("%" + param.getName() + "%");
        }
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

        List<ProductDO> doList = productDOMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);

        Long count = productDOMapper.countByExample(example);
        List<ProductQueryBO> boList = new ArrayList<>();
        if (!doList.isEmpty()) {
            for (ProductDO productDO : doList) {
                ProductQueryBO bo = new ProductQueryBO();
                bo.setId(productDO.getId());
                bo.setName(productDO.getName());
                bo.setMerchantId(productDO.getMerchantId());
                boList.add(bo);
            }
        }
        Page<ProductQueryBO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setRecords(boList);
        page.setTotalCount(count.intValue());
        return page;
    }

    @Override
    public ProductInfoBO getProductById(Long id) {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(id);
        return ProductConverter.convertInfoBO(productDO);
    }

    @Override
    public Integer selectProductCount(Long merchantId) {
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal()).andMerchantIdEqualTo(merchantId);
        Integer count = Long.valueOf(productDOMapper.countByExample(example)).intValue();
        return count;
    }

    @Override
    public List<ProductInfoBO> listProduct(ListProductParam listProductParam) {
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        RowBounds rowBounds = new RowBounds(listProductParam.getOffset(), listProductParam.getPageSize());
        List<ProductDO> productDOS = productDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        return ProductConverter.convertInfoBO(productDOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAverageDailySalesById(Long id, BigDecimal averageDailySales) {
        ProductDO productDO = new ProductDO();
        productDO.setId(id);
        productDO.setAverageDailySales(averageDailySales);
        productDOMapper.updateByPrimaryKeySelective(productDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeProductAverageDailySales(Integer pageSize) {
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());

        int currentPage = 0;
        List<ProductDO> productDOS;
        RowBounds rowBounds;
        while (true) {
            currentPage++;
            rowBounds = new RowBounds((currentPage - 1) * pageSize, pageSize);
            productDOS = productDOMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (productDOS == null || productDOS.isEmpty()) {
                return;
            }

            for (ProductDO productDO : productDOS) {
                int days = DateUtil.daysOfTwo(productDO.getGmtCreate());
                int salesVolume = productDO.getTotalSalesVolume();
                double averageDailySales = 0;
                if (days > 0) {
                    averageDailySales = (double) salesVolume / days;
                }
                ProductDO productDO1 = new ProductDO();
                productDO1.setId(productDO.getId());
                productDO1.setAverageDailySales(BigDecimal.valueOf(averageDailySales));
                productDOMapper.updateByPrimaryKeySelective(productDO1);
            }
        }
    }

    @Override
    public void rebuildProductIndex(Integer pageSize) {
        ListProductParam listProductParam = new ListProductParam();
        listProductParam.setPageSize(pageSize);
        int currentPage = 0;

        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        while (true) {
            currentPage++;
            listProductParam.setCurrentPage(currentPage);
            RowBounds rowBounds = new RowBounds(listProductParam.getOffset(), listProductParam.getPageSize());
            List<ProductDO> productDOS = productDOMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (productDOS.isEmpty()) {
                return;
            }
            List<ProductSolr> updateList = new ArrayList<>();
            for (ProductDO productDO : productDOS) {
                ProductSolr productSolr = ProductConverter.convertProductSolr(productDO);
                updateList.add(productSolr);
            }
            productSolrService.save(updateList);
        }
    }

    @Override
    public void delInvalidProductIndex(DelIndexTypeEnum typeEnum) {
        ListProductParam listProductParam = new ListProductParam();
        listProductParam.setPageSize(1000);
        int currentPage = 0;

        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusNotEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        if (typeEnum.equals(DelIndexTypeEnum.PART)) {
            Date beforeDate = DateUtil.getAppointTime(new Date(), -2);
            criteria.andGmtModifiedGreaterThanOrEqualTo(beforeDate);
        }
        while (true) {
            currentPage++;
            listProductParam.setCurrentPage(currentPage);
            RowBounds rowBounds = new RowBounds(listProductParam.getOffset(), listProductParam.getPageSize());
            List<ProductDO> productDOS = productDOMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (productDOS.isEmpty()) {
                return;
            }
            List<Long> ids = new ArrayList<>();
            for (ProductDO productDO : productDOS) {
                ids.add(productDO.getId());
            }
            productSolrService.delete(ids);
        }
    }

    @Override
    public Page<ProductQueryBO> listAllProduct(ListProductParam listProductParam) {
        ProductDOExample example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        if (listProductParam.getStatusEnum() != null) {
            criteria.andStatusEqualTo(listProductParam.getStatusEnum().getVal());
        }
        if (StringUtils.isNotEmpty(listProductParam.getName())) {
            criteria.andNameLike("%" + listProductParam.getName() + "%");
        }
        if (StringUtils.isNotEmpty(listProductParam.getSortName()) && StringUtils.isNotEmpty(listProductParam.getSortOrder())) {
            example.setOrderByClause("gmt_create " + listProductParam.getSortOrder());
        }

        // 查询总数
        RowBounds rowBounds = new RowBounds(listProductParam.getOffset(), listProductParam.getPageSize());
        Page<ProductQueryBO> page = new Page<>();
        page.setTotalCount(Long.valueOf(productDOMapper.countByExample(example)).intValue());
        page.setCurrentPage(listProductParam.getCurrentPage());
        List<ProductDO> productDOS = productDOMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);

        List<ProductQueryBO> productBOS = new ArrayList<ProductQueryBO>();

        for (ProductDO productDO : productDOS) {

            ProductModelDOExample modelExample = new ProductModelDOExample();
            modelExample.createCriteria().andProductIdEqualTo(productDO.getId()).andStatusEqualTo(true);
            // 查询商品型号
            List<ProductModelDO> productModelDOS = productModelDOMapper.selectByExample(modelExample);
            List<ProductModelBO> ProductModelBOS = new ArrayList<ProductModelBO>();
            for (ProductModelDO productModelDO : productModelDOS) {
                ProductModelBO productModelBO = ProductModelConverter.convertBO(productModelDO);
                ProductModelBOS.add(productModelBO);
            }
            String specJson = JSON.toJSONString(ProductModelBOS);
            String category = productCategoryService.getFullName(productDO.getCategoryId());
            ProductQueryBO productBO = ProductConverter.convertQueryBO(productDO);
            productBO.setSpec(specJson);

            productBO.setCategory(category);

            productBOS.add(productBO);
        }
        page.setRecords(productBOS);

        return page;
    }

    @Override
    public List<ProductBO> listProductByIds(List<Long> ids) {
        List<ProductDOView> productDOViewList = productDOMapperExtend.listProductByIds(ids);
        return ProductConverter.convertBOS(productDOViewList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void soldOutProductByMerchantId(Long merchantId) {
        ProductDOExample example2 = new ProductDOExample();
        example2.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        List<ProductDO> productDOS = productDOMapper.selectByExample(example2);
        if (!productDOS.isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (ProductDO product : productDOS) {
                ids.add(product.getId());
            }
            productSolrService.delete(ids);
        }

        ProductDO productDO = new ProductDO();
        productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal());
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId);
        productDOMapper.updateByExampleSelective(productDO, example);
    }

    @Override
    public ProductRelateAdInfoBO selectProductRelateAdInfo(Long id) {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(id);
        ProductRelateAdInfoBO bo = new ProductRelateAdInfoBO();
        bo.setName(productDO.getName());
        bo.setImgUrl(productDO.getFeatureImage());
        return bo;
    }

    @Override
    public void downOperatorById(Long id, String remark) {
        ProductDO product = new ProductDO();
        product.setId(id);
        product.setGmtDown(new Date());
        product.setGmtModified(new Date());
        product.setStatus(ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal());
        product.setRemark(remark);
        productDOMapper.updateByPrimaryKeySelective(product);
        productSolrService.delete(id);
    }

    @Override
    public ProductTypeCountBO countProduct(Long merchantId) {
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        int sellingCount = (int) productDOMapper.countByExample(example);

        example.clear();
        List<Byte> statusList = new ArrayList<>();
        statusList.add(ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal());
        statusList.add(ProductStatusEnum.PRODUCT_STATUS_TEMP.getVal());
        example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusIn(statusList);
        int warehouseCount = (int) productDOMapper.countByExample(example);

        example.clear();
        example.createCriteria().andMerchantIdEqualTo(merchantId).andPositionEqualTo(ProductPositionEnum.SHOPWINDOW.getVal()).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        int shopwindowCount = (int) productDOMapper.countByExample(example);

        example = new ProductDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andMerchantIdEqualTo(merchantId).andTotalInventoryEqualTo(0).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        example.or(example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_DEL.getVal()));
        int soldOutCount = (int) productDOMapper.countByExample(example);

        ProductTypeCountBO countBO = new ProductTypeCountBO();
        countBO.setSellingCount(sellingCount);
        countBO.setWarehouseCount(warehouseCount);
        countBO.setShopwindowCount(shopwindowCount);
        countBO.setSoldOutCount(soldOutCount);
        return countBO;
    }

    @Override
    public Page<ProductListBO> listProduct(Long merchantId, ProductListQuery query) {
        ProductDOExample example = new ProductDOExample();
        if (query.getSortEnum() != null) {
            example.setOrderByClause(query.getSortEnum().getVal());
        }
        Criteria criteria = example.createCriteria();
        criteria.andMerchantIdEqualTo(merchantId);
        if (StringUtils.isNotEmpty(query.getName())) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        if (query.getCategoryId() != null && query.getCategoryId() > 0) {
            criteria.andCategoryIdEqualTo(query.getCategoryId());
        }
        if (query.getCategorySubitemId() != null && query.getCategorySubitemId() > 0) {
            criteria.andCategorySubitemIdEqualTo(query.getCategorySubitemId());
        }

        if (query.getQueryTypeEnum() == ProductQueryTypeEnum.SELLING) {
            criteria.andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        } else if (query.getQueryTypeEnum() == ProductQueryTypeEnum.WAREHOUSE) {
            List<Byte> statusList = new ArrayList<>();
            statusList.add(ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal());
            statusList.add(ProductStatusEnum.PRODUCT_STATUS_TEMP.getVal());
            criteria.andStatusIn(statusList);
        } else if (query.getQueryTypeEnum() == ProductQueryTypeEnum.SHOPWINDOW) {
            criteria.andPositionEqualTo(ProductPositionEnum.SHOPWINDOW.getVal()).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        } else {
            criteria.andTotalInventoryEqualTo(0).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
            Criteria delCriteria = example.createCriteria();
            delCriteria.andMerchantIdEqualTo(merchantId).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_DEL.getVal());
            if (StringUtils.isNotEmpty(query.getName())) {
                delCriteria.andNameLike("%" + query.getName() + "%");
            }
            if (query.getCategoryId() != null && query.getCategoryId() > 0) {
                delCriteria.andCategoryIdEqualTo(query.getCategoryId());
            }
            if (query.getCategorySubitemId() != null && query.getCategorySubitemId() > 0) {
                delCriteria.andCategorySubitemIdEqualTo(query.getCategorySubitemId());
            }
            example.or(delCriteria);
        }

        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<ProductDO> productDOS = productDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<ProductListBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) productDOMapper.countByExample(example));
        page.setRecords(ProductConverter.convertListBOS(productDOS));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductPosition(Long merchantId, String ids) {
        String[] idArr = ids.split(",");
        ProductDO updateProductDO = new ProductDO();
        ProductDOExample example = new ProductDOExample();
        for (String id : idArr) {
            ProductDO productDO = productDOMapper.selectByPrimaryKey(Long.valueOf(id));
            if (productDO == null) {
                continue;
            }
            if (ProductPositionEnum.getEnum(productDO.getPosition()) == ProductPositionEnum.CANCEL) {
                updateProductDO.setPosition(ProductPositionEnum.SHOPWINDOW.getVal());
            } else if (ProductPositionEnum.getEnum(productDO.getPosition()) == ProductPositionEnum.SHOPWINDOW) {
                updateProductDO.setPosition(ProductPositionEnum.CANCEL.getVal());
            } else {
                continue;
            }
            updateProductDO.setGmtModified(new Date());

            example.clear();
            example.createCriteria().andMerchantIdEqualTo(merchantId).andIdEqualTo(Long.valueOf(id));
            productDOMapper.updateByExampleSelective(updateProductDO, example);
        }
    }

    @Override
    public ProductDetailBO getProductDetail(Long id) {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(id);
        if (productDO == null) {
            return null;
        }
        ProductDetailBO detailBO = ProductConverter.convertDetailBO(productDO);

        // 查询商品型号
        ProductModelDOExample modelExample = new ProductModelDOExample();
        modelExample.createCriteria().andProductIdEqualTo(productDO.getId()).andStatusEqualTo(true);
        List<ProductModelDO> productModelDOS = productModelDOMapper.selectByExample(modelExample);
        if (productModelDOS.isEmpty()) {
            return null;
        }

        List<ProductModelInfoBO> infoBOS = new ArrayList<>();
        for (ProductModelDO modelDO : productModelDOS) {
            String modelKey = "";
            if (modelDO.getSpecOption1() != null && modelDO.getSpecOption1() != 0) {
                modelKey += modelDO.getSpecOption1() + ";";
            }
            if (modelDO.getSpecOption2() != null && modelDO.getSpecOption2() != 0) {
                modelKey += modelDO.getSpecOption2() + ";";
            }
            if (modelDO.getSpecOption3() != null && modelDO.getSpecOption3() != 0) {
                modelKey += modelDO.getSpecOption3() + ";";
            }
            if (modelDO.getSpecOption4() != null && modelDO.getSpecOption4() != 0) {
                modelKey += modelDO.getSpecOption4() + ";";
            }
            if (modelDO.getSpecOption5() != null && modelDO.getSpecOption5() != 0) {
                modelKey += modelDO.getSpecOption5() + ";";
            }
            if (StringUtils.isNotEmpty(modelKey)) {
                ProductModelInfoBO infoBO = new ProductModelInfoBO();
                infoBO.setId(modelDO.getId());
                infoBO.setModelKey(modelKey.substring(0, modelKey.length() - 1));
                infoBO.setPrice(modelDO.getPrice());
                infoBO.setInventory(modelDO.getInventory());
                infoBOS.add(infoBO);
            }
        }
        detailBO.setModelInfo(infoBOS);

        // 查询商品图片
        ProductImageDOExample imageExample = new ProductImageDOExample();
        imageExample.createCriteria().andProductIdEqualTo(productDO.getId()).andStatusEqualTo(true);
        imageExample.setOrderByClause(" sortid asc ");
        List<ProductImageDO> imageDOS = productImageDOMapper.selectByExample(imageExample);
        List<String> imagesHead = new ArrayList<>();
        List<String> imagesDetail = new ArrayList<>();
        for (ProductImageDO image : imageDOS) {
            if (image.getImgType().byteValue() == ProductImgTypeEnum.PRODUCT_IMG_HEAD.getVal().byteValue()) {
                imagesHead.add(image.getImagePath());
            } else if (image.getImgType().byteValue() == ProductImgTypeEnum.PRODUCT_IMG_DETAIL.getVal().byteValue()) {
                imagesDetail.add(image.getImagePath());
            }
        }
        detailBO.setImagesHeadUrl(imagesHead);
        //商品描述图片
        String imageContent = productDO.getImageContent() == null ? "[]" : productDO.getImageContent();
        List<String> imageContents = StringUtil.getJsonListToStringList(imageContent);
        List<MemberProductImageDetailDTO> imageDetail = new ArrayList<>();
        for (int i = 0; i < imageContents.size(); i++) {
            MemberProductImageDetailDTO d = new MemberProductImageDetailDTO();
            d.setDetail(imageContents.get(i));
            if (imagesDetail.size() < (i + 1)) {
                break;
            }
            d.setImageUrl(imagesDetail.get(i));
            imageDetail.add(d);
        }
        detailBO.setImageDetail(imageDetail);
        return detailBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int editProductUpgrade(EditProductUpgradeDataParam product) {
        //处理型号，获取最小价格、最大价格、总库存，规格/规格选项json串
        //保存/修改商品信息
        //保存/修改商品型号信息
        //处理商品图片信息
        //保存商家自定义信息
        //处理solr
        int retCode = ResultCode.SUCCESS;
        List<EditProductModelBeanParam> productModelBeanParamList = product.getProductModelParam();
        if (productModelBeanParamList == null || productModelBeanParamList.isEmpty()) {
            throw new ProductModelNotEmptyException(ResultCode.get(ResultCode.PRODUCT_MODEL_SPEC_NOT_EMPTY));
        }
        List<String> titleImages = product.getTitleImages();
        if (titleImages == null || titleImages.isEmpty()) {
            throw new TitleImageNotEmptyException(ResultCode.get(ResultCode.PRODUCT_TITLE_IMAGE_NOT_EMPTY));
        }

        //处理一二级类目、品牌选择系统定义时清空对应的name属性
        resetOthersParam(product);

        int totalInventory = 0;//总库存
        List<BigDecimal> priceList = new ArrayList<>();
        for (EditProductModelBeanParam param : productModelBeanParamList) {
            if (param.getPrice() == null || param.getInventory() == null) {
                throw new InvalidPriceAndStockException(ResultCode.get(ResultCode.PRODUCT_MODEL_PRICE_STOCK_INVALID));
            }
            totalInventory = totalInventory + param.getInventory();
            priceList.add(param.getPrice());
        }
        Collections.sort(priceList);
        BigDecimal minPrice = priceList.get(0);//最小价格
        BigDecimal maxPrice = priceList.get(priceList.size() - 1);//最大价格

        //获取类目下的规格，再拼接商家自定义的规格
        List<ProductSpecBO> productSpecBOList = productSpecService.getProductSpecByCategoryId(product.getCategorySubitemId() == null ? product.getCategoryId() : product.getCategorySubitemId().intValue());
        List<ProductSpecCustomParam> productSpecList = ProductSpecConverter.convertProductSpecCustomParamList(productSpecBOList);
        if (product.getProductSpecCustomParam() != null) {
            productSpecList.addAll(product.getProductSpecCustomParam());
        }

        if (productSpecList.size() > 5) {
            throw new ProductSpecOutSizeException(ResultCode.get(ResultCode.PRODUCT_MODEL_SPEC_OUT_SIZE));
        }

        //组装product表中spec的json字符串
        List<ProductSpecJsonDTO> productSpecJsonList = new ArrayList<>();
        boolean isExistCustom = false;
        try {
            isExistCustom = modelBeanConvertToJsonBean(productSpecJsonList, productSpecList, productModelBeanParamList);
        } catch (IndexOutOfBoundsException ex) {
            throw new ProductSpecOptionMismatchException(ResultCode.get(ResultCode.PRODUCT_MODEL_SPEC_MISMATCH));
        }

        //修改前商品记录
        ProductDO productDOOld = null;
        if (product.getProductId() != null && product.getProductId() > 0) {
            productDOOld = productDOMapper.selectByPrimaryKey(product.getProductId());
        }

        ProductDO productDO = new ProductDO();
        productDO.setMerchantNum(product.getMerchantNum());
        productDO.setMerchantId(product.getMerchantId());
        productDO.setName(product.getName());
        productDO.setContent(product.getContent());
        productDO.setCategoryId(product.getCategoryId());
        productDO.setCategorySubitemId(product.getCategorySubitemId());
        productDO.setBrandId(product.getBrandId());
        String categoryName = product.getCategoryName();
        String categorySubitemName = product.getCategorySubitemName();
        String brandName = product.getBrandName();
        if (StringUtils.isBlank(categoryName)) {
            ProductCategoryBO productCategoryBO = productCategoryService.getById(product.getCategoryId());
            categoryName = productCategoryBO.getName();
        }
        if (StringUtils.isBlank(categorySubitemName)) {
            ProductCategoryBO productCategoryBO = productCategoryService.getById(product.getCategorySubitemId() == null ? null : product.getCategorySubitemId().intValue());
            categorySubitemName = productCategoryBO == null ? "" : productCategoryBO.getName();
        }
        if (StringUtils.isBlank(brandName)) {
            ProductBrandBO productBrandBO = productBrandService.getProductBrandById(product.getBrandId());
            brandName = productBrandBO == null ? "" : productBrandBO.getBrandName();
        }
        productDO.setCategoryName(categoryName);
        productDO.setCategorySubitemName(categorySubitemName);
        productDO.setBrandName(brandName);
        productDO.setKeywords(categoryName + "," + categorySubitemName + "," + brandName);
        productDO.setSpec(JSON.toJSONString(productSpecJsonList));
        productDO.setFeatureImage(product.getTitleImages().get(0).replace("/", File.separator));
        productDO.setStatus(product.getProductStatus().getVal());
        productDO.setIsAllowRefund(product.getIsAllowRefund());
        productDO.setImageContent(JSON.toJSONString(product.getDetailContent()));
        productDO.setTotalInventory(new Integer(totalInventory));
        productDO.setMinPrice(minPrice);
        productDO.setMaxPrice(maxPrice);
        productDO.setProvinceId(product.getProvinceId());
        productDO.setProvinceName(product.getProvinceName());
        productDO.setCityId(product.getCityId());
        productDO.setCityName(product.getCityName());
        productDO.setIsExpressFree(product.getIsExpressFree());
        if (product.getIsExpressFree()) {
            productDO.setFreight("");
        } else {
            productDO.setFreight(JSON.toJSONString(product.getFreightParam()));
        }
        if (ProductStatusEnum.PRODUCT_STATUS_UP.getVal().equals(product.getProductStatus().getVal())) {
            productDO.setGmtUp(new Date());
        } else if (ProductStatusEnum.PRODUCT_STATUS_TEMP.getVal().equals(product.getProductStatus().getVal())) {
            productDO.setPosition(ProductPositionEnum.CANCEL.getVal());
        }
        productDO.setGmtModified(new Date());
        productDO.setGmtCreate(new Date());
        if (product.getProductId() == null || product.getProductId() == 0) {
            if (ProductStatusEnum.PRODUCT_STATUS_UP.getVal().equals(product.getProductStatus().getVal())) {
                productDO.setGmtFinishRichTask(new Date());//初次上架
                retCode = 1;
            }
            productDOMapper.insertSelective(productDO);
        } else {
            //修改时发布判断是否首次商家
            if (ProductStatusEnum.PRODUCT_STATUS_UP.getVal().equals(product.getProductStatus().getVal())) {
                ProductDO productDODb = productDOMapper.selectByPrimaryKey(product.getProductId());
                if (productDODb.getGmtFinishRichTask() == null) {
                    productDO.setGmtFinishRichTask(new Date());//初次上架
                    retCode = 1;//需要处理瑞奇岛动力任务
                }
            }
            productDO.setId(product.getProductId());
            productDOMapper.updateByPrimaryKeySelective(productDO);
        }

        //处理型号
        if (productDOOld != null) {
            //修改商品时，如果修改了类目，则直接删除掉所有型号数据
            if (productDOOld.getCategoryId().intValue() != product.getCategoryId().intValue() ||
                (productDOOld.getCategorySubitemId() == null ? 0 : productDOOld.getCategorySubitemId().longValue()) != (product.getCategorySubitemId() == null ? 0 : product.getCategorySubitemId().longValue())) {
                ProductModelDOExample productModelDOExample = new ProductModelDOExample();
                productModelDOExample.createCriteria().andProductIdEqualTo(product.getProductId());
                ProductModelDO productModelDO = new ProductModelDO();
                productModelDO.setStatus(false);
                productModelDO.setGmtModified(new Date());
                productModelDOMapper.updateByExampleSelective(productModelDO,productModelDOExample);
            }
        }

        for (EditProductModelBeanParam model : productModelBeanParamList) {
            List<String> specOptionIdArray = Arrays.asList(model.getSpecOptionIdStr().split(","));
            ProductModelDO productModelDO = new ProductModelDO();
            productModelDO.setName(assembleProductModelName(model));
            productModelDO.setPrice(model.getPrice());
            productModelDO.setInventory(model.getInventory());
            productModelDO.setStatus(true);
            if (specOptionIdArray.size() > 0) {
                productModelDO.setSpecOption1(StringUtils.isBlank(specOptionIdArray.get(0)) ? 0 : new Long(specOptionIdArray.get(0).trim()));
            }
            if (specOptionIdArray.size() > 1) {
                productModelDO.setSpecOption2(StringUtils.isBlank(specOptionIdArray.get(1)) ? 0 : new Long(specOptionIdArray.get(1).trim()));
            }
            if (specOptionIdArray.size() > 2) {
                productModelDO.setSpecOption3(StringUtils.isBlank(specOptionIdArray.get(2)) ? 0 : new Long(specOptionIdArray.get(2).trim()));
            }
            if (specOptionIdArray.size() > 3) {
                productModelDO.setSpecOption4(StringUtils.isBlank(specOptionIdArray.get(3)) ? 0 : new Long(specOptionIdArray.get(3).trim()));
            }
            if (specOptionIdArray.size() > 4) {
                productModelDO.setSpecOption5(StringUtils.isBlank(specOptionIdArray.get(4)) ? 0 : new Long(specOptionIdArray.get(4).trim()));
            }
            productModelDO.setGmtModified(new Date());
            if (product.getProductId() == null || product.getProductId() == 0) {
                productModelDO.setMerchantId(productDO.getMerchantId());
                productModelDO.setProductId(productDO.getId());
                productModelDO.setGmtCreate(new Date());
                productModelDOMapper.insertSelective(productModelDO);
            } else {
                if (model.getProductModelId() == null || model.getProductModelId() == 0) {
                    productModelDO.setMerchantId(productDO.getMerchantId());
                    productModelDO.setProductId(productDO.getId());
                    productModelDO.setGmtCreate(new Date());
                    productModelDOMapper.insertSelective(productModelDO);
                } else {
                    productModelDO.setId(model.getProductModelId());
                    productModelDOMapper.updateByPrimaryKeySelective(productModelDO);
                }
            }
        }
        //删除标记为删除的型号数据
        List<Long> delProductModelIds = product.getDelProductModelIds();
        if (delProductModelIds != null && !delProductModelIds.isEmpty()) {
            for (Long id : delProductModelIds) {
                ProductModelDO productModelDO = new ProductModelDO();
                productModelDO.setStatus(false);
                productModelDO.setGmtModified(new Date());
                productModelDO.setId(id);
                productModelDOMapper.updateByPrimaryKeySelective(productModelDO);
            }
        }

        // 处理图片
        if (product.getProductId() != null && product.getProductId() > 0) {
            ProductImageDOExample imageExample = new ProductImageDOExample();
            imageExample.createCriteria().andProductIdEqualTo(productDO.getId());
            ProductImageDO imageDO = new ProductImageDO();
            imageDO.setStatus(false);
            imageDO.setGmtModified(new Date());
            productImageDOMapper.updateByExampleSelective(imageDO, imageExample);
        }
        for (int i = 0; i < titleImages.size(); i++) {
            String titleImage = titleImages.get(i);
            titleImage = titleImage.replace("/", File.separator);
            ProductImageDO productImageDO = new ProductImageDO();
            productImageDO.setProductId(productDO.getId());
            productImageDO.setImagePath(titleImage);
            productImageDO.setGmtCreate(new Date());
            productImageDO.setGmtModified(new Date());
            productImageDO.setSortid(i + 1);
            productImageDO.setStatus(true);
            productImageDO.setImgType(ProductImgTypeEnum.PRODUCT_IMG_HEAD.getVal()); //顶部图
            if (StringUtils.isBlank(titleImage)) {
                continue;
            }
            productImageDOMapper.insert(productImageDO);
        }
        List<String> detailImages = product.getDetailImages();
        for (int i = 0; i < detailImages.size(); i++) {
            String detailImage = detailImages.get(i);
            detailImage = detailImage.replace("/", File.separator);
            ProductImageDO productImageDO = new ProductImageDO();
            productImageDO.setProductId(productDO.getId());
            productImageDO.setImagePath(detailImage);
            productImageDO.setGmtCreate(new Date());
            productImageDO.setGmtModified(new Date());
            productImageDO.setStatus(true);
            productImageDO.setSortid(i + 1);
            productImageDO.setImgType(ProductImgTypeEnum.PRODUCT_IMG_DETAIL.getVal()); //描述图片
            if (StringUtils.isBlank(detailImage)) {
                continue;
            }
            productImageDOMapper.insert(productImageDO);
        }

        //商家自定义信息
        productCustomSpecService.saveCustomInfo(productDO.getId(), product, productDO, productSpecList, isExistCustom);

        //处理solr
        if (product.getProductStatus().getVal() == ProductStatusEnum.PRODUCT_STATUS_UP.getVal()) {
            ProductDO productSolr = productDOMapper.selectByPrimaryKey(productDO.getId());
            productSolrService.save(ProductConverter.convertProductSolr(productSolr));
        } else {
            productSolrService.delete(productDO.getId());
        }

        return retCode;
    }

    /**
     * 处理一二级类目、品牌选择系统定义时清空对应的name属性
     *
     * @param product
     */
    private void resetOthersParam(EditProductUpgradeDataParam product) {
        if (product.getCategoryId() != null && product.getCategoryId() > 0) {
            List<ProductCategoryBO> productCategoryBOList = productCategoryService.find(0L);
            if (productCategoryBOList.get(productCategoryBOList.size() - 1).getId() != product.getCategoryId()) {
                product.setCategoryName("");
            }
        }
        if (product.getCategorySubitemId() != null && product.getCategorySubitemId() > 0) {
            List<ProductCategoryBO> productCategoryBOList = productCategoryService.find(new Long(product.getCategoryId()));
            if (productCategoryBOList.get(productCategoryBOList.size() - 1).getId() != product.getCategorySubitemId().intValue()) {
                product.setCategorySubitemName("");
            }
        }
        if (product.getBrandId() != null && product.getBrandId() > 0) {
            Long categoryId = new Long(product.getCategoryId());
            if (product.getCategorySubitemId() != null && product.getCategorySubitemId() > 0) {
                categoryId = product.getCategorySubitemId();
            }
            List<ProductBrandBO> productBrandBOList = productBrandService.getProductBrandByCategoryId(categoryId.intValue());
            if (productBrandBOList.get(productBrandBOList.size() - 1).getId() != product.getBrandId()) {
                product.setBrandName("");
            }
        }
    }

    @Override
    public ProductEditDetailBO getEditProductById(Long id) {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(id);
        ProductEditDetailBO productEditDetailBO = ProductConverter.convertEditDetailBO(productDO);
        if (productEditDetailBO == null) {
            return null;
        }
        ProductModelDOExample productModelDOExample = new ProductModelDOExample();
        productModelDOExample.createCriteria().andProductIdEqualTo(id).andStatusEqualTo(true);
        productModelDOExample.setOrderByClause(" id asc ");
        List<ProductModelDO> productModelDOList = productModelDOMapper.selectByExample(productModelDOExample);
        List<EditProductModelEntityParam> productModelParam = new ArrayList<>();
        for (ProductModelDO productModelDO : productModelDOList) {
            EditProductModelEntityParam editProductModelEntityParam = new EditProductModelEntityParam();
            editProductModelEntityParam.setModelName(productModelDO.getName());
            editProductModelEntityParam.setProductModelId(productModelDO.getId());
            StringBuilder specOptionIdStr = new StringBuilder();
            if (productModelDO.getSpecOption1() != null && productModelDO.getSpecOption1() != 0) {
                specOptionIdStr.append(productModelDO.getSpecOption1());
            }
            if (productModelDO.getSpecOption2() != null && productModelDO.getSpecOption2() != 0) {
                specOptionIdStr.append(",").append(productModelDO.getSpecOption2());
            }
            if (productModelDO.getSpecOption3() != null && productModelDO.getSpecOption3() != 0) {
                specOptionIdStr.append(",").append(productModelDO.getSpecOption3());
            }
            if (productModelDO.getSpecOption4() != null && productModelDO.getSpecOption4() != 0) {
                specOptionIdStr.append(",").append(productModelDO.getSpecOption4());
            }
            if (productModelDO.getSpecOption5() != null && productModelDO.getSpecOption5() != 0) {
                specOptionIdStr.append(",").append(productModelDO.getSpecOption5());
            }
            editProductModelEntityParam.setSpecOptionIdStr(specOptionIdStr.toString());
            editProductModelEntityParam.setSpecOptionNameStr(productModelDO.getName().replaceAll("\\+", ",").replaceAll("\"", ""));
            editProductModelEntityParam.setInventory(productModelDO.getInventory());
            editProductModelEntityParam.setPrice(productModelDO.getPrice());
            productModelParam.add(editProductModelEntityParam);
        }
        productEditDetailBO.setProductModelParam(productModelParam);

        ProductImageDOExample productImageDOExample = new ProductImageDOExample();
        productImageDOExample.createCriteria().andProductIdEqualTo(id).andStatusEqualTo(true).andImgTypeEqualTo(ProductImgTypeEnum.PRODUCT_IMG_HEAD.getVal());
        productImageDOExample.setOrderByClause(" sortid asc ");
        List<ProductImageDO> productTitleImageDOList = productImageDOMapper.selectByExample(productImageDOExample);
        List<String> titileImages = new ArrayList<>();
        for (ProductImageDO imageDO : productTitleImageDOList) {
            titileImages.add(imageDO.getImagePath());
        }
        productEditDetailBO.setTitleImages(titileImages);

        ProductImageDOExample productImageDOExample1 = new ProductImageDOExample();
        productImageDOExample1.createCriteria().andProductIdEqualTo(id).andStatusEqualTo(true).andImgTypeEqualTo(ProductImgTypeEnum.PRODUCT_IMG_DETAIL.getVal());
        productImageDOExample1.setOrderByClause(" sortid asc ");
        List<ProductImageDO> productDetailImageDOList = productImageDOMapper.selectByExample(productImageDOExample1);
        List<String> detailImages = new ArrayList<>();
        for (ProductImageDO imageDO : productDetailImageDOList) {
            detailImages.add(imageDO.getImagePath());
        }
        productEditDetailBO.setDetailImages(detailImages);
        return productEditDetailBO;
    }

    @Override
    public List<ProductCityDOView> listProductCity() {
        return productDOMapperExtend.listProductCity();
    }

    @Override
    public List<ProductCategoryTierBO> listMerchantProductCategory(Long merchantId) {
        List<ProductCategoryTierBO> tierBOS = new ArrayList<>();
        List<ProductCategoryItemDOView> itemDOViews = productDOMapperExtend.listMerchantProductCategory(merchantId);
        if (itemDOViews == null || itemDOViews.isEmpty()) {
            return tierBOS;
        }
        for (ProductCategoryItemDOView itemDOView : itemDOViews) {
            ProductCategoryTierBO tierBO = new ProductCategoryTierBO();
            tierBO.setId(itemDOView.getId());
            tierBO.setName(itemDOView.getName());
            List<ProductCategoryItemDOView> itemDOViewList = productDOMapperExtend.listProductCategoryItem(merchantId, itemDOView.getId());
            tierBO.setItemDOViews(itemDOViewList);
            tierBOS.add(tierBO);
        }
        return tierBOS;
    }

    @Override
    public List<ProductAllBO> getAllProduct() {
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andCategoryNameIsNull();
        return ProductConverter.convertProductAllBOList(productDOMapper.selectByExample(example));
    }

    /**
     * 商品型号兼容性处理
     *
     * @param productCompatibleParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void compatible(ProductCompatibleParam productCompatibleParam) {
        ProductModelDOExample productModelDOExample = new ProductModelDOExample();
        productModelDOExample.createCriteria().andProductIdEqualTo(productCompatibleParam.getProductId()).andStatusEqualTo(true);
        List<ProductModelDO> productModelDOList = productModelDOMapper.selectByExample(productModelDOExample);

        ProductSpecJsonDTO productSpecJsonDTO = new ProductSpecJsonDTO();
        productSpecJsonDTO.setSpecId(-1L);
        productSpecJsonDTO.setSpecName("其他");
        List<ProductSpecOptionJsonDTO> options = new ArrayList<>();
        Long i = -1L;
        for (ProductModelDO productModelDO : productModelDOList) {
            ProductModelDO productModelDO1 = new ProductModelDO();
            productModelDO1.setId(productModelDO.getId());
            productModelDO1.setSpecOption1(i);
            productModelDOMapper.updateByPrimaryKeySelective(productModelDO1);

            ProductSpecOptionJsonDTO productSpecOptionJsonDTO = new ProductSpecOptionJsonDTO();
            productSpecOptionJsonDTO.setId(i);
            productSpecOptionJsonDTO.setName(productModelDO.getName());
            productSpecOptionJsonDTO.setSelected(false);
            productSpecOptionJsonDTO.setIcon("");
            options.add(productSpecOptionJsonDTO);
            i--;
        }
        productSpecJsonDTO.setOptions(options);
        List<ProductSpecJsonDTO> productSpecJsonList = new ArrayList<>();
        productSpecJsonList.add(productSpecJsonDTO);

        ProductDO productDO = new ProductDO();
        productDO.setId(productCompatibleParam.getProductId());
        productDO.setSpec(JSON.toJSONString(productSpecJsonList));
        productDO.setProvinceId(productCompatibleParam.getProvinceId());
        productDO.setProvinceName(productCompatibleParam.getProvinceName());
        productDO.setCityId(productCompatibleParam.getCityId());
        productDO.setCityName(productCompatibleParam.getCityName());
        productDO.setCategoryName(productCategoryeDOMapper.selectByPrimaryKey(productCompatibleParam.getCategoryId()).getName());
        productDOMapper.updateByPrimaryKeySelective(productDO);

        //刷新缓存
        ProductDO productSolr = productDOMapper.selectByPrimaryKey(productDO.getId());
        productSolrService.save(ProductConverter.convertProductSolr(productSolr));

    }

    /**
     * 组装商品型号名称
     * 例如： "36"+"白色"+"XL"
     *
     * @param model
     * @return
     */
    private String assembleProductModelName(EditProductModelBeanParam model) {
        List<String> specOptionNameArray = Arrays.asList(model.getSpecOptionNameStr().split(","));
        StringBuilder modelNameBuilder = new StringBuilder();
        if (specOptionNameArray.size() > 0 && StringUtils.isNotBlank(specOptionNameArray.get(0))) {
            modelNameBuilder.append("\"").append(specOptionNameArray.get(0)).append("\"");
        }
        if (specOptionNameArray.size() > 1 && StringUtils.isNotBlank(specOptionNameArray.get(1))) {
            modelNameBuilder.append("+").append("\"").append(specOptionNameArray.get(1)).append("\"");
        }
        if (specOptionNameArray.size() > 2 && StringUtils.isNotBlank(specOptionNameArray.get(2))) {
            modelNameBuilder.append("+").append("\"").append(specOptionNameArray.get(2)).append("\"");
        }
        if (specOptionNameArray.size() > 3 && StringUtils.isNotBlank(specOptionNameArray.get(3))) {
            modelNameBuilder.append("+").append("\"").append(specOptionNameArray.get(3)).append("\"");
        }
        if (specOptionNameArray.size() > 4 && StringUtils.isNotBlank(specOptionNameArray.get(4))) {
            modelNameBuilder.append("+").append("\"").append(specOptionNameArray.get(4)).append("\"");
        }
        return modelNameBuilder.toString();
    }

    /**
     * 商品表json转化
     * 规格选项id小于0为商家自定义
     *
     * @param productSpecJsonList       商品表中商家选择商品规格信息json
     * @param productSpecList           系统自定义规格+商家自定义规格 集合
     * @param productModelBeanParamList 商家选择规格选项组合的型号集合
     */
    private boolean modelBeanConvertToJsonBean(List<ProductSpecJsonDTO> productSpecJsonList, List<ProductSpecCustomParam> productSpecList, List<EditProductModelBeanParam> productModelBeanParamList) {
        boolean isExistCustom = false;
        for (int i = 0; i < productSpecList.size(); i++) {
            ProductSpecJsonDTO jsonBean = new ProductSpecJsonDTO();
            jsonBean.setSpecId(productSpecList.get(i).getSpecId());
            jsonBean.setSpecName(productSpecList.get(i).getSpecName());
            List<ProductSpecOptionJsonDTO> options = new ArrayList<>();
            for (EditProductModelBeanParam modelParam : productModelBeanParamList) {
                Long specOptionId = 0L;
                String specOptionName = "";
                String icon = "";
                List<String> specOptionIdArray = Arrays.asList(modelParam.getSpecOptionIdStr().split(","));
                List<String> specOptionNameArray = Arrays.asList(modelParam.getSpecOptionNameStr().split(","));
                switch (i) {
                    case 0:
                        specOptionId = new Long(specOptionIdArray.get(0));
                        specOptionName = specOptionNameArray.get(0);
                        break;
                    case 1:
                        specOptionId = new Long(specOptionIdArray.get(1));
                        specOptionName = specOptionNameArray.get(1);
                        break;
                    case 2:
                        specOptionId = new Long(specOptionIdArray.get(2));
                        specOptionName = specOptionNameArray.get(2);
                        break;
                    case 3:
                        specOptionId = new Long(specOptionIdArray.get(3));
                        specOptionName = specOptionNameArray.get(3);
                        break;
                    case 4:
                        specOptionId = new Long(specOptionIdArray.get(4));
                        specOptionName = specOptionNameArray.get(4);
                        break;
                }

                //判断规格中选项是否重复添加
                boolean isRepeat = false;
                for (ProductSpecOptionJsonDTO option : options) {
                    if (specOptionId.longValue() == option.getId().longValue()) {
                        isRepeat = true;
                        break;
                    }
                }
                if (isRepeat) continue;

                if (specOptionId.longValue() > 0) { // 系统自定义
                    ProductSpecOptionBO productSpecOptionBO = productSpecOptionService.getSpecOptionDetail(specOptionId);
                    specOptionName = productSpecOptionBO.getOptionName();
                    icon = productSpecOptionBO.getIconUrl() == null ? "" : productSpecOptionBO.getIconUrl();
                } else {
                    isExistCustom = true;
                }
                ProductSpecOptionJsonDTO optionJsonDTO = new ProductSpecOptionJsonDTO();
                optionJsonDTO.setIcon(icon);
                optionJsonDTO.setId(new Long(specOptionId));
                optionJsonDTO.setName(specOptionName);
                options.add(optionJsonDTO);
            }
            jsonBean.setOptions(options);
            productSpecJsonList.add(jsonBean);
        }
        return isExistCustom;
    }

}
