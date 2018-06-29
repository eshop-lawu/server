package com.lawu.eshop.activity.srv.mapper.extend;

import com.lawu.eshop.activity.srv.domain.extend.DiamondDistributionParam;
import com.lawu.eshop.activity.srv.domain.extend.GetLuckyDiamondIdByIndexParam;
import com.lawu.eshop.activity.srv.domain.extend.RichAccountAddPowerParam;
import com.lawu.eshop.activity.srv.domain.extend.TotalPowerParam;
import com.lawu.eshop.activity.srv.domain.extend.UpdatePowerSnapshootParam;

/**
 * 瑞奇岛账户
 * @author jiangxinjun
 * @createDate 2018年5月3日
 * @updateDate 2018年5月3日
 */
public interface RichAccountDOExtendMapper {
    
    /**
     * 更新动力值快照
     * @param updatePowerSnapshootParam
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    int updatePowerSnapshoot(UpdatePowerSnapshootParam param);

    /**
     * 给用户加动力值
     * @param param
     */
    int addMemberPower(RichAccountAddPowerParam param);
    
    /**
     * 获取所有满足条件的动力值总和
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    Long totalPower(TotalPowerParam param);
    
    /**
     * 领取钻石
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    int receiveDiamonds(DiamondDistributionParam param);
    
    /**
     * 根据下标获取id
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    Long getLuckyDiamondIdByIndex(GetLuckyDiamondIdByIndexParam param);
}
