package com.lawu.eshop.member.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.order.param.ShoppingOrderSettlementItemParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.foreign.BatchCreateOrderParam;
import com.lawu.eshop.property.dto.OrderAssetInformationDTO;

public class PointsAllocation {
    
    @Test
    public void pointsAllocation() {
        OrderAssetInformationDTO orderAssetInformationDTO = new OrderAssetInformationDTO();
        orderAssetInformationDTO.setExchangeRate(new BigDecimal(100));
        
        BatchCreateOrderParam param = new BatchCreateOrderParam();
        param.setIntegral(new BigDecimal(500));
        
        BigDecimal totalPrice = new BigDecimal(0);
        List<ShoppingOrderSettlementParam> shoppingOrderSettlementParams = new ArrayList<>();
        if (true) {
            ShoppingOrderSettlementParam shoppingOrderSettlementParam2 = new ShoppingOrderSettlementParam();
            shoppingOrderSettlementParam2.setDeductionPoints(new BigDecimal(0));
            shoppingOrderSettlementParam2.setDeductionPointsAmount(new BigDecimal(0));
            shoppingOrderSettlementParam2.setOrderTotalPrice(new BigDecimal("18.89"));
            totalPrice = totalPrice.add(shoppingOrderSettlementParam2.getOrderTotalPrice());
            ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam2 = new ShoppingOrderSettlementItemParam();
            shoppingOrderSettlementItemParam2.setSalesPrice(new BigDecimal("18.88"));
            shoppingOrderSettlementItemParam2.setQuantity(1);
            shoppingOrderSettlementItemParam2.setPoint(new BigDecimal(0));
            shoppingOrderSettlementItemParam2.setDeductionPointsAmount(new BigDecimal(0));
            shoppingOrderSettlementParam2.setItems(new ArrayList<>());
            shoppingOrderSettlementParam2.getItems().add(shoppingOrderSettlementItemParam2);
            ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam = new ShoppingOrderSettlementItemParam();
            shoppingOrderSettlementItemParam.setSalesPrice(new BigDecimal("0.01"));
            shoppingOrderSettlementItemParam.setQuantity(1);
            shoppingOrderSettlementItemParam.setPoint(new BigDecimal(0));
            shoppingOrderSettlementItemParam.setDeductionPointsAmount(new BigDecimal(0));
            shoppingOrderSettlementParam2.getItems().add(shoppingOrderSettlementItemParam);
            shoppingOrderSettlementParams.add(shoppingOrderSettlementParam2);
            
            ShoppingOrderSettlementParam shoppingOrderSettlementParam = new ShoppingOrderSettlementParam();
            shoppingOrderSettlementParam.setDeductionPoints(new BigDecimal(0));
            shoppingOrderSettlementParam.setDeductionPointsAmount(new BigDecimal(0));
            shoppingOrderSettlementParam.setOrderTotalPrice(new BigDecimal("0.02"));
            totalPrice = totalPrice.add(shoppingOrderSettlementParam.getOrderTotalPrice());
            ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam3 = new ShoppingOrderSettlementItemParam();
            shoppingOrderSettlementItemParam3.setSalesPrice(new BigDecimal("0.01"));
            shoppingOrderSettlementItemParam3.setQuantity(1);
            shoppingOrderSettlementItemParam3.setPoint(new BigDecimal(0));
            shoppingOrderSettlementItemParam3.setDeductionPointsAmount(new BigDecimal(0));
            shoppingOrderSettlementParam.setItems(new ArrayList<>());
            shoppingOrderSettlementParam.getItems().add(shoppingOrderSettlementItemParam3);
            ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam4 = new ShoppingOrderSettlementItemParam();
            shoppingOrderSettlementItemParam4.setSalesPrice(new BigDecimal("0.01"));
            shoppingOrderSettlementItemParam4.setQuantity(1);
            shoppingOrderSettlementItemParam4.setPoint(new BigDecimal(0));
            shoppingOrderSettlementItemParam4.setDeductionPointsAmount(new BigDecimal(0));
            shoppingOrderSettlementParam.getItems().add(shoppingOrderSettlementItemParam4);
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
            while (pointsDeductionsAmount.doubleValue() != 0D) {
                for(int i = 0; i < shoppingOrderSettlementParams.size(); i++) {
                    ShoppingOrderSettlementParam shoppingOrderSettlementParam = shoppingOrderSettlementParams.get(i);
                    if (shoppingOrderSettlementParam.getOrderTotalPrice().compareTo(shoppingOrderSettlementParam.getDeductionPointsAmount()) == 0) {
                        continue;
                    }
                    // 计算当前订单抵扣金额占总金额比例
                    BigDecimal orderDeductionPointsAmount = shoppingOrderSettlementParam.getOrderTotalPrice().multiply(pointsDeductionsAmount).divide(totalPrice, 2, RoundingMode.DOWN);
                    /*
                     *  放入剩余的抵扣金额
                     *  1.如果当前是最后一个订单项
                     *  2.计算积分抵扣金额为0
                     */
                    if (i == shoppingOrderSettlementParams.size() - 1 || orderDeductionPointsAmount.doubleValue() == 0D) {
                        BigDecimal residualDeductionAmount = shoppingOrderSettlementParam.getOrderTotalPrice().subtract(shoppingOrderSettlementParam.getDeductionPointsAmount());
                        if (residualDeductionAmount.compareTo(pointsDeductionsAmount) >= 0) {
                            orderDeductionPointsAmount = pointsDeductionsAmount;
                        } else {
                            orderDeductionPointsAmount = residualDeductionAmount;
                        }
                    }
                    shoppingOrderSettlementParam.setDeductionPointsAmount(shoppingOrderSettlementParam.getDeductionPointsAmount().add(orderDeductionPointsAmount));
                    shoppingOrderSettlementParam.setDeductionPoints(shoppingOrderSettlementParam.getDeductionPointsAmount().multiply(orderAssetInformationDTO.getExchangeRate()));
                    pointsDeductionsAmount = pointsDeductionsAmount.subtract(orderDeductionPointsAmount);
                    
                    while (orderDeductionPointsAmount.doubleValue() != 0D) {
                        for(int j = 0; j < shoppingOrderSettlementParam.getItems().size(); j++) {
                            ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam = shoppingOrderSettlementParam.getItems().get(j);
                            BigDecimal orderItemTotalPrice = shoppingOrderSettlementItemParam.getSalesPrice().multiply(new BigDecimal(shoppingOrderSettlementItemParam.getQuantity()));
                            if (orderItemTotalPrice.compareTo(shoppingOrderSettlementItemParam.getDeductionPointsAmount()) == 0) {
                                continue;
                            }
                            // 计算当前订单抵扣金额占总金额比例
                            BigDecimal orderItemDeductionPointsAmount = orderItemTotalPrice.multiply(orderDeductionPointsAmount).divide(shoppingOrderSettlementParam.getOrderTotalPrice(), 2, RoundingMode.DOWN);
                            /*
                             *  放入剩余的抵扣金额
                             *  1.如果当前是最后一个订单项
                             *  2.计算积分抵扣金额为0
                             */
                            if (j == shoppingOrderSettlementParam.getItems().size() - 1 || orderItemDeductionPointsAmount.doubleValue() == 0D) {
                                BigDecimal residualDeductionAmount = orderItemTotalPrice.subtract(shoppingOrderSettlementItemParam.getDeductionPointsAmount());
                                if (residualDeductionAmount.compareTo(orderDeductionPointsAmount) >= 0) {
                                    orderItemDeductionPointsAmount = orderDeductionPointsAmount;
                                } else {
                                    orderItemDeductionPointsAmount = residualDeductionAmount;
                                }
                            }
                            shoppingOrderSettlementItemParam.setDeductionPointsAmount(shoppingOrderSettlementItemParam.getDeductionPointsAmount().add(orderItemDeductionPointsAmount));
                            shoppingOrderSettlementItemParam.setPoint(shoppingOrderSettlementItemParam.getDeductionPointsAmount().multiply(orderAssetInformationDTO.getExchangeRate()));
                            orderDeductionPointsAmount = orderDeductionPointsAmount.subtract(orderItemDeductionPointsAmount);
                        }
                    }
                }
            }
        }
        
        for (ShoppingOrderSettlementParam shoppingOrderSettlementParam : shoppingOrderSettlementParams) {
            System.out.println("order--" + shoppingOrderSettlementParam.getOrderTotalPrice() + ":" + shoppingOrderSettlementParam.getDeductionPoints() + ":" + shoppingOrderSettlementParam.getDeductionPointsAmount());
            for (ShoppingOrderSettlementItemParam shoppingOrderSettlementItemParam : shoppingOrderSettlementParam.getItems()) {
                System.out.println("orderItem--" + shoppingOrderSettlementItemParam.getSalesPrice().multiply(new BigDecimal(shoppingOrderSettlementItemParam.getQuantity())) + ":" + shoppingOrderSettlementItemParam.getPoint() + ":" + shoppingOrderSettlementItemParam.getDeductionPointsAmount());
            }
        }
    }
    
}
