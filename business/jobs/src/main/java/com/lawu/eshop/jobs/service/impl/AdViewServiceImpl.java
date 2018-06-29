package com.lawu.eshop.jobs.service.impl;

import java.util.List;
import java.util.Set;

import com.lawu.eshop.jobs.service.AdSrvService;
import com.lawu.eshop.jobs.service.AdViewExtendService;
import com.lawu.eshop.jobs.service.AdViewService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.dto.ViewDTO;

@Service
public class AdViewServiceImpl extends BaseController implements AdViewExtendService {
	
	@Autowired
	private AdViewService adViewService;
	
	@Autowired
	private AdSrvService adSrvService;
	
	@Override
	public void updateViewCount() {
		 Result<List<ViewDTO>> rs=adSrvService.getAllAd();
		 List<ViewDTO> list=rs.getModel();
		 if(!list.isEmpty()){
			 for (ViewDTO dto : list) {
				 Result<Set<String>> result=adViewService.getAdviews(dto.getId().toString());
				 Integer count=result.getModel().size();
				 if(dto.getViewCount()!=count){
					 adSrvService.updateViewCount(dto.getId(), count);
				 }
			}
		 }
	}

}
