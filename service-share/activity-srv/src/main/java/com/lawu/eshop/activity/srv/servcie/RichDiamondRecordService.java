package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.constants.RichDiamondRecordStatusEnum;
import com.lawu.eshop.activity.query.DiamondRecordQueryParam;
import com.lawu.eshop.activity.srv.bo.RichDiamondRecordBO;
import com.lawu.framework.core.page.Page;

/**
 * 瑞奇岛E钻收支记录
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public interface RichDiamondRecordService {

    /**
     * 获取自己未领取的E钻信息
     * @param userNum
     * @param flag 是否分页
     * @return
     */
    List<RichDiamondRecordBO> getMyRichDiamondRecord(String userNum,RichDiamondRecordStatusEnum status,boolean flag);
	
	/**
     * 分配E钻以及幸运钻
     * 
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    void diamondDistribution();
    
    /**
     * 发送钻石
     * 
     * @author jiangxinjun
     * @createDate 2018年5月4日
     * @updateDate 2018年5月4日
     */
    void grantDiamonds();
    
    /**
     * 查询钻石记录
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年6月7日
     * @updateDate 2018年6月7日
     */
    Page<RichDiamondRecordBO> page(DiamondRecordQueryParam param);
}
