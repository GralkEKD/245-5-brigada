package TSiPVSEVM.upr2.rfc2229.client.handler;

import java.io.*;

public class ResponseHandler {
    private static final int BUFF_SIZE = 1024;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    private String[] status;
    private String[] comment;

    public String[] getStatus() {
        return status;
    }

    public String[] getComment() {
        return comment;
    }

    public ResponseHandler(InputStream in, OutputStream out) {
        reader = new BufferedReader(new InputStreamReader(in), BUFF_SIZE);
        writer = new BufferedWriter(new OutputStreamWriter(out), BUFF_SIZE);
        try {
            parseResponse();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void parseResponse() throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
            if (line.matches("^150.*") ||
                    line.matches("^220.*") ||
                    line.matches("^250.*") ||
                    line.startsWith("4") ||
                    line.startsWith("5") ||
                    line.equals(".")) {
                break;
            }
        }
        String message = sb.toString();

        if (message.startsWith("110") || message.startsWith("111")) {
            String[] messageLines = message.split("\n");
            String[] array = messageLines[0].split(" ", 3);
            int messageLength = Integer.parseInt(array[1]);
            status = new String[messageLength];
            comment = new String[messageLength];

            for (int i = 1; i <= messageLength; i++) {
                int indexOfDelimiter = messageLines[i].indexOf('"');
                status[i - 1] = messageLines[i].substring(0, indexOfDelimiter - 1);
                comment[i - 1] = messageLines[i].substring(indexOfDelimiter + 1, messageLines[i].length() - 1);
            }
        }

        if (message.startsWith("150")) {
            String[] array = message.split(" ", 3);
            int messageLength = Integer.parseInt(array[1]);
            StringBuilder definitions = new StringBuilder();
            for (int i = 0; i <= messageLength; i++) {
                parseResponse();
                definitions.append(comment[1], 0, comment[1].length() - 1);
            }
            comment = new String[1];
            comment[0] = definitions.toString();
        }

        if (message.startsWith("151")) {
            comment = message.split("\n", 2);
        }
    }

    public void post(String query) throws IOException {
        writer.write(query);
        writer.flush();
        System.out.print("Posting query: " + query);
    }
}
