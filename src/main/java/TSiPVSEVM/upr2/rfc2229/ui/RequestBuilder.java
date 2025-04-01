package TSiPVSEVM.upr2.rfc2229.ui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class RequestBuilder {

    private final BufferedWriter writer;

    public RequestBuilder(OutputStream stream) {
        writer = new BufferedWriter(new OutputStreamWriter(stream), 1024);
    }

    public void doWrite() {
        try {
            writer.write(DictClientUI.getQuery());
        } catch (IOException e) {
            DictClientUI.setDefinition(e.getMessage());
        }
    }
}
