package com.lawu.eshop.activity.srv.mapper.extend;

import com.lawu.eshop.activity.srv.domain.extend.StatisticsDiamondNumberDO;
import com.lawu.eshop.activity.srv.domain.extend.StatisticsDiamondNumberParam;

/**
 * 钻石收支记录扩展
 * @author jiangxinjun
 * @createDate 2018年5月4日
 * @updateDate 2018年5月4日
 */
public interface RichDiamondRecordDOExtendMapper {
    
    /**
     * 发送钻石
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月4日
     * @updateDate 2018年5月4日
     */
    int grantDiamonds();
    
    /**
     * 统计钻石分配数量
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    StatisticsDiamondNumberDO statisticsDiamondNumber(StatisticsDiamondNumberParam param);
}
