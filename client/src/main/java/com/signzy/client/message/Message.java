package com.signzy.client.message;

import java.io.Serializable;

import com.signzy.client.message.type.MessageType;

/**
 *	Message interface for any kind of message 
 * 
 *
 */
public interface Message extends Serializable {

	public String getMessage();
	
	public MessageType getMessageType();
}
