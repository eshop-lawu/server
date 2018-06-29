package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.srv.domain.extend.ReportRiseRateView;

public class ReportConvert {
	
	/**
	 * 隐藏默认的构造器
	 */
	private ReportConvert() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * 增量折线图转换，没数据的x轴默认填充并赋值0
	 * @param list
	 * @param x
	 * @return ReportRiseRateDTO
	 * @author yangqh
	 * @date 2017年5月2日 下午4:29:43
	 */
	public static ReportRiseRateDTO reportBrokeLineShow(List<ReportRiseRateView> list, int x) {
		for (int i = 0; i < x; i++) {
			boolean f = true;
			int j = i + 1;
			for (ReportRiseRateView view : list) {
				int num = Integer.parseInt(view.getKeyTxt());
				if (num == j) {
					f = false;
					break;
				}
			}
			if (f) {
				ReportRiseRateView view = new ReportRiseRateView();
				view.setKeyTxt(Integer.toString(j));
				view.setNum("0");
				list.add(i, view);
			}
		}
		
		List<String> dates = new ArrayList<>();
		List<String> nums = new ArrayList<>();
		for (ReportRiseRateView view : list) {
			dates.add(Integer.valueOf(view.getKeyTxt()).toString());
			nums.add(view.getNum());
		}
		ReportRiseRateDTO dto = new ReportRiseRateDTO();
		dto.setX(dates);
		dto.setY(nums);
		return dto;
	}
	
}
