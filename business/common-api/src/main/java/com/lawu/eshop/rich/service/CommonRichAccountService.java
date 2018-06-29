package com.lawu.eshop.rich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.dto.IdentityInfoDTO;
import com.lawu.eshop.activity.dto.PersonalRichAccountDTO;
import com.lawu.eshop.activity.dto.RichDetailDTO;
import com.lawu.eshop.activity.dto.RichMyDiamondRecordInfoDTO;
import com.lawu.eshop.activity.dto.RichPowerDetailDTO;
import com.lawu.eshop.activity.dto.RichPowerInfoRecordDTO;
import com.lawu.eshop.activity.param.ReceiveDiamondsParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.web.Result;


/**
 * 
 * @author lihj
 * @date 2018年6月7日
 */
@Service
public class CommonRichAccountService {
	@Autowired
	private RichAccountService richAccountService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private MerchantStoreService merchantStoreService;
	
	public Result<PersonalRichAccountDTO> getPersonalRichAccountInfo(String userNum,UserTypeEnum userType) {
		return richAccountService.getPersonalRichAccountInfo(userNum,userType);
	}

	public RichDetailDTO getRichInfo(UserTypeEnum userType) {
		RichDetailDTO dto = richAccountService.getRichInfo(userType).getModel();
		for (RichPowerDetailDTO rich : dto.getPowerList()) {
			if(userType.equals(UserTypeEnum.MEMBER)){
				String nickName = memberService.getMemberByNum(rich.getMemberNum()).getModel().getNickname();
				rich.setNickName(nickName);
			}else{
				Long merchantId = merchantService.getMerchantByNum(rich.getMemberNum()).getModel().getId();
				Result<MerchantStoreDTO> mResult = merchantStoreService.selectMerchantStoreByMId(merchantId);
				if(mResult.getRet()==ResultCode.SUCCESS){
					rich.setNickName(mResult.getModel().getName());
				}else{
					rich.setNickName("E店商家");
				}
			}
		}
		return dto;
	}

	public Result<RichPowerInfoRecordDTO> getRichPowerInfoRecord(String userNum) {
		return richAccountService.getRichPowerInfoRecord(userNum);
	}

	public Result<RichMyDiamondRecordInfoDTO> getMyRichDiamondRecordInfo(String userNum) {
		return richAccountService.getMyRichDiamondRecordInfo(userNum);
	}

	public IdentityInfoDTO getIdentityInfo(String memberNum) {
		Result<IdentityInfoDTO> result = richAccountService.getIdentityInfo(memberNum);
		Result<UserDTO> user = memberService.getMemberByNum(memberNum);
		IdentityInfoDTO info = result.getModel();
		info.setAccount(user.getModel().getAccount().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
		info.setHeadImg(user.getModel().getHeadimg());
		info.setNickName(user.getModel().getNickname());
		return result.getModel();
	}

	public Result receiveDiamonds(String memberNum, ReceiveDiamondsParam param) {
		return richAccountService.receiveDiamonds(memberNum, param);
	}

}
