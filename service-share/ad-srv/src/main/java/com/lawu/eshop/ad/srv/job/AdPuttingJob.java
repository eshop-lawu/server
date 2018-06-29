package com.lawu.eshop.ad.srv.job;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.srv.converter.AdConverter;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.AdDOExample;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.solr.service.AdSolrService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.solr.impl.entity.AdSolr;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * @author zhangrc
 * @date 2017/4/14
 */
public class AdPuttingJob extends AbstractPageJob<AdDO> {
    
    @Autowired
	private AdDOMapper adDOMapper;

    @Autowired
    private AdSolrService adSolrService;
	
	@Autowired
	@Qualifier("adMerchantAddPointTransactionMainServiceImpl")
	private TransactionMainService<Reply> matransactionMainAddService;
    
    @Override
	public List<AdDO> queryPage(int offset, int pageSize) {
    	AdDOExample example = new AdDOExample();
		example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_ADD.val).andTypeNotEqualTo(AdTypeEnum.AD_TYPE_PACKET.getVal());
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<AdDO> list = adDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		return list;
	}

    @Override
    public void executeSingle(AdDO adDO) {
        Date date = new Date();
        if (adDO.getBeginTime().getTime() <= date.getTime()) {
            adDO.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
            adDO.setGmtModified(date);
            adDOMapper.updateByPrimaryKey(adDO);
            AdSolr solrBean = AdConverter.convert(adDO);
            adSolrService.save(solrBean);
        }
    }
	
	@Override
	public boolean continueWhenSinglePageFail() {
		return true;
	}

	@Override
	public boolean isStatusData() {
		return false;
	}
}
