package com.lawu.eshop.product.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.product.srv.bo.SeckillActivityAttentionBO;
import com.lawu.eshop.product.srv.service.SeckillActivityAttentionService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 提醒关注的用户抢购活动即将开始
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
public class PromptSeckillActivityAboutStartJob extends AbstractTxPageJob<SeckillActivityAttentionBO> {

    @Autowired
    private SeckillActivityAttentionService seckillActivityAttentionService;
    
    @Override
    public List<SeckillActivityAttentionBO> queryPage(int offset, int pageSize) {
        return seckillActivityAttentionService.selectAboutStartSeckillActivityAttention(offset, pageSize);
    }

    @Override
    public void executeSingle(SeckillActivityAttentionBO entity) {
        seckillActivityAttentionService.promptSeckillActivityAboutStart(entity);
    }

    @Override
    public boolean isStatusData() {
        return false;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }
}
