package TSiPVSEVM.upr2.rfc2229.server.handler;

import TSiPVSEVM.upr2.rfc2229.database.Database;
import TSiPVSEVM.upr2.rfc2229.database.WordRepository;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler {

    private static final Logger LOGGER = Logger.getLogger(RequestHandler.class.getName());

    private static final int BUFF_SIZE = 1024;

    private static final String CONNECTION_INITIATED =
            "220 host (version 0.1)\r\n";

    private String getDatabases() throws ResponseException {
        StringBuilder response = new StringBuilder();

        Database[] databases = Database.values();
        if (databases.length == 0) throw new ResponseException(554, "No databases available");

        response.append("110 ")
                .append(databases.length)
                .append(" databases present: list follows\r\n");
        for (Database db : databases) {
            response.append(db == Database.EXCLAMATION ? "!" :
                            db == Database.ASTERISK ? "*" : db)
                    .append(" ")
                    .append(db.getName())
                    .append("\r\n");
        }
        response.append(".\r\n");
        response.append("250 Command complete\r\n");

        LOGGER.log(Level.FINE, "Response built:\n" + response);
        return response.toString();
    }

    private String getStrategies() throws ResponseException {
        StringBuilder response = new StringBuilder();

        Strategy[] strategies = Strategy.values();
        if (strategies.length == 0) throw new ResponseException(555, "No strategies available");

        response.append("111 ")
                .append(strategies.length)
                .append(" strategies present: list follows\r\n");
        for (Strategy strat : strategies) {
            response.append(strat)
                    .append(" ")
                    .append(strat.getName())
                    .append("\r\n");
        }
        response.append(".\r\n");
        response.append("250 Command complete\r\n");

        LOGGER.log(Level.FINE, "Response built:\n" + response);
        return response.toString();
    }

    private static final String HTTP_RESPONSE = """
                    HTTP/1.1 403 Forbidden
                    Content-Length: 210
                    Content-Type: text/html
                    
                    <!DOCTYPE html>
                    <html lang="en">
                        <head>
                            <title>DICT Protocol Server</title>
                        </head>
                        <body>
                            <p>DICT Protocol Server. Please use valid user-agent to get access</p>
                        </body>
                    </html>
                    """;

    private final BufferedReader input;

    private final BufferedWriter output;

    private WordRepository repository;

    private boolean isConnectionOpen = true;

    public RequestHandler(InputStream inputStream, OutputStream outputStream) {
        input = new BufferedReader(new InputStreamReader(inputStream), BUFF_SIZE);
        output = new BufferedWriter(new OutputStreamWriter(outputStream), BUFF_SIZE);
    }

    public void sendInitialResponse() throws IOException {
        if (input.ready()) doHandle();
        if (isConnectionOpen) {
            output.write(CONNECTION_INITIATED);
            output.flush();
        }
        LOGGER.log(Level.FINE, "Initial response sent");
    }

    public void doHandle() throws IOException {
        LOGGER.log(Level.FINE, "Handling started");
        String request = input.readLine();
        if (Objects.isNull(request)) throw new IOException("Connection terminated by client");
        LOGGER.log(Level.FINE, "Received command: " + request);

        try {
            RequestBody body = new RequestBody(request.toLowerCase());

            switch (body.getCommand()) {

                case "get", "post", "delete": {
                    LOGGER.log(Level.WARNING, "Client tried to send an HTTP method");
                    output.write(HTTP_RESPONSE);
                    output.flush();
                    isConnectionOpen = false;
                    throw new IOException("Client tried to access server via HTTP instead of DICT");
                }
                case "show": {
                    if (body.tokens.length != 2) throw new ResponseException(501, "Syntax error, illegal parameters");
                    if (body.getMainArgument().equals("db") ||
                            body.getMainArgument().equals("databases")) {
                        output.write(getDatabases());
                    } else if (body.getMainArgument().equals("strat") ||
                            body.getMainArgument().equals("strategies")) {
                        output.write(getStrategies());
                    } else {
                        throw new ResponseException(501, "Syntax error, illegal parameters");
                    }
                    break;
                }

                case "quit": {
                    isConnectionOpen = false;
                    output.write("221 Connection closed");
                    if (body.tokens.length > 1)
                        output.write(" (I mean, you could just write \"quit\" but whatever)");
                    output.write("\r\n");
                    break;
                }

                case "define", "match": {
                    /* Cases in sake of testing */
                    throw new ResponseException(502, "Syntax error, command not implemented");
                }

                default: {
                    throw new ResponseException(500, "Syntax error, command undefined");
                }
            }
        } catch (ResponseException e) {
            output.write(e.getCode() + " " + e.getMessage() + "\n");
        } finally {
            output.flush();
            if (!isConnectionOpen) output.close();
        }
    }

    public boolean isConnectionOpen() {
        return isConnectionOpen;
    }

    private static class RequestBody {

        private final String[] tokens;

        private RequestBody(String incomingRequest) {
            tokens = incomingRequest.split(" ");
        }

        String getCommand() {
            return tokens[0];
        }

        String getMainArgument() {
            return tokens[tokens.length - 1];
        }

        String getDatabase() {
            return tokens[1];
        }

        String getStrategy() {
            if (getCommand().equals("match")) {
                return tokens[2];
            }
            return null;
        }

        @Override
        public String toString() {
            return "RequestBody{" +
                    "tokens=" + Arrays.toString(tokens) +
                    '}';
        }
    }
}
