package com.signzy.client;

public class CommandMessage implements Message {

	private static final long serialVersionUID = 8725989330495240862L;

	private String message = null;

	public CommandMessage(String message) {
		this.message  = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
