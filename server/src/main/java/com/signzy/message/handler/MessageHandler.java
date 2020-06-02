package com.signzy.message.handler;

import com.signzy.message.Message;

public interface MessageHandler {

	public boolean handle(Message message);
	
}
