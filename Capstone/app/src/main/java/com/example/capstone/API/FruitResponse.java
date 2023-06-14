package com.example.capstone.API;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FruitResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	public List<DataItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}