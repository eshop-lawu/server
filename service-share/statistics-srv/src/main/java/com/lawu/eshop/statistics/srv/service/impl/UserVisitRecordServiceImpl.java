package com.lawu.eshop.statistics.srv.service.impl;

import com.lawu.eshop.statistics.param.UserVisitRecordParam;
import com.lawu.eshop.statistics.srv.domain.UserVisitRecordDO;
import com.lawu.eshop.statistics.srv.mapper.UserVisitRecordDOMapper;
import com.lawu.eshop.statistics.srv.service.UserVisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/7/4.
 */
@Service
public class UserVisitRecordServiceImpl implements UserVisitRecordService {
    @Autowired
    private UserVisitRecordDOMapper userVisitRecordDOMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserVisitRecord(UserVisitRecordParam userVisitRecordParam) {
        UserVisitRecordDO userVisitRecordDO = new UserVisitRecordDO();
        userVisitRecordDO.setUserNum(userVisitRecordParam.getUserNum());
        userVisitRecordDO.setAccount(userVisitRecordParam.getAccount());
        userVisitRecordDO.setCityName(userVisitRecordParam.getCityName());
        userVisitRecordDO.setVisitCount(userVisitRecordParam.getVisitCount());
        userVisitRecordDO.setCityId(userVisitRecordParam.getCityId());
        userVisitRecordDO.setVisitDate(userVisitRecordParam.getVisitDate());
        userVisitRecordDO.setUserType(userVisitRecordParam.getUserType());
        userVisitRecordDO.setGmtCreate(new Date());
        userVisitRecordDOMapper.insertSelective(userVisitRecordDO);
    }
}
