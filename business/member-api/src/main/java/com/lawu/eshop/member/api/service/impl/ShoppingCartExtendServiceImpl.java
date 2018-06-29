package com.lawu.eshop.member.api.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.concurrentqueue.bizctrl.BusinessExecuteException;
import com.lawu.concurrentqueue.bizctrl.annotation.BusinessInventoryCtrl;
import com.lawu.eshop.concurrentqueue.impl.BusinessKey;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.member.api.service.AddressService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.ProductModelService;
import com.lawu.eshop.member.api.service.PropertyInfoService;
import com.lawu.eshop.member.api.service.ShoppingCartExtendService;
import com.lawu.eshop.member.api.service.ShoppingCartService;
import com.lawu.eshop.member.api.service.ShoppingOrderService;
import com.lawu.eshop.order.dto.CalculateFreightDTO;
import com.lawu.eshop.order.dto.ShoppingCartDTO;
import com.lawu.eshop.order.dto.foreign.MemberShoppingCartDTO;
import com.lawu.eshop.order.dto.foreign.MemberShoppingCartGroupDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingCartQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingCartSettlementDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingCartSettlementItemDTO;
import com.lawu.eshop.order.param.ShoppingCartParam;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementItemParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.foreign.BatchCreateOrderParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderBuyNowCreateOrderForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderSettlementForeignParam;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.SeckillActivityProductModelInfoDTO;
import com.lawu.eshop.product.dto.ShoppingCartProductModelDTO;
import com.lawu.eshop.property.dto.OrderAssetInformationDTO;
import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.eshop.user.dto.MerchantInfoForShoppingCartDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindMerchantInfoDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindUserInfoDTO;
import com.lawu.eshop.user.param.ShoppingOrderFindUserInfoParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 购物车扩展服务实现类
 * 
 * @author Sunny
 * @date 2017/4/6
 */
@Service
public class ShoppingCartExtendServiceImpl extends BaseController implements ShoppingCartExtendService {
	
    @Autowired
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Autowired
    private ProductModelService productModelService;
    
    @Autowired
    private MerchantStoreService merchantStoreService;
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private PropertyInfoService propertyInfoService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	public Result save(Long memberId, ShoppingCartParam param) {
    	Result<ShoppingCartProductModelDTO> resultShoppingCartProductModelDTO = productModelService.getShoppingCartProductModel(param.getProductModelId());
    	
    	if (!isSuccess(resultShoppingCartProductModelDTO)) {
    		return successCreated(resultShoppingCartProductModelDTO);
    	}
    	
    	ShoppingCartProductModelDTO shoppingCartProductModelDTO = resultShoppingCartProductModelDTO.getModel();
    	
    	Result<MerchantInfoForShoppingCartDTO> getMerchantInfoForShoppingCartResult = merchantStoreService.getMerchantInfoForShoppingCart(shoppingCartProductModelDTO.getMerchantId());
    	
    	if (!isSuccess(getMerchantInfoForShoppingCartResult)) {
    		return successCreated(getMerchantInfoForShoppingCartResult);
    	}
    	
    	ShoppingCartSaveParam shoppingCartSaveParam = new ShoppingCartSaveParam();
    	shoppingCartSaveParam.setMerchantName(getMerchantInfoForShoppingCartResult.getModel().getMerchantStoreName());
    	shoppingCartSaveParam.setMerchantStoreId(getMerchantInfoForShoppingCartResult.getModel().getMerchantStoreId());
    	shoppingCartSaveParam.setMerchantId(shoppingCartProductModelDTO.getMerchantId());
    	shoppingCartSaveParam.setProductId(shoppingCartProductModelDTO.getProductId());
    	shoppingCartSaveParam.setProductModelId(shoppingCartProductModelDTO.getId());
    	shoppingCartSaveParam.setQuantity(param.getQuantity());
    	shoppingCartSaveParam.setSalesPrice(shoppingCartProductModelDTO.getPrice());
    	
    	Result result = shoppingCartService.save(memberId, shoppingCartSaveParam);
    	if (!isSuccess(result)) {
    		return successCreated(result);
    	}
    			
    	return successCreated(result);
    }
    
