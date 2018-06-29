/**
 * 
 */
package com.lawu.eshop.product.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.MemberProductImageDetailDTO;
import com.lawu.eshop.product.dto.MemberProductModelDTO;
import com.lawu.eshop.product.dto.ProductCategoryTierDTO;
import com.lawu.eshop.product.dto.ProductCityDTO;
import com.lawu.eshop.product.dto.ProductDetailDTO;
import com.lawu.eshop.product.dto.ProductInfoDTO;
import com.lawu.eshop.product.dto.ProductListDTO;
import com.lawu.eshop.product.dto.ProductQueryDTO;
import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.dto.ProductTypeCountDTO;
import com.lawu.eshop.product.param.EditProductDataParam;
import com.lawu.eshop.product.param.EditProductDataParam_bak;
import com.lawu.eshop.product.srv.bo.ProductBO;
import com.lawu.eshop.product.srv.bo.ProductCategoryTierBO;
import com.lawu.eshop.product.srv.bo.ProductDetailBO;
import com.lawu.eshop.product.srv.bo.ProductEditInfoBO;
import com.lawu.eshop.product.srv.bo.ProductInfoBO;
import com.lawu.eshop.product.srv.bo.ProductListBO;
import com.lawu.eshop.product.srv.bo.ProductModelInfoBO;
import com.lawu.eshop.product.srv.bo.ProductQueryBO;
import com.lawu.eshop.product.srv.bo.ProductSearchBO;
import com.lawu.eshop.product.srv.bo.ProductTypeCountBO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.extend.ProductCategoryItemDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductCityDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductDOView;
import com.lawu.eshop.product.srv.domain.extend.ShoppingProductDOView;
import com.lawu.utils.DateUtil;
import com.lawu.utils.StringUtil;

/**
 * @author lihj
 * @date 2017年7月13日
 */
public class ProductConverterTest {

	@Test
	public void convertDTOS() {
		List<ProductQueryBO> productBOS = new ArrayList<ProductQueryBO>();
		ProductQueryBO bo = new ProductQueryBO();
		bo.setId(1l);
		bo.setMerchantId(2L);
		bo.setName("测试");
		bo.setCategory("category");
		bo.setFeatureImage("featureImage");
		bo.setGmtCreate("gmtcreate");
		bo.setSpec("spec");
		bo.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
		bo.setTotalInventory("totalInventory");
		bo.setTotalFavorite("totalFavorite");
		bo.setMinPrice("minPrice");
		productBOS.add(bo);
		List<ProductQueryDTO> list = ProductConverter.convertDTOS(productBOS);
		Assert.assertEquals(list.get(0).getId(), bo.getId());
		Assert.assertEquals(list.get(0).getMerchantId(), bo.getMerchantId());
		Assert.assertEquals(list.get(0).getName(), bo.getName());
		Assert.assertEquals(list.get(0).getCategory(), bo.getCategory());
		Assert.assertEquals(list.get(0).getFeatureImage(), bo.getFeatureImage());
		Assert.assertEquals(list.get(0).getGmtCreate(), bo.getGmtCreate());
		Assert.assertEquals(list.get(0).getSpec(), bo.getSpec());
		Assert.assertEquals(list.get(0).getStatus(), bo.getStatus());
		Assert.assertEquals(list.get(0).getTotalInventory(), bo.getTotalInventory());
		Assert.assertEquals(list.get(0).getTotalSalesVolume(), bo.getTotalSalesVolume());
		Assert.assertEquals(list.get(0).getTotalFavorite(), bo.getTotalFavorite());
		Assert.assertEquals(list.get(0).getMinPrice(), bo.getMinPrice());
	}

