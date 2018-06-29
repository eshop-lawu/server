package com.lawu.eshop.property.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 运营平台保证金查询条件参数对象
 * </p>
 * @author Yangqh
 * @date 2017年4月15日 下午1:23:17
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessDepositQueryParam  extends AbstractPageParam{

	@ApiParam(name = "content", value = "搜索文本")
	private String content;

	@ApiParam(name = "regionPath", value = "区域路径(格式：省ID/市ID/区ID)")
	private String regionPath;

	@ApiParam(name = "beginDate", required = true, value = "开始时间")
	private String beginDate;
	
	@ApiParam(name = "endDate", required = true, value = "结束时间")
	private String endDate;

	@ApiParam(name = "businessDepositStatusEnum", required = true, value = "状态")
	private BusinessDepositStatusEnum businessDepositStatusEnum;
	
	@ApiParam(name = "transactionPayTypeEnum", value = "支付方式")
	private TransactionPayTypeEnum transactionPayTypeEnum;

	//bootsrap table列排序
	private String sortName;
	private String sortOrder;
	
	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BusinessDepositStatusEnum getBusinessDepositStatusEnum() {
		return businessDepositStatusEnum;
	}

	public void setBusinessDepositStatusEnum(BusinessDepositStatusEnum businessDepositStatusEnum) {
		this.businessDepositStatusEnum = businessDepositStatusEnum;
	}

	public TransactionPayTypeEnum getTransactionPayTypeEnum() {
		return transactionPayTypeEnum;
	}

	public void setTransactionPayTypeEnum(TransactionPayTypeEnum transactionPayTypeEnum) {
		this.transactionPayTypeEnum = transactionPayTypeEnum;
	}

}
