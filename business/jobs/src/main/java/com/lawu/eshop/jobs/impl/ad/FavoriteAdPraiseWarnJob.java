package com.lawu.eshop.jobs.impl.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.ad.dto.FavoriteAdPraiseWarnDTO;
import com.lawu.eshop.ad.param.PraiseWarnParam;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.jobs.service.FavoriteAdService;
import com.lawu.eshop.jobs.service.MessageService;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageJob;

/**
 * @author zhangrc
 * @date 2017/7/27
 */
public class FavoriteAdPraiseWarnJob extends AbstractPageJob<FavoriteAdPraiseWarnDTO> {

    @Autowired 
	private FavoriteAdService favoriteAdService;
	
	@Autowired
    private MessageService messageService;

	@Override
	public List<FavoriteAdPraiseWarnDTO> queryPage(int offset, int pageSize) {
		PraiseWarnParam param = new PraiseWarnParam();
		param.setOffset(offset);
		param.setPageSize(pageSize);
		Result<List<FavoriteAdPraiseWarnDTO>>  result = favoriteAdService.selectFavoriteAdPraise(param);
		 
		List<FavoriteAdPraiseWarnDTO> list= result.getModel();
		
		return list;
	}

	@Override
	public void executeSingle(FavoriteAdPraiseWarnDTO favoriteAdPraiseWarnDTO) {
		MessageInfoParam messageInfoParam = new MessageInfoParam();
		MessageTempParam messageTempParam = new MessageTempParam();
		messageTempParam.setAdName(favoriteAdPraiseWarnDTO.getTitle());
		messageTempParam.setAdTypeName("抢赞");
		messageInfoParam.setRelateId(favoriteAdPraiseWarnDTO.getAdId());
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_AD_PRAISE_NOTICE);
		messageInfoParam.setMessageParam(messageTempParam);
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		favoriteAdService.updateIsSend(favoriteAdPraiseWarnDTO.getId());
		 
		messageService.saveMessage(favoriteAdPraiseWarnDTO.getMemberNum(), messageInfoParam); 
	}

	@Override
	public boolean isStatusData() {
		return true;
	}

	@Override
	public boolean continueWhenSinglePageFail() {
		return true;
	}
}