	@Test
	public void convertQueryBO() {
		ProductDO pdo = new ProductDO();
		pdo.setId(1L);
		pdo.setMerchantId(2l);
		pdo.setName("测试");
		pdo.setFeatureImage("featureImage");
		pdo.setGmtCreate(new Date());
		pdo.setStatus(Byte.valueOf("2"));
		pdo.setTotalInventory(4);
		pdo.setTotalSalesVolume(5);
		pdo.setTotalFavorite(7);
		pdo.setMinPrice(new BigDecimal(200));
		ProductQueryBO bo = ProductConverter.convertQueryBO(pdo);
		Assert.assertEquals(bo.getId(), pdo.getId());
		Assert.assertEquals(bo.getMerchantId(), pdo.getMerchantId());
		Assert.assertEquals(bo.getName(), pdo.getName());
		Assert.assertEquals(bo.getFeatureImage(), pdo.getFeatureImage());
		Assert.assertEquals(bo.getGmtCreate(), DateUtil.getDateFormat(pdo.getGmtCreate(), "yyyy-MM-dd"));
		Assert.assertEquals(bo.getStatus().getVal(), pdo.getStatus());
		Assert.assertEquals(Integer.valueOf(bo.getTotalInventory()), pdo.getTotalInventory());
		Assert.assertEquals(Integer.valueOf(bo.getTotalSalesVolume()), pdo.getTotalSalesVolume());
		Assert.assertEquals(Integer.valueOf(bo.getTotalFavorite()), pdo.getTotalFavorite());
		Assert.assertEquals(new BigDecimal(bo.getMinPrice()), pdo.getMinPrice());
	}

	@Test
	public void convertDTO() {
		ProductQueryBO bo = new ProductQueryBO();
		bo.setId(1L);
		bo.setName("name");
		bo.setCategory("category");
		bo.setFeatureImage("featureImage");
		bo.setGmtCreate("gmtCreate");
		bo.setSpec("spec");
		bo.setStatus(ProductStatusEnum.PRODUCT_STATUS_DOWN);
		ProductQueryDTO dto = ProductConverter.convertDTO(bo);
		Assert.assertEquals(dto.getId(), bo.getId());
		Assert.assertEquals(dto.getName(), bo.getName());
		Assert.assertEquals(dto.getCategory(), bo.getCategory());
		Assert.assertEquals(dto.getFeatureImage(), bo.getFeatureImage());
		Assert.assertEquals(dto.getGmtCreate(), bo.getGmtCreate());
		Assert.assertEquals(dto.getSpec(), bo.getSpec());
		Assert.assertEquals(dto.getStatus(), bo.getStatus());
	}

	@Test
	public void convertInfoBO() {
		ProductDO pdo = new ProductDO();
		pdo.setId(1L);
		pdo.setName("name");
		pdo.setFeatureImage("featureImage");
		pdo.setContent("content");
		pdo.setMerchantId(2L);
		pdo.setMerchantNum("merchantNum");
		pdo.setTotalSalesVolume(3);
		pdo.setTotalInventory(4);
		pdo.setMaxPrice(new BigDecimal(100));
		pdo.setMinPrice(new BigDecimal(50));
		pdo.setGmtCreate(new Date());
		pdo.setCategoryId(8);
		pdo.setIsAllowRefund(true);
		pdo.setStatus(Byte.valueOf("1"));

		ProductInfoBO pb = ProductConverter.convertInfoBO(pdo);
		Assert.assertEquals(pb.getId(), pdo.getId());
		Assert.assertEquals(pb.getName(), pdo.getName());
		Assert.assertEquals(pb.getFeatureImage(), pdo.getFeatureImage());
		Assert.assertEquals(pb.getContent(), pdo.getContent());
		Assert.assertEquals(pb.getMerchantId(), pdo.getMerchantId());
		Assert.assertEquals(pb.getMerchantNum(), pdo.getMerchantNum());
		Assert.assertEquals(pb.getTotalSalesVolume(), pdo.getTotalSalesVolume());
		Assert.assertEquals(pb.getTotalInventory(), pdo.getTotalInventory());
		Assert.assertEquals(new BigDecimal(pb.getMaxPrice()), pdo.getMaxPrice());
		Assert.assertEquals(new BigDecimal(pb.getMinPrice()), pdo.getMinPrice());
		Assert.assertEquals(pb.getGmtCreate(), pdo.getGmtCreate());
		Assert.assertEquals(pb.getCategoryId(), pdo.getCategoryId());
		Assert.assertEquals(pb.isAllowRefund(), pdo.getIsAllowRefund());
		Assert.assertEquals(pb.getProductStatus().getVal(), pdo.getStatus());
	}
	
