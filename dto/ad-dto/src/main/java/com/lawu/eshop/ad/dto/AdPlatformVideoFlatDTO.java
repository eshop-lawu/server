package com.lawu.eshop.ad.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class AdPlatformVideoFlatDTO {
	
	@ApiModelProperty(value = "三个视频")
	private List<AdPlatformVideoDTO> listVideo;
	
	@ApiModelProperty(value = "一个平面")
	private List<AdPlatformFlatDTO> listOneFlat;
	
	@ApiModelProperty(value = "两个平面")
	private List<AdPlatformFlatDTO> listTwoFlat;
	
	@ApiModelProperty(value = "五个平面")
	private List<AdPlatformFlatDTO> listFiveFlat;

	public List<AdPlatformVideoDTO> getListVideo() {
		return listVideo;
	}

	public void setListVideo(List<AdPlatformVideoDTO> listVideo) {
		this.listVideo = listVideo;
	}

	public List<AdPlatformFlatDTO> getListOneFlat() {
		return listOneFlat;
	}

	public void setListOneFlat(List<AdPlatformFlatDTO> listOneFlat) {
		this.listOneFlat = listOneFlat;
	}

	public List<AdPlatformFlatDTO> getListTwoFlat() {
		return listTwoFlat;
	}

	public void setListTwoFlat(List<AdPlatformFlatDTO> listTwoFlat) {
		this.listTwoFlat = listTwoFlat;
	}

	public List<AdPlatformFlatDTO> getListFiveFlat() {
		return listFiveFlat;
	}

	public void setListFiveFlat(List<AdPlatformFlatDTO> listFiveFlat) {
		this.listFiveFlat = listFiveFlat;
	}

	
	
	
}
