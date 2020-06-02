package com.signzy.message;

import java.io.Serializable;

import com.signzy.message.type.MessageType;

/**
 *	Interface for all the messages in the system 
 * 
 */
public interface Message extends Serializable {

	/**
	 * Returns message string 
	 * 
	 * @return
	 */
	public String getMessage();
	
	/**
	 * Returns the {@link MessageType} of message
	 * 
	 * @return
	 */
	public MessageType getMessageType();
}
