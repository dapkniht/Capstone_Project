package com.example.capstone.API;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setImage(String image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage(){
		return image;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}
}