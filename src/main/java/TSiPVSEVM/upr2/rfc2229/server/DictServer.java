package TSiPVSEVM.upr2.rfc2229.server;

import TSiPVSEVM.upr2.rfc2229.server.handler.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DictServer {

    private final static int PORT = 2628;

    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            try {
                System.out.println("Socket created, listening port " + serverSocket.getLocalPort());
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection: " + clientSocket.getLocalAddress());

                InputStream is = clientSocket.getInputStream();
                OutputStream os = clientSocket.getOutputStream();

                RequestHandler requestHandler = new RequestHandler(is, os);

                requestHandler.sendInitialResponse();
                System.out.println("Initial response sent");
                while (requestHandler.isConnectionOpen()) {
                    requestHandler.doHandle();
                    System.out.println("Request handled");
                }
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException occurred: " + e.getMessage());
            }
        }
    }
}
