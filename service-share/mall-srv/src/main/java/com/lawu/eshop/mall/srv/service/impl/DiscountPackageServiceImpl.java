package com.lawu.eshop.mall.srv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.constants.DiscountPackageStatusEnum;
import com.lawu.eshop.mall.param.DiscountPackageImageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageQueryForeignParam;
import com.lawu.eshop.mall.srv.bo.DiscountPackageBO;
import com.lawu.eshop.mall.srv.bo.DiscountPackageExtendBO;
import com.lawu.eshop.mall.srv.converter.DiscountPackageContentConverter;
import com.lawu.eshop.mall.srv.converter.DiscountPackageConverter;
import com.lawu.eshop.mall.srv.converter.DiscountPackageExtendConverter;
import com.lawu.eshop.mall.srv.converter.DiscountPackageImageConverter;
import com.lawu.eshop.mall.srv.domain.DiscountPackageContentDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageDOExample;
import com.lawu.eshop.mall.srv.domain.DiscountPackageImageDO;
import com.lawu.eshop.mall.srv.domain.extend.DiscountPackageExtendDO;
import com.lawu.eshop.mall.srv.exception.DataNotExistException;
import com.lawu.eshop.mall.srv.exception.IllegalOperationException;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageContentDOMapper;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageDOMapper;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageImageDOMapper;
import com.lawu.eshop.mall.srv.mapper.extend.DiscountPackageExtendDOMapper;
import com.lawu.eshop.mall.srv.service.DiscountPackageService;
import com.lawu.eshop.mall.srv.solr.MerchantStoreSolrService;
import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;

/**
 * 优惠套餐服务接口实现类
 * 
 * @author Sunny
 * @date 2017/06/26
 */
@Service
public class DiscountPackageServiceImpl implements DiscountPackageService {

	@Autowired
	private DiscountPackageDOMapper discountPackageDOMapper;

	@Autowired
	private DiscountPackageExtendDOMapper discountPackageExtendDOMapper;

	@Autowired
	private DiscountPackageContentDOMapper discountPackageContentDOMapper;

	@Autowired
	private DiscountPackageImageDOMapper discountPackageImageDOMapper;
	
	@Autowired
	private MerchantStoreSolrService merchantStoreSolrService;

	
	/**
	 * 根据商家id查询商家的所有优惠套餐<p>
	 * 用户端
	 * 
	 * @param merchantId
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Override
	public List<DiscountPackageBO> listForMember(Long merchantId) {
		DiscountPackageDOExample example = new DiscountPackageDOExample();
		example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(DiscountPackageStatusEnum.UP.getValue());

		// 按上架时间倒序
		example.setOrderByClause("gmt_up desc");
		List<DiscountPackageBO> rtn = DiscountPackageConverter.convertDiscountPackageBOList(discountPackageDOMapper.selectByExample(example));
		return rtn;
	}
	
	/**
	 * 根据商家id以及查询参数查询商家的所有优惠套餐<p>
	 * 商家端
	 * 
	 * @param merchantId 商家id
	 * @param param 查询参数
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Override
	public List<DiscountPackageBO> listForMerchant(Long merchantId, DiscountPackageQueryForeignParam param) {
		DiscountPackageDOExample example = new DiscountPackageDOExample();
		
		example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(param.getStatus().getValue());

		// 按上架时间倒序
		example.setOrderByClause("gmt_up desc");
		List<DiscountPackageBO> rtn = DiscountPackageConverter.convertDiscountPackageBOList(discountPackageDOMapper.selectByExample(example));
		return rtn;
	}

	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Override
	public DiscountPackageExtendBO get(Long id, Long merchantId) {
		DiscountPackageExtendDO discountPackageExtendDO = discountPackageExtendDOMapper.selectByPrimaryKey(id);
		boolean isNotExist = discountPackageExtendDO == null || discountPackageExtendDO.getId() == null || discountPackageExtendDO.getId() <= 0
				|| DiscountPackageStatusEnum.INVALID.getValue().equals(discountPackageExtendDO.getStatus());
		if (isNotExist) {
			throw new DataNotExistException("优惠套餐不存在");
		}
		
		if (merchantId != null && !discountPackageExtendDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException("非法操作优惠套餐");
		}
		return DiscountPackageExtendConverter.convert(discountPackageExtendDO);
	}
	
	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Override
	public DiscountPackageExtendBO get(Long id) {
		return get(id, null);
	}

	/**
	 * 保存优惠套餐
	 * 
	 * @param discountPackageSaveParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void save(Long merchantId, DiscountPackageSaveParam discountPackageSaveParam) {
		DiscountPackageDO discountPackageDO = DiscountPackageConverter.convert(merchantId, discountPackageSaveParam);
		discountPackageDOMapper.insert(discountPackageDO);

		for (DiscountPackageContentSaveForeignParam discountPackageContentSaveParam : discountPackageSaveParam.getDiscountPackageContents()) {
			DiscountPackageContentDO discountPackageContentDO = DiscountPackageContentConverter.convert(discountPackageDO.getId(), discountPackageContentSaveParam);
			discountPackageContentDOMapper.insert(discountPackageContentDO);
		}

		for (DiscountPackageImageSaveParam discountPackageImageSaveParam : discountPackageSaveParam.getDiscountPackageImages()) {
			DiscountPackageImageDO discountPackageImageDO = DiscountPackageImageConverter.convert(discountPackageDO.getId(), discountPackageImageSaveParam);
			discountPackageImageDOMapper.insert(discountPackageImageDO);
		}

		updateSolrDiscountPackage(merchantId, 0L);
	}

	/**
	 * 更新优惠套餐
	 * 
	 * @param id
	 * @param discountPackageUpdateParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(Long merchantId, Long id, DiscountPackageUpdateParam discountPackageUpdateParam) {
		DiscountPackageExtendDO discountPackageExtendDO = discountPackageExtendDOMapper.selectByPrimaryKey(id);
		
		boolean isNotExist = discountPackageExtendDO == null || discountPackageExtendDO.getId() == null || discountPackageExtendDO.getId() <= 0
				|| DiscountPackageStatusEnum.INVALID.getValue().equals(discountPackageExtendDO.getStatus());
		if (isNotExist) {
			throw new DataNotExistException("优惠套餐不存在");
		}
		
		if (!discountPackageExtendDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException("非法操作优惠套餐");
		}
		
		DiscountPackageDO discountPackageDO = DiscountPackageConverter.convert(id, discountPackageUpdateParam);
		discountPackageDOMapper.updateByPrimaryKeySelective(discountPackageDO);
		
		/*
		 *  当id不存在保存一条记录,id存在就更新当前数据
		 *  并且保存id到list,用于删除
		 */
		List<Long> discountPackageContentIdList = new ArrayList<>();
		discountPackageUpdateParam.getDiscountPackageContents().forEach(discountPackageContentUpdateParam -> {
			if (discountPackageContentUpdateParam.getId() == null) {
				DiscountPackageContentDO discountPackageContentDO = DiscountPackageContentConverter.convert(discountPackageDO.getId(), discountPackageContentUpdateParam);
				discountPackageContentDOMapper.insert(discountPackageContentDO);
			} else {
				DiscountPackageContentDO discountPackageContentDO = DiscountPackageContentConverter.convert(discountPackageContentUpdateParam);
				discountPackageContentDOMapper.updateByPrimaryKeySelective(discountPackageContentDO);
				discountPackageContentIdList.add(discountPackageContentDO.getId());
			}
		});
		
