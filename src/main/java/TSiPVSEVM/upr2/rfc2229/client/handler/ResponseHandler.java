package TSiPVSEVM.upr2.rfc2229.client.handler;

import java.io.*;

public class ResponseHandler {
    private static final int BUFF_SIZE = 1024;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    private String status;
    private String comment;
    String message;

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public ResponseHandler(InputStream in, OutputStream out) {
        reader = new BufferedReader(new InputStreamReader(in));
        writer = new BufferedWriter(new OutputStreamWriter(out));
        try {
            parseResponse();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void parseResponse() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            String line = reader.readLine();
            sb.append(line).append("\n");
        }

        message = sb.toString();

        System.out.print("message: " + message);

    }

    public void post(String query) throws IOException {
        writer.write(query);
        writer.flush();
        System.out.print("Posting query: " + query);
    }
}
