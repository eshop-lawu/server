package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.srv.domain.extend.FansMerchantDOReportView;

public class ReportConvert {

	/**
	 * 增量折线图转换，没数据的x轴默认填充并赋值0
	 * @param list
	 * @param x
	 * @return ReportRiseRateDTO
	 * @author yangqh
	 * @date 2017年5月2日 下午4:29:43
	 */
	public static ReportRiseRateDTO reportBrokeLineShow(List<FansMerchantDOReportView> list, int x) {
		for (int i = 0; i < x; i++) {
			boolean f = true;
			int j = i + 1;
			for (FansMerchantDOReportView view : list) {
				int num = Integer.parseInt(view.getKeyTxt());
				if (num == j) {
					f = false;
					break;
				}
			}
			if (f) {
				FansMerchantDOReportView view = new FansMerchantDOReportView();
				view.setKeyTxt(Integer.toString(j));
				view.setNum("0");
				list.add(i, view);
			}
		}
		
		List<String> dates = new ArrayList<String>();
		List<String> nums = new ArrayList<String>();
		for (FansMerchantDOReportView view : list) {
			dates.add(Integer.valueOf(view.getKeyTxt()).toString());
			nums.add(view.getNum());
		}
		ReportRiseRateDTO dto = new ReportRiseRateDTO();
		dto.setX(dates);
		dto.setY(nums);
		return dto;
	}

}
