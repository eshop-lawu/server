package com.lawu.eshop.order.srv.utils.express.kuaidi100.bo;

/**
 * 快递100单号自动识别封装数据
 * 
 * @author jiangxinjun
 * @date 2017年9月27日
 */
public class Express {
	
	/**
	 * 物流公司编号
	 */
	private String comCode;
	
	private String id;
	
	private String noCount;
	
	private String noPre;
	
	private String startTime;

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoCount() {
		return noCount;
	}

	public void setNoCount(String noCount) {
		this.noCount = noCount;
	}

	public String getNoPre() {
		return noPre;
	}

	public void setNoPre(String noPre) {
		this.noPre = noPre;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}
