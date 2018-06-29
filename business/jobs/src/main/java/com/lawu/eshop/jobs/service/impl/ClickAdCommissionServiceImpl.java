package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.common.param.CommissionJobParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.dto.AdCommissionResultDTO;
import com.lawu.eshop.property.param.CommissionResultParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.dto.MemberAdRecodeCommissionDTO;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.jobs.service.AdSrvService;
import com.lawu.eshop.jobs.service.ClickAdCommissionService;
import com.lawu.eshop.jobs.service.CommissionCommonService;
import com.lawu.eshop.jobs.service.CommonPropertyService;
import com.lawu.eshop.jobs.service.PropertySrvService;

@Service
public class ClickAdCommissionServiceImpl implements ClickAdCommissionService {

	private static Logger logger = LoggerFactory.getLogger(ClickAdCommissionServiceImpl.class);

	@Autowired
	private AdSrvService adService;
	@Autowired
	private CommissionCommonService userCommonService;
	@Autowired
	private CommonPropertyService commonPropertyService;
	@Autowired
	private PropertySrvService propertySrvService;
	@Autowired
	private CommissionUtilImpl commissionUtilImpl;

	@Override
	public void executeAutoClickAdCommission(MemberAdRecodeCommissionDTO memberAdRecodeCommissionDTO,boolean isTest) {

		Map<String, BigDecimal> property = commonPropertyService.getAdCommissionPropertys();
		BigDecimal loveAccountScale = property.get(PropertyType.love_account_scale);// 爱心账户比例
		BigDecimal actualCommissionScope = property.get("acture_in_scale");// 实际提成比例=1-爱心账户(0.003)
		BigDecimal adCommission0 = property.get(PropertyType.ad_commission_0);

		MemberAdRecodeCommissionDTO dto = memberAdRecodeCommissionDTO;
		if(dto.getMemberNum() == null || "".equals(dto.getMemberNum())){
			logger.error("查询未计算提成的点广告记录用户编号为空！");
			throw new RuntimeException();
		}
		// 查询用户上3级推荐
		List<CommissionInvitersUserDTO> inviters = userCommonService.selectHigherLevelInviters(dto.getMemberNum(), 3, false);

		if(isTest){
			logger.info("广告提成说明：等级比例（上1级{}，上2级{}，上3级{}），爱心账户比例：{}，基础比例{}",property.get(PropertyType.ad_commission_1),property.get(PropertyType.ad_commission_2),property.get(PropertyType.ad_commission_3),property.get(PropertyType.love_account_scale), property.get(PropertyType.ad_commission_0));
			logger.info("点击广告金额：{}",dto.getPoint());
			logger.info("提成公式：点击广告金额*基础比例*上级提成*(1-爱心账户)=实际提成");
		}

		int retCode = ResultCode.SUCCESS;
		if (inviters != null && !inviters.isEmpty()) {
			int m = 0;
			for (int i = 0; i < inviters.size(); i++) {
				CommissionJobParam param = new CommissionJobParam();
				param.setUserNum(inviters.get(i).getUserNum());
				param.setBizId(dto.getId());
				param.setTempBidId(dto.getAdId());

				BigDecimal clickMoney = dto.getPoint();

				BigDecimal sale_commission = null;
				if (inviters.get(i).getDept() == 1) {
					sale_commission = property.get(PropertyType.ad_commission_1);
				} else if (inviters.get(i).getDept() == 2) {
					sale_commission = property.get(PropertyType.ad_commission_2);
				} else if (inviters.get(i).getDept() == 3) {
					param.setLast(true);
					sale_commission = property.get(PropertyType.ad_commission_3);
				}

				CommissionResultParam commissionResultparam = new CommissionResultParam();
				commissionResultparam.setBeforeMoney(clickMoney);
				commissionResultparam.setCommission0(adCommission0);
				commissionResultparam.setCurrentCommission(sale_commission);
				commissionResultparam.setActualCommissionScope(actualCommissionScope);
				commissionResultparam.setLoveAccountScale(loveAccountScale);
				commissionResultparam.setDept(inviters.get(i).getDept());
				AdCommissionResultDTO rntDTO = commissionUtilImpl.getCommissionResult(commissionResultparam);
				param.setActureMoneyIn(rntDTO.getActureMoneyIn());
				param.setActureLoveIn(rntDTO.getActureLoveIn());

				if(inviters.get(i).getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
					param.setTypeVal(MemberTransactionTypeEnum.LOWER_INCOME.getValue());
					param.setTypeName(MemberTransactionTypeEnum.LOWER_INCOME.getName());
					param.setTransactionDesc(MemberTransactionTypeEnum.LOWER_INCOME.getDescPrefix());
				}else if(inviters.get(i).getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)){
					param.setTypeVal(MerchantTransactionTypeEnum.LOWER_INCOME.getValue());
					param.setTypeName(MerchantTransactionTypeEnum.LOWER_INCOME.getName());
					param.setTransactionDesc(MerchantTransactionTypeEnum.LOWER_INCOME.getDescPrefix());
				}
				param.setLoveTypeVal(LoveTypeEnum.AD_COMMISSION.getValue());
				param.setLoveTypeName(LoveTypeEnum.AD_COMMISSION.getName());

				if(!isTest){
					logger.info("点广告比例（获得提成账号编号：{},memberAdRecordId={}）；基础金额(a)：{}，参与提成金额比例(b)={},提成比例(c)={}（所得比例(d)={}|爱心账户比例(e)={}）；所得（实际实际[a*b*c*d]={},爱心账户[[a*b*c*e]]={}）",inviters.get(i).getUserNum(),dto.getId(), clickMoney, adCommission0,sale_commission,actualCommissionScope,loveAccountScale,param.getActureMoneyIn(),param.getActureLoveIn());
				} else {
					logger.info("上{}级:{}，实际提成{}，爱心账户{}",inviters.get(i).getDept(),inviters.get(i).getUserNum(),rntDTO.getActureMoneyIn(),rntDTO.getActureLoveIn());
				}


				try {
					retCode = propertySrvService.calculation(param);
					if (ResultCode.SUCCESS == retCode) {
						m++;
					}
				}catch (Exception e){
					throw new RuntimeException();
				}
			}
			// 所有上线提成计算成功才算成功
			if (m != inviters.size()) {
				throw new RuntimeException();
			}
		}

		// 修改用户点击广告记录状态为已计算提成
		if (ResultCode.SUCCESS == retCode) {
			retCode = adService.updateMemberAdRecardStatus(dto.getId());
			if (ResultCode.SUCCESS != retCode) {
				logger.error("广告点击提成修改用户点击记录状态时返回错误,memberAdRecordId={},retCode={}", dto.getId(), retCode);
				throw new RuntimeException();
			}
		}

	}

}
