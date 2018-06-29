/**
 *
 */
package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.constant.ProductListSortEnum;
import com.lawu.eshop.product.constant.ProductQueryTypeEnum;
import com.lawu.eshop.product.constant.ProductSortFieldEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.param.EditProductDataParam;
import com.lawu.eshop.product.param.EditProductUpgradeDataParam;
import com.lawu.eshop.product.param.ListProductParam;
import com.lawu.eshop.product.param.ProductParam;
import com.lawu.eshop.product.query.ProductDataQuery;
import com.lawu.eshop.product.query.ProductListQuery;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductModelDO;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.framework.core.page.OrderType;
import com.lawu.framework.web.HttpCode;

/**
 * @author lihj
 * @date 2017年7月12日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductControllerTest {

    private MockMvc mvc;

    private Logger log = Logger.getLogger(FavoriteProductControllerTest.class);

    @Autowired
    private ProductController productController;
    @Autowired
    private ProductDOMapper productDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    /**
     * 添加、编辑商品
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveProduct() {
        EditProductDataParam product = initProduct("1");
        RequestBuilder request = post("/product/saveProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(product));
        try {
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /**
     * 查询商品列表
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectProduct() {
        ProductDataQuery query = new ProductDataQuery();
        query.setProductSortFieldEnum(ProductSortFieldEnum.TOTAL_INVENTORY);
        query.setProductStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
        query.setOrderType(OrderType.DESC);
        query.setMerchantId(1L);
        RequestBuilder request = post("/product/selectProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(query));
        try {
            addProduct();
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 商品批量操作上下架
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateProductStatus() {
        RequestBuilder request = put("/product/updateProductStatus").param("ids", "1,2")
                .param("productStatus", ProductStatusEnum.PRODUCT_STATUS_DOWN + "").param("merchantId", "1");
        // 查询
        ProductDataQuery query = new ProductDataQuery();
        query.setProductSortFieldEnum(ProductSortFieldEnum.TOTAL_INVENTORY);
        query.setProductStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
        query.setOrderType(OrderType.DESC);
        query.setMerchantId(1L);
        RequestBuilder requestQueryUp = post("/product/selectProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(query));
        // 查询下架
        ProductDataQuery queryDown = new ProductDataQuery();
        queryDown.setProductSortFieldEnum(ProductSortFieldEnum.TOTAL_INVENTORY);
        queryDown.setProductStatus(ProductStatusEnum.PRODUCT_STATUS_DOWN);
        queryDown.setOrderType(OrderType.DESC);
        queryDown.setMerchantId(1L);
        RequestBuilder requestQueryDown = post("/product/selectProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(query));
        try {
            addProduct();
            ResultActions performQueryUp = mvc.perform(requestQueryUp);
            log.info(performQueryUp.andReturn().getResponse().getContentAsString());

            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            ResultActions performQueryDown = mvc.perform(requestQueryDown);
            log.info(performQueryDown.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 用户端商品详情，根据ID查询商品详情
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectProductById() {
        ListProductParam param = new ListProductParam();
        param.setCurrentPage(1);
        param.setName("小炒肉");
        param.setPageSize(10);
        param.setSortName("id");
        param.setSortOrder("desc");
        param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);

        RequestBuilder requestq = post("/product/listProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            addProduct();
            ResultActions performq = mvc.perform(requestq);
            String str = performq.andReturn().getResponse().getContentAsString();
            List<String> list = forJson(str);
            for (int i = 0; i < list.size(); i++) {
                RequestBuilder request = get("/product/selectProductById").param("productId", list.get(i));
                ResultActions perform = mvc.perform(request);
                log.info(perform.andReturn().getResponse().getContentAsString());
                MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                        .andReturn();
                Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 商家端编辑商品时，根据ID查询商品
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectEditProductById() {
        ListProductParam param = new ListProductParam();
        param.setCurrentPage(1);
        param.setName("小炒肉");
        param.setPageSize(10);
        param.setSortName("id");
        param.setSortOrder("desc");
        param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);

        RequestBuilder requestq = post("/product/listProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            addProduct();
            ResultActions performq = mvc.perform(requestq);
            String str = performq.andReturn().getResponse().getContentAsString();
            List<String> list = forJson(str);
            for (int i = 0; i < list.size(); i++) {
                RequestBuilder request = get("/product/selectEditProductById").param("productId", list.get(i));
                ResultActions perform = mvc.perform(request);
                log.info(perform.andReturn().getResponse().getContentAsString());
                MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                        .andReturn();
                Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }


    /**
     * @param str
     * @return
     */
    private List<String> forJson(String str) {
        List<String> listids = Lists.newArrayList();
        JSONObject json = JSONObject.parseObject(str);
        List<JSONObject> list = (List<JSONObject>) json.get("model");
        for (int i = 0; i < list.size(); i++) {
            JSONObject obj = list.get(i);
            listids.add(obj.getString("id"));
        }
        return listids;
    }

    /**
     * 操作库存 num 加减数量数量 flag M-减、A-加
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void editTotalInventory() {
        try {
            //addProduct();
            ProductDO productDO = new ProductDO();
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

            RequestBuilder request = post("/product/editTotalInventory").param("productId", productDO.getId().toString()).param("num", "1000")
                    .param("flag", "A");
            RequestBuilder requestQuery = get("/product/selectProductById").param("productId", productDO.getId().toString());

            ResultActions perform = mvc.perform(request);
            ResultActions performQuery = mvc.perform(requestQuery);
            log.info(performQuery.andReturn().getResponse().getContentAsString());
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /**
     * 操作销量 num 加减数量数量 flag M-减、A-加
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void editTotalSaleVolume() {
        try {
            //addProduct();
            ProductDO productDO = new ProductDO();
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

            RequestBuilder request = post("/product/editTotalSaleVolume").param("productId", productDO.getId().toString()).param("num", "1000")
                    .param("flag", "A");
            RequestBuilder requestQuery = get("/product/selectProductById").param("productId", productDO.getId().toString());

            ResultActions perform = mvc.perform(request);
            ResultActions performQuery = mvc.perform(requestQuery);
            log.info(performQuery.andReturn().getResponse().getContentAsString());
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 根据商品ID查询商品
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProduct() {
        ListProductParam param = new ListProductParam();
        param.setCurrentPage(1);
        param.setName("小炒肉");
        param.setPageSize(10);
        param.setSortName("id");
        param.setSortOrder("desc");
        param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);

        RequestBuilder requestq = post("/product/listProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));

        try {
            addProduct();
            ResultActions performq = mvc.perform(requestq);
            String str = performq.andReturn().getResponse().getContentAsString();
            List<String> list = forJson(str);
            for (int i = 0; i < list.size(); i++) {

                RequestBuilder request = get("/product/getProduct/" + list.get(i));
                ResultActions perform = mvc.perform(request);
                log.info(perform.andReturn().getResponse().getContentAsString());
                MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                        .andReturn();
                Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 查询已审核的所有商品
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectProductByPlat() {
        ProductParam param = new ProductParam();
        param.setName("小炒肉");
        RequestBuilder request = post("/product/selectProductByPlat").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            addProduct();
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /**
     * 查询商家上架商品的总数量
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectProductCount() {
        RequestBuilder request = get("/product/selectProductCount").param("merchantId", "1");
        try {
            addProduct();
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 查询所有上架中商品
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listProduct() {
        ListProductParam param = new ListProductParam();
        param.setCurrentPage(1);
        param.setName("小炒肉");
        param.setPageSize(10);
        param.setSortName("id");
        param.setSortOrder("desc");
        param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);

        RequestBuilder request = post("/product/listProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            addProduct();
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /**
     * 更新商品平均日销量，同时更新solr
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateAverageDailySales() {
        ListProductParam param = new ListProductParam();
        param.setCurrentPage(1);
        param.setName("小炒肉");
        param.setPageSize(10);
        param.setSortName("id");
        param.setSortOrder("desc");
        param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);

        RequestBuilder requestq = post("/product/listProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            addProduct();

            ResultActions performq = mvc.perform(requestq);
            String str = performq.andReturn().getResponse().getContentAsString();
            List<String> list = forJson(str);
            for (int i = 0; i < list.size(); i++) {
                RequestBuilder request = put("/product/updateAverageDailySales/" + list.get(i)).param("averageDailySales", "1");
                ResultActions perform = mvc.perform(request);
                log.info(perform.andReturn().getResponse().getContentAsString());
                MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
                        .andDo(MockMvcResultHandlers.print()).andReturn();
                Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /**
     * 重建商品索引
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void rebuildProductIndex() {
        RequestBuilder request = get("/product/rebuildProductIndex");
        try {
            addProduct();
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /**
     * 删除无效的商品索引
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delInvalidProductIndex() {
        RequestBuilder request = get("/product/delInvalidProductIndex");
        try {
            addProduct();
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /**
     * 查询所有上架的商品
     */
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listAllProduct() {
        ListProductParam param = new ListProductParam();
        param.setCurrentPage(1);
        param.setName("小炒肉");
        param.setPageSize(10);
        param.setSortName("id");
        param.setSortOrder("desc");
        param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
        RequestBuilder request = post("/product/listAllProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            addProduct();
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 根据ids查询商品信息
     */
/*	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listProductByIds() {
		List<Long> list =Lists.newArrayList();
		list.add(1L);
		list.add(2L);
		RequestBuilder request = get("/product/listProductByIds").contentType(MediaType.ALL).content(JSON.toJSONString(list));
		RequestBuilder request = get("/product/listProductByIds").param("ids", "[1L,2L]");
		try{
			addProduct();
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateKeywordsById() {
		RequestBuilder request = put("/product/updateKeywordsById/10").param("merchantId", "200").param("keywords", "test");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}*/
    private void addProduct() throws Exception {
        EditProductDataParam product1 = initProduct("1");
        EditProductDataParam product2 = initProduct("2");
        RequestBuilder requestAdd1 = post("/product/saveProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(product1));
        RequestBuilder requestAdd2 = post("/product/saveProduct").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(product2));
        ResultActions performAdd1 = mvc.perform(requestAdd1);
        ResultActions performAdd2 = mvc.perform(requestAdd2);
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
    public void soldOutProductByMerchantId() {
        RequestBuilder request = put("/product/soldOutProductByMerchantId").param("id", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delAllProductIndex() {
        RequestBuilder request = get("/product/delAllProductIndex");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void countProduct() {
        RequestBuilder request = get("/product/countProduct/100");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listProduct1() {
        ProductListQuery query = new ProductListQuery();
        query.setQueryTypeEnum(ProductQueryTypeEnum.WAREHOUSE);
        query.setSortEnum(ProductListSortEnum.PRODUCT_STATUS_UP_ASC);
        RequestBuilder request = post("/product/listProduct/100").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(query));
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateProductPosition() {
        RequestBuilder request = put("/product/updateProductPosition/100").param("ids", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductDetail() {
        RequestBuilder request = get("/product/getProductDetail/100");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void editProductUpgrade()  throws Exception {
        EditProductUpgradeDataParam product = initProductUpgrade();
        RequestBuilder request = post("/product/editProductUpgrade").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(product));
        ResultActions perform = mvc.perform(request);

        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(ResultCode.SUCCESS))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
    }

    private EditProductUpgradeDataParam initProductUpgrade() {
        EditProductUpgradeDataParam product = new EditProductUpgradeDataParam();
        product.setMerchantId(1L);
        product.setMerchantNum("B000001");
        product.setProvinceName("广东省");
        product.setCityName("深圳市");
        product.setProductId(0L);
        product.setName("乐高积木");
        product.setCategoryId(1);
//        product.setCategoryName();
//        product.setCategorySubitemId(param.getCategorySubitemId());
//        product.setCategorySubitemName(param.getCategorySubitemName());
//        product.setBrandId(param.getBrandId());
//        product.setBrandName(param.getBrandName());
//        product.setProductModelParam(param.getProductModelParam());
//        product.setDelProductModelIds(param.getDelProductModelIds());
//        product.setProductSpecCustomParam(param.getProductSpecCustomParam());
//        product.setContent(param.getContent());
//        product.setTitleImages(param.getTitleImages());
//        product.setDetailImages(param.getDetailImages());
//        product.setDetailContent(param.getDetailContent());
//        product.setAllowRefund(param.getAllowRefund());
//        product.setFreightFee(param.getFreightFee());
//        product.setFreightParam(param.getFreightParam());
//        product.setProvinceId(param.getProvinceId());
//        product.setCityId(param.getCityId());
//        product.setProductStatus(param.getProductStatus());
        return product;
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listProductCity() {
        RequestBuilder request = get("/product/listProductCity");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listMerchantProductCategory() {
        RequestBuilder request = get("/product/listMerchantProductCategory/100");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
