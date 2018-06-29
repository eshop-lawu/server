package com.lawu.eshop.user.srv.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantInfoDTO;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import com.lawu.eshop.user.dto.param.MerchantSizeLinkDTO;
import com.lawu.eshop.user.param.MerchantProfileParam;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantProfileBO;
import com.lawu.eshop.user.srv.bo.MerchantSizeLinkBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.service.MerchantProfileService;
import com.lawu.eshop.user.srv.service.MerchantService;
import com.lawu.eshop.user.srv.service.MerchantStoreInfoService;
import com.lawu.eshop.user.srv.service.MerchantStoreProfileService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 商家扩展信息
 * Created by zhangyong on 2017/3/23.
 */
@RestController
@RequestMapping(value = "merchantInfo/")
public class MerchantInfoController extends BaseController{

    @Autowired
    private MerchantProfileService merchantProfileService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantStoreInfoService merchantStoreInfoService;

    @Autowired
    private MerchantStoreProfileService merchantStoreProfileService;


    /**
     * 设置网站链接
     * @param merchantProfileParam
     */
    @RequestMapping(value = "updateMerchantSizeLink/{id}", method = RequestMethod.PUT)
    public Result updateMerchantSizeLink(@RequestBody MerchantProfileParam merchantProfileParam, @PathVariable("id") Long id){
       int result =  merchantProfileService.updateMerchantSizeLink(merchantProfileParam,id);
        if(result ==1){
           return successCreated();
        }else{
            return successCreated(ResultCode.USER_WRONG_ID);
        }
    }

    /**
     * 查询商家主页基本信息
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "getCurrentMerchantInfo/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantInfoDTO> getCurrentMerchantInfo(@PathVariable("merchantId") Long merchantId){

        MerchantInfoDTO merchantInfoDTO = new MerchantInfoDTO();
        // 商家基本信息
        MerchantInfoBO merchantInfoBO = merchantService.findMerchantInfo(merchantId);
        if(merchantInfoBO == null){
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        //门店信息
        merchantInfoDTO.setAccount(merchantInfoBO.getAccount());
        merchantInfoDTO.setGtCid(merchantInfoBO.getGtCid());
        merchantInfoDTO.setRyToken(merchantInfoBO.getRyToken());
        merchantInfoDTO.setHeadimg(merchantInfoBO.getHeadimg());
        merchantInfoDTO.setLevel(merchantInfoBO.getLevel());
        //商家扩展信息
        MerchantProfileBO merchantProfileBO = merchantProfileService.findMerchantProfileInfo(merchantId);
        merchantInfoDTO.setInviteMemberCount(merchantProfileBO.getInviteMemberCount());
        merchantInfoDTO.setInviteMerchantCount(merchantProfileBO.getInviteMerchantCount());

        MerchantStoreInfoBO merchantStoreBO = merchantStoreInfoService.selectMerchantStoreByMId(merchantId);
        if(merchantStoreBO != null){
            //门店ID
            merchantInfoDTO.setMerchantStoreId(merchantStoreBO.getMerchantStoreId());
            merchantInfoDTO.setPrincipalName(merchantStoreBO.getPrincipalName());
            merchantInfoDTO.setNOReasonReturn(merchantStoreBO.getIsNoReasonReturn());
            merchantInfoDTO.setStoreName(merchantStoreBO.getName());
            merchantInfoDTO.setStoreStatusEnum(merchantStoreBO.getStatusEnum());
            //门店类型
            MerchantStoreProfileBO merchantStoreProfileBO = merchantStoreProfileService.findMerchantStoreInfo(merchantId);
            if(merchantStoreProfileBO !=null){
                merchantInfoDTO.setStoreTypeEnum(MerchantStoreTypeEnum.getEnum(merchantStoreProfileBO.getManageType()));
                merchantInfoDTO.setCertifTypeEnum(CertifTypeEnum.getEnum(merchantStoreProfileBO.getCertifType()));
                merchantInfoDTO.setHeadimg(merchantStoreProfileBO.getLogoUrl());
            }

            //查询门店审核状态
            MerchantStoreAuditBO auditBO = merchantStoreInfoService.findStoreAuditInfo(merchantId);
            if(auditBO != null){
                merchantInfoDTO.setAuditStatusEnum(MerchantAuditStatusEnum.getEnum(auditBO.getStatus()));
                merchantInfoDTO.setAuditTypeEnum(MerchantAuditTypeEnum.getEnum(auditBO.getType()));
            }
        }
        return successGet(merchantInfoDTO);
    }

    @RequestMapping(value = "getMerchantSizeLink/{merchantId}",method = RequestMethod.GET)
    public Result<MerchantSizeLinkDTO> getMerchantSizeLink(@PathVariable("merchantId") Long merchantId){
        if(merchantId == null || merchantId<=0){
            return  successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        MerchantSizeLinkBO merchantSizeLinkBO = merchantProfileService.getMerchantSizeLink(merchantId);
        MerchantSizeLinkDTO merchantSizeLinkDTO = new MerchantSizeLinkDTO();
        if(merchantSizeLinkBO != null){
            BeanUtils.copyProperties(merchantSizeLinkBO,merchantSizeLinkDTO);
        }
        return successGet(merchantSizeLinkDTO);
    }

}
