package com.lawu.eshop.mall.srv.mapper.extend;

import com.lawu.eshop.mall.param.MessageParam;
import com.lawu.eshop.mall.param.MessageQueryParam;
import com.lawu.eshop.mall.srv.domain.extend.MessageDOView;

import java.util.List;

/**
 * 站内信息扩展mapper
 * Created by Administrator on 2017/3/30.
 */
public interface MessageDOMMapperExtend {

    List<MessageDOView> selectByUserNum(MessageQueryParam param);

    int  selectCountByUserNum(String userNum);
}
