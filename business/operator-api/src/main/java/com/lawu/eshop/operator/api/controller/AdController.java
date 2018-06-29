package com.lawu.eshop.operator.api.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.AuditEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.param.AdFindParam;
import com.lawu.eshop.ad.param.ListAdParam;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.operator.api.event.EventPublisher;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.AdService;
import com.lawu.eshop.operator.api.service.LogService;
import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.operator.api.service.MessageService;
import com.lawu.eshop.operator.api.service.RegionService;
import com.lawu.eshop.operator.api.service.UserService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.operator.dto.UserListDTO;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.dto.MerchantViewDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 描述：广告管理
 *
 * @author zhangrc
 * @date 2017/04/5
 */
@Api(tags = "ad")
@RestController
@RequestMapping(value = "ad/")
public class AdController extends BaseController {

    @Autowired
    private AdService adService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private LogService logService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private EventPublisher eventPublisher;

    @ApiOperation(value = "广告列表", notes = "广告列表,[]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectList", method = RequestMethod.POST)
    public Result<Page<AdDTO>> selectListByMerchant(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                    @ModelAttribute @ApiParam(value = "查询信息") AdFindParam adPlatParam) {
        return adService.selectListByPlatForm(adPlatParam);
    }

    @ApiOperation(value = "广告列表", notes = "查询广告列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    @RequiresPermissions("adAudit:list")
    @RequestMapping(value = "listAd", method = RequestMethod.POST)
    public Result<Page<AdDTO>> listAd(@RequestBody @ApiParam ListAdParam listAdParam) {
        Result<Page<AdDTO>> result = adService.listAd(listAdParam);
        List<AdDTO> list = result.getModel().getRecords();
        if(list != null && !list.isEmpty()){
            for(AdDTO adDTO : list){
                if (adDTO.getAuditorId() != null && adDTO.getAuditorId() > 0) {
                    Result<UserListDTO> userListDTOResult = userService.findUserById(adDTO.getAuditorId());
                    if (isSuccess(userListDTOResult)) {
                        adDTO.setAuditorName(userListDTOResult.getModel().getName());
                    }
                }
                Result<MerchantViewDTO> merchantViewDTOResult = merchantService.getMerchantView(adDTO.getMerchantId());
                if(isSuccess(merchantViewDTOResult)){
                    adDTO.setName(merchantViewDTOResult.getModel().getStoreName());
                    adDTO.setAccount(merchantViewDTOResult.getModel().getAccount());
                }
            }
        }
        return result;
    }

    @ApiOperation(value = "广告详情", notes = "查询广告详情。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("adAudit:detail")
    @RequestMapping(value = "getAd/{id}", method = RequestMethod.GET)
    public Result<AdDTO> getAd(@PathVariable @ApiParam(value = "ID") Long id) {
        Result<AdDTO> result = adService.getAdById(id);
        AdDTO adDTO = result.getModel();
        if (adDTO != null && adDTO.getPutWayEnum().val == PutWayEnum.PUT_WAY_AREAS.val) {
            if (StringUtils.isEmpty(adDTO.getAreas())) {
                adDTO.setAreas("全国");
                return result;
            }
            StringBuilder stringBuilder = new StringBuilder();
            String[] areasArr = adDTO.getAreas().split(",");
            for (String areas : areasArr) {
                Result<String> regionFullName = regionService.getRegionFullName(Integer.valueOf(areas));
                stringBuilder.append(regionFullName.getModel()).append(",");
            }
            adDTO.setAreas(stringBuilder.substring(0, stringBuilder.length() - 1));
        }
        return result;
    }

    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.AD_DOWN,idParamName ="id")
    @ApiOperation(value = "广告操作下架", notes = "广告操作下架,[5001]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("adAudit:soldOut")
    @RequestMapping(value = "adDown/{id}", method = RequestMethod.POST)
    public Result adDown(@PathVariable @ApiParam(required = true, value = "广告id") Long id,
    		 @RequestParam @ApiParam(required = true, value = "审核备注") String remark) {
        Integer auditorId = 0;
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            auditorId = userResult.getModel().getId();
        }
        Result rs = adService.downOperatorById(id, auditorId, remark);
        if(!isSuccess(rs)){
            return rs;
        }

        
        //发送站内消息
        Result<AdDTO> adDTOResult = adService.getAdById(id);
        Result<MerchantSNSDTO> merchantSNSDTOResult = merchantService.selectMerchantInfo(adDTOResult.getModel().getMerchantId());

        MessageInfoParam messageInfoParam = new MessageInfoParam();
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setAdTypeName(adDTOResult.getModel().getTypeEnum().getName());
        messageTempParam.setAdName(adDTOResult.getModel().getTitle());
        messageInfoParam.setRelateId(id);
        messageTempParam.setFailReason(remark);
        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_AD_FORCE_DOWN);
        messageInfoParam.setMessageParam(messageTempParam);
        messageService.saveMessage(merchantSNSDTOResult.getModel().getNum(), messageInfoParam);

        return rs;
    }


