package com.signzy.message;

import com.signzy.message.type.MessageType;

public class CommandMessage extends AbstractMessage {

	private static final long serialVersionUID = 8725989330495240862L;

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
