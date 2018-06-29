package com.lawu.eshop.jobs.service;

import java.util.List;

import com.lawu.eshop.property.dto.IncomeMsgDTO;

/**
 * 收益通知
 * 
 * @author yangqh
 * @date 2017年7月3日
 */
public interface IncomeMsgService {

	void execute(List<IncomeMsgDTO> list);
}
