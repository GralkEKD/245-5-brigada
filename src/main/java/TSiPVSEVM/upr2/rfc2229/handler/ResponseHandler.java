package TSiPVSEVM.upr2.rfc2229.handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResponseHandler {

    private final BufferedReader reader;

    public ResponseHandler(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 1024);
    }

    public String doHandle(byte[] buffer, Command command) {
        StringBuilder handledText = new StringBuilder();
        switch (command) {
            case INIT: {

            }

            case DEFINE: {

            }

            case MATCH: {

            }
        }
        return handledText.toString();
    }
}
