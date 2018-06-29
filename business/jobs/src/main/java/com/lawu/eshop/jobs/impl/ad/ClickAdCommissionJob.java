package com.lawu.eshop.jobs.impl.ad;

import java.util.List;

import com.lawu.eshop.ad.dto.MemberAdRecodeCommissionDTO;
import com.lawu.eshop.jobs.service.AdSrvService;
import com.lawu.eshop.jobs.service.ClickAdCommissionRefactorService;
import com.lawu.eshop.jobs.service.ClickAdCommissionService;
import com.lawu.jobsextend.AbstractPageJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * <p>
 * Description: 点击广告提成
 * </p>
 * @author Yangqh
 * @date 2017年4月24日 下午3:31:10
 *
 */
public class ClickAdCommissionJob extends AbstractPageJob<MemberAdRecodeCommissionDTO> {

    private static Logger logger = LoggerFactory.getLogger(ClickAdCommissionJob.class);

    @Autowired
    private ClickAdCommissionService clickAdCommissionService;
    @Autowired
    private AdSrvService adService;
    @Autowired
    private ClickAdCommissionRefactorService clickAdCommissionRefactorService;

    @Override
    public boolean isStatusData() {
        return true;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }

    @Override
    public void executeSingle(MemberAdRecodeCommissionDTO memberAdRecodeCommissionDTO) {
        //clickAdCommissionService.executeAutoClickAdCommission(memberAdRecodeCommissionDTO,false);
        clickAdCommissionRefactorService.executeRefactor(memberAdRecodeCommissionDTO);
    }

    @Override
    public List<MemberAdRecodeCommissionDTO> queryPage(int offset, int pageSize) {
        List<MemberAdRecodeCommissionDTO> ads = adService.getNoneCommissionAds(offset,pageSize);
        return ads;
    }
}