	@Test
	public void convertEditInfoBO(){
		ProductDO productDO =new ProductDO();
		productDO.setId(1l);
		productDO.setName("name");
		productDO.setCategoryId(2);
		productDO.setFeatureImage("featureImage");
		productDO.setContent("content");
		productDO.setMerchantId(3L);
		productDO.setImageContent(null);
		productDO.setIsAllowRefund(true);
		
		ProductEditInfoBO bo =ProductConverter.convertEditInfoBO(productDO);
		Assert.assertEquals(bo.getId(), productDO.getId());
		Assert.assertEquals(bo.getName(), productDO.getName());
		Assert.assertEquals(bo.getCategory(), Long.valueOf(productDO.getCategoryId()));
		Assert.assertEquals(bo.getFeatureImage(), productDO.getFeatureImage());
		Assert.assertEquals(bo.getContent(), productDO.getContent());
		Assert.assertEquals(bo.getMerchantId(), productDO.getMerchantId());
		String str =(productDO.getImageContent() == null || "".equals(productDO.getImageContent())) ? "[]" : productDO.getImageContent();
		Assert.assertEquals(bo.getImageContent(), StringUtil.getJsonListToStringList(str));
		Assert.assertEquals(bo.isAllowRefund(), productDO.getIsAllowRefund());
	}
	
	@Test
	public void convertInfoDTO(){
		ProductInfoBO bo =new ProductInfoBO();
		bo.setId(1L);
		bo.setMerchantId(2L);
		bo.setName("name");
		bo.setFeatureImage("featureImage");
		bo.setContent("content");
		List<String> list =Lists.newArrayList();
		list.add("1");
		list.add("2");
		bo.setImagesHeadUrl(list);
		List<MemberProductModelDTO> listdto =Lists.newArrayList();
		MemberProductModelDTO dto =new MemberProductModelDTO();
		dto.setId(1L);
		dto.setInventory(2);
		dto.setName("name");
		dto.setOriginalPrice(new BigDecimal(5));
		dto.setPrice(new BigDecimal(7));
		listdto.add(dto);
		bo.setSpec(listdto);
		bo.setMaxPrice("maxPrice");
		bo.setMinPrice("minPrice");
		bo.setTotalSalesVolume(7);
		bo.setGmtCreate(new Date());

		ProductInfoDTO pi =ProductConverter.convertInfoDTO(bo);
		Assert.assertEquals(pi.getId(), bo.getId());
		Assert.assertEquals(pi.getMerchantId(), bo.getMerchantId());
		Assert.assertEquals(pi.getName(), bo.getName());
		Assert.assertEquals(pi.getFeatureImage(), bo.getFeatureImage());
		Assert.assertEquals(pi.getContent(), bo.getContent());
		Assert.assertEquals(pi.getImagesHeadUrl(), bo.getImagesHeadUrl());
		Assert.assertEquals(pi.getSpec(), bo.getSpec());
		Assert.assertEquals(pi.getMaxPrice(), bo.getMaxPrice());
		Assert.assertEquals(pi.getMinPrice(), bo.getMinPrice());
		Assert.assertEquals(pi.getTotalSalesVolume(), bo.getTotalSalesVolume());
		Assert.assertEquals(pi.getGmtCreate(), bo.getGmtCreate());
	}
	
	@Test
	public void convertDO(){
		EditProductDataParam_bak bak =new EditProductDataParam_bak();
		bak.setName("name");
		bak.setCategoryId(1);
		bak.setMerchantId(2L);
		bak.setContent("content");
		bak.setFeatureImage("featureImage");
		bak.setImageContents("imageContents");
		bak.setIsAllowRefund(true);
		
		ProductDO pd =ProductConverter.convertDO(bak, 0L);
		Assert.assertEquals(pd.getName(), bak.getName());
		Assert.assertEquals(pd.getCategoryId(), bak.getCategoryId());
		Assert.assertEquals(pd.getMerchantId(), bak.getMerchantId());
		Assert.assertEquals(pd.getContent(), bak.getContent());
		Assert.assertEquals(pd.getFeatureImage(), bak.getFeatureImage());
		Assert.assertEquals(pd.getImageContent(), bak.getImageContents());
		Assert.assertEquals(pd.getIsAllowRefund(), bak.getIsAllowRefund());
		Assert.assertEquals(pd.getStatus(),ProductStatusEnum.PRODUCT_STATUS_UP.getVal() );
	}

