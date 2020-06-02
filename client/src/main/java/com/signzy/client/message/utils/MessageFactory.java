package com.signzy.client.message.utils;

import com.signzy.client.message.CommandMessage;
import com.signzy.client.message.Message;
import com.signzy.client.message.TextMessage;
import com.signzy.client.message.type.MessageType;

public class MessageFactory {

	private MessageFactory() {}
	
	public static Message getMessage(String message) {
		String[] splitString = message.split(":");
		if(MessageType.COMMAND.toString().equals(splitString[0])) {
			return new CommandMessage(splitString[1]);
		} else {
			return new TextMessage(splitString[1]);
		}
	}
	
}
