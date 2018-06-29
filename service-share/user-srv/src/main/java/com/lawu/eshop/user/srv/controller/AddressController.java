package com.lawu.eshop.user.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.eshop.user.param.AddressParam;
import com.lawu.eshop.user.param.OperatorAddressParam;
import com.lawu.eshop.user.srv.bo.AddressBO;
import com.lawu.eshop.user.srv.converter.AddressConverter;
import com.lawu.eshop.user.srv.service.AddressService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 描述：收货地址管理
 * 
 * @author zhangrc
 * @date 2017/03/22
 *
 */
@RestController
@RequestMapping(value = "address/")
public class AddressController extends BaseController {

	@Autowired
	private AddressService addressService;

	/**
	 * 根据用户编号 查询用户所有地址
	 * 
	 * @param userNum
	 *            用户编号
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "selectByUserNum/{userNum}", method = RequestMethod.GET)
	public Result<List<AddressDTO>> selectByUserNum(@PathVariable("userNum") String userNum) {

		List<AddressBO> addressBOS = addressService.selectByUserNum(userNum);

		return successGet(AddressConverter.convertListDOTS(addressBOS));
	}

	/**
	 * 根据用户编号 添加收货地址
	 * 
	 * @param userNum
	 *            用户编号
	 * @param addressDO
	 *            保存地址参数
	 * @author Sunny
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveWithUserNum/{userNum}", method = RequestMethod.POST)
	public Result saveWithUserNum(@PathVariable("userNum") String userNum, @RequestBody AddressParam addressDO) {
		int result = addressService.saveWithUserNum(userNum, addressDO);

		if (result != ResultCode.SUCCESS) {
			return successCreated(result);
		}

		return successCreated();
	}

    /**
     * 通过id单个查询地址
     * 
     * @param id
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月18日
     * @updateDate 2017年12月18日
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Result<AddressDTO> get(@PathVariable Long id,
            @RequestParam(name = "userNum", required = false) String userNum) {
        AddressBO addressBO = addressService.get(id);
        if (addressBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA, "地址数据不存在");
        }
        if (userNum != null && !addressBO.getUserNum().equals(userNum)) {
            return successGet(ResultCode.ILLEGAL_OPERATION, "非法操作地址数据");
        }
        return successGet(AddressConverter.convertDTO(addressBO));
    }

	/**
	 * 修改地址
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public Result update(@RequestBody AddressParam addressParam, @PathVariable Long id, @RequestParam String userNum) {
		Integer i = addressService.update(addressParam, id, userNum);
		if (i > 0) {
			return successCreated(ResultCode.SUCCESS);
		} else {
			return successCreated(ResultCode.FAIL);
		}
	}

	/**
	 * 删除地址
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
	public Result remove(@PathVariable Long id,@RequestParam String userNum) {
		boolean flag=addressService.isCheckAddress(id, userNum);
		if(!flag){
			return successCreated(ResultCode.NOT_FOUND_DATA);
		}
		Integer i = addressService.remove(id);
		
		if (i > 0) {
			return successCreated();
		} else {
			return successCreated(ResultCode.FAIL);
		}
	}

	/**
	 * 修改默认地址
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateDefault/{id}", method = RequestMethod.GET)
	public Result updateDefault(@PathVariable Long id, @RequestParam String userNum) {
		boolean flag=addressService.isCheckAddress(id,userNum);
		if(!flag)
			return successCreated(ResultCode.NOT_FOUND_DATA);
		Integer i = addressService.updateDefault(id, userNum);
		if (i > 0) {
			return successCreated(ResultCode.SUCCESS);
		} else {
			return successCreated(ResultCode.FAIL);
		}
	}
	
	
	/**
	 * 地址管理
	 * @param param
	 * @return
	 * @author zhangrc
	 */
	@RequestMapping(value = "operatorAddressPage", method = RequestMethod.POST)
	public Result<Page<AddressDTO>> operatorAddressPage(@RequestBody OperatorAddressParam param) {

		Page<AddressBO> page = addressService.operatorAddressPage(param);
		Page<AddressDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(page.getCurrentPage());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setRecords(AddressConverter.convertListDOTS(page.getRecords()));
		return successCreated(pageDTO);
	}

}
