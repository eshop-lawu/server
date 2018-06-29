package com.lawu.eshop.user.srv.service.impl;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.param.AliUserInfoDataParam;
import com.lawu.eshop.user.srv.domain.AliUserInfoDO;
import com.lawu.eshop.user.srv.domain.AliUserInfoDOExample;
import com.lawu.eshop.user.srv.mapper.AliUserInfoDOMapper;
import com.lawu.eshop.user.srv.service.AliUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AliUserInfoServiceImpl implements AliUserInfoService {

    @Autowired
    private AliUserInfoDOMapper aliUserInfoDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(AliUserInfoDataParam aliUserInfoDataParam) {
        AliUserInfoDOExample aliUserInfoDOExample = new AliUserInfoDOExample();
        aliUserInfoDOExample.createCriteria().andUserNumEqualTo(aliUserInfoDataParam.getUserNum()).andAliUserIdEqualTo(aliUserInfoDataParam.getAliUserInfoParam().getAliUserId());
        List<AliUserInfoDO> aliUserInfoDOList = aliUserInfoDOMapper.selectByExample(aliUserInfoDOExample);

        AliUserInfoDO aliUserInfoDO = new AliUserInfoDO();
        aliUserInfoDO.setUserNum(aliUserInfoDataParam.getUserNum());
        aliUserInfoDO.setUserType(aliUserInfoDataParam.getUserTypeEnum().getValue());
        aliUserInfoDO.setAliUserId(aliUserInfoDataParam.getAliUserInfoParam().getAliUserId());
        aliUserInfoDO.setAliUserInfo(JSON.toJSONString(aliUserInfoDataParam.getAliUserInfoParam()));
        if (aliUserInfoDOList == null || aliUserInfoDOList.isEmpty()) {
            aliUserInfoDO.setGmtCreate(new Date());
            aliUserInfoDOMapper.insertSelective(aliUserInfoDO);
        } else {
            aliUserInfoDO.setId(aliUserInfoDOList.get(0).getId());
            aliUserInfoDO.setGmtModify(new Date());
            aliUserInfoDOMapper.updateByPrimaryKeySelective(aliUserInfoDO);
        }
        return ResultCode.SUCCESS;
    }
}
