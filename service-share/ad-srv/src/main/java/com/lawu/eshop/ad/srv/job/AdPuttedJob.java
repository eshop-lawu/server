package com.lawu.eshop.ad.srv.job;

import java.util.Calendar;
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
import com.lawu.eshop.mq.dto.order.AdDownnNoticeNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.solr.impl.entity.AdSolr;
import com.lawu.jobsextend.AbstractPageJob;
import com.lawu.mq.message.MessageProducerService;

/**
 * @author zhangrc
 * @date 2017/4/14
 */
public class AdPuttedJob extends AbstractPageJob<AdDO> {
    
	@Autowired
	private AdDOMapper adDOMapper;

    @Autowired
    private AdSolrService adSolrService;
	
	@Autowired
	@Qualifier("adMerchantAddPointTransactionMainServiceImpl")
	private TransactionMainService<Reply> matransactionMainAddService;

	@Autowired
    private MessageProducerService messageProducerService;

	@Override
	public List<AdDO> queryPage(int offset, int pageSize) {
		AdDOExample example = new AdDOExample();
		example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_PUTING.val).andTypeEqualTo(AdTypeEnum.AD_TYPE_PRAISE.getVal());
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<AdDO> list = adDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		return list;
	}

	@Override
	public void executeSingle(AdDO adDO) {
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, -20);
		if ((nowTime.getTime().getTime() - adDO.getBeginTime().getTime()) > 0) {
			adDO.setStatus(AdStatusEnum.AD_STATUS_PUTED.val);
			adDO.setGmtModified(new Date());
			adDOMapper.updateByPrimaryKey(adDO);
			// 将没有领完的积分退还给用户
			if(adDO.getHits() < adDO.getAdCount()){
				matransactionMainAddService.sendNotice(adDO.getId());
			}
            AdSolr solrBean = AdConverter.convert(adDO);
            adSolrService.save(solrBean);

            AdDownnNoticeNotification adDownnNoticeNotification = new AdDownnNoticeNotification();
			adDownnNoticeNotification.setAdId(adDO.getId());
			adDownnNoticeNotification.setAdTitle(adDO.getTitle());
			adDownnNoticeNotification.setMerchantNum(adDO.getMerchantNum());
			adDownnNoticeNotification.setAdType(AdTypeEnum.getEnum(adDO.getType()).getName());
			messageProducerService.sendMessage(MqConstant.TOPIC_AD_SRV, MqConstant.TAG_TO_AD_DOWN_NOTICE, adDownnNoticeNotification);
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
