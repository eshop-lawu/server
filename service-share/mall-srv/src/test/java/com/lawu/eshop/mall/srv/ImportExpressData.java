package com.lawu.eshop.mall.srv;

import java.io.IOException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.lawu.utils.ExcelUtil;
import com.lawu.utils.PinyinUtil;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月22日
 */
public class ImportExpressData {

    @Ignore
	@Test
	public void importExpressData() throws IOException {
		String path = "C:/Users/Administrator/Desktop/快递100快递公司编码-标准国际类-（截止至2017.6.27）.xlsx";
		List<List<String>> list = ExcelUtil.readExcel(path, 1, 4);
		StringBuilder stringBuilder = new StringBuilder();
		String sql = "INSERT INTO `eshop_mall`.`express_company` (`id`, `code`, `name`, `homepage`, `tel`, `ordinal`, `status`, `gmt_modified`, `gmt_create`) VALUES ('%d', '%s', '%s', '', '', '%s', '%d', NOW(),NOW());%n";
		int count = 1; 
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				List<String> data = list.get(i);
				int isShow = 1;
				if (data.get(4) != null && !"CN".equals(data.get(4))) {
					isShow = 0;
				}
				stringBuilder.append(String.format(sql, count, data.get(1), data.get(0), (int)PinyinUtil.getPinYinHeadChar(data.get(1)).toUpperCase().charAt(0), isShow));
				count++;
			}
			System.out.println(stringBuilder.toString());
		}

	}
}