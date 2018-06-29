package com.lawu.eshop.order.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lawu.eshop.common.dto.FreightDTO;

/**
 * 运费计算DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年4月18日
 * @updateDate 2018年4月18日
 */
public class CalculateFreightDTO {
    
    /**
     * 数量
     */
    private Integer quantity;
    
    /**
     * 运费计算规则
     */
    private FreightDTO freight;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public FreightDTO getFreight() {
        return freight;
    }

    public void setFreight(FreightDTO freight) {
        this.freight = freight;
    }
    
    /**
     * 计算运费
     * @param calculateFreights
     * @return
     * @author jiangxinjun
     * @createDate 2018年4月18日
     * @updateDate 2018年4月18日
     */
    public static BigDecimal calculateFreight(List<CalculateFreightDTO> calculateFreights) {
        BigDecimal freightPrice = new BigDecimal(0);
        if (calculateFreights == null || calculateFreights.isEmpty()) {
            return freightPrice;
        }
        // 以运费规则为key, 统计当前商品总件数
        Map<FreightDTO, Integer> calculateFreightMap = new HashMap<>();
        calculateFreights.forEach(item -> {
            if (!calculateFreightMap.containsKey(item.getFreight())) {
                calculateFreightMap.put(item.getFreight(), 0);
            }
            calculateFreightMap.put(item.getFreight(), calculateFreightMap.get(item.getFreight()) + item.getQuantity());
        });
        Set<Entry<FreightDTO, Integer>> entrySet = calculateFreightMap.entrySet();
        for (Entry<FreightDTO, Integer> item : entrySet) {
            FreightDTO freight = item.getKey();
            Integer quantity = item.getValue();
            if (freight == null) { continue; }
            // 计算运费
            BigDecimal itemFreightPrice = null;
            // 如果在默认件数以内, 使用默认运费
            if (freight.getDefaultPieceNumber() >= quantity) {
                itemFreightPrice = freight.getDefaultPieceMoney();
            } else {
                // 如果用户的购买的件数大于默认的件数, 计算运费的增量值
                itemFreightPrice = freight.getDefaultPieceMoney();
                itemFreightPrice = itemFreightPrice.add(new BigDecimal(quantity - freight.getDefaultPieceNumber())
                        .divide(new BigDecimal(freight.getAddPieceNumber()), 0, RoundingMode.UP).multiply(freight.getAddPieceMoney()));
            }
            freightPrice = freightPrice.add(itemFreightPrice);
        }
        return freightPrice;
    }
}
