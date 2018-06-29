package com.lawu.eshop.property.srv.controller;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.dto.FreezeDTO;
import com.lawu.eshop.property.dto.OrderAssetInformationDTO;
import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.property.dto.PropertyInfoDTO;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyLoveAccountDTO;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.eshop.property.param.BackagePropertyinfoDataParam;
import com.lawu.eshop.property.param.FreezeQueryParam;
import com.lawu.eshop.property.param.PropertyInfoBackageParam;
import com.lawu.eshop.property.srv.bo.FreezeBO;
import com.lawu.eshop.property.srv.bo.PropertyBalanceBO;
import com.lawu.eshop.property.srv.bo.PropertyInfoBO;
import com.lawu.eshop.property.srv.bo.PropertyPointAndBalanceBO;
import com.lawu.eshop.property.srv.bo.PropertyPointBO;
import com.lawu.eshop.property.srv.converter.PropertyBalanceConverter;
import com.lawu.eshop.property.srv.converter.PropertyInfoConverter;
import com.lawu.eshop.property.srv.converter.PropertyPointConverter;
import com.lawu.eshop.property.srv.service.PropertyInfoService;
import com.lawu.eshop.property.srv.service.PropertyService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.PwdUtil;

/**
 * @author meishuquan
 * @date 2017/3/27
 */
@RestController
@RequestMapping(value = "propertyInfo/")
public class PropertyInfoController extends BaseController {

	@Autowired
	private PropertyInfoService propertyInfoService;

	@Autowired
	private PropertyService propertyService;

