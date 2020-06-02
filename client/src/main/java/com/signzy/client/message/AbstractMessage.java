package com.signzy.client.message;

import com.signzy.client.message.type.MessageType;

/**
 *	Abstract implementation of a message 
 * 
 */
public abstract class AbstractMessage implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8681036205680382647L;
	
	/** Stores the message type of message */
	protected final MessageType messageType;
	
	public AbstractMessage(MessageType messageType) {
		this.messageType = messageType;
	}
	
	@Override
	public final MessageType getMessageType() {
		return this.messageType;
	}
}
