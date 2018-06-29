package com.lawu.eshop.jobs.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.jobs.service.CollectionUserRegService;
import com.lawu.eshop.jobs.service.RegionService;
import com.lawu.eshop.jobs.service.UserRegService;
import com.lawu.eshop.jobs.service.UserRegStatisticsService;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.statistics.dto.ReportNewDateDTO;
import com.lawu.eshop.statistics.param.UserRegAreaParam;
import com.lawu.eshop.user.param.CollectionUserRegParam;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
@Service
public class UserRegStatisticsServiceImpl implements UserRegStatisticsService {

    @Autowired
    private CollectionUserRegService collectionUserRegService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private UserRegService userRegService;

    @Override
    public void executeCollectionUserRegDaily() {
        Result<ReportNewDateDTO> result = userRegService.getReportDateUserRegDaily();
        Date newReportDate = result.getModel().getGmtReport();

        Calendar calendar = Calendar.getInstance();
        if (DateUtil.daysOfTwo(newReportDate) == 0) {
            calendar.add(Calendar.DATE, -1);
            CollectionUserRegParam param = new CollectionUserRegParam();
            param.setYear(calendar.get(Calendar.YEAR));
            param.setMonth(calendar.get(Calendar.MONTH) + 1);
            param.setDay(calendar.get(Calendar.DATE));
            Result<Integer> memberResult = collectionUserRegService.collectionMemberRegDaily(param);
            Result<Integer> merchantResult = collectionUserRegService.collectionMerchantRegDaily(param);
            userRegService.saveUserRegDaily(memberResult.getModel(), merchantResult.getModel());
            return;
        }

        for (int i = 1; i < DateUtil.daysOfTwo(newReportDate); i++) {
            calendar.add(Calendar.DATE, -i);
            CollectionUserRegParam param = new CollectionUserRegParam();
            param.setYear(calendar.get(Calendar.YEAR));
            param.setMonth(calendar.get(Calendar.MONTH) + 1);
            param.setDay(calendar.get(Calendar.DATE));
            Result<Integer> memberResult = collectionUserRegService.collectionMemberRegDaily(param);
            Result<Integer> merchantResult = collectionUserRegService.collectionMerchantRegDaily(param);
            userRegService.saveUserRegDaily(memberResult.getModel(), merchantResult.getModel());
        }

    }

    @Override
    public void executeCollectionUserRegMonth() {
        Result<ReportNewDateDTO> result = userRegService.getReportDateUserRegMonth();
        Date newReportDate = result.getModel().getGmtReport();

        Calendar calendar = Calendar.getInstance();
        if (DateUtil.monthsOfTwo(newReportDate) == 0) {
            calendar.add(Calendar.MONTH, -1);
            CollectionUserRegParam param = new CollectionUserRegParam();
            param.setYear(calendar.get(Calendar.YEAR));
            param.setMonth(calendar.get(Calendar.MONTH) + 1);
            Result<Integer> memberResult = collectionUserRegService.collectionMemberRegMonth(param);
            Result<Integer> merchantResult = collectionUserRegService.collectionMerchantRegMonth(param);
            userRegService.saveUserRegMonth(memberResult.getModel(), merchantResult.getModel());
            return;
        }

        for (int i = 1; i < DateUtil.monthsOfTwo(newReportDate); i++) {
            calendar.add(Calendar.MONTH, -i);
            CollectionUserRegParam param = new CollectionUserRegParam();
            param.setYear(calendar.get(Calendar.YEAR));
            param.setMonth(calendar.get(Calendar.MONTH) + 1);
            Result<Integer> memberResult = collectionUserRegService.collectionMemberRegMonth(param);
            Result<Integer> merchantResult = collectionUserRegService.collectionMerchantRegMonth(param);
            userRegService.saveUserRegMonth(memberResult.getModel(), merchantResult.getModel());
        }
    }

