package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.param.WindowMessageParam;
import com.lawu.eshop.mall.query.OperatorWindowMessageQuery;
import com.lawu.eshop.mall.srv.bo.WindowMessageBO;
import com.lawu.eshop.mall.srv.converter.WindowMessageConverter;
import com.lawu.eshop.mall.srv.domain.WindowMessageDO;
import com.lawu.eshop.mall.srv.domain.WindowMessageDOExample;
import com.lawu.eshop.mall.srv.mapper.WindowMessageDOMapper;
import com.lawu.eshop.mall.srv.service.WindowMessageService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
@Service
public class WindowMessageServiceImpl implements WindowMessageService {

    @Autowired
    private WindowMessageDOMapper windowMessageDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveWindowMessage(WindowMessageParam param) {
        WindowMessageDO messageDO = new WindowMessageDO();
        messageDO.setImgPath(param.getImgPath());
        messageDO.setRelateId(param.getRelateId());
        messageDO.setType(param.getTypeEnum().getVal());
        messageDO.setStatus(param.getStatusEnum().getVal());
        messageDO.setGmtModified(new Date());
        if (param.getId() != null && param.getId() > 0) {
            messageDO.setId(param.getId());
            windowMessageDOMapper.updateByPrimaryKeySelective(messageDO);
        } else {
            messageDO.setGmtCreate(new Date());
            windowMessageDOMapper.insertSelective(messageDO);
        }
    }

    @Override
    public Page<WindowMessageBO> listOperatorWindowMessage(OperatorWindowMessageQuery query) {
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<WindowMessageDO> messageDOS = windowMessageDOMapper.selectByExampleWithRowbounds(null, rowBounds);

        Page<WindowMessageBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) windowMessageDOMapper.countByExample(null));
        page.setRecords(WindowMessageConverter.convertBOS(messageDOS));
        return page;
    }

    @Override
    public WindowMessageBO getWindowMessage(Long id) {
        WindowMessageDO messageDO = windowMessageDOMapper.selectByPrimaryKey(id);
        return WindowMessageConverter.convertBO(messageDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWindowMessageStatus(Long id, WindowMessageStatusEnum statusEnum) {
        WindowMessageDO messageDO = new WindowMessageDO();
        messageDO.setId(id);
        messageDO.setStatus(statusEnum.getVal());
        messageDO.setGmtModified(new Date());
        windowMessageDOMapper.updateByPrimaryKeySelective(messageDO);
    }

    @Override
    public List<WindowMessageBO> listWindowMessage() {
        WindowMessageDOExample example = new WindowMessageDOExample();
        example.createCriteria().andStatusEqualTo(WindowMessageStatusEnum.ENABLE.getVal());
        List<WindowMessageDO> messageDOS = windowMessageDOMapper.selectByExample(example);
        return WindowMessageConverter.convertBOS(messageDOS);
    }

}
