package com.signzy.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.signzy.client.message.Message;
import com.signzy.client.message.utils.JsonUtils;
import com.signzy.client.message.utils.MessageFactory;

public class ClientSocket {

	// TODO -> Get the Server IP and Server port while creating the client socket in constructor
	private static final int SERVER_PORT = 5000;
	private static final String SERVER_IP = "127.0.0.1";
	
	private int count = 0;
	
	private final Random random = new Random();
	
	private static final Logger LOG = Logger.getLogger(ClientSocket.class.getName());
	
	public ClientSocket() {
		try(Stream<String> lines = Files.lines(Paths.get(ClientSocket.class.getClassLoader().getResource("messages.txt").toURI()))) {
			this.count = (int) lines.count();
		} catch (IOException e) {
			throw new InstantiationError(e.getMessage());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
	public void startSendingMessages() throws Exception {
		Message message = null;
		while(true) {
			// 1. Get message from file
			message = getRandomMessage();
			LOG.info("Sending message: " + message);
			// 2. Convert to JSON format
			String jsonStr = JsonUtils.getJson(message);
			// TODO -> Encrypt it
			
			// 3. Send it over a socket
			try (Socket socket = new Socket(SERVER_IP, SERVER_PORT); DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
				dos.write(jsonStr.getBytes());
				dos.flush();
				// 4. Wait for ack
			} catch (IOException e) {
				LOG.log(Level.FINEST, e.getMessage());
				throw e;
			} 
		}
	}

	private Message getRandomMessage() {
		int lineNumber = random.nextInt(count);
		try(Stream<String> lines = Files.lines(Paths.get(ClientSocket.class.getClassLoader().getResource("messages.txt").toURI()))) {
			Optional<String> line = lines.skip(lineNumber)
				.findFirst();
			if(line.isPresent()) {
				return MessageFactory.getMessage(line.get());
			} else {
				return null;
			}
		} catch (IOException | URISyntaxException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
}
