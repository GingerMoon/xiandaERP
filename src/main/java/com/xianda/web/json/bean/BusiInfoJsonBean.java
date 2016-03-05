package com.xianda.web.json.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusiInfoJsonBean {
	private String id = "-1"; // 编号
	private String mixingStationName = ""; // 搅拌站名称
	private String mixingStationContactPersonName = ""; // 搅拌站联系人
	private String mixingStationContactPersonPhone = ""; // 搅拌站联系人电话
	private String constructionSiteName = ""; // 工地名称
	private String constructionContactPersonName = ""; // 工地负责人姓名 
	private String constructionContactPersonPhone = ""; // 工地负责人电话
	private String beginDate = ""; // 开工时间
	private String endDate = ""; // 完工时间
	private String utPricePumpSky = ""; // 单价
	private String volumeCompleted = ""; // 完成方量
	private String volumeTotal = ""; // 总方量
	private String volumePercent = ""; // 方量完成百分比
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("mixingStationName")
	public String getMixingStationName() {
		return mixingStationName;
	}
	public void setMixingStationName(String mixingStationName) {
		this.mixingStationName = mixingStationName;
	}
	
	@JsonProperty("mixingStationContactPersonName")
	public String getMixingStationContactPersonName() {
		return mixingStationContactPersonName;
	}
	public void setMixingStationContactPersonName(String mixingStationContactPersonName) {
		this.mixingStationContactPersonName = mixingStationContactPersonName;
	}
	
	@JsonProperty("mixingStationContactPersonPhone")
	public String getMixingStationContactPersonPhone() {
		return mixingStationContactPersonPhone;
	}
	public void setMixingStationContactPersonPhone(String mixingStationContactPersonPhone) {
		this.mixingStationContactPersonPhone = mixingStationContactPersonPhone;
	}

	@JsonProperty("constructionSiteName")
	public String getConstructionSiteName() {
		return constructionSiteName;
	}
	public void setConstructionSiteName(String constructionSiteName) {
		this.constructionSiteName = constructionSiteName;
	}
	
	@JsonProperty("constructionContactPersonName")
	public String getConstructionContactPersonName() {
		return constructionContactPersonName;
	}
	public void setConstructionContactPersonName(String constructionContactPersonName) {
		this.constructionContactPersonName = constructionContactPersonName;
	}
	
	@JsonProperty("constructionContactPersonPhone")
	public String getConstructionContactPersonPhone() {
		return constructionContactPersonPhone;
	}
	public void setConstructionContactPersonPhone(String constructionContactPersonPhone) {
		this.constructionContactPersonPhone = constructionContactPersonPhone;
	}
	
	@JsonProperty("beginDate")
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonProperty("endDate")
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@JsonProperty("utPricePumpSky")
	public String getUtPricePumpSky() {
		return utPricePumpSky;
	}
	public void setUtPricePumpSky(String utPricePumpSky) {
		this.utPricePumpSky = utPricePumpSky;
	}
		
	@JsonProperty("volumeCompleted")
	public String getVolumeCompleted() {
		return volumeCompleted;
	}
	public void setVolumeCompleted(String volumeCompleted) {
		this.volumeCompleted = volumeCompleted;
	}
	
	@JsonProperty("volumeTotal")
	public String getVolumeTotal() {
		return volumeTotal;
	}
	public void setVolumeTotal(String volumeTotal) {
		this.volumeTotal = volumeTotal;
	}
	
	@JsonProperty("volumePercent")
	public String getVolumePercent() {
		return volumePercent;
	}
	public void setVolumePercent(String volumePercent) {
		this.volumePercent = volumePercent;
	}
	
	
}
