package com.lawu.eshop.user.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import com.lawu.eshop.user.param.ApplyStoreParam;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.param.ShoppingOrderFindUserInfoParam;
import com.lawu.eshop.user.srv.bo.CashUserInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.bo.PayOrderStoreInfoBO;
import com.lawu.eshop.user.srv.bo.ShoppingOrderFindMerchantInfoBO;
import com.lawu.eshop.user.srv.bo.ShoppingStoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreSolrInfoBO;
import com.lawu.eshop.user.srv.converter.MerchantStoreConverter;
import com.lawu.eshop.user.srv.domain.FansMerchantDO;
import com.lawu.eshop.user.srv.domain.FansMerchantDOExample;
import com.lawu.eshop.user.srv.domain.FavoriteMerchantDO;
import com.lawu.eshop.user.srv.domain.FavoriteMerchantDOExample;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDOExample;
import com.lawu.eshop.user.srv.domain.extend.PayOrderStoreInfoView;
import com.lawu.eshop.user.srv.domain.extend.ShoppingStoreInfoDOView;
import com.lawu.eshop.user.srv.domain.extend.StoreDetailDOView;
import com.lawu.eshop.user.srv.domain.extend.StoreSolrInfoDOView;
import com.lawu.eshop.user.srv.mapper.FansMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.FavoriteMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreAuditDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.MerchantStoreDOMapperExtend;
import com.lawu.eshop.user.srv.service.MerchantStoreInfoService;
import com.lawu.utils.DataTransUtil;

import net.sf.json.JSONObject;

/**
 * 商家门店service Created by Administrator on 2017/3/24.
 */
@Service
public class MerchantStoreInfoServiceImpl implements MerchantStoreInfoService {

	@Autowired
	private MerchantDOMapper merchantDOMapper;

	@Autowired
	private MerchantStoreDOMapper merchantStoreDOMapper;

	@Autowired
	private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

	@Autowired
	MerchantStoreImageDOMapper merchantStoreImageDOMapper;

	@Autowired
	private MerchantStoreAuditDOMapper merchantStoreAuditDOMapper;

	@Autowired
	private FavoriteMerchantDOMapper favoriteMerchantDOMapper;

	@Autowired
	private MerchantStoreDOMapperExtend merchantStoreDOMapperExtend;

	@Autowired
	private FansMerchantDOMapper fansMerchantDOMapper;

	@Override
	public MerchantStoreInfoBO selectMerchantStore(Long merchantStoreId) {

		// 商家门店基本信息

		MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(merchantStoreId);
		if (merchantStoreDO == null) {
			return null;
		}

		// 商家店铺扩展信息

		MerchantStoreProfileDO merchantStoreProfileDO = merchantStoreProfileDOMapper.selectByPrimaryKey(merchantStoreId);

		// 商家店铺图片信息
		MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
		merchantStoreImageDOExample.createCriteria().andMerchantStoreIdEqualTo(merchantStoreId).andStatusEqualTo(true);
		List<MerchantStoreImageDO> merchantStoreImageDOS = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);

		MerchantStoreInfoBO merchantStoreInfoBO = MerchantStoreConverter.coverter(merchantStoreDO);
		if (merchantStoreProfileDO != null) {
			merchantStoreInfoBO.setCompanyAddress(merchantStoreProfileDO.getCompanyAddress());
			merchantStoreInfoBO.setCompanyName(merchantStoreProfileDO.getCompanyName());
			merchantStoreInfoBO.setRegNumber(merchantStoreProfileDO.getRegNumber());
			merchantStoreInfoBO.setLicenseIndate(merchantStoreProfileDO.getLicenseIndate());
			merchantStoreInfoBO.setManageType(merchantStoreProfileDO.getManageType());
			merchantStoreInfoBO.setCertifType(merchantStoreProfileDO.getCertifType());
			merchantStoreInfoBO.setOperatorCardId(merchantStoreProfileDO.getOperatorCardId());
			merchantStoreInfoBO.setOperatorName(merchantStoreProfileDO.getOperatorName());
		}

