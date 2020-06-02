package com.signzy.message;

import java.io.Serializable;

import com.signzy.message.type.MessageType;

public interface Message extends Serializable {

	public String getMessage();
	
	public MessageType getMessageType();
}
