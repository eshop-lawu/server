package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.dto.RichDiamondRecordDTO;
import com.lawu.eshop.activity.srv.bo.RichDiamondRecordBO;
import com.lawu.eshop.activity.srv.domain.RichDiamondRecordDO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum; 

/**
 * @author lihj
 * @date 2018/5/2
 */
public class RichDiamondRecordConvert {
    
    private RichDiamondRecordConvert() {
    }
    
    public static List<RichDiamondRecordBO> convertRichDiamondRecordBO(List<RichDiamondRecordDO> list) {
        List<RichDiamondRecordBO> lt = Lists.newArrayList();
        if(null ==list || list.size()==0){
            return lt;
        }
        for(RichDiamondRecordDO rd :list){
            RichDiamondRecordBO bo = new RichDiamondRecordBO();
            bo.setRelatedId(rd.getRelatedId());
            bo.setAmount(rd.getAmount());
            bo.setDirectionEnum(RichPowerRecordDirectionEnum.getEnum(rd.getDirection()));
            bo.setId(rd.getId());
            bo.setSourceEnum(RichDiamondRecordSourceEnum.getEnum(rd.getSource()));
            bo.setTitle(rd.getTitle());
            bo.setTypeEnum(RichDiamondRecordTypeEnum.getEnum(rd.getType()));
            bo.setUserNum(rd.getUserNum());
            bo.setTakeTime(rd.getTakeTime());
            bo.setUserType(UserTypeEnum.getEnum(rd.getUserType()));
            lt.add(bo);
        }
        return lt;
    }
    
    public static List<RichDiamondRecordDTO> convert(List<RichDiamondRecordBO> source) {
        List<RichDiamondRecordDTO> target = new ArrayList<>();
        if(null == source || source.isEmpty()){
            return target;
        }
        for(RichDiamondRecordBO item : source){
            RichDiamondRecordDTO record = new RichDiamondRecordDTO();
            record.setRelatedId(item.getRelatedId());
            record.setAmount(item.getAmount());
            record.setDirection(item.getDirectionEnum());
            record.setId(item.getId());
            record.setSource(item.getSourceEnum());
            record.setTitle(item.getTitle());
            record.setType(item.getTypeEnum());
            record.setUserNum(item.getUserNum());
            record.setTakeTime(item.getTakeTime());
            record.setUserType(item.getUserType());
            target.add(record);
        }
        return target;
    }
}
