package com.example.capstone.API;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PredictResponse{

	@SerializedName("data")
	private Predict data;

	@SerializedName("message")
	private String message;

	public void setData(Predict data){
		this.data = data;
	}

	public Predict getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}