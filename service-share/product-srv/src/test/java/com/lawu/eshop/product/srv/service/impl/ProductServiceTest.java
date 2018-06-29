/**
 * 
 */
package com.lawu.eshop.product.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.product.constant.ProductImgTypeEnum;
import com.lawu.eshop.product.constant.ProductListSortEnum;
import com.lawu.eshop.product.constant.ProductPositionEnum;
import com.lawu.eshop.product.constant.ProductQueryTypeEnum;
import com.lawu.eshop.product.constant.ProductSortFieldEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.param.EditProductDataParam;
import com.lawu.eshop.product.param.ListProductParam;
import com.lawu.eshop.product.param.ProductParam;
import com.lawu.eshop.product.query.ProductDataQuery;
import com.lawu.eshop.product.query.ProductListQuery;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.ProductBO;
import com.lawu.eshop.product.srv.bo.ProductCategoryTierBO;
import com.lawu.eshop.product.srv.bo.ProductDetailBO;
import com.lawu.eshop.product.srv.bo.ProductEditInfoBO;
import com.lawu.eshop.product.srv.bo.ProductInfoBO;
import com.lawu.eshop.product.srv.bo.ProductListBO;
import com.lawu.eshop.product.srv.bo.ProductQueryBO;
import com.lawu.eshop.product.srv.bo.ProductTypeCountBO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductImageDO;
import com.lawu.eshop.product.srv.domain.ProductModelDO;
import com.lawu.eshop.product.srv.domain.extend.ProductCityDOView;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductImageDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductModelDOMapper;
import com.lawu.eshop.product.srv.service.ProductService;
import com.lawu.framework.core.page.OrderType;
import com.lawu.framework.core.page.Page;

