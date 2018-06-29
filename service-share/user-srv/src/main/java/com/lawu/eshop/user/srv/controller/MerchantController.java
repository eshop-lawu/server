package com.lawu.eshop.user.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.constants.UserStatusEnum;
import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.dto.MerchantBaseInfoDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantDetailDTO;
import com.lawu.eshop.user.dto.MerchantFreezeInfoDTO;
import com.lawu.eshop.user.dto.MerchantInviterDTO;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.dto.MerchantStoreInfoDTO;
import com.lawu.eshop.user.dto.MerchantStoreProfileDTO;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.dto.MerchantViewDTO;
import com.lawu.eshop.user.dto.MessagePushDTO;
import com.lawu.eshop.user.dto.MobileDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserHeadImgDTO;
import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.MerchantInviterParam;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.bo.MerchantBaseInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantDetailBO;
import com.lawu.eshop.user.srv.bo.MerchantFreezeInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInviterBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.bo.MessagePushBO;
import com.lawu.eshop.user.srv.bo.RongYunBO;
import com.lawu.eshop.user.srv.converter.LoginUserConverter;
import com.lawu.eshop.user.srv.converter.MerchantConverter;
import com.lawu.eshop.user.srv.converter.MerchantInviterConverter;
import com.lawu.eshop.user.srv.converter.RongYunConverter;
import com.lawu.eshop.user.srv.domain.extend.MerchantDOView;
import com.lawu.eshop.user.srv.service.InviteService;
import com.lawu.eshop.user.srv.service.MerchantProfileService;
import com.lawu.eshop.user.srv.service.MerchantService;
import com.lawu.eshop.user.srv.service.MerchantStoreProfileService;
import com.lawu.eshop.user.srv.service.MerchantStoreService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.PwdUtil;

/**
 * @author meishuquan
 * @date 2017/3/22
 */
