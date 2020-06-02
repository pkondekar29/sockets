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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ClientSocket {

	private File messagesFile = null;
	private int count = 0;
	private final Random random = new Random();
	private final Logger LOG = Logger.getLogger(ClientSocket.class.getName());
	
	public ClientSocket() {
		try(Stream<String> lines = Files.lines(Paths.get(ClientSocket.class.getClassLoader().getResource("messages.txt").toURI()))) {
			// this.messagesFile = new File("messages.txt");
			this.count = (int) lines.count();
		} catch (IOException e) {
			throw new InstantiationError(e.getMessage());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
	public void startSendingMessages() throws Exception {
		String message = "start";
		while(true) {
			// 1. Get message from file
			// message = getRandomMessage();
			//message = sc.nextLine();
			LOG.info("Sending message: " + message);
			// 2. Convert to JSON format
			
			// 3. Send it over a socket
			try (Socket socket = new Socket("127.0.0.1", 5000); DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
				dos.write(message.getBytes());
				dos.flush();
				Thread.sleep(100L);
			} catch (IOException | InterruptedException e) {
				LOG.log(Level.FINEST, e.getMessage());
				throw e;
			} 
			
			// 4. Wait for ack
			
		}
	}

	private String getRandomMessage() {
		int lineNumber = random.nextInt(count);
		try(Stream<String> lines = Files.lines(Paths.get(ClientSocket.class.getClassLoader().getResource("messages.txt").toURI()))) {
			Optional<String> message = lines.skip(lineNumber)
				.findFirst();
			if(message.isPresent()) {
				return message.get();
			}
		} catch (IOException | URISyntaxException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return "m: finished";
	}
	
}