		if (!merchantStoreImageDOS.isEmpty()) {

			for (MerchantStoreImageDO merchantStoreImageDO : merchantStoreImageDOS) {
				Byte type = merchantStoreImageDO.getType();
				// merchantStoreInfoBO.setType(type);
				if (type == MerchantStoreImageEnum.STORE_IMAGE_STORE.val) {
					merchantStoreInfoBO.setStoreUrl(merchantStoreImageDO.getPath());
				}
				if (type == MerchantStoreImageEnum.STORE_IMAGE_LICENSE.val) {
					merchantStoreInfoBO.setLicenseUrl(merchantStoreImageDO.getPath());
				}
				if (type == MerchantStoreImageEnum.STORE_IMAGE_LOGO.val) {
					merchantStoreInfoBO.setLogoUrl(merchantStoreImageDO.getPath());
				}
				if (type == MerchantStoreImageEnum.STORE_IMAGE_OTHER.val) {
					merchantStoreInfoBO.setOtherUrl(merchantStoreImageDO.getPath());
				}
				if (type == MerchantStoreImageEnum.STORE_IMAGE_IDCARD.val) {
					merchantStoreInfoBO.setIdcardUrl(merchantStoreImageDO.getPath());
				}
				if (type == MerchantStoreImageEnum.STORE_IMAGE_ENVIRONMENT.val) {
					merchantStoreInfoBO.setEnvironmentUrl(merchantStoreImageDO.getPath());
				}

			}

		}
		// 查询门店审核记录
		MerchantStoreAuditDOExample merchantStoreAuditDOExample = new MerchantStoreAuditDOExample();
		merchantStoreAuditDOExample.createCriteria().andMerchantStoreIdEqualTo(merchantStoreId).andStatusEqualTo(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_UNCHECK.val);
		List<MerchantStoreAuditDO> auditInfos = merchantStoreAuditDOMapper.selectByExample(merchantStoreAuditDOExample);
		if (auditInfos.isEmpty()) {
			merchantStoreInfoBO.setAuditSuccess(true);// 未存在未审核状态
		}

