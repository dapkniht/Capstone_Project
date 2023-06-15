package com.example.capstone.API;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HistoryResponse{

	@SerializedName("data")
	private List<History> data;

	@SerializedName("message")
	private String message;

	public void setData(List<History> data){
		this.data = data;
	}

	public List<History> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}