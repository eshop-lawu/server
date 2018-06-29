package com.lawu.eshop.mall.srv.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.srv.MallSrvApplication;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.RegionBO;
import com.lawu.eshop.mall.srv.domain.RegionDO;
import com.lawu.eshop.mall.srv.mapper.RegionDOMapper;
import com.lawu.eshop.mall.srv.service.RegionService;

/**
 * @author zhangyong
 * @date 2017/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class RegionServiceImplTest {

	private static Logger logger = LoggerFactory.getLogger(MallSrvApplication.class);
	
    @Autowired
    private RegionDOMapper regionDOMapper;
    @Autowired
    private RegionService regionService;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRegionFullName(){
        RegionDO regionDO = new RegionDO();
        regionDO.setName("广东省深圳市南山区");
        regionDO.setLatitude(BigDecimal.TEN);
        regionDO.setLongitude(BigDecimal.ONE);
        regionDO.setLevel((byte) 0X01);
        regionDO.setParentId(0);
        regionDO.setPath("11/11/11");
        regionDO.setId(110101);
        regionDOMapper.insert(regionDO);
        String name  = regionService.getRegionFullName(regionDO.getId());
        System.out.println(name);
        Assert.assertNotNull(name);
        Assert.assertTrue("广东省深圳市南山区".equals(name));
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAreaName(){
    	List<RegionDO> list = regionDOMapper.selectByExample(null);
    	for (RegionDO item : list) {
    		logger.error("getAreaName---" + item.getId() + ","+  item.getPath() + "," + item.getName());
    	}
    	
        //已经初始化一条广东省深圳市南山区数据
        String name = regionService.getAreaName("44/4403/440305");
        logger.error("getAreaName---" + name);
        logger.error("getAreaName---" + "南山区");
        Assert.assertTrue("南山区".equals(name));
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void  getRegionLevelTwo(){
        List<RegionBO> list = regionService.getRegionLevelTwo();
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRegionById(){
        RegionBO regionBO = regionService.getRegionById(440305);
        Assert.assertNotNull(regionBO);
        Assert.assertTrue("南山区".equals(regionBO.getName()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void  updateRegionLonLat(){
        regionService.updateRegionLonLat(440305,BigDecimal.TEN,BigDecimal.ONE);
        RegionDO regionDO = regionDOMapper.selectByPrimaryKey(440305);
        System.out.println(regionDO.getLongitude());
        Assert.assertNotNull(regionDO);
        Assert.assertEquals(0,BigDecimal.TEN.compareTo(regionDO.getLongitude()));
    }

}