		return merchantStoreInfoBO;
	}

	@Override
	public MerchantStoreInfoBO getMerchantStore(Long id, Long merchantId) {
		MerchantStoreDOExample example = new MerchantStoreDOExample();
		example.createCriteria().andIdEqualTo(id).andMerchantIdEqualTo(merchantId);
		List<MerchantStoreDO> merchantStoreDOS = merchantStoreDOMapper.selectByExample(example);
		if(merchantStoreDOS.isEmpty()){
			return null;
		}
		return MerchantStoreConverter.coverter(merchantStoreDOS.get(0));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMerchantStoreInfo(Long merchantId, MerchantStoreParam merchantStoreParam) {
		boolean isShow = true;
		// 新增门店基本信息
		MerchantStoreDO merchantStoreDO = (MerchantStoreDO) MerchantStoreConverter.couverDOByParam(merchantStoreParam, 1);
		merchantStoreDO.setMerchantId(merchantId);
		merchantStoreDO.setIsNoReasonReturn(false);
		merchantStoreDO.setGmtCreate(new Date());
		merchantStoreDO.setGmtModified(new Date());
		merchantStoreDO.setBuyNumbers(0);
		merchantStoreDO.setFavoriteNumber(0);
		merchantStoreDO.setAverageConsumeAmount(BigDecimal.ZERO);
		merchantStoreDO.setAverageScore(BigDecimal.ZERO);
		merchantStoreDO.setFeedbackRate(BigDecimal.ZERO);
		merchantStoreDO.setKeywords(merchantStoreParam.getKeywords());
		// 设置门店待审核状态
		if (CertifTypeEnum.CERTIF_TYPE_IDCARD.val == merchantStoreParam.getCertifType().val) {
			// 填写身份证用户需要交保证金
			merchantStoreDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_NOT_MONEY.val);
			isShow = false;
		} else {
			merchantStoreDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK.val);
		}
		merchantStoreDOMapper.insertSelective(merchantStoreDO);

		// 新增商家店铺扩展信息
		MerchantStoreProfileDO merchantStoreProfileDO = (MerchantStoreProfileDO) MerchantStoreConverter.couverDOByParam(merchantStoreParam, 2);
		merchantStoreProfileDO.setMerchantId(merchantId);

		merchantStoreProfileDO.setId(merchantStoreDO.getId());

		merchantStoreProfileDO.setManageType(merchantStoreParam.getManageType().val);
		merchantStoreProfileDO.setCertifType(merchantStoreParam.getCertifType().val);
		merchantStoreProfileDO.setGmtCreate(new Date());
		merchantStoreProfileDO.setGmtModified(new Date());

		merchantStoreProfileDOMapper.insertSelective(merchantStoreProfileDO);

		MerchantStoreImageDO merchantStoreImageDO = new MerchantStoreImageDO();
		merchantStoreImageDO.setMerchantId(merchantId);
		merchantStoreImageDO.setMerchantStoreId(merchantStoreDO.getId());
		merchantStoreImageDO.setGmtCreate(new Date());
		merchantStoreImageDO.setGmtModified(new Date());
		merchantStoreImageDO.setStatus(true);
		// 新增门店照
		if (!StringUtils.isEmpty(merchantStoreParam.getStoreUrl())) {
			String storeUrl = merchantStoreParam.getStoreUrl();
			String lastChar = storeUrl.substring(storeUrl.length() - 1);
			if (",".equals(lastChar)) {
				storeUrl = storeUrl.substring(0, storeUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(storeUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_STORE.val);
			merchantStoreImageDOMapper.insert(merchantStoreImageDO);
		}
		// 新增门店环境照
		if (!StringUtils.isEmpty(merchantStoreParam.getEnvironmentUrl())) {
			String evUrl = merchantStoreParam.getEnvironmentUrl();
			String lastChar = evUrl.substring(evUrl.length() - 1);
			if (",".equals(lastChar)) {
				evUrl = evUrl.substring(0, evUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(evUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_ENVIRONMENT.val);
			merchantStoreImageDOMapper.insert(merchantStoreImageDO);
		}
		// 新增营业执照
		if (!StringUtils.isEmpty(merchantStoreParam.getLicenseUrl())) {
			String licenseUrl = merchantStoreParam.getLicenseUrl();
			String lastChar = licenseUrl.substring(licenseUrl.length() - 1);
			if (",".equals(lastChar)) {
				licenseUrl = licenseUrl.substring(0, licenseUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(licenseUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LICENSE.val);
			merchantStoreImageDOMapper.insert(merchantStoreImageDO);
		}
		// 新增其他许可证
		if (!StringUtils.isEmpty(merchantStoreParam.getOtherUrl())) {
			String otherUrl = merchantStoreParam.getOtherUrl();
			String lastChar = otherUrl.substring(otherUrl.length() - 1);
			if (",".equals(lastChar)) {
				otherUrl = otherUrl.substring(0, otherUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(otherUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_OTHER.val);
			merchantStoreImageDOMapper.insert(merchantStoreImageDO);
		}
		// 新增门店LOGO
		if (!StringUtils.isEmpty(merchantStoreParam.getLogoUrl())) {
			String logoUrl = merchantStoreParam.getLogoUrl();
			String lastChar = logoUrl.substring(logoUrl.length() - 1);
			if (",".equals(lastChar)) {
				logoUrl = logoUrl.substring(0, logoUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(logoUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
			merchantStoreImageDOMapper.insert(merchantStoreImageDO);
		}
		// 新增门店手持身份证照
		if (!StringUtils.isEmpty(merchantStoreParam.getIdcardUrl())) {
			String idcardUrl = merchantStoreParam.getIdcardUrl();
			String lastChar = idcardUrl.substring(idcardUrl.length() - 1);
			if (",".equals(lastChar)) {
				idcardUrl = idcardUrl.substring(0, idcardUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(idcardUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_IDCARD.val);
			merchantStoreImageDOMapper.insert(merchantStoreImageDO);
		}

		// 增加门店审核信息
		MerchantStoreAuditDO merchantStoreAuditDO = new MerchantStoreAuditDO();
		merchantStoreAuditDO.setMerchantId(merchantId);
		merchantStoreAuditDO.setMerchantStoreId(merchantStoreDO.getId());
		merchantStoreAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
		merchantStoreAuditDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK.val);// 待审核
		merchantStoreAuditDO.setGmtCreate(new Date());
		merchantStoreAuditDO.setType(MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO.val);
		merchantStoreAuditDO.setIsShow(isShow);
		merchantStoreAuditDO.setGmtModified(new Date());
		merchantStoreAuditDOMapper.insert(merchantStoreAuditDO);

	}

	@Override
	public MerchantStoreProfileBO selectStoreInfoByExample(String example, Integer type) {
		MerchantStoreProfileDOExample merchantStoreProfileDOExample = new MerchantStoreProfileDOExample();
		if (type == 1) {
			merchantStoreProfileDOExample.createCriteria().andRegNumberEqualTo(example);
		} else {
			merchantStoreProfileDOExample.createCriteria().andOperatorCardIdEqualTo(example);
		}
		List<MerchantStoreProfileDO> merchantStoreProfileDOS = merchantStoreProfileDOMapper.selectByExample(merchantStoreProfileDOExample);
		if (!merchantStoreProfileDOS.isEmpty()) {
			return MerchantStoreConverter.convertBO(merchantStoreProfileDOS.get(0));
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMerchantStoreInfo(Long merchantId, MerchantStoreParam merchantStoreParam, Long merchantStoreId) {

		MerchantStoreDO merchantStoreDO = (MerchantStoreDO) MerchantStoreConverter.couverDOByParam(merchantStoreParam, 1);
		// 设置门店待审核状态
		merchantStoreDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK.val);
		merchantStoreDO.setGmtModified(new Date());

		// 更新门店信息
		MerchantStoreDOExample example = new MerchantStoreDOExample();
		example.createCriteria().andIdEqualTo(merchantStoreId).andMerchantIdEqualTo(merchantId);
		merchantStoreDOMapper.updateByExampleSelective(merchantStoreDO, example);

		// 更新门店扩展信息
		MerchantStoreProfileDO merchantStoreProfileDO = (MerchantStoreProfileDO) MerchantStoreConverter.couverDOByParam(merchantStoreParam, 2);
		merchantStoreProfileDO.setGmtModified(new Date());
		merchantStoreProfileDO.setManageType(merchantStoreParam.getManageType().val);
		merchantStoreProfileDO.setCertifType(merchantStoreParam.getCertifType().val);
		MerchantStoreProfileDOExample merchantStoreProfileDOExample = new MerchantStoreProfileDOExample();
		merchantStoreProfileDOExample.createCriteria().andIdEqualTo(merchantStoreId).andMerchantIdEqualTo(merchantId);
		merchantStoreProfileDOMapper.updateByExampleSelective(merchantStoreProfileDO, merchantStoreProfileDOExample);

		// 更新门店图片信息

		// 先删除对应商家门店图片---逻辑删除
		MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
		merchantStoreImageDOExample.createCriteria().andMerchantStoreIdEqualTo(merchantStoreId).andMerchantIdEqualTo(merchantId);
		MerchantStoreImageDO merchantStoreImageDODel = new MerchantStoreImageDO();
		merchantStoreImageDODel.setStatus(false);
		merchantStoreImageDOMapper.updateByExampleSelective(merchantStoreImageDODel, merchantStoreImageDOExample);

		MerchantStoreImageDO merchantStoreImageDO = new MerchantStoreImageDO();
		merchantStoreImageDO.setMerchantId(merchantId);
		merchantStoreImageDO.setMerchantStoreId(merchantStoreId);
		merchantStoreImageDO.setGmtCreate(new Date());
		merchantStoreImageDO.setGmtModified(new Date());
		merchantStoreImageDO.setStatus(true);
		// 新增门店照
		if (!StringUtils.isEmpty(merchantStoreParam.getStoreUrl())) {
			String storeUrl = merchantStoreParam.getStoreUrl();
			String lastChar = storeUrl.substring(storeUrl.length() - 1);
			if (",".equals(lastChar)) {
				storeUrl = storeUrl.substring(0, storeUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(storeUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_STORE.val);
			merchantStoreImageDOMapper.insertSelective(merchantStoreImageDO);
		}
		// 新增门店环境照
		if (!StringUtils.isEmpty(merchantStoreParam.getEnvironmentUrl())) {
			String evUrl = merchantStoreParam.getEnvironmentUrl();
			String lastChar = evUrl.substring(evUrl.length() - 1);
			if (",".equals(lastChar)) {
				evUrl = evUrl.substring(0, evUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(evUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_ENVIRONMENT.val);
			merchantStoreImageDOMapper.insertSelective(merchantStoreImageDO);
		}
		// 新增营业执照
		if (!StringUtils.isEmpty(merchantStoreParam.getLicenseUrl())) {
			String licenseUrl = merchantStoreParam.getLicenseUrl();
			String lastChar = licenseUrl.substring(licenseUrl.length() - 1);
			if (",".equals(lastChar)) {
				licenseUrl = licenseUrl.substring(0, licenseUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(licenseUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LICENSE.val);
			merchantStoreImageDOMapper.insertSelective(merchantStoreImageDO);
		}
		// 新增其他许可证
		if (!StringUtils.isEmpty(merchantStoreParam.getOtherUrl())) {
			String otherUrl = merchantStoreParam.getOtherUrl();
			String lastChar = otherUrl.substring(otherUrl.length() - 1);
			if (",".equals(lastChar)) {
				otherUrl = otherUrl.substring(0, otherUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(otherUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_OTHER.val);
			merchantStoreImageDOMapper.insertSelective(merchantStoreImageDO);
		}
		// 新增门店LOGO
		if (!StringUtils.isEmpty(merchantStoreParam.getLogoUrl())) {
			String logoUrl = merchantStoreParam.getLogoUrl();
			String lastChar = logoUrl.substring(logoUrl.length() - 1);
			if (",".equals(lastChar)) {
				logoUrl = logoUrl.substring(0, logoUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(logoUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
			merchantStoreImageDOMapper.insertSelective(merchantStoreImageDO);
		}
		// 新增门店手持身份证照
		if (!StringUtils.isEmpty(merchantStoreParam.getIdcardUrl())) {
			String idcardUrl = merchantStoreParam.getIdcardUrl();
			String lastChar = idcardUrl.substring(idcardUrl.length() - 1);
			if (",".equals(lastChar)) {
				idcardUrl = idcardUrl.substring(0, idcardUrl.length() - 1);
			}
			merchantStoreImageDO.setPath(idcardUrl);
			merchantStoreImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_IDCARD.val);
			merchantStoreImageDOMapper.insertSelective(merchantStoreImageDO);
		}

	}

	@Override
	public MerchantStoreInfoBO selectMerchantStoreByMId(Long merchantId) {
		MerchantStoreDOExample merchantStoreDOExample = new MerchantStoreDOExample();
		merchantStoreDOExample.createCriteria().andMerchantIdEqualTo(merchantId);
		List<MerchantStoreDO> merchantStoreDOS = merchantStoreDOMapper.selectByExample(merchantStoreDOExample);
		if (merchantStoreDOS.isEmpty()) {
			return null;
		}
		return MerchantStoreConverter.coverter(merchantStoreDOS.get(0));
	}

	/**
	 * 商家是否支持七天退货、商家的用户编号、当前用户是否是商家的粉丝
	 *
	 * @param param
	 * @return
	 */
	@Override
	public List<ShoppingOrderFindMerchantInfoBO> shoppingOrderFindUserInfo(ShoppingOrderFindUserInfoParam param) {
		MerchantStoreDOExample merchantStoreDOExample = new MerchantStoreDOExample();
		merchantStoreDOExample.createCriteria().andMerchantIdIn(param.getMerchantIdList());
		List<MerchantStoreDO> merchantStoreDOList = merchantStoreDOMapper.selectByExample(merchantStoreDOExample);

		MerchantDOExample merchantDOExample = new MerchantDOExample();
		merchantDOExample.createCriteria().andIdIn(param.getMerchantIdList());
		List<MerchantDO> merchantDOList = merchantDOMapper.selectByExample(merchantDOExample);

		Map<Long, MerchantDO> merchantDOMap = new HashMap<Long, MerchantDO>();
		for (MerchantDO merchantDO : merchantDOList) {
			merchantDOMap.put(merchantDO.getId(), merchantDO);
		}

		List<ShoppingOrderFindMerchantInfoBO> rtn = new ArrayList<>();
		FansMerchantDOExample fansMerchantDOExample = null;
		ShoppingOrderFindMerchantInfoBO shoppingOrderFindUserInfoBO = null;
		for (MerchantStoreDO merchantStoreDO : merchantStoreDOList) {
			fansMerchantDOExample = new FansMerchantDOExample();
			fansMerchantDOExample.createCriteria().andMemberIdEqualTo(param.getMemberId()).andMerchantIdEqualTo(merchantStoreDO.getMerchantId()).andStatusEqualTo((byte)1);
			int count = fansMerchantDOMapper.countByExample(fansMerchantDOExample);
			boolean isFans = false;
			if (count > 0) {
				isFans = true;
			}
			shoppingOrderFindUserInfoBO = MerchantStoreConverter.convert(merchantStoreDO, merchantDOMap.get(merchantStoreDO.getMerchantId()));
			shoppingOrderFindUserInfoBO.setIsFans(isFans);
			rtn.add(shoppingOrderFindUserInfoBO);
		}

		return rtn;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMerchantStoreAuditInfo(Long merchantId, MerchantStoreParam merchantStoreParam, Long merchantStoreId) {
		boolean isShow = true;
		MerchantStoreAuditDO merchantStoreAuditDO = new MerchantStoreAuditDO();
		merchantStoreAuditDO.setMerchantStoreId(merchantStoreId);
		merchantStoreAuditDO.setMerchantId(merchantId);
		JSONObject json = JSONObject.fromObject(merchantStoreParam);
		merchantStoreAuditDO.setContent(json.toString());
		merchantStoreAuditDO.setGmtModified(new Date());
		MerchantStoreDO storeDO = new MerchantStoreDO();
		storeDO.setId(merchantStoreId);
		if (MerchantStatusEnum.MERCHANT_STATUS_CANCEL.val.byteValue() == merchantStoreParam.getMerchantStoreStatus().val.byteValue()) {
			//门店注销情况
			isShow = false;
			//修改店铺状态, 填写身份证用户需要交保证金
			if (CertifTypeEnum.CERTIF_TYPE_IDCARD.val.byteValue() == merchantStoreParam.getCertifType().val.byteValue()) {
				storeDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_NOT_MONEY.val);
			} else {
				storeDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK.val);
			}
		}else{
			storeDO.setStatus(merchantStoreParam.getMerchantStoreStatus().val);
		}
		merchantStoreDOMapper.updateByPrimaryKeySelective(storeDO);
		merchantStoreAuditDO.setIsShow(isShow);
		merchantStoreAuditDO.setType(MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO.val);
		merchantStoreAuditDO.setGmtCreate(new Date());
		merchantStoreAuditDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK.val);
		merchantStoreAuditDOMapper.insert(merchantStoreAuditDO);
	}

	@Override
	public StoreDetailBO getStoreDetailById(Long id, Long memberId) {
		// 查询门店信息

		List<StoreDetailDOView>  storeDetailDOViews = merchantStoreDOMapperExtend.getStoreDetailById(id);
		if(storeDetailDOViews.isEmpty()){
			return null;
		}
		StoreDetailBO storeDetailBO = new StoreDetailBO();
		storeDetailBO.setMerchantId(storeDetailDOViews.get(0).getMerchantId());
		storeDetailBO.setName(storeDetailDOViews.get(0).getName());
		storeDetailBO.setAddress(storeDetailDOViews.get(0).getAddress());
		storeDetailBO.setAverageConsumeAmount(storeDetailDOViews.get(0).getAverageConsumeAmount());
		storeDetailBO.setAverageScore(storeDetailDOViews.get(0).getAverageScore());
		storeDetailBO.setBuyNumbers(storeDetailDOViews.get(0).getBuyNumbers());
		storeDetailBO.setFavoriteNumber(storeDetailDOViews.get(0).getFavoriteNumber());
		storeDetailBO.setFeedbackRate(storeDetailDOViews.get(0).getFeedbackRate());
		storeDetailBO.setIntro(storeDetailDOViews.get(0).getIntro());
		storeDetailBO.setPrincipalMobile(storeDetailDOViews.get(0).getPrincipalMobile());
		storeDetailBO.setUserNum(storeDetailDOViews.get(0).getUserNum());
		storeDetailBO.setRegionName(storeDetailDOViews.get(0).getRegionName());
		storeDetailBO.setRegionPath(storeDetailDOViews.get(0).getRegionPath());
		storeDetailBO.setLongitude(storeDetailDOViews.get(0).getLongitude());
		storeDetailBO.setLatitude(storeDetailDOViews.get(0).getLatitude());
		storeDetailBO.setIndustryPath(storeDetailDOViews.get(0).getIndustryPath());
		for (StoreDetailDOView storeDetailDOView :storeDetailDOViews){
			//门店照
			if(storeDetailDOView.getType() == MerchantStoreImageEnum.STORE_IMAGE_STORE.val){
				storeDetailBO.setStorePic(storeDetailDOView.getPath());
			}
			//环境照
			if(storeDetailDOView.getType() == MerchantStoreImageEnum.STORE_IMAGE_ENVIRONMENT.val){
				int picCount = 0;
				if(StringUtils.isNotEmpty(storeDetailDOView.getPath())){
					picCount = storeDetailDOView.getPath().split(",").length;
				}
				storeDetailBO.setPicCount(picCount);
			}
			//门店logo
			if(storeDetailDOView.getType() == MerchantStoreImageEnum.STORE_IMAGE_LOGO.val){
				storeDetailBO.setStoreLogo(storeDetailDOView.getPath());
			}
		}
		// 查询是否被收藏
		FavoriteMerchantDOExample favoriteMerchantDOExample = new FavoriteMerchantDOExample();
		favoriteMerchantDOExample.createCriteria().andMemberIdEqualTo(memberId).andMerchantIdEqualTo(storeDetailDOViews.get(0).getMerchantId()).andManageTypeEqualTo(ManageTypeEnum.ENTITY.getVal());
		List<FavoriteMerchantDO> favoriteMerchantDOS = favoriteMerchantDOMapper.selectByExample(favoriteMerchantDOExample);
		if (favoriteMerchantDOS.isEmpty()) {
			storeDetailBO.setFavorite(false);
		} else {
			storeDetailBO.setFavorite(true);
		}
		return storeDetailBO;
	}

	@Override
	public CashUserInfoBO findCashUserInfo(Long id) {
		MerchantStoreDOExample example = new MerchantStoreDOExample();
		example.createCriteria().andMerchantIdEqualTo(id);
		List<MerchantStoreDO> stores = merchantStoreDOMapper.selectByExample(example);
		if (stores == null || stores.isEmpty()) {
			return null;
		} 
		if (stores.get(0).getRegionPath() == null || stores.get(0).getRegionPath().split("/").length != 3) {
			return null;
		}
		CashUserInfoBO bo = new CashUserInfoBO();
		bo.setName(stores.get(0).getName());
		bo.setRegionFullName(stores.get(0).getRegionName());
		bo.setProvinceId(Integer.valueOf(stores.get(0).getRegionPath().split("/")[0]));
		bo.setCityId(Integer.valueOf(stores.get(0).getRegionPath().split("/")[1]));
		bo.setAreaId(Integer.valueOf(stores.get(0).getRegionPath().split("/")[2]));
		return bo;
	}

	@Override
	public MerchantStoreInfoBO findStoreNameAndImgByMerchantId(Long merchantId) {
		MerchantStoreDOExample example = new MerchantStoreDOExample();
		example.createCriteria().andMerchantIdEqualTo(merchantId);
		List<MerchantStoreDO> stores = merchantStoreDOMapper.selectByExample(example);
		if (stores == null || stores.isEmpty()) {
			return null;
		}
		MerchantStoreInfoBO merchantStoreInfoBO = new MerchantStoreInfoBO();
		merchantStoreInfoBO.setName(stores.get(0).getName());
		MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
		merchantStoreImageDOExample.createCriteria().andMerchantIdEqualTo(merchantId)
				.andTypeEqualTo(MerchantStoreImageEnum.STORE_IMAGE_STORE.val)
				.andStatusEqualTo(true);// 门店照
		List<MerchantStoreImageDO> merchantStoreImageDOS = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);
		if (!merchantStoreImageDOS.isEmpty() && merchantStoreImageDOS.get(0) != null) {
			if (StringUtils.isEmpty(merchantStoreImageDOS.get(0).getPath())) {
				merchantStoreInfoBO.setStoreUrl("");
			} else {
				merchantStoreInfoBO.setStoreUrl(merchantStoreImageDOS.get(0).getPath());
			}
		}
		return merchantStoreInfoBO;
	}

	@Override
	public MerchantStoreAuditBO findStoreAuditInfo(Long merchantId) {
		MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
		example.createCriteria().andMerchantIdEqualTo(merchantId);
		example.setOrderByClause("id desc");
		List<MerchantStoreAuditDO> merchantStoreAuditDOS = merchantStoreAuditDOMapper.selectByExample(example);
		if (merchantStoreAuditDOS.isEmpty()) {
			return null;
		}
		MerchantStoreAuditBO merchantStoreAuditBO = new MerchantStoreAuditBO();
		merchantStoreAuditBO.setId(merchantStoreAuditDOS.get(0).getId());
		merchantStoreAuditBO.setStatus(merchantStoreAuditDOS.get(0).getStatus());
		merchantStoreAuditBO.setType(merchantStoreAuditDOS.get(0).getType());
		return merchantStoreAuditBO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addMerchantStoreBuyNums(Long merchantId) {
		merchantStoreDOMapperExtend.addMerchantStoreBuyNums(merchantId);
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMerchantStoreBuyNums(Long merchantId, Integer buyNums) {
        merchantStoreDOMapperExtend.updateMerchantStoreBuyNums(merchantId, buyNums);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addMerchantStoreCommentsCount(Long merchantId, Integer commentsCount) {
		merchantStoreDOMapperExtend.addMerchantStoreCommentsCount(merchantId, commentsCount);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMerchantStoreStatus(Long merchantId, Byte status) {
		MerchantStoreDOExample example = new MerchantStoreDOExample();
		example.createCriteria().andMerchantIdEqualTo(merchantId);
		MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
		merchantStoreDO.setStatus(status);
		merchantStoreDOMapper.updateByExampleSelective(merchantStoreDO, example);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer applyPhysicalStore(Long merchantId, Long storeId, ApplyStoreParam param) {

		MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
		example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_UNCHECK.val).andTypeEqualTo(MerchantAuditTypeEnum.AUDIT_TYPE_STORE.val);
		List<MerchantStoreAuditDO> auditDOS = merchantStoreAuditDOMapper.selectByExample(example);
		if (!auditDOS.isEmpty()) {
			return -1;
		}
		MerchantStoreAuditDO audit = new MerchantStoreAuditDO();
		audit.setMerchantId(merchantId);
		audit.setMerchantStoreId(storeId);
		audit.setType(MerchantAuditTypeEnum.AUDIT_TYPE_STORE.val);
		audit.setStatus(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_UNCHECK.val);
		audit.setContent(JSONObject.fromObject(param).toString());
		audit.setIsShow(true);
		audit.setGmtCreate(new Date());
		audit.setGmtModified(new Date());
		Integer row = merchantStoreAuditDOMapper.insert(audit);
		return row;
	}

	@Override
	public ShoppingStoreDetailBO getShoppingStoreDetailById(Long id, Long memberId) {
		// 查询门店信息

		ShoppingStoreInfoDOView storeInfoDOView = merchantStoreDOMapperExtend.getShoppingStoreInfo(id);
		if(storeInfoDOView == null){
			return null;
		}
		ShoppingStoreDetailBO shoppingStoreDetailBO = new ShoppingStoreDetailBO();
		shoppingStoreDetailBO.setName(storeInfoDOView.getName());
		shoppingStoreDetailBO.setLogoPic(storeInfoDOView.getPath());
		shoppingStoreDetailBO.setMerchantId(storeInfoDOView.getMerchantId());

		// 查询粉丝数量
		FansMerchantDOExample fansMerchantDOExample = new FansMerchantDOExample();
		fansMerchantDOExample.createCriteria().andMerchantIdEqualTo(storeInfoDOView.getMerchantId()).andStatusEqualTo(DataTransUtil.intToByte(1));
		int fansCount = fansMerchantDOMapper.countByExample(fansMerchantDOExample);
		shoppingStoreDetailBO.setFansCount(fansCount);

		// 查询是否关注
		fansMerchantDOExample = new FansMerchantDOExample();
		fansMerchantDOExample.createCriteria().andMemberIdEqualTo(memberId).andMerchantIdEqualTo(storeInfoDOView.getMerchantId()).andStatusEqualTo(DataTransUtil.intToByte(1));
		List<FansMerchantDO> fansMerchantDOS = fansMerchantDOMapper.selectByExample(fansMerchantDOExample);
		if (fansMerchantDOS.isEmpty()) {
			shoppingStoreDetailBO.setFans(false);
		} else {
			shoppingStoreDetailBO.setFans(true);
		}

		// 查询是否收藏
		FavoriteMerchantDOExample favoriteMerchantDOExample = new FavoriteMerchantDOExample();
		favoriteMerchantDOExample.createCriteria().andMemberIdEqualTo(memberId).andMerchantIdEqualTo(storeInfoDOView.getMerchantId()).andManageTypeEqualTo(ManageTypeEnum.COMMON.getVal());
		List<FavoriteMerchantDO> favoriteMerchantDOS = favoriteMerchantDOMapper.selectByExample(favoriteMerchantDOExample);
		if (favoriteMerchantDOS.isEmpty()) {
			shoppingStoreDetailBO.setFavorite(false);
		} else {
			shoppingStoreDetailBO.setFavorite(true);
		}
		return shoppingStoreDetailBO;
	}

	/**
	 * 查询门店name，门店照
	 * @param merchantIds
	 * @return
	 */
	@Override
	public List<PayOrderStoreInfoBO> getPayOrderStoreInfo(List<Long> merchantIds) {
		List<PayOrderStoreInfoView> storeInfoViews = merchantStoreDOMapperExtend.getPayOrderStoreInfo(merchantIds);
		if(storeInfoViews.isEmpty()){
			return  null;
		}
		List<PayOrderStoreInfoBO> storeInfoBOS = new ArrayList<>();
		for(PayOrderStoreInfoView storeInfoView :storeInfoViews){
			PayOrderStoreInfoBO merchantStoreInfoBO = new PayOrderStoreInfoBO();
			merchantStoreInfoBO.setName(storeInfoView.getName());
			merchantStoreInfoBO.setStoreUrl(storeInfoView.getPath());
			merchantStoreInfoBO.setMerchantId(storeInfoView.getMerchantId());
			storeInfoBOS.add(merchantStoreInfoBO);
		}
		return storeInfoBOS;
	}

	@Override
	public List<StoreSolrInfoBO> getMerchantStoreByIds(List<Long> merchantStoreIds) {
		List<StoreSolrInfoDOView> viewList = merchantStoreDOMapperExtend.getMerchantStoreByIds(merchantStoreIds);
		if(viewList.isEmpty()){
			return null;
		}
		List<StoreSolrInfoBO> storeSolrInfoBOS = new ArrayList<>();
		for(StoreSolrInfoDOView storeInfoView :viewList){
			StoreSolrInfoBO merchantStoreInfoBO = new StoreSolrInfoBO();
			merchantStoreInfoBO.setMerchantId(storeInfoView.getMerchantId());
			merchantStoreInfoBO.setMerchantStoreId(storeInfoView.getMerchantStoreId());
			merchantStoreInfoBO.setIndustryPath(storeInfoView.getIndustryPath());
			merchantStoreInfoBO.setIndustryName(storeInfoView.getIndustryName());
			storeSolrInfoBOS.add(merchantStoreInfoBO);
		}
		return storeSolrInfoBOS;
	}

	@Override
	public PayOrderStoreInfoBO getPayOrderDetailStoreInfo(Long merchantId) {
		PayOrderStoreInfoView storeInfoView = merchantStoreDOMapperExtend.getPayOrderDetailStoreInfo(merchantId);
		PayOrderStoreInfoBO payOrderStoreInfoBO = MerchantStoreConverter.coverPayOrderStoreInfoBO(storeInfoView);
		return payOrderStoreInfoBO;
	}

	@Override
	public MerchantInfoBO getPayOrderMerchantInfo(Long merchantId) {
		PayOrderStoreInfoView storeInfoView = merchantStoreDOMapperExtend.getPayOrderMerchantInfo(merchantId);
		if(storeInfoView == null){
			return null;
		}
		MerchantInfoBO merchantInfoBO = new MerchantInfoBO();
		merchantInfoBO.setName(storeInfoView.getName());
		merchantInfoBO.setAccount(storeInfoView.getAccount());
		return merchantInfoBO;
	}

}
