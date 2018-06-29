package com.lawu.eshop.mall.srv;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.mall.srv.domain.RegionDO;
import com.lawu.eshop.mall.srv.domain.RegionDOExample;
import com.lawu.eshop.mall.srv.mapper.RegionDOMapper;
import com.lawu.utils.HttpUtil;

/**
 * 
 * @author jiangxinjun
 * @createDate 2017年12月12日
 * @updateDate 2017年12月12日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class ImportRegionData {
    
    @Autowired
    private RegionDOMapper regionDOMapper;
    
    @Ignore
	@Test
	public void importData() throws IOException {
		String filePath = "C:\\Users\\Administrator\\Desktop\\list.json";
		String regionJson = FileUtils.readFileToString(new File(filePath));
		JSONObject jsonObject = (JSONObject) JSONObject.parse(regionJson);
		//System.out.println(jsonObject);
		
		// 查询所有的地区数据
		List<RegionDO> list = regionDOMapper.selectByExample(null);
		Map<Integer, RegionDO> map = new HashMap<>();
		for (RegionDO item : list) {
		    map.put(item.getId(), item);
		}
		
		StringBuilder sb = new StringBuilder();
		
		String insertTemplate = "INSERT INTO `eshop_mall`.`region` (`id`, `parent_id`, `path`, `level`, `name`, `longitude`, `latitude`) VALUES ('%d', '%d', '%s', '%d', '%s', %s, %s);%n";
        String updateTemplate = "UPDATE `eshop_mall`.`region` SET `id`=%d, `parent_id`='%d', `path`='%s', `level`='%d', `name`='%s', `longitude`=%s, `latitude`=%s WHERE (`id`= '%d');%n";
		
		jsonObject.forEach((key, value) -> {
		    Integer level = null;
		    Integer parentId = null;
		    String path = null;
		    // 去除末位的0
		    String code =  key.replaceAll("(0{2}|0{4})$", "");
		    Integer id = Integer.valueOf(code);
		    if (code.length() == 2) { // 省
		        level = 1;
		        parentId = 0;
		        path = code;
		    } else if (code.length() == 4) { //市
		        level = 2;
		        parentId = Integer.valueOf(code.substring(0, 2));
		        path = parentId.toString().concat("/").concat(code);
		    } else if (code.length() == 6) { //区县
		        level = 3;
		        parentId = Integer.valueOf(code.substring(0, 4));
		        path = code.substring(0, 2).concat("/").concat(parentId.toString()).concat("/").concat(code);
		    }
		    RegionDO regionDO = map.get(id);
		    String longitude = "NULL";
		    String latitude = "NULL";
		    if (regionDO != null) {
		        if (regionDO.getLongitude() != null) {
		            longitude = regionDO.getLongitude().toPlainString();
                }
		        if (regionDO.getLatitude() != null) {
		            latitude = regionDO.getLatitude().toPlainString();
		        }
		    }
		    sb.append(String.format(insertTemplate, id, parentId, path, level, value, longitude, latitude));
		});
		System.out.println(sb);
	}
    
    @Ignore
    @Test
    public void updateRegionLocation() throws InterruptedException {
        String url = "http://api.map.baidu.com/geocoder/v2/";
        Map<String, String> params = new HashMap<>();
        params.put("ak", "baDzNkfq1aVkqhKC7vyeH4CmhF8wSvER");
        params.put("output", "json");
        RegionDOExample example = new RegionDOExample();
        example.createCriteria().andLevelEqualTo((byte) 2);
        List<RegionDO> list = regionDOMapper.selectByExample(example);
        RegionDO record = null;
        for (RegionDO item : list) {
            params.put("address", item.getName());
            // 防止并发数过高导致请求失败
            Thread.sleep(200);
            String result = HttpUtil.doGet(url, params);
            JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
            System.out.println(jsonObject);
            if (jsonObject.getInteger("status").equals(0)) {
                JSONObject location = jsonObject.getJSONObject("result").getJSONObject("location");
                System.out.println("lng:" + location.getString("lng") + "," + "lat:" + location.getString("lat"));
                record = new RegionDO();
                record.setId(item.getId());
                record.setLatitude(new BigDecimal(location.getString("lat")));
                record.setLongitude(new BigDecimal(location.getString("lng")));
                regionDOMapper.updateByPrimaryKeySelective(record);
            }
        }
    }
}