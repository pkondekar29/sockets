package com.signzy.message.handler;

import com.signzy.message.Message;

/**
 *	Handler to handle {@link Message} 
 * 
 */
public interface MessageHandler {

	/**
	 * API to handle a input {@link Message}
	 * 
	 * The API returns true if handled successfully, else false
	 * 
	 * @param message
	 * @return
	 */
	public boolean handle(Message message);
	
}
