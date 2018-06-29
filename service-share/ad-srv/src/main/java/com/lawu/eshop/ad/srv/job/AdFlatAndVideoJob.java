package com.lawu.eshop.ad.srv.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

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
 * 修改平面、视频两个星期下架
 * @author zhangrc
 *
 */
public class AdFlatAndVideoJob extends AbstractPageJob<AdDO>{
    
    @Autowired
	private AdDOMapper adDOMapper;
    
    @Autowired
    private AdSolrService adSolrService;
    
    @Autowired
    private MessageProducerService messageProducerService;



	@Override
	public List<AdDO> queryPage(int offset, int pageSize) {
		AdDOExample example = new AdDOExample();
		List<Byte> bytes = new ArrayList<>();
		bytes.add(AdTypeEnum.AD_TYPE_FLAT.getVal());
		bytes.add(AdTypeEnum.AD_TYPE_VIDEO.getVal());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -14); // 设置为14天前
		Date before14days = calendar.getTime();
		example.createCriteria().andStatusEqualTo(AdStatusEnum.AD_STATUS_PUTING.val).andTypeIn(bytes)
				.andBeginTimeLessThan(before14days);
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<AdDO> list = adDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		return list;
	}

	@Override
	public void executeSingle(AdDO adDO) {
		if (adDO.getHits() >= adDO.getAdCount()) {
			adDO.setStatus(AdStatusEnum.AD_STATUS_PUTED.val); // 投放结束
			adDO.setGmtModified(new Date());
			adDOMapper.updateByPrimaryKey(adDO);
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
