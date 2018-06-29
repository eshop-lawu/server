package com.lawu.eshop.mall.srv.bo;

/**
 * 意见反馈BO
 *
 * @author Sunny
 * @date 2017/3/24
 */
public class ExpressCompanyBO {

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 快递鸟编号
	 */
	private String code;
	
    /**
    * 快递100编号
    */
    private String kuaidi100Code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 网址
	 */
	private String homepage;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 排序
	 */
	private Integer ordinal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKuaidi100Code() {
		return kuaidi100Code;
	}

	public void setKuaidi100Code(String kuaidi100Code) {
		this.kuaidi100Code = kuaidi100Code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

}
