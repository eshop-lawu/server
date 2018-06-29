package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import com.lawu.eshop.user.param.ListStoreAuditParam;
import com.lawu.eshop.user.param.MerchantAuditParam;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.converter.MerchantStoreAuditConverter;
import com.lawu.eshop.user.srv.converter.MerchantStoreConverter;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDOExample;
import com.lawu.eshop.user.srv.mapper.MerchantStoreAuditDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.service.MerchantAuditService;
import com.lawu.eshop.user.srv.solr.service.MerchantStoreSolrService;
import com.lawu.framework.core.page.Page;

import net.sf.json.JSONObject;

/**
 * @author zhangyong
 * @date 2017/3/31.
 */
@Service
public class MerchantAuditServiceImpl implements MerchantAuditService {
    
    @Autowired
    private MerchantStoreAuditDOMapper merchantStoreAuditDOMapper;
    
    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;
    
    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;
    
    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;
    
    @Autowired
    private MerchantStoreSolrService merchantStoreSolrService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMerchantAudit(Long storeAuditId, MerchantAuditParam auditParam) {

        MerchantStoreAuditDO oldAudit = merchantStoreAuditDOMapper.selectByPrimaryKey(storeAuditId);
        if (oldAudit != null) {
            //先更新审核信息表记录
            MerchantStoreAuditDO merchantStoreAuditDO = new MerchantStoreAuditDO();
            merchantStoreAuditDO.setId(storeAuditId);
            merchantStoreAuditDO.setRemark(auditParam.getRemark());
            merchantStoreAuditDO.setStatus(auditParam.getAuditStatusEnum().val);
            merchantStoreAuditDO.setAuditTime(new Date());
            merchantStoreAuditDO.setSynTime(new Date());
            merchantStoreAuditDO.setGmtModified(new Date());
            merchantStoreAuditDO.setAuditorId(auditParam.getAuditorId());
            merchantStoreAuditDOMapper.updateByPrimaryKeySelective(merchantStoreAuditDO);

            //查询门店信息记录
            MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(auditParam.getMerchantStoreId());
            List<MerchantStoreProfileDO> merchantStoreProfileDOS ;
            if (merchantStoreDO != null && merchantStoreDO.getId() > 0) {
                MerchantStoreDO newStoreDO = new MerchantStoreDO();
                newStoreDO.setId(merchantStoreDO.getId());
                newStoreDO.setGmtModified(new Date());

                //查询商家店铺扩展信息
                MerchantStoreProfileDOExample merchantStoreProfileDOExample = new MerchantStoreProfileDOExample();
                merchantStoreProfileDOExample.createCriteria().andMerchantIdEqualTo(merchantStoreDO.getMerchantId());
                merchantStoreProfileDOS = merchantStoreProfileDOMapper.selectByExample(merchantStoreProfileDOExample);
                if (MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_CHECKED.val == auditParam.getAuditStatusEnum().val) {
                    //审核通过
                    if (MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO.val == auditParam.getTypeEnum().val) {
                            //修改更新门店信息
                            JSONObject jsonObject = JSONObject.fromObject(oldAudit.getContent());
                            MerchantStoreParam merchantStoreParam = (MerchantStoreParam) JSONObject.toBean(jsonObject, MerchantStoreParam.class);
                            newStoreDO.setName(merchantStoreParam.getName());
                            newStoreDO.setRegionName(merchantStoreParam.getRegionName());
                            newStoreDO.setRegionPath(merchantStoreParam.getRegionPath());
                            newStoreDO.setAddress(merchantStoreParam.getAddress());
                            newStoreDO.setLongitude(merchantStoreParam.getLongitude());
                            newStoreDO.setLatitude(merchantStoreParam.getLatitude());
                            newStoreDO.setIndustryPath(merchantStoreParam.getIndustryPath());
                            newStoreDO.setIntro(merchantStoreParam.getIntro());
                            newStoreDO.setPrincipalName(merchantStoreParam.getPrincipalName());
                            newStoreDO.setPrincipalMobile(merchantStoreParam.getPrincipalMobile());
                            newStoreDO.setIndustryName(merchantStoreParam.getIndustryName());
                            newStoreDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_CHECKED.val);
                            newStoreDO.setKeywords(merchantStoreParam.getKeywords());
                            merchantStoreDOMapper.updateByPrimaryKeySelective(newStoreDO);
                            //修改更新门店扩展信息
                            MerchantStoreProfileDO profile = new MerchantStoreProfileDO();
                            profile.setId(merchantStoreDO.getId());
                            profile.setCompanyName(merchantStoreParam.getCompanyName());
                            profile.setRegNumber(merchantStoreParam.getRegNumber());
                            profile.setCompanyAddress(merchantStoreParam.getCompanyAddress());
                            profile.setLicenseIndate(merchantStoreParam.getLicenseIndate());
                            profile.setManageType(merchantStoreParam.getManageType().val);
                            profile.setCertifType(merchantStoreParam.getCertifType().val);
                            profile.setOperatorCardId(merchantStoreParam.getOperatorCardId());
                            profile.setOperatorName(merchantStoreParam.getOperatorName());
                            profile.setGmtModified(new Date());
                            merchantStoreProfileDOMapper.updateByPrimaryKeySelective(profile);

                            //更新门店图片信息
                            //先删除对应商家门店图片---逻辑删除
                            MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
                            merchantStoreImageDOExample.createCriteria().andMerchantStoreIdEqualTo(merchantStoreDO.getId());
                            MerchantStoreImageDO merchantStoreImageDODel = new MerchantStoreImageDO();
                            merchantStoreImageDODel.setStatus(false);
                            merchantStoreImageDOMapper.updateByExampleSelective(merchantStoreImageDODel, merchantStoreImageDOExample);

                            MerchantStoreImageDO merchantStoreImageDO = new MerchantStoreImageDO();
                            merchantStoreImageDO.setMerchantId(merchantStoreDO.getMerchantId());
                            merchantStoreImageDO.setMerchantStoreId(merchantStoreDO.getId());
                            merchantStoreImageDO.setGmtCreate(new Date());
                            merchantStoreImageDO.setGmtModified(new Date());
                            merchantStoreImageDO.setStatus(true);
                            //新增门店照
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
                            //新增门店环境照
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
                            //新增营业执照
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
                            //新增其他许可证
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
                            //新增门店LOGO
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
                            //新增门店手持身份证照
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

                    } else {
                        //申请实体店铺
                        //先删除对应商家LOGO图片---逻辑删除
                        MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
                        merchantStoreImageDOExample.createCriteria().andMerchantStoreIdEqualTo(merchantStoreDO.getId()).
                                andTypeEqualTo(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
                        MerchantStoreImageDO merchantStoreImageDODel = new MerchantStoreImageDO();
                        merchantStoreImageDODel.setStatus(false);
                        merchantStoreImageDOMapper.updateByExampleSelective(merchantStoreImageDODel, merchantStoreImageDOExample);

                        JSONObject jsonObject = JSONObject.fromObject(oldAudit.getContent());
                        MerchantStoreParam merchantStoreParam = (MerchantStoreParam) JSONObject.toBean(jsonObject, MerchantStoreParam.class);
                        MerchantStoreImageDO merchantStoreImageDO = new MerchantStoreImageDO();
                        merchantStoreImageDO.setMerchantId(merchantStoreDO.getMerchantId());
                        merchantStoreImageDO.setMerchantStoreId(merchantStoreDO.getId());
                        merchantStoreImageDO.setGmtCreate(new Date());
                        merchantStoreImageDO.setGmtModified(new Date());
                        merchantStoreImageDO.setStatus(true);
                        //新增门店照
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
                        //新增门店环境照
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
                        //新增门店LOGO
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

                        // 更新店铺类型为实体店铺
                        if(!merchantStoreProfileDOS.isEmpty()){
                            MerchantStoreProfileDO merchantStoreProfileDO = new MerchantStoreProfileDO();
                            merchantStoreProfileDO.setId(merchantStoreProfileDOS.get(0).getId());
                            merchantStoreProfileDO.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT.val);
                            merchantStoreProfileDOMapper.updateByPrimaryKeySelective(merchantStoreProfileDO);
                        }
                    }

                    MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
                    merchantStoreImageDOExample.createCriteria().andMerchantStoreIdEqualTo(merchantStoreDO.getId()).andTypeEqualTo(MerchantStoreImageEnum.STORE_IMAGE_STORE.val).andStatusEqualTo(true);
                    List<MerchantStoreImageDO> merchantStoreImageDOS = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);
                    String storePic = merchantStoreImageDOS.isEmpty() ? "" : merchantStoreImageDOS.get(0).getPath();

                    boolean isEntity = false;
                    if(!merchantStoreProfileDOS.isEmpty() && merchantStoreProfileDOS.get(0).getManageType().byteValue() == MerchantStoreTypeEnum.ENTITY_MERCHANT.val){
                        isEntity = true;
                    }
                    if(MerchantAuditTypeEnum.AUDIT_TYPE_STORE.val.byteValue() == auditParam.getTypeEnum().val || isEntity){
                        MerchantStoreDO indexStore = merchantStoreDOMapper.selectByPrimaryKey(merchantStoreDO.getId());
                        MerchantStoreSolr solrBean = MerchantStoreConverter.convert(indexStore, storePic);
                        merchantStoreSolrService.update(solrBean);
                    }
                } else {
                    //审核不通过
                    if(merchantStoreDO.getStatus() == MerchantStatusEnum.MERCHANT_STATUS_CHECKED.val){
                        //已经通过审核的门店
                      return;
                    }
                    newStoreDO.setStatus(MerchantStatusEnum.MERCHANT_STATUS_CHECK_FAILED.val);
                    merchantStoreDOMapper.updateByPrimaryKeySelective(newStoreDO);
                }
            }
        }
    }

    @Override
    public MerchantStoreAuditBO getMerchantAuditInfo(Long merchantId) {
        MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId);
        example.setOrderByClause("id desc");
        List<MerchantStoreAuditDO> merchantStoreAuditDOS = merchantStoreAuditDOMapper.selectByExample(example);
        if (merchantStoreAuditDOS.isEmpty()) {
            return null;
        }
        MerchantStoreAuditBO merchantStoreAuditBO = new MerchantStoreAuditBO();
        merchantStoreAuditBO.setStatus(merchantStoreAuditDOS.get(0).getStatus());
        merchantStoreAuditBO.setRemark(merchantStoreAuditDOS.get(0).getRemark());
        merchantStoreAuditBO.setGmtCreate(merchantStoreAuditDOS.get(0).getGmtCreate());
        merchantStoreAuditBO.setType(merchantStoreAuditDOS.get(0).getType());
        return merchantStoreAuditBO;
    }

    @Override
    public MerchantStoreAuditBO getMerchantAuditInfoByUncheck(Long merchantId, Byte status) {
        MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(status).andTypeEqualTo(MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO.val);
        List<MerchantStoreAuditDO> merchantStoreAuditDOS = merchantStoreAuditDOMapper.selectByExample(example);
        if (merchantStoreAuditDOS.isEmpty()) {
            return null;
        }
        MerchantStoreAuditBO merchantStoreAuditBO = new MerchantStoreAuditBO();
        merchantStoreAuditBO.setStatus(merchantStoreAuditDOS.get(0).getStatus());
        merchantStoreAuditBO.setRemark(merchantStoreAuditDOS.get(0).getRemark());
        return merchantStoreAuditBO;
    }

    @Override
    public Page<MerchantStoreAuditBO> listAllStoreAudit(ListStoreAuditParam auditParam) {
        MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
        MerchantStoreAuditDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsShowEqualTo(true);
        if (StringUtils.isNotEmpty(auditParam.getSortName()) && StringUtils.isNotEmpty(auditParam.getSortOrder())) {
            example.setOrderByClause("gmt_modified " + auditParam.getSortOrder());
        }
        if (auditParam.getStatusEnum() != null) {
            criteria.andStatusEqualTo(auditParam.getStatusEnum().val);
        }
        if (auditParam.getTypeEnum() != null) {
            criteria.andTypeEqualTo(auditParam.getTypeEnum().val);
        }

        RowBounds rowBounds = new RowBounds(auditParam.getOffset(), auditParam.getPageSize());

        Page<MerchantStoreAuditBO> page = new Page<>();
        page.setCurrentPage(auditParam.getCurrentPage());
        page.setTotalCount(merchantStoreAuditDOMapper.countByExample(example));

        List<MerchantStoreAuditDO> merchantStoreAuditDOS = merchantStoreAuditDOMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        page.setRecords(MerchantStoreAuditConverter.convertBO(merchantStoreAuditDOS));
        return page;
    }

    @Override
    public MerchantStoreAuditBO getMerchantStoreAuditById(Long id) {
        MerchantStoreAuditDO merchantStoreAuditDO = merchantStoreAuditDOMapper.selectByPrimaryKey(id);
        return MerchantStoreAuditConverter.convertBO(merchantStoreAuditDO);
    }

    @Override
    public MerchantStoreAuditBO getMerchantStoreAudit(Long id, Long merchantStoreId) {
        MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
        example.createCriteria().andIdEqualTo(id).andMerchantStoreIdEqualTo(merchantStoreId);
        List<MerchantStoreAuditDO> storeAuditDOS = merchantStoreAuditDOMapper.selectByExample(example);
        if(storeAuditDOS.isEmpty()){
            return null;
        }
        return MerchantStoreAuditConverter.convertBO(storeAuditDOS.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setAuditInfoShow(Long merchantId) {
        MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_UNCHECK.val);
        MerchantStoreAuditDO merchantStoreAuditDO = new MerchantStoreAuditDO();
        merchantStoreAuditDO.setIsShow(true);
        merchantStoreAuditDOMapper.updateByExampleSelective(merchantStoreAuditDO,example);
    }

    @Override
    public MerchantStoreAuditBO getRecentMerchantAuditRecord(Long merchantId) {
        MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId);
        example.setOrderByClause("id desc");
        List<MerchantStoreAuditDO> list = merchantStoreAuditDOMapper.selectByExampleWithBLOBs(example);
        if(list.isEmpty()){
            return null;
        }
        return MerchantStoreAuditConverter.convertBO(list.get(0));
    }

    @Override
    public Long getRecentMerchantAuditSuccessRecord(Long merchantId) {
        MerchantStoreAuditDOExample example = new MerchantStoreAuditDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_CHECKED.val);
        example.setOrderByClause("id desc");
        List<MerchantStoreAuditDO> list = merchantStoreAuditDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0).getId();
    }
}