	@Test
	public void convertDOEdit(){
		EditProductDataParam param =new EditProductDataParam();
		param.setName("name");
		param.setCategoryId(1);
		param.setContent("content");
		param.setFeatureImage("featureImage");
		param.setImageContents("imageContents");
		param.setIsAllowRefund(true);
		param.setMerchantId(2L);
		param.setMerchantNum("merchantNum");
		ProductDO po =ProductConverter.convertDO(param,0L);
		Assert.assertEquals(po.getName(), param.getName());
		Assert.assertEquals(po.getCategoryId(), param.getCategoryId());
		Assert.assertEquals(po.getContent(), param.getContent());
		Assert.assertEquals(po.getFeatureImage(), param.getFeatureImage());
		Assert.assertEquals(po.getImageContent(), param.getImageContents());
		Assert.assertEquals(po.getIsAllowRefund(), param.getIsAllowRefund());
		Assert.assertEquals(po.getMerchantId(), param.getMerchantId());
		Assert.assertEquals(po.getMerchantNum(), param.getMerchantNum());
	}
	
	@Test
	public void convertBO(){
		List<ProductDO> productDOS =Lists.newArrayList();
		ProductDO pd =new ProductDO();
		pd.setId(1L);
		pd.setName("name");
		pd.setContent("content");
		pd.setFeatureImage("featureImage");
		pd.setMaxPrice(new BigDecimal(100));
		pd.setMinPrice(new BigDecimal(200));
		pd.setTotalSalesVolume(4);
		productDOS.add(pd);
		List<ProductSearchBO> list =ProductConverter.convertBO(productDOS);
		Assert.assertEquals(list.get(0).getProductId(), pd.getId());
		Assert.assertEquals(list.get(0).getName(), pd.getName());
		Assert.assertEquals(list.get(0).getContent(), pd.getContent());
		Assert.assertEquals(list.get(0).getFeatureImage(), pd.getFeatureImage());
		Assert.assertEquals(list.get(0).getOriginalPrice(), pd.getMaxPrice().doubleValue(),0);
		Assert.assertEquals(list.get(0).getPrice(), pd.getMinPrice().doubleValue(),0);
		Assert.assertEquals(list.get(0).getSalesVolume(), pd.getTotalSalesVolume());
	}
	
	
	@Test
	public void convertDTOList(){
		List<ProductSearchBO> productSearchBOS  =Lists.newArrayList();
		ProductSearchBO ps =new ProductSearchBO();
		ps.setProductId(1L);
		ps.setName("name");
		ps.setContent("content");
		ps.setFeatureImage("featureImage");
		ps.setOriginalPrice(Double.valueOf(100));
		ps.setPrice(Double.valueOf(200));
		ps.setSalesVolume(500);
		productSearchBOS.add(ps);
		List<ProductSearchDTO> list =ProductConverter.convertDTO(productSearchBOS);
		Assert.assertEquals(list.get(0).getProductId(), ps.getProductId());
		Assert.assertEquals(list.get(0).getName(), ps.getName());
		Assert.assertEquals(list.get(0).getContent(), ps.getContent());
		Assert.assertEquals(list.get(0).getFeatureImage(), ps.getFeatureImage());
		Assert.assertEquals(list.get(0).getOriginalPrice(), ps.getOriginalPrice());
		Assert.assertEquals(list.get(0).getPrice(), ps.getPrice());
		Assert.assertEquals(list.get(0).getSalesVolume(), ps.getSalesVolume());
	}
	@Test
	public void convertSearchDTO(){
		List<ProductBO> productBOS =Lists.newArrayList();
		ProductBO pb =new ProductBO();
		pb.setId(1L);
		pb.setTotalSalesVolume(3);
		pb.setMaxPrice(Double.valueOf(100));
		pb.setMaxPrice(Double.valueOf(200));
		pb.setMinPrice(Double.valueOf(500));
		productBOS.add(pb);
		List<ProductSearchDTO> list =ProductConverter.convertSearchDTO(productBOS);
		Assert.assertEquals(list.get(0).getProductId(),pb.getId());	
		Assert.assertEquals(list.get(0).getSalesVolume(),pb.getTotalSalesVolume());	
		Assert.assertEquals(list.get(0).getOriginalPrice(),pb.getMaxPrice());	
		Assert.assertEquals(list.get(0).getPrice(),pb.getMinPrice());	
	}
	
