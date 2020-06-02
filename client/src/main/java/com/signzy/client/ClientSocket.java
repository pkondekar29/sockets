package com.signzy.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.signzy.client.message.Message;
import com.signzy.client.message.utils.JsonUtils;
import com.signzy.client.security.RSAEncrypterDecrypter;
import com.signzy.message.factory.MessageFactory;

/**
 *	Client socket which connects to Server 
 *
 */
public class ClientSocket {

	private int serverPort;
	private String serverIp;
	
	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJ77ykYWt1DgZgVM69GOiT6Xs+gYkesRSRcOhj5BZxfJnetWFcR6wCed2cbOiRr6OVvFAXkjn7omBaQwJ0FPl2P8WJXH3J2iRP7JWEFjyRk0YIbuVgt4L6paPNYqfnTGzmEuslLFANC6s17v7TQ5GewYeSxPXfSuKcvlTkYt9g5QIDAQAB";
	
	private int count = 0;
	
	private final Random random = new Random();
	
	private static final Logger LOG = Logger.getLogger(ClientSocket.class.getName());
	
	public ClientSocket(String ip, int port) {
		this.serverIp = ip;
		this.serverPort = port;
		try(Stream<String> lines = Files.lines(Paths.get(ClientSocket.class.getClassLoader().getResource("messages.txt").toURI()))) {
			this.count = (int) lines.count();
		} catch (IOException | URISyntaxException e) {
			throw new InstantiationError(e.getMessage());
		}
	}
	
	/**
	 * Sends message to server
	 * 
	 * @throws Exception
	 */
	public void startSendingMessages() throws Exception {
		Message message = null;
		while(!isStopped()) {
			// 1. Get message from file
			message = getRandomMessage();
			LOG.info("Sending message: " + message.getMessage());
			// 2. Convert to JSON format
			String jsonStr = JsonUtils.getJson(message);
			// 3. Encrypt the message
			String encryptedString = Base64.getEncoder().encodeToString(RSAEncrypterDecrypter.encrypt(jsonStr, PUBLIC_KEY));

			// 4. Send it over a socket
			try (Socket socket = new Socket(serverIp, serverPort); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
				// 4.1 Write message to socket 
				bw.write(encryptedString + "\n");
				bw.flush();
				
				// 5. Get for ack
				String serverRespone = br.readLine();
				LOG.info("Received server response: " + serverRespone);
			} catch (IOException e) {
				LOG.log(Level.FINEST, e.getMessage());
				throw e;
			} 
		}
	}

	/**
	 * Returns if the client is stopped
	 * 
	 * @return
	 */
	private boolean isStopped() {
		return false;
	}

	/**
	 * Returns a random message from file "message.txt"
	 * 
	 * @return
	 */
	private Message getRandomMessage() {
		int lineNumber = random.nextInt(count);
		try(Stream<String> lines = Files.lines(Paths.get(ClientSocket.class.getClassLoader().getResource("messages.txt").toURI()))) {
			Optional<String> line = lines.skip(lineNumber)
				.findFirst();
			return MessageFactory.getMessage(line.get());
		} catch (IOException | URISyntaxException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
}
