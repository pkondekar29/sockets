package com.signzy.message.factory;

import com.signzy.message.CommandMessage;
import com.signzy.message.Message;
import com.signzy.message.TextMessage;
import com.signzy.message.type.MessageType;

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
