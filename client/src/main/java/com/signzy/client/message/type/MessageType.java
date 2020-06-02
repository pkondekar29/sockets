package com.signzy.client.message.type;

public enum MessageType {

	MESSAGE("m"),
	COMMAND("cmd");
	
	private String type;
	
	private MessageType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	
}
