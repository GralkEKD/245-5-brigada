package TSiPVSEVM.upr2.rfc2229.client;

import TSiPVSEVM.upr2.rfc2229.client.handler.ResponseHandler;
import TSiPVSEVM.upr2.rfc2229.client.ui.DictClientUI;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DictClient {
    public static String[] dataBases;
    public static String[] strategies;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("127.0.0.1", 2628)) {
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            SwingUtilities.invokeAndWait(() -> new DictClientUI(new ResponseHandler(in, out)));

            while(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
