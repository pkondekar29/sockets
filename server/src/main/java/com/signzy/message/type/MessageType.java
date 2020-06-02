package com.signzy.message.type;

/**
 *		Messages supported by server 
 * 
 */
public enum MessageType {

	/** Text message */
	TEXT("m"),
	/** Command message */
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