/**
 * @author lihj
 * @date 2017年7月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class ProductServiceTest {

	private Logger log = Logger.getLogger(ProductServiceTest.class);
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDOMapper productDOMapper;

	@Autowired
	private ProductModelDOMapper productModelDOMapper;

	@Autowired
	private ProductImageDOMapper productImageDOMapper;

	/**
	 * 商品列表查询
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectProduct() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		ProductDataQuery query =new ProductDataQuery();
		query.setProductSortFieldEnum(ProductSortFieldEnum.TOTAL_INVENTORY);
		query.setProductStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
		query.setOrderType(OrderType.DESC);
		query.setMerchantId(1L);
		Page<ProductQueryBO> page = productService.selectProduct(query);
		Assert.assertEquals(page.getRecords().size(), 1);
	}

	/**
	 * 批量处理
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateProductStatus() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		int count = productService.updateProductStatus("1,2", ProductStatusEnum.PRODUCT_STATUS_DOWN, 1L,null);
		ProductDataQuery query =new ProductDataQuery();
		query.setProductSortFieldEnum(ProductSortFieldEnum.TOTAL_INVENTORY);
		query.setProductStatus(ProductStatusEnum.PRODUCT_STATUS_DOWN);
		query.setOrderType(OrderType.DESC);
		query.setMerchantId(1L);
		Page<ProductQueryBO> page = productService.selectProduct(query);
		log.info(JSON.toJSON(page));
	}

	/**
	 * 用户端商品详情，根据商品ID获取商品信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectProductById() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		ListProductParam param =new ListProductParam();
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setSortName("id");
		param.setSortOrder("desc");
		param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
		Page<ProductQueryBO> page = productService.listAllProduct(param);
		for (int i = 0; i < page.getRecords().size(); i++) {
			ProductInfoBO bo = productService.selectProductById(page.getRecords().get(i).getId());
			log.info(JSON.toJSON(bo));
			Assert.assertNotNull(bo);
		}
	}

	/**
	 * 商家端编辑商品时，根据ID查询商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectEditProductById() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		
		ListProductParam param =new ListProductParam();
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setSortName("id");
		param.setSortOrder("desc");
		param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
		Page<ProductQueryBO> page = productService.listAllProduct(param);
		for (int i = 0; i < page.getRecords().size(); i++) {
			ProductEditInfoBO bo = productService.selectEditProductById(page.getRecords().get(i).getId());
			log.info(JSON.toJSON(bo));
			Assert.assertNotNull(bo);
			
		}
	}

	/**
	 * 编辑商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void eidtProduct() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		ProductDataQuery query =new ProductDataQuery();
		query.setProductSortFieldEnum(ProductSortFieldEnum.TOTAL_INVENTORY);
		query.setProductStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
		query.setOrderType(OrderType.DESC);
		query.setMerchantId(1L);
		Page<ProductQueryBO> page = productService.selectProduct(query);
		Assert.assertEquals(page.getRecords().size(), 1);
	}

	/**
	 * 操作库存
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void editTotalInventory() {
//		EditProductDataParam product = initProduct("1");
//		productService.eidtProduct(product);
//		EditProductDataParam product1 = initProduct("2");
//		productService.eidtProduct(product1);
		ProductDO productDO  = new ProductDO();
		productDO.setStatus(new Byte("1"));
		productDO.setName("name");
		productDO.setGmtModified(new Date());
		productDO.setAverageDailySales(new BigDecimal(1));
		productDO.setCategoryId(1);
		productDO.setContent("content");
		productDO.setFeatureImage("/fdf/1.jpg");
		productDO.setGmtCreate(new Date());
		productDO.setGmtDown(new Date());
		productDO.setImageContent("[]");
		productDO.setIsAllowRefund(true);
		productDO.setKeywords("fdf");
		productDO.setMaxPrice(new BigDecimal(1));
		productDO.setMerchantId(1L);
		productDO.setMerchantNum("B1000");
		productDO.setMinPrice(new BigDecimal(1));
		productDO.setStatus(new Byte("1"));
		productDO.setTotalFavorite(1);
		productDO.setTotalInventory(1);
		productDO.setTotalSalesVolume(1);
		productDOMapper.insertSelective(productDO);
		productService.editTotalInventory(productDO.getId(), 100, "A");
		ProductInfoBO bo = productService.selectProductById(productDO.getId());
		log.info(JSON.toJSON(bo));
	}

	/**
	 * 操作销量
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void editTotalSaleVolume() {
//		EditProductDataParam product = initProduct("1");
//		productService.eidtProduct(product);
//		EditProductDataParam product1 = initProduct("2");
//		productService.eidtProduct(product1);
		ProductDO productDO  = new ProductDO();
		productDO.setStatus(new Byte("1"));
		productDO.setName("name");
		productDO.setGmtModified(new Date());
		productDO.setAverageDailySales(new BigDecimal(1));
		productDO.setCategoryId(1);
		productDO.setContent("content");
		productDO.setFeatureImage("/fdf/1.jpg");
		productDO.setGmtCreate(new Date());
		productDO.setGmtDown(new Date());
		productDO.setImageContent("[]");
		productDO.setIsAllowRefund(true);
		productDO.setKeywords("fdf");
		productDO.setMaxPrice(new BigDecimal(1));
		productDO.setMerchantId(1L);
		productDO.setMerchantNum("B1000");
		productDO.setMinPrice(new BigDecimal(1));
		productDO.setStatus(new Byte("1"));
		productDO.setTotalFavorite(1);
		productDO.setTotalInventory(1);
		productDO.setTotalSalesVolume(1);
		productDOMapper.insertSelective(productDO);

		productService.editTotalSaleVolume(productDO.getId(), 100, "A");
		ProductInfoBO bo = productService.selectProductById(productDO.getId());
		log.info(JSON.toJSON(bo));
	}

	/**
	 * 操作收藏
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void editTotalFavorite() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");  
		productService.eidtProduct(product1);
		productService.editTotalFavorite(2L, 1000, "A");
	}

	/**
	 * 根据商品ID查询商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getProductById() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		ListProductParam param =new ListProductParam();
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setSortName("id");
		param.setSortOrder("desc");
		param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
		Page<ProductQueryBO> page = productService.listAllProduct(param);
		for (int i = 0; i < page.getRecords().size(); i++) {
			ProductInfoBO bo = productService.getProductById(page.getRecords().get(i).getId());
			log.info(JSON.toJSON(bo));
			Assert.assertNotNull(bo);
		}
	}

	/**
	 * 运营平台查询所有已审核的商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectProductPlat() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		ProductParam param = new ProductParam();
		param.setName("小炒肉");
		Page<ProductQueryBO> page = productService.selectProductPlat(param);
		log.info(JSON.toJSONString(page.getRecords()));
		Assert.assertEquals(page.getRecords(), 2);
	}

	/**
	 * 查询当前商家总有多少商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectProductCount() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		Integer count = productService.selectProductCount(1L);
		Assert.assertEquals(count, Integer.valueOf(2));
	}

	/**
	 * 查询所有上架中商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listProduct() {
//		EditProductDataParam product = initProduct("1");
//		productService.eidtProduct(product);
//		EditProductDataParam product1 = initProduct("2");
//		productService.eidtProduct(product1);
		ProductDO productDO  = new ProductDO();
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		productDO.setName("name");
		productDO.setGmtModified(new Date());
		productDO.setAverageDailySales(new BigDecimal(1));
		productDO.setCategoryId(1);
		productDO.setContent("content");
		productDO.setFeatureImage("/fdf/1.jpg");
		productDO.setGmtCreate(new Date());
		productDO.setGmtDown(new Date());
		productDO.setImageContent("[]");
		productDO.setIsAllowRefund(true);
		productDO.setKeywords("fdf");
		productDO.setMaxPrice(new BigDecimal(1));
		productDO.setMerchantId(1L);
		productDO.setMerchantNum("B1000");
		productDO.setMinPrice(new BigDecimal(1));
		productDO.setTotalFavorite(1);
		productDO.setTotalInventory(1);
		productDO.setTotalSalesVolume(1);
		productDOMapper.insertSelective(productDO);

		ListProductParam param = new ListProductParam();
		param.setCurrentPage(1);
		param.setName("name");
		param.setPageSize(10);
		param.setSortName("id");
		param.setSortOrder("desc");
		param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
		List<ProductInfoBO> list = productService.listProduct(param);
		log.info(JSON.toJSON(list));
		Assert.assertEquals(list.size(), 1);
	}

	/**
	 * 更新商品平均日销量
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateAverageDailySalesById() {
//		EditProductDataParam product = initProduct("1");
//		productService.eidtProduct(product);
//		EditProductDataParam product1 = initProduct("2");
//		productService.eidtProduct(product1);
		ProductDO productDO  = new ProductDO();
		productDO.setStatus(new Byte("1"));
		productDO.setName("name");
		productDO.setGmtModified(new Date());
		productDO.setAverageDailySales(new BigDecimal(1));
		productDO.setCategoryId(1);
		productDO.setContent("content");
		productDO.setFeatureImage("/fdf/1.jpg");
		productDO.setGmtCreate(new Date());
		productDO.setGmtDown(new Date());
		productDO.setImageContent("[]");
		productDO.setIsAllowRefund(true);
		productDO.setKeywords("fdf");
		productDO.setMaxPrice(new BigDecimal(1));
		productDO.setMerchantId(1L);
		productDO.setMerchantNum("B1000");
		productDO.setMinPrice(new BigDecimal(1));
		productDO.setTotalFavorite(1);
		productDO.setTotalInventory(1);
		productDO.setTotalSalesVolume(1);
		productDOMapper.insertSelective(productDO);

		productService.updateAverageDailySalesById(productDO.getId(), new BigDecimal(888));
		ListProductParam param =new ListProductParam();
		param.setCurrentPage(1);
		param.setName("name");
		param.setPageSize(10);
		param.setSortName("id");
		param.setSortOrder("desc");
		param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
		Page<ProductQueryBO> page = productService.listAllProduct(param);
		log.info(JSON.toJSON(page));
	}

	/**
	 * 重建商品索引
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void rebuildProductIndex() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		productService.rebuildProductIndex(1000);
	}

	/**
	 * 删除无效的商品索引
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void delInvalidProductIndex() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		productService.delInvalidProductIndex(DelIndexTypeEnum.ALL);
	}

	/**
	 * 查询所有上架的商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listAllProduct() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		ListProductParam param =new ListProductParam();
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setSortName("id");
		param.setSortOrder("desc");
		param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
		Page<ProductQueryBO> page = productService.listAllProduct(param);
		log.info(JSON.toJSON(page));
		
	}

	/**
	 * 根据ids查询商品信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listProductByIds() {
		EditProductDataParam product = initProduct("1");
		productService.eidtProduct(product);
		EditProductDataParam product1 = initProduct("2");
		productService.eidtProduct(product1);
		ListProductParam param =new ListProductParam();
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setSortName("id");
		param.setSortOrder("desc");
		param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
		Page<ProductQueryBO> page = productService.listAllProduct(param);
		List<Long> ids =Lists.newArrayList();
		for (int i = 0; i < page.getRecords().size(); i++) {
			ids.add(page.getRecords().get(i).getId());
		}
		List<ProductBO> list = productService.listProductByIds(ids);
		log.info(JSON.toJSON(list));
		Assert.assertEquals(list.size(), 2);
	}

	/**
	 * @return
	 */
	private EditProductDataParam initProduct(String str) {
		EditProductDataParam product = new EditProductDataParam();
		product.setProductId(0L);
		product.setCategoryId(1);
		product.setName("小炒肉".concat(str));
		product.setContent("商品描述".concat(str));
		List<ProductModelDO> listModel = new ArrayList<ProductModelDO>();
		ProductModelDO model = new ProductModelDO();
		model.setName("大碗".concat(str));
		model.setOriginalPrice(new BigDecimal(15));
		model.setPrice(new BigDecimal(12));
		model.setInventory(1);
		model.setSalesVolume(0);
		ProductModelDO model1 = new ProductModelDO();
		model1.setName("小碗".concat(str));
		model1.setOriginalPrice(new BigDecimal(10));
		model1.setPrice(new BigDecimal(8));
		model1.setInventory(1);
		model1.setSalesVolume(0);
		listModel.add(model);
		listModel.add(model1);
		product.setSpec(JSON.toJSONString(listModel));
		product.setImageContents("['详情图片描述111','详情图片描述222']");// ["sssssssssssss","ddddddddddddddddd"]
		product.setIsAllowRefund(true);
		product.setMerchantId(1L);
		product.setMerchantNum("1");
		product.setFeatureImage("www.baidu.com");
		product.setProductImages("www.163.com");
		product.setDetailImages("['描述111','描述222']");
		return product;
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void soldOutProductByMerchantId(){
		ProductDO productDO = new ProductDO();
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		productDO.setId(1l);
		productDO.setMerchantId(1L);
		productDO.setContent("test");
		productDOMapper.insertSelective(productDO);
		productService.soldOutProductByMerchantId(1L);

		List<ProductDO> productDOS = productDOMapper.selectByExample(null);
		Assert.assertNotNull(productDOS);
		Assert.assertEquals(productDOS.get(0).getStatus(), ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void countProduct() {
		ProductDO productDO = new ProductDO();
		productDO.setMerchantId(100L);
		productDO.setTotalInventory(10);
		productDO.setPosition(ProductPositionEnum.SHOPWINDOW.getVal());
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_DEL.getVal());
		productDOMapper.insertSelective(productDO);
		productDO.setTotalInventory(0);
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		productDOMapper.insertSelective(productDO);
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal());
		productDOMapper.insertSelective(productDO);
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_TEMP.getVal());
		productDOMapper.insertSelective(productDO);

		ProductTypeCountBO countBO = productService.countProduct(productDO.getMerchantId());
		Assert.assertNotNull(countBO);
		Assert.assertEquals(1, countBO.getSellingCount().intValue());
		Assert.assertEquals(2, countBO.getWarehouseCount().intValue());
		Assert.assertEquals(1, countBO.getShopwindowCount().intValue());
		Assert.assertEquals(2, countBO.getSoldOutCount().intValue());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listProduct1() {
		ProductDO productDO = new ProductDO();
		productDO.setMerchantId(100L);
		productDO.setName("test商品");
		productDO.setTotalInventory(10);
		productDO.setCategoryId(10);
		productDO.setCategorySubitemId(1010L);
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal());
		productDO.setGmtUp(new Date());
		productDOMapper.insertSelective(productDO);

		ProductListQuery query = new ProductListQuery();
		query.setSortEnum(ProductListSortEnum.PRODUCT_STATUS_UP_ASC);
		query.setName("test");
		query.setCategoryId(10);
		query.setCategorySubitemId(1010L);
		query.setQueryTypeEnum(ProductQueryTypeEnum.WAREHOUSE);
		Page<ProductListBO> page = productService.listProduct(productDO.getMerchantId(), query);
		Assert.assertNotNull(page.getRecords());
		Assert.assertEquals(1, page.getTotalCount().intValue());
		Assert.assertEquals(productDO.getName(), page.getRecords().get(0).getName());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateProductPosition() {
		ProductDO productDO = new ProductDO();
		productDO.setMerchantId(100L);
		productDO.setName("test商品");
		productDO.setTotalInventory(10);
		productDO.setCategoryId(10);
		productDO.setCategorySubitemId(1010L);
		productDO.setPosition(ProductPositionEnum.SHOPWINDOW.getVal());
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		productDO.setGmtUp(new Date());
		productDOMapper.insertSelective(productDO);

		productService.updateProductPosition(productDO.getMerchantId(), String.valueOf(productDO.getId()));
		productDO = productDOMapper.selectByPrimaryKey(productDO.getId());
		Assert.assertNotNull(productDO);
		Assert.assertEquals(ProductPositionEnum.CANCEL.getVal(), productDO.getPosition());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getProductDetail() {
		ProductDO productDO = new ProductDO();
		productDO.setMerchantId(100L);
		productDO.setName("test商品");
		productDO.setTotalInventory(10);
		productDO.setCategoryId(10);
		productDO.setCategorySubitemId(1010L);
		productDO.setImageContent("[\"\"]");
		productDO.setPosition(ProductPositionEnum.SHOPWINDOW.getVal());
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		productDO.setGmtUp(new Date());
		productDOMapper.insertSelective(productDO);

		ProductModelDO modelDO = new ProductModelDO();
		modelDO.setMerchantId(productDO.getMerchantId());
		modelDO.setProductId(productDO.getId());
		modelDO.setPrice(BigDecimal.valueOf(12));
		modelDO.setSalesVolume(10);
		modelDO.setInventory(5);
		modelDO.setStatus(true);
		modelDO.setSpecOption1(1L);
		modelDO.setSpecOption2(2L);
		productModelDOMapper.insertSelective(modelDO);

		ProductImageDO imageDO = new ProductImageDO();
		imageDO.setProductId(productDO.getId());
		imageDO.setImagePath("png");
		imageDO.setStatus(true);
		imageDO.setImgType(ProductImgTypeEnum.PRODUCT_IMG_HEAD.getVal());
		imageDO.setSortid(1);
		productImageDOMapper.insertSelective(imageDO);
		imageDO.setImgType(ProductImgTypeEnum.PRODUCT_IMG_DETAIL.getVal());
		productImageDOMapper.insertSelective(imageDO);

		ProductDetailBO detailBO = productService.getProductDetail(productDO.getId());
		Assert.assertNotNull(detailBO);
		Assert.assertEquals(1, detailBO.getImagesHeadUrl().size());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listProductCity() {
		ProductDO productDO = new ProductDO();
		productDO.setCityId(4403L);
		productDO.setCityName("深圳市");
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		productDOMapper.insertSelective(productDO);

		List<ProductCityDOView> cityDOViews = productService.listProductCity();
		Assert.assertNotNull(cityDOViews);
		Assert.assertEquals(productDO.getCityId(), cityDOViews.get(0).getCityId());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listMerchantProductCategory() {
		ProductDO productDO = new ProductDO();
		productDO.setMerchantId(100L);
		productDO.setCategoryId(3);
		productDO.setCategoryName("服装");
		productDO.setCategorySubitemId(13L);
		productDO.setCategorySubitemName("男装");
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		productDOMapper.insertSelective(productDO);

		List<ProductCategoryTierBO> tierBOS = productService.listMerchantProductCategory(productDO.getMerchantId());
		Assert.assertNotNull(tierBOS);
		Assert.assertEquals(productDO.getCategoryId(), tierBOS.get(0).getId());
		Assert.assertEquals(productDO.getCategorySubitemId().intValue(), tierBOS.get(0).getItemDOViews().get(0).getId().intValue());
	}

}
