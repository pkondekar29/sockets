package com.signzy.client.message;

import java.io.Serializable;

import com.signzy.client.message.type.MessageType;

public interface Message extends Serializable {

	public String getMessage();
	
	public MessageType getMessageType();
}
