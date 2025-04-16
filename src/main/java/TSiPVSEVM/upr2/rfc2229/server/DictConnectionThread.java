package TSiPVSEVM.upr2.rfc2229.server;

import TSiPVSEVM.upr2.rfc2229.server.handler.RequestHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictConnectionThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(DictConnectionThread.class.getName());

    private final Socket clientSocket;

    public DictConnectionThread(Socket socket) {
        if (!Objects.isNull(socket)) clientSocket = socket;
        else {
            LOGGER.log(Level.SEVERE, "Client Socket is not initialized properly");
            clientSocket = null;

        }
    }

    @Override
    public void run() {
        try {
            LOGGER.log(Level.FINE, "Accepted connection: " + clientSocket.getInetAddress());

            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();

            RequestHandler requestHandler = new RequestHandler(is, os);

            requestHandler.sendInitialResponse();
            LOGGER.log(Level.FINE, "Initial response sent");
            while (requestHandler.isConnectionOpen()) {
                requestHandler.doHandle();
                LOGGER.log(Level.FINE, "Request handled");
            }
            if (!clientSocket.isClosed()) clientSocket.close();
            LOGGER.log(Level.FINE, "Connection closed");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred: " + e.getMessage());
        }
    }
}