		// 删除套餐内容
		discountPackageExtendDO.getDiscountPackageContents().forEach(discountPackageContentDO -> {
			if (!discountPackageContentIdList.contains(discountPackageContentDO.getId())) {
				DiscountPackageContentDO updateDiscountPackageContentDO = new DiscountPackageContentDO();
				updateDiscountPackageContentDO.setId(discountPackageContentDO.getId());
				updateDiscountPackageContentDO.setStatus(StatusEnum.INVALID.getValue());
				discountPackageContentDOMapper.updateByPrimaryKeySelective(updateDiscountPackageContentDO);
			}
		});
		
		/*
		 *  当id不存在保存一条记录,id存在就更新当前数据
		 *  并且保存id到list,用于删除
		 */
		List<Long> discountPackageImage = new ArrayList<>();
		discountPackageUpdateParam.getDiscountPackageImages().forEach(discountPackageImageUpdateParam -> {
			if (discountPackageImageUpdateParam.getId() == null) {
				DiscountPackageImageDO discountPackageImageDO = DiscountPackageImageConverter.convert(discountPackageDO.getId(), discountPackageImageUpdateParam);
				discountPackageImageDOMapper.insert(discountPackageImageDO);
			} else {
				DiscountPackageImageDO discountPackageImageDO = DiscountPackageImageConverter.convert(discountPackageImageUpdateParam);
				discountPackageImageDOMapper.updateByPrimaryKeySelective(discountPackageImageDO);
				discountPackageImage.add(discountPackageImageDO.getId());
			}
		});
		
		// 删除套餐内容
		discountPackageExtendDO.getDiscountPackageImages().forEach(discountPackageImageDO -> {
			if (!discountPackageImage.contains(discountPackageImageDO.getId())) {
				DiscountPackageImageDO updateDiscountPackageImageDO = new DiscountPackageImageDO();
				updateDiscountPackageImageDO.setId(discountPackageImageDO.getId());
				updateDiscountPackageImageDO.setStatus(StatusEnum.INVALID.getValue());
				discountPackageImageDOMapper.updateByPrimaryKeySelective(updateDiscountPackageImageDO);
			}
		});

