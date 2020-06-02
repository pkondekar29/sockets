package com.signzy;

import java.io.IOException;

import com.signzy.server.SocketServer;

/**
 *	Server application for socket 
 * 
 */
public final class ServerApp {
    private ServerApp() {
    }

    /**
     * @param args The arguments of the program.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        SocketServer server = new SocketServer();
        server.startServer();
    }
}
