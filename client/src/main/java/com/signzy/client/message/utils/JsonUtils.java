package com.signzy.client.message.utils;

import com.google.gson.Gson;
import com.signzy.client.message.Message;

public class JsonUtils {

	private JsonUtils() {}
	private static final Gson gson = new Gson();
	
	public static String getJson(Message message) {
		return gson.toJson(message);
	}
	
}
