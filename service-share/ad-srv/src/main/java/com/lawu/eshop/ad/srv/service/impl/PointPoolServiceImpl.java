package com.lawu.eshop.ad.srv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.srv.bo.GetRedPacketBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.AdDOExample;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.domain.PointPoolDOExample;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.eshop.ad.srv.mapper.extend.PointPoolDOMapperExtend;
import com.lawu.eshop.ad.srv.service.PointPoolService;
import com.lawu.eshop.common.constants.AdTypeEnum;

@Service
public class PointPoolServiceImpl implements PointPoolService {
	
	@Autowired 
	private PointPoolDOMapperExtend pointPoolDOMapperExtend;
	
	@Autowired
	private PointPoolDOMapper pointPoolDOMapper;

	@Autowired
	private AdDOMapper adDOMapper;

	@Override
	public List<PointPoolDO> selectMemberList(Long adId) {
		 List<PointPoolDO>  pointPoolDO=pointPoolDOMapperExtend.selectMember(adId);
		return pointPoolDO;
	}

	@Override
	public Boolean selectStatusByMember(Long adId,Long memberId) {
		PointPoolDOExample example=new PointPoolDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andTypeEqualTo(new Byte("1")).andAdIdEqualTo(adId);
		Long  count=pointPoolDOMapper.countByExample(example);
		if(count != null && count.intValue() > 0){
			return true;
		}
		return false;
	}

	@Override
	public GetRedPacketBO isGetRedPacket(Long merchantId, String userNum) {

		AdDOExample adDOExample = new AdDOExample();
		adDOExample.createCriteria().andMerchantIdEqualTo(merchantId).andTypeEqualTo(AdTypeEnum.AD_TYPE_PACKET.getVal()).andStatusEqualTo(AdStatusEnum.AD_STATUS_ADD.val);
		List<AdDO> list = adDOMapper.selectByExample(adDOExample);
		GetRedPacketBO bo=new GetRedPacketBO();
		if(list.isEmpty()){
			bo.setNullRedPacket(true);
			return bo;
		}else{
			PointPoolDOExample example2=new PointPoolDOExample();
			example2.createCriteria().andAdIdEqualTo(list.get(0).getId()).andMemberNumEqualTo(userNum);
			Long count=pointPoolDOMapper.countByExample(example2);
			if(count.intValue()>0){
				bo.setGetRedPacket(true);
			}else{
				bo.setGetRedPacket(false);
			}
			return bo;
		}

	}

	


}