    @ApiOperation(value = "广告操作删除", notes = "广告操作删除,[]（张荣成）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.DELETE ,title = LogTitleEnum.AD_DELETE,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("adAudit:del")
    @RequestMapping(value = "adDelete/{id}", method = RequestMethod.PUT)
    public Result adDelete(@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
        Result rs = adService.operatorUpdateAdStatus(id, AdStatusEnum.AD_STATUS_DELETE);
        if(!isSuccess(rs)){
            return rs;
        }
        return rs;
    }

    @ApiOperation(value = "广告审核通过", notes = "广告审核,[]（张荣成）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.AD_VIDEO_AUDIT_SUCCESS,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("adAudit:pass")
    @RequestMapping(value = "auditVideoPass/{id}", method = RequestMethod.PUT)
    public Result auditVideoPass(@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
        Integer auditorId = 0;
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            auditorId = userResult.getModel().getId();
        }
        Result rs = adService.auditVideo(id, auditorId, "", AuditEnum.AD_AUDIT_PASS);
        if(!isSuccess(rs)){
            return rs;
        }

        //发送站内消息
        Result<AdDTO> adDTOResult = adService.getAdById(id);
        Result<MerchantSNSDTO> merchantSNSDTOResult = merchantService.selectMerchantInfo(adDTOResult.getModel().getMerchantId());

        MessageInfoParam messageInfoParam = new MessageInfoParam();
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setAdTypeName(AdTypeEnum.AD_TYPE_VIDEO.getName());
        messageTempParam.setAdName(adDTOResult.getModel().getTitle());
        messageInfoParam.setRelateId(id);
        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_CHECK_AD_SUCCESS);
        messageInfoParam.setMessageParam(messageTempParam);
        messageService.saveMessage(merchantSNSDTOResult.getModel().getNum(), messageInfoParam);

        return rs;
    }

    @ApiOperation(value = "广告审核不通过", notes = "广告审核,[8110]（张荣成）", httpMethod = "POST")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.AD_VIDEO_AUDIT_FAIL,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("adAudit:unpass")
    @RequestMapping(value = "auditVideoUnPass/{id}", method = RequestMethod.POST)
    public Result auditVideoUnPass(@PathVariable @ApiParam(required = true, value = "广告id") Long id,
                                   @RequestParam @ApiParam(required = true, value = "审核备注") String remark) {
        Integer auditorId = 0;
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            auditorId = userResult.getModel().getId();
        }
        Result rs = adService.auditVideo(id, auditorId, remark, AuditEnum.AD_AUDIT_UN_PASS);
        if(!isSuccess(rs)){
            return rs;
        }

        //发送站内消息
        Result<AdDTO> adDTOResult = adService.getAdById(id);
        Result<MerchantSNSDTO> merchantSNSDTOResult = merchantService.selectMerchantInfo(adDTOResult.getModel().getMerchantId());

        MessageInfoParam messageInfoParam = new MessageInfoParam();
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setAdTypeName(AdTypeEnum.AD_TYPE_VIDEO.getName());
        messageTempParam.setAdName(adDTOResult.getModel().getTitle());
        messageTempParam.setFailReason(remark);
        messageInfoParam.setRelateId(id);
        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_CHECK_AD_FAIL);
        messageInfoParam.setMessageParam(messageTempParam);
        messageService.saveMessage(merchantSNSDTOResult.getModel().getNum(), messageInfoParam);

        return rs;
    }

    
}
