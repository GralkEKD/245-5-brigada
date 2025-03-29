package TSiPVSEVM.upr2.rfc2229.handler;

import java.io.InputStream;

public class ResponseHandler {

    private final InputStream is;

    public ResponseHandler(InputStream stream) {
        is = stream;
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