@RestController
@RequestMapping(value = "merchant/")
public class MerchantController extends BaseController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantStoreService merchantStoreService;	

    @Autowired
    private MerchantStoreProfileService merchantStoreProfileService;
    @Autowired
    private MerchantProfileService merchantProfileService;

    @Autowired
    private InviteService inviteService;

    @RequestMapping(value = "withPwd/{account}", method = RequestMethod.GET)
    public Result<LoginUserDTO> find(@PathVariable String account, @RequestParam String pwd) {
        MerchantBO merchantBO = merchantService.find(account, pwd);
        if (merchantBO == null) {
            return successGet(ResultCode.MEMBER_WRONG_PWD);
        }
        if(merchantBO.getIsFreeze()){
            return successGet(ResultCode.ACCOUNT_IS_FREEZE, merchantBO.getFreezeReason());
        }
        if (merchantBO.getStatus().byteValue() == UserStatusEnum.MEMBER_STATUS_NOVALID.val) {
            return successGet(ResultCode.ACCOUNT_IS_INVALID);
        }
       
        //更新最后登录时间
        merchantProfileService.updateLastLoginTime(merchantBO.getId());
        
        //更新推荐关系
        inviteService.updateInviteUserProfileInfo(merchantBO.getNum(),merchantBO.getId());
        
        LoginUserDTO rtn = LoginUserConverter.convert(merchantBO);
        MerchantStoreBO merchantStoreBO = merchantStoreService.selectMerchantStore(merchantBO.getId());
        rtn.setMerchantStoreType(merchantStoreBO != null ? merchantStoreBO.getManageTypeEnum() : null);
        return successGet(rtn);
    }


    /**
     * 修改登录密码
     *
     * @param id          ID
     * @param originalPwd 原始密码
     * @param newPwd      新密码
     */
    @RequestMapping(value = "updateLoginPwd/{id}", method = RequestMethod.PUT)
    public Result updateLoginPwd(@PathVariable Long id, @RequestParam String originalPwd, @RequestParam String newPwd) {
        MerchantBO merchantBO = merchantService.getMerchantBOById(id);
        if (merchantBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (!PwdUtil.verify(originalPwd, merchantBO.getPwd())) {
            return successGet(ResultCode.VERIFY_PWD_FAIL);
        }
        merchantService.updateLoginPwd(id, newPwd);
        return successCreated();
    }

    /**
     * 重置登录密码
     *
     * @param mobile 账号
     * @param newPwd 新密码
     */
    @RequestMapping(value = "resetLoginPwd/{mobile}", method = RequestMethod.PUT)
    public Result resetLoginPwd(@PathVariable String mobile, @RequestParam String newPwd) {
        MerchantBO merchantBO = merchantService.getMerchantByAccount(mobile);
        if (merchantBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        merchantService.updateLoginPwd(merchantBO.getId(), newPwd);
        return successCreated();
    }

    /**
     * 根据账号查询商户信息
     *
     * @param account 商户账号
     */
    @RequestMapping(value = "getMerchant/{account}", method = RequestMethod.GET)
    public Result<MerchantDTO> getMerchantByAccount(@PathVariable String account) {
        MerchantBO merchantBO = merchantService.getMerchantByAccount(account);
        if (merchantBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(MerchantConverter.convertDTO(merchantBO));
    }
    
    /**
     * 查询商户信息
     *
     * @param merchantId 商户账号
     */
    @RequestMapping(value = "selectMerchantInfo", method = RequestMethod.GET)
    public Result<MerchantSNSDTO> selectMerchantInfo(@RequestParam Long merchantId) {
        MerchantBO merchantBO = merchantService.selectMerchantInfo(merchantId);
        if (merchantBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(MerchantConverter.convertSNSDTO(merchantBO));
    }

    
    /**
     * 推荐商家
     *
     * @param pageQuery 用户id
     * @return
     * @author zhangrc
     * @date 2017/03/23
     */
    @RequestMapping(value = "getMerchantByInviter", method = RequestMethod.POST)
    public Result<Page<MerchantInviterDTO>> getMerchantByInviter(@RequestParam Long userId, @RequestBody MerchantInviterParam pageQuery,byte inviterType) {
        Page<MerchantInviterBO> pageBO = merchantService.getMerchantByInviter(userId, pageQuery,inviterType);
        Page<MerchantInviterDTO> pageDTOS = MerchantInviterConverter.convertPageMIDOTS(pageBO);
        return successGet(pageDTOS);
    }

    /**
     * 商户注册
     *
     * @param registerRealParam 商户注册信息
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result register(@RequestBody RegisterRealParam registerRealParam) {
        merchantService.register(registerRealParam);
        return successCreated();
    }

    /**
     * 增加推送、即时通讯token
     * @param id
     * @param cid
     * @return
     */
    @RequestMapping(value = "setGtAndRongYunInfo/{id}",method = RequestMethod.PUT)
    public Result setGtAndRongYunInfo(@PathVariable("id") Long id,@RequestParam("cid") String cid){
        Integer row =  merchantService.setGtAndRongYunInfo(id,cid);
        if(row == null || row <=0){
            successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }


    @RequestMapping(value = "findMessagePushList",method = RequestMethod.GET)
    public Result<List<MessagePushDTO>> findMessagePushList(@RequestParam(value = "area") String area){
        List<MessagePushBO> list = merchantService.findMessagePushList(area);
        if(list == null||list.isEmpty()){
          return   successGet(new ArrayList<>());
        }
        List<MessagePushDTO> messagePushDTOS = new ArrayList<>();
        for(MessagePushBO messagePushBO : list){
            MessagePushDTO messagePushDTO = new MessagePushDTO();
            messagePushDTO.setUserNum(messagePushBO.getUserNum());
            messagePushDTO.setGtCid(messagePushBO.getGtCid());
            messagePushDTOS.add(messagePushDTO);
        }
        return successGet(messagePushDTOS);
    }


    /**
     * 根据手机号查询userNum
     * @param moblie
     * @return
     */
    @RequestMapping(value = "findMessagePushByMobile", method = RequestMethod.GET)
    public MessagePushDTO findMessagePushByMobile(@RequestParam("moblie") String moblie){
        MessagePushBO messagePushBO = merchantService.findMessagePushByMobile(moblie);
        if(messagePushBO == null){
            return null;
        }
        MessagePushDTO messagePushDTO = new MessagePushDTO();
        messagePushDTO.setUserNum(messagePushBO.getUserNum());
        return messagePushDTO;
    }

    /**
     * 修改头像
     *
     * @param merchantId
     * @param headimg
     * @return
     */
    @RequestMapping(value = "saveHeadImage/{merchantId}", method = RequestMethod.POST)
    public Result<UserHeadImgDTO> saveHeadImage(@PathVariable("merchantId") Long merchantId, @RequestParam String headimg) {
        merchantService.updateHeadImg(headimg, merchantId);
        UserHeadImgDTO userHeadImgDTO = new UserHeadImgDTO();
        userHeadImgDTO.setHeadImg(headimg);
        return successCreated(userHeadImgDTO);
    }
    
    
    /**
     * 获取商家电话
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "selectMobile/{merchantId}", method = RequestMethod.GET)
    public Result<MobileDTO> selectMobile(@PathVariable("merchantId") Long merchantId) {
    	MerchantBO merchantBO=merchantService.getMerchantBOById(merchantId);
    	MobileDTO dto=new MobileDTO();
    	if(merchantBO != null){
            dto.setMobile(merchantBO.getMobile());
        }
        return successCreated(dto);
    }

    /**
     * 根据商家编号查询融云需要的信息
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "getRongYunInfo/{num}", method = RequestMethod.GET)
    public Result<RongYunDTO> getRongYunInfo(@PathVariable String num) {
        RongYunBO rongYunBO = merchantService.getRongYunInfoByNum(num);
        if (rongYunBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(RongYunConverter.convertDTO(rongYunBO));
    }

    /**
     * 根据商家ID查询商家基本信息
     * @param merchantId
     * @return
     * @author yangqh
     * @date 2017年5月22日 上午10:52:54
     */
    @RequestMapping(value = "getMerchantById/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantBaseInfoDTO> getMerchantById(@PathVariable Long merchantId) {
    	MerchantBaseInfoBO merchantBO = merchantService.getMerchantById(merchantId);
        if (merchantBO == null) {
            return successGet(ResultCode.ID_EMPTY);
        }
        MerchantBaseInfoDTO dto = new MerchantBaseInfoDTO();
        dto.setUserNum(merchantBO.getUserNum());
        return successGet(dto);
    }
    /**
     * 根据编号查询商家信息
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "getMerchantByNum", method = RequestMethod.GET)
    public Result<MerchantDTO> getMerchantByNum(@RequestParam String num) {
        MerchantBO merchantBO = merchantService.getMerchantByNum(num);
        if (merchantBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(MerchantConverter.convertDTO(merchantBO));
    }
    
    /**
     * 是否注册
     * @param mobile
     * @return
     */
    @RequestMapping(value = "isRegister", method = RequestMethod.GET)
    public Result<Boolean> isRegister(@RequestParam String mobile) {
    	Boolean flag = merchantService.isRegister(mobile);
        return successGet(flag);
    }

    @RequestMapping(value = "delMerchantGtPush", method = RequestMethod.PUT)
    public Result delMerchantGtPush(@RequestParam Long merchantId){
        merchantService.delMerchantGtPush(merchantId);
        return successCreated();
    }

    /**
     * 根据商家ID查询商家基本信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getMerchantView/{id}", method = RequestMethod.GET)
    public Result<MerchantViewDTO> getMerchantView(@PathVariable Long id){
        MerchantDOView merchantDOView = merchantService.getMerchantView(id);
        if(merchantDOView == null){
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        MerchantViewDTO merchantViewDTO = new MerchantViewDTO();
        merchantViewDTO.setAccount(merchantDOView.getAccount());
        merchantViewDTO.setStoreName(merchantDOView.getStoreName());
        return successGet(merchantViewDTO);
    }

    @RequestMapping(value = "getTotalCount",method = RequestMethod.GET)
    public Integer getTotalCount(){
        return merchantService.getTotalCount();
    }

    @RequestMapping(value = "getAccountList", method = RequestMethod.POST)
    public Result<Page<AccountDTO>> getAccountList(@RequestBody AccountParam param) {
        Page<MerchantBO> merchantBOPage = merchantService.getAccountList(param);
        if (merchantBOPage.getRecords().isEmpty()) {
            return successGet(new Page<>());
        }
        List<AccountDTO> accountDTOS = MerchantConverter.convertAccountDOTS(merchantBOPage.getRecords());
        Page<AccountDTO> pages = new Page<>();
        pages.setCurrentPage(merchantBOPage.getCurrentPage());
        pages.setTotalCount(merchantBOPage.getTotalCount());
        pages.setRecords(accountDTOS);
        return successGet(pages);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "freezeAccount", method = RequestMethod.PUT)
    public Result freezeAccount(@RequestBody @Validated FreezeParam param, BindingResult bindingResult){
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        merchantService.freezeAccount(param);
        return successCreated();
    }

    @RequestMapping(value = "getMerchantStoreProfileInfo", method = RequestMethod.GET)
    public Result<MerchantStoreProfileDTO> getMerchantStoreProfileInfo(@RequestParam(value ="id" ) Long id) {
        MerchantStoreProfileBO merchantStoreProfileBO = merchantStoreProfileService.findMerchantStoreInfo(id);
        if (merchantStoreProfileBO == null) {
            return successGet(ResultCode.MERCHANT_STORE_NO_EXIST);
        }
        MerchantStoreProfileDTO profileDTO = new MerchantStoreProfileDTO();
        profileDTO.setTypeEnum(MerchantStoreTypeEnum.getEnum(merchantStoreProfileBO.getManageType()));
        profileDTO.setMerchantStoreId(merchantStoreProfileBO.getMerchantStoreId());
        return successGet(profileDTO);
    }

    /**
     * 根据商家ID查询商家详细信息(包括门店、图片等信息)
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getMerchantDetail/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantDetailDTO> getMerchantDetailById(@PathVariable Long merchantId) {
        MerchantDetailBO detailBO = merchantService.getMerchantDetailById(merchantId);
        if (detailBO == null) {
            return successGet(new MerchantDetailDTO());
        }
        return successGet(MerchantConverter.convertDTO(detailBO));
    }

    /**
     * 保存商家登录日志
     *
     * @param loginLogParam
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveLoginLog", method = RequestMethod.POST)
    public Result saveLoginLog(@RequestBody UserLoginLogParam loginLogParam) {
        merchantService.saveLoginLog(loginLogParam);
        return successCreated();
    }

    /**
     * 根据id查询商家冻结相关信息
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getMerchantFreezeInfo/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantFreezeInfoDTO> getMerchantFreezeInfo(@PathVariable Long merchantId) {
        MerchantFreezeInfoBO freezeInfoBO = merchantService.getMerchantFreezeInfo(merchantId);
        if (freezeInfoBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        MerchantFreezeInfoDTO freezeInfoDTO = new MerchantFreezeInfoDTO();
        freezeInfoDTO.setAccount(freezeInfoBO.getAccount());
        freezeInfoDTO.setUserNum(freezeInfoBO.getUserNum());
        freezeInfoDTO.setFreezeReason(freezeInfoBO.getFreezeReason());
        return successGet(freezeInfoDTO);
    }
    
    
    /**
     * 短信登录
     * @param account
     * @return
     */
    @RequestMapping(value = "smsLogin/{account}", method = RequestMethod.GET)
    public Result<LoginUserDTO> smsLogin(@PathVariable("account") String account){
    	 MerchantBO merchantBO = merchantService.smsLogin(account);
         
         if (merchantBO == null) {
             return successGet(ResultCode.MEMBER_WRONG_PWD);
         }
         if(merchantBO.getIsFreeze()){
             return successGet(ResultCode.ACCOUNT_IS_FREEZE, merchantBO.getFreezeReason());
         }
         if (merchantBO.getStatus().byteValue() == UserStatusEnum.MEMBER_STATUS_NOVALID.val) {
             return successGet(ResultCode.ACCOUNT_IS_INVALID);
         }
         //更新最后登录时间
         merchantProfileService.updateLastLoginTime(merchantBO.getId());
         //更新推荐关系
         inviteService.updateInviteUserProfileInfo(merchantBO.getNum(),merchantBO.getId());
         
         LoginUserDTO rtn = LoginUserConverter.convert(merchantBO);
         MerchantStoreBO merchantStoreBO = merchantStoreService.selectMerchantStore(merchantBO.getId());
         rtn.setMerchantStoreType(merchantStoreBO != null ? merchantStoreBO.getManageTypeEnum() : null);
         return successGet(rtn);
    }

    /**
     * 获取完成E友邀请人的编号
     *
     * @param userNum
     * @return
     */
    @RequestMapping(value = "getRichTaskInviterNum", method = RequestMethod.GET)
    public Result<String> getRichTaskInviterNum(@RequestParam String userNum) {
        String inviterNum = merchantService.getRichTaskInviterNum(userNum);
        return successGet(inviterNum);
    }

    /**
     * 根据编号获取商家ID,店铺ID
     * @param userNum
     * @return
     */
    @RequestMapping(value = "getMerchantChatInfo", method = RequestMethod.GET)
    public Result<MerchantStoreInfoDTO> getMerchantChatInfo(@RequestParam("userNum") String userNum) {
        MerchantStoreBO storeBO = merchantService.getMerchantChatInfo(userNum);
        if (storeBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        MerchantStoreInfoDTO infoDTO = new MerchantStoreInfoDTO();
        infoDTO.setMerchantId(storeBO.getMerchantId());
        infoDTO.setStoreId(storeBO.getId());
        return successGet(infoDTO);
    }

}
