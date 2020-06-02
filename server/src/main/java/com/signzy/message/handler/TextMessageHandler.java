package com.signzy.message.handler;

import java.util.logging.Logger;

import com.signzy.message.Message;

public class TextMessageHandler implements MessageHandler {

	private static final Logger LOG = Logger.getLogger(TextMessageHandler.class.getName());
	
	@Override
	public boolean handle(Message message) {
		LOG.info("Read the text message: " + message.getMessage());
		return true;
	}	
	
}
