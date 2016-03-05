package com.xianda.web.json.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusiInfoJsonBean {
	private String id = "-1"; // ���
	private String mixingStationName = ""; // ����վ����
	private String mixingStationContactPersonName = ""; // ����վ��ϵ��
	private String mixingStationContactPersonPhone = ""; // ����վ��ϵ�˵绰
	private String constructionSiteName = ""; // ��������
	private String constructionContactPersonName = ""; // ���ظ��������� 
	private String constructionContactPersonPhone = ""; // ���ظ����˵绰
	private String beginDate = ""; // ����ʱ��
	private String endDate = ""; // �깤ʱ��
	private String utPricePumpSky = ""; // ����
	private String volumeCompleted = ""; // ��ɷ���
	private String volumeTotal = ""; // �ܷ���
	private String volumePercent = ""; // ������ɰٷֱ�
	
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
