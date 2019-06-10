package com.puffinz.utils.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class HttpResponse {
	
	private Integer status;
	private String res;
	private JsonObject jsonRes;
	
	public JsonObject getJsonRes() {
		Gson gson = new Gson();
		return gson.fromJson(res, JsonObject.class);
	}
}
