package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.domain.RichAccountDO;
import com.lawu.eshop.activity.srv.mapper.RichAccountDOMapper;
import com.lawu.eshop.activity.srv.mock.service.MockRichConfigCacheService;
import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月9日
 * @updateDate 2018年5月9日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RichDiamondRecordServiceImplTest {
    
    @Autowired
    private RichDiamondRecordService richDiamondRecordService;
    
    @Autowired
    private RichAccountDOMapper richAccountDOMapper;
    
    @Autowired
    private RichConfigCacheService richConfigCacheService;
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void diamondDistribution() {
        MockRichConfigCacheService.diamondConfig.setInitReleaseTime(DateUtil.getDateFormat(new Date(), "HH:mm"));
        
        Result<DiamondConfigCacheDTO> getCacheDiamondConfigResult = richConfigCacheService.getCacheDiamondConfig();
        DiamondConfigCacheDTO model = getCacheDiamondConfigResult.getModel();
        
        for (int i = 0; i < 10; i++) {
            RichAccountDO rich =new RichAccountDO();
            rich.setUserNum("M0000" + i);
            rich.setOrderNum(i+1);
            rich.setDiamond(BigDecimal.ZERO);
            rich.setDiamondLucky(BigDecimal.ZERO);
            rich.setDiamondTotal(BigDecimal.ZERO);
            rich.setPower(model.getInitPower());//配置信息中获取
            rich.setPowerSnapshoot(0);
            rich.setLastTakeTime(new Date());
            rich.setGmtCreate(new Date());
            rich.setGmtModified(new Date());
            richAccountDOMapper.insert(rich);
        }
        
        richDiamondRecordService.diamondDistribution();
         
    }
}
