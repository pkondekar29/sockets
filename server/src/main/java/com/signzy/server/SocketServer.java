package com.signzy.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.signzy.configuration.ServerConfiguration;

public class SocketServer {

    private static final Logger LOG = Logger.getLogger(SocketServer.class.getName());

    private ServerSocket serverSocket;

    public SocketServer(){
        try {
            init();
		} catch (IOException e) {
			throw new InstantiationError(e.getMessage());
		}
    }

    public void startServer() throws IOException {
        String message = null;
        while(true) {
            try(Socket socket = getServerSocket().accept(); BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            	synchronized (SocketServer.class) {
            		message = reader.readLine();
                	LOG.info("Received message: " + message);
                	
				}
            } catch (IOException e) {
                LOG.log(Level.FINEST, "Error in accepting the message", e);
            } catch(Exception e) {
            	LOG.log(Level.FINEST, "unexpected error");
            }
        }
    }

    private ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    private void init() throws IOException {
        try {
            this.serverSocket = new ServerSocket(ServerConfiguration.PORT);
		} catch (IOException e) {
            LOG.log(Level.SEVERE, String.format("Could not open server socket at port: %s", ServerConfiguration.PORT));
            throw e;
		}
    }

}

