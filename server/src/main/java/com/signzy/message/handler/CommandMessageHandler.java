package com.signzy.message.handler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.signzy.message.Message;

public class CommandMessageHandler implements MessageHandler {

	private static final Logger LOG = Logger.getLogger(CommandMessageHandler.class.getName());
	
	@Override
	public boolean handle(Message message) {
		boolean commandExecuted = false;
		// Execute the command
		if(message.getMessage() != null) {
			try {
				Runtime runtime = Runtime.getRuntime();
				Process pr = runtime.exec(message.getMessage());
				LOG.info(String.format("Executed the command: %s", message.getMessage()));
				commandExecuted = (pr.exitValue() == 0);
			} catch (IOException e) {
				LOG.log(Level.FINEST, "Error in executing command: " + message.getMessage());
			}
		}
		return commandExecuted;
	}

}
