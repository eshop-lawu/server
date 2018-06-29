package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;

import com.lawu.eshop.activity.query.DiamondDistributionRecordQueryParam;
import com.lawu.eshop.activity.srv.bo.DiamondDistributionRecordBO;
import com.lawu.framework.core.page.Page;

/**
 * 鸡蛋分配记录接口
 * 
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public interface DiamondDistributionRecordService {

    /**
     * 保存分配记录
     * 
     * @param chicks
     *            小鸡的总数量
     * @param layEggsTotal
     *            金蛋分配的总数量
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    Long save(Long residents, BigDecimal expectedOrdinaryAllocations);
    
    /**
     * 更新幸运钻数量
     * 
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    Long updateLuckyDiamondNumber(Long luckyResidents, BigDecimal expectedLuckyAllocations);
    
    /**
     * 分配完成
     * 
     * @param recordId
     *            记录id
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    void distributionCompleted();
    
    /**
     * 更新状态为发放完成
     * 
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    void releaseCompleted();
    
    /**
     * 分页查询钻石分配记录
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    Page<DiamondDistributionRecordBO> page(DiamondDistributionRecordQueryParam param);
}
