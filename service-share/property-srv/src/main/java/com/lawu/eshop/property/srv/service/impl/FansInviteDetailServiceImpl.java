package com.lawu.eshop.property.srv.service.impl;

import com.lawu.eshop.property.param.ListFansInviteDetailParam;
import com.lawu.eshop.property.srv.bo.FansInviteDetailBO;
import com.lawu.eshop.property.srv.converter.FansInviteDetailConverter;
import com.lawu.eshop.property.srv.domain.FansInviteDetailDO;
import com.lawu.eshop.property.srv.domain.FansInviteDetailDOExample;
import com.lawu.eshop.property.srv.mapper.FansInviteDetailDOMapper;
import com.lawu.eshop.property.srv.service.FansInviteDetailService;
import com.lawu.framework.core.page.Page;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/24.
 */
@Service
public class FansInviteDetailServiceImpl implements FansInviteDetailService {

    @Autowired
    private FansInviteDetailDOMapper fansInviteDetailDOMapper;

    @Override
    public Page<FansInviteDetailBO> listFansInviteDetail(Long merchantId, ListFansInviteDetailParam listFansInviteDetailParam) {
        FansInviteDetailDOExample example = new FansInviteDetailDOExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andMerchantIdEqualTo(merchantId);

        RowBounds rowBounds = new RowBounds(listFansInviteDetailParam.getOffset(), listFansInviteDetailParam.getPageSize());

        Page<FansInviteDetailBO> page = new Page<>();
        page.setTotalCount(fansInviteDetailDOMapper.countByExample(example));
        page.setCurrentPage(listFansInviteDetailParam.getCurrentPage());

        List<FansInviteDetailDO> fansInviteDetailDOS = fansInviteDetailDOMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        page.setRecords(FansInviteDetailConverter.convertBO(fansInviteDetailDOS));
        return page;
    }

    @Override
    public FansInviteDetailBO getFansInviteDetailByPointNum(String pointNum) {
        FansInviteDetailDOExample example = new FansInviteDetailDOExample();
        example.createCriteria().andPointNumEqualTo(pointNum);
        List<FansInviteDetailDO> transactionDetailFansInviteDOList = fansInviteDetailDOMapper.selectByExample(example);
        if (transactionDetailFansInviteDOList.isEmpty()) {
            return null;
        }
        return FansInviteDetailConverter.convertBO(transactionDetailFansInviteDOList.get(0));
    }
}