	/**
	 * 根据memberId查询用户的购物车列表。
	 * 
	 * @param memberId 会员id
	 * @return
	 */
    @Override
    public Result<ShoppingCartQueryDTO> findListByMemberId(Long memberId){
    	ShoppingCartQueryDTO rtn = new ShoppingCartQueryDTO();
    	
    	// 通过memberId查询用户购物车资料
    	Result<List<ShoppingCartDTO>> resultShoppingCartDTOS = shoppingCartService.findListByMemberId(memberId);
    	if (!isSuccess(resultShoppingCartDTOS)) {
    		return successGet(resultShoppingCartDTOS.getRet());
    	}
    	
    	// 如果购物车列表为空直接返回
    	if (resultShoppingCartDTOS.getModel() == null || resultShoppingCartDTOS.getModel().isEmpty()) {
    		return successGet(rtn);
    	}
    	
    	// 把要查询的id放入set,统一一次性查询
    	Set<Long> ids = new HashSet<Long>();
    	for (ShoppingCartDTO shoppingCartDTO : resultShoppingCartDTOS.getModel()) {
    		ids.add(shoppingCartDTO.getProductModelId());
    	}
    	
    	// 通过商品型号id列表查询商品信息
    	Result<List<ShoppingCartProductModelDTO>> resultShoppingCartProductModelDTOS = productModelService.getShoppingCartProductModel(new ArrayList<>(ids));
    	if (!isSuccess(resultShoppingCartProductModelDTOS)) {
    		return successGet(resultShoppingCartProductModelDTOS.getRet());
    	}
    	
    	// 组装数据
    	Map<Long, ShoppingCartProductModelDTO> shoppingCartProductModelDTOMap = new HashMap<>();
    	for (ShoppingCartProductModelDTO shoppingCartProductModelDTO : resultShoppingCartProductModelDTOS.getModel()) {
    		if (!shoppingCartProductModelDTOMap.containsKey(shoppingCartProductModelDTO.getId())) {
    			shoppingCartProductModelDTOMap.put(shoppingCartProductModelDTO.getId(), shoppingCartProductModelDTO);
    		}
    	}
    	
    	List<MemberShoppingCartDTO> shoppingCartInvalidList = new ArrayList<>();
    	Map<Long, List<MemberShoppingCartDTO>> map = new LinkedHashMap<>();
    	ShoppingCartProductModelDTO shoppingCartProductModelDTO = null;
    	for (ShoppingCartDTO shoppingCartDTO : resultShoppingCartDTOS.getModel()) {
    		MemberShoppingCartDTO memberShoppingCartDTO = new MemberShoppingCartDTO();
    		memberShoppingCartDTO.setId(shoppingCartDTO.getId());
    		memberShoppingCartDTO.setMerchantId(shoppingCartDTO.getMerchantId());
    		memberShoppingCartDTO.setMerchantStoreId(shoppingCartDTO.getMerchantStoreId());
    		memberShoppingCartDTO.setMerchantName(shoppingCartDTO.getMerchantName());
    		memberShoppingCartDTO.setProductId(shoppingCartDTO.getProductId());
    		memberShoppingCartDTO.setProductModelId(shoppingCartDTO.getProductModelId());
    		memberShoppingCartDTO.setQuantity(shoppingCartDTO.getQuantity());
    		memberShoppingCartDTO.setSalesPrice(shoppingCartDTO.getSalesPrice());
    		
    		shoppingCartProductModelDTO = shoppingCartProductModelDTOMap.get(shoppingCartDTO.getProductModelId());
    		if (shoppingCartProductModelDTO == null) {
    			continue;
    		}
    		memberShoppingCartDTO.setProductModelName(shoppingCartProductModelDTO.getName());
    		memberShoppingCartDTO.setProductName(shoppingCartProductModelDTO.getProductName());
    		memberShoppingCartDTO.setFeatureImage(shoppingCartProductModelDTO.getFeatureImage());
    		memberShoppingCartDTO.setSalesPrice(shoppingCartProductModelDTO.getPrice());
    		// 计算差价(商品表的现价减去购物车表价格,正为涨价,负为降价)
    		memberShoppingCartDTO.setDifference(shoppingCartProductModelDTO.getPrice().subtract(shoppingCartDTO.getSalesPrice()));
    		memberShoppingCartDTO.setInventory(shoppingCartProductModelDTO.getInventory());
    		memberShoppingCartDTO.setGmtDown(shoppingCartProductModelDTO.getGmtDown());
    		
    		if (ProductStatusEnum.PRODUCT_STATUS_UP != shoppingCartProductModelDTO.getStatus()) {
    			memberShoppingCartDTO.setIsInvalid(true);
    			shoppingCartInvalidList.add(memberShoppingCartDTO);
    		} else {
    			memberShoppingCartDTO.setIsInvalid(false);
    			if (!map.containsKey(shoppingCartDTO.getMerchantId())) {
        			map.put(shoppingCartDTO.getMerchantId(), new ArrayList<>());
        		}
    			map.get(shoppingCartDTO.getMerchantId()).add(memberShoppingCartDTO);
    		}
    	}
    	
    	List<MemberShoppingCartGroupDTO> memberShoppingCartGroupDTOList = new ArrayList<>();
    	for (Map.Entry<Long, List<MemberShoppingCartDTO>> item : map.entrySet()) {
    		MemberShoppingCartGroupDTO memberShoppingCartGroupDTO = new MemberShoppingCartGroupDTO();
    		memberShoppingCartGroupDTO.setItem(item.getValue());
    		memberShoppingCartGroupDTOList.add(memberShoppingCartGroupDTO);
    	}
    	rtn.setShoppingCartGroupList(memberShoppingCartGroupDTOList);
    	
        shoppingCartInvalidList.sort((o1, o2) -> {
            Date gmtDown1 = o1.getGmtDown();
            Date gmtDown2 = o2.getGmtDown();
            if (gmtDown1 == null && gmtDown2 == null) {
                return 0;
            }
            if (gmtDown1 == null) {
                return 1;
            }
            if (gmtDown2 == null) {
                return -1;
            }
            return o2.getGmtDown().compareTo(o1.getGmtDown());
        });

        rtn.setShoppingCartInvalidList(shoppingCartInvalidList);
        return successGet(rtn);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Result<List<Long>> createOrder(Long memberId, BatchCreateOrderParam param, String memberNum) {
        List<ShoppingOrderSettlementForeignParam> settlementParams = param.getSettlementParams();
        
        //查找用户余额,积分和积分兑换比例
        Result<OrderAssetInformationDTO> orderAssetInformationResult = propertyInfoService
                .orderAssetInformation(memberNum);
        if (!isSuccess(orderAssetInformationResult)) {
            return successGet(orderAssetInformationResult.getRet());
        }
        OrderAssetInformationDTO orderAssetInformationDTO = orderAssetInformationResult.getModel();
        // 校验用户的积分
        if (param.getIntegral() != null && param.getIntegral().compareTo(orderAssetInformationDTO.getPoint()) > 0) {
            return successCreated(ResultCode.WRONG_OPERATION, "当前积分不足");
        }
        
        // 组装ids查询购物车列表
        Map<Long, ShoppingOrderSettlementForeignParam> shoppingOrderSettlementForeignParamMap = new HashMap<>();
        List<Long> ids = new ArrayList<>();
        for (ShoppingOrderSettlementForeignParam shoppingOrderSettlementForeignParam : settlementParams) {
            ids.addAll(shoppingOrderSettlementForeignParam.getIds());
            shoppingOrderSettlementForeignParamMap.put(shoppingOrderSettlementForeignParam.getMerchantId(),
                    shoppingOrderSettlementForeignParam);
        }
        Result<List<ShoppingCartDTO>> resultShoppingCartDTOS = shoppingCartService.findListByIds(memberId, ids);
        if (!isSuccess(resultShoppingCartDTOS)) {
            return successCreated(resultShoppingCartDTOS.getRet());
        }
        // 购物车分单,同一个商家的商品分在同一个订单当中
        Map<Long, List<ShoppingCartDTO>> shoppingCartDTOMap = new HashMap<>();
        for (ShoppingCartDTO shoppingCartDTO : resultShoppingCartDTOS.getModel()) {
            if (!shoppingCartDTOMap.containsKey(shoppingCartDTO.getMerchantId())) {
                shoppingCartDTOMap.put(shoppingCartDTO.getMerchantId(), new ArrayList<>());
            }
            shoppingCartDTOMap.get(shoppingCartDTO.getMerchantId()).add(shoppingCartDTO);
        }
        // 把要查询的id放入set,统一一次性查询
        Set<Long> idSet = new HashSet<Long>();
        for (ShoppingCartDTO shoppingCartDTO : resultShoppingCartDTOS.getModel()) {
            idSet.add(shoppingCartDTO.getProductModelId());
        }
        // 通过商品型号id列表查询商品信息
        Result<List<ShoppingCartProductModelDTO>> resultShoppingCartProductModelDTOS = productModelService
                .getShoppingCartProductModel(new ArrayList<>(idSet));
        if (!isSuccess(resultShoppingCartProductModelDTOS)) {
            return successCreated(resultShoppingCartProductModelDTOS.getRet());
        }
        Map<Long, ShoppingCartProductModelDTO> shoppingCartProductModelDTOMap = new HashMap<>();
        for (ShoppingCartProductModelDTO shoppingCartProductModelDTO : resultShoppingCartProductModelDTOS.getModel()) {
            shoppingCartProductModelDTOMap.put(shoppingCartProductModelDTO.getId(), shoppingCartProductModelDTO);
        }
        // 查询地址
        Result<AddressDTO> resultAddressDTO = addressService.get(param.getAddressId(), memberNum);
        if (!isSuccess(resultAddressDTO)) {
            return successCreated(resultAddressDTO.getRet(), resultAddressDTO.getMsg());
        }
        // 查询商家是否支持七天退货
        List<Long> merchantIdList = new ArrayList<>(shoppingCartDTOMap.keySet());
        ShoppingOrderFindUserInfoParam shoppingOrderFindUserInfoParam = new ShoppingOrderFindUserInfoParam();
        shoppingOrderFindUserInfoParam.setMerchantIdList(merchantIdList);
        shoppingOrderFindUserInfoParam.setMemberId(memberId);
        Result<ShoppingOrderFindUserInfoDTO> shoppingOrderFindUserInfoDTOResult = merchantStoreService
                .shoppingOrderFindUserInfo(shoppingOrderFindUserInfoParam);
        if (!isSuccess(shoppingOrderFindUserInfoDTOResult)) {
            return successCreated(shoppingOrderFindUserInfoDTOResult.getRet());
        }
        // 把商家信息放入Map
        Map<Long, ShoppingOrderFindMerchantInfoDTO> shoppingOrderFindMerchantInfoDTOMap = new HashMap<>();
        for (ShoppingOrderFindMerchantInfoDTO shoppingOrderFindMerchantInfoDTO : shoppingOrderFindUserInfoDTOResult
                .getModel().getShoppingOrderFindMerchantInfoDTOList()) {
            shoppingOrderFindMerchantInfoDTOMap.put(shoppingOrderFindMerchantInfoDTO.getMerchantId(),
                    shoppingOrderFindMerchantInfoDTO);
        }
        BigDecimal totalPrice = new BigDecimal(0);
        // 组装订单
        List<ShoppingOrderSettlementParam> shoppingOrderSettlementParams = new ArrayList<>();
        for (Map.Entry<Long, List<ShoppingCartDTO>> entry : shoppingCartDTOMap.entrySet()) {
            Long key = entry.getKey();
            List<ShoppingCartDTO> values = entry.getValue();
            ShoppingOrderSettlementParam shoppingOrderSettlementParam = new ShoppingOrderSettlementParam();
            shoppingOrderSettlementParam.setMemberId(memberId);
            shoppingOrderSettlementParam.setMemberNum(shoppingOrderFindUserInfoDTOResult.getModel().getMemberNum());
            shoppingOrderSettlementParam
                    .setMemberNickname(shoppingOrderFindUserInfoDTOResult.getModel().getMemberNickname());
            shoppingOrderSettlementParam.setMerchantId(key);
            shoppingOrderSettlementParam
                    .setMerchantStoreId(shoppingOrderFindMerchantInfoDTOMap.get(key).getMerchantStoreId());
            shoppingOrderSettlementParam.setMerchantStoreRegionPath(
                    shoppingOrderFindMerchantInfoDTOMap.get(key).getMerchantStoreRegionPath());
            shoppingOrderSettlementParam.setMerchantNum(shoppingOrderFindMerchantInfoDTOMap.get(key).getMerchantNum());
            shoppingOrderSettlementParam.setMerchantName(values.get(0).getMerchantName());
            shoppingOrderSettlementParam.setMessage(shoppingOrderSettlementForeignParamMap.get(key).getMessage());
            
            // 设置初始运费
            shoppingOrderSettlementParam.setFreightPrice(new BigDecimal(0));
            
            // 设置收货人信息,对应每个订单
            shoppingOrderSettlementParam.setConsigneeAddress(
                    resultAddressDTO.getModel().getRegionName() + " " + resultAddressDTO.getModel().getAddr());
            shoppingOrderSettlementParam.setConsigneeName(resultAddressDTO.getModel().getName());
            shoppingOrderSettlementParam.setConsigneeMobile(resultAddressDTO.getModel().getMobile());
            // 是否支持七天退货
            shoppingOrderSettlementParam
                    .setIsNoReasonReturn(shoppingOrderFindMerchantInfoDTOMap.get(key).getIsNoReasonReturn());
            // 用户是否是商家粉丝
            shoppingOrderSettlementParam.setIsFans(shoppingOrderFindMerchantInfoDTOMap.get(key).getIsFans());
            shoppingOrderSettlementParam.setCommodityTotalPrice(new BigDecimal(0));
            List<ShoppingOrderSettlementItemParam> items = new ArrayList<>(values.size());
            List<CalculateFreightDTO> calculateFreights = new ArrayList<>(values.size());
            for (ShoppingCartDTO shoppingCartDTO : values) {
                ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam = new ShoppingOrderSettlementItemParam();
                ShoppingCartProductModelDTO shoppingCartProductModelDTO = shoppingCartProductModelDTOMap
                        .get(shoppingCartDTO.getProductModelId());
                // 加入购物车id,用于在保存订单之后删除购物车记录
                shoppingOrderSettlementItemParam.setIsAllowRefund(shoppingCartProductModelDTO.getIsAllowRefund());
                shoppingOrderSettlementItemParam.setShoppingCartId(shoppingCartDTO.getId());
                shoppingOrderSettlementItemParam.setProductId(shoppingCartProductModelDTO.getProductId());
                shoppingOrderSettlementItemParam.setProductName(shoppingCartProductModelDTO.getProductName());
                shoppingOrderSettlementItemParam.setProductFeatureImage(shoppingCartProductModelDTO.getFeatureImage());
                shoppingOrderSettlementItemParam.setProductModelId(shoppingCartProductModelDTO.getId());
                shoppingOrderSettlementItemParam.setProductModelName(shoppingCartProductModelDTO.getName());
                shoppingOrderSettlementItemParam.setQuantity(shoppingCartDTO.getQuantity());
                // 判断商品是否失效
                if (!ProductStatusEnum.PRODUCT_STATUS_UP.equals(shoppingCartProductModelDTO.getStatus())) {
                    return successCreated(ResultCode.PRODUCT_HAS_EXPIRED);
                }
                // 判断库存
                if (shoppingCartProductModelDTO.getInventory() < shoppingCartDTO.getQuantity()) {
                    return successCreated(ResultCode.INVENTORY_SHORTAGE);
                }
                shoppingOrderSettlementItemParam.setRegularPrice(shoppingCartProductModelDTO.getOriginalPrice());
                shoppingOrderSettlementItemParam.setSalesPrice(shoppingCartProductModelDTO.getPrice());
                shoppingOrderSettlementItemParam.setPoint(new BigDecimal(0));
                shoppingOrderSettlementItemParam.setDeductionPointsAmount(new BigDecimal(0));
                shoppingOrderSettlementItemParam.setFreight(shoppingCartProductModelDTO.getFreight());
                shoppingOrderSettlementItemParam.setSubtotal(shoppingCartProductModelDTO.getPrice().multiply(new BigDecimal(shoppingCartDTO.getQuantity())));
                shoppingOrderSettlementParam.setCommodityTotalPrice(shoppingOrderSettlementParam.getCommodityTotalPrice()
                        .add(shoppingOrderSettlementItemParam.getSubtotal()));
                
                CalculateFreightDTO calculateFreight = new CalculateFreightDTO();
                calculateFreight.setFreight(shoppingCartProductModelDTO.getFreight());
                calculateFreight.setQuantity(shoppingOrderSettlementItemParam.getQuantity());
                calculateFreights.add(calculateFreight);
                items.add(shoppingOrderSettlementItemParam);
            }
            shoppingOrderSettlementParam.setItems(items);
            // 计算运费
            shoppingOrderSettlementParam.setFreightPrice(CalculateFreightDTO.calculateFreight(calculateFreights));
            // 订单总价等于货物总价+运费
            BigDecimal orderTotalPrice = shoppingOrderSettlementParam.getCommodityTotalPrice().add(shoppingOrderSettlementParam.getFreightPrice());
            shoppingOrderSettlementParam.setOrderTotalPrice(orderTotalPrice);
            totalPrice = totalPrice.add(shoppingOrderSettlementParam.getOrderTotalPrice());
            shoppingOrderSettlementParam.setDeductionPoints(new BigDecimal(0));
            shoppingOrderSettlementParam.setDeductionPointsAmount(new BigDecimal(0));
            shoppingOrderSettlementParam.setExchangeRate(orderAssetInformationDTO.getExchangeRate());
            shoppingOrderSettlementParams.add(shoppingOrderSettlementParam);
        }
        
        /*
         * 抵扣积分分配,按照订单价格分配
         */
        if (param.getIntegral() != null && param.getIntegral().doubleValue() != 0D) {
            // 按照订单价格从低到高排列, 保证所有订单都能分配到积分
            shoppingOrderSettlementParams.sort((o1, o2) -> {
                return o1.getOrderTotalPrice().compareTo(o2.getOrderTotalPrice());
            });
            for (ShoppingOrderSettlementParam shoppingOrderSettlementParam : shoppingOrderSettlementParams) {
                shoppingOrderSettlementParam.getItems().sort((o1, o2) -> {
                    BigDecimal orderItemTotalPrice1 = o1.getSalesPrice().multiply(new BigDecimal(o1.getQuantity()));
                    BigDecimal orderItemTotalPrice2 = o2.getSalesPrice().multiply(new BigDecimal(o2.getQuantity()));
                    return orderItemTotalPrice1.compareTo(orderItemTotalPrice2);
                });
            }
            /*
             *  计算积分抵扣金额
             *  校验积分兑换金额是否大于总价 
             */
            BigDecimal pointsDeductionsAmount = param.getIntegral().divide(orderAssetInformationDTO.getExchangeRate()).setScale(2, RoundingMode.DOWN);
            // 判断当前用户的积分抵扣金额是否大于商品的总价
            if (pointsDeductionsAmount.compareTo(totalPrice) > 0) {
                pointsDeductionsAmount = totalPrice;
            }
            BigDecimal currentPointsDeductionsAmount = pointsDeductionsAmount;
            while (pointsDeductionsAmount.doubleValue() != 0D) {
                for(int i = 0; i < shoppingOrderSettlementParams.size(); i++) {
                    ShoppingOrderSettlementParam shoppingOrderSettlementParam = shoppingOrderSettlementParams.get(i);
                    if (shoppingOrderSettlementParam.getOrderTotalPrice().compareTo(shoppingOrderSettlementParam.getDeductionPointsAmount()) <= 0) {
                        continue;
                    }
                    // 计算当前订单抵扣金额占总金额比例
                    BigDecimal orderDeductionPointsAmount = shoppingOrderSettlementParam.getOrderTotalPrice().multiply(currentPointsDeductionsAmount).divide(totalPrice, 2, RoundingMode.DOWN);
                    /*
                     *  放入剩余的抵扣金额
                     *  1.如果当前是最后一个订单项
                     *  2.计算积分抵扣金额为0
                     */
                    BigDecimal residualDeductionAmount = shoppingOrderSettlementParam.getOrderTotalPrice().subtract(shoppingOrderSettlementParam.getDeductionPointsAmount());
                    if (i == shoppingOrderSettlementParams.size() - 1 || orderDeductionPointsAmount.doubleValue() == 0D) {
                        if (residualDeductionAmount.compareTo(pointsDeductionsAmount) >= 0) {
                            orderDeductionPointsAmount = pointsDeductionsAmount;
                        }
                    }
                    if (orderDeductionPointsAmount.compareTo(residualDeductionAmount) >= 0) {
                        orderDeductionPointsAmount = residualDeductionAmount;
                    }
                    shoppingOrderSettlementParam.setDeductionPointsAmount(shoppingOrderSettlementParam.getDeductionPointsAmount().add(orderDeductionPointsAmount));
                    shoppingOrderSettlementParam.setDeductionPoints(shoppingOrderSettlementParam.getDeductionPointsAmount().multiply(orderAssetInformationDTO.getExchangeRate()));
                    pointsDeductionsAmount = pointsDeductionsAmount.subtract(orderDeductionPointsAmount);
                    
                    /*
                     *  如果当前订单抵扣金额大于商品总价, 抵扣金额就为商品总价, 超过的部分抵扣运费
                     */
                    if (orderDeductionPointsAmount.compareTo(shoppingOrderSettlementParam.getCommodityTotalPrice()) >= 0) {
                        orderDeductionPointsAmount = shoppingOrderSettlementParam.getCommodityTotalPrice();
                    } else {
                        if (shoppingOrderSettlementParam.getDeductionPointsAmount().compareTo(shoppingOrderSettlementParam.getCommodityTotalPrice()) >= 0) {
                            orderDeductionPointsAmount = shoppingOrderSettlementParam.getDeductionPointsAmount().subtract(shoppingOrderSettlementParam.getCommodityTotalPrice());
                        }
                    }
                    
                    BigDecimal currentOrderDeductionPointsAmount = orderDeductionPointsAmount;
                    while (orderDeductionPointsAmount.doubleValue() != 0D) {
                        for(int j = 0; j < shoppingOrderSettlementParam.getItems().size(); j++) {
                            ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam = shoppingOrderSettlementParam.getItems().get(j);
                            BigDecimal orderItemTotalPrice = shoppingOrderSettlementItemParam.getSalesPrice().multiply(new BigDecimal(shoppingOrderSettlementItemParam.getQuantity()));
                            if (orderItemTotalPrice.compareTo(shoppingOrderSettlementItemParam.getDeductionPointsAmount()) <= 0) {
                                continue;
                            }
                            // 计算当前订单抵扣金额占总金额比例
                            BigDecimal orderItemDeductionPointsAmount = orderItemTotalPrice.multiply(currentOrderDeductionPointsAmount).divide(shoppingOrderSettlementParam.getCommodityTotalPrice(), 2, RoundingMode.DOWN);
                            /*
                             *  放入剩余的抵扣金额
                             *  1.如果当前是最后一个订单项
                             *  2.计算积分抵扣金额为0
                             */
                            BigDecimal itemResidualDeductionAmount = orderItemTotalPrice.subtract(shoppingOrderSettlementItemParam.getDeductionPointsAmount());
                            if (j == shoppingOrderSettlementParam.getItems().size() - 1 || orderItemDeductionPointsAmount.doubleValue() == 0D) {
                                if (itemResidualDeductionAmount.compareTo(orderDeductionPointsAmount) >= 0) {
                                    orderItemDeductionPointsAmount = orderDeductionPointsAmount;
                                }
                            }
                            if (orderItemDeductionPointsAmount.compareTo(itemResidualDeductionAmount) >= 0) {
                                orderItemDeductionPointsAmount = itemResidualDeductionAmount;
                            }
                            shoppingOrderSettlementItemParam.setDeductionPointsAmount(shoppingOrderSettlementItemParam.getDeductionPointsAmount().add(orderItemDeductionPointsAmount));
                            shoppingOrderSettlementItemParam.setPoint(shoppingOrderSettlementItemParam.getDeductionPointsAmount().multiply(orderAssetInformationDTO.getExchangeRate()));
                            orderDeductionPointsAmount = orderDeductionPointsAmount.subtract(orderItemDeductionPointsAmount);
                        }
                        currentOrderDeductionPointsAmount = orderDeductionPointsAmount;
                    }
                }
                currentPointsDeductionsAmount = pointsDeductionsAmount;
            }
            // 订单总价-积分抵扣金额
            for (ShoppingOrderSettlementParam settlement : shoppingOrderSettlementParams) {
                for (ShoppingOrderSettlementItemParam settlementItem : settlement.getItems()) {
                    settlementItem.setSubtotal(settlementItem.getSubtotal().subtract(settlementItem.getDeductionPointsAmount()));
                }
                settlement.setOrderTotalPrice(settlement.getOrderTotalPrice().subtract(settlement.getDeductionPointsAmount()));
                if (settlement.getDeductionPointsAmount().compareTo(settlement.getCommodityTotalPrice()) > 0) {
                    settlement.setFreightPriceDeductionPoints(settlement.getDeductionPointsAmount().subtract(settlement.getCommodityTotalPrice()).multiply(settlement.getExchangeRate()));
                }
            }
        }
        
        // 保存订单
        Result<List<Long>> resultIds = shoppingOrderService.save(shoppingOrderSettlementParams);
        if (!isSuccess(resultIds)) {
            return successCreated(resultIds.getRet());
        }
        return successCreated(resultIds);
    }
	
    @SuppressWarnings("unchecked")
    @Override
    public Result<ShoppingCartSettlementDTO> settlement(List<Long> idList, String memberNum, Long memberId) {
        Result<List<ShoppingCartDTO>> resultShoppingCartDTOS = shoppingCartService.findListByIds(memberId, idList);
        if (!isSuccess(resultShoppingCartDTOS)) {
            return successGet(resultShoppingCartDTOS);
        }
        // 把要查询的id放入set,统一一次性查询
        Set<Long> ids = new HashSet<Long>();
        for (ShoppingCartDTO shoppingCartDTO : resultShoppingCartDTOS.getModel()) {
            ids.add(shoppingCartDTO.getProductModelId());
        }
        // 通过商品型号id列表查询商品信息
        Result<List<ShoppingCartProductModelDTO>> resultShoppingCartProductModelDTOS = productModelService
                .getShoppingCartProductModel(new ArrayList<>(ids));
        if (!isSuccess(resultShoppingCartProductModelDTOS)) {
            return successGet(resultShoppingCartProductModelDTOS.getRet());
        }
        // 查找用户余额,积分和积分兑换比例
        Result<OrderAssetInformationDTO> orderAssetInformationResult = propertyInfoService
                .orderAssetInformation(memberNum);
        if (!isSuccess(orderAssetInformationResult)) {
            return successGet(orderAssetInformationResult.getRet());
        }

        // 组装数据
        Map<Long, ShoppingCartProductModelDTO> shoppingCartProductModelDTOMap = new HashMap<>();
        for (ShoppingCartProductModelDTO shoppingCartProductModelDTO : resultShoppingCartProductModelDTOS.getModel()) {
            if (!shoppingCartProductModelDTOMap.containsKey(shoppingCartProductModelDTO.getId())) {
                shoppingCartProductModelDTOMap.put(shoppingCartProductModelDTO.getId(), shoppingCartProductModelDTO);
            }
        }
        
        Map<Long, List<MemberShoppingCartDTO>> memberShoppingCartDTOMap = new HashMap<>();
        for (ShoppingCartDTO shoppingCartDTO : resultShoppingCartDTOS.getModel()) {
            MemberShoppingCartDTO memberShoppingCartDTO = new MemberShoppingCartDTO();
            memberShoppingCartDTO.setId(shoppingCartDTO.getId());
            memberShoppingCartDTO.setMerchantId(shoppingCartDTO.getMerchantId());
            memberShoppingCartDTO.setMerchantName(shoppingCartDTO.getMerchantName());
            memberShoppingCartDTO.setProductId(shoppingCartDTO.getProductId());
            memberShoppingCartDTO.setProductModelId(shoppingCartDTO.getProductModelId());
            memberShoppingCartDTO.setQuantity(shoppingCartDTO.getQuantity());
            ShoppingCartProductModelDTO shoppingCartProductModelDTO = shoppingCartProductModelDTOMap.get(shoppingCartDTO.getProductModelId());
            if (shoppingCartProductModelDTO == null) {
                continue;
            }
            memberShoppingCartDTO.setProductModelName(shoppingCartProductModelDTO.getName());
            memberShoppingCartDTO.setProductName(shoppingCartProductModelDTO.getProductName());
            memberShoppingCartDTO.setFeatureImage(shoppingCartProductModelDTO.getFeatureImage());
            memberShoppingCartDTO.setSalesPrice(shoppingCartProductModelDTO.getPrice());
            // 计算差价(商品表的现价减去购物车表价格,正为涨价,负为降价)
            memberShoppingCartDTO.setDifference(shoppingCartProductModelDTO.getPrice()
                    .subtract(shoppingCartDTO.getSalesPrice()));
            if (shoppingCartProductModelDTO.getStatus().equals(ProductStatusEnum.PRODUCT_STATUS_DEL)
                    || shoppingCartProductModelDTO.getStatus().equals(ProductStatusEnum.PRODUCT_STATUS_DOWN)) {
                memberShoppingCartDTO.setIsInvalid(true);
            } else {
                memberShoppingCartDTO.setIsInvalid(false);
            }
            memberShoppingCartDTO.setInventory(shoppingCartProductModelDTO.getInventory());
            if (!memberShoppingCartDTOMap.containsKey(shoppingCartDTO.getMerchantId())) {
                memberShoppingCartDTOMap.put(shoppingCartDTO.getMerchantId(), new ArrayList<>());
            }
            memberShoppingCartDTOMap.get(shoppingCartDTO.getMerchantId()).add(memberShoppingCartDTO);
        }
        
        ShoppingCartSettlementDTO model = new ShoppingCartSettlementDTO();
        BigDecimal total = new BigDecimal(0);
        // 每一个商家的商品会合并在一起，小计金额
        List<ShoppingCartSettlementItemDTO> items = new ArrayList<>();
        for (Map.Entry<Long, List<MemberShoppingCartDTO>> entry : memberShoppingCartDTOMap.entrySet()) {
            ShoppingCartSettlementItemDTO shoppingCartSettlementItemDTO = new ShoppingCartSettlementItemDTO();
            shoppingCartSettlementItemDTO.setFreightPrice(new BigDecimal(0));
            BigDecimal subtotal = new BigDecimal(0);
            Integer productNumber = Integer.valueOf(0);
            List<CalculateFreightDTO> calculateFreights = new ArrayList<>(entry.getValue().size());
            for (MemberShoppingCartDTO memberShoppingCartDTO : entry.getValue()) {
                subtotal = subtotal.add(memberShoppingCartDTO.getSalesPrice()
                        .multiply(new BigDecimal(memberShoppingCartDTO.getQuantity())));
                productNumber += memberShoppingCartDTO.getQuantity();
                ShoppingCartProductModelDTO shoppingCartProductModelDTO = shoppingCartProductModelDTOMap.get(memberShoppingCartDTO.getProductModelId());
                if (shoppingCartProductModelDTO == null) {
                    continue;
                }
                CalculateFreightDTO calculateFreight = new CalculateFreightDTO();
                calculateFreight.setFreight(shoppingCartProductModelDTO.getFreight());
                calculateFreight.setQuantity(memberShoppingCartDTO.getQuantity());
                calculateFreights.add(calculateFreight);
            }
            // 计算运费
            shoppingCartSettlementItemDTO.setFreightPrice(CalculateFreightDTO.calculateFreight(calculateFreights));
            shoppingCartSettlementItemDTO.setSubtotal(subtotal.add(shoppingCartSettlementItemDTO.getFreightPrice()));
            shoppingCartSettlementItemDTO.setProductNumber(productNumber);
            shoppingCartSettlementItemDTO.setItems(entry.getValue());
            items.add(shoppingCartSettlementItemDTO);
            total = total.add(shoppingCartSettlementItemDTO.getSubtotal());
        }
        model.setTotal(total);
        model.setItems(items);
        // 放入用户余额,积分和积分比例
        OrderAssetInformationDTO orderAssetInformationDTO = orderAssetInformationResult.getModel();
        model.setBalance(orderAssetInformationDTO.getBalance());
        model.setPoint(orderAssetInformationDTO.getPoint());
        model.setExchangeRate(orderAssetInformationDTO.getExchangeRate());
        return successCreated(model);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Result<ShoppingCartSettlementDTO> buyNow(Long memberId, String memberNum, ShoppingCartParam param) {
        ShoppingCartSettlementDTO rtn = new ShoppingCartSettlementDTO();
        ShoppingCartProductModelDTO shoppingCartProductModelDTO = null;
        /*
         * 查询产品信息 判断是否是抢购
         */
        if (param.getActivityProductModelId() == null) {
            Result<ShoppingCartProductModelDTO> result = productModelService
                    .getShoppingCartProductModel(param.getProductModelId());
            if (!isSuccess(result)) {
                return successCreated(result);
            }
            shoppingCartProductModelDTO = result.getModel();
        } else {
            Result<SeckillActivityProductModelInfoDTO> result = productModelService
                    .seckillActivityProductModel(param.getActivityProductModelId());
            if (!isSuccess(result)) {
                return successCreated(result);
            }
            SeckillActivityProductModelInfoDTO model = result.getModel();
            rtn.setActivityId(model.getActivityId());
            rtn.setActivityProductId(model.getActivityProductId());
            rtn.setActivityProductModelId(param.getActivityProductModelId());
            param.setQuantity(1);
            shoppingCartProductModelDTO = model;
        }
        // 查询商家名称
        Result<MerchantInfoForShoppingCartDTO> getMerchantInfoForShoppingCartResult = merchantStoreService
                .getMerchantInfoForShoppingCart(shoppingCartProductModelDTO.getMerchantId());
        if (!isSuccess(getMerchantInfoForShoppingCartResult)) {
            return successCreated(getMerchantInfoForShoppingCartResult);
        }

        List<MemberShoppingCartDTO> memberShoppingCartDTOList = new ArrayList<>(1);
        List<CalculateFreightDTO> calculateFreights = new ArrayList<>(1);
        MemberShoppingCartDTO memberShoppingCartDTO = new MemberShoppingCartDTO();
        memberShoppingCartDTO.setMerchantName(getMerchantInfoForShoppingCartResult.getModel().getMerchantStoreName());
        memberShoppingCartDTO.setMerchantStoreId(getMerchantInfoForShoppingCartResult.getModel().getMerchantStoreId());
        memberShoppingCartDTO.setMerchantId(shoppingCartProductModelDTO.getMerchantId());
        memberShoppingCartDTO.setProductId(shoppingCartProductModelDTO.getProductId());
        memberShoppingCartDTO.setProductModelId(shoppingCartProductModelDTO.getId());
        memberShoppingCartDTO.setQuantity(param.getQuantity());
        memberShoppingCartDTO.setProductModelName(shoppingCartProductModelDTO.getName());
        memberShoppingCartDTO.setProductName(shoppingCartProductModelDTO.getProductName());
        memberShoppingCartDTO.setFeatureImage(shoppingCartProductModelDTO.getFeatureImage());
        memberShoppingCartDTO.setSalesPrice(shoppingCartProductModelDTO.getPrice());
        if (shoppingCartProductModelDTO.getStatus().equals(ProductStatusEnum.PRODUCT_STATUS_DEL)
                || shoppingCartProductModelDTO.getStatus().equals(ProductStatusEnum.PRODUCT_STATUS_DOWN)) {
            memberShoppingCartDTO.setIsInvalid(true);
        } else {
            memberShoppingCartDTO.setIsInvalid(false);
        }
        memberShoppingCartDTO.setInventory(shoppingCartProductModelDTO.getInventory());
        
        CalculateFreightDTO calculateFreight = new CalculateFreightDTO();
        calculateFreight.setFreight(shoppingCartProductModelDTO.getFreight());
        calculateFreight.setQuantity(memberShoppingCartDTO.getQuantity());
        calculateFreights.add(calculateFreight);
        
        memberShoppingCartDTOList.add(memberShoppingCartDTO);

        // 查找用户余额
        Result<OrderAssetInformationDTO> orderAssetInformationResult = propertyInfoService
                .orderAssetInformation(memberNum);
        if (!isSuccess(orderAssetInformationResult)) {
            return successGet(orderAssetInformationResult.getRet());
        }

        // 每一个商家的商品会合并在一起，小计金额
        List<ShoppingCartSettlementItemDTO> items = new ArrayList<>();
        ShoppingCartSettlementItemDTO shoppingCartSettlementItemDTO = new ShoppingCartSettlementItemDTO();
        // 计算运费
        shoppingCartSettlementItemDTO.setFreightPrice(CalculateFreightDTO.calculateFreight(calculateFreights));
        // 小计
        BigDecimal subtotal = memberShoppingCartDTO.getSalesPrice().multiply(new BigDecimal(memberShoppingCartDTO.getQuantity())).add(shoppingCartSettlementItemDTO.getFreightPrice());
        shoppingCartSettlementItemDTO.setSubtotal(subtotal);
        shoppingCartSettlementItemDTO.setProductNumber(memberShoppingCartDTO.getQuantity());
        shoppingCartSettlementItemDTO.setItems(memberShoppingCartDTOList);
        items.add(shoppingCartSettlementItemDTO);
        rtn.setTotal(shoppingCartSettlementItemDTO.getSubtotal());
        rtn.setItems(items);

        // 放入用户余额,积分和积分比例
        OrderAssetInformationDTO orderAssetInformationDTO = orderAssetInformationResult.getModel();
        rtn.setBalance(orderAssetInformationDTO.getBalance());
        rtn.setPoint(orderAssetInformationDTO.getPoint());
        rtn.setExchangeRate(orderAssetInformationDTO.getExchangeRate());
        return successCreated(rtn);
    }
	
	@SuppressWarnings("unchecked")
    @BusinessInventoryCtrl(idParamIndex = 2, businessKey = BusinessKey.SECKILL_ACTIVITY_PRODUCT, using = SeckillActivityProductBusinessDecisionServiceImpl.class)
    @Override
    public Result<Long> buyNowCreateOrder(Long memberId, ShoppingOrderBuyNowCreateOrderForeignParam param, Long seckillActivityProductModelId, String memberNum) throws BusinessExecuteException {
	    Result<Long> result = buyNowCreateOrder(memberId, param, memberNum);
	    if (!isSuccess(result)) {
	        throw new BusinessExecuteException(result.getRet(), result.getMsg());
	    }
        return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Result buyNowCreateOrder(Long memberId, ShoppingOrderBuyNowCreateOrderForeignParam param, String memberNum) {
        //查找用户余额,积分和积分兑换比例
        Result<OrderAssetInformationDTO> orderAssetInformationResult = propertyInfoService
                .orderAssetInformation(memberNum);
        if (!isSuccess(orderAssetInformationResult)) {
            return orderAssetInformationResult;
        }
        OrderAssetInformationDTO orderAssetInformationDTO = orderAssetInformationResult.getModel();
        // 校验用户的积分
        if (param.getIntegral() != null && param.getIntegral().compareTo(orderAssetInformationDTO.getPoint()) > 0) {
            Result result = new Result<>();
            result.setRet(ResultCode.WRONG_OPERATION);
            result.setMsg("当前积分不足");
            return result;
        }
        
        // 通过商品型号id列表查询商品信息
        ShoppingCartProductModelDTO shoppingCartProductModelDTO = null;
        /*
         * 查询产品信息 判断是否是抢购
         */
        boolean isSeckillActivityProduct = param.getActivityProductModelId() != null;
        if (!isSeckillActivityProduct) {
            Result<ShoppingCartProductModelDTO> result = productModelService
                    .getShoppingCartProductModel(param.getProductModelId());
            if (!isSuccess(result)) {
                return result;
            }
            shoppingCartProductModelDTO = result.getModel();
        } else {
            Result<SeckillActivityProductModelInfoDTO> result = productModelService
                    .seckillActivityProductModel(param.getActivityProductModelId());
            if (!isSuccess(result)) {
                return result;
            }
            SeckillActivityProductModelInfoDTO model = result.getModel();
            param.setQuantity(1);
            shoppingCartProductModelDTO = model;
        }
        // 判断商品是否失效
        if (param.getActivityProductModelId() == null
                && !ProductStatusEnum.PRODUCT_STATUS_UP.equals(shoppingCartProductModelDTO.getStatus())) {
            Result result = new Result<>();
            result.setRet(ResultCode.PRODUCT_HAS_EXPIRED);
            result.setMsg(ResultCode.get(result.getRet()));
            return result;
        }
        // 判断库存
        if (shoppingCartProductModelDTO.getInventory() < param.getQuantity()) {
            Result result = new Result<>();
            result.setRet(ResultCode.INVENTORY_SHORTAGE);
            result.setMsg(ResultCode.get(result.getRet()));
            return result;
        }
        // 查询地址
        Result<AddressDTO> resultAddressDTO = addressService.get(param.getAddressId(), memberNum);
        if (!isSuccess(resultAddressDTO)) {
            return resultAddressDTO;
        }
        // 查询商家是否支持七天退货
        List<Long> merchantIdList = new ArrayList<>();
        merchantIdList.add(shoppingCartProductModelDTO.getMerchantId());
        ShoppingOrderFindUserInfoParam shoppingOrderFindUserInfoParam = new ShoppingOrderFindUserInfoParam();
        shoppingOrderFindUserInfoParam.setMerchantIdList(merchantIdList);
        shoppingOrderFindUserInfoParam.setMemberId(memberId);
        Result<ShoppingOrderFindUserInfoDTO> shoppingOrderFindUserInfoDTOResult = merchantStoreService
                .shoppingOrderFindUserInfo(shoppingOrderFindUserInfoParam);
        if (!isSuccess(shoppingOrderFindUserInfoDTOResult)) {
            return shoppingOrderFindUserInfoDTOResult;
        }
        ShoppingOrderFindMerchantInfoDTO shoppingOrderFindMerchantInfoDTO = shoppingOrderFindUserInfoDTOResult
                .getModel().getShoppingOrderFindMerchantInfoDTOList().get(0);
        // 组装订单
        List<ShoppingOrderSettlementParam> shoppingOrderSettlementParams = new ArrayList<>();
        ShoppingOrderSettlementParam shoppingOrderSettlementParam = new ShoppingOrderSettlementParam();
        shoppingOrderSettlementParam.setCommodityTotalPrice(new BigDecimal(0));
        shoppingOrderSettlementParam.setMemberId(memberId);
        shoppingOrderSettlementParam.setMemberNum(shoppingOrderFindUserInfoDTOResult.getModel().getMemberNum());
        shoppingOrderSettlementParam
                .setMemberNickname(shoppingOrderFindUserInfoDTOResult.getModel().getMemberNickname());
        shoppingOrderSettlementParam.setMerchantId(shoppingCartProductModelDTO.getMerchantId());
        shoppingOrderSettlementParam.setMerchantStoreId(shoppingOrderFindMerchantInfoDTO.getMerchantStoreId());
        shoppingOrderSettlementParam
                .setMerchantStoreRegionPath(shoppingOrderFindMerchantInfoDTO.getMerchantStoreRegionPath());
        shoppingOrderSettlementParam.setMerchantNum(shoppingOrderFindMerchantInfoDTO.getMerchantNum());
        shoppingOrderSettlementParam.setMerchantName(shoppingOrderFindMerchantInfoDTO.getMerchantStoreName());
        shoppingOrderSettlementParam.setMessage(param.getMessage());
        
        // 设置收货人信息,对应每个订单
        shoppingOrderSettlementParam.setConsigneeAddress(
                resultAddressDTO.getModel().getRegionName() + " " + resultAddressDTO.getModel().getAddr());
        shoppingOrderSettlementParam.setConsigneeName(resultAddressDTO.getModel().getName());
        shoppingOrderSettlementParam.setConsigneeMobile(resultAddressDTO.getModel().getMobile());
        // 是否支持七天退货
        shoppingOrderSettlementParam.setIsNoReasonReturn(shoppingOrderFindMerchantInfoDTO.getIsNoReasonReturn());
        // 用户是否是商家粉丝
        shoppingOrderSettlementParam.setIsFans(shoppingOrderFindMerchantInfoDTO.getIsFans());
        if (isSeckillActivityProduct) {
            SeckillActivityProductModelInfoDTO seckillActivityProductModelInfoDTO = (SeckillActivityProductModelInfoDTO) shoppingCartProductModelDTO;
            shoppingOrderSettlementParam.setActivityId(seckillActivityProductModelInfoDTO.getActivityId());
            shoppingOrderSettlementParam
                    .setActivityProductId(seckillActivityProductModelInfoDTO.getActivityProductId());
        }
        List<ShoppingOrderSettlementItemParam> items = new ArrayList<>(1);
        List<CalculateFreightDTO> calculateFreights = new ArrayList<>(1);
        ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam = new ShoppingOrderSettlementItemParam();
        // 加入购物车id,用于在保存订单之后删除购物车记录
        shoppingOrderSettlementItemParam.setIsAllowRefund(shoppingCartProductModelDTO.getIsAllowRefund());
        shoppingOrderSettlementItemParam.setProductId(shoppingCartProductModelDTO.getProductId());
        shoppingOrderSettlementItemParam.setProductName(shoppingCartProductModelDTO.getProductName());
        shoppingOrderSettlementItemParam.setProductFeatureImage(shoppingCartProductModelDTO.getFeatureImage());
        shoppingOrderSettlementItemParam.setProductModelId(shoppingCartProductModelDTO.getId());
        if (isSeckillActivityProduct) {
            shoppingOrderSettlementItemParam.setActivityProductModelId(param.getActivityProductModelId());
        }
        shoppingOrderSettlementItemParam.setProductModelName(shoppingCartProductModelDTO.getName());
        shoppingOrderSettlementItemParam.setQuantity(param.getQuantity());
        shoppingOrderSettlementItemParam.setRegularPrice(shoppingCartProductModelDTO.getOriginalPrice());
        shoppingOrderSettlementItemParam.setSalesPrice(shoppingCartProductModelDTO.getPrice());
        shoppingOrderSettlementItemParam.setFreight(shoppingCartProductModelDTO.getFreight());
        shoppingOrderSettlementItemParam.setSubtotal(shoppingCartProductModelDTO.getPrice().multiply(new BigDecimal(param.getQuantity())));
        
        CalculateFreightDTO calculateFreight = new CalculateFreightDTO();
        calculateFreight.setFreight(shoppingCartProductModelDTO.getFreight());
        calculateFreight.setQuantity(shoppingOrderSettlementItemParam.getQuantity());
        calculateFreights.add(calculateFreight);
        
        shoppingOrderSettlementParam.setCommodityTotalPrice(shoppingOrderSettlementParam.getCommodityTotalPrice()
                .add(shoppingOrderSettlementItemParam.getSubtotal()));
        
        // 计算运费
        shoppingOrderSettlementParam.setFreightPrice(CalculateFreightDTO.calculateFreight(calculateFreights));
        shoppingOrderSettlementParam.setExchangeRate(orderAssetInformationDTO.getExchangeRate());
        items.add(shoppingOrderSettlementItemParam);
        shoppingOrderSettlementParam.setItems(items);
        
        /*
         * 订单总价等于货物总价+运费
         */
        shoppingOrderSettlementParam.setOrderTotalPrice(shoppingOrderSettlementParam.getCommodityTotalPrice().add(shoppingOrderSettlementParam.getFreightPrice()));
        shoppingOrderSettlementParams.add(shoppingOrderSettlementParam);
        
        if (param.getIntegral() != null && param.getIntegral().doubleValue() > 0D) {
            /*
             *  计算积分抵扣金额
             *  校验积分兑换金额是否大于总价 
             */
            BigDecimal pointsDeductionsAmount = param.getIntegral().divide(orderAssetInformationDTO.getExchangeRate()).setScale(2, RoundingMode.DOWN);
            // 判断当前用户的积分抵扣金额是否大于商品的总价
            if (pointsDeductionsAmount.compareTo(shoppingOrderSettlementParam.getOrderTotalPrice()) > 0) {
                pointsDeductionsAmount = shoppingOrderSettlementParam.getOrderTotalPrice();
            }
            
            BigDecimal orderDeductionPointsAmount = pointsDeductionsAmount;
            /*
             *  如果当前订单抵扣金额大于商品总价, 抵扣金额就为商品总价, 超过的部分抵扣运费
             */
            if (orderDeductionPointsAmount.compareTo(shoppingOrderSettlementParam.getCommodityTotalPrice()) >= 0) {
                orderDeductionPointsAmount = shoppingOrderSettlementParam.getCommodityTotalPrice();
            }
            
            shoppingOrderSettlementItemParam.setPoint(orderDeductionPointsAmount.multiply(orderAssetInformationDTO.getExchangeRate()));
            shoppingOrderSettlementItemParam.setDeductionPointsAmount(orderDeductionPointsAmount);
            shoppingOrderSettlementItemParam.setSubtotal(shoppingOrderSettlementItemParam.getSubtotal().subtract(shoppingOrderSettlementItemParam.getDeductionPointsAmount()));
            shoppingOrderSettlementParam.setDeductionPoints(pointsDeductionsAmount.multiply(orderAssetInformationDTO.getExchangeRate()));
            shoppingOrderSettlementParam.setDeductionPointsAmount(pointsDeductionsAmount);
            shoppingOrderSettlementParam.setOrderTotalPrice(shoppingOrderSettlementParam.getOrderTotalPrice().subtract(shoppingOrderSettlementParam.getDeductionPointsAmount()));
            if (shoppingOrderSettlementParam.getDeductionPointsAmount().compareTo(shoppingOrderSettlementParam.getCommodityTotalPrice()) > 0) {
                shoppingOrderSettlementParam.setFreightPriceDeductionPoints(shoppingOrderSettlementParam.getDeductionPointsAmount().subtract(shoppingOrderSettlementParam.getCommodityTotalPrice()).multiply(shoppingOrderSettlementParam.getExchangeRate()));
            }
        }
        // 保存订单
        Result<List<Long>> resultIds = shoppingOrderService.save(shoppingOrderSettlementParams);
        if (!isSuccess(resultIds)) {
            return resultIds;
        }
        return resultIds;
    }
}