		updateSolrDiscountPackage(merchantId, 0L);
	}

	/**
	 * 删除优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(Long merchantId, Long id) {
		DiscountPackageDO discountPackageDO = discountPackageDOMapper.selectByPrimaryKey(id);
		
		boolean isNotExist = discountPackageDO == null || discountPackageDO.getId() == null || discountPackageDO.getId() <= 0
				|| DiscountPackageStatusEnum.INVALID.getValue().equals(discountPackageDO.getStatus());
		if (isNotExist) {
			throw new DataNotExistException("优惠套餐不存在");
		}
		
		if (!discountPackageDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException("非法操作优惠套餐");
		}
		
		DiscountPackageDO updateDiscountPackageDO = new DiscountPackageDO();
		updateDiscountPackageDO.setId(id);
		updateDiscountPackageDO.setStatus(DiscountPackageStatusEnum.INVALID.getValue());
		discountPackageDOMapper.updateByPrimaryKeySelective(updateDiscountPackageDO);

		updateSolrDiscountPackage(merchantId, id);
	}

	/**
	 * 上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void up(Long merchantId, Long id) {
		DiscountPackageDO discountPackageDO = discountPackageDOMapper.selectByPrimaryKey(id);
		
		boolean isNotExist = discountPackageDO == null || discountPackageDO.getId() == null || discountPackageDO.getId() <= 0
				|| DiscountPackageStatusEnum.INVALID.getValue().equals(discountPackageDO.getStatus());
		if (isNotExist) {
			throw new DataNotExistException("优惠套餐不存在");
		}
		
		if (!discountPackageDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException("非法操作优惠套餐");
		}
		
		DiscountPackageDO updateDiscountPackageDO = new DiscountPackageDO();
		updateDiscountPackageDO.setId(id);
		updateDiscountPackageDO.setStatus(DiscountPackageStatusEnum.UP.getValue());
		discountPackageDOMapper.updateByPrimaryKeySelective(updateDiscountPackageDO);

		updateSolrDiscountPackage(merchantId, 0L);
	}

	/**
	 * 下架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void down(Long merchantId, Long id) {
		DiscountPackageDO discountPackageDO = discountPackageDOMapper.selectByPrimaryKey(id);
		
		boolean isNotExist = discountPackageDO == null || discountPackageDO.getId() == null || discountPackageDO.getId() <= 0
				|| DiscountPackageStatusEnum.INVALID.getValue().equals(discountPackageDO.getStatus());
		if (isNotExist) {
			throw new DataNotExistException("优惠套餐不存在");
		}
		
		if (!discountPackageDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException("非法操作优惠套餐");
		}
		
		DiscountPackageDO updateDiscountPackageDO = new DiscountPackageDO();
		updateDiscountPackageDO.setId(id);
		updateDiscountPackageDO.setStatus(DiscountPackageStatusEnum.DOWN.getValue());
		discountPackageDOMapper.updateByPrimaryKeySelective(updateDiscountPackageDO);

		updateSolrDiscountPackage(merchantId, id);
	}

	/**
	 * 批量删除优惠套餐
	 * 
	 * @param merchantId
	 * @param idList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(Long merchantId, List<Long> idList) {
		for (Long id : idList) {
			delete(merchantId, id);
		}
	}

	/**
	 * 批量上架优惠套餐
	 * 
	 * @param merchantId
	 * @param idList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void up(Long merchantId, List<Long> idList) {
		for (Long id : idList) {
			up(merchantId, id);
		}
	}

	/**
	 * 批量下架优惠套餐
	 * 
	 * @param merchantId
	 * @param idList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void down(Long merchantId, List<Long> idList) {
		for (Long id : idList) {
			down(merchantId, id);
		}
	}

	/**
	 * 更新solr保存的门店优惠套餐
	 *
	 * @param merchantId
	 */
	private void updateSolrDiscountPackage(Long merchantId, Long id) {
		DiscountPackageDOExample example = new DiscountPackageDOExample();
		example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(DiscountPackageStatusEnum.UP.getValue());
		example.setOrderByClause("gmt_up desc");
		List<DiscountPackageDO> discountPackageDOS = discountPackageDOMapper.selectByExample(example);

		Long storeId = 0L;
		String discountPackage = "";
		if (discountPackageDOS.isEmpty()) {
			DiscountPackageDO discountPackageDO = discountPackageDOMapper.selectByPrimaryKey(id);
			storeId = discountPackageDO == null ? 0L : discountPackageDO.getMerchantStoreId();
		} else {
			DiscountPackageDO discountPackageDO = discountPackageDOS.get(0);
			storeId = discountPackageDO.getMerchantStoreId();
			discountPackage = discountPackageDO.getName();
		}
		MerchantStoreSolr solrBean = new MerchantStoreSolr();
		solrBean.setId(storeId);
		solrBean.setDiscountPackage(discountPackage);
		merchantStoreSolrService.update(solrBean);
	}

}
