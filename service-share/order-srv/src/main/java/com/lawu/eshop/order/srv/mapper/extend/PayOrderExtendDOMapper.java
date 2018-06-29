package com.lawu.eshop.order.srv.mapper.extend;

import com.lawu.eshop.order.param.PayOrderReportDataParam;
import com.lawu.eshop.order.srv.domain.extend.PayOrderExtendDOVew;
import com.lawu.eshop.order.srv.domain.extend.ReportFansSaleTransFormDO;
import com.lawu.eshop.order.srv.domain.extend.ReportRiseRateView;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PayOrderExtendDOMapper {

	List<ReportRiseRateView> payVolumeRiseRate(@Param("formatDate") String formatDate, @Param("flag") Byte flag,
			@Param("merchantId") Long merchantId, @Param("status") Byte status);

	String payVolumeTotal(@Param("formatDate") String formatDate, @Param("flag") Byte flag,
			@Param("merchantId") Long merchantId, @Param("status") Byte status);

	List<PayOrderExtendDOVew> getAutoCommentPayOrderList(Date nowTime);

    List<ReportFansSaleTransFormDO> selectByFansSaleTransformPay(PayOrderReportDataParam param);
}