	/**
	 * 修改用户支付密码
	 *
	 * @param userNum
	 *            用户编号
	 * @param originalPwd
	 *            原始密码
	 * @param newPwd
	 *            新密码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updatePayPwd/{userNum}", method = RequestMethod.PUT)
	public Result updatePayPwd(@PathVariable String userNum, @RequestParam String originalPwd,
			@RequestParam String newPwd) {
		PropertyInfoBO propertyInfoBO = propertyInfoService.getPropertyInfoByUserNum(userNum);
		if (propertyInfoBO == null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		if (!PwdUtil.verify(originalPwd, propertyInfoBO.getPayPassword())) {
			return successGet(ResultCode.VERIFY_PWD_FAIL);
		}
		propertyInfoService.updatePayPwd(userNum, newPwd);
		return successCreated();
	}

	/**
	 * 重置用户支付密码
	 *
	 * @param userNum
	 *            用户编号
	 * @param newPwd
	 *            新密码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "resetPayPwd/{userNum}", method = RequestMethod.PUT)
	public Result resetPayPwd(@PathVariable String userNum, @RequestParam String newPwd) {
		PropertyInfoBO propertyInfoBO = propertyInfoService.getPropertyInfoByUserNum(userNum);
		if (propertyInfoBO == null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		propertyInfoService.updatePayPwd(userNum, newPwd);
		return successCreated();
	}

	/**
	 * 设置用户支付密码
	 *
	 * @param userNum
	 *            用户编号
	 * @param newPwd
	 *            新密码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "setPayPwd/{userNum}", method = RequestMethod.PUT)
	public Result setPayPwd(@PathVariable String userNum, @RequestParam String newPwd) {
		PropertyInfoBO propertyInfoBO = propertyInfoService.getPropertyInfoByUserNum(userNum);
		if (propertyInfoBO == null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		propertyInfoService.updatePayPwd(userNum, newPwd);
		return successCreated();
	}

	/**
	 * 查询用户是否设置支付密码
	 *
	 * @param userNum
	 *            用户编号
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "isSetPayPwd/{userNum}", method = RequestMethod.GET)
	public Result isSetPayPwd(@PathVariable String userNum) {
		PropertyInfoBO propertyInfoBO = propertyInfoService.getPropertyInfoByUserNum(userNum);
		if (propertyInfoBO == null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		if (StringUtils.isEmpty(propertyInfoBO.getPayPassword())) {
			return successGet(false);
		}
		return successGet(true);
	}

	/**
	 * 校验支付密码
	 * 
	 * @param userNum
	 *            用户编号
	 * @param payPwd
	 *            明文
	 * @return
	 */
	@RequestMapping(value = "varifyPayPwd", method = RequestMethod.GET)
	public Result<Boolean> varifyPayPwd(@RequestParam String userNum, @RequestParam String payPwd) {
		PropertyInfoBO propertyInfoBO = propertyInfoService.getPropertyInfoByUserNum(userNum);
		if (propertyInfoBO == null) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}
		if (StringUtils.isEmpty(propertyInfoBO.getPayPassword())) {
			return successGet(ResultCode.PAY_PWD_NULL);
		}
		if (PwdUtil.verify(payPwd, propertyInfoBO.getPayPassword())) {
			return successGet(true);
		}
		return successGet(false);
	}

	/**
	 * 根据用户编号获取资产余额
	 *
	 * @param userNum
	 *            用户编号
	 * @return
	 */
	@RequestMapping(value = "propertyBalance/{userNum}", method = RequestMethod.GET)
	public Result<PropertyBalanceDTO> getPropertyBalance(@PathVariable("userNum") String userNum) {
		PropertyBalanceBO propertyBalanceBO = propertyInfoService.getPropertyBalanceByUserNum(userNum);
		return successCreated(PropertyBalanceConverter.convert(propertyBalanceBO));
	}

	/**
	 * 根据用户编号获取用户积分
	 *
	 * @param userNum
	 *            用户编号
	 * @return
	 */
	@RequestMapping(value = "propertyPoint/{userNum}", method = RequestMethod.GET)
	public Result<PropertyPointDTO> getPropertyPoint(@PathVariable("userNum") String userNum) {
		PropertyPointBO propertyPointBO = propertyInfoService.getPropertyPointByUserNum(userNum);
		return successCreated(PropertyPointConverter.convert(propertyPointBO));
	}

	/**
	 * 查询用户商家财产积分余额
	 * 
	 * @param userNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPropertyInfoMoney/{userNum}", method = RequestMethod.GET)
	public Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@PathVariable("userNum") String userNum) {
		PropertyPointAndBalanceBO propertyPointBO = propertyInfoService.getPropertyInfoMoney(userNum);
		PropertyPointAndBalanceDTO dto = new PropertyPointAndBalanceDTO();
		dto.setBalance(propertyPointBO.getBalance().setScale(2,BigDecimal.ROUND_DOWN));
		dto.setPoint(propertyPointBO.getPoint().setScale(2,BigDecimal.ROUND_DOWN));
		dto.setFreeze(propertyPointBO.getFreeze().setScale(2,BigDecimal.ROUND_DOWN));
		String exchangeRateStr = propertyService.getValue(PropertyType.MEMBER_BALANCE_PAY_POINT_SCALE);
		BigDecimal exchangeRate = new BigDecimal(exchangeRateStr);
		exchangeRate = exchangeRate.divide(new BigDecimal(100));
		dto.setExchangeRate(exchangeRate);
		return successCreated(dto);
	}

	/**
	 * 操作用户积分财产
	 *
	 * @param userNum
	 *            用户编号
	 * @param column
	 *            列名：B-余额，P-积分，L-爱心账户
	 * @param flag
	 *            标记:A-加,M-减
	 * @param number
	 *            余额、积分、爱心账户的数额
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updatePoint/{userNum}", method = RequestMethod.POST)
	public Result updatePoint(@PathVariable("userNum") String userNum, @RequestParam String column,
			@RequestParam String flag, @RequestParam BigDecimal number) {
		int retCode = propertyInfoService.updatePropertyNumbers(userNum, column, flag, number);
		return successCreated(retCode);
	}

	/**
	 * 查询爱心账户
	 * 
	 * @param userNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "selectLoveAccount/{userNum}", method = RequestMethod.GET)
	public Result<PropertyLoveAccountDTO> selectLoveAccount(@PathVariable("userNum") String userNum){
		BigDecimal selectLoveAccount = propertyInfoService.selectLoveAccount(userNum);
		PropertyLoveAccountDTO dto = new PropertyLoveAccountDTO();
		dto.setLoveAccount(selectLoveAccount);
		return successCreated(dto);
	}

	/**
	 * 运营平台余额积分处理
	 * 
	 * @param dparam
	 * @return
	 * @author yangqh
	 * @date 2017年5月16日 下午2:36:23
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateBalanceAndPoint", method = RequestMethod.POST)
	public Result updateBalanceAndPoint(@RequestBody BackagePropertyinfoDataParam dparam) {
		int retCode = propertyInfoService.updateBalanceAndPoint(dparam);
		return successCreated(retCode);
	}

	/**
	 * 运营平台资金冻结操作
	 * @param userNum
	 * @param freeze
	 * @return
	 * @author yangqh
	 * @date 2017年5月16日 下午4:56:44
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updatePropertyinfoFreeze", method = RequestMethod.POST)
	public Result updatePropertyinfoFreeze(@RequestParam String userNum, @RequestParam PropertyinfoFreezeEnum freeze) {
		int retCode = propertyInfoService.updatePropertyinfoFreeze(userNum,freeze);
		return successCreated(retCode);
	}
	
	/**
	 * 运营平台查询用户资金冻结情况
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "getPropertyinfoPageList", method = RequestMethod.POST)
	public Result<Page<PropertyInfoDTO>> getPropertyinfoPageList(@RequestBody PropertyInfoBackageParam param)  {
		Page<PropertyInfoBO>  propertyInfoBOPage = propertyInfoService.getPropertyinfoPageList(param);
		Page<PropertyInfoDTO> page = new Page<>();
		page.setCurrentPage(propertyInfoBOPage.getCurrentPage());
		page.setTotalCount(propertyInfoBOPage.getTotalCount());
		page.setRecords(PropertyInfoConverter.convertDTO(propertyInfoBOPage.getRecords()));
		return successGet(page);
	}
	
	/**
	 * 获取用户是否冻结
	 * @param userNum
	 * @return 0-否、1-是、2-异常
	 * @author yangqh
	 * @date 2017年5月26日 上午11:08:32
	 */
	@RequestMapping(value = "getPropertyinfoFreeze/{userNum}", method = RequestMethod.GET)
	public Result<PropertyInfoFreezeDTO> getPropertyinfoFreeze(@PathVariable("userNum") String userNum)  {
		PropertyinfoFreezeEnum freeze = propertyInfoService.getPropertyinfoFreeze(userNum);
		if (freeze == null) {
			successGet(ResultCode.PROPERTYINFO_FREEZE_EXCEPITON);
		}
		PropertyInfoFreezeDTO rtn = new PropertyInfoFreezeDTO();
		rtn.setStatus(freeze);
		if (freeze == PropertyinfoFreezeEnum.YES) {
			rtn.setMessage(ResultCode.get(ResultCode.PROPERTYINFO_FREEZE_YES_WORD));
		}
		return successGet(rtn);
	}

	/**
	 * 冻结资金列表y
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "getFreezeList", method = RequestMethod.POST)
	public Result<Page<FreezeDTO>> getFreezeList(@RequestBody FreezeQueryParam param)  {
		Page<FreezeBO>  freezeBOPage = propertyInfoService.getFreezeList(param);
		Page<FreezeDTO> page = new Page<>();
		page.setCurrentPage(freezeBOPage.getCurrentPage());
		page.setTotalCount(freezeBOPage.getTotalCount());
		page.setRecords(PropertyInfoConverter.freezeConvertDTO(freezeBOPage.getRecords()));
		return successGet(page);
	}

	/**
	 * 校验积分账户
	 *
	 * @param userNum
	 * @param point
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "validatePoint", method = RequestMethod.GET)
	public Result validatePoint(@RequestParam String userNum, @RequestParam String point) {
		int retCode = propertyInfoService.validatePoint(userNum, point);
		return successGet(retCode);
	}

    /**
     * 根据用户编号查询订单所需要的资产信息
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月10日
     * @updateDate 2018年3月10日
     */
    @GetMapping(value = "orderAssetInformation/{userNum}")
    public Result<OrderAssetInformationDTO> orderAssetInformation(@PathVariable("userNum") String userNum) {
        PropertyPointAndBalanceBO propertyPointBO = propertyInfoService.getPropertyInfoMoney(userNum);
        OrderAssetInformationDTO dto = new OrderAssetInformationDTO();
        dto.setBalance(propertyPointBO.getBalance().setScale(2, BigDecimal.ROUND_DOWN));
        dto.setPoint(propertyPointBO.getPoint().setScale(2, BigDecimal.ROUND_DOWN));
        // 查看当前积分兑换比例
        String exchangeRateStr = propertyService.getValue(PropertyType.MEMBER_BALANCE_PAY_POINT_SCALE);
        dto.setExchangeRate(new BigDecimal(exchangeRateStr));
        return successCreated(dto);
    }
}
