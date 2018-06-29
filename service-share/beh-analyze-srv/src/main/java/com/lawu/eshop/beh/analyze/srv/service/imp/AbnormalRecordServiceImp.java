package com.lawu.eshop.beh.analyze.srv.service.imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.param.AbnormalAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalParam;
import com.lawu.eshop.beh.analyze.srv.bo.AbnormalBO;
import com.lawu.eshop.beh.analyze.srv.convert.AbnormalConverter;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDOExample;
import com.lawu.eshop.beh.analyze.srv.mapper.InviteAbnormalDecideRecordDOMapper;
import com.lawu.eshop.beh.analyze.srv.service.AbnormalRecordService;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.synchronization.lock.impl.LockConstant;
import com.lawu.eshop.synchronization.lock.impl.LockService;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
@Service
public class AbnormalRecordServiceImp implements AbnormalRecordService {
    @Autowired
    private InviteAbnormalDecideRecordDOMapper inviteAbnormalDecideRecordDOMapper;

    @Autowired
    private LockService lockService;

    @Override
    public Page<AbnormalBO> getAbnormalRecord(AbnormalParam param) {
        InviteAbnormalDecideRecordDOExample example = new InviteAbnormalDecideRecordDOExample();
        InviteAbnormalDecideRecordDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsAbnormalEqualTo(true);
        if (StringUtils.isNotEmpty(param.getAccount())) {
            criteria.andAccountEqualTo(param.getAccount());
        }
        if (param.getProcessType() != null) {
            criteria.andProcessTypeEqualTo(param.getProcessType().getVal());
        }
        if (param.getUserType() != null) {
            criteria.andTypeEqualTo(param.getUserType().getValue());
        }
        Page<AbnormalBO> boPage = new Page<>();
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

        boPage.setCurrentPage(param.getCurrentPage());
        boPage.setTotalCount((int)inviteAbnormalDecideRecordDOMapper.countByExample(example));
        List<InviteAbnormalDecideRecordDO> list = inviteAbnormalDecideRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        boPage.setRecords(AbnormalConverter.convertBOS(list));
        return boPage;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editAbnormalRecord(AbnormalJobAddParam param) {
        InviteAbnormalDecideRecordDOExample example = new InviteAbnormalDecideRecordDOExample();
        example.createCriteria().andUserNumEqualTo(param.getUserNum()).andProcessTypeEqualTo(ProcessEnum.NORMAL.getVal());
        List<InviteAbnormalDecideRecordDO> list = inviteAbnormalDecideRecordDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
            recordDO.setIsIpHf(true);
            recordDO.setUserNum(param.getUserNum());
            recordDO.setAccount(param.getAccount());
            recordDO.setIsAbnormal(false);
            recordDO.setType(param.getType().getValue());
            recordDO.setGmtCreate(new Date());
            recordDO.setGmtModified(new Date());
            inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
        } else {
            if (!list.get(0).getIsIpHf()) {
                InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
                recordDO.setIsIpHf(true);
                recordDO.setId(list.get(0).getId());
                AbnormalAddParam addParam = new AbnormalAddParam();
                addParam.setEarlyHf(list.get(0).getIsEarlyHf());
                addParam.setShortHf(list.get(0).getIsShortHf());
                addParam.setManyShortHf(list.get(0).getIsManyShortHf());
                addParam.setLongHf(list.get(0).getIsLongHf());
                addParam.setManyLongHf(list.get(0).getIsManyLongHf());
                addParam.setOneDayHf(list.get(0).getIsOneDayHf());
                addParam.setEarlyHf(list.get(0).getIsEarlyHf());
                addParam.setIpHf(true);
                if (addParam.isAbnormal()) {
                    recordDO.setIsAbnormal(true);
                }else{
                    recordDO.setIsAbnormal(false);
                }
                recordDO.setGmtModified(new Date());
                inviteAbnormalDecideRecordDOMapper.updateByPrimaryKeySelective(recordDO);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdateAbnormalRecord(AbnormalAddParam param) {
        InviteAbnormalDecideRecordDOExample example = new InviteAbnormalDecideRecordDOExample();
        example.createCriteria().andUserNumEqualTo(param.getUserNum()).andProcessTypeEqualTo(ProcessEnum.NORMAL.getVal());
        List<InviteAbnormalDecideRecordDO> list = inviteAbnormalDecideRecordDOMapper.selectByExample(example);
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setType(param.getType().getValue());
        recordDO.setUserNum(param.getUserNum());
        recordDO.setAccount(param.getAccount());
        if(param.getShortHf()){
            recordDO.setIsShortHf(param.getShortHf());
        }
        if (param.getManyShortHf()) {
            recordDO.setIsManyShortHf(param.getManyShortHf());
        }
        if (param.getLongHf()) {
            recordDO.setIsLongHf(param.getLongHf());
        }
        if (param.getManyLongHf()) {
            recordDO.setIsManyLongHf(param.getManyLongHf());
        }
        if (param.getOneDayHf()) {
            recordDO.setIsOneDayHf(param.getOneDayHf());
        }
        if (param.getEarlyHf()) {
            recordDO.setIsEarlyHf(param.getEarlyHf());
        }
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setGmtModified(new Date());
        if (param.isAbnormal()) {
            recordDO.setIsAbnormal(true);
            //发短信OK
            //TODO
            recordDO.setIsNoticed(true);
        } else {
            recordDO.setIsAbnormal(false);
            recordDO.setIsNoticed(false);
        }
        if (list.isEmpty()) {
            try {
                boolean isLock = lockService.tryLock(0,1000,LockConstant.LockModule.LOCK_BEH_ANALYZE_SRV, LockConstant.CREATE_ABNORMAL_RECORD.concat(param.getUserNum()));
                if (!isLock) {
                    return;
                }
                List<InviteAbnormalDecideRecordDO> olds = inviteAbnormalDecideRecordDOMapper.selectByExample(example);
                if (olds.isEmpty()) {
                    recordDO.setGmtCreate(new Date());
                    inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
                } else {
                    recordDO.setId(olds.get(0).getId());
                    if(isExistAbnormal(param,olds)){
                        recordDO.setIsAbnormal(true);
                        //发短信OK
                        //TODO
                        recordDO.setIsNoticed(true);
                    }
                    inviteAbnormalDecideRecordDOMapper.updateByPrimaryKeySelective(recordDO);
                }
            }catch (Exception ex){
                throw new WrongOperationException("新增修改异常账户记录异常---------");
            }

        } else {
            recordDO.setId(list.get(0).getId());
            if(isExistAbnormal(param,list)){
                recordDO.setIsAbnormal(true);
                //发短信OK
                //TODO
                recordDO.setIsNoticed(true);
            }
            inviteAbnormalDecideRecordDOMapper.updateByPrimaryKeySelective(recordDO);
        }
    }

    @Override
    public void addActiveAbnormalInfo(AbnormalJobAddParam addParam) {
        InviteAbnormalDecideRecordDOExample example = new InviteAbnormalDecideRecordDOExample();
        example.createCriteria().andUserNumEqualTo(addParam.getUserNum()).andProcessTypeEqualTo(ProcessEnum.NORMAL.getVal());
        List<InviteAbnormalDecideRecordDO> list = inviteAbnormalDecideRecordDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
            recordDO.setIsActivityAbnormal(true);
            recordDO.setUserNum(addParam.getUserNum());
            recordDO.setAccount(addParam.getAccount());
            recordDO.setIsAbnormal(true);
            recordDO.setType(addParam.getType().getValue());
            recordDO.setGmtCreate(new Date());
            recordDO.setGmtModified(new Date());
            inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
        } else {
            if (!list.get(0).getIsActivityAbnormal()) {
                InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
                recordDO.setIsActivityAbnormal(true);
                recordDO.setId(list.get(0).getId());
                recordDO.setGmtModified(new Date());
                inviteAbnormalDecideRecordDOMapper.updateByPrimaryKeySelective(recordDO);
            }
        }
    }

    private Boolean isExistAbnormal(AbnormalAddParam param, List<InviteAbnormalDecideRecordDO> recordDOS) {
        if (!recordDOS.isEmpty()) {
            if (recordDOS.get(0).getIsShortHf()) {
                param.setShortHf(true);
            }
            if (recordDOS.get(0).getIsManyShortHf()) {
                param.setManyShortHf(true);
            }
            if (recordDOS.get(0).getIsLongHf()) {
                param.setLongHf(true);
            }
            if (recordDOS.get(0).getIsManyLongHf()) {
                param.setManyLongHf(true);
            }
            if (recordDOS.get(0).getIsEarlyHf()) {
                param.setEarlyHf(true);
            }
            if (recordDOS.get(0).getIsIpHf()) {
                param.setIpHf(true);
            }
        }
        return param.isAbnormal();
    }


}
