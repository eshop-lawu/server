package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.activity.param.GenerateLargeRedpacketParam;
import com.lawu.eshop.activity.param.GenerateNormalRedpacketParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivitySaveParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivityUpdateParam;
import com.lawu.eshop.activity.param.RedpacketActivityQueryParam;
import com.lawu.eshop.activity.param.SaveRedpacketParam;
import com.lawu.eshop.activity.srv.bo.ExpectedRedpacketAmountBO;
import com.lawu.eshop.activity.srv.bo.GenerateLargeRedpacketBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.framework.core.page.Page;

/**
 * 助力红包活动服务接口
 * 
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
public interface HelpRedpacketActivityService {

    /**
     * 获取助力红包活动
     * 
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2018年1月12日
     */
    HelpRedpacketActivityBO get(Integer id);

    /**
     * 更新助力红包活动
     * 
     * @param param
     *            更新参数
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    void update(Integer id, HelpRedpacketActivityUpdateParam param);
    
    /**
     * 生成大额红包
     * 
     * @param param
     *            生成参数
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    GenerateLargeRedpacketBO generateLargeRedpacket(Integer id, GenerateLargeRedpacketParam param) throws WrongOperationException;
    
    /**
     * 保存大额红包
     * 
     * @param param
     *            保存参数
     * @author jiangxinjun
     * @createDate 2017年12月30日
     * @updateDate 2017年12月30日
     */
    void saveLargeRedpacket(Integer id, List<SaveRedpacketParam> params) throws WrongOperationException;
    
    /**
     * 生成普通红包
     * 
     * @author jiangxinjun
     * @createDate 2017年12月30日
     * @updateDate 2017年12月30日
     */
    void generateNormalRedpacket(Integer id, GenerateNormalRedpacketParam param) throws WrongOperationException;
    
    /**
     * 批量保存红包
     * 
     * @author jiangxinjun
     * @createDate 2018年1月4日
     * @updateDate 2018年1月4日
     */
    void batchSaveNormalRedpacket(List<HelpRedpacketAttendDetailDO> batchList);
    
    /**
     * 获取已经分配的普通红包总金额
     * 
     * @author jiangxinjun
     * @createDate 2018年1月4日
     * @updateDate 2018年1月4日
     */
    BigDecimal getNormalRedpacketTotalAmount(Integer id);
    
    /**
     * 重新生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的重新生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    void againGenerateNormalRedpacket(Integer id, GenerateNormalRedpacketParam param);
    
    /**
     * 继续生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的继续生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    void continueGenerateNormalRedpacket(Integer id);
    
    /**
     * 获取活动列表
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    Page<HelpRedpacketActivityBO> list(RedpacketActivityQueryParam param);
    
    /**
     * 保存红包活动
     * 
     * @param param
     *            保存参数
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    void save(HelpRedpacketActivitySaveParam param);
    
    /**
     * 获取开启的活动列表
     * 
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    List<HelpRedpacketActivityBO> openActivityList();
    
    /**
     * 获取预期的红包金额
     * @param activityId
     * @param excludeIds
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月30日
     * @updateDate 2018年1月30日
     */
    ExpectedRedpacketAmountBO expectedRedpacketAmount(Integer activityId, List<Long> excludeIds);
    
    /**
     * 获取疑似异常账号分配的总金额
     * 
     * @param id
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月1日
     * @updateDate 2018年3月1日
     */
    BigDecimal getAbnormalRedpacketTotalAmount(Integer id);
}
