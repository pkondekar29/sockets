package com.signzy;

import java.net.UnknownHostException;

import com.signzy.client.ClientSocket;

public final class ClientApp {
    private ClientApp() {
    }

    /**
     * @param args The arguments of the program.
     * @throws Exception 
     * @throws UnknownHostException 
     */
    public static void main(String[] args) throws Exception {
    	ClientSocket socket = new ClientSocket(args[0], Integer.parseInt(args[1]));
    	socket.startSendingMessages();
    }
}
