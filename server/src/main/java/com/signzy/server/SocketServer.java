package com.signzy.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.signzy.configuration.ServerConfiguration;
import com.signzy.message.Message;
import com.signzy.message.handler.MessageHandler;
import com.signzy.message.utils.JsonUtils;
import com.signzy.queue.MessageQueue;
import com.signzy.security.RSAEncrypterDecrypter;
import com.signzy.server.message.handler.factory.MessageHandlerFactory;

/**
 *	Socket server to listen to clien messages 
 * 
 */
public class SocketServer {

    private static final Logger LOG = Logger.getLogger(SocketServer.class.getName());

    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMnvvKRha3UOBmBUzr0Y6JPpez6BiR6xFJFw6GPkFnF8md61YVxHrAJ53Zxs6JGvo5W8UBeSOfuiYFpDAnQU+XY/xYlcfcnaJE/slYQWPJGTRghu5WC3gvqlo81ip+dMbOYS6yUsUA0LqzXu/tNDkZ7Bh5LE9d9K4py+VORi32DlAgMBAAECgYEAwD4qMbfI7CQQ7+5s7trit8WkHcqF9qEQerGpm0dOEnZTqhC1Anv9BlJsJnoMIn26V1dyTC5aYvOflO6MTGSXY0lsT5WA+wkz5xPg3JV1hGkJ6m592i7JtgygjmyYvg5h+EKhYunBEwNlKS8kPFGeGtsTP5SFj02VpJTTvhTYUZUCQQDsj5qL2fOYfROqw/bDW8iGVdmYkYbI9Zxkf8d845lNZzaXEKRGMYLn0jbLupIsinYzBBLnLdonNofSYTn5J8+PAkEA2ofCCsiTCrqKIb2G2P9c09h2/EW5uC15V5QRVRfMD5+bz5FMABzjEeVHc1aGEqPTyOWk72hs63znKEI4kYhOSwJBAJ7V/UsR2MH2mZH7amDZ69ZnJmeOrxWX9J52PZfZqkGSn9Dm16ZZ8C9/slMwvp0xixPOFHMtEmFp9+CGb8qftHkCQDHj4Ib5t6gxYivFyy1rT1QGDVQWzwmfSB6YbUSSBEJk2/uNXl6AOc7yG1fXnxBOWqF43CjUHFm+0D8+rw96oPUCQA5VJMaU3RhQWzWSssOTqFINcBjvw5kU9Bz0RNukmSWCzO+ZqlfIKA5xDbToJnx4jMxND64QveAJWOWBGU1TK00=";
    
    private ServerSocket serverSocket;
    
    public SocketServer(){
        try {
            init();
		} catch (IOException e) {
			throw new InstantiationError(e.getMessage());
		}
    }

    /**
     * Starts listening to client messages
     * 
     * @throws IOException
     */
    public void startServer() throws IOException {
        String jsonStr = null;
        LOG.info("Server listening at " + ServerConfiguration.PORT);
        while(!isStopped()) {
        	// 1. Accept a connection from client
        	Socket socket = getServerSocket().accept();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            	
            	// 2. Read the json string
            	jsonStr = reader.readLine();
            	
            	// 3. Decrypt the string
            	String decryptedStr = RSAEncrypterDecrypter.decrypt(jsonStr, PRIVATE_KEY);
            	LOG.log(Level.FINE, String.format("Received message: %s", decryptedStr));
            	
            	// 4. Create a messge obj from the string
        		Message message = JsonUtils.getMessage(decryptedStr);
        		
        		// 5. Add to queue
        		MessageQueue.put(message);
        		
        		// 6. Poll the queue
        		message = MessageQueue.poll();
        		if(message != null) {
        			// 7. Get the message handler for the message
        			MessageHandler handler = MessageHandlerFactory.getHandler(message.getMessageType());
        			// 8. Handle the message
        			boolean handled = handler.handle(message);
        			// 9. Send the response
        			if(handled) {
        				writer.write("ACK" + "\n");
        			} else {
        				writer.write("NACK" + "\n");
        			}
        			writer.flush();
        		}
            } catch (IOException e) {
                LOG.log(Level.FINEST, "Error in accepting the message", e);
            } catch(Exception e) {
            	LOG.log(Level.FINEST, "unexpected error");
            }
        }
    }

    private boolean isStopped() {
		return false;
	}

	private ServerSocket getServerSocket() {
        return this.serverSocket;
    }

	/**
	 * Initialize the server socket
	 * 
	 * @throws IOException
	 */
    private void init() throws IOException {
        try {
            this.serverSocket = new ServerSocket(ServerConfiguration.PORT);
		} catch (IOException e) {
            LOG.log(Level.SEVERE, String.format("Could not open server socket at port: %s", ServerConfiguration.PORT));
            throw e;
		}
    }

}

