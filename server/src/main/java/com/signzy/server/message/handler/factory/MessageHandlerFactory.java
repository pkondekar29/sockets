package com.signzy.server.message.handler.factory;

import com.signzy.message.handler.CommandMessageHandler;
import com.signzy.message.handler.MessageHandler;
import com.signzy.message.handler.TextMessageHandler;
import com.signzy.message.type.MessageType;

public class MessageHandlerFactory {

	private MessageHandlerFactory() {}

	public static MessageHandler getHandler(MessageType messageType) {
		switch (messageType) {
			case COMMAND:
				return new CommandMessageHandler();
			case TEXT:
				return new TextMessageHandler();
			default:
				return null;
		}
	}
	
	
}
