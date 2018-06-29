package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.srv.bo.VerifyCodeBO;
import com.lawu.eshop.mall.srv.converter.VerifyCodeConverter;
import com.lawu.eshop.mall.srv.domain.VerifyCodeDO;
import com.lawu.eshop.mall.srv.domain.VerifyCodeDOExample;
import com.lawu.eshop.mall.srv.mapper.VerifyCodeDOMapper;
import com.lawu.eshop.mall.srv.service.VerifyCodeService;

/**
 * @author meishuquan
 * @date 2017/3/28
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private VerifyCodeDOMapper verifyCodeDOMapper;

    @Override
    public Long savePicCode(String mobile, String picCode, VerifyCodePurposeEnum purpose) {
        VerifyCodeDO verifyCodeDO = new VerifyCodeDO();
        verifyCodeDO.setMobile(mobile);
        verifyCodeDO.setCode(picCode);
        verifyCodeDO.setPurpose(purpose.val);
        verifyCodeDO.setGmtCreate(new Date());
        verifyCodeDOMapper.insertSelective(verifyCodeDO);
        return verifyCodeDO.getId();
    }

    @Override
    public VerifyCodeBO getVerifyCodeById(Long id) {
        VerifyCodeDO verifyCodeDO = verifyCodeDOMapper.selectByPrimaryKey(id);
        return VerifyCodeConverter.convertBO(verifyCodeDO);
    }

    @Override
    public VerifyCodeBO getLastPicVerifyCode(String mobile) {
        VerifyCodeDOExample verifyCodeDOExample = new VerifyCodeDOExample();
        verifyCodeDOExample.setOrderByClause("gmt_create desc");
        verifyCodeDOExample.createCriteria().andMobileEqualTo(mobile).andPurposeEqualTo((byte)0);
        List<VerifyCodeDO> verifyCodeDOList = verifyCodeDOMapper.selectByExample(verifyCodeDOExample);
        return verifyCodeDOList.isEmpty() ? null : VerifyCodeConverter.convertBO(verifyCodeDOList.get(0));
    }
}
