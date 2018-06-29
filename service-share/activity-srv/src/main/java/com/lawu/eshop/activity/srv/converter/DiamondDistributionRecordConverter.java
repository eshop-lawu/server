package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.activity.constants.DistributionStatusEnum;
import com.lawu.eshop.activity.dto.DiamondDistributionRecordDTO;
import com.lawu.eshop.activity.srv.bo.DiamondDistributionRecordBO;
import com.lawu.eshop.activity.srv.domain.RichDiamondDistributionRecordDO;

/**
 * 金蛋分配记录转换类
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
public class DiamondDistributionRecordConverter {
    
    private DiamondDistributionRecordConverter() {}
    
    public static DiamondDistributionRecordBO convert(RichDiamondDistributionRecordDO source) {
        DiamondDistributionRecordBO rtn = null;
        if (source == null) {
            return rtn;
        }
        rtn = new DiamondDistributionRecordBO();
        rtn.setResidents(source.getResidents());
        rtn.setAllocatedLuckyResidents(source.getAllocatedLuckyResidents());
        rtn.setAllocatedResidents(source.getAllocatedResidents());
        rtn.setExpectedLuckyAllocations(source.getExpectedLuckyAllocations());
        rtn.setExpectedOrdinaryAllocations(source.getExpectedOrdinaryAllocations());
        rtn.setLuckyResidents(source.getLuckyResidents());
        rtn.setRealLuckAllocations(source.getRealLuckAllocations());
        rtn.setRealOrdinaryAllocations(source.getRealOrdinaryAllocations());
        rtn.setMerchantRealOrdinaryAllocations(source.getMerchantRealOrdinaryAllocations());
        rtn.setMerchantRealLuckAllocations(source.getMerchantRealLuckAllocations());
        rtn.setGmtAllocationsComplete(source.getGmtAllocationsComplete());
        rtn.setGmtGrantComplete(source.getGmtGrantComplete());
        rtn.setId(source.getId());
        rtn.setStatus(DistributionStatusEnum.getEnum(source.getStatus()));
        return rtn;
    }

    public static List<DiamondDistributionRecordBO> convert(List<RichDiamondDistributionRecordDO> source) {
        List<DiamondDistributionRecordBO> rtn = null;
        if (source == null || source.isEmpty()) {
            return rtn;
        }
        rtn = new ArrayList<>();
        for (RichDiamondDistributionRecordDO item : source) {
            rtn.add(convert(item));
        }
        return rtn;
    }
    
    public static DiamondDistributionRecordDTO convert(DiamondDistributionRecordBO source) {
        DiamondDistributionRecordDTO rtn = null;
        if (source == null) {
            return rtn;
        }
        rtn = new DiamondDistributionRecordDTO();
        rtn.setResidents(source.getResidents());
        rtn.setAllocatedLuckyResidents(source.getAllocatedLuckyResidents());
        rtn.setAllocatedResidents(source.getAllocatedResidents());
        rtn.setExpectedLuckyAllocations(source.getExpectedLuckyAllocations());
        rtn.setExpectedOrdinaryAllocations(source.getExpectedOrdinaryAllocations());
        rtn.setLuckyResidents(source.getLuckyResidents());
        rtn.setRealLuckAllocations(source.getRealLuckAllocations());
        rtn.setRealOrdinaryAllocations(source.getRealOrdinaryAllocations());
        rtn.setMerchantRealOrdinaryAllocations(source.getMerchantRealOrdinaryAllocations());
        rtn.setMerchantRealLuckAllocations(source.getMerchantRealLuckAllocations());
        rtn.setGmtAllocationsComplete(source.getGmtAllocationsComplete());
        rtn.setGmtGrantComplete(source.getGmtGrantComplete());
        rtn.setId(source.getId());
        rtn.setStatus(source.getStatus());
        return rtn;
    }

    public static List<DiamondDistributionRecordDTO> convertDTOList(List<DiamondDistributionRecordBO> source) {
        List<DiamondDistributionRecordDTO> rtn = new ArrayList<>();
        if (source == null || source.isEmpty()) {
            return rtn;
        }
        for (DiamondDistributionRecordBO item : source) {
            rtn.add(convert(item));
        }
        return rtn;
    }
}