	@Test
	public void convertBOS(){
		List<ProductDOView> productDOViewList =Lists.newArrayList();
		ProductDOView view =new ProductDOView();
		view.setId(1L);
		view.setTotalSalesVolume(2);
		view.setMaxPrice(Double.valueOf(200));
		view.setMinPrice(Double.valueOf(500));
		productDOViewList.add(view);
		
		List<ProductBO> list =ProductConverter.convertBOS(productDOViewList);
		Assert.assertEquals(list.get(0).getId(),view.getId());	
		Assert.assertEquals(list.get(0).getTotalSalesVolume(),view.getTotalSalesVolume());	
		Assert.assertEquals(list.get(0).getMaxPrice(),view.getMaxPrice());	
		Assert.assertEquals(list.get(0).getMinPrice(),view.getMinPrice());	
	}
	
	@Test
	public void convertSearchBOS(){
		List<ShoppingProductDOView> productDOViews =Lists.newArrayList();
		ShoppingProductDOView view =new ShoppingProductDOView();
		view.setName("name");
		view.setFeatureImage("featureImage");
		view.setMaxPrice(new BigDecimal(100));
		view.setProductId(2L);
		view.setMinPrice(new BigDecimal(200));
		view.setTotalSalesVolume(5);
		productDOViews.add(view);
		
		List<ProductSearchBO> list =ProductConverter.convertSearchBOS(productDOViews);
		Assert.assertEquals(list.get(0).getName(),view.getName());	
		Assert.assertEquals(list.get(0).getFeatureImage(),view.getFeatureImage());	
		Assert.assertEquals(list.get(0).getOriginalPrice(),view.getMaxPrice().doubleValue(),0);	
		Assert.assertEquals(list.get(0).getPrice(),view.getMinPrice().doubleValue(),0);	
		Assert.assertEquals(list.get(0).getProductId(),view.getProductId());	
		Assert.assertEquals(list.get(0).getSalesVolume(),view.getTotalSalesVolume());	
	}

	@Test
	public void countCountDTO() {
		ProductTypeCountBO countBO = new ProductTypeCountBO();
		countBO.setSellingCount(1);
		countBO.setWarehouseCount(2);
		countBO.setShopwindowCount(3);
		countBO.setSoldOutCount(4);

		ProductTypeCountDTO countDTO = ProductConverter.countCountDTO(countBO);
		Assert.assertNotNull(countBO);
		Assert.assertEquals(countBO.getSellingCount(), countDTO.getSellingCount());
		Assert.assertEquals(countBO.getWarehouseCount(), countDTO.getWarehouseCount());
		Assert.assertEquals(countBO.getShopwindowCount(), countDTO.getShopwindowCount());
		Assert.assertEquals(countBO.getSoldOutCount(), countDTO.getSoldOutCount());
	}

	@Test
	public void convertListBOS() {
		List<ProductDO> productDOS = new ArrayList<>();
		ProductDO productDO = new ProductDO();
		productDO.setId(100L);
		productDO.setName("test");
		productDO.setFeatureImage("png");
		productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		productDO.setTotalInventory(10);
		productDO.setTotalSalesVolume(2);
		productDO.setGmtUp(new Date());
		productDOS.add(productDO);

		List<ProductListBO> listBOS = ProductConverter.convertListBOS(productDOS);
		Assert.assertNotNull(listBOS);
		Assert.assertEquals(productDO.getId(), listBOS.get(0).getId());
		Assert.assertEquals(productDO.getName(), listBOS.get(0).getName());
		Assert.assertEquals(productDO.getFeatureImage(), listBOS.get(0).getFeatureImage());
		Assert.assertEquals(productDO.getStatus(), listBOS.get(0).getStatus());
		Assert.assertEquals(productDO.getTotalInventory(), listBOS.get(0).getTotalInventory());
		Assert.assertEquals(productDO.getTotalSalesVolume(), listBOS.get(0).getTotalSalesVolume());
		Assert.assertEquals(productDO.getGmtUp(), listBOS.get(0).getGmtUp());
	}

	@Test
	public void convertListDTOS() {
		List<ProductListBO> listBOS = new ArrayList<>();
		ProductListBO listBO = new ProductListBO();
		listBO.setId(100L);
		listBO.setName("test");
		listBO.setFeatureImage("png");
		listBO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		listBO.setTotalInventory(10);
		listBO.setTotalSalesVolume(2);
		listBO.setGmtUp(new Date());
		listBOS.add(listBO);

		List<ProductListDTO> listDTOS = ProductConverter.convertListDTOS(listBOS);
		Assert.assertNotNull(listDTOS);
		Assert.assertEquals(listBO.getId(), listDTOS.get(0).getId());
		Assert.assertEquals(listBO.getName(), listDTOS.get(0).getName());
		Assert.assertEquals(listBO.getFeatureImage(), listDTOS.get(0).getFeatureImage());
		Assert.assertEquals(ProductStatusEnum.getEnum(listBO.getStatus()), listDTOS.get(0).getStatusEnum());
		Assert.assertEquals(listBO.getTotalInventory(), listDTOS.get(0).getTotalInventory());
		Assert.assertEquals(listBO.getTotalSalesVolume(), listDTOS.get(0).getTotalSalesVolume());
		Assert.assertEquals(listBO.getGmtUp(), listDTOS.get(0).getGmtUp());
	}

