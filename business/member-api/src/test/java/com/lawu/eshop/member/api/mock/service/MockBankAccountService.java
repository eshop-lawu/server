package com.lawu.eshop.member.api.mock.service;

import java.util.List;

import com.lawu.eshop.property.param.BandingWxDataParam;
import com.lawu.eshop.property.param.BandingWxEditDataParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.BankAccountService;
import com.lawu.eshop.property.dto.BankAccountDTO;
import com.lawu.eshop.property.dto.BankAccountNameDTO;
import com.lawu.eshop.property.dto.BankAccountThirdDTO;
import com.lawu.eshop.property.dto.BankThirdAccountDTO;
import com.lawu.eshop.property.param.BankAccountParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditDataParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockBankAccountService extends BaseController implements BankAccountService {


	@Override
	public Result saveBankAccount(@RequestParam("userNum") String userNum, @RequestBody BankAccountParam bankAccountParam) {
		return successCreated();
	}

	@Override
	public Result<List<BankAccountDTO>> selectMyBank(@RequestParam("userNum") String userNum) {
		return null;
	}

	@Override
	public Result delete(@PathVariable("id") Long id) {
		return successCreated();
	}

	@Override
	public Result<BankAccountDTO> selectAccount(@PathVariable("id") Long id) {
		BankAccountDTO dto = new BankAccountDTO();
		return successCreated(dto);
	}

	@Override
	public Result updateBankAccount(@PathVariable("id") Long id, @RequestParam("userNum") String userNum, @RequestBody BankAccountParam bankAccountParam) {
		return successCreated();
	}

	@Override
	public Result<BankAccountNameDTO> selectBankName(@RequestParam("userNum") String userNum) {
		return successCreated();
	}

	@Override
	public Result saveThirdAccount(@RequestBody BankAccountThirdAccountDataParam bankAccountThirdAccountDataParam) {
		return null;
	}

	@Override
	public Result<BankAccountThirdDTO> selectBankAccountByUserNum(@RequestParam("userNum") String userNum) {
		return null;
	}

	@Override
	public Result<BankThirdAccountDTO> selectThirdAccount(@PathVariable("id") Long id) {
		return null;
	}

	@Override
	public Result updateThirdAccount(@RequestBody BankAccountThirdAccountEditDataParam bankAccountThirdAccountEditDataParam) {
		return null;
	}

	@Override
	public Result saveWxAccount(@RequestBody BandingWxDataParam bandingWxDataParam) {
		return null;
	}

	@Override
	public Result updateWxAccount(@RequestBody BandingWxEditDataParam bandingWxEditDataParam) {
		return null;
	}
}
