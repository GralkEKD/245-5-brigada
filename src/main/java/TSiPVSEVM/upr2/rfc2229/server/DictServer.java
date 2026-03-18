package TSiPVSEVM.upr2.rfc2229.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictServer {

    private final static Logger LOGGER = Logger.getLogger(DictServer.class.getName());

    private final static int PORT = 2628;

    public static void main(String[] args) {

        //noinspection InfiniteLoopStatement
        while (true)
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                LOGGER.log(Level.INFO, "Socket created, listening port " + serverSocket.getLocalPort());
                //noinspection InfiniteLoopStatement
                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        new DictConnectionThread(clientSocket).start();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "IOException occurred: " + e.getMessage());
                    }

                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
    }
}
