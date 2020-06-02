package com.signzy.message.type;

public enum MessageType {

	TEXT("m"),
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
