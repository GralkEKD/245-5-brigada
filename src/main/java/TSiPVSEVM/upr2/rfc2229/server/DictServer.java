package TSiPVSEVM.upr2.rfc2229.server;

import TSiPVSEVM.upr2.rfc2229.server.handler.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictServer {

    private final static Logger LOGGER = Logger.getLogger(DictServer.class.getName());

    private final static int PORT = 2628;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        LOGGER.log(Level.FINE, "Socket created, listening port " + serverSocket.getLocalPort());
        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "IOException occurred: " + e.getMessage());
            }

            new DictConnectionThread(clientSocket).start();
        }
    }

}
