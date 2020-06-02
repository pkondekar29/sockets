package com.signzy.message.handler;

import java.util.logging.Logger;

import com.signzy.message.Message;
import com.signzy.message.TextMessage;

/**
 *	Handler for {@link TextMessage} 
 * 
 */
public class TextMessageHandler implements MessageHandler {

	private static final Logger LOG = Logger.getLogger(TextMessageHandler.class.getName());
	
	/**
	 * Reads the text message
	 */
	@Override
	public boolean handle(Message message) {
		LOG.info("Read the text message: " + message.getMessage());
		return true;
	}	
	
}
