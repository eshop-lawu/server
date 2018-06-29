package com.lawu.eshop.product.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.concurrentqueue.impl.BusinessInventorySynService;
import com.lawu.eshop.concurrentqueue.impl.BusinessKey;
import com.lawu.eshop.mq.dto.order.ProductModeUpdateInventoryDTO;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCancelOrderNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCreateOrderNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderTradingSuccessIncreaseSalesNotification;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderCreateOrderReply;
import com.lawu.eshop.mq.dto.product.CheckLessInventoryResultEnum;
import com.lawu.eshop.product.constant.ActivityProductStatusEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.constant.ProductModelInventoryTypeEnum;
import com.lawu.eshop.product.constant.ProductNumFlagEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.srv.bo.CommentProductInfoBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductModelInfoBO;
import com.lawu.eshop.product.srv.bo.ShoppingCartProductModelBO;
import com.lawu.eshop.product.srv.bo.productModelDataBO;
import com.lawu.eshop.product.srv.converter.ProductConverter;
import com.lawu.eshop.product.srv.converter.ShoppingCartProductModelConverter;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductDOExample;
import com.lawu.eshop.product.srv.domain.ProductModelDO;
import com.lawu.eshop.product.srv.domain.ProductModelDOExample;
import com.lawu.eshop.product.srv.domain.ProductModelInventoryDO;
import com.lawu.eshop.product.srv.domain.ProductModelInventoryDOExample;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductModelDO;
import com.lawu.eshop.product.srv.domain.extend.ProductModelNumsView;
import com.lawu.eshop.product.srv.domain.extend.ProductNumsView;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductModelDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductModelInventoryDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityProductDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityProductModelDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.ProductDOMapperExtend;
import com.lawu.eshop.product.srv.mapper.extend.ProductModelDOMapperExtend;
import com.lawu.eshop.product.srv.mapper.extend.SeckillActivityProductDOExtendMapper;
import com.lawu.eshop.product.srv.mapper.extend.SeckillActivityProductModelDOExtendMapper;
import com.lawu.eshop.product.srv.service.ProductModelService;
import com.lawu.eshop.product.srv.solr.service.ProductSolrService;

@Service
public class ProductModelServiceImpl implements ProductModelService {
    
	@Autowired
	private ProductDOMapper productDOMapper;
	
	@Autowired
	private ProductModelDOMapper productModelDOMapper;
	
	@Autowired
	private ProductDOMapperExtend productDOMapperExtend;
	
	@Autowired
	private ProductModelDOMapperExtend productModelDOMapperExtend;
	
	@Autowired
	private ProductModelInventoryDOMapper productModelInventoryDOMapper;
	
    @Autowired
    private SeckillActivityProductModelDOMapper seckillActivityProductModelDOMapper;
    
    @Autowired
    private SeckillActivityProductDOMapper seckillActivityProductDOMapper;
    
    @Autowired
    private SeckillActivityDOMapper seckillActivityDOMapper;
    
    @Autowired
    private SeckillActivityProductDOExtendMapper seckillActivityProductDOExtendMapper;
    
    @Autowired
    private SeckillActivityProductModelDOExtendMapper seckillActivityProductModelDOExtendMapper;

    @Autowired
    private ProductSolrService productSolrService;
	
	@Autowired
	private BusinessInventorySynService businessInventorySynService;

	@Override
	public ShoppingCartProductModelBO getShoppingCartProductModel(Long id) throws DataNotExistException, WrongOperationException {
        ProductModelDO productModelDO = productModelDOMapper.selectByPrimaryKey(id);
        if (productModelDO == null || !productModelDO.getStatus()) {
            throw new DataNotExistException("商品型号数据不存在");
        }
        ProductDO productDO = productDOMapper.selectByPrimaryKey(productModelDO.getProductId());
        if (productDO == null || ProductStatusEnum.PRODUCT_STATUS_DEL.getVal().equals(productDO.getStatus())) {
            throw new DataNotExistException("商品数据不存在");
        }
        if (ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal().equals(productDO.getStatus())) {
            throw new WrongOperationException("商品已下架");
        }
		return ShoppingCartProductModelConverter.convert(productModelDO, productDO);
	}
	
