package com.signzy.message;

import com.signzy.message.type.MessageType;

/**
 *	Text message implementation of {@link Message} 
 * 
 */
public class TextMessage extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1694544545398650672L;

	private String message;
	
	public TextMessage(String message) {
		// Initialise super class with MessageType -> TEXT
		super(MessageType.TEXT);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
