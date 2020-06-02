package com.signzy.client.message.type;

/**
 *	Type of messages supported 
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
