package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.eshop.activity.srv.bo.RedpacketSendRecordBO;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
public interface RedpacketSendRecordService {

    /**
     * 查询待处理的发放红包记录商户订单号
     *
     * @param offset
     * @param pageSize
     * @return
     * @author meishuquan
     */
    List<RedpacketSendRecordBO> listSendRedpacketMchBillno(int offset, int pageSize);

    /**
     * 保存发放红包记录、更新参与详情记录
     *
     * @param param
     * @author meishuquan
     */
    Long saveRedpacketSendRecord(RedpacketSendRecordParam param);

    /**
     * 更新红包发送结果
     *
     * @param param
     * @author meishuquan
     */
    void updateRedpacketSendRecordResult(RedpacketSendRecordParam param);

    /**
     * 更新发放红包记录、参与详情记录
     *
     * @param param
     * @author meishuquan
     */
    void updateRedpacketSendRecordStatus(RedpacketSendRecordParam param);

    /**
     * 根据参与详情id和商户订单号查询红包记录
     *
     * @param attendDetailId
     * @param mchBillno
     * @return
     * @author meishuquan
     */
    RedpacketSendRecordBO getRedpacketSendRecord(Long attendDetailId, String mchBillno);

}
