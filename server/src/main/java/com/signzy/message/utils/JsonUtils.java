package com.signzy.message.utils;

import org.json.JSONObject;

import com.signzy.message.CommandMessage;
import com.signzy.message.Message;
import com.signzy.message.TextMessage;
import com.signzy.message.type.MessageType;

public class JsonUtils {

	private JsonUtils() {}

	public static Message getMessage(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		if(MessageType.COMMAND.name().equals(json.getString("messageType"))) {
			return new CommandMessage(json.getString("message"));
		} else {
			return new TextMessage(json.getString("message"));
		}
	}
	
}
