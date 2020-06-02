package com.signzy.client.message;

import com.signzy.client.message.type.MessageType;

/**
 * 	Command implementation of Message
 *	 
 */
public class CommandMessage extends AbstractMessage {

	private static final long serialVersionUID = 8725989330495240862L;

	/** Stores the message for the command */
	private String message = null;

	public CommandMessage(String message) {
		super(MessageType.COMMAND);
		this.message  = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