	@Test
	public void convertDetailBO() {
		ProductDO productDO = new ProductDO();
		productDO.setId(100L);
		productDO.setMerchantId(10L);
		productDO.setCategoryId(10);
		productDO.setCategorySubitemId(1010L);
		productDO.setName("test");
		productDO.setContent("[]");
		productDO.setFeatureImage("png");
		productDO.setSpec("spec");
		productDO.setMinPrice(BigDecimal.valueOf(10));
		productDO.setMaxPrice(BigDecimal.valueOf(100));
		productDO.setTotalSalesVolume(10);
		productDO.setTotalInventory(15);
		productDO.setIsAllowRefund(true);

		ProductDetailBO detailBO = ProductConverter.convertDetailBO(productDO);
		Assert.assertNotNull(detailBO);
		Assert.assertEquals(productDO.getId(), detailBO.getId());
		Assert.assertEquals(productDO.getMerchantId(), detailBO.getMerchantId());
		Assert.assertEquals(productDO.getCategoryId(), detailBO.getCategoryId());
		Assert.assertEquals(productDO.getCategorySubitemId(), detailBO.getCategorySubitemId());
		Assert.assertEquals(productDO.getName(), detailBO.getName());
		Assert.assertEquals(productDO.getContent(), detailBO.getContent());
		Assert.assertEquals(productDO.getFeatureImage(), detailBO.getFeatureImage());
		Assert.assertEquals(productDO.getSpec(), detailBO.getSpec());
		Assert.assertEquals(productDO.getMinPrice(), detailBO.getMinPrice());
		Assert.assertEquals(productDO.getMaxPrice(), detailBO.getMaxPrice());
		Assert.assertEquals(productDO.getTotalSalesVolume(), detailBO.getTotalSalesVolume());
		Assert.assertEquals(productDO.getTotalInventory(), detailBO.getTotalInventory());
		Assert.assertEquals(productDO.getIsAllowRefund(), detailBO.getIsAllowRefund());
	}