    @Override
    public List<ShoppingCartProductModelBO> getShoppingCartProductModel(List<Long> ids) {
        ProductModelDOExample productModelDOExample = new ProductModelDOExample();
        productModelDOExample.createCriteria().andIdIn(ids);
        List<ProductModelDO> productModelDOS =  productModelDOMapper.selectByExample(productModelDOExample);
        // 剔除重复
        Set<Long> productIds = new HashSet<Long>();
        for (ProductModelDO productModelDO : productModelDOS) {
            productIds.add(productModelDO.getProductId());
        }
        ProductDOExample productDOExample = new ProductDOExample();
        productDOExample.createCriteria().andIdIn(new ArrayList<>(productIds));
        List<ProductDO> productDOs = productDOMapper.selectByExample(productDOExample);
        return ShoppingCartProductModelConverter.convert(productModelDOS, productDOs);
    }
	
	/**
	 * 创建购物订单
	 * 订单模块发送消息更新商品库存，并且保存商品库存流水记录
	 * 
	 * @param shoppingOrderCreateOrderNotification 接收的数据
	 * @author Sunny
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void lessInventory(ShoppingOrderCreateOrderNotification shoppingOrderCreateOrderNotification) {
		for (ProductModeUpdateInventoryDTO param : shoppingOrderCreateOrderNotification.getParams()) {
		    // 根据ActivityProductModelId是否为空判断库存记录的类型
		    ProductModelInventoryTypeEnum type = ProductModelInventoryTypeEnum.CREATE_ORDER;
		    if (param.getActivityProductModelId() != null) {
		        type = ProductModelInventoryTypeEnum.CREATE_SECKILL_ACTIVITY_ORDER;
		    }
            /*
             * 可能重复收到MQ消息
             * 需要实现幂等性
             */
            ProductModelInventoryDOExample productModelInventoryDOExample = new ProductModelInventoryDOExample();
            ProductModelInventoryDOExample.Criteria criteria = productModelInventoryDOExample.createCriteria();
            criteria.andShoppingOrderIdEqualTo(shoppingOrderCreateOrderNotification.getShoppingOrderId());
            criteria.andTypeEqualTo(type.getValue());
            criteria.andProductModelIdEqualTo(param.getProdecutModelId());
            int count = productModelInventoryDOMapper.countByExample(productModelInventoryDOExample);
            // 如果记录已经存在。继续循环
            if (count > 0) {
                continue;
            }
		    if (param.getActivityProductModelId() == null) {
    			// 获取商品型号之前的库存数据
    			ProductModelDO productModelDO = productModelDOMapper.selectByPrimaryKey(param.getProdecutModelId());
    			// 获取商品库存信息
    			ProductDO productDO = productDOMapper.selectByPrimaryKey(productModelDO.getProductId());
    			/*
    			 *  判断商品是否有效
    			 *  商品状态是否是上架状态
    			 *  商品型号状态是否正常
    			 */
    			if (!ProductStatusEnum.PRODUCT_STATUS_UP.getVal().equals(productDO.getStatus()) || !productModelDO.getStatus()) {
    				return;
    			}
    			// 判断库存
    			if (productModelDO.getInventory() < param.getQuantity()) {
    				return;
    			}
    			// 判断库存
    			if (productDO.getTotalInventory() < param.getQuantity()) {
    				return;
    			}
    			// 减商品型号库存
    			ProductModelNumsView productModelNumsView = new ProductModelNumsView();
    			productModelNumsView.setFlag(ProductNumFlagEnum.MINUS.getValue());
    			productModelNumsView.setGmtModified(new Date());
    			productModelNumsView.setNum(param.getQuantity());
    			productModelNumsView.setProductModelId(param.getProdecutModelId());
    			productModelDOMapperExtend.editInventory(productModelNumsView);
    			// 减商品库存
    			ProductNumsView productNumsView = new ProductNumsView();
    			productNumsView.setFlag(ProductNumFlagEnum.MINUS.getValue());
    			productNumsView.setGmtModified(new Date());
    			productNumsView.setNum(param.getQuantity());
    			productNumsView.setProductId(productModelDO.getProductId());
    			productDOMapperExtend.editTotalInventory(productNumsView);
		    } else {
		        // 获取商品型号之前的库存数据
		        SeckillActivityProductModelDO seckillActivityProductModelDO = seckillActivityProductModelDOMapper.selectByPrimaryKey(param.getActivityProductModelId());
                // 获取商品库存信息
		        SeckillActivityProductDO seckillActivityProductDO = seckillActivityProductDOMapper.selectByPrimaryKey(seckillActivityProductModelDO.getActivityProductId());
                // 判断库存
                if (seckillActivityProductModelDO.getLeftCount() < param.getQuantity()) {
                    return;
                }
                // 判断库存
                if (seckillActivityProductDO.getLeftCount() < param.getQuantity()) {
                    return;
                }
                // 减商品型号库存
                ProductModelNumsView productModelNumsView = new ProductModelNumsView();
                productModelNumsView.setFlag(ProductNumFlagEnum.MINUS.getValue());
                productModelNumsView.setGmtModified(new Date());
                productModelNumsView.setNum(param.getQuantity());
                productModelNumsView.setProductModelId(seckillActivityProductModelDO.getId());
                seckillActivityProductModelDOExtendMapper.editInventory(productModelNumsView);
                // 减商品库存
                ProductNumsView productNumsView = new ProductNumsView();
                productNumsView.setFlag(ProductNumFlagEnum.MINUS.getValue());
                productNumsView.setGmtModified(new Date());
                productNumsView.setNum(param.getQuantity());
                productNumsView.setProductId(seckillActivityProductDO.getId());
                seckillActivityProductDOExtendMapper.editTotalInventory(productNumsView);
		    }
            // 保存库存流程记录
            ProductModelInventoryDO productModelInventoryDO = new ProductModelInventoryDO();
            productModelInventoryDO.setProductModelId(param.getProdecutModelId());
            productModelInventoryDO.setQuantity(param.getQuantity());
            productModelInventoryDO.setShoppingOrderId(shoppingOrderCreateOrderNotification.getShoppingOrderId());
            productModelInventoryDO.setType(type.getValue());
            productModelInventoryDO.setGmtCreate(new Date());
            productModelInventoryDO.setGmtModified(new Date());
            productModelInventoryDOMapper.insertSelective(productModelInventoryDO);
		}
	}
	
	/**
	 * 取消购物订单
	 * 订单模块发送消息释放商品库存，并且保存商品库存流水记录
	 * 
	 * @param shoppingOrderCancelOrderNotification 发送的数据
	 * @author Sunny
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void releaseInventory(ShoppingOrderCancelOrderNotification shoppingOrderCancelOrderNotification) {
		for (ProductModeUpdateInventoryDTO param : shoppingOrderCancelOrderNotification.getParams()) {
		    // 根据ActivityProductModelId是否为空判断库存记录的类型
            ProductModelInventoryTypeEnum type = ProductModelInventoryTypeEnum.CREATE_ORDER;
            if (param.getActivityProductModelId() != null) {
                type = ProductModelInventoryTypeEnum.CREATE_SECKILL_ACTIVITY_ORDER;
            }
            
            if (param.getActivityProductModelId() == null) {
    			// 获取商品型号数据
    			ProductModelDO productModelDO = productModelDOMapper.selectByPrimaryKey(param.getProdecutModelId());
    			
    			// 释放商品型号库存
    			ProductModelNumsView productModelNumsView = new ProductModelNumsView();
    			productModelNumsView.setFlag(ProductNumFlagEnum.ADD.getValue());
    			productModelNumsView.setGmtModified(new Date());
    			productModelNumsView.setNum(param.getQuantity());
    			productModelNumsView.setProductModelId(param.getProdecutModelId());
    			productModelDOMapperExtend.editInventory(productModelNumsView);
    			
    			/*
    			 * 释放商品总库存
    			 */
    			ProductNumsView productNumsView = new ProductNumsView();
    			productNumsView.setFlag(ProductNumFlagEnum.ADD.getValue());
    			productNumsView.setGmtModified(new Date());
    			productNumsView.setNum(param.getQuantity());
    			productNumsView.setProductId(productModelDO.getProductId());
    			productDOMapperExtend.editTotalInventory(productNumsView);
            } else {
                // 获取商品型号数据
                SeckillActivityProductModelDO seckillActivityProductModelDO = seckillActivityProductModelDOMapper.selectByPrimaryKey(param.getActivityProductModelId());
                
                // 释放商品型号库存
                ProductModelNumsView productModelNumsView = new ProductModelNumsView();
                productModelNumsView.setFlag(ProductNumFlagEnum.ADD.getValue());
                productModelNumsView.setGmtModified(new Date());
                productModelNumsView.setNum(param.getQuantity());
                productModelNumsView.setProductModelId(param.getActivityProductModelId());
                seckillActivityProductModelDOExtendMapper.editInventory(productModelNumsView);
                
                /*
                 * 释放商品总库存
                 */
                ProductNumsView productNumsView = new ProductNumsView();
                productNumsView.setFlag(ProductNumFlagEnum.ADD.getValue());
                productNumsView.setGmtModified(new Date());
                productNumsView.setNum(param.getQuantity());
                productNumsView.setProductId(seckillActivityProductModelDO.getActivityProductId());
                seckillActivityProductDOExtendMapper.editTotalInventory(productNumsView);

                // 从缓存中清除库存key
                businessInventorySynService.clearInventory(BusinessKey.SECKILL_ACTIVITY_PRODUCT, param.getActivityProductModelId());
            }
	        // 保存库存流程记录
            ProductModelInventoryDO productModelInventoryDO = new ProductModelInventoryDO();
            productModelInventoryDO.setProductModelId(param.getProdecutModelId());
            productModelInventoryDO.setQuantity(param.getQuantity());
            productModelInventoryDO.setShoppingOrderId(shoppingOrderCancelOrderNotification.getShoppingOrderId());
            // 类型为创建订单
            productModelInventoryDO.setType(type.getValue());
            productModelInventoryDO.setGmtCreate(new Date());
            productModelInventoryDO.setGmtModified(new Date());
            productModelInventoryDOMapper.insertSelective(productModelInventoryDO);

		}
	}

	/**
	 * 
	 * @author Yangqh
	 */
	@Override
	public CommentProductInfoBO selectCommentProductInfo(Long productModelId) {
		ProductModelDO model = productModelDOMapper.selectByPrimaryKey(Long.valueOf(productModelId));
		Long productId = model.getProductId();
		if(productId == null){
			return null;
		}
		ProductDO product = productDOMapper.selectByPrimaryKey(productId);
		CommentProductInfoBO bo = new CommentProductInfoBO();
		bo.setName(product.getName());
		bo.setPrice(model.getPrice().toString());
		bo.setModelName(model.getName());
		bo.setFeatureImage(product.getFeatureImage());
		return bo;
	}
	
	/**
	 * 确认收货，增加销量
	 * 
	 * @param notification 接收的数据
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void increaseSales(ShoppingOrderTradingSuccessIncreaseSalesNotification notification) {
		for (ProductModeUpdateInventoryDTO param : notification.getParams()) {
			
			/*
			 * 增加商品型号销量
			 */
			// 获取商品型号数据
			ProductModelDO productModelDO = productModelDOMapper.selectByPrimaryKey(param.getProdecutModelId());
			
			ProductModelNumsView productModelNumsView = new ProductModelNumsView();
			productModelNumsView.setFlag(ProductNumFlagEnum.ADD.getValue());
			productModelNumsView.setGmtModified(new Date());
			productModelNumsView.setNum(param.getQuantity());
			productModelNumsView.setProductModelId(param.getProdecutModelId());
			productModelDOMapperExtend.editSaleVolume(productModelNumsView);
			
			/*
			 * 增加商品的总销量
			 */
			ProductNumsView productNumsView = new ProductNumsView();
			productNumsView.setFlag(ProductNumFlagEnum.ADD.getValue());
			productNumsView.setGmtModified(new Date());
			productNumsView.setNum(param.getQuantity());
			productNumsView.setProductId(productModelDO.getProductId());
			productDOMapperExtend.editTotalSaleVolume(productNumsView);
			
			// 获取增加之后的销量，放入solr
			ProductDO productDO = productDOMapper.selectByPrimaryKey(productModelDO.getProductId());
			productSolrService.save(ProductConverter.convertProductSolr(productDO));
		}
	}
	
	/**
	 * 检查库存是否扣除成功
	 * 
	 * @param shoppingOrderCreateOrderNotification 接收到的数据
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ShoppingOrderCreateOrderReply checkLessInventory(ShoppingOrderCreateOrderNotification shoppingOrderCreateOrderNotification) {
		ShoppingOrderCreateOrderReply rtn = new ShoppingOrderCreateOrderReply();
		for (ProductModeUpdateInventoryDTO param : shoppingOrderCreateOrderNotification.getParams()) {
		    // 根据ActivityProductModelId是否为空判断库存记录的类型
            ProductModelInventoryTypeEnum type = ProductModelInventoryTypeEnum.CREATE_ORDER;
            if (param.getActivityProductModelId() != null) {
                type = ProductModelInventoryTypeEnum.CREATE_SECKILL_ACTIVITY_ORDER;
            }
			/*
	    	 * 可能重复收到MQ消息
	    	 * 需要实现幂等性
	    	 */
			ProductModelInventoryDOExample productModelInventoryDOExample = new ProductModelInventoryDOExample();
			ProductModelInventoryDOExample.Criteria criteria = productModelInventoryDOExample.createCriteria();
			criteria.andShoppingOrderIdEqualTo(shoppingOrderCreateOrderNotification.getShoppingOrderId());
			criteria.andTypeEqualTo(type.getValue());
			criteria.andProductModelIdEqualTo(param.getProdecutModelId());
			int count = productModelInventoryDOMapper.countByExample(productModelInventoryDOExample);
			// 如果记录已经存在。继续循环
			if (count > 0) {
				continue;
			}
			// 获取商品型号之前的库存数据
			ProductModelDO productModelDO = productModelDOMapper.selectByPrimaryKey(param.getProdecutModelId());
			// 获取商品库存信息
			ProductDO productDO = productDOMapper.selectByPrimaryKey(productModelDO.getProductId());
			/*
			 *  判断商品是否有效
			 *  商品状态是否是上架状态
			 *  商品型号状态是否正常
			 */
			if (param.getActivityProductModelId() != null && (!ProductStatusEnum.PRODUCT_STATUS_UP.getVal().equals(productDO.getStatus()) || !productModelDO.getStatus())) {
				rtn.setResult(CheckLessInventoryResultEnum.PRODUCT_HAS_EXPIRED);
			}
			// 判断库存
			if (productModelDO.getInventory() < param.getQuantity()) {
				rtn.setResult(CheckLessInventoryResultEnum.INVENTORY_SHORTAGE);
			}
			// 判断库存
			if (productDO.getTotalInventory() < param.getQuantity()) {
				rtn.setResult(CheckLessInventoryResultEnum.INVENTORY_SHORTAGE);
			}
		}
		return rtn;
	}

	@Override
	public List<productModelDataBO> queryProductModel(Long productId) {
		
		ProductModelDOExample pmExample = new ProductModelDOExample();
		pmExample.createCriteria().andProductIdEqualTo(productId).andStatusEqualTo(true);
		List<ProductModelDO> list = productModelDOMapper.selectByExample(pmExample);
		
		ProductDO  product = productDOMapper.selectByPrimaryKey(productId);
		
		List<productModelDataBO> modelList = new ArrayList<>();
		for (ProductModelDO productModelDO : list) {
			productModelDataBO modelBO = new productModelDataBO();
			modelBO.setId(productModelDO.getId());
			modelBO.setName(productModelDO.getName());
			modelBO.setUrl(product.getFeatureImage());
			modelList.add(modelBO);
		}
		
		return modelList;
	}
	
    @Override
    public SeckillActivityProductModelInfoBO seckillActivityProductModel(Long activityProductModelId) throws DataNotExistException {
        SeckillActivityProductModelDO seckillActivityProductModelDO = seckillActivityProductModelDOMapper.selectByPrimaryKey(activityProductModelId);
        if (seckillActivityProductModelDO == null) {
            throw new DataNotExistException("抢购活动商品型号数据不存在");
        }
        SeckillActivityProductDO seckillActivityProductDO = seckillActivityProductDOMapper.selectByPrimaryKey(seckillActivityProductModelDO.getActivityProductId());
        // 如果活动商品未审核也提示数据不存在
        if (seckillActivityProductDO == null) {
            throw new DataNotExistException("抢购活动商品数据不存在");
        }
        if (!ActivityProductStatusEnum.AUDITED.getValue().equals(seckillActivityProductDO.getStatus())) {
            throw new WrongOperationException("抢购活动商品未审核");
        }
        SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(seckillActivityProductDO.getActivityId());
        // 如果活动不是进行中也提示数据不存在
        if (seckillActivityDO == null) {
            throw new DataNotExistException("抢购活动数据不存在");
        }
        if (!ActivityStatusEnum.PROCESSING.getValue().equals(seckillActivityDO.getActivityStatus())) {
            throw new WrongOperationException("抢购活动不在进行中");
        }
        ProductModelDO productModelDO = productModelDOMapper.selectByPrimaryKey(seckillActivityProductModelDO.getProductModelId());
        if (productModelDO == null || !productModelDO.getStatus()) {
            throw new DataNotExistException("商品型号数据不存在");
        }
        ProductDO productDO = productDOMapper.selectByPrimaryKey(productModelDO.getProductId());
        if (productDO == null || ProductStatusEnum.PRODUCT_STATUS_DEL.getVal().equals(productDO.getStatus())) {
            throw new DataNotExistException("商品数据不存在");
        }
        if (ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal().equals(productDO.getStatus())) {
            throw new WrongOperationException("商品已下架");
        }
        return ShoppingCartProductModelConverter.convert(productModelDO, productDO, seckillActivityDO, seckillActivityProductDO, seckillActivityProductModelDO);
    }
}
