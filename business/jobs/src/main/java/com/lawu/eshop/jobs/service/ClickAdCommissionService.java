package com.lawu.eshop.jobs.service;

import com.lawu.eshop.ad.dto.MemberAdRecodeCommissionDTO;

public interface ClickAdCommissionService {


	void executeAutoClickAdCommission(MemberAdRecodeCommissionDTO memberAdRecodeCommissionDTO,boolean isTest);

}
