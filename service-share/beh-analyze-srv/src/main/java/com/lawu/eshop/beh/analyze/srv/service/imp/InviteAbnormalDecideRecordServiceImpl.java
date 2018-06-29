package com.lawu.eshop.beh.analyze.srv.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.srv.bo.InviteAbnormalDecideRecordBO;
import com.lawu.eshop.beh.analyze.srv.convert.InviteAbnormalDecideRecordConverter;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.beh.analyze.srv.mapper.InviteAbnormalDecideRecordDOMapper;
import com.lawu.eshop.beh.analyze.srv.service.InviteAbnormalDecideRecordService;

/**
 * 注册异常判定记录服务实现类
 * 
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
@Service
public class InviteAbnormalDecideRecordServiceImpl implements InviteAbnormalDecideRecordService {

    @Autowired
    private InviteAbnormalDecideRecordDOMapper inviteAbnormalDecideRecordDOMapper;
    
    @Override
    public InviteAbnormalDecideRecordBO get(Long id) {
        InviteAbnormalDecideRecordDO inviteAbnormalDecideRecordDO = inviteAbnormalDecideRecordDOMapper.selectByPrimaryKey(id);
        return InviteAbnormalDecideRecordConverter.convert(inviteAbnormalDecideRecordDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void notProcessed(Long id) {
        InviteAbnormalDecideRecordDO record = new InviteAbnormalDecideRecordDO();
        record.setId(id);
        record.setProcessType(ProcessEnum.NOT_HANDLE.getVal());
        inviteAbnormalDecideRecordDOMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void freeze(Long id) {
        InviteAbnormalDecideRecordDO record = new InviteAbnormalDecideRecordDO();
        record.setId(id);
        record.setProcessType(ProcessEnum.FREEZE.getVal());
        inviteAbnormalDecideRecordDOMapper.updateByPrimaryKeySelective(record);
    }
}
