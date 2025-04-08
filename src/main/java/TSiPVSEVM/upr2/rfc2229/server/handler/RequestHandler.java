package TSiPVSEVM.upr2.rfc2229.server.handler;

import TSiPVSEVM.upr2.rfc2229.database.Database;
import TSiPVSEVM.upr2.rfc2229.database.WordRepository;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RequestHandler {

    private static final int BUFF_SIZE = 1024;

    private static final String CONNECTION_INITIATED =
            "220 host (version 0.1)\n";

    private static final String strats = """
                                111 4 strategies present: list follows
                                exact "Match words exactly"
                                prefix "Match word prefixes"
                                substring "Match substrings anywhere in word"
                                regex "Match using regular expressions"
                                .
                                250 Command complete
                                """;

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

    private final WordRepository repository;

    private boolean isConnectionOpen = true;

    public RequestHandler(InputStream inputStream, OutputStream outputStream) {
        input = new BufferedReader(new InputStreamReader(inputStream), BUFF_SIZE);
        output = new BufferedWriter(new OutputStreamWriter(outputStream), BUFF_SIZE);

        /* Dud while no implementation */
        repository = List::of;
    }

    public void sendInitialResponse() throws IOException {
        output.write(CONNECTION_INITIATED);
        output.flush();
    }

    public void doHandle() throws IOException {
        String request = input.readLine();
        if (Objects.isNull(request)) throw new IOException("Connection terminated by client");

        try {
            RequestBody body = new RequestBody(request.toLowerCase());

            StringBuilder response = new StringBuilder();
            switch (body.getCommand()) {

                case "get", "post", "delete": {
                    output.write(HTTP_RESPONSE);
                    isConnectionOpen = false;
                    throw new IOException("Client tried to access server via HTTP instead of DICT");
                }
                case "show": {
                    if (body.tokens.length != 2) throw new ResponseException(501, "Syntax error, illegal parameters");
                    if (body.getMainArgument().equals("db") ||
                            body.getMainArgument().equals("databases")) {
                        Database[] databases = Database.values();
                        if (databases.length == 0) throw new ResponseException(554, "No databases available");

                        response.append("110 ")
                                .append(databases.length)
                                .append(" databases present: list follows\n");
                        for (Database db : databases) {
                            response.append(db == Database.EXCLAMATION ? "!" :
                                            db == Database.ASTERISK ? "*" : db)
                                    .append(" ")
                                    .append(db.getName())
                                    .append("\n");
                        }
                        response.append(".\n");
                        response.append("250 Command complete\n");
                        output.write(response.toString());
                    } else if (body.getMainArgument().equals("strat") ||
                            body.getMainArgument().equals("strategies")) {
                        output.write(strats);
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
                    output.write("\n");
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

        private RequestBody(String incomingRequest) throws ResponseException {
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
