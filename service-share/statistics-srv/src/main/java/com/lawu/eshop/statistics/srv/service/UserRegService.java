package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.param.UserRegAreaParam;
import com.lawu.eshop.statistics.param.UserRegParam;
import com.lawu.eshop.statistics.srv.bo.ReportUserRegAreaBO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportNewDateDOView;
import com.lawu.eshop.statistics.srv.domain.extend.ReportUserRegDOView;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
public interface UserRegService {

    /**
     * 按日统计用户注册量
     *
     * @param memberCount
     * @param merchantCount
     */
    void saveUserRegDaily(Integer memberCount, Integer merchantCount);

    /**
     * 按月统计用户注册量
     *
     * @param memberCount
     * @param merchantCount
     */
    void saveUserRegMonth(Integer memberCount, Integer merchantCount);

    /**
     * 按市级更新用户注册量
     *
     * @param param
     */
    void updateUserRegArea(UserRegAreaParam param);

    /**
     * 查询日统计列表
     *
     * @param param
     * @return
     */
    List<ReportUserRegDOView> getReportUserRegDaily(UserRegParam param);

    /**
     * 查询月统计列表
     *
     * @param param
     * @return
     */
    List<ReportUserRegDOView> getReportUserRegMonth(UserRegParam param);

    /**
     * 查询区域统计列表
     *
     * @return
     */
    List<ReportUserRegAreaBO> getReportUserRegArea();

    void addUserRegAreaDaily(UserRegAreaParam userRegAreaParam);

    void addUserRegAreaMonth(UserRegAreaParam userRegAreaParam);
    
    /**
     * 获取日统计最后一条数据统计时间
     * @author zhangrc
     * @date 2017/09/08
     * @return
     */
    ReportNewDateDOView getReportDateUserRegDaily();
    
    
    /**
     * 获取月统计最后一条数据统计时间
     * @author zhangrc
     * @date 2017/09/08
     * @return
     */
    ReportNewDateDOView getReportDateUserRegMonth();
}