    @Override
    public void executeCollectionUserRegArea() {
        Result<List<RegionDTO>> regionResult = regionService.getRegionLevelTwo();
        if (regionResult.getModel().isEmpty()) {
            return;
        }
        CollectionUserRegParam param = new CollectionUserRegParam();
        UserRegAreaParam userRegAreaParam = new UserRegAreaParam();
        for (RegionDTO regionDTO : regionResult.getModel()) {
            param.setRegionPath(regionDTO.getPath());
            Result<Integer> memberResult = collectionUserRegService.collectionMemberRegArea(param);
            Result<Integer> merchantCommonResult = collectionUserRegService.collectionMerchantCommonRegArea(param);
            Result<Integer> merchantEntityResult = collectionUserRegService.collectionMerchantEntityRegArea(param);
            userRegAreaParam.setMemberCount(memberResult.getModel());
            userRegAreaParam.setMerchantCommonCount(merchantCommonResult.getModel());
            userRegAreaParam.setMerchantEntityCount(merchantEntityResult.getModel());
            userRegAreaParam.setMerchantCount(merchantCommonResult.getModel() + merchantEntityResult.getModel());
            userRegAreaParam.setCityId(regionDTO.getId());
            userRegService.updateUserRegArea(userRegAreaParam);
        }
    }

    /**
     * 按市日统计注册数量
     */
    @Override
    public void executeCollectionUserRegAreaDaily() {
        //查询二级城市Path
        Result<List<RegionDTO>> regionResult = regionService.getRegionLevelTwo();
        if (regionResult.getModel().isEmpty()) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setDay(calendar.get(Calendar.DATE));
        for (RegionDTO regionDTO : regionResult.getModel()) {
            param.setRegionPath(regionDTO.getPath());
            //查询日用户总数
            Result<Integer> memberResult = collectionUserRegService.collectionMemberRegDailyArea(param);
            //查询日普通店铺总数
            Result<Integer> merchantCommonResult = collectionUserRegService.collectionMerchantNormalRegDailyArea(param);
            //查询日实体店铺总数
            Result<Integer> merchantEntityResult = collectionUserRegService.collectionMerchantEntityRegDailyArea(param);
            if ((memberResult.getModel() + merchantCommonResult.getModel() + merchantEntityResult.getModel()) > 0) {

                UserRegAreaParam userRegAreaParam = new UserRegAreaParam();
                userRegAreaParam.setMemberCount(memberResult.getModel());
                userRegAreaParam.setMerchantCommonCount(merchantCommonResult.getModel());
                userRegAreaParam.setMerchantEntityCount(merchantEntityResult.getModel());
                userRegAreaParam.setMerchantCount(merchantCommonResult.getModel() + merchantEntityResult.getModel());
                userRegAreaParam.setCityId(regionDTO.getId());
                userRegAreaParam.setName(regionDTO.getName());
                userRegService.addUserRegAreaDaily(userRegAreaParam);

            }
        }
    }

    /**
     * 月统计地区用户注册量
     */
    @Override
    public void executeCollectionUserRegAreaMonth() {
        //查询二级城市Path
        Result<List<RegionDTO>> regionResult = regionService.getRegionLevelTwo();
        if (regionResult.getModel().isEmpty()) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);

        for (RegionDTO regionDTO : regionResult.getModel()) {
            param.setRegionPath(regionDTO.getPath());
            //查询日用户总数
            Result<Integer> memberResult = collectionUserRegService.collectionMemberRegMonthArea(param);
            //查询日普通店铺总数
            Result<Integer> merchantCommonResult = collectionUserRegService.collectionMerchantNormalRegMonthArea(param);
            //查询日实体店铺总数
            Result<Integer> merchantEntityResult = collectionUserRegService.collectionMerchantEntityRegMonthArea(param);
            if ((memberResult.getModel() + merchantCommonResult.getModel() + merchantEntityResult.getModel()) > 0) {

                UserRegAreaParam userRegAreaParam = new UserRegAreaParam();
                userRegAreaParam.setMerchantCommonCount(merchantCommonResult.getModel());
                userRegAreaParam.setMemberCount(memberResult.getModel());
                userRegAreaParam.setMerchantEntityCount(merchantEntityResult.getModel());
                userRegAreaParam.setMerchantCount(merchantCommonResult.getModel() + merchantEntityResult.getModel());
                userRegAreaParam.setCityId(regionDTO.getId());
                userRegAreaParam.setName(regionDTO.getName());
                userRegService.addUserRegAreaMonth(userRegAreaParam);

            }
        }

    }

}
