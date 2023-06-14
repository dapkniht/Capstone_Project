package com.example.capstone.API;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("carbohydrates")
	private String carbohydrates;

	@SerializedName("image")
	private String image;

	@SerializedName("potassium")
	private String potassium;

	@SerializedName("calcium")
	private String calcium;

	@SerializedName("vitamin_c")
	private String vitaminC;

	@SerializedName("portion")
	private Object portion;

	@SerializedName("vitamin_d")
	private String vitaminD;

	@SerializedName("description")
	private String description;

	@SerializedName("magnesium")
	private String magnesium;

	@SerializedName("scientific_name")
	private String scientificName;

	@SerializedName("vitamin_b6")
	private String vitaminB6;

	@SerializedName("calories")
	private String calories;

	@SerializedName("sodium")
	private String sodium;

	@SerializedName("colestrol")
	private String colestrol;

	@SerializedName("name")
	private String name;

	@SerializedName("fat")
	private String fat;

	@SerializedName("iron")
	private String iron;

	@SerializedName("vitamin_b12")
	private String vitaminB12;

	@SerializedName("id")
	private String id;

	@SerializedName("sugar")
	private String sugar;

	public void setCarbohydrates(String carbohydrates){
		this.carbohydrates = carbohydrates;
	}

	public String getCarbohydrates(){
		return carbohydrates;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPotassium(String potassium){
		this.potassium = potassium;
	}

	public String getPotassium(){
		return potassium;
	}

	public void setCalcium(String calcium){
		this.calcium = calcium;
	}

	public String getCalcium(){
		return calcium;
	}

	public void setVitaminC(String vitaminC){
		this.vitaminC = vitaminC;
	}

	public String getVitaminC(){
		return vitaminC;
	}

	public void setPortion(Object portion){
		this.portion = portion;
	}

	public Object getPortion(){
		return portion;
	}

	public void setVitaminD(String vitaminD){
		this.vitaminD = vitaminD;
	}

	public String getVitaminD(){
		return vitaminD;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setMagnesium(String magnesium){
		this.magnesium = magnesium;
	}

	public String getMagnesium(){
		return magnesium;
	}

	public void setScientificName(String scientificName){
		this.scientificName = scientificName;
	}

	public String getScientificName(){
		return scientificName;
	}

	public void setVitaminB6(String vitaminB6){
		this.vitaminB6 = vitaminB6;
	}

	public String getVitaminB6(){
		return vitaminB6;
	}

	public void setCalories(String calories){
		this.calories = calories;
	}

	public String getCalories(){
		return calories;
	}

	public void setSodium(String sodium){
		this.sodium = sodium;
	}

	public String getSodium(){
		return sodium;
	}

	public void setColestrol(String colestrol){
		this.colestrol = colestrol;
	}

	public String getColestrol(){
		return colestrol;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setFat(String fat){
		this.fat = fat;
	}

	public String getFat(){
		return fat;
	}

	public void setIron(String iron){
		this.iron = iron;
	}

	public String getIron(){
		return iron;
	}

	public void setVitaminB12(String vitaminB12){
		this.vitaminB12 = vitaminB12;
	}

	public String getVitaminB12(){
		return vitaminB12;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSugar(String sugar){
		this.sugar = sugar;
	}

	public String getSugar(){
		return sugar;
	}
}