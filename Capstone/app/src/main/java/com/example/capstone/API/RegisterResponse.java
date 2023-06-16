package com.example.capstone.API;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse{

	@SerializedName("data")
	private Register data;

	@SerializedName("message")
	private String message;

	public void setData(Register data){
		this.data = data;
	}

	public Register getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}