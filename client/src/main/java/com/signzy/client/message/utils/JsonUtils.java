package com.signzy.client.message.utils;

import com.google.gson.Gson;
import com.signzy.client.message.Message;

/**
 *	Json utils to convert the message into JSON format 
 * 
 */
public class JsonUtils {

	private JsonUtils() {}
	private static final Gson gson = new Gson();
	
	/**
	 * Return JSON message string from input {@link Message}
	 * 
	 * @param message
	 * @return
	 */
	public static String getJson(Message message) {
		return gson.toJson(message);
	}
	
}
