package com.signzy.client.message;

import com.signzy.client.message.type.MessageType;

public abstract class AbstractMessage implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8681036205680382647L;
	
	protected final MessageType messageType;
	
	public AbstractMessage(MessageType messageType) {
		this.messageType = messageType;
	}
	
	@Override
	public final MessageType getMessageType() {
		return this.messageType;
	}
}
