package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.user.param.UserFreezeRecordParam;
import com.lawu.eshop.user.srv.domain.UserFreezeRecordDO;
import com.lawu.eshop.user.srv.mapper.UserFreezeRecordDOMapper;
import com.lawu.eshop.user.srv.service.UserFreezeRecordService;

/**
 * @author meishuquan
 * @date 2017/9/11.
 */
@Service
public class UserFreezeRecordServiceImpl implements UserFreezeRecordService {

    @Autowired
    private UserFreezeRecordDOMapper userFreezeRecordDOMapper;

    @Override
    public void saveUserFreezeRecord(UserFreezeRecordParam param) {
        UserFreezeRecordDO userFreezeRecordDO = new UserFreezeRecordDO();
        userFreezeRecordDO.setUserNum(param.getUserNum());
        userFreezeRecordDO.setAccount(param.getAccount());
        userFreezeRecordDO.setUserType(param.getUserType());
        userFreezeRecordDO.setCause(param.getCause());
        userFreezeRecordDO.setGmtCreate(new Date());
        userFreezeRecordDOMapper.insertSelective(userFreezeRecordDO);
    }
}
