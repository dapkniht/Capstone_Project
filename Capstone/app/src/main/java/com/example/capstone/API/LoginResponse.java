package com.example.capstone.API;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("data")
	private User data;

	@SerializedName("message")
	private String message;

	public void setData(User data){
		this.data = data;
	}

	public User getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}