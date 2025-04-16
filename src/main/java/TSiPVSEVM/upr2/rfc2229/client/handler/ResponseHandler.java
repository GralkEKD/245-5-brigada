package TSiPVSEVM.upr2.rfc2229.client.handler;

import java.io.*;

public class ResponseHandler {
    private static final int BUFF_SIZE = 1024;
    BufferedReader reader;
    BufferedWriter writer;

    int responseCode;
    String status;
    String comment;
    String message;

    public int getResponseCode() {
        return responseCode;
    }

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public ResponseHandler(InputStream in, OutputStream out) {
        reader = new BufferedReader(new InputStreamReader(in), BUFF_SIZE);
        writer = new BufferedWriter(new OutputStreamWriter(out), BUFF_SIZE);
    }

    public void parseResponse() throws IOException{
        StringBuilder sb = new StringBuilder();
        String line;
        while (reader.ready()) {
            line = reader.readLine();
            sb.append(line).append("\n");
        }

        message = sb.toString();
        responseCode = 418;
        status = "I'm a Teapot";
        comment = "Literally Teapot";
    }

    public void post(String query) throws IOException {
        writer.write(query);
        writer.flush();
        System.out.println("Posting query: " + query);
    }
}
