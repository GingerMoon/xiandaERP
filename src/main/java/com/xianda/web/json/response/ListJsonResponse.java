/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianda.web.json.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListJsonResponse <T> {

	private String Result = "ok";
	private List<T> Records = new ArrayList<T>();
	private long StartIndex = 0;
	private String Message = "";

	
	public ListJsonResponse() {
		
	}
	
	public ListJsonResponse(String Result, List<T> Records) {
		this.Result = Result;
		this.Records = Records;
	}

	public ListJsonResponse(List<T> Records, long StartIndex) {
		this.Records = Records;
		this.StartIndex = StartIndex;
	}
	
	public ListJsonResponse(String Result, String Message) {
		this.Result = Result;
		this.Message = Message;
	}

	@JsonProperty("Result")
	public String getResult() {
		return Result;
	}

	public void setResult(String Result) {
		this.Result = Result;
	}

	@JsonProperty("Records")
	public List<T> getRecords() {
		return Records;
	}

	public void setRecords(List<T> Records) {
		this.Records = Records;
	}

	@JsonProperty("StartIndex")
	public long getStartIndex() {
		return StartIndex;
	}

	public void setStartIndex(int StartIndex) {
		this.StartIndex = StartIndex;
	}
	
	@JsonProperty("Message")
	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}

}
