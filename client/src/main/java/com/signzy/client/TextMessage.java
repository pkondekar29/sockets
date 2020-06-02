package com.signzy.client;

public class TextMessage implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1694544545398650672L;

	private String message;
	
	public TextMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