	@Test
	public void convertDetailDTO() {
		ProductDetailBO detailBO = new ProductDetailBO();
		detailBO.setId(100L);
		detailBO.setMerchantId(10L);
		detailBO.setCategoryId(10);
		detailBO.setCategorySubitemId(1010L);
		detailBO.setName("test");
		detailBO.setContent("[]");
		detailBO.setFeatureImage("png");
		detailBO.setSpec("[{\"options\":[{\"icon\":\"\",\"name\":\"L\",\"id\":1},{\"icon\":\"\",\"name\":\"M\",\"id\":2}],\"specId\":1,\"specName\":\"尺寸\"},{\"options\":[{\"icon\":\"\",\"name\":\"白色\",\"id\":3},{\"icon\":\"\",\"name\":\"红色\",\"id\":4}],\"specId\":2,\"specName\":\"颜色\"}]");
		detailBO.setMinPrice(BigDecimal.valueOf(10));
		detailBO.setMaxPrice(BigDecimal.valueOf(100));
		detailBO.setTotalSalesVolume(10);
		detailBO.setTotalInventory(15);
		detailBO.setIsAllowRefund(true);
		List<String> imagesHeadUrl = new ArrayList<>();
		imagesHeadUrl.add("png");
		detailBO.setImagesHeadUrl(imagesHeadUrl);
		List<MemberProductImageDetailDTO> imageDetail = new ArrayList<>();
		MemberProductImageDetailDTO imageDetailDTO = new MemberProductImageDetailDTO();
		imageDetailDTO.setDetail("detail");
		imageDetailDTO.setImageUrl("png");
		imageDetail.add(imageDetailDTO);
		detailBO.setImageDetail(imageDetail);
		List<ProductModelInfoBO> infoBOS = new ArrayList<>();
		ProductModelInfoBO infoBO = new ProductModelInfoBO();
		infoBO.setId(100L);
		infoBO.setModelKey("1;3;5");
		infoBO.setPrice(BigDecimal.valueOf(20));
		infoBO.setInventory(10);
		infoBOS.add(infoBO);
		detailBO.setModelInfo(infoBOS);

		ProductDetailDTO detailDTO = ProductConverter.convertDetailDTO(detailBO);
		Assert.assertNotNull(detailDTO);
		Assert.assertEquals(detailBO.getId(), detailDTO.getId());
		Assert.assertEquals(detailBO.getMerchantId(), detailDTO.getMerchantId());
		Assert.assertEquals(detailBO.getCategoryId(), detailDTO.getCategoryId());
		Assert.assertEquals(detailBO.getCategorySubitemId(), detailDTO.getCategorySubitemId());
		Assert.assertEquals(detailBO.getName(), detailDTO.getName());
		Assert.assertEquals(detailBO.getContent(), detailDTO.getContent());
		Assert.assertEquals(detailBO.getFeatureImage(), detailDTO.getFeatureImage());
		Assert.assertEquals(2, detailDTO.getSpecJsonDTOS().size());
		Assert.assertEquals(detailBO.getMinPrice(), detailDTO.getMinPrice());
		Assert.assertEquals(detailBO.getMaxPrice(), detailDTO.getMaxPrice());
		Assert.assertEquals(detailBO.getTotalSalesVolume(), detailDTO.getTotalSalesVolume());
		Assert.assertEquals(detailBO.getTotalInventory(), detailDTO.getTotalInventory());
		Assert.assertEquals(detailBO.getIsAllowRefund(), detailDTO.getIsAllowRefund());
		Assert.assertEquals(detailBO.getImagesHeadUrl(), detailDTO.getImagesHeadUrl());
		Assert.assertEquals(detailBO.getImageDetail(), detailDTO.getImageDetail());
		Assert.assertEquals(infoBO.getId(), detailDTO.getModelInfo().get(0).getId());
		Assert.assertEquals(infoBO.getModelKey(), detailDTO.getModelInfo().get(0).getModelKey());
		Assert.assertEquals(infoBO.getPrice(), detailDTO.getModelInfo().get(0).getPrice());
		Assert.assertEquals(infoBO.getInventory(), detailDTO.getModelInfo().get(0).getInventory());
	}

	@Test
	public void convertProductCityDTOS() {
		List<ProductCityDOView> cityDOViews = new ArrayList<>();
		ProductCityDOView cityDOView = new ProductCityDOView();
		cityDOView.setCityId(4403L);
		cityDOView.setCityName("深圳市");
		cityDOViews.add(cityDOView);

		List<ProductCityDTO> cityDTOS = ProductConverter.convertProductCityDTOS(cityDOViews);
		Assert.assertNotNull(cityDTOS);
		Assert.assertEquals(cityDOView.getCityId(), cityDTOS.get(0).getCityId());
		Assert.assertEquals(cityDOView.getCityName(), cityDTOS.get(0).getCityName());
	}

	@Test
	public void convertProductCategoryTierDTOS() {
		List<ProductCategoryTierBO> tierBOS = new ArrayList<>();
		List<ProductCategoryItemDOView> itemDOViews = new ArrayList<>();
		ProductCategoryItemDOView itemDOView = new ProductCategoryTierBO();
		itemDOView.setId(13);
		itemDOView.setName("男装");
		itemDOViews.add(itemDOView);
		ProductCategoryTierBO tierBO = new ProductCategoryTierBO();
		tierBO.setId(3);
		tierBO.setName("服装");
		tierBO.setItemDOViews(itemDOViews);
		tierBOS.add(tierBO);

		List<ProductCategoryTierDTO> tierDTOS = ProductConverter.convertProductCategoryTierDTOS(tierBOS);
		Assert.assertNotNull(tierDTOS);
		Assert.assertEquals(tierBO.getId(), tierDTOS.get(0).getId());
		Assert.assertEquals(tierBO.getName(), tierDTOS.get(0).getName());
		Assert.assertEquals(itemDOView.getId(), tierDTOS.get(0).getItemDTOS().get(0).getId());
		Assert.assertEquals(itemDOView.getName(), tierDTOS.get(0).getItemDTOS().get(0).getName());
	}

}
